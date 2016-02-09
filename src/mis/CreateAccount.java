package mis;

import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.border.LineBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@SuppressWarnings("serial")
public class CreateAccount extends JFrame {

	private static Point point = new Point();
	private JPanel contentPane;
	private JTextField txtRegNo;
	private JTextField txtPass;
	private JTextField txtRPass;
	private JTextField txtSecurity;
	private JTextField txtAns;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateAccount frame = new CreateAccount();
					frame.setUndecorated(true);
					
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
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connection=null;
	/**
	 * Create the frame.
	 */
	public CreateAccount() {
		connection=DBConnector.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new LineBorder(Color.RED));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		StartPosition.centerOnScreen(this);
		
		JLabel lblEnterRegistrationNumber = new JLabel("Enter Registration Number");
		lblEnterRegistrationNumber.setForeground(Color.WHITE);
		lblEnterRegistrationNumber.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblEnterRegistrationNumber.setBounds(61, 61, 169, 21);
		contentPane.add(lblEnterRegistrationNumber);
		
		JLabel lblEnterAPassword = new JLabel("Enter A Password");
		lblEnterAPassword.setForeground(Color.WHITE);
		lblEnterAPassword.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblEnterAPassword.setBounds(61, 93, 169, 21);
		contentPane.add(lblEnterAPassword);
		
		JLabel lblReenterPassword = new JLabel("Re-enter Password");
		lblReenterPassword.setForeground(Color.WHITE);
		lblReenterPassword.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblReenterPassword.setBounds(61, 125, 169, 21);
		contentPane.add(lblReenterPassword);
		
		JLabel lblEnterSecurityQuestion = new JLabel("Enter Security Question");
		lblEnterSecurityQuestion.setForeground(Color.WHITE);
		lblEnterSecurityQuestion.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblEnterSecurityQuestion.setBounds(61, 157, 169, 21);
		contentPane.add(lblEnterSecurityQuestion);
		
		JLabel lblEnterAnswer = new JLabel("Enter Answer");
		lblEnterAnswer.setForeground(Color.WHITE);
		lblEnterAnswer.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblEnterAnswer.setBounds(61, 189, 169, 21);
		contentPane.add(lblEnterAnswer);
		
		txtRegNo = new JTextField();
		txtRegNo.setBorder(BorderFactory.createEmptyBorder());
		txtRegNo.setBounds(240, 62, 135, 20);
		contentPane.add(txtRegNo);
		txtRegNo.setColumns(10);
		
		txtPass = new JTextField();
		txtPass.setColumns(10);
		txtPass.setBorder(BorderFactory.createEmptyBorder());
		txtPass.setBounds(240, 94, 135, 20);
		contentPane.add(txtPass);
		
		txtRPass = new JTextField();
		txtRPass.setColumns(10);
		txtRPass.setBorder(BorderFactory.createEmptyBorder());
		txtRPass.setBounds(240, 126, 135, 20);
		contentPane.add(txtRPass);
		
		txtSecurity = new JTextField();
		txtSecurity.setColumns(10);
		txtSecurity.setBorder(BorderFactory.createEmptyBorder());
		txtSecurity.setBounds(240, 158, 135, 20);
		contentPane.add(txtSecurity);
		
		txtAns = new JTextField();
		txtAns.setColumns(10);
		txtAns.setBorder(BorderFactory.createEmptyBorder());
		txtAns.setBounds(240, 190, 135, 20);
		contentPane.add(txtAns);
		
		JButton button = new JButton("X");
		button.setFocusable(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				closeFrame();
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Ubuntu", Font.BOLD, 8));
		button.setFocusable(false);
		button.setBackground(Color.RED);
		button.setBounds(411, 1, 39, 23);
		contentPane.add(button);
		
		JButton btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				CreateAccData data = new CreateAccData();
				data.setRegNo(txtRegNo.getText());
				data.setPass(txtPass.getText());
				data.setPassR(txtRPass.getText());
				data.setSQ(txtSecurity.getText());
				data.setAns(txtAns.getText());
				
				if(data.getAns().equals("") || data.getPass().equals("") || data.getPassR().equals("") || data.getRegNo().equals("") || data.getSQ().equals(""))
					JOptionPane.showMessageDialog(null, "Fill in all the Details", "Error", JOptionPane.WARNING_MESSAGE);
				else
				{	
					try
					{
						
						
						//checking whether RegNo exist in the database {
						String query1 = "select RegNo from TeachersMF where RegNo=?";
						String query2 = "select RegNo from StudentsMF where RegNo=?";
						PreparedStatement pst1 = connection.prepareStatement(query1);
						pst1.setString(1, data.getRegNo());
						PreparedStatement pst2 = connection.prepareStatement(query2);
						pst2.setString(1, data.getRegNo());
						ResultSet rs1 = pst1.executeQuery();
						ResultSet rs2 = pst2.executeQuery();
						
						int count1=0, count2=0;
						while(rs1.next())
							count1++;
						while(rs2.next())
							count2++;
						
						if (count1==0 && count2==0)
								JOptionPane.showMessageDialog(null, "Registration Number does not exist!", "Error", JOptionPane.WARNING_MESSAGE);
						
						else if(count1 == 1 && count2 == 0)
						{
							String query3 = "select * from TeachersMF where RegNo='"+data.getRegNo()+"'";
							PreparedStatement pst3 = connection.prepareStatement(query3);
							ResultSet rs3 = pst3.executeQuery();
							String p = new String();
							while(rs3.next())
								 p=rs3.getString(18);
							System.out.println(p);
							if (p != null)
								JOptionPane.showMessageDialog(null, "User Already Exist!");
							else if(p == null)
							{
								if((txtPass.getText().equals(txtRPass.getText()))==false)
									JOptionPane.showConfirmDialog(null, "Passwords do not Match!", "Error", JOptionPane.WARNING_MESSAGE);
								else if(txtPass.getText().equals(txtRPass.getText()))
								{
									String query4 = "update TeachersMF set Password='"+data.getPass()+"', SecurityQuestion='"+data.getSQ()+"', Answer='"+data.getAns()+"' where RegNo='"+data.getRegNo()+"'";
									PreparedStatement pst4 = connection.prepareStatement(query4);
									pst4.executeUpdate();
									JOptionPane.showMessageDialog(null, "Account Created!");
									clear();
									rs3.close();
									pst3.close();
									pst4.close();
								}
							}
						}
						
						else if(count1 == 0 && count2 == 1)
						{
							String query6 = "select * from StudentsMF where RegNo='"+data.getRegNo()+"'";
							PreparedStatement pst5 = connection.prepareStatement(query6);
							ResultSet rs4 = pst5.executeQuery();
							String p = new String();
							while(rs4.next())
								 p=rs4.getString(23);
							System.out.println(p);
							if (p != null)
								JOptionPane.showMessageDialog(null, "User Already Exist!");
							else if(p == null)
							{
								if(data.getPass() != data.getPassR())
									JOptionPane.showConfirmDialog(null, "Passwords do not Match!", "Error", JOptionPane.WARNING_MESSAGE);
								else if(data.getPass() == data.getPassR())
								{
									String query5 = "update StudentsMF set Password='"+data.getPass()+"', SecurityQuestion='"+data.getSQ()+"', Answer='"+data.getAns()+"' where RegNo='"+data.getRegNo()+"'";
									PreparedStatement pst6 = connection.prepareStatement(query5);
									pst6.executeUpdate();
									JOptionPane.showMessageDialog(null, "Account Created!");
									clear();
									rs4.close();
									pst5.close();
									pst6.close();
									
								}
							}
						}
						
						rs1.close();
						rs2.close();
						pst1.close();
						pst2.close();
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
				}
				
			}
		});
		btnCreateAccount.setForeground(Color.WHITE);
		btnCreateAccount.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		btnCreateAccount.setBackground(Color.DARK_GRAY);
		btnCreateAccount.setBounds(95, 238, 135, 23);
		contentPane.add(btnCreateAccount);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clear();
			}
		});
		btnClear.setBackground(Color.DARK_GRAY);
		btnClear.setForeground(Color.WHITE);
		btnClear.setBounds(250, 238, 89, 23);
		contentPane.add(btnClear);
	}
	public void closeFrame()
	{
		super.dispose();
	}
	
	public void clear()
	{
		txtRegNo.setText("");
		txtAns.setText("");
		txtPass.setText("");
		txtRPass.setText("");
		txtSecurity.setText("");
	}
}
