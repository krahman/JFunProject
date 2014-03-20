/* Author : Khalilur Rahman */

function validateForm(objectForm)
{	
	// ensure name text field is not blank
	if( objectForm.firstName.value == "" )
	{
		objectForm.firstName.focus();
		return false;
	}
	
	if( objectForm.lastName.value == "" )
	{
		objectForm.lastName.focus();
		return false;
	}

	if( objectForm.emailAddress.value == "" )
	{
		objectForm.emailAddress.focus();
		return false;
	}
	
	if( objectForm.userName.value == "" )
	{
		objectForm.userName.focus();
		return false;
	}
	
	if( objectForm.userPassword.value == "" )
	{
		objectForm.userPassword.focus();
		return false;
	}
	
	if( objectForm.retypePassword.value == "" )
	{
		objectForm.retypePassword.focus();
		return false;
	} 
	
	if(objectForm.userPassword.value != objectForm.retypePassword.value)
	{		objectForm.userPassword.focus();
		objectForm.userPassword.value="";
		objectForm.retypePassword.value="";
		return false;				
	}
	
	// if we get here, then all form controls are filled, so return true
	return true;
}