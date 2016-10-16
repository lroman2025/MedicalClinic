var userApp = angular.module("userApp", [ 'ngRoute', 'ngDragDrop', 'xeditable',
		'ui.bootstrap', 'ngAnimate', 'ng-context-menu' ]);

userApp.factory('alertService', [ '$timeout', '$filter',
		function($timeout, $filter) {
			var alerts = [];

			var closeAlert = function(index) {
				alerts.splice(index, 1);
			};

			return {
				add : function(msg, type, timeout) {
					var alert = {
						msg : msg,
						type : type
					};
					alerts.push(alert);

					if (timeout) {
						$timeout(function() {
							closeAlert(alerts.indexOf(alert));
						}, timeout);
					}
				},

				handleErrors : function(data) {
					if (data.status == 500) {
						this.add("Oops! Something went wrong!", 'danger', 3000);
					} else if (data.status == 901) {
						this.add("Session expired. Please refresh page and log in again.", 'danger', 3000);
					} else {
						this.add(data.response, 'danger');
					}
				},
				closeAlert : closeAlert,
				alerts : alerts
			};
		} ]);

userApp.config(function($routeProvider) {

	$routeProvider.when('/', {
		controller : 'UserDashboardController',
		templateUrl : 'dashboard'
	});

	$routeProvider.when('/schedule', {
		controller : 'ScheduleController',
		templateUrl : 'schedule'
	});

	$routeProvider.when('/calendar', {
		controller : 'CalendarController',
		templateUrl : 'calendar'
	});

}).run(function(editableOptions) {
	editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2',
									// 'default'
});