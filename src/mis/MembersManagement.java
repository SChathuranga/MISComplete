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
public class MembersManagement extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtRegNo;
	private JTextField txtPassword;
	private JTextField txtRPassword;
	private JTable table_1;
	private JTextField txtSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MembersManagement frame = new MembersManagement();
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
	public MembersManagement() {
		connection = DBConnector.dbConnector();
		setIconImage(Toolkit.getDefaultToolkit().getImage(MembersManagement.class.getResource("/Resources/Custo.Man.Christmas.Folder.Library.ico.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new LineBorder(Color.BLUE, 1, true));
		setContentPane(contentPane);
		StartPosition.centerOnScreen(this);
		
		String[] MemberType = new String[] {"Students", "Teachers"};
		
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
		button_1.setIcon(new ImageIcon(MembersManagement.class.getResource("/Resources/Maximize.png")));
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
		
		JLabel lblStudentsManagement = new JLabel("Members Management");
		lblStudentsManagement.setBounds(403, 34, 543, 78);
		lblStudentsManagement.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentsManagement.setForeground(new Color(255, 255, 255));
		lblStudentsManagement.setFont(new Font("Ubuntu", Font.BOLD, 36));
		contentPane.add(lblStudentsManagement);
		
		
		String[] searchCriteria = new String[] {"Name", "Registration Number"};
		
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
		lblSelectSearchCriteria.setBounds(487, 39, 149, 21);
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
		lblCriteria.setBounds(952, 39, 149, 21);
		panel3.add(lblCriteria);
		
		JComboBox cmbSearchCriteria = new JComboBox(searchCriteria);
		cmbSearchCriteria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cmbSearchCriteria.getSelectedItem().toString() == "Name")
				{
					lblCriteria.setText("Name");
					txtSearch.setBounds(1001, 37, 160, 25);
				}
				else if(cmbSearchCriteria.getSelectedItem().toString()=="Registration Number")
				{
					lblCriteria.setText("Registration Number");
					txtSearch.setBounds(1100, 37, 160, 25);
				}
			}
		});
		cmbSearchCriteria.setForeground(Color.WHITE);
		cmbSearchCriteria.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		cmbSearchCriteria.setBorder(emptyBorder);
		cmbSearchCriteria.setBackground(Color.DARK_GRAY);
		cmbSearchCriteria.setBounds(646, 39, 160, 20);
		panel3.add(cmbSearchCriteria);
		
		JComboBox cmbType = new JComboBox(MemberType);
		cmbType.setForeground(Color.WHITE);
		cmbType.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		cmbType.setBackground(Color.DARK_GRAY);
		cmbType.setBounds(246, 39, 160, 20);
		panel3.add(cmbType);
		
		JLabel lblSearchBy = new JLabel("Search By");
		lblSearchBy.setForeground(Color.WHITE);
		lblSearchBy.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblSearchBy.setBounds(885, 39, 75, 21);
		panel3.add(lblSearchBy);
		
		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String type = cmbType.getSelectedItem().toString();
				String sCriteria = cmbSearchCriteria.getSelectedItem().toString();
				if(type == "Students")
				{
					if(sCriteria == "Name")
					{
						try
						{
							LibMemberDM bDM = new LibMemberDM();
							table_1.setModel(DbUtils.resultSetToTableModel(bDM.searchByNameS(txtSearch.getText())));
						}
						catch(Exception ex)
						{
							JOptionPane.showMessageDialog(null, ex.getMessage());
						}
					}
					else if(sCriteria == "Registration Number")
					{
						try
						{
							LibMemberDM bDM = new LibMemberDM();
							table_1.setModel(DbUtils.resultSetToTableModel(bDM.searchByRegNoS(txtSearch.getText())));
						}
						catch(Exception ex)
						{
							JOptionPane.showMessageDialog(null, ex.getMessage());
						}
					}
				}
				else if(type == "Teachers")
				{
					if(sCriteria == "Name")
					{
						try
						{
							LibMemberDM bDM = new LibMemberDM();
							table_1.setModel(DbUtils.resultSetToTableModel(bDM.searchByNameT(txtSearch.getText())));
						}
						catch(Exception ex)
						{
							JOptionPane.showMessageDialog(null, ex.getMessage());
						}
					}
					else if(sCriteria == "Registration Number")
					{
						try
						{
							LibMemberDM bDM = new LibMemberDM();
							table_1.setModel(DbUtils.resultSetToTableModel(bDM.searchByRegNoT(txtSearch.getText())));
						}
						catch(Exception ex)
						{
							JOptionPane.showMessageDialog(null, ex.getMessage());
						}
					}
				}
			}
		});
		txtSearch.setForeground(Color.WHITE);
		txtSearch.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtSearch.setBorder(emptyBorder);
		txtSearch.setBackground(Color.GRAY);
		txtSearch.setBounds(1001, 37, 200, 25);
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
		
		JLabel lblSelectMemberType = new JLabel("Select Member Type");
		lblSelectMemberType.setForeground(Color.WHITE);
		lblSelectMemberType.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblSelectMemberType.setBounds(97, 39, 149, 21);
		panel3.add(lblSelectMemberType);	
		
		JPanel panel1 = new JPanel();
		panel1.setBorder(new LineBorder(Color.BLUE));
		panel1.setBackground(Color.DARK_GRAY);
		panel1.setBounds(14, 187, 1340, 568);
		contentPane.add(panel1);
		panel1.setVisible(true);
		panel1.setLayout(null);
		
		JComboBox cmbMemType = new JComboBox(MemberType);
		cmbMemType.setForeground(Color.WHITE);
		cmbMemType.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		cmbMemType.setBorder(emptyBorder);
		cmbMemType.setBackground(Color.DARK_GRAY);
		cmbMemType.setBounds(449, 39, 146, 25);
		panel1.add(cmbMemType);
		
		JButton btnView = new JButton("View Members");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Type = cmbMemType.getSelectedItem().toString();
				try
				{
					if(Type == "Students")
					{
						String Query = "select t2.MemID, t1.FullName, t1.Address, t1.DOB, t1.NIC, t1.Sex, t1.ContactNo, t1.CurrentGrade, t1.CurrentClass from StudentsMF t1, LibMem t2 where t1.RegNo = t2.RegNoS";
						PreparedStatement pst = connection.prepareStatement(Query);
						ResultSet rs = pst.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs));
						
						rs.close();
						pst.close();
					}
					else if(Type == "Teachers")
					{
						String Query = "select t2.MemID, t1.Name, t1.Adress, t1.DOB, t1.NIC, t1.Sex, t1.ContactNo, t1.TGrade, t1.Type from TeachersMF t1, LibMem t2 where t1.RegNo = t2.RegNoT";
						PreparedStatement pst = connection.prepareStatement(Query);
						ResultSet rs = pst.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs));
						
						rs.close();
						pst.close();

					}
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
		btnView.setBounds(736, 36, 131, 30);
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
		
		JLabel lblISBN = new JLabel("Registration Number");
		lblISBN.setForeground(Color.WHITE);
		lblISBN.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblISBN.setBounds(232, 183, 142, 22);
		panel2.add(lblISBN);
		
		txtRegNo = new JTextField();
		txtRegNo.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtRegNo.setBackground(Color.GRAY);
		txtRegNo.setForeground(Color.WHITE);
		txtRegNo.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtRegNo.setBounds(413, 182, 208, 24);
		txtRegNo.setBorder(emptyBorder);
		panel2.add(txtRegNo);
		txtRegNo.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtPassword.setForeground(Color.WHITE);
		txtPassword.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtPassword.setBorder(emptyBorder);
		txtPassword.setColumns(10);
		txtPassword.setBackground(Color.GRAY);
		txtPassword.setBounds(413, 214, 208, 24);
		panel2.add(txtPassword);
		
		JLabel lblName = new JLabel("Password");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblName.setBounds(232, 215, 142, 22);
		panel2.add(lblName);
		
		JLabel lblNicNumber = new JLabel("Re-Enter Password");
		lblNicNumber.setForeground(Color.WHITE);
		lblNicNumber.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblNicNumber.setBounds(232, 248, 142, 22);
		panel2.add(lblNicNumber);
		
		txtRPassword = new JTextField();
		txtRPassword.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtRPassword.setForeground(Color.WHITE);
		txtRPassword.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtRPassword.setBorder(emptyBorder);
		txtRPassword.setColumns(10);
		txtRPassword.setBackground(Color.GRAY);
		txtRPassword.setBounds(413, 247, 208, 24);
		panel2.add(txtRPassword);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFeilds();
				
			}
		});
		btnClear.setForeground(Color.WHITE);
		btnClear.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		btnClear.setBackground(Color.DARK_GRAY);
		btnClear.setBounds(474, 344, 110, 32);
		panel2.add(btnClear);
		
		
		JButton btnAddMember = new JButton("Add Member");
		btnAddMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtRPassword.getText().equals("") || !txtPassword.getText().equals("") || !txtRegNo.getText().equals(""))
				{
					if(txtPassword.getText().equals(txtRPassword.getText()))
					{
							try
							{						
								LibMemberDM mDM = new LibMemberDM();
								LibMember member = new LibMember();
								member.setMemID(txtRegNo.getText());
								member.setPassword(txtPassword.getText());
								
								//checking whether RegNo exist in the database {
								String query1 = "select RegNo from TeachersMF where RegNo=?";
								String query2 = "select RegNo from StudentsMF where RegNo=?";
								PreparedStatement pst1 = connection.prepareStatement(query1);
								pst1.setString(1, member.getMemID());
								PreparedStatement pst2 = connection.prepareStatement(query2);
								pst2.setString(1, member.getMemID());
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
									String query3 = "select MemID from LibMem where MemID='"+member.getMemID()+"'";
									PreparedStatement pst3 = connection.prepareStatement(query3);
									ResultSet rs3 = pst3.executeQuery();
									int count3=0;
									while(rs3.next())
										 count3++;
									if (count3 != 0)
										JOptionPane.showMessageDialog(null, "User Already Exist!");
									else if(count3 == 0)
									{
										if(mDM.insertMemberT(member))
										{
											JOptionPane.showMessageDialog(null, "Successful");
											clearFeilds();
										}
										else
											JOptionPane.showMessageDialog(null, "Failed");
											rs3.close();
											pst3.close();
									}
								}
								else if(count1 == 0 && count2 == 1)
								{
									String query6 = "select * from LibMem where MemID='"+member.getMemID()+"'";
									PreparedStatement pst5 = connection.prepareStatement(query6);
									ResultSet rs4 = pst5.executeQuery();
									int count3=0;
									while(rs4.next())
										 count3++;
									if (count3 != 0)
										JOptionPane.showMessageDialog(null, "User Already Exist!");
									else if(count3 == 0)
									{
											if(mDM.insertMemberS(member))
											{
												JOptionPane.showMessageDialog(null, "Successful");
												clearFeilds();
											}
											else
												JOptionPane.showMessageDialog(null, "Failed");
											
											rs4.close();
											pst5.close();
											
									}
								}
								
								rs1.close();
								rs2.close();
								pst1.close();
								pst2.close();
								
							}
							catch(Exception x)
							{
								JOptionPane.showMessageDialog(null, x);
								System.out.println(x);
							}
					}
					else
						JOptionPane.showMessageDialog(null, "Passwords do not Match");
				}
				else
					JOptionPane.showMessageDialog(null, "Fill in All the Details");				
			}
		});
		btnAddMember.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		btnAddMember.setBackground(Color.DARK_GRAY);
		btnAddMember.setForeground(Color.WHITE);
		btnAddMember.setBounds(265, 344, 125, 32);
		panel2.add(btnAddMember);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(MembersManagement.class.getResource("/Resources/sign-up-icon.png")));
		label.setBounds(856, 140, 256, 226);
		panel2.add(label);
		panel2.setVisible(false);
		
		//Creating Tab Buttons of Tabs Pane
		JButton btnEditStudentDetails = new JButton("Edit Member Details");
		JButton btnAddStudents = new JButton("Add Members");
		JButton btnViewStudents = new JButton("View Members");
		
		btnViewStudents.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		btnViewStudents.setForeground(Color.WHITE);
		btnViewStudents.setBackground(Color.BLUE);
		btnViewStudents.setBounds(14, 164, 124, 23);
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
		btnAddStudents.setBounds(137, 164, 144, 23);
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
		txtRPassword.setText("");
		txtPassword.setText("");
		txtRegNo.setText("");
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
