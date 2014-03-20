package model;
/**
 * @author khalilur
 *
 */
import java.io.*;

public class UserBeans implements Serializable {
  private String firstName = "";
  private String lastName = "";
  private String emailAddress = "";
  private String userName = "";
  private String userPassword = "";

  public String getFirstName() {
    return(firstName);
  }

  public void setFirstName(String firstName) {
    if (!isMissing(firstName)) {
      this.firstName = firstName;
    }
  }

  public String getLastName() {
    return(lastName);
  }

  public void setLastName(String lastName) {
    if (!isMissing(lastName)) {
      this.lastName = lastName;
    }
  }
  
  public String getEmailAddress() {
	  return(emailAddress);
  }
  
  public void setEmailAddress(String emailAddress){
	  if (!isMissing(emailAddress)) {
		  this.emailAddress = emailAddress;
	  }
  }
  
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
  //further, checking will be done in client side using AJAX 
  private boolean isMissing(String value) {
    return((value == null) || (value.trim().equals("")));
  }
  
  private boolean isMatched(String value) {
	return((value == userPassword));
  }
}
