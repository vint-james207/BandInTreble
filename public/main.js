(function e(t,n,r){function s(o,u){if(!n[o]){if(!t[o]){var a=typeof require=="function"&&require;if(!u&&a)return a(o,!0);if(i)return i(o,!0);throw new Error("Cannot find module '"+o+"'")}var f=n[o]={exports:{}};t[o][0].call(f.exports,function(e){var n=t[o][1][e];return s(n?n:e)},f,f.exports,e,t,n,r)}return n[o].exports}var i=typeof require=="function"&&require;for(var o=0;o<r.length;o++)s(r[o]);return s})({1:[function(require,module,exports){
module.exports = function(app) {
    app.controller('HomeController',['MusicFactory','$scope','$location',function(MusicFactory,$scope, $location) {
        $scope.loginClick = function() {
          MusicFactory.postThis($scope.username)
            console.log('clicked')
            if ($scope.username != null){
              $location.path('/home');
            } else {
              alert ('Please enter a username');
            }
        }
    $scope.bandManagerSelect = function() {
            console.log('clicked band manger options')
            // $location.path('/available');
        }
        $scope.musicianSelect = function($scope, $location) {
            console.log('clicked musical instruments')
            ////need to make sure they can select multiple before they are redirected to the lookingfor page
            // $location.path('/lookingfor');
        }
    }]);
};

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

//
// THIS IS THE SERVICE BREH BRO
//
app.factory('MusicFactory', ['$http', '$location', function($http, $location) {
    let musicianPeople = [];
    return {
        // todo: rename this to be more specific
        postThis: function(name) {
            $http({
                url: '/login',
                method: 'post',
                data: {
                    name: name,
                    password: "1234",
                    avatar: null,

                },
            }).then(function(results) {
                console.log("posted")
            });
        },
        getThis: function(name) {
            $http({
                url: '/musician',
                method: 'get',

            }).then(function(response) {
                let musicians = response.data;
                musicians.forEach(function(element) {
                    musicianPeople.push(element.value);
                })
                console.log("gotit")
            });
        },
    }; // end return
}]);

},{"./controllers/homecontroller":1,"./controllers/lookingforcontroller":2}]},{},[3])