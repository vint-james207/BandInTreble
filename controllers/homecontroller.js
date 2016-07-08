module.exports = function(app){
  app.controller('HomeController',['$scope','$http',function($scope,$http){
  $scope.loginClick = function(){
    let categories=[]
  var gettrivia = function(){
    console.log('trivia')
    return $http({
    method:'GET',
    url: 'http://jservice.io/api/random',
  }).then(function(response){
      let data= response.data[0]
      value= data.value;
      score = score + data.value;
      correct.push({
      answer: data.answer,
      value : data.value
    });
  })
    console.log('clicked')
  }
}
