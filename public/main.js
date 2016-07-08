(function e(t,n,r){function s(o,u){if(!n[o]){if(!t[o]){var a=typeof require=="function"&&require;if(!u&&a)return a(o,!0);if(i)return i(o,!0);throw new Error("Cannot find module '"+o+"'")}var f=n[o]={exports:{}};t[o][0].call(f.exports,function(e){var n=t[o][1][e];return s(n?n:e)},f,f.exports,e,t,n,r)}return n[o].exports}var i=typeof require=="function"&&require;for(var o=0;o<r.length;o++)s(r[o]);return s})({1:[function(require,module,exports){
<<<<<<< HEAD
module.exports = function(app){
  app.controller('HomeController',['$scope','$http',function($scope,$http){
  $scope.loginClick = function(){
    console.log(clicked)
  }
}])}
=======
module.exports = function(app) {
    app.controller('HomeController', function($scope, $location) {
        $scope.loginClick = function() {
            console.log('clicked')
            if ($scope.username != null){
              $location.path('/home');
            } else {
              alert ('Please enter a username');
            }
        }
        $scope.bandManagerSelect = function() {
            console.log('clicked band manger options')
        }
        $scope.musicianSelect = function() {
            console.log('clicked musical instruments')
        }
    });
};
>>>>>>> 9af4d9f4d448a0f96324c86d15aed2010e9cdf63

},{}],2:[function(require,module,exports){
module.exports = function(app) {
    app.controller('LookingForController', ['$scope', '$http', function($scope, $http) {
        $scope.loginClick = function() {
            console.log('clicked')
        }
    }]);
};

},{}],3:[function(require,module,exports){

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

},{"./controllers/homecontroller":1,"./controllers/lookingforcontroller":2}]},{},[3])