package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.management.Query;

public class UserClass extends DatabaseClass{	
	private String ErrorDesc = null;
	private int ErrorCode = 0;
	
	public UserClass(){	
		
	}
	
	public int getUserid(String username) {
		int uid = 0;
		String query = "";
		Connection conn = openConnection();
		ResultSet rs=null;
		try {			
			query = "select id from shareit_users where username='"+username+"'";
			PreparedStatement statement = conn.prepareStatement(query);			
			rs = statement.executeQuery();
			while (rs.next())
				uid = rs.getInt("id");
			rs.close();
			closeConnection();
		} catch (SQLException e) {			
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());			
		}
		
		return uid;
	}
	

	public String getUsername(int userid) {
		String username="";
		String query = "";
		Connection conn = openConnection();
		ResultSet rs=null;
		try {			
			query = "select username from shareit_users where id="+userid+"";
			PreparedStatement statement = conn.prepareStatement(query);			
			rs = statement.executeQuery();
			while (rs.next())
				username = rs.getString("username");
			rs.close();
			closeConnection();
		} catch (SQLException e) {			
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());			
		}
		return username;
	}
	
	public List<String> getDetail(String username) {
		List<String> detail = new ArrayList<String>();
		String query = "";
		Connection conn = openConnection();
		ResultSet rs=null;
		try {			
			query = "select first_name, last_name, email from shareit_users where username='"+username+"'";
			PreparedStatement statement = conn.prepareStatement(query);			
			rs = statement.executeQuery();
			while (rs.next()){
				detail.add(rs.getString("first_name"));
				detail.add(rs.getString("last_name"));
				detail.add(rs.getString("email"));
			}
			rs.close();
			closeConnection();
		} catch (SQLException e) {			
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());			
		}		
		return detail;
	}
	
	public void setErrorNumber(int errorNumber){
		ErrorCode = errorNumber;
	}
	public int getErrorNumber(){
		return ErrorCode;
	}
	
	public void setErrorDescription(String errorDescription){
		ErrorDesc = errorDescription;
	}
	public String getErrorDescription(){
		return ErrorDesc;		
	}

	
}
