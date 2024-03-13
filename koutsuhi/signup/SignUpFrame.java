package koutsuhi.signup;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import koutsuhi.login.LoginFrame;

public class SignUpFrame extends JFrame {

	JTextField t1 = new JTextField(10);
	JPasswordField t2 = new JPasswordField(10);
	JPasswordField t3 = new JPasswordField(10);
	JTextField t4 = new JTextField(10);
	JRadioButton r1 = new JRadioButton("남");
	JRadioButton r2 = new JRadioButton("여");

	boolean inputErrorFlag = false;

	public SignUpFrame() {
		setTitle("교통비정산 시스템 - 회원가입");
		setSize(300,300);
		setLocationRelativeTo(null);

		JPanel panel1 = new JPanel(new GridLayout(6,2));
		JLabel l1 = new JLabel("ID");

		JLabel l2 = new JLabel("PW");

		JLabel l3 = new JLabel("PW확인용");

		JLabel l4 = new JLabel("이름");

		JLabel l5 = new JLabel("성별");
		JPanel genderPanel = new JPanel();
		ButtonGroup buttonGroup = new ButtonGroup();


        // 버튼 그룹에 라디오버튼을 추가
        buttonGroup.add(r1);
        buttonGroup.add(r2);

        genderPanel.add(r1);
        genderPanel.add(r2);

        JButton b1 = new JButton("등록");

        b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				inputCheck();

				if(!inputErrorFlag) {
					saveUserInfo();
				}

			}
		});

        JButton b2 = new JButton("뒤로가기");

        b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new LoginFrame();
			}
		});

		panel1.add(l1);
		panel1.add(t1);
		panel1.add(l2);
		panel1.add(t2);
		panel1.add(l3);
		panel1.add(t3);
		panel1.add(l4);
		panel1.add(t4);
		panel1.add(l5);
		panel1.add(genderPanel);
		panel1.add(b1);
		panel1.add(b2);
		add(panel1);


		setVisible(true);

	}

	void inputCheck() {

		String pw1 = String.valueOf(t2.getPassword());
		String pw2 = String.valueOf(t3.getPassword());

		// 공백체크
		if(t1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "ID를 입력해주세요");
            inputErrorFlag =true;
		}
		if(pw1.isEmpty()) {
			JOptionPane.showMessageDialog(null, "PW를 입력해주세요");
			inputErrorFlag =true;
		}
		if(pw2.isEmpty()) {
			JOptionPane.showMessageDialog(null, "확인용PW를 입력해주세요");
			inputErrorFlag =true;
		}

		if(t4.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "이름을 입력해주세요");
			inputErrorFlag =true;
		}
		if(!r1.isSelected() && !r2.isSelected()) {
			JOptionPane.showMessageDialog(null, "성별을 입력해주세요");
			inputErrorFlag =true;
		}

		// 패스워드 체크
		if(!pw1.equals(pw2)) {
			JOptionPane.showMessageDialog(null, "패스워드가 일치하지 않습니다.");
			inputErrorFlag =true;
		}

	}

	void saveUserInfo() {

		String path = "C:\\soon\\202402\\swing\\src\\userinfo.txt";

		try {
			// FileWriter를 사용하여 파일 생성 또는 열기 (두 번째 매개변수는 append 여부)
            FileWriter fileWriter = new FileWriter(path, true);

            // 파일에 쓸 내용 작성
            StringBuffer sb = new StringBuffer();
            sb.append(t1.getText());
            sb.append(",");
            sb.append(t2.getPassword());
            sb.append(",");
            sb.append(t3.getText());
            sb.append(",");
            sb.append(t4.getText());
            sb.append(",");
            if(r1.isSelected()) {
            	sb.append("0");
            } else {
            	sb.append("1");
            }
            sb.append("\n");

            fileWriter.write(sb.toString());

            // 파일 닫기
            fileWriter.close();

			JOptionPane.showMessageDialog(null, "회원가입이 완료 되었습니다.");
			setVisible(false);
			new LoginFrame();

		}catch (Exception e) {
			System.out.println("파일 생성 중 오류가 발생했습니다.");
            e.printStackTrace();
		}


	}




}
