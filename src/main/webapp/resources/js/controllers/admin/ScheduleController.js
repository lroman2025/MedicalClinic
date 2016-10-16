adminApp.controller("ScheduleController", function($scope, $http, $location, $modal, $filter, alertService){
	
	$scope.alerts = alertService.alerts;
	
	$scope.isCollapsed = false;
	
	$scope.display = false;
	
	$scope.test = [];
	
	$scope.patientList = [];
	
	$scope.param = "col-lg-offset-3";
	
	$scope.$on('$routeChangeSuccess', function(){
		$scope.getAllDoctors();
		$scope.getAllPatients();
	})
		
	$scope.patient;
	
	$scope.getDoctorsSchedule = function(d){
		$http.post("../schedule/getDoctorsSchedule", d).success(function(result){
			$scope.scheduleList = result;
			$scope.firstname = d.userData.firstname;
			$scope.lastname = d.userData.lastname;
			$scope.display = true;
			$scope.param = "col-lg-5";
		});
	}
	
	$scope.getCalendarByDoctor = function(pesel){
		$http.get("../calendar/getCalendarByDoctor", pesel).success(function(result){
			$scope.calendar = result;
		})
	}
	
	$scope.getAllDoctors = function(){
		$http.get("../doctorData/getAll").success(function(data){
			$scope.doctorList = data;
		})
	}
	
	$scope.getAllPatients = function(){
		$http.get("../patientData/getAll").success(function(data){
			$scope.patientList = data;
		})
	}
	
	$scope.doctorList;
	
	$scope.showHours = function(schedule){
		$scope.schedule = schedule;
		
		var modalInstance = $modal.open({
			templateUrl : 'hours.html',
			controller : ModalInstanceCtrl,
			resolve : {
				schedule : function(){
					return $scope.schedule;	
				},
				patient : function(){
					return $scope.patient;
				}
			}
		});
	}
	
	var ModalInstanceCtrl = function($scope, $modalInstance, schedule, patient){
		
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
			var map = {"date" : $scope.date, "schedule" : $scope.schedule.id, "patient" : patient.id};
			$http.post("../calendar/setCalendarByAdmin", map)
				.success(function(){
					alertService.add("Patient was register to visit doctor!", "success", 3000);
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
});