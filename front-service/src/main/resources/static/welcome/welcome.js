angular.module('game-front').controller('welcomeController', function ($scope, $http) {

    const contextPath = 'http://localhost:8080/gameplay';

        // $scope.checkOut = function () {
        //     $http({
        //         //url: contextPath + '?player1='+$scope.users.player1+'&player2='+$scope.users.player2,
        //         url: contextPath + '?player1=Maxxx&player2=тест',
        //         method: 'GET',
        //     }).then(function (response) {
        //         alert("saffas");
        //         $scope.auth = true;
        //         $scope.XOResponse = response.data;
        //     });
        // };

    $scope.checkOut = function () {
        $http.get(contextPath + '?player1=Maxxx&player2=tset')
            .then(function (response) {
                $scope.XOResponse = response.data;
            });
    }

});