angular.module('game-front').controller('gameController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8080/gameplay';

    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: contextPath + 'api/v1/products',
            method: 'GET',
            params: {
                p: pageIndex,
                title_part: $scope.filter ? $scope.filter.title_part : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null
            }
        }).then(function (response) {
            $scope.ProductsPage = response.data;
            $scope.paginationArray = $scope.generatePagesIndexes(1, $scope.ProductsPage.totalPages);
        });
    };

    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.addToCart = function (productId) {
        $http.get('http://localhost:5555/cart/api/v1/cart/' + $localStorage.springWebGuestCartId + '/add/' + productId)
            .then(function (response) {
                $scope.addResponse = response.data;

                if (response.data.value != "OK"){alert(response.data.value);}
            }, function errorCallback(response) {
                alert("корзина не отвечает")
            });
    }

    $scope.getTop = function (productId) {
        $http.get('http://localhost:5555/statistic/api/v1/getTop')
            .then(function (response) {
                $scope.top = response.data;
            });
    }



    $scope.checkOut = function () {
        $http.get(contextPath + '?player1=Maxxx&player2=tset')
            .then(function (response) {
                $scope.XOResponse = response.data;
            });
    }



    $scope.loadProducts();
    $scope.getTop();
});