var locationServices = angular.module('LocationServices', []);

locationServices.factory('LocationService',
    ['$http',
    function ($http) {
        var service = {};

        service.GetAllLocations = function () {
        	return $http.get('services/locations');
        };
        
        service.DeleteLocation = function (location) {
        	console.log(location);
        	return $http.delete('services/locations/'+location.id);
        };
        
        service.GetLocation = function (locId) {
        	return $http.get('services/locations/'+locId);
        };
        
        service.LocationEvent = function (location) {
        	return $http.put('locations/events',locations);
        };
 
        return service;
    }]);