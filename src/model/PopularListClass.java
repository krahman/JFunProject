/**
 * 
 */
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
/**	
 * @author khalilur
 *
 */
public class PopularListClass extends DatabaseClass{	
	private int ErrorCode = 0;
	private String ErrorDesc = null;
	
	public PopularListClass(){	
		
	}
	
	
	public TreeMap<Integer, List<String>> getList(){
		TreeMap<Integer, List<String>> popularList = new TreeMap <Integer, List<String>>();
		List<String> l = null;
		Connection conn = openConnection();
		String query = "";
		ResultSet rs = null;
		try {
			query = "select a.id id, a.uri uri, a.title title, b.username username, " +
					"a.description description, a.bookmark_time bookmark_time, a.bookmark_date bookmark_date, a.tags tags " +
					"from shareit_bookmarks a, shareit_users b where a.user_id=b.id and a.share=1 order by a.id desc";	
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
				l.add(rs.getString("uri"));
				l.add(rs.getString("title"));
				l.add(rs.getString("username"));
				l.add(rs.getString("description"));
				l.add(rs.getString("bookmark_time"));
				l.add(rs.getString("bookmark_date"));
				l.add(rs.getString("tags"));
				popularList.put(i++, l);				
				//System.out.println(popularList.keySet());				
			}
			rs.close();
			closeConnection();
		} catch (SQLException e) {
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());
		}
		return popularList;
	}
	
	public TreeMap<Integer, List<String>> getFriendBookmarks(String username){
		TreeMap<Integer, List<String>> popularList = new TreeMap <Integer, List<String>>();
		List<String> l = null;
		Connection conn = openConnection();
		String query = "";
		ResultSet rs = null;
		try {
			query = "select a.id id, a.uri uri, a.title title, b.username username, " +
					"a.description description, a.bookmark_time bookmark_time, a.bookmark_date bookmark_date, a.tags tags " +
					"from shareit_bookmarks a, shareit_users b where a.user_id=b.id and username='"+username+"' and a.share=1 order by a.id desc";	
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
				l.add(rs.getString("uri"));
				l.add(rs.getString("title"));
				l.add(rs.getString("username"));
				l.add(rs.getString("description"));
				l.add(rs.getString("bookmark_time"));
				l.add(rs.getString("bookmark_date"));
				l.add(rs.getString("tags"));
				popularList.put(i++, l);				
				//System.out.println(popularList.keySet());				
			}
			rs.close();
			closeConnection();
		} catch (SQLException e) {
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());
		}
		return popularList;
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
