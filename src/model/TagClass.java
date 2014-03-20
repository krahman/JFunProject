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

public class TagClass extends DatabaseClass{	
	private String ErrorDesc = null;
	private int ErrorCode = 0;
	
	public TagClass(){	
		
	}
	
	public TreeMap<Integer, List<String>> getTags(String username){
		TreeMap<Integer, List<String>> tags = new TreeMap<Integer, List<String>>();
		List<String> tag = new ArrayList<String>();
		UserClass user = new UserClass();
		int userid = user.getUserid(username);
		String query = "";
		Connection conn = openConnection();
		ResultSet rs=null;
		try {			
			query = "select id, user_id, tags, share from shareit_bookmarks where user_id="+userid;
			PreparedStatement statement = conn.prepareStatement(query);			
			rs = statement.executeQuery();
			while (rs.next()){								
				tag.add(rs.getString("tags"));				
				tags.put(rs.getInt("id"), tag);				
			}
			rs.close();
			closeConnection();
		} catch (SQLException e) {			
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());			
		}
		
		return tags;
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
