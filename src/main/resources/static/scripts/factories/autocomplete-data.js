'use strict';

angular.module('angularExampleApp')
    .factory('autoCompleteDataService', ['$http', function ($http) {
        return {
            search: function (country) {
                return $http.get("/autocomplete?country=" + country).then(function (response) {
                    return response.data;
                });
            }
        }
    }]);