'use strict';

angular.module('App')
    .factory('autoCompleteDataService', ['$http', function ($http) {
        return {
            search: function (country) {
                return $http.get("/countries?country=" + country).then(function (response) {
                    return response.data;
                });
            }
        }
    }]);
