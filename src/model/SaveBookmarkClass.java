package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class SaveBookmarkClass extends DatabaseClass{
	private String ErrorDesc = null;
	private int ErrorCode = 0;
	
	public SaveBookmarkClass(){
	}
	
	public void insertBookmark(String link, String title, String description, Integer userid, String tags, int share){
		String query ="";
		Connection conn = openConnection();		
		try {
			query = "insert into shareit_bookmarks(uri, title, description, user_id, bookmark_date, bookmark_time, tags, share) values ('"+link+
					"', '"+title+"', '"+description+"', "+userid+", curdate(), curtime(), '"+tags+"',"+share+")";
			//System.out.println(query);
			PreparedStatement statement = conn.prepareStatement(query);
			statement.executeUpdate();
			closeConnection();
		} catch (SQLException e) {			
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());			
		}
	}
	
	public int getUserid(String username){
		int userid = 0;
		ResultSet rs = null;
		String query ="";
		Connection conn = openConnection();
		try {
			query ="select id from shareit_users where username ='"+username+"'";
			
			PreparedStatement statement = conn.prepareStatement(query);
			rs = statement.executeQuery();
			System.out.println(rs.getRow());
			if (rs.next())
				userid = rs.getInt("id");
			
			rs.close();			
			closeConnection();
		} catch (SQLException e) {
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());
		}		
		return userid;
	}	

	public List<String> getTimeDate(int userid, String link, String description){
		List<String> TimeDate = null;
		ResultSet rs = null;
		String query ="";
		Connection conn = openConnection();
		try {
			query ="select id, bookmark_time, bookmark_date from shareit_bookmarks where " +
					"user_id = "+userid+" and uri ='"+link+"' and description ='"+description+"'";
			PreparedStatement statement = conn.prepareStatement(query);
			rs = statement.executeQuery();
			
			if (rs.next()){
				TimeDate = new ArrayList<String>();
				TimeDate.add(rs.getString("id").toString());
				TimeDate.add(rs.getString("bookmark_time").toString());
				TimeDate.add(rs.getString("bookmark_date").toString());
			}
			rs.close();
			closeConnection();
		} catch (SQLException e) {
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());
		}		
		
		return TimeDate;
	}
	
	public TreeMap<Integer, List<String>> getTagList(int bookmarkid){
		TreeMap<Integer, List<String>> tagList = new TreeMap <Integer, List<String>>();
		List<String> l = null;	
		ResultSet rs = null;
		String query ="";
		Connection conn = openConnection();
		try {
			int i =0;
			query ="select a.id id, a.bookmark_id bookmarkid, a.tag_name tagname, " +					
					"b.uri link from shareit_tags a, shareit_bookmarks b where " +
					"a.bookmark_id = b.id and a.bookmark_id ="+bookmarkid;			
			PreparedStatement statement = conn.prepareStatement(query);
			rs = statement.executeQuery();			
			while (rs.next()){
				int id = rs.getInt("id");
				String strId = String.valueOf(id);
				int bookmark_id = rs.getInt("bookmarkid");
				String strBookmarkid = String.valueOf(bookmark_id);				
				l = new ArrayList<String>();
				l.add(strId);
				l.add(strBookmarkid);
				l.add(rs.getString("tagname"));								
				l.add(rs.getString("link"));
				tagList.put(i++, l);
			}			
			rs.close();			
			closeConnection();
		} catch (SQLException e) {
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());
		}				
		return tagList;
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
