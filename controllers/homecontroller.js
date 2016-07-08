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
