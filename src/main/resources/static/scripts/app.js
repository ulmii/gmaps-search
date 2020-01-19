'use strict';

angular
  .module('angularExampleApp', [
    'ngRoute'
  ])
  .config(function ($routeProvider, $locationProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'index.html',
        controller: 'MainCtrl',
        controllerAs: 'main'
      })
      .otherwise({
        redirectTo: '/'
      });
    $locationProvider.html5Mode({
      enabled: true,
      requireBase: false
    });

  }).run(function ($rootScope) {

});
