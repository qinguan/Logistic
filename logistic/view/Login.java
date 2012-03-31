package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import db.entity.DBCode;

public class Login extends JFrame{
	
	//系统名称
	private JLabel headLabel;
	//界面图形
	private ImageIcon icon ;
	private JLabel image;	
	private JPanel headPanel ;
	
	
	//界面输入
	private JLabel upLabel;
	private JTextField inputTextField;
	private JLabel downLabel;
	private JPasswordField inputPasswordField;
	private JPanel middlePanel;
	
	//登录确认按钮
	private JButton ensureButton;
	private JButton cancelButton;	
	private JPanel bottomPanel;
	
	
	private Container contentPanel = getContentPane();
	
	//字体设置
	Font font0 = new Font("SanSerif" , Font.BOLD,30);
	
	//登录主界面
	public Login(){	
		initialize();
	}

	private void Login_Interface(){
		
		headLabel = new JLabel("欢迎进入后勤管理系统",JLabel.CENTER);
		headLabel.setFont(font0);
		headLabel.setHorizontalTextPosition(headLabel.CENTER); 
		icon = new ImageIcon("beihang.jpg");
		image=new JLabel(icon);
		
		headPanel = new JPanel(new BorderLayout());
		headPanel.add(headLabel,BorderLayout.NORTH);
		headPanel.add(image,BorderLayout.SOUTH);
		
		
		upLabel = new JLabel("用户名：");
		upLabel.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		inputTextField = new JTextField(18);
		downLabel = new JLabel("密码：");
		downLabel.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		inputPasswordField = new JPasswordField(18);
		
		middlePanel = new JPanel(new FlowLayout());
		middlePanel.add(upLabel);
		middlePanel.add(inputTextField);
		middlePanel.add(downLabel);
		middlePanel.add(inputPasswordField);
		
		ensureButton = new JButton("确定");
		ensureButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				actionEnsureButton(); 
			}
		});
		
		cancelButton = new JButton("取消");
		cancelButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				actionExit();
			}
		});
		bottomPanel = new JPanel(new FlowLayout());
		bottomPanel.add(ensureButton);
		bottomPanel.add(cancelButton);
		
		contentPanel.setLayout(new BorderLayout());
		contentPanel.add(headPanel,BorderLayout.NORTH);
		contentPanel.add(middlePanel,BorderLayout.CENTER);
		contentPanel.add(bottomPanel,BorderLayout.SOUTH);
			
		contentPanel.setVisible(true);
		
	} 

	private void initialize() {
		this.setSize(800, 600);
//		********************************************************
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = screenSize.width/2;
		int centerY = screenSize.height/2;
		int halfwidth = frameSize.width/2;
		int halfHeight = frameSize.height/2;
		this.setLocation(centerX-halfwidth, centerY-halfHeight);
//	**********************************************************8
		
		this.setTitle("后勤管理系统");
		Login_Interface();
		
		this.setVisible(true);
		this.setResizable(false);
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				actionExit();
			}
		});
	}
	
	/**
	 * 退出
	 */
	public static void actionExit(){
		System.exit(0);
	}
	
	/**
	 * 对ensureButton的监听动作
	 */
	private void actionEnsureButton(){
		String name = inputTextField.getText();
		String passwd = new String(inputPasswordField.getPassword());
		
		int flag = DBCode.serchName(name, passwd);
		
		if(flag == 1){
			LogisticGui gui = new LogisticGui();
			gui.setVisible(true);
			this.dispose();//当前界面消失
		}
		else if(flag == 2){
			JOptionPane.showMessageDialog(null,
					"wrong password","",JOptionPane.WARNING_MESSAGE);
		}
		else {
			JOptionPane.showMessageDialog(null,
					"no such user.","",JOptionPane.WARNING_MESSAGE);
		}
		
	}
	
	public static void main(String[] args) {
		Login login = new Login();
		login.setVisible(true);
	}
}
