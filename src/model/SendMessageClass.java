package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

public class SendMessageClass extends DatabaseClass {	
	private String ErrorDesc = null;
	private int ErrorCode = 0;
	
	public SendMessageClass(){	
		
	}
	
	public void sendMessage(String recipient, String sender, String message){
		int recipientid=0;
		int senderid=0;
		
		UserClass user = new UserClass();
		recipientid = user.getUserid(recipient);
		senderid = user.getUserid(sender);
		
		String query ="";
		Connection conn = openConnection();		
		try {			
			query = "insert into shareit_inboxes(recipient, sender, date, time, message, status) values ("+recipientid+
					", "+senderid+", curdate(), curtime(),'"+message+"',0)";
			PreparedStatement statement = conn.prepareStatement(query);			
			statement.executeUpdate();			
			closeConnection();
		} catch (SQLException e) {			
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());			
		}
		
	}
	
	public void saveMessage(String recipient, String sender, String message){
		int recipientid=0;
		int senderid=0;
		
		UserClass user = new UserClass();
		recipientid = user.getUserid(recipient);
		senderid = user.getUserid(sender);
		
		String query ="";
		Connection conn = openConnection();		
		try {			
			query = "insert into shareit_outboxes(recipient, sender, date, time, message, status) values ("+recipientid+
					", "+senderid+", curdate(), curtime(),'"+message+"',0)";
			PreparedStatement statement = conn.prepareStatement(query);			
			statement.executeUpdate();			
			closeConnection();
		} catch (SQLException e) {			
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());			
		}		
	}
	
	public TreeMap<Integer, List<String>> getInbox(String recipient){
		int recipientid=0;
		TreeMap<Integer, List<String>> inbox = new TreeMap<Integer, List<String>>();
		UserClass user = new UserClass();
		recipientid = user.getUserid(recipient);	
		
		String query ="";
		Connection conn = openConnection();
		ResultSet rs = null;
		UserClass usr = new UserClass();
		try {			
			query = "select id, recipient, sender, message, date, time, status from shareit_inboxes where recipient="+recipientid;
			PreparedStatement statement = conn.prepareStatement(query);			
			rs=statement.executeQuery();
			int i=0;
			
			while (rs.next()){
				i++;
				List<String> messages = new ArrayList<String>();
				messages.add(String.valueOf(rs.getInt("id")));
				messages.add(usr.getUsername(rs.getInt("recipient")));
				messages.add(usr.getUsername(rs.getInt("sender")));
				messages.add(rs.getString("message"));
				messages.add(rs.getString("date"));
				messages.add(rs.getString("time"));
				messages.add(String.valueOf(rs.getBoolean("status")));
				inbox.put(i, messages);
			}				
			rs.close();
			closeConnection();
		} catch (SQLException e) {			
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());			
		}	
		return inbox;
	}
	
	public TreeMap<Integer, List<String>> getOutbox(String sender){
		int senderid=0;
		TreeMap<Integer, List<String>> inbox = new TreeMap<Integer, List<String>>();
		UserClass user = new UserClass();
		senderid = user.getUserid(sender);	
		
		String query ="";
		Connection conn = openConnection();
		ResultSet rs = null;
		UserClass usr = new UserClass();
		try {			
			query = "select id, recipient, sender, message, date, time, status from shareit_outboxes where sender="+senderid;
			PreparedStatement statement = conn.prepareStatement(query);	
			
			rs=statement.executeQuery();
			int i=0;			
			while (rs.next()){
				i++;
				List<String> messages = new ArrayList<String>();
				messages.add(String.valueOf(rs.getInt("id")));
				messages.add(usr.getUsername(rs.getInt("recipient")));
				messages.add(usr.getUsername(rs.getInt("sender")));
				messages.add(rs.getString("message"));
				messages.add(rs.getString("date"));
				messages.add(rs.getString("time"));
				messages.add(String.valueOf(rs.getBoolean("status")));
				inbox.put(i, messages);
			}				
			rs.close();
			closeConnection();
		} catch (SQLException e) {			
			setErrorNumber(e.getErrorCode());
			setErrorDescription(e.getMessage());			
		}	
		return inbox;
	}
	
	public void getOubox(String sender){
		
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
