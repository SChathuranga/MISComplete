package mis;


import java.awt.EventQueue;
//import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseMotionAdapter;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import java.awt.Toolkit;

@SuppressWarnings("serial")
public class MIS_HomeTeacher extends JFrame {

	private JPanel contentPane;
	//private static Point point = new Point();
	public JLabel profilePicture;
	public JLabel lblTName;
	public JLabel lblTID;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MIS_HomeTeacher frame = new MIS_HomeTeacher();
					frame.setUndecorated(true);
					/*
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
					//}*/
					frame.setVisible(true);
					//frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	/**
	 * Create the frame.
	 */
	public MIS_HomeTeacher() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MIS_HomeTeacher.class.getResource("/Resources/Custo.Man.Christmas.Folder.Library.ico.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new LineBorder(Color.BLUE));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		StartPosition.centerOnScreen(this);
		
		Border emptyBorder = BorderFactory.createEmptyBorder();
		
		JButton btnX = new JButton("X");
		btnX.setForeground(Color.WHITE);
		btnX.setFont(new Font("Ubuntu", Font.BOLD, 8));
		btnX.setBackground(Color.RED);
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CloseFrame();
				MIS_Initial.main(null);
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.BLUE));
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 24, 137, 71);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setVisible(false);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(MIS_HomeTeacher.class.getResource("/Resources/Lock.png")));
		label_1.setBounds(5, 6, 25, 21);
		panel.add(label_1);
		
		JButton btnLock = new JButton("Lock");
		btnLock.setFont(new Font("Ubuntu", Font.PLAIN, 11));
		btnLock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CloseFrame();
				MIS_LoginTeacher.main(null);
			}
		});
		btnLock.setForeground(Color.WHITE);
		btnLock.setBackground(Color.DARK_GRAY);
		btnLock.setBounds(30, 6, 62, 23);
		btnLock.setBorder(emptyBorder);
		panel.add(btnLock);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(MIS_HomeTeacher.class.getResource("/Resources/theme.png")));
		label_2.setBounds(5, 36, 25, 21);
		panel.add(label_2);
		
		JButton btnChangeTheme = new JButton("Settings");
		btnChangeTheme.setFont(new Font("Ubuntu", Font.PLAIN, 11));
		btnChangeTheme.setBackground(Color.DARK_GRAY);
		btnChangeTheme.setForeground(Color.WHITE);
		btnChangeTheme.setBounds(30, 36, 71, 23);
		btnChangeTheme.setBorder(emptyBorder);
		panel.add(btnChangeTheme);
		btnX.setBounds(1321, 1, 44, 23);
		btnX.setFocusable(false);
		contentPane.add(btnX);
		
		JLabel lblManagementInformationSystem = new JLabel("Management Information System");
		lblManagementInformationSystem.setFont(new Font("Ubuntu", Font.BOLD, 36));
		lblManagementInformationSystem.setForeground(Color.WHITE);
		lblManagementInformationSystem.setBounds(403, 23, 602, 98);
		contentPane.add(lblManagementInformationSystem);
		
		JButton btnSMS = new JButton("");
		btnSMS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentsManagement.main(null);
			}
		});
		btnSMS.setBackground(Color.DARK_GRAY);
		btnSMS.setIcon(new ImageIcon(MIS_HomeTeacher.class.getResource("/Resources/elementary_school.png")));
		btnSMS.setBounds(228, 244, 167, 167);
		contentPane.add(btnSMS);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(MIS_HomeTeacher.class.getResource("/Resources/Specs.png")));
		label.setBounds(113, -49, 256, 282);
		contentPane.add(label);
		
		JLabel lblStaffMembersDetails = new JLabel("Students Details");
		lblStaffMembersDetails.setForeground(Color.WHITE);
		lblStaffMembersDetails.setFont(new Font("Ubuntu", Font.PLAIN, 20));
		lblStaffMembersDetails.setBounds(422, 244, 231, 167);
		contentPane.add(lblStaffMembersDetails);
		
		JButton btnExam = new JButton("");
		btnExam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//StudentsManagement.main(null);
			}
		});
		btnExam.setIcon(new ImageIcon(MIS_HomeTeacher.class.getResource("/Resources/ResultSheet.png")));
		btnExam.setBackground(Color.DARK_GRAY);
		btnExam.setBounds(228, 484, 167, 167);
		contentPane.add(btnExam);
		
		JLabel lblStudentsDetails = new JLabel("Examination Results");
		lblStudentsDetails.setHorizontalAlignment(SwingConstants.LEFT);
		lblStudentsDetails.setForeground(Color.WHITE);
		lblStudentsDetails.setFont(new Font("Ubuntu", Font.PLAIN, 20));
		lblStudentsDetails.setBounds(422, 484, 231, 167);
		contentPane.add(lblStudentsDetails);
		
		
		JButton btnMinimize = new JButton("_");
		btnMinimize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MinFrame();
			}
		});
		btnMinimize.setForeground(Color.WHITE);
		btnMinimize.setBackground(Color.DARK_GRAY);
		btnMinimize.setBounds(1234, 1, 44, 23);
		btnMinimize.setFocusable(false);
		//button_1.setBorder(emptyBorder);
		contentPane.add(btnMinimize);
		
		JButton btnLMS = new JButton("");
		btnLMS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LMSLoginT.main(null);
				CloseFrame();
			}
		});
		btnLMS.setIcon(new ImageIcon(MIS_HomeTeacher.class.getResource("/Resources/Glossy.Library.icon.png")));
		btnLMS.setBackground(Color.DARK_GRAY);
		btnLMS.setBounds(733, 244, 167, 167);
		contentPane.add(btnLMS);
		
		JLabel lblLibraryManagementSystem = new JLabel("Library Management System");
		lblLibraryManagementSystem.setForeground(Color.WHITE);
		lblLibraryManagementSystem.setFont(new Font("Ubuntu", Font.PLAIN, 20));
		lblLibraryManagementSystem.setBounds(920, 244, 272, 167);
		contentPane.add(lblLibraryManagementSystem);
		
		JButton btnReports = new JButton("");
		btnReports.setIcon(new ImageIcon(MIS_HomeTeacher.class.getResource("/Resources/Progress.png")));
		btnReports.setBackground(Color.DARK_GRAY);
		btnReports.setBounds(733, 484, 167, 167);
		contentPane.add(btnReports);
		
		JLabel lblExaminationResults = new JLabel("Reports");
		lblExaminationResults.setForeground(Color.WHITE);
		lblExaminationResults.setFont(new Font("Ubuntu", Font.PLAIN, 20));
		lblExaminationResults.setBounds(920, 484, 272, 167);
		contentPane.add(lblExaminationResults);
		
		JButton btnInstantChat = new JButton("Messenger");
		btnInstantChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnInstantChat.setForeground(Color.WHITE);
		btnInstantChat.setFont(new Font("Ubuntu", Font.PLAIN, 16));
		btnInstantChat.setIcon(new ImageIcon(MIS_HomeTeacher.class.getResource("/Resources/Chat-icon.png")));
		btnInstantChat.setBackground(Color.DARK_GRAY);
		btnInstantChat.setBounds(1153, 700, 211, 66);
		btnInstantChat.setBorder(emptyBorder);
		contentPane.add(btnInstantChat);
		
		JButton btnOptions = new JButton("Options");
		btnOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(panel.isVisible())
				{
					panel.setVisible(false);
					getBackground();
					btnOptions.setBackground(Color.DARK_GRAY);
					getForeground();
					btnOptions.setForeground(Color.BLUE);
				}
				else if(panel.isVisible()==false)
				{
					panel.setVisible(true);
					getBackground();
					btnOptions.setBackground(Color.BLUE);
					getForeground();
					btnOptions.setForeground(Color.WHITE);
				}
					
			}
		});
		btnOptions.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		btnOptions.setForeground(Color.BLUE);
		btnOptions.setBackground(Color.DARK_GRAY);
		btnOptions.setBounds(1, 2, 86, 23);
		btnOptions.setFocusable(false);
		btnOptions.setBorder(emptyBorder);
		contentPane.add(btnOptions);
		
		lblTName = new JLabel("TName");
		lblTName.setForeground(Color.WHITE);
		lblTName.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblTName.setBounds(94, 686, 155, 19);
		contentPane.add(lblTName);
		
		profilePicture = new JLabel("");
		profilePicture.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		profilePicture.setBounds(10, 686, 74, 71);
		contentPane.add(profilePicture);
		
		lblTID = new JLabel("TID");
		lblTID.setForeground(Color.WHITE);
		lblTID.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblTID.setBounds(94, 716, 155, 19);
		contentPane.add(lblTID);
			
		
	}
	
	public void CloseFrame()
	{
		super.dispose();
	}
	
	@SuppressWarnings("static-access")
	public void MinFrame()
	{
		super.setState(super.ICONIFIED);
	}
	public JLabel getProfilePicture() {
		return profilePicture;
	}
	public JLabel getLblTName() {
		return lblTName;
	}
	public JLabel getLblTID() {
		return lblTID;
	}
}
