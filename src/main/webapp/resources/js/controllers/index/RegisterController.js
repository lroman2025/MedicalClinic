indexApp.controller('RegisterController', function($scope, $modal, $http, alertService) {

	$scope.alerts = alertService.alerts;
	
	$scope.open = function() {
		var modalInstance = $modal.open({
			templateUrl : 'registerModalContent.html',
			controller : RegisterModalController
		});
		modalInstance.result.then(function(newUser) {
			$scope.register(newUser);
		});
	};

	var RegisterModalController = function($scope, $modalInstance) {
		$scope.newUser = {};
		
		$scope.cancel = function() {
			$modalInstance.dismiss('cancel');
		};

		$scope.ok = function() {
			$modalInstance.close($scope.newUser);
		};
	};

	$scope.register = function(newUser) {
		$http.post('registerPatient', newUser).success(function(data) {
			alertService.add("You was registered", "success", 3000);
		}).error(function(data, status, headers, config) {
			if (status == 409) {
				alertService.add(data, "danger", 3000);
			} else {
				alertService.add("Oops! Something went wrong!", "danger", 3000);
			}
			
		});
	}
	
	$scope.getUrlValue = function(varSearch) {
		var searchString = window.location.search.substring(1);
		var variableArray = searchString.split('&');

		for (var i = 0; i < variableArray.length; i++) {
			var keyValuePair = variableArray[i].split('=');
			if (keyValuePair[0] == varSearch) {
				return keyValuePair[1];
			};
		};
	};
	
	$scope.checkIfError = function() {
		$scope.error = $scope.getUrlValue("error");

		if ($scope.error != '' && $scope.error != undefined
				&& $scope.error == "true") {
			$http.get('lastSecurityException').success(function(data){
				alertService.add("ERROR: Invalid pesel or password", 'danger', 3000);
			});
			$scope.forgotPassword = true;
		};
	};

	$scope.checkIfError();
})