var authenticationServices = angular.module('Authentication', []);

authenticationServices.factory('AuthenticationService',
    ['$http', '$cookieStore', '$rootScope', '$timeout',
    function ($http, $cookieStore, $rootScope, $timeout) {
        var service = {};

        service.Login = function (username, password, callback) {

            /* Dummy authentication for testing, uses $timeout to simulate api call
             ----------------------------------------------*/
//            $timeout(function(){
//                var response = { 
//                		success: username === 'test' && password === 'test',
//                		auth_token: "token"
//                			};
//                if(!response.success) {
//                    response.message = 'Username or password is incorrect';
//                }
//                callback(response);
//            }, 1000);


            /* Use this for real authentication
             ----------------------------------------------*/
            $http.post('services/auth/login', { username: username, password: password })
                .success(function (response) {
                    callback(response);
                })
                .error(function (response) {
                    callback(response);
                });

        };
 
        service.SetCredentials = function (username, token) {
            //
        	var authdata = username + ':' + token; //TODO token from login
            $rootScope.globals = {
                currentUser: {
                    username: username,
                    token: token
                }
            };
 
            $http.defaults.headers.common['auth_token'] = token; // TODO token here
            $cookieStore.put('globals', $rootScope.globals);
        };
 
        service.ClearCredentials = function () {
            $rootScope.globals = {};
            $cookieStore.remove('globals');
            $http.defaults.headers.common['auth_token'] = "";
        };

//        service.token = "";
//        
//        service.isAnonymous = service.token === "";
        return service;
    }]);