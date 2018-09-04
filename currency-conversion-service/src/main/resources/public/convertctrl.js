var app = angular.module('myApp', [])
    .controller('ConvertController', function($scope, $http) {

        $scope.currencyObject = {
                from : "",
                to  : "",
                amount : "",
                amount_converted : "",
                exchangeRate : "",
                totalCalculatedAmount : ""
               
        };

        $scope.currencyCodes = [{value : 'INR', display : 'Indian Rupee'}, {value : 'USD', display : 'US Dollar'}, {value : 'EUR', display : 'Euro Pound'}];
        
        
        $scope.getexchangerate = function() {

           $http.get("http://localhost:8100/currency-converter-feign/from/"+$scope.currencyObject.from+"/to/"+$scope.currencyObject.to+"/quantity/"+$scope.currencyObject.amount) 
        		  
                .then(function(response) {

                    $scope.currencyObject.exchangeRate = response.data.conversionMultiple;
                    $scope.currencyObject.totalCalculatedAmount = response.data.totalCalculatedAmount;
                    
                    
            });

        };


    });

app.filter('toDecimal', function() {
    return function(input, precision) {
        return input.toFixed(precision);
    };
});

