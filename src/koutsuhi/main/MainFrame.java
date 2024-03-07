package koutsuhi.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import koutsuhi.list.ListFrame;

public class MainFrame extends JFrame{

	public MainFrame() {
		setTitle("교통비정산 시스템 - 메인");
		setSize(300,200);
		setLocationRelativeTo(null);


		JButton b1 = new JButton("리스트보기");
		add(b1);

		setVisible(true);

		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ListFrame();
			}
		});

	}

	public static void main(String[] args) {
		new MainFrame();
	}

}
