/* Author : Khalilur Rahman */

function validateForm(objectForm)
{	
	// ensure name text field is not blank
	var title = document.getElementById("title"); 
	var link = document.getElementById("link");
	var tag = document.getElementById("tag");
	var desc = document.getElementById("desc");
	
	if( title.value=="")
	{
		//objectForm.textTitle.focus();
		return false;
	}
	
	if( link.value == "" )
	{
		//objectForm.textLink.focus();
		return false;
	}

	if( tag.value == "" )
	{
		//objectForm.textTag.focus();
		return false;
	}
	
	if( desc.value == "" )
	{
		//objectForm.textDesc.focus();
		return false;
	}	
		
	// if we get here, then all form controls are filled, so return true
	return true;
}