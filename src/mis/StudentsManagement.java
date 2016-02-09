package mis;


import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

import com.toedter.calendar.JDateChooser;

import javax.swing.JComboBox;

import net.proteanit.sql.DbUtils;

import java.awt.Cursor;

import dataman.*;
import entities.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
@SuppressWarnings("serial")
public class StudentsManagement extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtRegNo;
	private JTextField txtFullName;
	private JTextField txtAddress;
	private JTextField txtNIC;
	private JTextField txtContact;
	private JTextField txtFathersName;
	private JTextField txtPrevSchool;
	private JTextField txtNationalit;
	private JTextField txtReligion;
	private JTable table_1;
	private JTextField txtSearch;
	private JTextField txtOther;
	private JTextField txtClassEntered;
	private File file;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentsManagement frame = new StudentsManagement();
					frame.setUndecorated(true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
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
	
	private byte[] ConvertFile(String filename)
	{
		FileInputStream fileInputStream = null;
		File file = new File(filename);
		byte[] bFile = new byte[(int) file.length()];
		try
		{
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(bFile);
			fileInputStream.close();
		}
		catch(Exception ex)
		{
			bFile = null;
		}
		return bFile;
	}

	Connection connection=null;
	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public StudentsManagement() {
		ButtonGroup bG = new ButtonGroup();
		connection = DBConnector.dbConnector();
		java.util.Date today = new java.util.Date();
		setIconImage(Toolkit.getDefaultToolkit().getImage(StudentsManagement.class.getResource("/Resources/Custo.Man.Christmas.Folder.Library.ico.png")));
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
		button_1.setIcon(new ImageIcon(StudentsManagement.class.getResource("/Resources/Maximize.png")));
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
		
		JLabel lblStudentsManagement = new JLabel("Students Management");
		lblStudentsManagement.setBounds(403, 34, 543, 78);
		lblStudentsManagement.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentsManagement.setForeground(new Color(255, 255, 255));
		lblStudentsManagement.setFont(new Font("Ubuntu", Font.BOLD, 36));
		contentPane.add(lblStudentsManagement);
		
		
		String[] searchCriteria = new String[] {"Name", "Registration Number"};
		String[] grade = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"};
		
		
		JPanel panel1 = new JPanel();
		panel1.setBorder(new LineBorder(Color.BLUE));
		panel1.setBackground(Color.DARK_GRAY);
		panel1.setBounds(14, 187, 1340, 568);
		contentPane.add(panel1);
		panel1.setVisible(true);
		panel1.setLayout(null);
		
		JButton btnView = new JButton("View Students");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					String Query = "select RegNo, FullName, Address, DOB, NIC, Sex, ContactNo, RegDate, FathersName, NameofPrev, Nationality, Religion, GradeEntered, ClassEntered, ResgndDate, DateRCertIssued, LastDateAttended, ResgnCertNo, ReasonforResgn, OtherDetails, ProfilePicture, CurrentGrade, CurrentClass from StudentsMF";
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
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);
		
		JPanel panel2 = new JPanel();
		panel2.setBorder(new LineBorder(Color.BLUE));
		panel2.setBackground(Color.DARK_GRAY);
		panel2.setBounds(14, 187, 1340, 568);
		contentPane.add(panel2);
		panel2.setLayout(null);
		
		JLabel lblRegistrationNumber = new JLabel("Registration Number");
		lblRegistrationNumber.setForeground(Color.WHITE);
		lblRegistrationNumber.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblRegistrationNumber.setBounds(231, 81, 142, 22);
		panel2.add(lblRegistrationNumber);
		
		txtRegNo = new JTextField();
		txtRegNo.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtRegNo.setBackground(Color.GRAY);
		txtRegNo.setForeground(Color.WHITE);
		txtRegNo.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtRegNo.setBounds(412, 80, 160, 24);
		txtRegNo.setBorder(emptyBorder);
		panel2.add(txtRegNo);
		txtRegNo.setColumns(10);
		
		txtFullName = new JTextField();
		txtFullName.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtFullName.setForeground(Color.WHITE);
		txtFullName.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtFullName.setBorder(emptyBorder);
		txtFullName.setColumns(10);
		txtFullName.setBackground(Color.GRAY);
		txtFullName.setBounds(412, 115, 160, 24);
		panel2.add(txtFullName);
		
		JLabel lblName = new JLabel("Full Name");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblName.setBounds(231, 113, 142, 22);
		panel2.add(lblName);
		
		JLabel lblNicNumber = new JLabel("Address");
		lblNicNumber.setForeground(Color.WHITE);
		lblNicNumber.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblNicNumber.setBounds(231, 146, 142, 22);
		panel2.add(lblNicNumber);
		
		txtAddress = new JTextField();
		txtAddress.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtAddress.setForeground(Color.WHITE);
		txtAddress.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtAddress.setBorder(emptyBorder);
		txtAddress.setColumns(10);
		txtAddress.setBackground(Color.GRAY);
		txtAddress.setBounds(412, 148, 160, 24);
		panel2.add(txtAddress);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setForeground(Color.WHITE);
		lblDateOfBirth.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblDateOfBirth.setBounds(231, 179, 142, 22);
		panel2.add(lblDateOfBirth);
		
		JLabel lblNicNumber_1 = new JLabel("NIC Number");
		lblNicNumber_1.setForeground(Color.WHITE);
		lblNicNumber_1.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblNicNumber_1.setBounds(231, 212, 142, 22);
		panel2.add(lblNicNumber_1);
		
		JLabel lblSex = new JLabel("Sex");
		lblSex.setForeground(Color.WHITE);
		lblSex.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblSex.setBounds(231, 245, 142, 22);
		panel2.add(lblSex);
		
		JLabel lblContactNumber = new JLabel("Contact Number");
		lblContactNumber.setForeground(Color.WHITE);
		lblContactNumber.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblContactNumber.setBounds(231, 278, 142, 22);
		panel2.add(lblContactNumber);
		
		JLabel lblTeachersGrade = new JLabel("Registered Date");
		lblTeachersGrade.setForeground(Color.WHITE);
		lblTeachersGrade.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblTeachersGrade.setBounds(231, 311, 142, 22);
		panel2.add(lblTeachersGrade);
		
		JLabel lblType = new JLabel("Father's Name");
		lblType.setForeground(Color.WHITE);
		lblType.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblType.setBounds(231, 344, 142, 22);
		panel2.add(lblType);
		
		JLabel lblWnop = new JLabel("Name of Previous School");
		lblWnop.setForeground(Color.WHITE);
		lblWnop.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblWnop.setBounds(720, 81, 151, 22);
		panel2.add(lblWnop);
		
		JLabel lblPrefferedSubject = new JLabel("Nationality");
		lblPrefferedSubject.setForeground(Color.WHITE);
		lblPrefferedSubject.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblPrefferedSubject.setBounds(720, 113, 142, 22);
		panel2.add(lblPrefferedSubject);
		
		JLabel lblDistance = new JLabel("Religion");
		lblDistance.setForeground(Color.WHITE);
		lblDistance.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblDistance.setBounds(720, 146, 142, 22);
		panel2.add(lblDistance);
		
		txtNIC = new JTextField();
		txtNIC.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtNIC.setForeground(Color.WHITE);
		txtNIC.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtNIC.setBorder(emptyBorder);
		txtNIC.setColumns(10);
		txtNIC.setBackground(Color.GRAY);
		txtNIC.setBounds(412, 214, 160, 24);
		panel2.add(txtNIC);
		
		txtContact = new JTextField();
		txtContact.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtContact.setForeground(Color.WHITE);
		txtContact.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtContact.setBorder(emptyBorder);
		txtContact.setColumns(10);
		txtContact.setBackground(Color.GRAY);
		txtContact.setBounds(412, 280, 160, 24);
		panel2.add(txtContact);
		
		txtFathersName = new JTextField();
		txtFathersName.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtFathersName.setForeground(Color.WHITE);
		txtFathersName.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtFathersName.setBorder(emptyBorder);
		txtFathersName.setColumns(10);
		txtFathersName.setBackground(Color.GRAY);
		txtFathersName.setBounds(412, 346, 160, 24);
		panel2.add(txtFathersName);
		
		txtPrevSchool = new JTextField();
		txtPrevSchool.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtPrevSchool.setForeground(Color.WHITE);
		txtPrevSchool.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtPrevSchool.setBorder(emptyBorder);
		txtPrevSchool.setColumns(10);
		txtPrevSchool.setBackground(Color.GRAY);
		txtPrevSchool.setBounds(910, 80, 160, 24);
		panel2.add(txtPrevSchool);
		
		txtNationalit = new JTextField();
		txtNationalit.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtNationalit.setForeground(Color.WHITE);
		txtNationalit.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtNationalit.setBorder(emptyBorder);
		txtNationalit.setColumns(10);
		txtNationalit.setBackground(Color.GRAY);
		txtNationalit.setBounds(910, 112, 160, 24);
		panel2.add(txtNationalit);
		
		txtReligion = new JTextField();
		txtReligion.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtReligion.setForeground(Color.WHITE);
		txtReligion.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtReligion.setBorder(emptyBorder);
		txtReligion.setColumns(10);
		txtReligion.setBackground(Color.GRAY);
		txtReligion.setBounds(910, 145, 160, 24);
		panel2.add(txtReligion);
		
		JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		rdbtnMale.setForeground(Color.WHITE);
		rdbtnMale.setBackground(Color.DARK_GRAY);
		rdbtnMale.setBounds(412, 246, 62, 23);
		rdbtnMale.setSelected(true);
		bG.add(rdbtnMale);
		panel2.add(rdbtnMale);
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setForeground(Color.WHITE);
		rdbtnFemale.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		rdbtnFemale.setBackground(Color.DARK_GRAY);
		rdbtnFemale.setBounds(496, 245, 76, 23);
		bG.add(rdbtnFemale);
		panel2.add(rdbtnFemale);
		
		JDateChooser dcDOB = new JDateChooser();
		dcDOB.setBorder(BorderFactory.createEmptyBorder());
		dcDOB.getCalendarButton().setBackground(Color.DARK_GRAY);
		dcDOB.getCalendarButton().setForeground(Color.WHITE);
		dcDOB.setForeground(Color.WHITE);
		dcDOB.setBackground(Color.DARK_GRAY);
		dcDOB.setDate(today);
		dcDOB.setBounds(412, 183, 160, 22);
		panel2.add(dcDOB);
		
		JLabel lblDateOfFirst = new JLabel("Grade Entered");
		lblDateOfFirst.setForeground(Color.WHITE);
		lblDateOfFirst.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblDateOfFirst.setBounds(720, 179, 176, 22);
		panel2.add(lblDateOfFirst);
		
		JDateChooser dcRegisteredDate = new JDateChooser();
		dcRegisteredDate.getCalendarButton().setForeground(Color.WHITE);
		dcRegisteredDate.getCalendarButton().setBackground(Color.DARK_GRAY);
		dcRegisteredDate.setForeground(Color.WHITE);
		dcRegisteredDate.setBorder(BorderFactory.createEmptyBorder());
		dcRegisteredDate.setBackground(Color.DARK_GRAY);
		dcRegisteredDate.setBounds(412, 313, 160, 22);
		dcRegisteredDate.setDate(today);
		dcRegisteredDate.setEnabled(false);
		panel2.add(dcRegisteredDate);
		
		JLabel lblOtherDetails = new JLabel("Other Details");
		lblOtherDetails.setForeground(Color.WHITE);
		lblOtherDetails.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblOtherDetails.setBounds(720, 245, 176, 22);
		panel2.add(lblOtherDetails);
		
		txtOther = new JTextField();
		txtOther.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtOther.setForeground(Color.WHITE);
		txtOther.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtOther.setBorder(emptyBorder);
		txtOther.setColumns(10);
		txtOther.setBackground(Color.GRAY);
		txtOther.setBounds(910, 244, 160, 24);
		panel2.add(txtOther);
		
		JLabel lblProfilePicture = new JLabel("Profile Picture");
		lblProfilePicture.setForeground(Color.WHITE);
		lblProfilePicture.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblProfilePicture.setBounds(720, 278, 176, 22);
		panel2.add(lblProfilePicture);
		
		JLabel profilePicture = new JLabel("");
		profilePicture.setBounds(910, 278, 160, 132);
		profilePicture.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		panel2.add(profilePicture);
		
		JComboBox cmbGrade = new JComboBox(grade);
		cmbGrade.setFont(new Font("Ubuntu", Font.PLAIN, 11));
		cmbGrade.setForeground(Color.WHITE);
		cmbGrade.setBackground(Color.GRAY);
		cmbGrade.setBounds(910, 180, 67, 22);
		cmbGrade.setBorder(BorderFactory.createEmptyBorder());
		panel2.add(cmbGrade);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFeilds();
				
			}
		});
		btnClear.setForeground(Color.WHITE);
		btnClear.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		btnClear.setBackground(Color.DARK_GRAY);
		btnClear.setBounds(696, 506, 110, 32);
		panel2.add(btnClear);
		
		JButton btnNewButton = new JButton("Browse");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfilechooser = new JFileChooser();
				jfilechooser.setDialogTitle("Please Select a Photo");
				jfilechooser.setMultiSelectionEnabled(false);
				jfilechooser.setFileFilter(new FileTypeFilter(".jpg", "JPG"));
				jfilechooser.setFileFilter(new FileTypeFilter(".png", "PNG"));
				jfilechooser.setFileFilter(new FileTypeFilter(".gif", "GIF"));
				int result = jfilechooser.showOpenDialog(null);
				if(result == JFileChooser.APPROVE_OPTION)
				{
					file = jfilechooser.getSelectedFile();
					try
					{
						BufferedImage bufImage = ImageIO.read(file);
						ImageIcon pp = new ImageIcon(resizeImage(bufImage, profilePicture.getWidth(), profilePicture.getHeight()));
						profilePicture.setIcon(pp);
						
					}
					catch(Exception x)
					{
						JOptionPane.showMessageDialog(null, x.getMessage());
					}
				}
			}
		});
		
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setBounds(987, 411, 83, 23);
		panel2.add(btnNewButton);
		
		JLabel lblClass = new JLabel("Class Entered");
		lblClass.setForeground(Color.WHITE);
		lblClass.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblClass.setBounds(720, 212, 176, 22);
		panel2.add(lblClass);
		
		txtClassEntered = new JTextField();
		txtClassEntered.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtClassEntered.setBorder(BorderFactory.createEmptyBorder());
		txtClassEntered.setForeground(Color.WHITE);
		txtClassEntered.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtClassEntered.setColumns(10);
		txtClassEntered.setBackground(Color.GRAY);
		txtClassEntered.setBounds(910, 211, 160, 24);
		panel2.add(txtClassEntered);
		
		
		JButton btnAddStaffMember_1 = new JButton("Add Student");
		btnAddStaffMember_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nicpattern = "(\\d{9}[v|V])";
				String contactNo = "(\\d{10})";
				String sClass1 = "([A-Z, a-z]{1}\\d{1})";
				String sClass2 = "([A-Z, a-z]{1})"; 
				String sex = null;
				if(rdbtnFemale.isSelected())
					sex=rdbtnFemale.getText();
				else if(rdbtnMale.isSelected())
					sex=rdbtnMale.getText();

				if(!txtAddress.getText().equals("") || !txtClassEntered.getText().equals("") || !txtContact.getText().equals("") || !txtFathersName.getText().equals("") || !txtFullName.getText().equals("") || !txtNationalit.getText().equals("") || !txtNIC.getText().equals("") || !txtOther.getText().equals("") || !txtPrevSchool.getText().equals("") || !txtRegNo.getText().equals("") || !txtReligion.getText().equals(""))
				{
					if(txtNIC.getText().matches(nicpattern))
					{
						if(txtContact.getText().matches(contactNo))
						{
							if(!(dcDOB.getDate().equals(today)))
							{
								if(file != null)
								{
									if(txtClassEntered.getText().matches(sClass1) || txtClassEntered.getText().matches(sClass2))
									{
										try
										{
											java.util.Date utilDateDOB = new java.util.Date();
											java.util.Date utilDateReg = new java.util.Date();
											utilDateDOB = dcDOB.getDate();
											utilDateReg = dcRegisteredDate.getDate();
											Date dob = new Date(utilDateDOB.getTime());
											Date reg = new Date(utilDateReg.getTime());
											
											StudentsDM sDM = new StudentsDM();
											Students student = new Students();
											student.setAddress(txtAddress.getText());
											student.setClassEntered(txtClassEntered.getText());
											student.setContactNo(Integer.parseInt(txtContact.getText()));
											student.setCurrentClass(txtClassEntered.getText());
											student.setCurrentGrade(Integer.parseInt(cmbGrade.getSelectedItem().toString()));
											student.setDOB(dob);
											student.setFathersName(txtFathersName.getText());
											student.setFullName(txtFullName.getText());
											student.setGradeEntered(Integer.parseInt(cmbGrade.getSelectedItem().toString()));
											student.setNameofPrev(txtPrevSchool.getText());
											student.setNationality(txtNationalit.getText());
											student.setNIC(txtNIC.getText());
											student.setOtherDetails(txtOther.getText());
											student.setProfilePicture(ConvertFile(file.getAbsolutePath()));
											student.setRegDate(reg);
											student.setReligion(txtReligion.getText());
											student.setRegNo(txtRegNo.getText());
											student.setSex(sex);
											if(sDM.insertStudent(student))
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
										JOptionPane.showMessageDialog(null, "Invalid Class");
								}
								else
									JOptionPane.showMessageDialog(null, "Please Selecta a Profile Picture");
							}
							else
								JOptionPane.showMessageDialog(null, "Enter Valid Date of Birth !");
						}
						else
							JOptionPane.showMessageDialog(null, "Invalid Telephone Number !");
					}
					else
						JOptionPane.showMessageDialog(null, "Invalid NIC Number !");
				}
				else
					JOptionPane.showMessageDialog(null, "Fill in All the Details!");				
			}
		});
		btnAddStaffMember_1.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		btnAddStaffMember_1.setBackground(Color.DARK_GRAY);
		btnAddStaffMember_1.setForeground(Color.WHITE);
		btnAddStaffMember_1.setBounds(496, 506, 167, 32);
		panel2.add(btnAddStaffMember_1);
		panel2.setVisible(false);
		
		//Creating Tab Buttons of Tabs Pane
		JButton btnEditStudentDetails = new JButton("Edit Student Details");
		JButton btnAddStudents = new JButton("Add Students");
		
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
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
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
				else if(cmbSearchCriteria.getSelectedItem().toString()=="Registration Number")
				{
					lblCriteria.setText("Registration Number");
					txtSearch.setBounds(968, 37, 160, 25);
				}
			}
		});
		cmbSearchCriteria.setForeground(Color.WHITE);
		cmbSearchCriteria.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		cmbSearchCriteria.setBorder(emptyBorder);
		cmbSearchCriteria.setBackground(Color.DARK_GRAY);
		cmbSearchCriteria.setBounds(490, 39, 160, 20);
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
						StudentsDM ssDM = new StudentsDM();
						table_1.setModel(DbUtils.resultSetToTableModel(ssDM.searchByName(txtSearch.getText())));
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
						StudentsDM ssDM = new StudentsDM();
						table_1.setModel(DbUtils.resultSetToTableModel(ssDM.searchByRegNo(txtSearch.getText())));
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
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setBackground(Color.DARK_GRAY);
		btnUpdate.setFont(new Font("Ubuntu", Font.PLAIN, 12));
		btnUpdate.setBounds(557, 523, 89, 23);
		panel3.add(btnUpdate);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setForeground(Color.WHITE);
		btnRemove.setBackground(Color.DARK_GRAY);
		btnRemove.setFont(new Font("Ubuntu", Font.PLAIN, 12));
		btnRemove.setBounds(725, 523, 89, 23);
		panel3.add(btnRemove);
		JButton btnViewStudents = new JButton("View Students");
		
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
		txtAddress.setText("");
		txtClassEntered.setText("");
		txtContact.setText("");
		txtFathersName.setText("");
		txtFullName.setText("");
		txtNationalit.setText("");
		txtNIC.setText("");
		txtOther.setText("");
		txtPrevSchool.setText("");
		txtRegNo.setText("");
		txtReligion.setText("");
		file = null;
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
