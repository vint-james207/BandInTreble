module.exports = function(app){
  app.controller('HomeController',['$scope','$http',function($scope,$http){
  $scope.loginClick = function(){
    console.log('clicked')
  }
}])}
