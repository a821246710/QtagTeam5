import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import java.awt.EventQueue;
import java.awt.Frame;
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
import java.io.Console;

class CFrame extends JFrame implements ActionListener{
	JLabel lblAccount = new JLabel("Account");
	JTextField txtAccountInput = new JTextField("");
	JLabel lblPassword = new JLabel("Password");
	JTextField txtPasswordInput = new JPasswordField("");
	JButton btnOK = new JButton("LOGIN");
	JLabel lblResult = new JLabel("");

	Auth auth = new Auth();
	
	CFrame(){
		setTitle("QTag Login");
		setLocation(200,150);
		setSize(300,250);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

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

	public void resetText(){
		txtAccountInput.setText("");
		txtPasswordInput.setText("");
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == btnOK){
			String AC = txtAccountInput.getText();
			String PW = txtPasswordInput.getText();

			Account acc = new Account(AC, PW);
			
			if(auth.login(acc)){
				TagSystemMainGUI.frame.setVisible(true);
				TagSystemMainGUI.labelAcc.setText("Account: " + auth.getAccount().getId());
				resetText();
				setVisible(false);
			}
			else{
				lblResult.setText("Failure");
			}
		}
	} 
	
}
