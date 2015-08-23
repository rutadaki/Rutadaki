var eventControllers = angular.module('eventControllers', []);

// Controller for the event list
eventControllers.controller('EventListCtrl',
	['$scope','$http', 'EventService', function($scope, $http, EventService) {

		// all events
		$scope.events = []
		// variables to manage pagination:
		$scope.filteredEvents = [], $scope.currentPage = 1,
				$scope.numPerPage = 10, $scope.maxSize = 5;
		//$scope.searchEvent = '';
		$scope.loading = true;
		$scope.loadingPercent = 0;

		// gets the number of pages needed
		$scope.numPages = function() {
			return Math.ceil($scope.events.length
					/ $scope.numPerPage);
		};
		
		$scope.getEventsToShow = function(){
			if($scope.searchEvent){
				return $scope.events;
			} else {
				return $scope.filteredEvents;
			}
				
		}
		
		// sets the events displayed when the users changes the current
		// page
		$scope.$watch('currentPage + numPerPage', function() {
			var begin = (($scope.currentPage - 1) * $scope.numPerPage), 
			end = begin + $scope.numPerPage;

			$scope.filteredEvents = $scope.events.slice(begin, end);
		});
		
		// removes an event 
    	$scope.deleteEvent = function(event){
//    		var eventToRemove = $scope.events[index]; 
//		    $scope.events.splice(index, 1);
		    
		    EventService.DeleteEvent(event)
		    .then(new function(data){
		    	
		    	var i = 0;
		    	for(i = $scope.events.length; i--;) {
		    		if($scope.events[i].id === event.id ) break;
		    	}
		    	
		    	console.log("index: "+i);
		    	
		    	$scope.events.splice(i, 1);
		    	
		    	// we have to do this here to update the pagination
				var begin = (($scope.currentPage - 1) * $scope.numPerPage), 
				end = begin + $scope.numPerPage;
				$scope.filteredEvents = $scope.events.slice(begin, end);
		    });
		};
		
		// Retrieve events from server
		$scope.loadData = function() {
			$scope.loadingPercent = 50;
			$http.get('services/events')
			.success(function(data) {
				$scope.events = data;
				
				// we have to do this here to prepare the pagination
				var begin = (($scope.currentPage - 1) * $scope.numPerPage), 
				end = begin + $scope.numPerPage;
				$scope.filteredEvents = $scope.events.slice(begin, end);
				
				// end of the retrieval
				$scope.loadingPercent = 100;
				$scope.loading = false;
			});
		};
		
		// load the events:
		$scope.loadData();
}]);

// COntroles the Edit/Create event view:
eventControllers.controller('EventDetailsCtrl', 
['$scope',
'$http',
'$routeParams',
'fileReader',
function($scope, $http, $routeParams, fileReader) {
	$scope.eventId = $routeParams.eventId;
	$scope.isNew = $scope.eventId == "new";
	$scope.openedS = false;
	$scope.openedE = false;
	$scope.editLoc = false;
	$scope.showModal = false;
	
	
	// get the event data from server if it is an existing one.
	// Otherwise: creates an empty, new event
	$scope.initEvent = function (eventId){
		if(eventId=="new"){
			$scope.event = {
					categories: [],
					location: {},
					startDate: new Date(),
					endDate: new Date(),
					timeTable: { entries:[]}
			};
			// return the promise of "get Categories". TODO make this
			// with proper promises
			return $http.get('services/categories');
		}else{
			return $http.get('services/events/' + $scope.eventId)
			.then(function(result) {
				$scope.event = result.data;
				
				if($scope.event.date){
					$scope.event.startDate = $scope.event.date;
					$scope.event.endDate = $scope.event.date;
					
					$scope.event.timeTable = 
						{id:null, version:null, lastUpdate:null, entries:[
                          {dayOfTheWeek:"EVERYDAY", 
                        	  startHour:$scope.event.startHour, 
                        	  endHour:$scope.event.endHour,}]};
				}
				
				// if the timetable is null, we create an empty one
				//if($scope.timeTable === null) $scope.timeTable = {id:null, version:null, lastUpdate:null, entries:[]};
				
				// Set the event image src in view
				if($scope.event.imageFileName!==null){
					$scope.imageSrc = "http://www.rutadaki.com/event_images/" + $scope.event.imageFileName;
				}
				return $http.get('services/categories');
			});
		}
	};
	
	$scope.initEvent($scope.eventId).then(function(result) {
		$scope.categories = result.data;
		
		//sort categories
		$scope.categories.sort(function(a, b){
		    if(a.name < b.name) return -1;
		    if(a.name > b.name) return 1;
		    return 0;
		});
		
		// set selected categories
		$scope.selectedCategories=[];
		 for(var i=0; i < $scope.event.categories.length; i++){
			 var category = $scope.event.categories[i];
			 $scope.selectedCategories.push(category.id);
		 }
		
		// TODO we have to make this properly with promises
		return $http.get('services/locations');
	}).then(function(result) {
		$scope.locations = result.data;
	});

	// Save the changes made. If the event is a new one, it will create it
	$scope.saveChanges = function() {
		
		// set event categories with the selected ones
		$scope.event.categories = $scope.getSelectedCategories();
		
		// set the image file name
		if($scope.uploadedFile !== undefined){
			$scope.event.imageFileName = $scope.uploadedFile.name;
		}
		
//		if($scope.timeTable.entries.length != 0) {
//			$scope.event.timeTable = null;
//			$scope.event.timeTable = $scope.timeTable;
//		}
		
		// If endDate = startDate: we have to make up a little the event
		// to make it 'punctual'
		if($scope.event.startDate != null
				&& $scope.event.endDate != null
				&& $scope.event.startDate.getTime() === $scope.event.endDate.getTime()){
		
			// set Event date
			$scope.event.date = $scope.event.startDate;
			
			delete $scope.event.startDate;
			delete $scope.event.endDate;
			
			// set start hour from the first entry (if any) in the timetable
			if($scope.event.timeTable && $scope.event.timeTable.entries.length > 0){
				var entry = $scope.event.timeTable.entries[0];
				$scope.event.startHour = entry.startHour;
				$scope.event.endHour = entry.endHour;
				
				delete $scope.event.timeTable;
			}
		}
		
		console.log($scope.event)
		
		
		
		// this API call updates/creates the event TODO: make separate calls?
		$http.put('services/events', $scope.event)
		.success(function(res) {
			if($scope.uploadedFile !== undefined){
				
//				if($scope.event.id) {
//					
//					$scope.uploadedFile.name = $scope.event.id +".jpg";
//					console.log($scope.uploadedFile);
					// upload the file
					uploadFile($scope.uploadedFile);
					$scope.uploadedFile = undefined;
				//}
			}
			
		}).error(function(err) {
			console.log(err.message);
		});
	};
	
	// "selectedCategories" is an ID's array, this function
	// will go through to create a Category objects list
	// with the selected ones:
	$scope.getSelectedCategories = function() {
	    var resultCat = [];
	    for(var i=0; i < $scope.categories.length; i++){
			var category = $scope.categories[i];
			var idx = $scope.selectedCategories.indexOf(category.id);
			
			// is  selected
		    if (idx > -1) resultCat.push(category); 
		 }
	    return resultCat;
	  };
	
	 // makes the "startDate" calendar show
	$scope.openStartDate = function($event) {

		$event.preventDefault();
		$event.stopPropagation();

		$scope.openedS = true;
	};
	
	// makes the "endDate" calendar show
	$scope.openEndDate = function($event) {

		$event.preventDefault();
		$event.stopPropagation();

		$scope.openedE = true;
	};
	
	// makes the choose Location modal show
	$scope.toggleLocationModal = function(){
		$scope.loadingLocations=true;
		$scope.showModal = !$scope.showModal;
		
		$http.get('services/locations')
		.then(function(result) {
			$scope.locations = result.data;
			$scope.loadingLocations=false;
		});
	}
	
	// allows Location edition, disabled for now
	$scope.editLocation = function(){
		$scope.editLoc = true;
	}
	
	// sets the event location with the selected one in the modal
	$scope.selectLocation = function(loc){
		$scope.event.location = loc;
		$scope.toggleLocationModal();
	}
	
	// An image has been dropped so we preview it:
	$scope.imageDropped = function() {
		// Get the file
		var file = $scope.uploadedFile;
		
		// Preview the file
		$scope.previewFile();
		
	};
	
	// Reads the dropped file and sets the Event Image src on gui
	$scope.previewFile = function () {
        $scope.progress = 0;
        fileReader.readAsDataUrl($scope.uploadedFile, $scope)
                      .then(function(result) {
                          $scope.imageSrc = result;
                      });
    };
    
    // Sets the upload progress bar
    $scope.$on("fileProgress", function(e, progress) {
        $scope.progress = 100 * progress.loaded / progress.total;
    });

}]);

// Uploads a file to the server
// TODO: add "upload url" as a new argument
// TODO: should this function be controllers private?
function uploadFile(file){
	
	formData = new FormData();
	
	formData.append('file', file);
	
	var xhr = new XMLHttpRequest();
    xhr.open('POST', 'http://www.rutadaki.com/UploadServlet');
    xhr.onload = function() {
      //progress.value = progress.innerHTML = 100;
    };
    
//      xhr.upload.onprogress = function (event) {
//        if (event.lengthComputable) {
//          var complete = (event.loaded / event.total * 100 | 0);
//          progress.value = progress.innerHTML = complete;
//        }
//      }
    xhr.send(formData);
}

