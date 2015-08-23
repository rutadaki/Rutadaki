var categorySelControllers = angular.module('categorySelectorControllers', []);

categorySelControllers.controller('categorySelectorCtrl', ['$scope', function($scope) {
  
  // toggle selection for a given fruit by name
  $scope.toggleSelection = function (category) {
    var idx = $scope.selectedCategories.indexOf(category.id);

    // is currently selected
    if (idx > -1) {
      $scope.selectedCategories.splice(idx, 1);
    }
    // is newly selected
    else {
      $scope.selectedCategories.push(category.id);
    }
  };
  
}]);