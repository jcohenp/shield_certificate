'use strict';

angular.module('myApp.dashboard', ['ngRoute', 'ui.bootstrap'])
.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/', {
    templateUrl: 'dashboard/dashboard.html',
    controller: DashboardCtrl,
		resolve: DashboardCtrl.resolve
  });
}])

.directive('ngFiles', ['$parse', function ($parse) {

    function fn_link(scope, element, attrs) {
        var onChange = $parse(attrs.ngFiles);
        element.on('change', function (event) {
            onChange(scope, { $files: event.target.files });
        });
    };

    return {
        link: fn_link
    }
} ])
.controller('CreateCertificateCtrl', ["$scope", "$http", "AuthService", function ($scope, $http, authService) {

    var formdata = new FormData();
    $scope.getTheFiles = function ($files) {
        angular.forEach($files, function (value, key) {
            formdata.append(key, value);
        });
      var userName = $("#DataCertificate").attr("data-userName");
      formdata.append("userName", userName);
      formdata.append("idCert", $("#DataCertificate").attr("data-certId"));
    };

    $scope.CreateCertificate = function() {

        var request = {
            method: 'POST',
            url: '/api/user/create_certificat',
            data: formdata,
            headers :{
                'Authorization': 'Bearer ' + authService.getJwtToken(),
                'Content-Type': undefined
            }
        };

        // SEND THE FILES.
        $http(request)
            .success(function (d) {
              formdata.delete("userName");
              formdata.delete("idCert");
              document.getElementById("uploadCaptureInputFile").value = "";
              $(".modal").modal('hide');
              $("#" + d.userName).append("<li>" + d.fileName + "</li>")
            })
            .error(function () {
            });
    };
}]);

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
        $("#DataCertificate").attr("data-userName", event.currentTarget.attributes["data-userName"].value);
        $("#DataCertificate").attr("data-certId", event.currentTarget.parentNode.firstElementChild.childElementCount);

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

