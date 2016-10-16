patientApp.controller("VisitsController", function($scope, $http, $modal,
		alertService) {
	
	$scope.curPage = 1;
	$scope.pageSize = 10;
	$scope.maxSize = 10;

	$scope.alerts = alertService.alerts;

	$scope.$on('$routeChangeSuccess', function() {
		$scope.getAllVisits();
	})

	$scope.getAllVisits = function() {
		$http.get("../calendar/getPatientsCalendar").success(function(result) {
			$scope.calendarList = result;
		})
	}

	$scope.cancelReservation = function(id) {
		$http.post("../calendar/cancel", id).success(function() {
			alertService.add("Visit was cancelled!", "success", 3000);
			$scope.getAllVisits();
		})
		.error(function(data, status, headers, config){
			alertService.add("Oops! Something went wrong!", "danger", 3000);
		});
	}

	$scope.open = function(id) {

		$scope.id = id;

		var modalInstance = $modal.open({
			templateUrl : 'description.html',
			controller : ModalInstanceCtrl,
			scope : $scope,
			resolve : {
				description : function() {
					return $scope.description;
				},
				id : function() {
					return $scope.id;
				}
			}
		});

		modalInstance.result.then(function(selectedItem) {
			$scope.selected = selectedItem;
		})
	}

	var ModalInstanceCtrl = function($scope, $modalInstance, description, id) {

		$scope.getByCalendar = function(id) {
			$http.get('../description/getByCalendar', {
				params : {
					calendarId : id
				}
			}).success(function(result) {
				$scope.description = result;
			})
		}

		$scope.getByCalendar(id);

		$scope.description = description;

		$scope.cancel = function() {
			$modalInstance.dismiss('cancel');
		};
	}
})