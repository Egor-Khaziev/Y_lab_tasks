(function () {
    angular
        .module('game-front', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'welcome/welcome.html',
                controller: 'welcomeController'
            })
            .when('/game', {
                templateUrl: 'game/game.html',
                controller: 'gameController'
            })

            .otherwise({
                redirectTo: '/'
            });
    }

    function run($rootScope, $http, $localStorage) {

    }
})();

angular.module('game-front').controller('indexController', function ($rootScope, $scope, $http) {

    $scope.tryToAuth = function () {
        $http({
            url: 'http://localhost:8080/gameplay?player1='+$scope.user+'&player2='+$scope.user,
            method: 'GET',
        }).then(function (response) {
            $scope.auth = true;
            $scope.XOResponse = response.data;
        });
    };

    // $scope.tryToA222uth = function () {
    //     $http.get('http://localhost:8080/gameplay' + $scope.user1)
    //         .then(function successCallback(response) {
    //             if (response.data.token) {
    //                 $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
    //                 $localStorage.springWebUser = {username: $scope.user.username, token: response.data.token};
    //
    //                 $scope.user.username = null;
    //                 $scope.user.password = null;
    //
    //                 $http.get('http://localhost:5555/cart/api/v1/cart/' + $localStorage.springWebGuestCartId + '/merge')
    //                     .then(function successCallback(response) {
    //                     });
    //
    //                 $location.path('/');
    //             }
    //         }, function errorCallback(response) {
    //         });
    // };

});