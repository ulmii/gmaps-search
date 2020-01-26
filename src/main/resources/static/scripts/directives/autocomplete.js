'use strict';

angular.module('App')
    .directive('autoComplete', ['autoCompleteDataService', function (autoCompleteDataService) {
        return {
            restrict: 'A',
            scope: {
                countryName: '=',
                selectedCountry: '='
            },
            link: function (scope, elem) {
                elem.autocomplete({
                    source: function (searchTerm, response) {
                        autoCompleteDataService.search(searchTerm.term).then(function (autocompleteResults) {
                            response($.map(autocompleteResults, function (autocompleteResult) {
                                return {
                                    value: autocompleteResult.name,
                                    country: autocompleteResult
                                }
                            }))
                        });
                    },
                    minLength: 1,
                    select: function (event, selectedItem) {
                        event.stopPropagation();
                        var scope = angular.element($("div[ng-controller='MainCtrl']")).scope();
                        scope.$apply(function () {
                            console.log("selected: " + selectedItem.item.country);
                            scope.selectedCountry = selectedItem.item.country;
                        });
                        scope.countryName = selectedItem.item.value;
                        scope.$apply();
                    }
                })
            }
        };
    }]);
