package mis;


import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import entities.SetDetaills;

import java.awt.Toolkit;


@SuppressWarnings("serial")
public class MIS_HomeStudent extends JFrame {
	
	String name;
	String id;
	byte[] image;
	private JPanel contentPane;
	private static Point point = new Point();
	public JLabel lblSName;
	public JLabel lblSid;
	public JLabel profilePicture;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MIS_HomeStudent frame = new MIS_HomeStudent();
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
	public MIS_HomeStudent() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MIS_HomeStudent.class.getResource("/Resources/Custo.Man.Christmas.Folder.Library.ico.png")));
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
		label_1.setIcon(new ImageIcon(MIS_HomeStudent.class.getResource("/Resources/Lock.png")));
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
		label_2.setIcon(new ImageIcon(MIS_HomeStudent.class.getResource("/Resources/theme.png")));
		label_2.setBounds(5, 36, 25, 21);
		panel.add(label_2);
		
		JButton btnChangeTheme = new JButton("Settings");
		btnChangeTheme.setFont(new Font("Ubuntu", Font.PLAIN, 11));
		btnChangeTheme.setBackground(Color.DARK_GRAY);
		btnChangeTheme.setForeground(Color.WHITE);
		btnChangeTheme.setBounds(30, 36, 74, 23);
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
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(MIS_HomeStudent.class.getResource("/Resources/Specs.png")));
		label.setBounds(113, -49, 256, 282);
		contentPane.add(label);
		
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
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LMSLoginS.main(null);
				CloseFrame();
			}
		});
		button_1.setIcon(new ImageIcon(MIS_HomeStudent.class.getResource("/Resources/Glossy.Library.icon.png")));
		button_1.setBackground(Color.DARK_GRAY);
		button_1.setBounds(583, 309, 167, 167);
		contentPane.add(button_1);
		
		JButton btnMax = new JButton("");
		btnMax.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MaxFrame();
			}
		});
		btnMax.setIcon(new ImageIcon(MIS_HomeStudent.class.getResource("/Resources/Maximize.png")));
		btnMax.setForeground(Color.WHITE);
		btnMax.setFocusable(false);
		btnMax.setBackground(Color.DARK_GRAY);
		btnMax.setBounds(1277, 1, 44, 23);
		contentPane.add(btnMax);
		
		JLabel lblLibraryManagementSystem = new JLabel("Library Management System");
		lblLibraryManagementSystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblLibraryManagementSystem.setForeground(Color.WHITE);
		lblLibraryManagementSystem.setFont(new Font("Ubuntu", Font.PLAIN, 20));
		lblLibraryManagementSystem.setBounds(530, 487, 272, 167);
		contentPane.add(lblLibraryManagementSystem);
		
		JButton button_2 = new JButton("");
		button_2.setIcon(new ImageIcon(MIS_HomeStudent.class.getResource("/Resources/ResultSheet.png")));
		button_2.setBackground(Color.DARK_GRAY);
		button_2.setBounds(150, 309, 167, 167);
		contentPane.add(button_2);
		
		JLabel lblExaminationResults = new JLabel("Examination Results");
		lblExaminationResults.setHorizontalAlignment(SwingConstants.CENTER);
		lblExaminationResults.setForeground(Color.WHITE);
		lblExaminationResults.setFont(new Font("Ubuntu", Font.PLAIN, 20));
		lblExaminationResults.setBounds(101, 487, 272, 167);
		contentPane.add(lblExaminationResults);
		
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
		
		profilePicture = new JLabel("");
		profilePicture.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		profilePicture.setBounds(10, 686, 74, 71);
		contentPane.add(profilePicture);
		
		lblSName = new JLabel("SName");
		lblSName.setForeground(Color.WHITE);
		lblSName.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblSName.setBounds(94, 686, 74, 19);
		contentPane.add(lblSName);
		
		lblSid = new JLabel("SID");
		lblSid.setForeground(Color.WHITE);
		lblSid.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblSid.setBounds(94, 716, 74, 19);
		contentPane.add(lblSid);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon(MIS_HomeStudent.class.getResource("/Resources/Progress.png")));
		button.setBackground(Color.DARK_GRAY);
		button.setBounds(1026, 309, 167, 167);
		contentPane.add(button);
		
		JLabel label_4 = new JLabel("Reports");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Ubuntu", Font.PLAIN, 20));
		label_4.setBounds(973, 487, 272, 167);
		contentPane.add(label_4);
		
	}
	public void details(SetDetaills sd)
	{
		name = sd.getName();
		id = sd.getRegNo();
		System.out.println(name);
		System.out.println(id);
		
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
	
	@SuppressWarnings("static-access")
	public void MaxFrame()
	{
		super.setExtendedState(super.MAXIMIZED_BOTH);
	}
	
	public JLabel getLblSName() {
		return lblSName;
	}
	public JLabel getLblSid() {
		return lblSid;
	}
	public JLabel getProfilePicture() {
		return profilePicture;
	}
}
