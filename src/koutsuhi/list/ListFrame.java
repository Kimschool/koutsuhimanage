package koutsuhi.list;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import koutsuhi.data.DataUtil;
import koutsuhi.login.LoginFrame;

public class ListFrame extends JFrame{

	public ListFrame() {
		setTitle("교통비정산 시스템 - 리스트");
		setSize(300,200);
		setLocationRelativeTo(null);

		setLayout(new BorderLayout());

		JButton a1 = new JButton("<");
		JButton a2 = new JButton(">");

		JPanel p1 = new JPanel();
		  // 현재 날짜를 가져오기
        LocalDate currentDate = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

        String formattedDate = currentDate.format(formatter);

        JLabel l1 = new JLabel(formattedDate);
        p1.add(a1);
        p1.add(l1);
        p1.add(a2);
        add(p1, BorderLayout.NORTH);

        String[] columnNames = {"DATE", "D_STATION", "A_STATION", "PARE"};

        DataUtil du = new DataUtil();
        String[][] userInfoArr = du.loadUserTransInfo(formattedDate, LoginFrame.userId);

        int sum = 0;
        for(int i = 0; i<userInfoArr.length - 1; i++) {
        	sum+=Integer.parseInt(userInfoArr[i][3]);
        }

        DefaultTableModel model = new DefaultTableModel(userInfoArr, columnNames);

        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

        JPanel p2 = new JPanel(new FlowLayout());
        JButton b1 = new JButton("뒤로가기");
        JButton b2 = new JButton("수정하기");
        JLabel l2 = new JLabel("합계");
        JTextField t2 = new JTextField(sum + "엔");
        t2.setEditable(false);
        p2.add(b1);
        p2.add(b2);
        p2.add(l2);
        p2.add(t2);

        add(p2, BorderLayout.SOUTH);

        setVisible(true);
	}

	public static void main(String[] args) {
		new ListFrame();
	}

}
