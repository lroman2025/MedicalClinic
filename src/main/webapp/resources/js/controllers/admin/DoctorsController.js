adminApp.controller("DoctorsController", function($scope, $http, $location,
		$modal, $filter, alertService, $timeout) {

	$scope.curPage = 1;
	$scope.pageSize = 10;
	$scope.maxSize = 10;
	
	$scope.alerts = alertService.alerts;

	$scope.$on('$routeChangeSuccess', function() {
		$scope.getAllDoctors();
	})

	$scope.getAllDoctors = function() {
		$http.get("../doctorData/getAll").success(function(result) {
			$scope.doctorList = result;
		})
	};

	$scope.open = function() {
		var modalInstance = $modal.open({
			templateUrl : 'addDoctor.html',
			controller : DoctorModalController
		});
		modalInstance.result.then(function(newDoctor) {
			$scope.addDoctor(newDoctor);
		});
	};

	var DoctorModalController = function($scope, $modalInstance) {
		$scope.newDoctor = {};

		$scope.cancel = function() {
			$modalInstance.dismiss('cancel');
		};

		$scope.ok = function() {
			$modalInstance.close($scope.newDoctor);

		};
	};

	$scope.addDoctor = function(newDoctor) {
		$http.post('../registerDoctor', newDoctor).success(function(data) {
			alertService.add("Doctor was added", 'success', 3000);
			$scope.getAllDoctors();
		}).error(function(data, status, headers, config) {
			if (status == 409) {
				alertService.add(data, "danger", 3000);
			} else {
				alertService.add("Oops! Something went wrong!", "danger", 3000);
			}
		});
	};

	$scope.schedule = function(d) {
		$scope.d = d;

		var modalInstance = $modal.open({
			templateUrl : 'addSchedule.html',
			controller : ScheduleModalController,
			scope : $scope,
			resolve : {
				newSchedule : function() {
					return $scope.newSchedule;
				},
				d : function() {
					return $scope.d;
				}
			}

		});
		modalInstance.result.then(function(newSchedule) {
			$scope.addSchedule(newSchedule);
		});
	};

	var ScheduleModalController = function($modalInstance, $scope, newSchedule,
			d) {

		$scope.newSchedule = newSchedule;

		$scope.cancel = function() {
			$modalInstance.dismiss('cancel');
		};

		$scope.add = function(newSchedule) {
			$scope.day = $filter('date')(newSchedule.day, "MM/dd/yyyy");
			$scope.hourStart = new Date($scope.day + ', '
					+ newSchedule.hourStart);
			$scope.hourEnd = new Date($scope.day + ', ' + newSchedule.hourEnd);

			$scope.date = $filter('date')(newSchedule.day,
					"yyyy-MM-dd HH:mm:ss");
			$scope.hourStart = $filter('date')($scope.hourStart,
					"yyyy-MM-dd HH:mm:ss");
			$scope.hourEnd = $filter('date')($scope.hourEnd,
					"yyyy-MM-dd HH:mm:ss");

			var map = {
				'day' : $scope.date,
				'hourStart' : $scope.hourStart,
				'hourStop' : $scope.hourEnd,
				'doctorId' : d,
			};
			$http.post("../schedule/addByDoctorId", map).success(function() {
				$scope.getAllDoctors();
				alertService.add("Schedule was added", 'success', 3000);
			}).error(function(data, status, headers, config) {
				alertService.add("Oops! Something went wrong!", "danger", 3000);
			});

			$scope.cancel();
		};

		$scope.opened = false;

		$scope.openCalendar = function($event) {
			$event.preventDefault();
			$event.stopPropagation();
			$scope.openedStart = true;
		};

		$scope.today = function() {
			$scope.dt = new Date();
		};

		$scope.today();

		$scope.clear = function() {
			$scope.dt = null;
		};

		$scope.toggleMin = function() {
			$scope.minDate = $scope.minDate ? null : new Date();
		};

		$scope.toggleMin();

		$scope.dateOptions = {
			formatYear : 'yy',
			startingDay : 1
		};
	};

	$scope.editDoctor = function(item) {
		$http.post('../userData/update', item).success(function(result) {
			$scope.getAllDoctors();
			alertService.add("Data was updated!", "success", 3000);
		}).error(function(data, status, headers, config) {
			alertService.handleErrors(status);
		});
	};

	$scope.checkFirstname = function(data) {
		if (data == '' || data == null) {
			return "Firstname connot be empty";
		}
	};

	$scope.checkLastname = function(data) {
		if (data == '' || data == null) {
			return "Lastname connot be empty";
		}
	};

	$scope.checkAddress = function(data) {
		if (data == '' || data == null) {
			return "Address connot be empty";
		}
	};

	$scope.checkPhone = function(data) {
		if (data == '' || data == null) {
			return "Phone connot be empty";
		}
		;
	};
});