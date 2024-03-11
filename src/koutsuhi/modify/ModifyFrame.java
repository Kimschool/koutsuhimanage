package koutsuhi.modify;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		JTextField t2 = new JTextField(d_station);
		p1.add(l2);
		p1.add(t2);
		JLabel l3 = new JLabel("도착역");
		JTextField t3 = new JTextField(a_station);
		p1.add(l3);
		p1.add(t3);
		JLabel l4 = new JLabel("금액");
		JTextField t4 = new JTextField(fare);
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
				checkInputData();

			}
		});

		JButton b3 = new JButton("삭제");
		p2.add(b1);
		p2.add(b2);
		p2.add(b3);

		add(p2, BorderLayout.SOUTH);

		setVisible(true);


	}

	public void checkInputData() {
		// yyyy-MM-dd
		String regex = "^\\d{4}-\\d{2}-\\d{2}$";

        // 정규식 패턴을 컴파일
        Pattern pattern = Pattern.compile(regex);

        // 입력된 날짜를 체크한다.
        Matcher matcher = pattern.matcher(t1.getText());

        // 일치 여부 반환
        if(!matcher.matches()) {
			JOptionPane.showMessageDialog(null, "날짜는 yyyy-mm-dd 형식으로 입력해주세요.");
        }
	}

}
