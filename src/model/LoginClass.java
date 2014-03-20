package model;
/**
 * @author khalilur
 *
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginClass extends DatabaseClass{	
	private Boolean passed = false;
	private int ErrorCode = 0;
	private String ErrorDesc = null;
	
	public LoginClass(){		
	}
	
	public Boolean Authenticate(String username, String password){
		String query = "";
		Connection conn = openConnection();
		ResultSet rs = null;
		try {			
			query = "select * from shareit_users where username ='"+ username +"' and password ='"+password +"'";
			PreparedStatement statement = conn.prepareStatement(query); 
			rs = statement.executeQuery();				
			
		} catch (SQLException e) {		
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());
		}
		try {
			while(rs.next()){
				passed = true;			
			}
			rs.close();
			closeConnection();
		} catch (SQLException e) {		
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());
		}		
		return passed;
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
