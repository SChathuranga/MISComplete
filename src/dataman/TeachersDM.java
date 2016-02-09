package dataman;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import mis.DBConnector;
import entities.*;
public class TeachersDM {

	public boolean insertTeacher(Teachers teacher)
	{
		try
		{
			String Query = "insert into TeachersMF (RegNo, Name, Adress, DOB, NIC, Sex, ContactNo, TGrade, Type, WNOP, PrefSub, Distance, DateofFirstApp, DateofCurrentApp, Nationality, SpecialSkills, ProfilePicture) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = DBConnector.dbConnector().prepareStatement(Query);
			ps.setString(1, teacher.getRegNo());
			ps.setString(2, teacher.getName());
			ps.setString(3, teacher.getAdress());
			ps.setDate(4, (Date) teacher.getDOB());
			ps.setString(5, teacher.getNIC());
			ps.setString(6, teacher.getSex());
			ps.setInt(7, teacher.getContactNo());
			ps.setString(8, teacher.getTGrade());
			ps.setString(9, teacher.getType());
			ps.setString(10, teacher.getWNOP());
			ps.setString(11, teacher.getPrefSub());
			ps.setInt(12, teacher.getDistance());
			ps.setDate(13, teacher.getDateofFirstApp());
			ps.setDate(14, teacher.getDateofCurrentApp());
			ps.setString(15, teacher.getNationality());
			ps.setString(16, teacher.getSpecialSkills());
			ps.setBytes(17, teacher.getProfilePicture());
			return ps.executeUpdate()>0;
		}
		catch(Exception x)
		{
			JOptionPane.showMessageDialog(null, x.getMessage());
			return false;
		}
	}
	
	public ResultSet searchByName(String name)
	{
		PreparedStatement pst = null;
		ResultSet rs = null;
		try
		{
			String Query = "select RegNo, Name, Adress, DOB, NIC,Sex, ContactNo, TGrade, Type, WNOP, PrefSub, Distance, DateofFirstApp, DateofCurrentApp, Nationality, SpecialSkills, ProfilePicture from TeachersMF where Name like ? ";
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
			String Query = "select RegNo, Name, Adress, DOB, NIC,Sex, ContactNo, TGrade, Type, WNOP, PrefSub, Distance, DateofFirstApp, DateofCurrentApp, Nationality, SpecialSkills, ProfilePicture from TeachersMF where RegNo like ? ";
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
