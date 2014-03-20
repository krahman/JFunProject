/* Author : Khalilur Rahman */

function manageEvent(eventObject, event, eventHandler){
	if (eventObject.addEventListener){
		eventObject.addEventListener(event, eventHandler, false);
	} else if (eventObject.attachEvent){
		eventObject.attachEvent("on"+event, eventHandler);
	} else {
		eventObject["on"+event]= eventHandler;
	}
}