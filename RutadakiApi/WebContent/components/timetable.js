var timetableModule = angular.module('ui.timetable', []);

timetableModule.directive('rnTimetable', function() {
    return {
        restrict: 'AE',
        templateUrl: 'components/timetable.html',
        scope: {
            timeTable: '=ngModel'
        },
        link: function(scope, iElement, iAttrs) {
        	
            scope.weekDays = [
	                   {id:'MONDAY', text:'Lunes'},
	                   {id:'TUESDAY', text:'Martes'},
	                   {id:'WEDNESDAY', text:'Mi\xE9rcoles'},
	                   {id:'THURSDAY', text:'Jueves'},
	                   {id:'FRIDAY', text:'Viernes'},
	                   {id:'SATURDAY', text:'S\xE1bado'},
	                   {id:'SUNDAY', text:'Domingo'},
	                   {id:'EVERYDAY', text:'Todos los d\xEDas'},
	                   {id:'MIDWEEK', text:'De lunes a viernes'},
	                   {id:'WEEKEND', text:'Fin de semana'}
	                   ];
            
            // removes an entry from the timetable
        	scope.removeEntry = function(index){
    		    scope.timeTable.entries.splice(index, 1);
    		};
    		
    		// adds an entry to the timetable
    		scope.newEntry = function(){
    			
    			var entry = {
//    					"id":null,
    					"version":1,
    					"lastUpdate":null,
    					"startHour":{"hour":"","minutes":""},
    					"endHour":{"hour":"","minutes":""},
    					"dayOfTheWeek":"EVERYDAY"};
    			
    			scope.timeTable.entries.push(entry);
    			
    		};
    		
    		scope.$watch('timeTable.entries', function (newVal, oldVal) { 
    			// Every time the entries change, we have to update
    			// the hours, to display them in a friendly way.
    			// "asText" is the value the users modify in the gui,
    			// we parse it and update de "hour" and "minutes" values
    			// every time the user makes a change:
    			for (var i = 0; i < newVal.length; i++) {
    				var entry = newVal[i];
    				
    				if(entry.startHour.asText===undefined){
    					if(entry.startHour != null){
    						entry.startHour.asText = pad(entry.startHour.hour, 2) + ":" + pad(entry.startHour.minutes, 2);
    					}
    					if(entry.endHour != null){
    						entry.endHour.asText = pad(entry.endHour.hour, 2) + ":" + pad(entry.endHour.minutes, 2);
    					}
    				}
    				
    				// Parse and set Start Hour
    				if (entry.startHour.asText==""){
    					entry.startHour=null;
    				} else {
	    				var startHour = entry.startHour.asText.split(":")[0];
	    				var startMinutes = entry.startHour.asText.split(":")[1];
	    				entry.startHour.hour = startHour;
	    				entry.startHour.minutes = startMinutes;
    				}
    				
    				// Same for End Hour
    				if (entry.endHour.asText==""){
    					entry.endHour=null;
    				} else {
						var endHour = entry.endHour.asText.split(":")[0];
						var endMinutes = entry.endHour.asText.split(":")[1];
						entry.endHour.hour = endHour;
						entry.endHour.minutes = endMinutes;
    				}
    			}
    			
    		}, true); //this argument has to be true -> ¿¿??
        }
   
    };
});

//Private functions:
function pad(num, size) {
    var s = num+"";
    while (s.length < size) s = "0" + s;
    return s;
}