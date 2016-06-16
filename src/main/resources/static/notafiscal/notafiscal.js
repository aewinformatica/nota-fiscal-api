'use strict';

angular.module('app.notafiscal', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/notafiscal', {
    templateUrl: './notafiscal/notafiscal-list.html',
    controller: 'NotaFiscalCtrl'
  }).when('/notafiscal/detail', {
    templateUrl: './notafiscal/notafiscal-detail.html',
    controller: 'NotaFiscalCtrl'
  });
}])
.controller('NotaFiscalCtrl', ['$http', function($http) {
  var self = this;

  self.detail = function(nf) {
    self.nf = nf;
    $http.get(nf._links.itens.href).success(function(response) {
      self.itens = response._embedded.mercadoria;
    }).error(function(error) {
      console.log('error: ' + error);
    });
  };

  self.init = function() {
    $http.get('/api/notafiscal').success(function(response) {
      self.notasfiscais = response._embedded.notafiscal;
    }).error(function(error) {
      console.log('error: ' + error);
    });
  };

  self.init();
}]);

