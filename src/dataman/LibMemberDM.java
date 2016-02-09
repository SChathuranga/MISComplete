package dataman;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import mis.DBConnector;
import entities.LibMember;

public class LibMemberDM {

	public boolean insertMemberS(LibMember member)
	{
		try
		{
			String Query = "insert into LibMem (MemID, Password, RegNoS) values (?, ?, ?)";
			PreparedStatement ps = DBConnector.dbConnector().prepareStatement(Query);
			ps.setString(1, member.getMemID());
			ps.setString(2, member.getPassword());
			ps.setString(3, member.getMemID());
			return ps.executeUpdate()>0;
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex);
			return false;
		}
	}
	
	public boolean insertMemberT(LibMember member)
	{
		try
		{
			String Query = "insert into LibMem (MemID, Password, RegNoT) values (?, ?, ?)";
			PreparedStatement ps = DBConnector.dbConnector().prepareStatement(Query);
			ps.setString(1, member.getMemID());
			ps.setString(2, member.getPassword());
			ps.setString(3, member.getMemID());
			return ps.executeUpdate()>0;
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex);
			return false;
		}
	}
	
	public ResultSet searchByNameS(String name)
	{
		PreparedStatement pst = null;
		ResultSet rs = null;
		try
		{
			String Query = "select t2.MemID, t1.FullName, t1.Address, t1.DOB, t1.NIC, t1.Sex, t1.ContactNo, t1.CurrentGrade, t1.CurrentClass from StudentsMF t1, LibMem t2 where t1.RegNo = t2.RegNoS and t1.FullName like ?";
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
	
	public ResultSet searchByRegNoS(String regno)
	{
		PreparedStatement pst = null;
		ResultSet rs = null;
		try
		{
			String Query = "select t2.MemID, t1.FullName, t1.Address, t1.DOB, t1.NIC, t1.Sex, t1.ContactNo, t1.CurrentGrade, t1.CurrentClass from StudentsMF t1, LibMem t2 where t1.RegNo = t2.RegNoS and t1.RegNo like ?";
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
	
	public ResultSet searchByNameT(String name)
	{
		PreparedStatement pst = null;
		ResultSet rs = null;
		try
		{
			String Query = "select t2.MemID, t1.Name, t1.Adress, t1.DOB, t1.NIC, t1.Sex, t1.ContactNo, t1.TGrade, t1.Type from TeachersMF t1, LibMem t2 where t1.RegNo = t2.RegNoT and t1.Name like ?";
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
	
	public ResultSet searchByRegNoT(String regno)
	{
		PreparedStatement pst = null;
		ResultSet rs = null;
		try
		{
			String Query = "select t2.MemID, t1.Name, t1.Adress, t1.DOB, t1.NIC, t1.Sex, t1.ContactNo, t1.TGrade, t1.Type from TeachersMF t1, LibMem t2 where t1.RegNo = t2.RegNoT and t1.RegNo like ?";
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
