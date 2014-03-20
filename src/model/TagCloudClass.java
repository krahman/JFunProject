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

public class TagCloudClass extends DatabaseClass{	
	private String ErrorDesc = null;
	private int ErrorCode = 0;
	
	public TagCloudClass(){		
		
	}
	
	public TreeMap<Integer, List<String>> getTagCloud(){
		TreeMap<Integer, List<String>> tagCloud = new TreeMap <Integer, List<String>>();
		List<String> l = null;	
		ResultSet rs = null;
		Connection conn = openConnection();
		String query  = "";
		try {
			query = "select id, tags from shareit_bookmarks where tags != 'null' and share=1";
			PreparedStatement statement = conn.prepareStatement(query);
			rs = statement.executeQuery();			
		} catch (SQLException e){
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());
		}
		int i =0;
		try {
			while (rs.next()){
				int id = rs.getInt("id");
				String strId = String.valueOf(id);
				l = new ArrayList<String>();
				l.add(strId);
				l.add(rs.getString("tags"));
				tagCloud.put(i++, l);				
				//System.out.println(tagCloud.keySet());				
			}
			rs.close();
			closeConnection();
		} catch (SQLException e) {
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());
		}				
		return tagCloud;
	}
	
	public TreeMap<Integer, List<String>> getMyTagCloud(String username){
		TreeMap<Integer, List<String>> tagCloud = new TreeMap <Integer, List<String>>();
		List<String> l = null;	
		ResultSet rs = null;
		Connection conn = openConnection();
		String query  = "";
		try {
			query = "select a.id, a.tags, b.username username from shareit_bookmarks a, " +
					"shareit_users b where a.user_id=b.id and a.tags != 'null' and b.username ='"+username+"'";
			PreparedStatement statement = conn.prepareStatement(query);
			rs = statement.executeQuery();			
		} catch (SQLException e){
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());
		}
		int i =0;
		try {
			while (rs.next()){
				int id = rs.getInt("id");
				String strId = String.valueOf(id);
				l = new ArrayList<String>();
				l.add(strId);
				l.add(rs.getString("tags"));
				tagCloud.put(i++, l);				
				//System.out.println(tagCloud.keySet());				
			}
			rs.close();
			closeConnection();
		} catch (SQLException e) {
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());
		}				
		return tagCloud;
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
