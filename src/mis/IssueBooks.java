package mis;

import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import dataman.BooksDM;
import entities.Books;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@SuppressWarnings("serial")
public class IssueBooks extends JFrame {

	private JPanel contentPane;
	private JTextField txtRegNo;
	private JTextField txtName;
	private JTextField txtAddress;
	private JTextField txtTP;
	private JTextField txtAcc;
	private JTextField txtBName;
	private JTextField txtAuthor;
	private JTextField txtPub;
	private JTextField txtEdition;
	private JTextField txtPrice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IssueBooks frame = new IssueBooks();
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
	private Image resizeImage(Image img, int w, int h)
	{
		BufferedImage resizedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resizedImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(img, 0, 0, w, h, null);
        return resizedImage;
	}
	
	Connection connection = null;
	@SuppressWarnings("unchecked")
	public IssueBooks() {
		connection = DBConnector.dbConnector();
		setIconImage(Toolkit.getDefaultToolkit().getImage(IssueBooks.class.getResource("/Resources/Custo.Man.Christmas.Folder.Library.ico.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new LineBorder(Color.BLUE));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		StartPosition.centerOnScreen(this);
		
		Border emptyBorder = BorderFactory.createEmptyBorder();
		String[] Type = new String[] {"Teacher", "Student"};
		
		JLabel lblMin = new JLabel("__");
		lblMin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				minFrame();
			}
		});
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(IssueBooks.class.getResource("/Resources/issue.png")));
		label_3.setBounds(487, 27, 34, 72);
		contentPane.add(label_3);
		lblMin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMin.setForeground(Color.RED);
		lblMin.setFont(new Font("Ubuntu", Font.PLAIN, 16));
		lblMin.setBounds(1276, 11, 34, 23);
		contentPane.add(lblMin);
		
		JLabel lblClose = new JLabel("X");
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				closeFrame();
			}
		});
		lblClose.setHorizontalAlignment(SwingConstants.CENTER);
		lblClose.setForeground(Color.RED);
		lblClose.setFont(new Font("Ubuntu", Font.PLAIN, 16));
		lblClose.setBounds(1320, 11, 34, 23);
		contentPane.add(lblClose);
		
		JLabel lblIssueBook = new JLabel("Issue Book");
		lblIssueBook.setHorizontalAlignment(SwingConstants.CENTER);
		lblIssueBook.setForeground(Color.WHITE);
		lblIssueBook.setFont(new Font("Ubuntu", Font.BOLD, 36));
		lblIssueBook.setBounds(418, 27, 518, 72);
		contentPane.add(lblIssueBook);
		
		JLabel lblMemberType = new JLabel("Member Type");
		lblMemberType.setForeground(Color.WHITE);
		lblMemberType.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblMemberType.setBounds(188, 225, 151, 23);
		contentPane.add(lblMemberType);
		
		JLabel lblRegistrationNumber = new JLabel("Registration Number");
		lblRegistrationNumber.setForeground(Color.WHITE);
		lblRegistrationNumber.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblRegistrationNumber.setBounds(188, 259, 151, 23);
		contentPane.add(lblRegistrationNumber);
		
		JLabel lblMembersName = new JLabel("Member's Name");
		lblMembersName.setForeground(Color.WHITE);
		lblMembersName.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblMembersName.setBounds(188, 293, 151, 23);
		contentPane.add(lblMembersName);
		
		JLabel lblMembersAddress = new JLabel("Member's Address");
		lblMembersAddress.setForeground(Color.WHITE);
		lblMembersAddress.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblMembersAddress.setBounds(188, 327, 151, 23);
		contentPane.add(lblMembersAddress);
		
		JLabel lblMembersContactNo = new JLabel("Member's Contact No");
		lblMembersContactNo.setForeground(Color.WHITE);
		lblMembersContactNo.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblMembersContactNo.setBounds(188, 361, 151, 23);
		contentPane.add(lblMembersContactNo);
		
		JLabel lblProfilePicture = new JLabel("Profile Picture");
		lblProfilePicture.setForeground(Color.WHITE);
		lblProfilePicture.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblProfilePicture.setBounds(188, 395, 151, 23);
		contentPane.add(lblProfilePicture);
		
		JLabel profilePicture = new JLabel("");
		profilePicture.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		profilePicture.setForeground(Color.WHITE);
		profilePicture.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		profilePicture.setBounds(358, 395, 151, 151);
		contentPane.add(profilePicture);
		
		@SuppressWarnings("rawtypes")
		JComboBox cmbType = new JComboBox(Type);
		cmbType.setForeground(Color.WHITE);
		cmbType.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		cmbType.setBackground(Color.DARK_GRAY);
		cmbType.setBounds(358, 226, 151, 20);
		contentPane.add(cmbType);
		
		txtRegNo = new JTextField();
		txtRegNo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				String type = cmbType.getSelectedItem().toString();
				if(type == "Teacher")
				{
					try
					{
						String Query = "select t2.MemID, t1.Name, t1.Adress, t1.ContactNo, t1.ProfilePicture from TeachersMF t1, LibMem t2 where t1.RegNo like ? and t1.RegNo = t2.RegNoT";
						PreparedStatement pst = connection.prepareStatement(Query);
						pst.setString(1, "%" + txtRegNo.getText() + "%");
						ResultSet rs = pst.executeQuery();
						while(rs.next())
						{
							//set name, address and tp
							txtName.setText(rs.getString(2));
							txtAddress.setText(rs.getString(3));
							txtTP.setText(String.valueOf(rs.getInt(4)));
							//converting and setting profile picture
							byte[] img = rs.getBytes(5);
							BufferedImage bufimg;
							bufimg = ImageIO.read(new ByteArrayInputStream(img));
							Image prof = resizeImage(bufimg, profilePicture.getWidth(), profilePicture.getHeight());
							ImageIcon profilePic = new ImageIcon(prof);
							profilePicture.setIcon(profilePic);
						}
							
					}
					catch(Exception ex)
					{
						JOptionPane.showMessageDialog(null, ex.getMessage().toString(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				else if(type == "Student")
				{
					try
					{
						String Query1 = "select t2.MemID, t1.FullName, t1.Address, t1.ContactNo, t1.ProfilePicture from StudentsMF t1, LibMem t2 where t1.RegNo = t2.RegNoS and t1.RegNo like ?";
						PreparedStatement pst1 = connection.prepareStatement(Query1);
						pst1.setString(1, "%" + txtRegNo.getText() + "%");
						ResultSet rs1 = pst1.executeQuery();
						while(rs1.next())
						{
							//set name, address and tp
							txtName.setText(rs1.getString(2));
							txtAddress.setText(rs1.getString(3));
							txtTP.setText(String.valueOf(rs1.getInt(4)));
							//converting and setting profile picture
							byte[] img = rs1.getBytes(5);
							BufferedImage bufimg;
							bufimg = ImageIO.read(new ByteArrayInputStream(img));
							Image prof = resizeImage(bufimg, profilePicture.getWidth(), profilePicture.getHeight());
							ImageIcon profilePic = new ImageIcon(prof);
							profilePicture.setIcon(profilePic);
						}
					}
					catch(Exception ex)
					{
						JOptionPane.showMessageDialog(null, ex.getMessage().toString(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		txtRegNo.setForeground(Color.WHITE);
		txtRegNo.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtRegNo.setBorder(emptyBorder);
		txtRegNo.setColumns(10);
		txtRegNo.setBackground(Color.GRAY);
		txtRegNo.setBounds(358, 260, 151, 20);
		contentPane.add(txtRegNo);
		
		txtName = new JTextField();
		txtName.setEnabled(false);
		txtName.setForeground(Color.WHITE);
		txtName.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtName.setBorder(emptyBorder);
		txtName.setColumns(10);
		txtName.setBackground(Color.GRAY);
		txtName.setBounds(358, 294, 151, 20);
		contentPane.add(txtName);
		
		txtAddress = new JTextField();
		txtAddress.setEnabled(false);
		txtAddress.setForeground(Color.WHITE);
		txtAddress.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtAddress.setBorder(emptyBorder);
		txtAddress.setColumns(10);
		txtAddress.setBackground(Color.GRAY);
		txtAddress.setBounds(358, 328, 151, 20);
		contentPane.add(txtAddress);
		
		txtTP = new JTextField();
		txtTP.setEnabled(false);
		txtTP.setForeground(Color.WHITE);
		txtTP.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtTP.setBorder(emptyBorder);
		txtTP.setColumns(10);
		txtTP.setBackground(Color.GRAY);
		txtTP.setBounds(358, 362, 151, 20);
		contentPane.add(txtTP);
		
		JLabel lblMembersDetails = new JLabel("Member's Details");
		lblMembersDetails.setBackground(Color.DARK_GRAY);
		lblMembersDetails.setForeground(Color.WHITE);
		lblMembersDetails.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblMembersDetails.setBounds(156, 167, 121, 23);
		contentPane.add(lblMembersDetails);
		
		JLabel Group1 = new JLabel("");
		Group1.setBorder(new LineBorder(Color.BLUE));
		Group1.setBounds(135, 179, 442, 407);
		contentPane.add(Group1);
		
		JLabel lblAccNo = new JLabel("Acc No");
		lblAccNo.setForeground(Color.WHITE);
		lblAccNo.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblAccNo.setBounds(836, 225, 151, 23);
		contentPane.add(lblAccNo);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblName.setBounds(836, 259, 151, 23);
		contentPane.add(lblName);
		
		JLabel lblPublisher = new JLabel("Publisher");
		lblPublisher.setForeground(Color.WHITE);
		lblPublisher.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblPublisher.setBounds(836, 327, 151, 23);
		contentPane.add(lblPublisher);
		
		JLabel lblEdition = new JLabel("Edition");
		lblEdition.setForeground(Color.WHITE);
		lblEdition.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblEdition.setBounds(836, 361, 151, 23);
		contentPane.add(lblEdition);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setForeground(Color.WHITE);
		lblPrice.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblPrice.setBounds(836, 395, 151, 23);
		contentPane.add(lblPrice);
		
		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setForeground(Color.WHITE);
		lblAuthor.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblAuthor.setBounds(836, 293, 151, 23);
		contentPane.add(lblAuthor);
		
		txtAcc = new JTextField();
		txtAcc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				try
				{
					String Query = "select BName, Author, Publisher, Edition, Price from LibBooks where AcNo like ?";
					PreparedStatement pst = connection.prepareStatement(Query);
					pst.setString(1, "%" + txtAcc.getText() + "%");
					ResultSet rs = pst.executeQuery();
					while(rs.next())
					{
						txtBName.setText(rs.getString(1));
						txtAuthor.setText(rs.getString(2));
						txtPub.setText(rs.getString(3));
						txtEdition.setText(rs.getString(4));
						txtPrice.setText(rs.getString(5));
					}
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage().toString(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		txtAcc.setForeground(Color.WHITE);
		txtAcc.setBorder(emptyBorder);
		txtAcc.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtAcc.setColumns(10);
		txtAcc.setBackground(Color.GRAY);
		txtAcc.setBounds(997, 226, 151, 20);
		contentPane.add(txtAcc);
		
		txtBName = new JTextField();
		txtBName.setEnabled(false);
		txtBName.setForeground(Color.WHITE);
		txtBName.setBorder(emptyBorder);
		txtBName.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtBName.setColumns(10);
		txtBName.setBackground(Color.GRAY);
		txtBName.setBounds(997, 260, 151, 20);
		contentPane.add(txtBName);
		
		txtAuthor = new JTextField();
		txtAuthor.setEnabled(false);
		txtAuthor.setForeground(Color.WHITE);
		txtAuthor.setBorder(emptyBorder);
		txtAuthor.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtAuthor.setColumns(10);
		txtAuthor.setBackground(Color.GRAY);
		txtAuthor.setBounds(997, 294, 151, 20);
		contentPane.add(txtAuthor);
		
		txtPub = new JTextField();
		txtPub.setEnabled(false);
		txtPub.setForeground(Color.WHITE);
		txtPub.setBorder(emptyBorder);
		txtPub.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtPub.setColumns(10);
		txtPub.setBackground(Color.GRAY);
		txtPub.setBounds(997, 328, 151, 20);
		contentPane.add(txtPub);
		
		txtEdition = new JTextField();
		txtEdition.setEnabled(false);
		txtEdition.setForeground(Color.WHITE);
		txtEdition.setBorder(emptyBorder);
		txtEdition.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtEdition.setColumns(10);
		txtEdition.setBackground(Color.GRAY);
		txtEdition.setBounds(997, 362, 151, 20);
		contentPane.add(txtEdition);
		
		txtPrice = new JTextField();
		txtPrice.setEnabled(false);
		txtPrice.setForeground(Color.WHITE);
		txtPrice.setBorder(emptyBorder);
		txtPrice.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtPrice.setColumns(10);
		txtPrice.setBackground(Color.GRAY);
		txtPrice.setBounds(997, 396, 151, 20);
		contentPane.add(txtPrice);
		
		JLabel lblBooksDetails = new JLabel("Book's Details");
		lblBooksDetails.setForeground(Color.WHITE);
		lblBooksDetails.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblBooksDetails.setBackground(Color.DARK_GRAY);
		lblBooksDetails.setBounds(792, 167, 121, 23);
		contentPane.add(lblBooksDetails);
		
		JLabel Group2 = new JLabel("");
		Group2.setBorder(new LineBorder(Color.BLUE));
		Group2.setBounds(772, 179, 442, 407);
		contentPane.add(Group2);
		
		JButton btnIssue = new JButton("Issue Book");
		btnIssue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.util.Date date = new java.util.Date();
				Date today = new Date(date.getTime());
				
				long theFuture = System.currentTimeMillis() + 2*(86400 * 7 * 1000);
				Date dueD = new Date(theFuture);
				
				if(!txtRegNo.getText().equals("") || !txtAcc.getText().equals(""))
				{
					try
					{//check if the book is already issued
						String query1 = "select * from LibBooks where AcNo=? and MemID IS NOT NULL";
						PreparedStatement ps1 = connection.prepareStatement(query1);
						ps1.setString(1, txtAcc.getText());
						ResultSet rs1 = ps1.executeQuery();
						if(rs1.next())
						{
							JOptionPane.showMessageDialog(null, "Book already Issued");
						}
						else
						{//checks if the user has already burrowed three books
							String query2 = "select * from LibBooks where MemID = ?";
							PreparedStatement ps2 = connection.prepareStatement(query2);
							ps2.setString(1, txtRegNo.getText());
							ResultSet rs2 = ps2.executeQuery();
							int count=0;
							while(rs2.next())
							{
								count++;
							}
							if(count<3)
							{
								BooksDM bDM = new BooksDM();
								Books book = new Books();
								book.setAccNo(txtAcc.getText());
								book.setMemID(txtRegNo.getText());
								book.setIssuedDate(today);
								book.setDueDate(dueD);
								
								if(bDM.issueBook(book))
								{
									JOptionPane.showMessageDialog(null, "Book Issued");
									txtRegNo.setText("");
									txtAcc.setText("");
								}
								else
									JOptionPane.showMessageDialog(null, "Book Issuance Failed");
							}
							else
								JOptionPane.showMessageDialog(null, "User has already Burrowed 3 Books");
						}
					}
					catch(Exception ex)
					{
						JOptionPane.showMessageDialog(null, ex.getMessage().toString());
					}
					
				}
				else
					JOptionPane.showMessageDialog(null, "Registration No or Accquisition No is Not Valid");
			}
		});
		btnIssue.setForeground(Color.WHITE);
		btnIssue.setFont(new Font("Ubuntu", Font.PLAIN, 12));
		btnIssue.setBackground(Color.DARK_GRAY);
		btnIssue.setBounds(554, 676, 104, 30);
		contentPane.add(btnIssue);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtRegNo.setText("");
				txtAcc.setText("");
			}
		});
		btnClear.setForeground(Color.WHITE);
		btnClear.setFont(new Font("Ubuntu", Font.PLAIN, 12));
		btnClear.setBackground(Color.DARK_GRAY);
		btnClear.setBounds(720, 676, 104, 30);
		contentPane.add(btnClear);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(IssueBooks.class.getResource("/Resources/delicious_library.png")));
		label_1.setBounds(611, 327, 138, 138);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(IssueBooks.class.getResource("/Resources/laptop-144-180140.png")));
		label_2.setBounds(428, 27, 138, 79);
		contentPane.add(label_2);
	}
	
	public void closeFrame()
	{
		super.dispose();
	}
	
	@SuppressWarnings("static-access")
	public void minFrame()
	{
		super.setState(super.ICONIFIED);
	}
}
