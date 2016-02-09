package mis;

import java.awt.EventQueue;

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
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import dataman.BooksDM;
import entities.Books;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class ReturnBook extends JFrame {

	private JPanel contentPane;
	private JTextField txtMemID;
	private JTextField txtAcc;
	private JTextField txtBName;
	private JTextField txtAuthor;
	private JTextField txtEdition;
	private JTextField txtPrice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReturnBook frame = new ReturnBook();
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
	Connection connection = null;
	public ReturnBook() {
		connection = DBConnector.dbConnector();
		setIconImage(Toolkit.getDefaultToolkit().getImage(ReturnBook.class.getResource("/Resources/Custo.Man.Christmas.Folder.Library.ico.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new LineBorder(Color.BLUE));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		StartPosition.centerOnScreen(this);
		
		Border emptyBorder = BorderFactory.createEmptyBorder();
		
		JLabel lblMin = new JLabel("__");
		lblMin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				minFrame();
			}
		});
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(ReturnBook.class.getResource("/Resources/issue.png")));
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
		
		JLabel lblIssueBook = new JLabel("Return Book");
		lblIssueBook.setHorizontalAlignment(SwingConstants.CENTER);
		lblIssueBook.setForeground(Color.WHITE);
		lblIssueBook.setFont(new Font("Ubuntu", Font.BOLD, 36));
		lblIssueBook.setBounds(418, 27, 518, 72);
		contentPane.add(lblIssueBook);
		
		JLabel lblRegistrationNumber = new JLabel("Member ID");
		lblRegistrationNumber.setForeground(Color.WHITE);
		lblRegistrationNumber.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblRegistrationNumber.setBounds(512, 419, 121, 23);
		contentPane.add(lblRegistrationNumber);
		
		txtMemID = new JTextField();
		txtMemID.setForeground(Color.WHITE);
		txtMemID.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtMemID.setBorder(emptyBorder);
		txtMemID.setColumns(10);
		txtMemID.setBackground(Color.GRAY);
		txtMemID.setBounds(673, 420, 151, 20);
		contentPane.add(txtMemID);
		
		JLabel lblMembersDetails = new JLabel("Book Infomation");
		lblMembersDetails.setBackground(Color.DARK_GRAY);
		lblMembersDetails.setForeground(Color.WHITE);
		lblMembersDetails.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblMembersDetails.setBounds(326, 157, 121, 23);
		contentPane.add(lblMembersDetails);
		
		JLabel lblAccNo = new JLabel("Acc No");
		lblAccNo.setForeground(Color.WHITE);
		lblAccNo.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblAccNo.setBounds(512, 249, 151, 23);
		contentPane.add(lblAccNo);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblName.setBounds(512, 283, 151, 23);
		contentPane.add(lblName);
		
		JLabel lblEdition = new JLabel("Edition");
		lblEdition.setForeground(Color.WHITE);
		lblEdition.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblEdition.setBounds(512, 351, 151, 23);
		contentPane.add(lblEdition);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setForeground(Color.WHITE);
		lblPrice.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblPrice.setBounds(512, 385, 151, 23);
		contentPane.add(lblPrice);
		
		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setForeground(Color.WHITE);
		lblAuthor.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblAuthor.setBounds(512, 317, 151, 23);
		contentPane.add(lblAuthor);
		
		JDateChooser dcIssuedDate = new JDateChooser();
		dcIssuedDate.getCalendarButton().setForeground(Color.WHITE);
		dcIssuedDate.getCalendarButton().setBackground(Color.DARK_GRAY);
		dcIssuedDate.setForeground(Color.WHITE);
		dcIssuedDate.setBorder(BorderFactory.createEmptyBorder());
		dcIssuedDate.setBackground(Color.DARK_GRAY);
		dcIssuedDate.setBounds(673, 456, 151, 20);
		dcIssuedDate.setEnabled(false);
		contentPane.add(dcIssuedDate);
		
		txtAcc = new JTextField();
		txtAcc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				try
				{
					String Query = "select BName, Author, Edition, Price from LibBooks where AcNo like ? and MemID IS NOT NULL";
					String Query1 = "select MemID, IssuedDate from LibBooks where AcNo like ?";
					PreparedStatement pst = connection.prepareStatement(Query);
					PreparedStatement pst1 = connection.prepareStatement(Query1);
					
					pst.setString(1, "%" + txtAcc.getText() + "%");
					ResultSet rs = pst.executeQuery();
					while(rs.next())
					{
						txtBName.setText(rs.getString(1));
						txtAuthor.setText(rs.getString(2));
						txtEdition.setText(rs.getString(4));
						txtPrice.setText(rs.getString(4));
					}
					
					pst1.setString(1, "%" + txtAcc.getText()+"%");
					ResultSet rs1 = pst1.executeQuery();
					while(rs1.next())
					{
						txtMemID.setText(rs1.getString(1));
						dcIssuedDate.setDate(rs1.getDate(2));
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
		txtAcc.setBounds(673, 250, 151, 20);
		contentPane.add(txtAcc);
		
		txtBName = new JTextField();
		txtBName.setEnabled(false);
		txtBName.setForeground(Color.WHITE);
		txtBName.setBorder(emptyBorder);
		txtBName.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtBName.setColumns(10);
		txtBName.setBackground(Color.GRAY);
		txtBName.setBounds(673, 284, 151, 20);
		contentPane.add(txtBName);
		
		txtAuthor = new JTextField();
		txtAuthor.setEnabled(false);
		txtAuthor.setForeground(Color.WHITE);
		txtAuthor.setBorder(emptyBorder);
		txtAuthor.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtAuthor.setColumns(10);
		txtAuthor.setBackground(Color.GRAY);
		txtAuthor.setBounds(673, 318, 151, 20);
		contentPane.add(txtAuthor);
		
		txtEdition = new JTextField();
		txtEdition.setEnabled(false);
		txtEdition.setForeground(Color.WHITE);
		txtEdition.setBorder(emptyBorder);
		txtEdition.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtEdition.setColumns(10);
		txtEdition.setBackground(Color.GRAY);
		txtEdition.setBounds(673, 352, 151, 20);
		contentPane.add(txtEdition);
		
		txtPrice = new JTextField();
		txtPrice.setEnabled(false);
		txtPrice.setForeground(Color.WHITE);
		txtPrice.setBorder(emptyBorder);
		txtPrice.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtPrice.setColumns(10);
		txtPrice.setBackground(Color.GRAY);
		txtPrice.setBounds(673, 386, 151, 20);
		contentPane.add(txtPrice);
		
		JButton btnReturn = new JButton("Return Book");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.util.Date date = new java.util.Date();
				java.util.Date issuedDate = new java.util.Date();
				
				Date today = new Date(date.getTime());
				issuedDate = dcIssuedDate.getDate();
				Date iDate = new Date(issuedDate.getTime());
				JOptionPane.showMessageDialog(null, iDate.toString());
				
				if(!txtAcc.getText().equals(""))
				{
					BooksDM bDM = new BooksDM();
					Books book = new Books();
					
					book.setReturnDate(today);
					book.setAccNo(txtAcc.getText());
					book.setMemID(txtMemID.getText());
					book.setIssuedDate(iDate);
					
					if(bDM.returnBook(book))
					{
						JOptionPane.showMessageDialog(null, "Book Returned");
					}
					else
						JOptionPane.showMessageDialog(null, "Book Returning Failed");
				}
				else
					JOptionPane.showMessageDialog(null, "Enter Accquisition Number of the Book");
			}
		});
		
		JLabel lblIssuedDate = new JLabel("Issued Date");
		lblIssuedDate.setForeground(Color.WHITE);
		lblIssuedDate.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblIssuedDate.setBounds(512, 453, 121, 23);
		contentPane.add(lblIssuedDate);
		
		btnReturn.setForeground(Color.WHITE);
		btnReturn.setFont(new Font("Ubuntu", Font.PLAIN, 12));
		btnReturn.setBackground(Color.DARK_GRAY);
		btnReturn.setBounds(547, 676, 111, 30);
		contentPane.add(btnReturn);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtAcc.setText("");
				txtAuthor.setText("");
				txtBName.setText("");
				txtEdition.setText("");
				txtPrice.setText("");
				txtMemID.setText("");
			}
		});
		btnClear.setForeground(Color.WHITE);
		btnClear.setFont(new Font("Ubuntu", Font.PLAIN, 12));
		btnClear.setBackground(Color.DARK_GRAY);
		btnClear.setBounds(720, 676, 104, 30);
		contentPane.add(btnClear);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(ReturnBook.class.getResource("/Resources/laptop-144-180140.png")));
		label_2.setBounds(428, 27, 138, 79);
		contentPane.add(label_2);
		
		JLabel Group2 = new JLabel("");
		Group2.setBorder(new LineBorder(Color.BLUE));
		Group2.setBounds(304, 170, 793, 407);
		contentPane.add(Group2);
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
