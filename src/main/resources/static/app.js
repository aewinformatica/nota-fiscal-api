'use strict';

angular.module('app', [
  'ngRoute',
  'app.home',
  'app.mercadoria',
  'app.notafiscal'
])
.config(['$locationProvider', '$routeProvider', function($locationProvider, $routeProvider) {
  $locationProvider.hashPrefix('!');
  $routeProvider.otherwise({redirectTo: '/home'});

}]);
