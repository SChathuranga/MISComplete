package mis;

import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Font;

import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.SwingConstants;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class MIS_Login extends JFrame {
	
	private static Point point = new Point();
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JButton btnLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
					MIS_Login frame = new MIS_Login();
					frame.setUndecorated(true);
					
					//{MouseListener
					frame.addMouseListener(new MouseAdapter() {
						public void mousePressed(MouseEvent e)
						{
							point.x = e.getX();
							point.y = e.getY();
						}
					});
					
					frame.addMouseMotionListener(new MouseMotionAdapter() {
						public void mouseDragged(MouseEvent e)
						{
							Point p = frame.getLocation();
							frame.setLocation(p.x + e.getX() - point.x, p.y + e.getY() - point.y);
						}
						
					});
					//}
					
					frame.setVisible(true);
			}
		});
	}

	Connection connection=null;
	/**
	 * Create the frame.
	 */
	public MIS_Login() {
		connection= DBConnector.dbConnector();
		setIconImage(Toolkit.getDefaultToolkit().getImage(MIS_Login.class.getResource("/Resources/Custo.Man.Christmas.Folder.Library.ico.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 175);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new LineBorder(Color.BLUE, 1, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		StartPosition.centerOnScreen(this);
		
		JButton btnX = new JButton("X");
		btnX.setForeground(Color.WHITE);
		btnX.setFont(new Font("Ubuntu", Font.BOLD, 8));
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CloseFrame();
				MIS_Initial.main(null);
			}
		});
		btnX.setBackground(Color.RED);
		btnX.setBounds(411, 1, 39, 23);
		btnX.setFocusable(false);
		contentPane.add(btnX);
		
		JLabel lblEnterMasterPassword = new JLabel("Enter Access Code : ");
		lblEnterMasterPassword.setFont(new Font("Ubuntu", Font.BOLD, 15));
		lblEnterMasterPassword.setForeground(Color.WHITE);
		lblEnterMasterPassword.setBounds(47, 68, 171, 23);
		contentPane.add(lblEnterMasterPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBorder(BorderFactory.createEmptyBorder());
		passwordField.setBounds(228, 71, 153, 20);
		contentPane.add(passwordField);
		passwordField.setFocusable(true);
		
		btnLogin = new JButton("Login");
		btnLogin.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void keyPressed(KeyEvent evnt) {
				if(evnt.getKeyCode() == KeyEvent.VK_ENTER )
				{
					if(passwordField.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Enter Master Password", "Error", JOptionPane.WARNING_MESSAGE);
					else
					{
						try
						{
							String query = "select * from MasterPassword where Password=?";
							PreparedStatement pst = connection.prepareStatement(query);
							pst.setString(1, passwordField.getText());
							ResultSet rs = pst.executeQuery();
							int count=0;
							while(rs.next())
							{
								count++;
							}
							if(count==1)
							{
								MIS_HomeAdmin.main(null);
								CloseFrame();
							}
							else if(count==0)
							{
								JOptionPane.showMessageDialog(null, "Invalid Password!", "Error", JOptionPane.WARNING_MESSAGE);
								passwordField.setText("");
							}
						}
						catch(Exception ex)
						{
							JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.WARNING_MESSAGE);
						}
					}
				}
			}
		});
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(passwordField.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Enter Master Password", "Error", JOptionPane.WARNING_MESSAGE);
				else
				{
					try
					{
						String query = "select * from MasterPassword where Password=?";
						PreparedStatement pst = connection.prepareStatement(query);
						pst.setString(1, passwordField.getText());
						ResultSet rs = pst.executeQuery();
						int count=0;
						while(rs.next())
						{
							count++;
						}
						if(count==1)
						{
							MIS_HomeAdmin.main(null);
							CloseFrame();
						}
						else if(count==0)
						{
							JOptionPane.showMessageDialog(null, "Invalid Password!", "Error", JOptionPane.WARNING_MESSAGE);
							passwordField.setText("");
						}
					}
					catch(Exception ex)
					{
						JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.WARNING_MESSAGE);
					}
				}
				
			}
		});
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(Color.DARK_GRAY);
		btnLogin.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		btnLogin.setBounds(308, 102, 73, 23);
		contentPane.add(btnLogin);
		
		JLabel label = new JLabel("?");
		label.setToolTipText("Forgot Password?");
		label.setFont(new Font("Ubuntu", Font.PLAIN, 11));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setBounds(385, 74, 19, 14);
		contentPane.add(label);
		passwordField.requestFocus();
		passwordField.setVisible(true);
	}
	
	public void CloseFrame()
	{
		super.dispose();
	}
}
