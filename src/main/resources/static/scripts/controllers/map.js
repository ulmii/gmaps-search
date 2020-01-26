'use strict';

angular.module('App')
    .controller('MapCtrl', ['$scope', '$rootScope', '$http', '$window', '$timeout', '$compile', function ($scope, $rootScope, $http, $window, $timeout, $compile) {
        var API_KEY = '';
        var params = (new URL(document.location)).searchParams;
        var name = params.get('name');
        var capital = params.get('capital');
        var mapsUrl = 'https://www.google.com/maps/embed/v1/place?key=' + API_KEY + '&q=' + name + ',' + capital;
        var html = '<iframe \
      width="1200" \
      height="700" \
      frameborder="0" style="border:0" \
      src="' + mapsUrl + '" allowfullscreen></iframe>';

        angular.element(document.querySelector('#map')).append($compile(html)($scope));
    }]);


