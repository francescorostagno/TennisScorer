'use strict';
 
angular.module('Home')
 
.controller('HomeController',
    ['$scope','$rootScope', '$location', 'AuthenticationService','fileUpload','$http',
    function ($scope, $rootScope, $location, AuthenticationService,fileUpload,$http) {
        $scope.username = $rootScope.globals.currentUser.username;
        $scope.role = $rootScope.globals.currentUser.role;
        $scope.enabled = $rootScope.globals.currentUser.enabled;

        $scope.uploadFile = function() {

            var file = $scope.myFile;
            console.log('file is ' );
            console.dir(file);
            var uploadUrl = "/api/csv/upload";
            fileUpload.uploadFileToUrl(file, uploadUrl);
        };

        $scope.submitPlayer = function (){
            var uploadUrl = "api/common/player";
            $scope.submitPlayerToUrl($scope.playerName,uploadUrl)

        }
        $scope.submitTourney = function (){
            var uploadUrl = "api/common/tourney";
            $scope.submitTourneyToUrl($scope.tourneyName,uploadUrl);

        }

        $scope.submitPlayerToUrl = function (playerName,uploadUrl){
            var data = new FormData();
            data.append('player_name', playerName);
            $http.post(uploadUrl,data,{
                withCredentials : false,
                transformRequest : angular.identity,
                headers : {
                    'Content-Type' : undefined
                }}).then(function (response){
                if( response.status === 200){
                    $scope.playerName = response.data.playerName;
                    $scope.hand = response.data.hand;
                    $scope.birthDate = response.data.birth_date;
                    $scope.countryCode = response.data.country_code;
                    $("#playerTable").css("visibility", "visible");
                }
            })
        }

        $scope.submitTourneyToUrl = function (tourneyName, uploadUrl){

            var data = new FormData();
            data.append('tourney_name', tourneyName);
            $http.post(uploadUrl,data,{
                withCredentials : false,
                transformRequest : angular.identity,
                headers : {
                    'Content-Type' : undefined
                }}).then(function (response){
                if( response.status === 200){
                    $scope.tourney = [];
                    if(response.data){
                        for(var i = 0; i < response.data.length ; i++){
                            var edition = {
                                'winnerName' : response.data[i].winnerName,
                                'loserName' : response.data[i].loserName,
                                'tourneyDate' : response.data[i].tourney_date
                            }
                            $scope.tourney.push(edition)
                        }
                    }
                }
            })

        }

    }])
.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function() {
                scope.$apply(function() {
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}])
    .service('fileUpload', ['$http', function ($http) {
        this.uploadFileToUrl = function(file, uploadUrl) {
            var fd = new FormData();
            fd.append('file', file);

            $http.post(uploadUrl, fd, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            })
                .success(function() {
                })
                .error(function() {
                });
        }
    }])
