'use strict';

angular.module('myApp.dashboard', ['ngRoute', 'ui.bootstrap'])
.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/', {
    templateUrl: 'dashboard/dashboard.html',
    controller: DashboardCtrl,
		resolve: DashboardCtrl.resolve
  });
}])
.controller('CreateCertificateCtrl', function ($scope) {

	// datePicker ANGULAR UI
    $scope.today = function() {
        $scope.dt = new Date();
    };
    $scope.today();

    $scope.clear = function() {
        $scope.dt = null;
    };

    $scope.dateOptions = {
        formatYear: 'yy',
        maxDate: new Date(2020, 5, 22),
        minDate: new Date(),
        startingDay: 1
    };

    $scope.open1 = function() {
        $scope.popup1.opened = true;
    };


    $scope.setDate = function(year, month, day) {
        $scope.dt = new Date(year, month, day);
    };

    $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
    $scope.format = $scope.formats[0];
    $scope.altInputFormats = ['M!/d!/yyyy'];

    $scope.popup1 = {
        opened: false
    };

    $scope.inlineOptions = {
        customClass: getDayClass,
        minDate: new Date(),
        showWeeks: true
    };


    function getDayClass(data) {
        var date = data.date,
            mode = data.mode;
        if (mode === 'day') {
            var dayToCheck = new Date(date).setHours(0,0,0,0);

            for (var i = 0; i < $scope.events.length; i++) {
                var currentDay = new Date($scope.events[i].date).setHours(0,0,0,0);

                if (dayToCheck === currentDay) {
                    return $scope.events[i].status;
                }
            }
        }

        return '';
    }


});

function DashboardCtrl($scope, $rootScope, $http, isAuthenticated, authService) {
	$rootScope.authenticated = isAuthenticated;

	$scope.serverResponse = '';

	var setResponse = function(res) {
		$rootScope.authenticated = isAuthenticated;
		$scope.serverResponse = res;
	};

	if ($rootScope.authenticated) {
		authService.getAllUser()
		.then(function(response) {
			$scope.users = response.data;
		});
	}

	$scope.getAllUserInfo = function() {
    $http({
      headers: authService.createAuthorizationTokenHeader(),
      method: 'GET',
      url: 'api/user/all'
    })
		.then(function(res) {
			setResponse(res, true);
		})
		.catch(function(response) {
			setResponse(response, false);
		});
	}

	$scope.showModal = function(event) {
        $("#DataCertificate").find("input#userName").val(event.currentTarget.attributes["data-userName"].value);
	};

    $scope.CreateCertificate = function() {
    	var date = $("input#datePicker").val();
        $http({
            headers: authService.createAuthorizationTokenHeader(),
            method: 'POST',
            url: 'api/certificat/create_certificat',
			data: {date: date}
        })
            .success(function(res) {
                console.log(res)
            })
            .catch(function(response) {
                setResponse(response, false);
            });
    };
}
DashboardCtrl.resolve = {
	isAuthenticated : function($q, $http, AuthService) {
		var deferred = $q.defer();
		var oldToken = AuthService.getJwtToken();
		if (oldToken !== null) {
      $http({
        headers: AuthService.createAuthorizationTokenHeader(),
        method: 'POST',
        url: 'auth/refresh'
      })
      .success(function(res) {
        AuthService.setJwtToken(res.access_token);
        deferred.resolve(res.access_token !== null);
      })
      .error(function(err){
        AuthService.removeJwtToken();
        deferred.resolve(false); // you could optionally pass error data here
      });
		} else {
      deferred.resolve(false);
		}
		return deferred.promise;
	}
};

DashboardCtrl.$inject = ['$scope', '$rootScope', '$http', 'isAuthenticated', 'AuthService'];

