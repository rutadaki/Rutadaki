var eventServices = angular.module('EventServices', []);

eventServices.factory('EventService',
    ['$http',
    function ($http) {
        var service = {};

        service.GetAllEvents = function () {
        	return $http.get('services/events');
        };
        
        service.DeleteEvent = function (event) {
        	console.log("Deleting event"+event.title)
        	return $http.delete('services/events/'+event.id);
        };
        
        service.GetEvent = function (eventId) {
        	return $http.get('services/events/'+eventId);
        };
        
        service.SaveEvent = function (event) {
        	return $http.put('services/events',event);
        };
 
        return service;
    }]);