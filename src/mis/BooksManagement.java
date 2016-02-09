package mis;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import net.proteanit.sql.DbUtils;

import java.awt.Cursor;

import dataman.*;
import entities.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
@SuppressWarnings("serial")
public class BooksManagement extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtISBN;
	private JTextField txtAccNo;
	private JTextField txtBName;
	private JTextField txtEdition;
	private JTextField txtAuthor;
	private JTextField txtPublisher;
	private JTextField txtPubYear;
	private JTable table_1;
	private JTextField txtSearch;
	private JTextField txtPrice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BooksManagement frame = new BooksManagement();
					frame.setUndecorated(true);
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BooksManagement() {
		connection = DBConnector.dbConnector();
		setIconImage(Toolkit.getDefaultToolkit().getImage(BooksManagement.class.getResource("/Resources/Custo.Man.Christmas.Folder.Library.ico.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new LineBorder(Color.BLUE, 1, true));
		setContentPane(contentPane);
		StartPosition.centerOnScreen(this);
		
		JButton button = new JButton("_");
		button.setBounds(1234, 1, 44, 23);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MinFrame();
			}
		});
		contentPane.setLayout(null);
		button.setForeground(Color.WHITE);
		button.setFocusable(false);
		button.setBackground(Color.DARK_GRAY);
		contentPane.add(button);
		
		JButton button_1 = new JButton("");
		button_1.setBounds(1277, 1, 44, 23);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MaxFrame();
			}
		});
		button_1.setIcon(new ImageIcon(BooksManagement.class.getResource("/Resources/Maximize.png")));
		button_1.setForeground(Color.WHITE);
		button_1.setFocusable(false);
		button_1.setBackground(Color.DARK_GRAY);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("X");
		button_2.setBounds(1321, 1, 44, 23);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CloseFrame();
			}
		});
		button_2.setForeground(Color.WHITE);
		button_2.setFont(new Font("Ubuntu", Font.BOLD, 8));
		button_2.setFocusable(false);
		button_2.setBackground(Color.RED);
		contentPane.add(button_2);
		
		Border emptyBorder = BorderFactory.createEmptyBorder();
		
		JLabel lblStudentsManagement = new JLabel("Books Management");
		lblStudentsManagement.setBounds(403, 34, 543, 78);
		lblStudentsManagement.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentsManagement.setForeground(new Color(255, 255, 255));
		lblStudentsManagement.setFont(new Font("Ubuntu", Font.BOLD, 36));
		contentPane.add(lblStudentsManagement);
		
		
		String[] searchCriteria = new String[] {"Name", "Acc. Number"};
		String[] status = new String[] {"Available", "Issued"};
		
		
		JPanel panel1 = new JPanel();
		panel1.setBorder(new LineBorder(Color.BLUE));
		panel1.setBackground(Color.DARK_GRAY);
		panel1.setBounds(14, 187, 1340, 568);
		contentPane.add(panel1);
		panel1.setVisible(true);
		panel1.setLayout(null);
		
		JButton btnView = new JButton("View Books");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					String Query = "select * from LibBooks";
					PreparedStatement pst = connection.prepareStatement(Query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					rs.close();
					pst.close();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		btnView.setForeground(Color.WHITE);
		btnView.setBackground(Color.DARK_GRAY);
		btnView.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		btnView.setBounds(579, 36, 131, 30);
		panel1.add(btnView);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 99, 1320, 458);
		panel1.add(scrollPane);
		
		table = new JTable();
		table.setGridColor(Color.GRAY);
		table.setFont(new Font("Ubuntu", Font.PLAIN, 11));
		table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		table.setBackground(Color.WHITE);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPane.setViewportView(table);
		
		JPanel panel2 = new JPanel();
		panel2.setBorder(new LineBorder(Color.BLUE));
		panel2.setBackground(Color.DARK_GRAY);
		panel2.setBounds(14, 187, 1340, 568);
		contentPane.add(panel2);
		panel2.setLayout(null);
		
		JLabel lblISBN = new JLabel("ISBN");
		lblISBN.setForeground(Color.WHITE);
		lblISBN.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblISBN.setBounds(338, 94, 142, 22);
		panel2.add(lblISBN);
		
		txtISBN = new JTextField();
		txtISBN.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtISBN.setBackground(Color.GRAY);
		txtISBN.setForeground(Color.WHITE);
		txtISBN.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtISBN.setBounds(519, 93, 413, 24);
		txtISBN.setBorder(emptyBorder);
		panel2.add(txtISBN);
		txtISBN.setColumns(10);
		
		txtAccNo = new JTextField();
		txtAccNo.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtAccNo.setForeground(Color.WHITE);
		txtAccNo.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtAccNo.setBorder(emptyBorder);
		txtAccNo.setColumns(10);
		txtAccNo.setBackground(Color.GRAY);
		txtAccNo.setBounds(519, 125, 413, 24);
		panel2.add(txtAccNo);
		
		JLabel lblName = new JLabel("Acc. Number");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblName.setBounds(338, 126, 142, 22);
		panel2.add(lblName);
		
		JLabel lblNicNumber = new JLabel("Book Name");
		lblNicNumber.setForeground(Color.WHITE);
		lblNicNumber.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblNicNumber.setBounds(338, 159, 142, 22);
		panel2.add(lblNicNumber);
		
		txtBName = new JTextField();
		txtBName.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtBName.setForeground(Color.WHITE);
		txtBName.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtBName.setBorder(emptyBorder);
		txtBName.setColumns(10);
		txtBName.setBackground(Color.GRAY);
		txtBName.setBounds(519, 158, 413, 24);
		panel2.add(txtBName);
		
		JLabel lblDateOfBirth = new JLabel("Author");
		lblDateOfBirth.setForeground(Color.WHITE);
		lblDateOfBirth.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblDateOfBirth.setBounds(338, 192, 142, 22);
		panel2.add(lblDateOfBirth);
		
		JLabel lblNicNumber_1 = new JLabel("Publisher");
		lblNicNumber_1.setForeground(Color.WHITE);
		lblNicNumber_1.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblNicNumber_1.setBounds(338, 225, 142, 22);
		panel2.add(lblNicNumber_1);
		
		JLabel lblSex = new JLabel("Published Year");
		lblSex.setForeground(Color.WHITE);
		lblSex.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblSex.setBounds(338, 258, 142, 22);
		panel2.add(lblSex);
		
		JLabel lblContactNumber = new JLabel("Edition");
		lblContactNumber.setForeground(Color.WHITE);
		lblContactNumber.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblContactNumber.setBounds(338, 291, 142, 22);
		panel2.add(lblContactNumber);
		
		JLabel lblTeachersGrade = new JLabel("Price");
		lblTeachersGrade.setForeground(Color.WHITE);
		lblTeachersGrade.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblTeachersGrade.setBounds(338, 324, 142, 22);
		panel2.add(lblTeachersGrade);
		
		JLabel lblType = new JLabel("Status");
		lblType.setForeground(Color.WHITE);
		lblType.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblType.setBounds(338, 357, 142, 22);
		panel2.add(lblType);
		
		txtEdition = new JTextField();
		txtEdition.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtEdition.setForeground(Color.WHITE);
		txtEdition.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtEdition.setBorder(emptyBorder);
		txtEdition.setColumns(10);
		txtEdition.setBackground(Color.GRAY);
		txtEdition.setBounds(519, 290, 413, 24);
		panel2.add(txtEdition);
		
		txtAuthor = new JTextField();
		txtAuthor.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtAuthor.setForeground(Color.WHITE);
		txtAuthor.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtAuthor.setBorder(emptyBorder);
		txtAuthor.setColumns(10);
		txtAuthor.setBackground(Color.GRAY);
		txtAuthor.setBounds(519, 191, 413, 24);
		panel2.add(txtAuthor);
		
		txtPublisher = new JTextField();
		txtPublisher.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtPublisher.setForeground(Color.WHITE);
		txtPublisher.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtPublisher.setBorder(emptyBorder);
		txtPublisher.setColumns(10);
		txtPublisher.setBackground(Color.GRAY);
		txtPublisher.setBounds(519, 224, 413, 24);
		panel2.add(txtPublisher);
		
		txtPubYear = new JTextField();
		txtPubYear.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtPubYear.setForeground(Color.WHITE);
		txtPubYear.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtPubYear.setBorder(emptyBorder);
		txtPubYear.setColumns(10);
		txtPubYear.setBackground(Color.GRAY);
		txtPubYear.setBounds(519, 257, 413, 24);
		panel2.add(txtPubYear);
		
		JComboBox cmbStatus = new JComboBox(status);
		cmbStatus.setFont(new Font("Ubuntu", Font.PLAIN, 11));
		cmbStatus.setForeground(Color.WHITE);
		cmbStatus.setBackground(Color.GRAY);
		cmbStatus.setBounds(519, 358, 413, 22);
		cmbStatus.setBorder(BorderFactory.createEmptyBorder());
		panel2.add(cmbStatus);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFeilds();
				
			}
		});
		btnClear.setForeground(Color.WHITE);
		btnClear.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		btnClear.setBackground(Color.DARK_GRAY);
		btnClear.setBounds(717, 461, 110, 32);
		panel2.add(btnClear);
		
		txtPrice = new JTextField();
		txtPrice.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtPrice.setBorder(BorderFactory.createEmptyBorder());
		txtPrice.setForeground(Color.WHITE);
		txtPrice.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtPrice.setColumns(10);
		txtPrice.setBackground(Color.GRAY);
		txtPrice.setBounds(519, 323, 413, 24);
		panel2.add(txtPrice);
		
		
		JButton btnAddBook = new JButton("Add Book");
		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String price = "(\\d+)";
				String year = "(\\d+)";
				if(!txtBName.getText().equals("") || !txtPrice.getText().equals("") || !txtEdition.getText().equals("") || !txtAccNo.getText().equals("") || !txtPublisher.getText().equals("") || !txtAuthor.getText().equals("") || !txtISBN.getText().equals("") || !txtPubYear.getText().equals(""))
				{
					if(txtPrice.getText().matches(price))
					{
						if(txtPubYear.getText().matches(year))
						{
							try
							{						
								BooksDM bDM = new BooksDM();
								Books book = new Books();
								book.setAccNo(txtAccNo.getText());
								book.setAuthor(txtAuthor.getText());
								book.setBname(txtBName.getText());
								book.setEdition(txtEdition.getText());
								book.setISBN(txtISBN.getText());
								book.setPrice(txtPrice.getText());
								book.setPublishedYear(Integer.parseInt(txtPubYear.getText()));
								book.setPublisher(txtPublisher.getText());
								book.setStatus(cmbStatus.getSelectedItem().toString());
								
								if(bDM.insertBook(book))
								{
									JOptionPane.showMessageDialog(null, "Successful");
									clearFeilds();
								}
								else
									JOptionPane.showMessageDialog(null, "Failed");
								
							}
							catch(Exception x)
							{
								JOptionPane.showMessageDialog(null, x);
								System.out.println(x);
							}
					}
						else
							JOptionPane.showMessageDialog(null, "Enter a Valid Year");
					}
					else
						JOptionPane.showMessageDialog(null, "Enter a Valid Price");
				}
				else
					JOptionPane.showMessageDialog(null, "Fill in All the Details");				
			}
		});
		btnAddBook.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		btnAddBook.setBackground(Color.DARK_GRAY);
		btnAddBook.setForeground(Color.WHITE);
		btnAddBook.setBounds(471, 461, 167, 32);
		panel2.add(btnAddBook);
		panel2.setVisible(false);
		
		JPanel panel3 = new JPanel();
		panel3.setBackground(Color.DARK_GRAY);
		panel3.setBorder(new LineBorder(Color.BLUE));
		panel3.setBounds(14, 187, 1340, 568);
		contentPane.add(panel3);
		panel3.setLayout(null);
		panel3.setVisible(false);
		
		JLabel lblSelectSearchCriteria = new JLabel("Select Search Criteria");
		lblSelectSearchCriteria.setForeground(Color.WHITE);
		lblSelectSearchCriteria.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblSelectSearchCriteria.setBounds(333, 39, 149, 21);
		panel3.add(lblSelectSearchCriteria);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 99, 1320, 402);
		panel3.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPane_1.setViewportView(table_1);
		
		JLabel lblCriteria = new JLabel("Name");
		lblCriteria.setForeground(Color.WHITE);
		lblCriteria.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblCriteria.setBounds(820, 39, 149, 21);
		panel3.add(lblCriteria);
		
		JComboBox cmbSearchCriteria = new JComboBox(searchCriteria);
		cmbSearchCriteria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cmbSearchCriteria.getSelectedItem().toString() == "Name")
				{
					lblCriteria.setText("Name");
					txtSearch.setBounds(869, 37, 160, 25);
				}
				else if(cmbSearchCriteria.getSelectedItem().toString()=="Acc. Number")
				{
					lblCriteria.setText("Accquisition Number");
					txtSearch.setBounds(968, 37, 160, 25);
				}
			}
		});
		cmbSearchCriteria.setForeground(Color.WHITE);
		cmbSearchCriteria.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		cmbSearchCriteria.setBorder(emptyBorder);
		cmbSearchCriteria.setBackground(Color.DARK_GRAY);
		cmbSearchCriteria.setBounds(492, 39, 160, 20);
		panel3.add(cmbSearchCriteria);
		
		JLabel lblSearchBy = new JLabel("Search By");
		lblSearchBy.setForeground(Color.WHITE);
		lblSearchBy.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblSearchBy.setBounds(753, 39, 75, 21);
		panel3.add(lblSearchBy);
		
		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String sCriteria = cmbSearchCriteria.getSelectedItem().toString();
				if(sCriteria == "Name")
				{
					try
					{
						BooksDM bsDM = new BooksDM();
						table_1.setModel(DbUtils.resultSetToTableModel(bsDM.searchByName(txtSearch.getText())));
					}
					catch(Exception ex)
					{
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
				}
				else if(sCriteria == "Acc. Number")
				{
					try
					{
						BooksDM bsDM = new BooksDM();
						table_1.setModel(DbUtils.resultSetToTableModel(bsDM.searchByAccNo(txtSearch.getText())));
					}
					catch(Exception ex)
					{
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
				}
			}
		});
		txtSearch.setForeground(Color.WHITE);
		txtSearch.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtSearch.setBorder(emptyBorder);
		txtSearch.setBackground(Color.GRAY);
		txtSearch.setBounds(869, 37, 160, 25);
		panel3.add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Ubuntu", Font.PLAIN, 12));
		btnUpdate.setBackground(Color.DARK_GRAY);
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setBounds(518, 523, 89, 23);
		panel3.add(btnUpdate);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setFont(new Font("Ubuntu", Font.PLAIN, 12));
		btnRemove.setBackground(Color.DARK_GRAY);
		btnRemove.setForeground(Color.WHITE);
		btnRemove.setBounds(676, 523, 89, 23);
		panel3.add(btnRemove);
		
		//Creating Tab Buttons of Tabs Pane
		JButton btnEditStudentDetails = new JButton("Edit Book Details");
		JButton btnAddStudents = new JButton("Add Books");
		JButton btnViewStudents = new JButton("View Books");
		
		btnViewStudents.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		btnViewStudents.setForeground(Color.WHITE);
		btnViewStudents.setBackground(Color.BLUE);
		btnViewStudents.setBounds(14, 164, 103, 23);
		contentPane.add(btnViewStudents);
		
		
		btnAddStudents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(btnAddStudents.hasFocus())
				{
					btnAddStudents.setBackground(Color.BLUE);
					btnAddStudents.setForeground(Color.WHITE);
					btnViewStudents.setBackground(Color.DARK_GRAY);
					btnViewStudents.setForeground(Color.WHITE);
					btnEditStudentDetails.setBackground(Color.DARK_GRAY);
					btnEditStudentDetails.setForeground(Color.WHITE);
				}
				
				if(panel2.isVisible()==false)
				{
					panel2.setVisible(true);
					panel1.setVisible(false);
					panel3.setVisible(false);
				}
			}
		});
		btnAddStudents.setForeground(Color.WHITE);
		btnAddStudents.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		btnAddStudents.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		btnAddStudents.setBackground(Color.DARK_GRAY);
		btnAddStudents.setBounds(112, 164, 169, 23);
		contentPane.add(btnAddStudents);
		
		btnEditStudentDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(btnEditStudentDetails.hasFocus()){
					btnEditStudentDetails.setBackground(Color.BLUE);
					btnEditStudentDetails.setForeground(Color.WHITE);
					btnAddStudents.setBackground(Color.DARK_GRAY);
					btnAddStudents.setForeground(Color.WHITE);
					btnViewStudents.setBackground(Color.DARK_GRAY);
					btnViewStudents.setForeground(Color.WHITE);
				}
				
				if(panel3.isVisible()==false)
				{
					panel3.setVisible(true);
					panel1.setVisible(false);
					panel2.setVisible(false);
				}
				
			}
		});
		btnEditStudentDetails.setForeground(Color.WHITE);
		btnEditStudentDetails.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		btnEditStudentDetails.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		btnEditStudentDetails.setBackground(Color.DARK_GRAY);
		btnEditStudentDetails.setBounds(280, 164, 169, 23);
		contentPane.add(btnEditStudentDetails);
		
		btnViewStudents.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		btnViewStudents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(btnViewStudents.hasFocus()){
					btnViewStudents.setBackground(Color.BLUE);
					btnViewStudents.setForeground(Color.WHITE);
					btnAddStudents.setBackground(Color.DARK_GRAY);
					btnAddStudents.setForeground(Color.WHITE);
					btnEditStudentDetails.setBackground(Color.DARK_GRAY);
					btnEditStudentDetails.setForeground(Color.WHITE);
				}
				
				if(panel1.isVisible()==false)
				{
					panel1.setVisible(true);
					panel2.setVisible(false);
					panel3.setVisible(false);
				}
				
			}
		});
		
		
	}
	public void clearFeilds()
	{
		txtBName.setText("");
		txtPrice.setText("");
		txtEdition.setText("");
		txtAccNo.setText("");
		txtPublisher.setText("");
		txtAuthor.setText("");
		txtISBN.setText("");
		txtPubYear.setText("");
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
}
