import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.BoxLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

import java.awt.event.*;

class CFrame extends JFrame implements ActionListener{
	JLabel lblAccount = new JLabel("Account");
	JTextField txtAccountInput = new JTextField("");
	JLabel lblPassword = new JLabel("Password");
	JTextField txtPasswordInput = new JTextField("");
	JButton btnOK = new JButton("LOGIN");
	JLabel lblResult = new JLabel("");



	CFrame(){
		setTitle("QTag Login");											//�إߤ@�ӷs��A�ó]�w�����D�C��r
		setLocation(200,150);
		setSize(300,250);												//�]�w��b�ù��W��ܪ���m�P�j�p�A�i�X�ּg��setBounds(100, 120, 400, 200);(�ù���m�B��j�p)
		setVisible(true);												//�]�w��i�H��ܦb�ù��W
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		

		//�]�w���һP��r���P���s
		lblAccount.setBounds(10,10,200,20);
		add(lblAccount);

		txtAccountInput.setBounds(10, 35, 200, 20);
		add(txtAccountInput);
		txtAccountInput.addActionListener(this);

		lblPassword.setBounds(10,70,200,20);
		add(lblPassword);

		txtPasswordInput.setBounds(10, 95, 200, 20);
		add(txtPasswordInput);
		txtPasswordInput.addActionListener(this);

		btnOK.setBounds(10,140,80,30);
		add(btnOK);
		btnOK.addActionListener(this);
		
		lblResult.setBounds(110,140,150,30);
		add(lblResult);
		
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == btnOK){
			String AC = txtAccountInput.getText();
			String PW = txtPasswordInput.getText();
			String ManagerAccount = "Xia100K";
			String UserAccount = "jim1217";
			String ManagerPassword = "3345678";
			String UserPassword = "b10115038";
			if(AC.compareTo(ManagerAccount)==0 && PW.compareTo(ManagerPassword)==0 || AC.compareTo(UserAccount)==0 && PW.compareTo(UserPassword)==0){
				lblResult.setText("Success");
				TagSystemMainGUI.frame.setVisible(true);
				setVisible(false);
				
			}
			else{
				lblResult.setText("Failure");
			}
		}
	} 
	
}
