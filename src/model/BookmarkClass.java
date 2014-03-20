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

public class BookmarkClass extends DatabaseClass{	
	private String ErrorDesc = null;
	private int ErrorCode = 0;
	
	public BookmarkClass(){	
		
	}
	
	public TreeMap<Integer, List<String>> getBookmarks(String username) {
		TreeMap<Integer, List<String>> bookmarks = new TreeMap<Integer, List<String>>();
		
		UserClass user = new UserClass();
		int userid = user.getUserid(username);
		String query = "";
		Connection conn = openConnection();
		ResultSet rs=null;
		try {			
			query = "select id, uri, title, description, user_id, bookmark_time, " +
					"bookmark_date, tags, share from shareit_bookmarks where user_id="+userid;
			PreparedStatement statement = conn.prepareStatement(query);			
			rs = statement.executeQuery();
			while (rs.next()){
				List<String> detil = new ArrayList<String>();
				detil.add(rs.getString("uri"));
				detil.add(rs.getString("title"));				
				detil.add(rs.getString("description"));				
				bookmarks.put(rs.getInt("id"), detil);				
			}
			rs.close();
			closeConnection();
		} catch (SQLException e) {			
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());			
		}
		System.out.println(bookmarks);
		return bookmarks;
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
