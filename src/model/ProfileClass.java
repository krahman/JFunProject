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

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

import sun.security.action.GetIntegerAction;

public class ProfileClass extends DatabaseClass{	
	private String ErrorDesc = null;
	private int ErrorCode = 0;
	
	public ProfileClass(){	
		
	}
	
	public List<String> getProfile(String username) {
		UserClass user = new UserClass();
		int userid = user.getUserid(username);
		List<String> profile = new ArrayList<String>();
		String query = "";		
		Connection conn = openConnection();
		ResultSet rs=null;
		try {			
			query = "select userid, address, dateofbirth, program, school, faculty from shareit_profiles where userid="+userid;
			PreparedStatement statement = conn.prepareStatement(query);			
			rs = statement.executeQuery();
			while (rs.next()){				
				profile.add(rs.getString("address"));
				profile.add(rs.getString("dateofbirth"));
				profile.add(rs.getString("program"));
				profile.add(rs.getString("school"));
				profile.add(rs.getString("faculty"));
			}
			rs.close();
			closeConnection();
		} catch (SQLException e) {			
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());			
		}	
		
		return profile;
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
