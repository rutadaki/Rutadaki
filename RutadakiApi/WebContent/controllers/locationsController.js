var locationControllers = angular.module('locationControllers', ['ui.bootstrap']);

locationControllers.controller('LocationListCtrl', [ '$scope', '$http', 'LocationService',
		function($scope, $http, LocationService) {
	$scope.loading = true;
			 $scope.locations=[]
			 $scope.filteredLocations=[]
			  ,$scope.currentPage = 1
			  ,$scope.numPerPage = 10
			  ,$scope.maxSize = 5;
			 
			 
	
			$scope.numPages = function () {
			    return Math.ceil($scope.locations.length / $scope.numPerPage);
			  };
			  
			  $scope.$watch('currentPage + numPerPage', function() {
			    var begin = (($scope.currentPage - 1) * $scope.numPerPage)
			    , end = begin + $scope.numPerPage;
			    
			    $scope.filteredLocations = $scope.locations.slice(begin, end);
			  });
			  
			  
			  $scope.loadData = function (){
				  $http.get('services/locations').success(function(data) {
						$scope.locations = data;
						 var begin = (($scope.currentPage - 1) * $scope.numPerPage)
						    , end = begin + $scope.numPerPage;
						    
						    $scope.filteredLocations = $scope.locations.slice(begin, end);
						    $scope.loading=false;
					});
			  };
			  
			  $scope.getLocationsToShow = function(){
					if($scope.searchLocation){
						return $scope.locations;
					} else {
						return $scope.filteredLocations;
					}
						
				}
			  
			// removes an event 
		    	$scope.deleteLocation = function(location){
//		    		var eventToRemove = $scope.events[index]; 
//				    $scope.events.splice(index, 1);
				    
				    LocationService.DeleteLocation(location)
				    .then(new function(data){
				    	
				    	var i = 0;
				    	for(i = $scope.locations.length; i--;) {
				    		if($scope.locations[i].id === location.id ) break;
				    	}
				    	
				    	console.log("index: "+i);
				    	
				    	$scope.locations.splice(i, 1);
				    	
				    	// we have to do this here to update the pagination
						var begin = (($scope.currentPage - 1) * $scope.numPerPage), 
						end = begin + $scope.numPerPage;
						$scope.filteredLocations = $scope.locations.slice(begin, end);
				    });
				};
			  
			  $scope.loadData();
			  
			
		} ]);

locationControllers.controller('LocationDetailsCtrl', [ '$scope', '$http',
		'$routeParams', function($scope, $http, $routeParams) {
			$scope.locationId = $routeParams.locId;
			$scope.location = {};
			console.log("Edit Location id: "+$routeParams.locId);
			$scope.selectedCity={};
			$scope.isNew = false;
			
			if($scope.locationId === 'new'){
				loadCities();
			} else {
				$http.get('services/locations/'+$scope.locationId)
				.then(function(result) {
					$scope.location = result.data;
					loadCities();
				});
			}
			
			$scope.saveChanges = function(){
				$scope.location.city = $scope.selectedCity;
				
				$http.put('services/locations', $scope.location).success(function(res){
					
				});
			}
			
			function loadCities() {
				$http.get('services/locations/cities').then(function(result) {
					$scope.cities = result.data;
					
					// If it is not a new location, we have to set the selectedCity Value
					// with the city of the editing location:
					if($scope.locationId != "new" ){
						for (var i = 0; i < $scope.cities.length; i++) {
							var city = $scope.cities[i];
							if (city.id == $scope.location.city.id)
								$scope.selectedCity = city;
						}
						
					}
				});
			};
			
		}]);

