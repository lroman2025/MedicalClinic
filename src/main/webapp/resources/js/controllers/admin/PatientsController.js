adminApp.controller("PatientsController", function($scope, $http, $location,
		$modal, alertService) {
	
	$scope.curPage = 1;
	$scope.pageSize = 10;
	$scope.maxSize = 10;
	
	$scope.alerts = alertService.alerts;
	
	$scope.$on('$routeChangeSuccess', function() {
		$scope.getAllPatients();
	})

	$scope.getAllPatients = function() {
		$http.get("../patientData/getAll").success(function(result) {
			$scope.patientList = result;
		})
	}

	$scope.open = function() {
		var modalInstance = $modal.open({
			templateUrl : 'addPatient.html',
			controller : ModalController
		});
		modalInstance.result.then(function(newPatient) {
			$scope.add(newPatient);
		});
	};

	var ModalController = function($scope, $modalInstance) {
		$scope.newPatient = {};

		$scope.cancel = function() {
			$modalInstance.dismiss('cancel');
		};

		$scope.ok = function() {
			$modalInstance.close($scope.newPatient);

		};
	};

	$scope.add = function(newPatient) {
		$http.post('../registerPatient', newPatient).success(function(data) {
			$scope.getAllPatients();
			alertService.add("Patient was added!", "success", 3000);
		}).error(function(data, status, headers, config) {
			alertService.add("Oops! Something went wrong!", "danger", 3000);
		});
	}
	
	$scope.editPatient = function(item) {
		$http.post('../userData/update', item).success(function(result) {
			$scope.getAllPatients();
			alertService.add("Data was updated!", "success", 3000);
		}).error(function(data, status, headers, config) {
			if (status == 409) {
				alertService.add(data, "danger", 3000);
			} else {
				alertService.add("Oops! Something went wrong!", "danger", 3000);
			}
		});
	};
	
	$scope.checkFirstname = function(data){
		if(data == '' || data == null){
			return "Firstname connot be empty";
		}
	};
	
	$scope.checkLastname = function(data){
		if(data == '' || data == null){
			return "Lastname connot be empty";
		}
	};
	
	$scope.checkAddress = function(data){
		if(data == '' || data == null){
			return "Address connot be empty";
		}
	};
	
	$scope.checkPhone = function(data){
		if(data == '' || data == null){
			return "Phone connot be empty";
		}
	};

});