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

import javax.swing.ScrollPaneConstants;

import entities.*;
import dataman.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Cursor;

@SuppressWarnings("serial")
public class StaffMembersManagement extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtRegNo;
	private JTextField txtName;
	private JTextField txtAddress;
	private JTextField txtNIC;
	private JTextField txtContact;
	private JTextField txtTGrade;
	private JTextField txtType;
	private JTextField txtWNOP;
	private JTextField txtPSub;
	private JTextField txtDistance;
	private JTextField txtNationality;
	private JTextField txtSpecial;
	private JTable table_1;
	private JTextField txtSearch;
	private File file;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffMembersManagement frame = new StaffMembersManagement();
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
	public StaffMembersManagement() {
		connection = DBConnector.dbConnector();
		java.util.Date today = new java.util.Date();
		setIconImage(Toolkit.getDefaultToolkit().getImage(StaffMembersManagement.class.getResource("/Resources/Custo.Man.Christmas.Folder.Library.ico.png")));
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
		button_1.setIcon(new ImageIcon(StaffMembersManagement.class.getResource("/Resources/Maximize.png")));
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
		
		JLabel lblStaffMembersManagement = new JLabel("Staff Members Management");
		lblStaffMembersManagement.setBounds(403, 34, 543, 78);
		lblStaffMembersManagement.setHorizontalAlignment(SwingConstants.CENTER);
		lblStaffMembersManagement.setForeground(new Color(255, 255, 255));
		lblStaffMembersManagement.setFont(new Font("Ubuntu", Font.BOLD, 36));
		contentPane.add(lblStaffMembersManagement);
		
		ButtonGroup bG = new ButtonGroup();
		
		String[] searchCriteria = new String[] {"Name", "Registration Number"};
		
		JPanel panel2 = new JPanel();
		panel2.setBorder(new LineBorder(Color.BLUE));
		panel2.setBackground(Color.DARK_GRAY);
		panel2.setBounds(14, 187, 1340, 568);
		contentPane.add(panel2);
		panel2.setLayout(null);
		
		
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClearFeilds();
			}
		});
		btnClear.setForeground(Color.WHITE);
		btnClear.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		btnClear.setBackground(Color.DARK_GRAY);
		btnClear.setBounds(686, 499, 110, 32);
		panel2.add(btnClear);
		
		JLabel lblRegistrationNumber = new JLabel("Registration Number");
		lblRegistrationNumber.setForeground(Color.WHITE);
		lblRegistrationNumber.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblRegistrationNumber.setBounds(118, 54, 142, 22);
		panel2.add(lblRegistrationNumber);
		
		txtRegNo = new JTextField();
		txtRegNo.setBackground(Color.GRAY);
		txtRegNo.setForeground(Color.WHITE);
		txtRegNo.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtRegNo.setBounds(270, 54, 160, 24);
		txtRegNo.setBorder(emptyBorder);
		panel2.add(txtRegNo);
		txtRegNo.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblName.setBounds(118, 87, 142, 22);
		panel2.add(lblName);
		
		txtName = new JTextField();
		txtName.setForeground(Color.WHITE);
		txtName.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtName.setBorder(emptyBorder);
		txtName.setColumns(10);
		txtName.setBackground(Color.GRAY);
		txtName.setBounds(270, 89, 160, 24);
		panel2.add(txtName);
		
		JLabel lblNicNumber = new JLabel("Address");
		lblNicNumber.setForeground(Color.WHITE);
		lblNicNumber.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblNicNumber.setBounds(118, 120, 142, 22);
		panel2.add(lblNicNumber);
		
		txtAddress = new JTextField();
		txtAddress.setForeground(Color.WHITE);
		txtAddress.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtAddress.setBorder(emptyBorder);
		txtAddress.setColumns(10);
		txtAddress.setBackground(Color.GRAY);
		txtAddress.setBounds(270, 122, 160, 24);
		panel2.add(txtAddress);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setForeground(Color.WHITE);
		lblDateOfBirth.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblDateOfBirth.setBounds(118, 153, 142, 22);
		panel2.add(lblDateOfBirth);
		
		JLabel lblNicNumber_1 = new JLabel("NIC Number");
		lblNicNumber_1.setForeground(Color.WHITE);
		lblNicNumber_1.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblNicNumber_1.setBounds(118, 186, 142, 22);
		panel2.add(lblNicNumber_1);
		
		JLabel lblSex = new JLabel("Sex");
		lblSex.setForeground(Color.WHITE);
		lblSex.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblSex.setBounds(118, 219, 142, 22);
		panel2.add(lblSex);
		
		JLabel lblContactNumber = new JLabel("Contact Number");
		lblContactNumber.setForeground(Color.WHITE);
		lblContactNumber.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblContactNumber.setBounds(118, 252, 142, 22);
		panel2.add(lblContactNumber);
		
		JLabel lblTeachersGrade = new JLabel("Teacher's Grade");
		lblTeachersGrade.setForeground(Color.WHITE);
		lblTeachersGrade.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblTeachersGrade.setBounds(118, 285, 142, 22);
		panel2.add(lblTeachersGrade);
		
		JLabel lblType = new JLabel("Type");
		lblType.setForeground(Color.WHITE);
		lblType.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblType.setBounds(118, 318, 142, 22);
		panel2.add(lblType);
		
		JLabel lblWnop = new JLabel("WNOP");
		lblWnop.setForeground(Color.WHITE);
		lblWnop.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblWnop.setBounds(118, 351, 142, 22);
		panel2.add(lblWnop);
		
		JLabel lblPrefferedSubject = new JLabel("Preffered Subject");
		lblPrefferedSubject.setForeground(Color.WHITE);
		lblPrefferedSubject.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblPrefferedSubject.setBounds(118, 384, 142, 22);
		panel2.add(lblPrefferedSubject);
		
		JLabel lblDistance = new JLabel("Distance to the School");
		lblDistance.setForeground(Color.WHITE);
		lblDistance.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblDistance.setBounds(118, 417, 142, 22);
		panel2.add(lblDistance);
		
		txtNIC = new JTextField();
		txtNIC.setForeground(Color.WHITE);
		txtNIC.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtNIC.setBorder(emptyBorder);
		txtNIC.setColumns(10);
		txtNIC.setBackground(Color.GRAY);
		txtNIC.setBounds(270, 188, 160, 24);
		panel2.add(txtNIC);
		
		txtContact = new JTextField();
		txtContact.setForeground(Color.WHITE);
		txtContact.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtContact.setBorder(emptyBorder);
		txtContact.setColumns(10);
		txtContact.setBackground(Color.GRAY);
		txtContact.setBounds(270, 254, 160, 24);
		panel2.add(txtContact);
		
		txtTGrade = new JTextField();
		txtTGrade.setForeground(Color.WHITE);
		txtTGrade.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtTGrade.setBorder(emptyBorder);
		txtTGrade.setColumns(10);
		txtTGrade.setBackground(Color.GRAY);
		txtTGrade.setBounds(270, 287, 160, 24);
		panel2.add(txtTGrade);
		
		txtType = new JTextField();
		txtType.setForeground(Color.WHITE);
		txtType.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtType.setBorder(emptyBorder);
		txtType.setColumns(10);
		txtType.setBackground(Color.GRAY);
		txtType.setBounds(270, 320, 160, 24);
		panel2.add(txtType);
		
		txtWNOP = new JTextField();
		txtWNOP.setForeground(Color.WHITE);
		txtWNOP.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtWNOP.setBorder(emptyBorder);
		txtWNOP.setColumns(10);
		txtWNOP.setBackground(Color.GRAY);
		txtWNOP.setBounds(270, 353, 160, 24);
		panel2.add(txtWNOP);
		
		txtPSub = new JTextField();
		txtPSub.setForeground(Color.WHITE);
		txtPSub.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtPSub.setBorder(emptyBorder);
		txtPSub.setColumns(10);
		txtPSub.setBackground(Color.GRAY);
		txtPSub.setBounds(270, 386, 160, 24);
		panel2.add(txtPSub);
		
		txtDistance = new JTextField();
		txtDistance.setForeground(Color.WHITE);
		txtDistance.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtDistance.setBorder(emptyBorder);
		txtDistance.setColumns(10);
		txtDistance.setBackground(Color.GRAY);
		txtDistance.setBounds(270, 419, 160, 24);
		panel2.add(txtDistance);
		
		JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		rdbtnMale.setForeground(Color.WHITE);
		rdbtnMale.setBackground(Color.DARK_GRAY);
		rdbtnMale.setBounds(270, 220, 62, 23);
		rdbtnMale.setSelected(true);
		bG.add(rdbtnMale);
		panel2.add(rdbtnMale);
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setForeground(Color.WHITE);
		rdbtnFemale.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		rdbtnFemale.setBackground(Color.DARK_GRAY);
		rdbtnFemale.setBounds(354, 219, 76, 23);
		bG.add(rdbtnFemale);
		panel2.add(rdbtnFemale);
		
		JDateChooser dcDOB = new JDateChooser();
		dcDOB.setBorder(BorderFactory.createEmptyBorder());
		dcDOB.getCalendarButton().setBackground(Color.DARK_GRAY);
		dcDOB.getCalendarButton().setForeground(Color.WHITE);
		dcDOB.setForeground(Color.WHITE);
		dcDOB.setBackground(Color.DARK_GRAY);
		dcDOB.setDate(today);
		dcDOB.setBounds(270, 157, 160, 22);
		panel2.add(dcDOB);
		
		JLabel lblDateOfFirst = new JLabel("Date of First Appointment");
		lblDateOfFirst.setForeground(Color.WHITE);
		lblDateOfFirst.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblDateOfFirst.setBounds(598, 59, 176, 22);
		panel2.add(lblDateOfFirst);
		
		JLabel lblAppointmentDate = new JLabel("Appointment Date");
		lblAppointmentDate.setForeground(Color.WHITE);
		lblAppointmentDate.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblAppointmentDate.setBounds(598, 92, 176, 22);
		panel2.add(lblAppointmentDate);
		
		JLabel lblNationality = new JLabel("Nationality");
		lblNationality.setForeground(Color.WHITE);
		lblNationality.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblNationality.setBounds(598, 125, 176, 22);
		panel2.add(lblNationality);
		
		JLabel lblSpecialSkills = new JLabel("Special Skills");
		lblSpecialSkills.setForeground(Color.WHITE);
		lblSpecialSkills.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblSpecialSkills.setBounds(598, 158, 176, 22);
		panel2.add(lblSpecialSkills);
		
		JDateChooser dcFA = new JDateChooser();
		dcFA.getCalendarButton().setForeground(Color.WHITE);
		dcFA.getCalendarButton().setBackground(Color.DARK_GRAY);
		dcFA.setForeground(Color.WHITE);
		dcFA.setBorder(BorderFactory.createEmptyBorder());
		dcFA.setBackground(Color.DARK_GRAY);
		dcFA.setDate(today);
		dcFA.setBounds(784, 54, 160, 22);
		panel2.add(dcFA);
		
		JDateChooser dcCA = new JDateChooser();
		dcCA.getCalendarButton().setForeground(Color.WHITE);
		dcCA.getCalendarButton().setBackground(Color.DARK_GRAY);
		dcCA.setForeground(Color.WHITE);
		dcCA.setBorder(BorderFactory.createEmptyBorder());
		dcCA.setBackground(Color.DARK_GRAY);
		dcCA.setDate(today);
		dcCA.setBounds(784, 87, 160, 22);
		dcCA.setEnabled(false);
		panel2.add(dcCA);
		
		txtNationality = new JTextField();
		txtNationality.setForeground(Color.WHITE);
		txtNationality.setBorder(emptyBorder);
		txtNationality.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtNationality.setColumns(10);
		txtNationality.setBackground(Color.GRAY);
		txtNationality.setBounds(784, 122, 160, 24);
		panel2.add(txtNationality);
		
		txtSpecial = new JTextField();
		txtSpecial.setForeground(Color.WHITE);
		txtSpecial.setBorder(emptyBorder);
		txtSpecial.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		txtSpecial.setColumns(10);
		txtSpecial.setBackground(Color.GRAY);
		txtSpecial.setBounds(784, 155, 160, 24);
		panel2.add(txtSpecial);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(StaffMembersManagement.class.getResource("/Resources/WorkSpace.png")));
		lblNewLabel.setBounds(931, 242, 376, 308);
		panel2.add(lblNewLabel);
		
		JLabel lblProfilePicture = new JLabel("Profile Picture");
		lblProfilePicture.setForeground(Color.WHITE);
		lblProfilePicture.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblProfilePicture.setBounds(598, 191, 176, 22);
		panel2.add(lblProfilePicture);
		
		JLabel profilePicture = new JLabel("");
		profilePicture.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		profilePicture.setBounds(784, 186, 160, 143);
		panel2.add(profilePicture);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		btnBrowse.setForeground(Color.WHITE);
		btnBrowse.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		btnBrowse.setBackground(Color.DARK_GRAY);
		btnBrowse.setBounds(861, 340, 83, 23);
		panel2.add(btnBrowse);
		
		JButton btnAddStaffMember_1 = new JButton("Add Staff Member");
		btnAddStaffMember_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nicpattern = "(\\d{9}[v|V])";
				String contactNo = "(\\d{10})";
				String tGrade = "(\\d{1}.\\d{1})";
				String distance = "(\\d{3})";
				String sex = null;
				if(rdbtnFemale.isSelected())
					sex=rdbtnFemale.getText();
				else if(rdbtnMale.isSelected())
					sex=rdbtnMale.getText();
				if(!txtAddress.getText().equals("") || !txtContact.getText().equals("") || !txtDistance.getText().equals("") || !txtName.getText().equals("") || !txtNationality.getText().equals("") || !txtNIC.getText().equals("") || !txtPSub.getText().equals("") || !txtRegNo.getText().equals("") || !txtSpecial.getText().equals("") || !txtTGrade.getText().equals("") || !txtType.getText().equals("") || !txtWNOP.getText().equals(""))
				{
					if(txtNIC.getText().matches(nicpattern))
					{
						if(txtContact.getText().matches(contactNo))
						{
							if(!(dcDOB.getDate().equals(today)))
							{
								if(file != null)
								{
									if(txtTGrade.getText().matches(tGrade))
									{
										if(txtDistance.getText().matches(distance))
										{
											try
											{
												java.util.Date utilDateDOB = new java.util.Date();
												java.util.Date utilFA = new java.util.Date();
												java.util.Date utilCA = new java.util.Date();
												utilDateDOB = dcDOB.getDate();
												utilFA = dcFA.getDate();
												utilCA = dcCA.getDate();
												Date dob = new Date(utilDateDOB.getTime());
												Date fa = new Date(utilFA.getTime());
												Date ca = new Date(utilCA.getTime());
												
												TeachersDM tDM = new TeachersDM();
												Teachers teacher = new Teachers();
												teacher.setAdress(txtAddress.getText());
												teacher.setContactNo(Integer.parseInt(txtContact.getText()));
												teacher.setDateofCurrentApp(ca);
												teacher.setDateofFirstApp(fa);
												teacher.setDistance(Integer.parseInt(txtDistance.getText()));
												teacher.setDOB(dob);
												teacher.setName(txtName.getText());
												teacher.setNationality(txtNationality.getText());
												teacher.setNIC(txtNIC.getText());
												teacher.setPrefSub(txtPSub.getText());
												teacher.setProfilePicture(ConvertFile(file.getAbsolutePath()));
												teacher.setRegNo(txtRegNo.getText());
												teacher.setSex(sex);
												teacher.setSpecialSkills(txtSpecial.getText());
												teacher.setTGrade(txtTGrade.getText());
												teacher.setType(txtType.getText());
												teacher.setWNOP(txtWNOP.getText());
												
												if(tDM.insertTeacher(teacher))
												{
													JOptionPane.showMessageDialog(null, "Successful");
													ClearFeilds();
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
											JOptionPane.showMessageDialog(null, "Invalid Distance to the School");
									}
									else
										JOptionPane.showMessageDialog(null, "Invalid Teachers Grade!");
								}
								else
									JOptionPane.showMessageDialog(null, "Please selecta a Profile Picture!");
							}
							else
								JOptionPane.showMessageDialog(null, "Invalid Date of Birth");
						}
						else
							JOptionPane.showMessageDialog(null, "Invalid Contact Number!");
					}
					else
						JOptionPane.showMessageDialog(null, "Invalid NIC Number");
				}
				else
					JOptionPane.showMessageDialog(null, "Enter All the Feilds!");
			}
		});
		btnAddStaffMember_1.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		btnAddStaffMember_1.setBackground(Color.DARK_GRAY);
		btnAddStaffMember_1.setForeground(Color.WHITE);
		btnAddStaffMember_1.setBounds(486, 499, 167, 32);
		panel2.add(btnAddStaffMember_1);
		
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
		lblSelectSearchCriteria.setBounds(333, 35, 149, 21);
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
		cmbSearchCriteria.setBounds(492, 35, 160, 20);
		panel3.add(cmbSearchCriteria);
		
		JLabel lblSearchBy = new JLabel("Search By");
		lblSearchBy.setForeground(Color.WHITE);
		lblSearchBy.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lblSearchBy.setBounds(753, 39, 75, 21);
		panel3.add(lblSearchBy);
		
		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				String sCriteria = cmbSearchCriteria.getSelectedItem().toString();
				if(sCriteria == "Name")
				{
					try
					{
						TeachersDM tsDM = new TeachersDM();
						table_1.setModel(DbUtils.resultSetToTableModel(tsDM.searchByName(txtSearch.getText())));
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
						TeachersDM tsDM = new TeachersDM();
						table_1.setModel(DbUtils.resultSetToTableModel(tsDM.searchByRegNo(txtSearch.getText())));
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
		btnUpdate.setFont(new Font("Ubuntu", Font.PLAIN, 12));
		btnUpdate.setBackground(Color.DARK_GRAY);
		btnUpdate.setBounds(539, 523, 89, 23);
		panel3.add(btnUpdate);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setForeground(Color.WHITE);
		btnRemove.setFont(new Font("Ubuntu", Font.PLAIN, 12));
		btnRemove.setBackground(Color.DARK_GRAY);
		btnRemove.setBounds(691, 523, 89, 23);
		panel3.add(btnRemove);
		
		JPanel panel1 = new JPanel();
		panel1.setBorder(new LineBorder(Color.BLUE));
		panel1.setBackground(Color.DARK_GRAY);
		panel1.setBounds(14, 187, 1340, 568);
		contentPane.add(panel1);
		panel1.setVisible(true);
		panel1.setLayout(null);
		
		JButton btnView = new JButton("View Staff");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					String Query = "select RegNo, Name, Adress, DOB, NIC,Sex, ContactNo, TGrade, Type, WNOP, PrefSub, Distance, DateofFirstApp, DateofCurrentApp, Nationality, SpecialSkills, ProfilePicture from TeachersMF";
					PreparedStatement pst = connection.prepareStatement(Query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					rs.close();
					pst.close();
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				
			}
		});
		btnView.setForeground(Color.WHITE);
		btnView.setBackground(Color.DARK_GRAY);
		btnView.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		btnView.setBounds(590, 34, 115, 30);
		panel1.add(btnView);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 99, 1320, 458);
		panel1.add(scrollPane);
		
		table = new JTable();
		table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		table.setFont(new Font("Ubuntu", Font.PLAIN, 11));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);
		
		//String[] searchCriteria = new String[] {"Name", "Registration Number"};
		
		//ButtonGroup bG = new ButtonGroup();
		
		//Creating Tab Buttons of Tabs Pane
		JButton btnEditMemberDetails = new JButton("Edit Member Details");
		JButton btnAddStaffMember = new JButton("Add Staff Members");
		JButton btnViewStaff = new JButton("View Staff");
		btnViewStaff.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		btnViewStaff.setForeground(Color.WHITE);
		btnViewStaff.setBackground(Color.BLUE);
		btnViewStaff.setBounds(14, 164, 103, 23);
		contentPane.add(btnViewStaff);
		
		
		btnAddStaffMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(btnAddStaffMember.hasFocus())
				{
					btnAddStaffMember.setBackground(Color.BLUE);
					btnAddStaffMember.setForeground(Color.WHITE);
					btnViewStaff.setBackground(Color.DARK_GRAY);
					btnViewStaff.setForeground(Color.WHITE);
					btnEditMemberDetails.setBackground(Color.DARK_GRAY);
					btnEditMemberDetails.setForeground(Color.WHITE);
				}
				
				if(panel2.isVisible()==false)
				{
					panel2.setVisible(true);
					panel1.setVisible(false);
					panel3.setVisible(false);
				}
			}
		});
		btnAddStaffMember.setForeground(Color.WHITE);
		btnAddStaffMember.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		btnAddStaffMember.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		btnAddStaffMember.setBackground(Color.DARK_GRAY);
		btnAddStaffMember.setBounds(112, 164, 169, 23);
		contentPane.add(btnAddStaffMember);
		
		btnEditMemberDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(btnEditMemberDetails.hasFocus()){
					btnEditMemberDetails.setBackground(Color.BLUE);
					btnEditMemberDetails.setForeground(Color.WHITE);
					btnAddStaffMember.setBackground(Color.DARK_GRAY);
					btnAddStaffMember.setForeground(Color.WHITE);
					btnViewStaff.setBackground(Color.DARK_GRAY);
					btnViewStaff.setForeground(Color.WHITE);
				}
				
				if(panel3.isVisible()==false)
				{
					panel3.setVisible(true);
					panel1.setVisible(false);
					panel2.setVisible(false);
				}
				
			}
		});
		btnEditMemberDetails.setForeground(Color.WHITE);
		btnEditMemberDetails.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		btnEditMemberDetails.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		btnEditMemberDetails.setBackground(Color.DARK_GRAY);
		btnEditMemberDetails.setBounds(280, 164, 169, 23);
		contentPane.add(btnEditMemberDetails);
		
		btnViewStaff.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		btnViewStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(btnViewStaff.hasFocus()){
					btnViewStaff.setBackground(Color.BLUE);
					btnViewStaff.setForeground(Color.WHITE);
					btnAddStaffMember.setBackground(Color.DARK_GRAY);
					btnAddStaffMember.setForeground(Color.WHITE);
					btnEditMemberDetails.setBackground(Color.DARK_GRAY);
					btnEditMemberDetails.setForeground(Color.WHITE);
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
	
	public void CloseFrame()
	{
		super.dispose();
	}
	
	public void ClearFeilds()
	{
		txtRegNo.setText(null);
		txtName.setText(null);
		txtAddress.setText(null);
		txtNIC.setText(null);
		txtContact.setText(null);
		txtTGrade.setText(null);
		txtType.setText(null);
		txtWNOP.setText(null);
		txtPSub.setText(null);
		txtDistance.setText(null);
		txtNationality.setText(null);
		txtSpecial.setText(null);
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
