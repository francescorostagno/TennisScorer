'use strict';
 
angular.module('Home')
 
.controller('HomeController',
    ['$scope','$rootScope', '$location', 'AuthenticationService','fileUpload','$http',
    function ($scope, $rootScope, $location, AuthenticationService,fileUpload,$http) {
        $scope.username = $rootScope.globals.currentUser.username;
        $scope.role = $rootScope.globals.currentUser.role;
        $scope.enabled = $rootScope.globals.currentUser.enabled;
        $scope.player = null;

        $scope.searchValue =[
            {
                value: 'playerName' , displayName: 'Search Player'
            },
            {
                value: 'tourneyName' , displayName: 'Search Tourney'
            },
            {
                value: 'goldenRegister' , displayName: 'Search Golden Register'
            },
            {
                value: 'tourneyMatch' ,displayName: 'Search Tourney Match'
            },
            {
                value:'rankingPlayer', displayName: 'Search Player Ranking'
            }
        ]

        $scope.uploadFile = function() {
            var file = $scope.myFile;
            console.log('file is ' );
            console.dir(file);
            var uploadUrl = "/api/csv/upload";
            fileUpload.uploadFileToUrl(file, uploadUrl);
        };

        $scope.clearScope = function (){
            $scope.player = null;
            $scope.tourney = [];
            $scope.goldenRegisterArray = [];
        }

        $scope.submitPlayer = function (){
            $scope.clearScope();
            var uploadUrl = "api/common/player";
            $scope.submitPlayerToUrl($scope.playerName,uploadUrl)

        }
        $scope.submitTourney = function (){
            $scope.clearScope();
            var uploadUrl = "api/common/tourney";
            $scope.submitTourneyToUrl($scope.tourneyName,uploadUrl);

        }

        $scope.submitGoldenRegister = function (){
            $scope.clearScope();
            var uploadUrl = 'api/common/golden_register';
            $scope.submitGoldenRegisterToUrl($scope.goldenRegister,uploadUrl)

        }

        $scope.submitTourneyMatch = function (){
            var uploadUrl = 'api/common/tourney_match';
            $scope.submitTourneyMatchToUrl($scope.tourneyMatchId, uploadUrl)
        }

        $scope.submitPlayerRanking = function(){
            var uploadUrl = 'api/common/player_ranking'
            $scope.submitPlayerRankingToUrl($scope.playerNameRanking,uploadUrl);
        }

        $scope.submitPlayerRankingToUrl = function (playerNameRanking,uploadUrl){
            var data = new FormData();
            data.append('player_name', playerNameRanking);
            $http.post(uploadUrl,data,{
                withCredentials : false,
                transformRequest : angular.identity,
                headers : {
                    'Content-Type' : undefined
                }}).then(function (response){
                if( response.status === 200){
                    $scope.playerRankingsArray = [];
                    if(response.data){
                        for(var i = 0; i < response.data.length ; i++){

                            var yy = response.data[i].rankingDate.slice(0,4);
                            var mm = response.data[i].rankingDate.slice(5,6);
                            var dd = response.data[i].rankingDate.slice(7,8);

                            var ranking = {
                                'rankingDate' : dd + ':' + mm + ':' + yy,
                                'rank' : response.data[i].rank,
                                'points' : response.data[i].points
                            }
                            $scope.playerRankingsArray.push(ranking);
                        }
                    }

                }
            })
        }

        $scope.submitTourneyMatchToUrl = function (tourneyMatchId,uploadUrl){
            var data = new FormData();
            data.append('tourney_id', tourneyMatchId);
            $http.post(uploadUrl,data,{
                withCredentials : false,
                transformRequest : angular.identity,
                headers : {
                    'Content-Type' : undefined
                }}).then(function (response){
                if( response.status === 200){
                    $scope.tourneyMatchsArray = [];
                    if(response.data){
                        for(var i = 0; i < response.data.length ; i++){

                            var yy = response.data[i].match_date.slice(0,4);
                            var mm = response.data[i].match_date.slice(5,6);
                            var dd = response.data[i].match_date.slice(7,8);

                            var match = {
                                'winnerPlayer' : response.data[i].winnerPlayer.playerName,
                                'loserPlayer' : response.data[i].loserPlayer.playerName,
                                'score' : response.data[i].score,
                                'matchStatistics' : response.data[i].matchStatistics,
                                'matchNum' : response.data[i].match_num,
                                'matchDate' : dd + ':' + mm + ':' + yy
                            }
                            $scope.tourneyMatchsArray.push(match);
                        }
                    }

                }
            })
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

                    var yy = response.data.birth_date.slice(0,4);
                    var mm = response.data.birth_date.slice(5,6);
                    var dd = response.data.birth_date.slice(7,8);
                    $scope.player = {
                        'player' :  response.data.playerName,
                        'hand' :  response.data.hand,
                        'birthDate': dd + ':' + mm + ':' + yy,
                        'countryCode': response.data.country_code
                    };
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

                            var yy = response.data[i].tourney_date.slice(0,4);
                            var mm = response.data[i].tourney_date.slice(5,6);
                            var dd = response.data[i].tourney_date.slice(7,8);

                            var edition = {
                                'winnerName' : response.data[i].winnerName,
                                'loserName' : response.data[i].loserName,
                                'tourneyDate' : dd + ':' + mm + ':' + yy
                            }
                            $scope.tourney.push(edition)
                        }
                    }
                }
            })

        }

        $scope.submitGoldenRegisterToUrl = function (tourneyName, uploadUrl){
            var data = new FormData();
            data.append('tourney_name', tourneyName);
            $http.post(uploadUrl,data,{
                withCredentials : false,
                transformRequest : angular.identity,
                headers : {
                    'Content-Type' : undefined
                }}).then(function (response){
                if( response.status === 200){
                    $scope.goldenRegisterArray = [];
                    if(response.data){
                        for(var i = 0; i < response.data.length ; i++){

                            var yy = response.data[i].tourney_date.slice(0,4);
                            var mm = response.data[i].tourney_date.slice(5,6);
                            var dd = response.data[i].tourney_date.slice(7,8);

                            var edition = {
                                'winnerName' : response.data[i].winner_name,
                                'loserName' : response.data[i].loser_name,
                                'tourneyDate' : dd + ':' + mm + ':' + yy,
                                'surface' : response.data[i].surface
                            }
                            $scope.goldenRegisterArray.push(edition)
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
