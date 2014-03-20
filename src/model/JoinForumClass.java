package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class JoinForumClass extends DatabaseClass {	
	private String ErrorDesc = null;
	private int ErrorCode = 0;
	
	public JoinForumClass(){	
		
	}
	
	public void joinForum(int forumid, String username){
		UserClass user = new UserClass();
		int userid = user.getUserid(username);
		if (!isJoined(forumid, userid)) {
			String query ="";
			Connection conn = openConnection();		
			try {			
				query = "insert into shareit_joinforums(forumid, userid, date, time) values ("+forumid+
						", "+userid+", curdate(), curtime())";
				PreparedStatement statement = conn.prepareStatement(query);				
				statement.executeUpdate();			
				closeConnection();
			} catch (SQLException e) {			
				setErrorNumber(e.getErrorCode());
				setErrorDescription(e.getMessage());			
			}
		}		
	}
		
	public boolean isJoined(int forumid, int userid){
		boolean status=false;
		String query ="";
		Connection conn = openConnection();
		ResultSet rs = null;
		try {			
			query = "select * from shareit_joinforums where forumid="+forumid+" and userid="+userid;
			PreparedStatement statement = conn.prepareStatement(query);			
			rs = statement.executeQuery();
			if(!rs.next())
				status = false;
			else 
				status = true;
			closeConnection();
		} catch (SQLException e) {			
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());			
		}
		return status;
	}
	
	public boolean isJoined(int forumid, String username){
		boolean status=false;
		UserClass user = new UserClass();
		int userid = user.getUserid(username);
		String query ="";
		Connection conn = openConnection();
		ResultSet rs = null;
		try {			
			query = "select * from shareit_joinforums where forumid="+forumid+" and userid="+userid;
			PreparedStatement statement = conn.prepareStatement(query);			
			rs = statement.executeQuery();
			if (!rs.next())
				status = false;
			else 
				status = true;
			closeConnection();
		} catch (SQLException e) {			
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());			
		}
		return status;
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
