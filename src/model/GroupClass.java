package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class GroupClass extends DatabaseClass {	
	private String ErrorDesc = null;
	private int ErrorCode = 0;
	
	public GroupClass(){	
		
	}
	
	public void setGroup(String groupname, String description, String username){
		String query ="";
		Connection conn = openConnection();		
		try {			
			query = "insert into shareit_groups(groupname, description, date, time, creator) values ('"+groupname +
					"', '"+description+"', curdate(), curtime(),'"+username+"')";
			PreparedStatement statement = conn.prepareStatement(query);			
			statement.executeUpdate();			
			closeConnection();
		} catch (SQLException e) {			
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());			
		}
		
	}
	
	public void joinGroup(String groupname, String username){
		String query ="";
		Connection conn = openConnection();
		UserClass user = new UserClass();		
		int groupid = getGroupid(groupname);
		int userid = user.getUserid(username);
		
		try {			
			query = "insert into shareit_joingroups(userid, groupid, date, time) values ("+userid+
					", "+groupid+", curdate(), curtime())";
			PreparedStatement statement = conn.prepareStatement(query);			
			statement.executeUpdate();
			closeConnection();
		} catch (SQLException e) {			
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());			
		}		
	}

	public int getGroupid(String groupname) {
		int groupid= 0;
		String query ="";
		Connection conn = openConnection();
		ResultSet rs = null;
		try {			
			query = "select id from shareit_groups where groupname='"+groupname+"'";
			PreparedStatement statement = conn.prepareStatement(query);			
			rs = statement.executeQuery();	
			while (rs.next())
				groupid = rs.getInt("id");
			rs.close();
			closeConnection();
		} catch (SQLException e) {			
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());			
		}
		return groupid;
	}	
	
	public String getGroupName(int groupid) {
		String groupname="";
		String query ="";
		Connection conn = openConnection();
		ResultSet rs = null;
		try {			
			query = "select groupname from shareit_groups where groupname="+groupid;
			PreparedStatement statement = conn.prepareStatement(query);			
			rs = statement.executeQuery();	
			while (rs.next())
				groupname = rs.getString("groupname");
			rs.close();
			closeConnection();
		} catch (SQLException e) {			
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());			
		}
		return groupname;
	}
	
	public TreeMap<Integer, List<String>> getGroupList() {
		TreeMap<Integer, List<String>> groupList = new TreeMap<Integer, List<String>>();		
		String query ="";
		Connection conn = openConnection();
		ResultSet rs = null;
		try {			
			query = "select id, groupname, description, date, time, creator from shareit_groups";
			PreparedStatement statement = conn.prepareStatement(query);			
			rs = statement.executeQuery();
			while (rs.next()){
				List<String> group = new ArrayList<String>();
				group.add(rs.getString("groupname"));
				group.add(rs.getString("description"));
				group.add(rs.getString("date"));
				group.add(rs.getString("time"));
				group.add(rs.getString("creator"));
				groupList.put(rs.getInt("id"), group);
			}
			rs.close();
			closeConnection();
		} catch (SQLException e) {			
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());			
		}
		return groupList;
	}
	
	public TreeMap<Integer, List<String>> findGroup(String groupname) {
		TreeMap<Integer, List<String>> groupList = new TreeMap<Integer, List<String>>();
		String query ="";
		Connection conn = openConnection();
		ResultSet rs = null;
		try {			
			query = "select id, groupname, description, date, time, creator " +
					"from shareit_groups where groupname like '%"+groupname+"%'";
			PreparedStatement statement = conn.prepareStatement(query);			
			rs = statement.executeQuery();
			while (rs.next()){
				List<String> group = new ArrayList<String>();				
				group.add(rs.getString("groupname"));
				group.add(rs.getString("description"));
				group.add(rs.getString("date"));
				group.add(rs.getString("time"));
				group.add(rs.getString("creator"));
				groupList.put(rs.getInt("id"), group);
			}	
			rs.close();
			closeConnection();
		} catch (SQLException e) {			
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());			
		}
		return groupList;
	}
	
	public TreeMap<Integer, List<String>> findThisGroup(int groupid) {
		TreeMap<Integer, List<String>> groupList = new TreeMap<Integer, List<String>>();
		String query ="";
		Connection conn = openConnection();
		ResultSet rs = null;
		try {			
			query = "select id, groupname, description, date, time, creator " +
					"from shareit_groups where id ="+groupid;
			PreparedStatement statement = conn.prepareStatement(query);			
			rs = statement.executeQuery();
			while (rs.next()){
				List<String> group = new ArrayList<String>();				
				group.add(rs.getString("groupname"));
				group.add(rs.getString("description"));
				group.add(rs.getString("date"));
				group.add(rs.getString("time"));
				group.add(rs.getString("creator"));
				groupList.put(rs.getInt("id"), group);
			}	
			rs.close();
			closeConnection();
		} catch (SQLException e) {			
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());			
		}
		return groupList;
	}
	
	public TreeMap<Integer, List<String>> getMyGroups(String username) {
		
		TreeMap<Integer, List<String>> groupList = new TreeMap<Integer, List<String>>();
		String query ="";
		Connection conn = openConnection();
		ResultSet rs = null;
		try {			
			query = "select a.id id, a.groupname groupname, a.description description, " +
					"a.date date, a.time time, a.creator creator " +
					"from shareit_groups a, shareit_users b, shareit_joingroups c " +
					"where a.id = c.groupid and c.userid = b.id and b.username = '"+username+"'";
			
			PreparedStatement statement = conn.prepareStatement(query);			
			rs = statement.executeQuery();
			while (rs.next()){
				List<String> group = new ArrayList<String>();
				group.add(rs.getString("groupname"));
				group.add(rs.getString("description"));
				group.add(rs.getString("date"));
				group.add(rs.getString("time"));
				group.add(rs.getString("creator"));
				groupList.put(rs.getInt("id"), group);
				//System.out.println(groupList);
			}	
			
			rs.close();
			closeConnection();
		} catch (SQLException e) {			
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());			
		}
		return groupList;
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
