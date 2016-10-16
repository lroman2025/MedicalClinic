userApp.controller("ScheduleController", function($scope, $http, $location, $modal, $filter, alertService){
	
	$scope.alerts = alertService.alerts;
	
	$scope.$on('$routeChangeSuccess', function(){
		$scope.getAllSchedule();
	})

	$scope.getAllSchedule = function(){
		$http.get("../schedule/getLoggedDoctorsSchedule").success(function(result){
			$scope.scheduleList = result;
		});
	}
	
	$scope.deleteSchedule = function(idx){
		var modalHtml = '<div class="modal-body"><h3>Are you sure?</h3></div>';
	    modalHtml += '<div class="modal-footer"><button class="btn btn-primary" ng-click="ok()">Yes</button><button class="btn btn-warning" ng-click="cancel()">No</button></div>';
		
		var modalInstance = $modal.open({
			templateUrl : 'myModalContent.html',
			template: modalHtml,
			size: 'sm',
		    controller: ConfirmInstanceCtrl,
		})
		modalInstance.result.then(function() {
			reallyChangeStatus(idx);
		});
	}
	
	var reallyChangeStatus = function(idx){
		$http.post("../schedule/delete", idx).success(function(){
			$scope.getAllSchedule();
			alertService.add("Hour was deleted from scheduele", "success", 3000);
		})
		.error(function(){
			alertService.add("You cannot delete this hour from schedule." + 
					"There are patients registered for visit on this hour!", "danger", 8000);
		})
		
	}
	
	var ConfirmInstanceCtrl = function($scope, $modalInstance) {
		$scope.editMode = false;
		
		$scope.ok = function() {
			$modalInstance.close("ok");
		};
		
		$scope.cancel = function() {
		    $modalInstance.dismiss('cancel');
		 };
	}
	
	$scope.open = function(){
		var modalInstance = $modal.open({
			templateUrl : 'addHours.html',
			controller : ModalInstanceCtrl,
			scope : $scope,
			windowClass : 'app-modal-window',
			resolve : {
				scheduleList : function(){
					return $scope.scheduleList;
				},
				newSchedule : function(){
					return $scope.newSchedule;
				},
				getAllSchedule : function(){
					return $scope.getAllSchedule();
				}
			}
		});
		
		modalInstance.result.then(function(){
			$scope.getAllSchedule();
		})
	}
	
	var ModalInstanceCtrl = function($modalInstance, $scope, scheduleList, newSchedule, getAllSchedule){
		$scope.newSchedule = newSchedule;

		$scope.add = function(newSchedule){
			$scope.day = $filter('date')(newSchedule.day, "MM/dd/yyyy");
			$scope.hourStart = new Date($scope.day + ', ' + newSchedule.hourStart);
			$scope.hourEnd = new Date($scope.day + ', ' + newSchedule.hourEnd);
			
			
			$scope.date = $filter('date')(newSchedule.day, "yyyy-MM-dd HH:mm:ss");
			$scope.hourStart = $filter('date')($scope.hourStart, "yyyy-MM-dd HH:mm:ss");
			$scope.hourEnd = $filter('date')($scope.hourEnd, "yyyy-MM-dd HH:mm:ss");
			
			
			var map = {'day' : $scope.date, 'hourStart' : $scope.hourStart, 'hourStop' : $scope.hourEnd};
			$http.post("../schedule/add", map).success(function(){
				$scope.getAllSchedule();
				alertService.add("Hours were added to schedule!", "success", 3000);
			})
			.error(function(data, status, headers, config){
				alertService.add("Oops! Something went wrong!", "danger", 3000);
			});
			$scope.cancel();
		}
		
		$scope.cancel = function(){
			$modalInstance.dismiss("cancel");
		}
		$scope.opened = false;
		
		$scope.openCalendar = function($event){
			$event.preventDefault();
		    $event.stopPropagation();
		    $scope.openedStart = true;  
		}
		
		$scope.today = function() {
			$scope.dt = new Date();
		}
		
		$scope.today();
		
		$scope.clear = function () {
		    $scope.dt = null;
		}
			
		$scope.toggleMin = function() {
		    $scope.minDate = $scope.minDate ? null : new Date();
		}
		
		$scope.toggleMin();
		
		$scope.dateOptions = {
				formatYear: 'yy',
			    startingDay: 1
		}
		
		$scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
		$scope.format = $scope.formats[0];
		
		$scope.cancel = function() {
		    $modalInstance.dismiss('cancel');
		};
	}
});