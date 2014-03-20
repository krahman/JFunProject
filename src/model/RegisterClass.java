package model;
/**
 * @author khalilur
 *
 */
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

public class RegisterClass extends DatabaseClass{
	private int ErrorCode = 0;
	private String ErrorDesc = null;	
	
	public void UserData(String username, String password, String lastname, String firstname, String email){
		Connection conn = openConnection();
		String query = "";
		try {
			query = "insert into shareit_users(username, password, first_name, last_name, email, " +
					"creation_time, creation_date) values ('"+username+
					"', '"+password+"','"+firstname+
					"', '"+lastname+"', '"+email+"', curtime(), curdate())";			
			PreparedStatement statement = conn.prepareStatement(query);
			statement.executeUpdate();		
			closeConnection();
		} catch (SQLException e) {			
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());			
		}
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
