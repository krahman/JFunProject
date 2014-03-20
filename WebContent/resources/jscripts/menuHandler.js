
function createMenu(){	
	var divs = 	
		"<div class=top-menu-right>"+	        		
			"<input id='queryColumn' type='text' size='25'></input>"+
			"<input id='buttonQuery' type='button' value='Search' onclick='search()'></input>"+	        		
		"</div>"+
		"<div class=top-menu-left>"+
			"<div class='top-menu-item' id='loginTopMenu'><a href='doLoginMain'>Login</a></div>" +
			"<div class='top-menu-item' id='registerTopMenu'><a href='doRegisterMain'>Register</a></div>" +
			"<div class='top-menu-item' id='homeTopMenu'><a href='index.html'>Home</a></div>"+
		"</div>";
				
	var popularList = document.getElementById("top-menu");
	popularList.innerHTML = "";
	popularList.innerHTML = divs;	
}

