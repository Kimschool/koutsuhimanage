package koutsuhi.modify;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import koutsuhi.data.DataUtil;
import koutsuhi.list.ListFrame;
import koutsuhi.login.LoginFrame;

public class ModifyFrame extends JFrame{

	JTextField t1;
	JTextField t2;
	JTextField t3;
	JTextField t4;

	public ModifyFrame(int selectedRow, String selectedDate) {
		setTitle("교통비정산 시스템 - 수정");
		setSize(300,200);
		setLocationRelativeTo(null);

		setLayout(new BorderLayout());

        DataUtil du = new DataUtil();
        String[][] userInfoArr = du.loadUserTransInfo(selectedDate, LoginFrame.userId);

        String[] selectedData = userInfoArr[selectedRow];
        String date = selectedData[0];
        String d_station = selectedData[1];
        String a_station = selectedData[2];
        String fare = selectedData[3];

		JPanel p1 = new JPanel(new GridLayout(4,2));

		JLabel l1 = new JLabel("날짜");
		t1 = new JTextField(date);
		p1.add(l1);
		p1.add(t1);
		JLabel l2 = new JLabel("출발역");
		t2 = new JTextField(d_station);
		p1.add(l2);
		p1.add(t2);
		JLabel l3 = new JLabel("도착역");
		t3 = new JTextField(a_station);
		p1.add(l3);
		p1.add(t3);
		JLabel l4 = new JLabel("금액");
		t4 = new JTextField(fare);
		p1.add(l4);
		p1.add(t4);

		add(p1, BorderLayout.CENTER);

		JPanel p2 = new JPanel();
		JButton b1 = new JButton("뒤로가기");

		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ListFrame();

			}
		});
		JButton b2 = new JButton("수정");

		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean check_success = checkInputData();

				if(check_success) {
					// 파일저장
					modifyData(userInfoArr, selectedRow, selectedDate, false);

				}

			}
		});

		JButton b3 = new JButton("삭제");

		b3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				modifyData(userInfoArr, selectedRow, selectedDate, true);
			}
		});
		p2.add(b1);
		p2.add(b2);
		p2.add(b3);

		add(p2, BorderLayout.SOUTH);

		setVisible(true);


	}

	public void modifyData(String[][] data, int selectedRow, String selectedDate, boolean isDelete) {

		String path = "src\\data\\"+ selectedDate + "\\" + LoginFrame.userId + ".csv" ;

		try {
			FileWriter fileWriter = new FileWriter(path, false);
			StringBuffer sb = new StringBuffer();

			for(int i = 0; i < data.length; i++) {
				if(i == selectedRow && !isDelete) {
					sb.append(t1.getText());
					sb.append(",");
					sb.append(t2.getText());
					sb.append(",");
					sb.append(t3.getText());
					sb.append(",");
					sb.append(t4.getText());
					sb.append("\n");
				} else if(i == selectedRow && isDelete) {
					continue;
				} else {
					sb.append(convertLine(data[i]));
				}
			}

			fileWriter.write(sb.toString());

			// 파일 닫기
			fileWriter.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "파일저장에 실패 했습니다.");
		}

	}

	public String convertLine(String[] line) {
		String conv_text = Arrays.toString(line);
		conv_text = conv_text.replace("[", "");
		conv_text = conv_text.replace("]", "\n");
		return conv_text;
	}

	public boolean checkInputData() {
		// yyyy-MM-dd
		String regex = "^\\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[1-2][0-9]|3[0-1])$";

        // 정규식 패턴을 컴파일
        Pattern pattern = Pattern.compile(regex);

        // 입력된 날짜를 체크한다.
        Matcher matcher = pattern.matcher(t1.getText());

        // 일치 여부 반환
        if(!matcher.matches()) {
			JOptionPane.showMessageDialog(null, "날짜는 yyyy-mm-dd 형식으로 입력해주세요.");
			return false;
        }

		String regex1 = "^[가-힣]+$";
		pattern = Pattern.compile(regex1);
		matcher = pattern.matcher(t2.getText());

        // 일치 여부 반환
        if(!matcher.matches()) {
			JOptionPane.showMessageDialog(null, "출발역은 한글로만 입력가능합니다.");
			return false;
        }

		matcher = pattern.matcher(t3.getText());

        // 일치 여부 반환
        if(!matcher.matches()) {
			JOptionPane.showMessageDialog(null, "도착역은 한글로만 입력가능합니다.");
			return false;
        }

		String regex2 = "^[1-9]\\d*$";
		pattern = Pattern.compile(regex2);
		matcher = pattern.matcher(t4.getText());

        // 일치 여부 반환
        if(!matcher.matches()) {
			JOptionPane.showMessageDialog(null, "금액은 정수로만 입력 가능합니다.");
			return false;
        }

        return true;


	}

}
