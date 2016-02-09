package mis;

import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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

import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@SuppressWarnings("serial")
public class LMSLoginT extends JFrame {
	
	public String accMode;
	private static Point point = new Point();
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JButton btnLogin;
	private JLabel lblRegistrationNumber;
	private JTextField txtRegNo;
	private JLabel label;
	private JLabel lblOr;
	private JButton btnCreateAccount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
					LMSLoginT frame = new LMSLoginT();
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

	private Image resizeImage(Image img, int w, int h)
	{
		BufferedImage resizedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resizedImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(img, 0, 0, w, h, null);
        return resizedImage;
	}
	Connection connection=null;
	/**
	 * Create the frame.
	 */
	public LMSLoginT() {
		connection = DBConnector.dbConnector();
		setIconImage(Toolkit.getDefaultToolkit().getImage(LMSLoginT.class.getResource("/Resources/Custo.Man.Christmas.Folder.Library.ico.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 258);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
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
				MIS_HomeTeacher.main(null);
			}
		});
		btnX.setBackground(Color.RED);
		btnX.setBounds(411, 1, 39, 23);
		btnX.setFocusable(false);
		contentPane.add(btnX);
		
		Border emptyBorder = BorderFactory.createEmptyBorder();
		
		JLabel lblEnterMasterPassword = new JLabel("Enter Acccess Code ");
		lblEnterMasterPassword.setFont(new Font("Ubuntu", Font.BOLD, 15));
		lblEnterMasterPassword.setForeground(Color.WHITE);
		lblEnterMasterPassword.setBounds(44, 84, 171, 23);
		contentPane.add(lblEnterMasterPassword);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		passwordField.setBounds(225, 85, 153, 23);
		passwordField.setBorder(emptyBorder);
		contentPane.add(passwordField);
		
		btnLogin = new JButton("Login");
		btnLogin.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					if(txtRegNo.getText().equals("") || passwordField.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Enter Registration Number & Access Code");
					else
					{
						try
						{
							String Query = "select * from LibMem where MemID=? and Password=?";
							PreparedStatement pst = connection.prepareStatement(Query);
							pst.setString(1, txtRegNo.getText());
							pst.setString(2, passwordField.getText());
							ResultSet rs = pst.executeQuery();
							int count=0;
							while(rs.next())
							{
								if(rs.getString(1).equals("LibAdmin"))
									LMSAdminHome.main(null);
								
								else
								{
									String Query1 = "select * from TeachersMF where RegNo=?";
									PreparedStatement pst1 = connection.prepareStatement(Query1);
									pst1.setString(1, txtRegNo.getText());
									ResultSet rs1 = pst1.executeQuery();
									while(rs1.next())
									{
										byte[] img = rs1.getBytes(17);
										BufferedImage bufimg;
										try {
											bufimg = ImageIO.read(new ByteArrayInputStream(img));
											LMSLocalHome frame = new LMSLocalHome();
											Image prof = resizeImage(bufimg, frame.lblProfilePicture.getWidth(), frame.lblProfilePicture.getHeight());
											frame.lblID.setText(rs1.getString(1));
											frame.lblName.setText(rs1.getString(2));
											ImageIcon profilePic = new ImageIcon(prof);
											frame.lblProfilePicture.setIcon(profilePic);
											frame.setUndecorated(true);
											frame.setVisible(true);
										}
										catch (IOException e1) 
										{
											e1.printStackTrace();
										}
										
									}
										
								}
									
								count++;
							}
							
							if(count==1)
							{
								CloseFrame();
							}
							else if (count==0)
							{
								JOptionPane.showMessageDialog(null, "Invalid Username & Password!", "Error", JOptionPane.WARNING_MESSAGE);
								clear();
							}
							
							rs.close();
							pst.close();
							
						}
						catch(SQLException ex)
						{
							JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.WARNING_MESSAGE);
						}
						finally
						{
							clear();
						}
				}
			}
		}});
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				if(txtRegNo.getText().equals("") || passwordField.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Enter Registration Number & Access Code");
				else
				{
					try
					{
						String Query = "select * from LibMem where MemID=? and Password=?";
						PreparedStatement pst = connection.prepareStatement(Query);
						pst.setString(1, txtRegNo.getText());
						pst.setString(2, passwordField.getText());
						ResultSet rs = pst.executeQuery();
						int count=0;
						while(rs.next())
						{
							if(rs.getString(1).equals("LibAdmin"))
								LMSAdminHome.main(null);
							
							else
							{
								String Query1 = "select * from TeachersMF where RegNo=?";
								PreparedStatement pst1 = connection.prepareStatement(Query1);
								pst1.setString(1, txtRegNo.getText());
								ResultSet rs1 = pst1.executeQuery();
								while(rs1.next())
								{
									byte[] img = rs1.getBytes(17);
									BufferedImage bufimg;
									try {
										bufimg = ImageIO.read(new ByteArrayInputStream(img));
										LMSLocalHome frame = new LMSLocalHome();
										Image prof = resizeImage(bufimg, frame.lblProfilePicture.getWidth(), frame.lblProfilePicture.getHeight());
										frame.lblID.setText(rs1.getString(1));
										frame.lblName.setText(rs1.getString(2));
										ImageIcon profilePic = new ImageIcon(prof);
										frame.lblProfilePicture.setIcon(profilePic);
										frame.setUndecorated(true);
										frame.setVisible(true);
									}
									catch (IOException e1) 
									{
										e1.printStackTrace();
									}
									
								}
									
							}
								
							count++;
						}
						
						if(count==1)
						{
							CloseFrame();
						}
						else if (count==0)
						{
							JOptionPane.showMessageDialog(null, "Invalid Username & Password!", "Error", JOptionPane.WARNING_MESSAGE);
							clear();
						}
						
						rs.close();
						pst.close();
						
					}
					catch(SQLException ex)
					{
						JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.WARNING_MESSAGE);
					}
					finally
					{
						clear();
					}
			}
			}
		});
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(Color.DARK_GRAY);
		btnLogin.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		btnLogin.setBounds(168, 137, 135, 23);
		contentPane.add(btnLogin);
		
		lblRegistrationNumber = new JLabel("Registration Number");
		lblRegistrationNumber.setForeground(Color.WHITE);
		lblRegistrationNumber.setFont(new Font("Ubuntu", Font.BOLD, 15));
		lblRegistrationNumber.setBounds(44, 48, 171, 23);
		contentPane.add(lblRegistrationNumber);
		
		txtRegNo = new JTextField();
		txtRegNo.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtRegNo.setBounds(225, 51, 153, 23);
		txtRegNo.setBorder(emptyBorder);
		txtRegNo.setFocusable(true);
		txtRegNo.requestFocus();
		contentPane.add(txtRegNo);
		txtRegNo.setColumns(10);
		
		label = new JLabel("?");
		label.setToolTipText("Forgot Password?");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Ubuntu", Font.PLAIN, 11));
		label.setBounds(382, 90, 19, 14);
		contentPane.add(label);
		
		lblOr = new JLabel("OR");
		lblOr.setForeground(Color.WHITE);
		lblOr.setFont(new Font("Ubuntu", Font.PLAIN, 11));
		lblOr.setBounds(231, 171, 24, 14);
		contentPane.add(lblOr);
		
		btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					CreateAccount.main(null);
				}
			}
		});
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateAccount.main(null);
			}
		});
		btnCreateAccount.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		btnCreateAccount.setBackground(Color.DARK_GRAY);
		btnCreateAccount.setForeground(Color.WHITE);
		btnCreateAccount.setBounds(168, 196, 135, 23);
		contentPane.add(btnCreateAccount);

	}
	
	public void CloseFrame()
	{
		super.dispose();
	}
	
	public void clear()
	{
		txtRegNo.setText("");
		passwordField.setText(null);
	}
}
