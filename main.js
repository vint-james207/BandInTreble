let app = angular.module('BandInTreble', ['ngRoute']);
require('./controllers/homecontroller')(app);
require('./controllers/availablecontroller')(app);
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
        .when('/available', {
            controller: 'AvailableController',
            templateUrl: 'templates/available.html',
        })
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
    let bandmanagerPeople = [];
    return {
        // todo: rename this to be more specific
        postThis: function(name) {
            $http({
                url: '/login',
                method: 'POST',
                data: {
                    name: name,
                    password: "1234",
                    avatar: null,

                },
            }).then(function(results) {
                console.log("posted")
            });
        },
        getMusician: function() {
            $http({
                url:'/band-manager',
                method: 'GET',
            }).then(function(response) {
              // console.log('response')
                let musicians = response.data;
                musicians.forEach(function(element) {
                    musicianPeople.push(element.value);
                })
            });
        },
        getBandManager: function() {
            $http({
                url: '/musician',
                method: 'GET',

            }).then(function(response) {
                let bandmanager = response.data;
                console.log(bandmanager);
                bandmanager.forEach(function(element) {
                    bandmanagerPeople.push(element.value);
                })
                console.log("gotit")

            });

        },
    }; // end return
}]);
