package dataman;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import mis.DBConnector;
import entities.*;

public class StudentsDM {

	public boolean insertStudent(Students student)
	{
		try
		{
			String Query = "insert into StudentsMF (RegNo, FullName, Address, DOB, NIC, Sex, ContactNo, RegDate, FathersName, NameofPrev, Nationality, Religion, GradeEntered, ClassEntered, OtherDetails, ProfilePicture, CurrentGrade, CurrentClass) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = DBConnector.dbConnector().prepareStatement(Query);
			ps.setString(1, student.getRegNo());
			ps.setString(2, student.getFullName());
			ps.setString(3, student.getAddress());
			ps.setDate(4, (Date) student.getDOB());
			ps.setString(5, student.getNIC());
			ps.setString(6, student.getSex());
			ps.setInt(7, student.getContactNo());
			ps.setDate(8, (Date) student.getRegDate());
			ps.setString(9, student.getFathersName());
			ps.setString(10, student.getNameofPrev());
			ps.setString(11, student.getNationality());
			ps.setString(12, student.getReligion());
			ps.setInt(13, student.getGradeEntered());
			ps.setString(14, student.getClassEntered());
			ps.setString(15, student.getOtherDetails());
			ps.setBytes(16, student.getProfilePicture());
			ps.setInt(17, student.getCurrentGrade());
			ps.setString(18, student.getCurrentClass());
			return ps.executeUpdate()>0;
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex);
			return false;
		}
	}
	
	public ResultSet searchByName(String name)
	{
		PreparedStatement pst = null;
		ResultSet rs = null;
		try
		{
			String Query = "select RegNo, FullName, Address, DOB, NIC, Sex, ContactNo, RegDate, FathersName, NameofPrev, Nationality, Religion, GradeEntered, ClassEntered, ResgndDate, DateRCertIssued, LastDateAttended, ResgnCertNo, ReasonforResgn, OtherDetails, ProfilePicture, CurrentGrade, CurrentClass from StudentsMF where FullName like ?";
			pst = DBConnector.dbConnector().prepareStatement(Query);
			pst.setString(1, "%" + name + "%");
			rs = pst.executeQuery();
			return rs;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
	}
	
	public ResultSet searchByRegNo(String regno)
	{
		PreparedStatement pst = null;
		ResultSet rs = null;
		try
		{
			String Query = "select RegNo, FullName, Address, DOB, NIC, Sex, ContactNo, RegDate, FathersName, NameofPrev, Nationality, Religion, GradeEntered, ClassEntered, ResgndDate, DateRCertIssued, LastDateAttended, ResgnCertNo, ReasonforResgn, OtherDetails, ProfilePicture, CurrentGrade, CurrentClass from StudentsMF where RegNo like ?";
			pst = DBConnector.dbConnector().prepareStatement(Query);
			pst.setString(1, "%" + regno + "%");
			rs = pst.executeQuery();
			return rs;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
		
		
	}
}
