package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class SaveCommentClass extends DatabaseClass {	
	private String ErrorDesc = null;
	private int ErrorCode = 0;
	
	public SaveCommentClass(){	
		
	}
	
	public void insertComment(int bookmark_id, String username, String comment){
		String query ="";
		Connection conn = openConnection();		
		try {			
			query = "insert into shareit_comments(bookmark_id, user_id, comment, comment_date, comment_time) values ("+bookmark_id+
					", "+getUserid(username)+", '"+comment+"', curdate(), curtime())";
			PreparedStatement statement = conn.prepareStatement(query);			
			statement.executeUpdate();			
			closeConnection();
		} catch (SQLException e) {			
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());			
		}
		
	}
	
	public String getUserid(String username){
		String userid = null;
		ResultSet rs = null;
		String query ="";
		Connection conn = openConnection();
		try {
			query ="select id from shareit_users where username ='"+username+"'";
			PreparedStatement statement = conn.prepareStatement(query);
			rs = statement.executeQuery();
			if (rs.next())
				userid = rs.getString("id").toString();
			rs.close();
			closeConnection();			
		} catch (SQLException e) {
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());
		}		
		return userid;
	}	

	public TreeMap<Integer, List<String>> getCommentList(int bookmarkid){
		TreeMap<Integer, List<String>> commentList = new TreeMap <Integer, List<String>>();
		List<String> l = null;	
		ResultSet rs = null;
		String query ="";
		Connection conn = openConnection();
		try {
			int i =0;
			query ="select a.id id, a.bookmark_id bookmark_id, a.comment comment, " +
					"a.comment_time comment_time, a.comment_date comment_date, " +
					"b.username username from shareit_comments a, shareit_users b where " +
					"bookmark_id ='"+bookmarkid+"' and a.user_id = b.id order by a.id";
			PreparedStatement statement = conn.prepareStatement(query);
			rs = statement.executeQuery();			
			while (rs.next()){
				int id = rs.getInt("id");
				String strId = String.valueOf(id);
				int bookmark_id = rs.getInt("bookmark_id");
				String strBookmarkid = String.valueOf(bookmark_id);				
				l = new ArrayList<String>();
				l.add(strId);
				l.add(strBookmarkid);
				l.add(rs.getString("username"));
				l.add(rs.getString("comment"));
				l.add(rs.getString("comment_time"));
				l.add(rs.getString("comment_date"));				
				commentList.put(i++, l);
			}			
			rs.close();			
			closeConnection();
		} catch (SQLException e) {
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());
		}		
		
		return commentList;
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
