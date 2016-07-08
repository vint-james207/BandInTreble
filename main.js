let app = angular.module('BandInTreble', ['ngRoute']);
require('./controllers/homecontroller')(app);
require('./controllers/lookingforcontroller')(app);

app.config(['$routeProvider', function($routeProvider) {
    $routeProvider
        .when('/', {
            redirectTo: '/login',
        })
        .when('/login', {
            controller: 'HomeController',
            templateUrl: 'templates/login.html',
        })
        .when('/home', {
            controller: 'HomeController',
            templateUrl: 'templates/home.html',
        })
        // .when('/available', {
        //     controller: 'AvailableController',
        //     templateUrl: 'templates/available.html',
        // })
        .when('/lookingfor', {
            controller: 'LookingForController',
            templateUrl: 'templates/lookingfor.html',
        });
}]);

//
// THIS IS THE SERVICE BREH BRO
//
app.factory('MusicFactory', ['$http', '$location', function($http) {
    return {
      // todo: rename this to be more specific
        postThis: function() {
            $http({
                url: '/musician',
                method: 'post',
                data: {
                    name: 'Logan'
                },
            }).then(function(results) {
                console.log("posted")
            });
        },
    }; // end return
}]);
