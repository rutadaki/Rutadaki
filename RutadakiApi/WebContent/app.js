
// declare modules
//angular.module('Authentication', []);

var app = angular.module('MainApp', 
		[ 'ngRoute',
		  'ngCookies',
		  
		  'Authentication',
		  'EventServices',
		  'LocationServices',
		  
		  'eventControllers',
		  'locationControllers',
		  'outstandingControllers',
		  'categorySelectorControllers',
		  'accountControllers',
		  'ui.bootstrap',
		  'ui.imagedrop',
		  'ui.timetable',
		  'ui.modal']
);

app.config([ '$routeProvider', function($routeProvider) {
	$routeProvider
	.when('/login', {
		templateUrl : 'views/login.html',
		controller : 'LoginCtrl',
		controllerAs : 'login'
	})
	.when('/events', {
		templateUrl : 'views/eventsList.html',
		controller : 'EventListCtrl',
		controllerAs : 'events'
	})
	.when('/events/:eventId', {
		templateUrl : 'views/eventDetails.html',
		controller : 'EventDetailsCtrl',
		controllerAs : 'eventDetails'
	})
	.when('/locations', {
		templateUrl : 'views/locationsList.html',
		controller : 'LocationListCtrl',
		controllerAs : 'events'
	})
	.when('/locations/:locId', {
		templateUrl : 'views/locationDetails.html',
		controller : 'LocationDetailsCtrl',
		controllerAs : 'locationDetails'
	})
	.when('/outstanding', {
		templateUrl : 'views/outstandingList.html',
		controller : 'OutstandingListCtrl',
		controllerAs : 'outstanding'
	})
	.otherwise({
		redirectTo : '/login'
	});
} ]);

app.factory('sessionInjector', ['$cookieStore', '$rootScope', function($cookieStore,$rootScope) {  
    var sessionInjector = {
        request: function(config) {
//            if (!AuthenticationService.isAnonymus) {
//                config.headers['auth_token'] = AuthenticationService.token;
//            }
        	
        	var globals = $cookieStore.get("globals");
        	
        	if( globals && globals.currentUser){
	        	var token = globals.currentUser.token;
	        	config.headers['auth_token'] = token;
	        	
        	}
        	config.headers['service_key'] = 'f80ebc87-ad5c-4b29-9366-5359768df5a1';
        	//console.log(config);
            return config;
        }
    };
    return sessionInjector;
}]);

app.config(['$httpProvider', function($httpProvider) {  
    $httpProvider.interceptors.push('sessionInjector');
}]);

// Initialize Code
app.run(['$rootScope', function($rootScope) {
	 $rootScope.globals = {};

	}]);

(function (module) {
    
    var fileReader = function ($q, $log) {
 
        var onLoad = function(reader, deferred, scope) {
            return function () {
                scope.$apply(function () {
                    deferred.resolve(reader.result);
                });
            };
        };
 
        var onError = function (reader, deferred, scope) {
            return function () {
                scope.$apply(function () {
                    deferred.reject(reader.result);
                });
            };
        };
 
        var onProgress = function(reader, scope) {
            return function (event) {
                scope.$broadcast("fileProgress",
                    {
                        total: event.total,
                        loaded: event.loaded
                    });
            };
        };
 
        var getReader = function(deferred, scope) {
            var reader = new FileReader();
            reader.onload = onLoad(reader, deferred, scope);
            reader.onerror = onError(reader, deferred, scope);
            reader.onprogress = onProgress(reader, scope);
            return reader;
        };
 
        var readAsDataURL = function (file, scope) {
            var deferred = $q.defer();
             
            var reader = getReader(deferred, scope);         
            reader.readAsDataURL(file);
             
            return deferred.promise;
        };
 
        return {
            readAsDataUrl: readAsDataURL  
        };
    };
 
    module.factory("fileReader",
                   ["$q", "$log", fileReader]);
 
}(app));

app.directive("ngFileSelect", function(){

	  return {
	    link: function($scope,el){
	      
	      el.bind("change", function(e){
	      
	        $scope.uploadedFile = (e.srcElement || e.target).files[0];
	        $scope.previewFile();
	      })
	      
	    }
	    
	  }	  
})