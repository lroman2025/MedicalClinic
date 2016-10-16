userApp.controller("CalendarController", function($scope, $http, $location, $modal, alertService){

	$scope.alerts = alertService.alerts;
	
	$scope.$on('$routeChangeSuccess', function(){
		$scope.getAllCalendar();
	})
	
	$scope.getAllCalendar = function(){
		$http.get("../calendar/getDoctorsCalendar").success(function(result){
			$scope.calendarList = result;
		})
	}
	
	$scope.open = function(id){
		
		$scope.id = id;
	
		var modalInstance = $modal.open({
			templateUrl: 'description.html',
			controller : ModalInstanceCtrl,
			scope : $scope,
			resolve: {
				description : function(){
					return $scope.description;
				},
				id : function(){
					return $scope.id;
				},
				doctorComment : function(){
					return $scope.doctorComment;
				}
			}	
		});	
		
		modalInstance.result.then(function(selectedItem){
			$scope.selected = selectedItem;
		})
	}
	
	var ModalInstanceCtrl = function($scope, $modalInstance, description, id, doctorComment){
		
		$scope.getByCalendar = function(id){
			$http.get('../description/getByCalendar', {params: {calendarId : id}}).success(function(result){
				$scope.description = result;
			})
		}
		
		$scope.getByCalendar(id);
		
		$scope.description = description;
		
		$scope.update = function(doctorComment){
			var map = {'comment': doctorComment, 'id' : id};
			$http.post("../description/update", map).success(function(){
				alertService.add("Comment was added!", "success", 3000)
			})
			.error(function(data, status, headers, config){
				alertService.add("Oops! Something went wrong!", "danger", 3000);
			});
			$scope.getByCalendar(id);
			$scope.getByCalendar(id);
			
			$scope.cancel();
		}
			
		$scope.cancel = function() {
		    $modalInstance.dismiss('cancel');
		};
	}
});