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
    });
};
