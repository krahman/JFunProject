/* Author : Khalilur Rahman */

manageEvent(window, "load", setUp);

var tabs = new Tabs();
var current = 0;

function manageEvent(eventObject, event, eventHandler){
	if (eventObject.addEventListener){
		eventObject.addEventListener(event, eventHandler, false);
	} else if (eventObject.attachEvent){
		eventObject.attachEvent("on"+event, eventHandler);
	} else {
		eventObject["on"+event]= eventHandler;
	}
}

function Tabs(){	
	var tab = new Array();
	var panels = new Array();	
	this.addTab = function(tabItem) {		
		var index = tab.length;
		tab[tab.length] = tabItem;
		manageEvent(tabItem, "click", function() {
			tabs.showPanel(index);								
		});			
	};
	
	this.addPanel = function(panel){		
		panels[panels.length] = panel;
	};
	
	this.showPanel = function(index) {	
		panels[current].style.display="none";
		tab[current].style.backgroundColor="rgb(101, 171, 58)";
		tab[current].style.borderBottom="solid 0.1em rgb(101, 171, 58)";
		tab[index].style.color="rgb(101, 171, 58)";
		tab[index].style.backgroundColor="rgb(255,255,255)";
		tab[index].style.borderBottom="solid 0.1em rgb(255,255,255)";
		tab[current].style.color="rgb(255, 255, 255)";
		panels[index].style.display="block";		
		current = index;			
	};
	
}

function setUp(){	
	var divs = document.getElementsByTagName('div');	
		for (var i =0; i < divs.length; i++){
			if (divs[i].className =='menu-item'){
				tabs.addTab(divs[i]);			
			} else if (divs[i].className == 'tab-panel-content'){
				tabs.addPanel(divs[i]);
			}
		}
	tabs.showPanel(current);	
}

