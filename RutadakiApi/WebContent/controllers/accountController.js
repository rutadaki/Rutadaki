var accountControllers = angular.module('accountControllers', []);

accountControllers.controller('LoginCtrl',
	    ['$scope', '$rootScope', '$location', 'AuthenticationService',
	     function ($scope, $rootScope, $location, AuthenticationService) {
	         // reset login status
	         AuthenticationService.ClearCredentials();
	
	         $scope.dologin = function () {
	        	 console.log("login");
	        	 
	             $scope.dataLoading = true;
	             AuthenticationService.Login($scope.username, $scope.password, function(response) {
	                 
	            	 
	            	 if(response.success) {
	                	 console.log(response);
	                	 
	                     AuthenticationService.SetCredentials($scope.username, response.auth_token);
	                     $location.path('/events');
	                     $scope.dataLoading = false;
	                 } else {
	                     $scope.error = response.message;
	                     $scope.dataLoading = false;
	                 }
	             });
	         };
	     }]);