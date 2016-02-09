package dataman;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import mis.DBConnector;
import entities.Books;

public class BooksDM {

	public boolean insertBook(Books book)
	{
		try
		{
			String Query = "insert into LibBooks (ISBN, AcNo, BName, Author, Publisher, PublishedYear, Edition, Price, Status) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = DBConnector.dbConnector().prepareStatement(Query);
			ps.setString(1, book.getISBN());
			ps.setString(2, book.getAccNo());
			ps.setString(3, book.getBname());
			ps.setString(4, book.getAuthor());
			ps.setString(5, book.getPublisher());
			ps.setInt(6, book.getPublishedYear());
			ps.setString(7, book.getEdition());
			ps.setString(8, book.getPrice());
			ps.setString(9, book.getStatus());
			return ps.executeUpdate()>0;
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex);
			return false;
		}
	}
	public boolean issueBook(Books book)
	{
		try
		{
			String query1 = "update LibBooks set MemID=?, IssuedDate=?, DueDate=?, Status='Issued' where AcNo=?";
			String query2 = "insert into LibHistory (AccNo, MemID, IssuedDate, DuedDate) values (?, ?, ?, ?)";
			PreparedStatement ps1 = DBConnector.dbConnector().prepareStatement(query1);
			PreparedStatement ps2 = DBConnector.dbConnector().prepareStatement(query2);
			
			ps1.setString(1, book.getMemID());
			ps1.setDate(2, book.getIssuedDate());
			ps1.setDate(3, book.getDueDate());
			ps1.setString(4, book.getAccNo());
			
			ps2.setString(1, book.getAccNo());
			ps2.setString(2, book.getMemID());
			ps2.setDate(3, book.getIssuedDate());
			ps2.setDate(4, book.getDueDate());
			
			return (ps1.executeUpdate()>0 && ps2.executeUpdate()>0);
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage().toString());
			return false;
		}
	}
	
	public boolean returnBook(Books book)
	{
		try
		{
			String query1 = "update LibBooks set MemID=null, IssuedDate=null, DueDate=null, Status='Available' where AcNo=?";
			PreparedStatement ps1 = DBConnector.dbConnector().prepareStatement(query1);
			
			String query2 = "update LibHistory set ReturnedDate=? where AccNo=? and MemID=? and IssuedDate=?";
			PreparedStatement ps2 = DBConnector.dbConnector().prepareStatement(query2);
			
			ps1.setString(1, book.getAccNo());
			ps2.setDate(1, book.getReturnDate());
			ps2.setString(2, book.getAccNo());
			ps2.setString(3, book.getMemID());
			ps2.setDate(4, book.getIssuedDate());
			return (ps1.executeUpdate()>0 && ps2.executeUpdate()>0);
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage().toString());
			return false;
		}
	}
	
	public ResultSet searchByName(String name)
	{
		PreparedStatement pst = null;
		ResultSet rs = null;
		try
		{
			String Query = "select ISBN, AcNo, BName, Author, Publisher, PublishedYear, Edition, Price, Status from LibBooks where Bname like ? and Status = 'Available'";
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
	
	public ResultSet searchByAccNo(String accno)
	{
		PreparedStatement pst = null;
		ResultSet rs = null;
		try
		{
			String Query = "select ISBN, AcNo, BName, Author, Publisher, PublishedYear, Edition, Price, Status from LibBooks where AcNo like ? and Status = 'Available'";
			pst = DBConnector.dbConnector().prepareStatement(Query);
			pst.setString(1, "%" + accno + "%");
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
