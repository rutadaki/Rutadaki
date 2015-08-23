var categoryServices = angular.module('CategoryServices', []);

categoryServices.factory('CategoryService',
    ['$http',
    function ($http) {
        var service = {};

        service.GetCategories = function () {
        	//var deferred = $q.defer();
        	
        	$http.get('services/categories')
        	.success(function(data){
        		
        		// sort the categories by name:
        		data.sort(function(a, b){
        		    if(a.name < b.name) return -1;
        		    if(a.name > b.name) return 1;
        		    return 0;
        		});
        		return data;
        	});
//        	.error(function(msg, code){
//        		
//        	});
        };
 
        return service;
    }]);