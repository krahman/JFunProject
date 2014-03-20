package model;
/**
 * @author khalilur
 *
 */
public class LoginBeans {
	private String userName;
	private String userPassword;
	
	public String getUserName(){
		return(userName);
	}
	  
	public void setUserName(String userName){
		if(!isMissing(userName)){
			this.userName = userName;
		}
	}
	  
	public String getUserPassword(){
		return(userPassword);
	}
	  
	   
	public void setUserPassword(String userPassword){
		if(isMissing(userPassword)){
			this.userPassword = userPassword;
		}
	}	

	private boolean isMissing(String value) {
		return((value == null) || (value.trim().equals("")));
	}
}
