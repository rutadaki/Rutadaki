var fessmodule = angular.module('myModule', []);

fessmodule
		.controller(
				'ctrlRead',
				function($scope) {

					$scope.gap = 5;

					$scope.groupedItems = [];
					$scope.itemsPerPage = 5;
					$scope.pagedItems = [];
					$scope.currentPage = 0;
					
					$scope.items=[];
					
					// calculate page in place
					$scope.groupToPages = function() {
						$scope.pagedItems = [];

						for (var i = 0; i < $scope.items.length; i++) {
							if (i % $scope.itemsPerPage === 0) {
								$scope.pagedItems[Math.floor(i
										/ $scope.itemsPerPage)] = [ $scope.items[i] ];
							} else {
								$scope.pagedItems[Math.floor(i
										/ $scope.itemsPerPage)]
										.push($scope.items[i]);
							}
						}
					};

					$scope.range = function(size, start, end) {
						var ret = [];
						console.log(size, start, end);

						if (size < end) {
							end = size;
							start = size - $scope.gap;
						}
						for (var i = start; i < end; i++) {
							ret.push(i);
						}
						console.log(ret);
						return ret;
					};
					
					$scope.setItems = function(itemList){
						console.log("setting items");
						$scope.items = itemList;
					}

					$scope.prevPage = function() {
						if ($scope.currentPage > 0) {
							$scope.currentPage--;
						}
					};

					$scope.nextPage = function() {
						if ($scope.currentPage < $scope.pagedItems.length - 1) {
							$scope.currentPage++;
						}
					};

					$scope.setPage = function() {
						$scope.currentPage = this.n;
					};

					// functions have been describe process the data for display
					// $scope.search();
					$scope.currentPage = 0;
					// now group by pages
					$scope.groupToPages();

				});
