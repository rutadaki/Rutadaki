var outstandingControllers = angular.module('outstandingControllers', []);

outstandingControllers.controller('OutstandingListCtrl',
	['$scope', '$http', function($scope, $http) {

		$scope.outstandingEvents = []
		$scope.events = [];
		$scope.filteredEvents = [];
		$scope.searchEvent = '';
		$scope.loading = true;
		$scope.loadingPercent = 0;
		$scope.showModal = false;

		$scope.loadData = function() {
			$scope.loadingPercent = 50;
			console.log($scope.loadingPercent);
			$http.get('services/outstanding')
			.success(function(data) {
				$scope.outstandingEvents = data;
				$scope.loadingPercent = 100;
				$scope.loading = false;
			});
		};
		
		$scope.toggleLocationModal = function(){
			if(!$scope.showModal){
				$scope.loadingEvents = true;
				$scope.showModal = !$scope.showModal;
				$http.get('services/events')
				.success(function(data) {
					$scope.events = data;
					$scope.loadingEvents = false;
				});
			}else{
				$scope.showModal = !$scope.showModal;
			}
		}
		
		$scope.selectEvent = function(event){
			$http.post('services/outstanding/'+event.id)
			.success(function(data) {
				$scope.loadData();
				$scope.toggleLocationModal();
			});
		}
		
		$scope.removeEvent = function(event){
			$http.delete('services/outstanding/'+event.id)
			.success(function(data) {
				//$scope.outstandingEvents = data;
				$scope.loadData();
			});
		}

		$scope.loadData();
}]);


