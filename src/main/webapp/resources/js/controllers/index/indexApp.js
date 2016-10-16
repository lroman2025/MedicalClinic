var indexApp = angular.module("indexApp", [ 'ngRoute', 'ngDragDrop',
		'xeditable', 'ui.bootstrap', 'ngAnimate', 'ng-context-menu' ]);

indexApp.factory(
				'alertService',
				[
						'$timeout',
						'$filter',
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
										this.add("Oops! Something went wrong!",
												'danger', 3000);
									} else if (data.status == 901) {
										this
												.add(
														"Session expired. Please refresh page and log in again.",
														'danger');
									} else {
										this.add(data.response, 'danger');
									}
								},
								closeAlert : closeAlert,
								alerts : alerts
							};
						} ]);

indexApp.config(function($routeProvider) {
	$routeProvider.when('/', {
		controller : 'RegisterController',
		templateUrl : 'login'
	});
});

indexApp.run(

function(editableOptions) {
	editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also
	// 'bs2', 'default'
});