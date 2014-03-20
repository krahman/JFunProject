package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class ForumClass extends DatabaseClass {	
	private String ErrorDesc = null;
	private int ErrorCode = 0;
	
	public ForumClass(){	
		
	}
	
	public void setForum(String forumname, String description, String username){
		String query ="";
		Connection conn = openConnection();		
		try {			
			query = "insert into shareit_forums(forumname, description, date, time, creator) values ('"+forumname+
					"', '"+description+"', curdate(), curtime(),'"+username+"')";
			PreparedStatement statement = conn.prepareStatement(query);			
			statement.executeUpdate();			
			closeConnection();
		} catch (SQLException e) {			
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());			
		}
		
	}
	
	public void joinForums(String forumname, String username){
		UserClass user = new UserClass();		
		int forumid = getForumid(forumname);
		int userid = user.getUserid(username);
		
		String query ="";
		Connection conn = openConnection();		
		try {			
			query = "insert into shareit_joinforums(userid, forumid, date, time) values ("+userid+
					", "+forumid+", curdate(), curtime())";
			PreparedStatement statement = conn.prepareStatement(query);			
			statement.executeUpdate();			
			closeConnection();
		} catch (SQLException e) {			
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());			
		}
	}

	public int getForumid(String forumname) {
		int forumid = 0;		
		String query ="";
		Connection conn = openConnection();
		ResultSet rs = null;
		try {			
			query = "select id from shareit_forums where forumname='"+forumname+"'";
			PreparedStatement statement = conn.prepareStatement(query);			
			rs = statement.executeQuery();
			while (rs.next())
				forumid = rs.getInt("id");
			rs.close();
			closeConnection();
		} catch (SQLException e) {			
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());			
		}
		return forumid;
	}
	
	public TreeMap<Integer, List<String>> getForumList() {
		TreeMap<Integer, List<String>> forumList = new TreeMap<Integer, List<String>>();		
		String query ="";
		Connection conn = openConnection();
		ResultSet rs = null;
		try {			
			query = "select id, forumname, description, date, time, creator from shareit_forums";
			PreparedStatement statement = conn.prepareStatement(query);			
			rs = statement.executeQuery();
			while (rs.next()){
				List<String> forum = new ArrayList<String>();
				forum.add(rs.getString("forumname"));
				forum.add(rs.getString("description"));
				forum.add(rs.getString("date"));
				forum.add(rs.getString("time"));
				forum.add(rs.getString("creator"));
				forumList.put(rs.getInt("id"), forum);
			}
			rs.close();
			closeConnection();
		} catch (SQLException e) {			
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());			
		}
		return forumList;
	}
	
	public TreeMap<Integer, List<String>> findForum(String forumname) {
		TreeMap<Integer, List<String>> forumList = new TreeMap<Integer, List<String>>();		
		String query ="";
		Connection conn = openConnection();
		ResultSet rs = null;
		try {			
			query = "select id, forumname, description, date, time, creator " +
					"from shareit_forums where forumname like '%"+forumname+"%'";
			PreparedStatement statement = conn.prepareStatement(query);			
			rs = statement.executeQuery();
			while (rs.next()){
				List<String> forum = new ArrayList<String>();
				forum.add(rs.getString("forumname"));
				forum.add(rs.getString("description"));
				forum.add(rs.getString("date"));
				forum.add(rs.getString("time"));
				forum.add(rs.getString("creator"));
				forumList.put(rs.getInt("id"), forum);
			}
			rs.close();
			closeConnection();
		} catch (SQLException e) {			
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());			
		}
		return forumList;
	}
	
	public TreeMap<Integer, List<String>> findThisForum(int forumid) {
		TreeMap<Integer, List<String>> forumList = new TreeMap<Integer, List<String>>();
		String query ="";
		Connection conn = openConnection();
		ResultSet rs = null;
		try {			
			query = "select id, forumname, description, date, time, creator " +
					"from shareit_forums where id ="+forumid;
			PreparedStatement statement = conn.prepareStatement(query);			
			rs = statement.executeQuery();
			while (rs.next()){
				List<String> forum = new ArrayList<String>();				
				forum.add(rs.getString("forumname"));
				forum.add(rs.getString("description"));
				forum.add(rs.getString("date"));
				forum.add(rs.getString("time"));
				forum.add(rs.getString("creator"));
				forumList.put(rs.getInt("id"), forum);
			}	
			rs.close();
			closeConnection();
		} catch (SQLException e) {			
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());			
		}
		return forumList;
	}
	
	public TreeMap<Integer, List<String>> getMyForums(String username) {
		
		TreeMap<Integer, List<String>> forumList = new TreeMap<Integer, List<String>>();
		String query ="";
		Connection conn = openConnection();
		ResultSet rs = null;
		try {			
			query = "select a.id id, a.forumname forumname, a.description description, " +
					"a.date date, a.time time, a.creator creator " +
					"from shareit_forums a, shareit_users b, shareit_joinforums c " +
					"where a.id = c.forumid and c.userid = b.id and b.username = '"+username+"'";
			
			PreparedStatement statement = conn.prepareStatement(query);			
			rs = statement.executeQuery();
			while (rs.next()){
				List<String> forum = new ArrayList<String>();
				forum.add(rs.getString("forumname"));
				forum.add(rs.getString("description"));
				forum.add(rs.getString("date"));
				forum.add(rs.getString("time"));
				forum.add(rs.getString("creator"));
				forumList.put(rs.getInt("id"), forum);
			}	
			
			rs.close();
			closeConnection();
		} catch (SQLException e) {			
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());			
		}
		return forumList;
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
