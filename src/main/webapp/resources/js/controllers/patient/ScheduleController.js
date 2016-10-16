patientApp.controller("ScheduleController", function($scope, $http, $modal, $filter, $location, $q, alertService){
	
	$scope.alerts = alertService.alerts;
	
	$scope.isCollapsed = false;
	
	$scope.display = false;
	
	$scope.param = "col-lg-offset-3";
	
	$scope.$on('$routeChangeSuccess', function(){
		$scope.getAllDoctors();
	})
	
	$scope.getDoctorsSchedule = function(d){
		$http.post("../schedule/getDoctorsSchedule", d).success(function(result){
			$scope.scheduleList = result;
			$scope.firstname = d.userData.firstname;
			$scope.lastname = d.userData.lastname;
			$scope.display = true;
			$scope.param = "col-lg-5";
		});
	}
	
	$scope.getAllDoctors = function(){
		$http.get("../doctorData/getAll").success(function(data){
			$scope.doctorList = data;
		})
	}
	
	$scope.showHours = function(schedule){
		$scope.schedule = schedule;
		
		$localStorage.schedule = schedule;
		
		var modalInstance = $modal.open({
			templateUrl : 'hours.html',
			controller : ModalInstanceCtrl,
			resolve : {
				schedule : function(){
					return $scope.schedule;	
				}
			}
		});
	}
	
	var ModalInstanceCtrl = function($scope, $modalInstance, schedule){
		
		$scope.schedule = schedule;
		
		$scope.day = $filter('date')(schedule.day, "MM/dd/yyyy");

		$scope.start = new Date($scope.day + ', ' + schedule.hourStart);
		$scope.end = new Date($scope.day + ', ' + schedule.hourEnd);
	
		$scope.start = new Date().setTime($scope.start);
		$scope.end = new Date().setTime($scope.end);
		
		$scope.hourList = [];
		
		for(var j = 1; j<= ($scope.end - $scope.start)/900000; j++){
			$scope.hourList.push({id: j, begin: $scope.start+900000*(j-1), finish: $scope.start+900000*(j), available: true});
		}
	
		$scope.addToCalendar = function(hourList){
			$scope.date = $filter('date')(hourList.begin, "yyyy-MM-dd HH:mm:ss");
			var map = {"date" : $scope.date, "schedule" : $scope.schedule.id};
			$http.post("../calendar/setCalendar", map).success(function(){
				alertService.add("You was registered to visit doctor!", "success", 3000);
			})
			.error(function(data, status, headers, config){
				alertService.add("Oops! Something went wrong!", "danger", 3000);
			});
			$modalInstance.close();
		}
	
		$scope.cancel = function() {
			$modalInstance.dismiss('cancel');
		};
	}
})