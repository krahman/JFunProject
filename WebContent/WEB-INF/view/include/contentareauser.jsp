<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>			
			<div id="middle-column">		
			<div class="tab-panel">
				<div class="tab-panel-content" id="one">
					<div class="middle-column-box-white">
						<div class="profile">        				
        				<div class="middle-column-box-title-grey">My Profile</div>
        				<div id="myProfile"></div> 	  
        				<div class="middle-column-box-title-yellow">My Bookmarks</div>
        				<div id="myBookmarkSummary"></div> 
        				<div class="middle-column-box-title-blue">My Tags</div>
        				<div class="tagCloudBox">
        					<div id="myTagsSummary"></div>
        				</div>
        				</div>
					</div>
				</div>				
				<div class="tab-panel-content" id="two">
					<div class="middle-column-box-white">
        				<div class="middle-column-box-title-grey">Bookmarks</div>        				
        				<div id="myBookmarks"></div> 	  	
					</div>
				</div>
				<div class="tab-panel-content" id="three">
					<div class="middle-column-box-white">
        				<div class="middle-column-box-title-grey">Save Link</div>        				
        				<div id="mySaveLink">      
        					<div class="mySaveForm">  				
        					<form>
        						<label for="user">Title</label>
								<input name="textTitle" type="text" id="title"></input>
								
								<label for="emailaddress">Link:</label>
								<input type="text" name= "textLink" id="link"></input>
								
								<label for="emailaddress">Tag</label>								
								<input type="text" name= "textTag" id="tag"></input>							
								
								<label for="comments">Description</label>
								<p>Please, fill 'Tag' with comma separated words.</p>
								<textarea name= "textDesc" id="desc"></textarea><br />
								
								<label for="terms">Don't share</label>
								<input type="checkbox" id="isSharing" name="sharing" class="boxes" /><br />
								<input type="button" id="submitbutton" value="Save" onclick="saveBookmark()">						
											
							</form>
							</div>
        				</div> 	  	
					</div>
				</div>				
				<div class="tab-panel-content" id="four">
					<div class="middle-column-box-white">
        				<div class="middle-column-box-title-grey">My Friends</div>
        				<div id="myFriends"></div>
        			</div>
        			<div class="middle-column-box-white"> 	  
        				<div class="middle-column-box-title-yellow">My Group</div>
        				<div id="myGroup"></div> 
        			</div>
        			<div class="middle-column-box-white">
        				<div class="middle-column-box-title-blue">My Forum</div>
        				<div id="myForums"></div>
					</div>
				</div>
				<div class="tab-panel-content" id="five">
					<div class="middle-column-box-white">
        				<div class="middle-column-box-title-grey">Tags</div>        				
        				<div id="myTags"></div> 	  	
					</div>
				</div>
				<div class="tab-panel-content" id="six">
					<div class="middle-column-box-white">
        				<div class="middle-column-box-title-grey">My Inbox</div>
        				<div id="inboxes"></div> 	  
        				<div class="middle-column-box-title-yellow">Sent Message</div>
        				<div id="outboxes"></div>        				 	  	
					</div>
				</div>
			</div>
			<div class="middle-column-box-white">
											
			</div>				
				
		</div>
			