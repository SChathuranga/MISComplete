package mis;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class LMSAdminHome extends JFrame {

	private JPanel contentPane;
	//private static Point point = new Point();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LMSAdminHome frame = new LMSAdminHome();
					frame.setUndecorated(true);
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
	public LMSAdminHome() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LMSAdminHome.class.getResource("/Resources/Custo.Man.Christmas.Folder.Library.ico.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 255)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		StartPosition.centerOnScreen(this);
		
		JLabel lblLibraryMnagementSystem = new JLabel("Library Management System");
		lblLibraryMnagementSystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblLibraryMnagementSystem.setForeground(Color.WHITE);
		lblLibraryMnagementSystem.setFont(new Font("Ubuntu", Font.BOLD, 36));
		lblLibraryMnagementSystem.setBounds(394, 38, 518, 72);
		contentPane.add(lblLibraryMnagementSystem);
		
		JLabel lblX = new JLabel("X");
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				closeFrame();
			}
		});
		lblX.setForeground(Color.RED);
		lblX.setFont(new Font("Ubuntu", Font.PLAIN, 16));
		lblX.setBounds(1320, 11, 34, 23);
		contentPane.add(lblX);
		
		JLabel lblMin = new JLabel("__");
		lblMin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				MinFrame();
			}
		});
		lblMin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMin.setForeground(Color.RED);
		lblMin.setFont(new Font("Ubuntu", Font.PLAIN, 16));
		lblMin.setBounds(1276, 11, 34, 23);
		contentPane.add(lblMin);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(LMSAdminHome.class.getResource("/Resources/library-icon.png")));
		label.setBounds(282, 11, 128, 159);
		contentPane.add(label);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BooksManagement.main(null);
			}
		});
		button.setIcon(new ImageIcon(LMSAdminHome.class.getResource("/Resources/emblem_library.png")));
		button.setBackground(Color.DARK_GRAY);
		button.setBounds(381, 210, 167, 167);
		contentPane.add(button);
		
		JLabel lblManageBooks = new JLabel("Manage Books");
		lblManageBooks.setHorizontalAlignment(SwingConstants.CENTER);
		lblManageBooks.setForeground(new Color(255, 255, 255));
		lblManageBooks.setFont(new Font("Ubuntu", Font.PLAIN, 20));
		lblManageBooks.setBounds(381, 402, 167, 37);
		contentPane.add(lblManageBooks);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MembersManagement.main(null);
			}
		});
		button_1.setIcon(new ImageIcon(LMSAdminHome.class.getResource("/Resources/Teachers-icon.png")));
		button_1.setBackground(Color.DARK_GRAY);
		button_1.setBounds(751, 210, 167, 167);
		contentPane.add(button_1);
		
		JLabel lblManageMembers = new JLabel("Manage Members");
		lblManageMembers.setHorizontalAlignment(SwingConstants.CENTER);
		lblManageMembers.setForeground(Color.WHITE);
		lblManageMembers.setFont(new Font("Ubuntu", Font.PLAIN, 20));
		lblManageMembers.setBounds(751, 402, 167, 37);
		contentPane.add(lblManageMembers);
		
		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewBooks.main(null);
			}
		});
		button_2.setIcon(new ImageIcon(LMSAdminHome.class.getResource("/Resources/1412164569_Book-128.png")));
		button_2.setBackground(Color.DARK_GRAY);
		button_2.setBounds(262, 507, 167, 167);
		contentPane.add(button_2);
		
		JLabel lblViewIssuedBooks = new JLabel("View Books");
		lblViewIssuedBooks.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewIssuedBooks.setForeground(Color.WHITE);
		lblViewIssuedBooks.setFont(new Font("Ubuntu", Font.PLAIN, 20));
		lblViewIssuedBooks.setBounds(230, 685, 222, 57);
		contentPane.add(lblViewIssuedBooks);
		
		JButton button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IssueBooks.main(null);
			}
		});
		button_3.setIcon(new ImageIcon(LMSAdminHome.class.getResource("/Resources/Open-folder-accept-icon.png")));
		button_3.setBackground(Color.DARK_GRAY);
		button_3.setBounds(578, 507, 167, 167);
		contentPane.add(button_3);
		
		JLabel lblIssueBooks = new JLabel("Issue Books");
		lblIssueBooks.setHorizontalAlignment(SwingConstants.CENTER);
		lblIssueBooks.setForeground(Color.WHITE);
		lblIssueBooks.setFont(new Font("Ubuntu", Font.PLAIN, 20));
		lblIssueBooks.setBounds(578, 685, 167, 57);
		contentPane.add(lblIssueBooks);
		
		JButton button_4 = new JButton("");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ReturnBook.main(null);
			}
		});
		button_4.setIcon(new ImageIcon(LMSAdminHome.class.getResource("/Resources/handshake-icon.png")));
		button_4.setBackground(Color.DARK_GRAY);
		button_4.setBounds(908, 507, 167, 167);
		contentPane.add(button_4);
		
		JLabel lblReturnBooks = new JLabel("Return Books");
		lblReturnBooks.setHorizontalAlignment(SwingConstants.CENTER);
		lblReturnBooks.setForeground(Color.WHITE);
		lblReturnBooks.setFont(new Font("Ubuntu", Font.PLAIN, 20));
		lblReturnBooks.setBounds(908, 685, 167, 57);
		contentPane.add(lblReturnBooks);
	}
	
	public void closeFrame()
	{
		super.dispose();
	}
	
	@SuppressWarnings("static-access")
	public void MinFrame()
	{
		super.setState(super.ICONIFIED);
	}
}
