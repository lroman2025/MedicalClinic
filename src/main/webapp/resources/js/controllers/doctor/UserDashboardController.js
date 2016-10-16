userApp.controller('UserDashboardController', function($scope, $http, $modal,
		$filter, alertService) {
	$scope.alerts = alertService.alerts;

	$scope.dataList = [];

	$scope.$on('$routeChangeSuccess', function() {
		$scope.getUserData();
	})

	$scope.getUserData = function() {
		$http.get("../doctorData/getUserData").success(function(result) {
			$scope.dataList = result;
		})
	};

	$scope.update = function() {
		$http.post("../userData/update", $scope.dataList.userData)
				.success(
						function() {
							$scope.getUserData();
							alertService.add("Your data was updated!",
									"success", 3000);
						}).error(
						function(data, status, headers, config) {
							alertService.add("Oops! Something went wrong!",
									"danger", 3000);
						})
	}
});