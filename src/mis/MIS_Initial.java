package mis;


import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import java.awt.Color;

import javax.swing.border.LineBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class MIS_Initial extends JFrame {

	private static Point point = new Point(); 
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MIS_Initial frame = new MIS_Initial();
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
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MIS_Initial() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MIS_Initial.class.getResource("/Resources/Custo.Man.Christmas.Folder.Library.ico.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 467);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new LineBorder(Color.BLUE, 1, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		StartPosition.centerOnScreen(this);
		
		JLabel lblSelectAccessMode = new JLabel("Select Access Mode");
		lblSelectAccessMode.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectAccessMode.setFont(new Font("Ubuntu", Font.PLAIN, 28));
		lblSelectAccessMode.setForeground(Color.WHITE);
		lblSelectAccessMode.setBounds(84, 24, 260, 80);
		contentPane.add(lblSelectAccessMode);
		
		JLabel lblAdmin = new JLabel("Admin");
		lblAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdmin.setForeground(Color.WHITE);
		lblAdmin.setFont(new Font("Ubuntu", Font.PLAIN, 18));
		lblAdmin.setBounds(219, 146, 97, 71);
		contentPane.add(lblAdmin);
		
		JButton btnAdmin = new JButton("");
		btnAdmin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					MIS_Login.main(null);
					HideFrame();
				}
			}
		});
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MIS_Login.main(null);
				HideFrame();
			}
		});
		btnAdmin.setIcon(new ImageIcon(MIS_Initial.class.getResource("/Resources/Admin.png")));
		btnAdmin.setBackground(Color.DARK_GRAY);
		btnAdmin.setBounds(128, 146, 81, 71);
		contentPane.add(btnAdmin);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Ubuntu", Font.PLAIN, 11));
		btnExit.setForeground(Color.RED);
		btnExit.setBackground(Color.DARK_GRAY);
		btnExit.setBounds(403, 1, 44, 23);
		contentPane.add(btnExit);
		btnExit.setFocusable(false);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		btnExit.setBorder(emptyBorder);
		
		JLabel lblTeacher = new JLabel("Teacher");
		lblTeacher.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeacher.setForeground(Color.WHITE);
		lblTeacher.setFont(new Font("Ubuntu", Font.PLAIN, 18));
		lblTeacher.setBounds(219, 228, 97, 71);
		contentPane.add(lblTeacher);
		
		JButton btnTeacher = new JButton("");
		btnTeacher.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					MIS_LoginTeacher.main(null);
					HideFrame();
				}
			}
		});
		btnTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MIS_LoginTeacher.main(null);
				HideFrame();
			}
		});
		btnTeacher.setIcon(new ImageIcon(MIS_Initial.class.getResource("/Resources/Teacher.png")));
		btnTeacher.setBackground(Color.DARK_GRAY);
		btnTeacher.setBounds(128, 228, 81, 71);
		contentPane.add(btnTeacher);
		
		JLabel lblStudent = new JLabel("Student");
		lblStudent.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudent.setForeground(Color.WHITE);
		lblStudent.setFont(new Font("Ubuntu", Font.PLAIN, 18));
		lblStudent.setBounds(219, 310, 97, 71);
		contentPane.add(lblStudent);
		
		JButton btnStudent = new JButton("");
		btnStudent.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					MIS_LoginStudent.main(null);
					HideFrame();
				}
			}
		});
		btnStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MIS_LoginStudent.main(null);
				HideFrame();
			}
		});
		btnStudent.setIcon(new ImageIcon(MIS_Initial.class.getResource("/Resources/Kid-icon.png")));
		btnStudent.setBackground(Color.DARK_GRAY);
		btnStudent.setBounds(128, 310, 81, 71);
		contentPane.add(btnStudent);
		btnExit.setVisible(true);
		
	}
	
	public void CloseFrame()
	{
		super.dispose();
	}
	
	public void HideFrame()
	{
		super.setVisible(false);
	}
}
