'use strict';
 
angular.module('Home')
 
.controller('HomeController',
    ['$scope','$rootScope', '$location', 'AuthenticationService','fileUpload','$http',"$window",
    function ($scope, $rootScope, $location, AuthenticationService,fileUpload,$http,$window) {
        $scope.username = $rootScope.globals.currentUser.username;
        $scope.role = $rootScope.globals.currentUser.role;
        $scope.enabled = $rootScope.globals.currentUser.enabled;
        $scope.player = null;
        $scope.errorMessage = null;

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
            },
            {
                value: 'dateRangeTourney' , displayName: 'Search Tourney Between Date'
            },{
                value: 'dateBirthRangePlayer' , displayName: 'Search Player Between Date'
            }

        ]


        $scope.createUser = function (){
            var uploadUrl = "login/create_user";
            $scope.createUserToUrl($scope.newUsername,$scope.newPassword,$scope.newEmail,uploadUrl)
        }

        $scope.uploadFile = function() {
            var file = $scope.myFile;
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

        $scope.submitGoldenRegister = function (){
            var uploadUrl = 'api/common/golden_register';
            $scope.submitGoldenRegisterToUrl($scope.goldenRegister,uploadUrl)

        }

        $scope.submitTourneyMatch = function (){

            var uploadUrl = 'api/common/tourney_match';
            $scope.submitTourneyMatchToUrl($scope.tourneyMatchId, uploadUrl)
        }

        $scope.submitPlayerRanking = function(){
            var uploadUrl = 'api/common/player_ranking';
            $scope.submitPlayerRankingToUrl($scope.playerNameRanking,uploadUrl);
        }

        $scope.submitDateRangeTourney = function (){
            var uploadUrl = 'api/common/get_date_range_tourney';
            $scope.submitDateRangeTourneyToUrl($scope.dateStartRangeTourney,$scope.dateEndRangeTourney,uploadUrl);
        }

        $scope.submitDateBirthRangePlayer = function (){
            var uploadUrl = 'api/common/get_date_birth_range_player';
            $scope.submitDateBirthRangePlayerToUrl($scope.dateStartRangeBirthPlayer,$scope.dateEndRangeBirthPlayer,uploadUrl);
        }

        $scope.showError = function (status){
            switch (status){
                case 204:
                    $scope.errorMessage = 'Nessuna Corrispondenza';
                    $window.alert( $scope.errorMessage);
                    break;
                case 400:
                    $scope.errorMessage = 'Errore Immissione Dati';
                    $window.alert( $scope.errorMessage);
                    break;
                case 500:
                    $scope.errorMessage = 'Errore Server Interno';
                    $window.alert( $scope.errorMessage);
                    break;
            }
        }

        $scope.createUserToUrl = function (username,password,email,uploadUrl){
            var data = new FormData();
            data.append('username', username);
            data.append('password', password);
            data.append('email', email);
            data.append('role',"user");
            $http.post(uploadUrl,data,{
                withCredentials : false,
                transformRequest : angular.identity,
                headers : {
                    'Content-Type' : undefined
                }}).then(function (response){
                if( response.status === 200){
                    $scope.errorMessage = null;
                    $location.path('');
                }else{
                    $scope.showError(response.status);
                }
            })
        }

        $scope.submitDateRangeTourneyToUrl = function (dateStart,dateEnd,uploadUrl){

            $http({
                url: uploadUrl,
                method: 'GET',
                params: {
                   date_start: dateStart,
                   date_end: dateEnd
                }
            }).then(function(response){
                if( response.status === 200){
                    $scope.errorMessage = null;
                    $scope.dateRangeTourneyArray = [];
                    if(response.data){
                        for(var i = 0; i < response.data.length ; i++){
                            var yy = response.data[i].tourney_date.slice(0,4);
                            var mm = response.data[i].tourney_date.slice(5,6);
                            var dd = response.data[i].tourney_date.slice(7,8);

                            var date = new Date(parseInt(yy),parseInt(mm),parseInt(dd));

                            var tourney = {
                                'tourneyName' : response.data[i].tourneyName,
                                'tourneyDate': date,
                                'winnerName' : response.data[i].winnerName,
                                'loserName' : response.data[i].loserName,
                                'surface': response.data[i].surface,
                                'level' : response.data[i].level,
                            }
                            $scope.dateRangeTourneyArray.push(tourney)
                        }
                    }
                }else{
                    $scope.showError(response.status);
                }
            })

        }

        $scope.submitDateBirthRangePlayerToUrl = function (dateStart,dateEnd,uploadUrl){

            $http({
                url:uploadUrl,
                method:'GET',
                params: {
                    date_start: dateStart,
                    date_end: dateEnd
                }
            }).then(function (response){
                if(response.status === 200){
                    $scope.errorMessage = null;
                    $scope.playerDateBirthRange = [];
                    if(response.data){
                        for(var i = 0; i < response.data.length; i++){
                            var yy = response.data[i].birth_date.slice(0,4);
                            var mm = response.data[i].birth_date.slice(5,6);
                            var dd = response.data[i].birth_date.slice(7,8);

                            var date = new Date(parseInt(yy),parseInt(mm),parseInt(dd));
                            var player = {
                                'playerName': response.data[i].playerName,
                                'birthDate' :date,
                                'hand' : response.data[i].hand,
                                'countryCode' : response.data[i].country_code
                            }
                            $scope.playerDateBirthRange.push(player);
                        }
                    }
                }else{
                    $scope.showError(response.status);
                }
            })
        }

        $scope.submitPlayerRankingToUrl = function (playerNameRanking,uploadUrl){

            $http({
                url: uploadUrl,
                method: 'GET',
                params: {
                    player_name: playerNameRanking
                }
            }).then(function (response){
                if( response.status === 200){
                    $scope.errorMessage = null;
                    $scope.playerRankingsArray = [];
                    if(response.data){
                        for(var i = 0; i < response.data.length ; i++){

                            var yy = response.data[i].rankingDate.slice(0,4);
                            var mm = response.data[i].rankingDate.slice(5,6);
                            var dd = response.data[i].rankingDate.slice(7,8);

                            var date = new Date(parseInt(yy),parseInt(mm),parseInt(dd));
                            var ranking = {
                                'rankingDate' :date,
                                'rank' : response.data[i].rank,
                                'points' : response.data[i].points
                            }
                            $scope.playerRankingsArray.push(ranking);
                        }
                    }

                }else{
                    $scope.showError(response.status);
                }
            })

        }

        $scope.submitTourneyMatchToUrl = function (tourneyMatchId,uploadUrl){

            $http({
                url: uploadUrl,
                method: 'GET',
                params: {
                    tourney_id: tourneyMatchId
                }
            }).then(function (response){
                if( response.status === 200){
                    $scope.errorMessage = null;
                    $scope.tourneyMatchsArray = [];
                    if(response.data){
                        for(var i = 0; i < response.data.length ; i++){

                            var yy = response.data[i].match_date.slice(0,4);
                            var mm = response.data[i].match_date.slice(5,6);
                            var dd = response.data[i].match_date.slice(7,8);
                            var date = new Date(parseInt(yy),parseInt(mm),parseInt(dd));
                            var match = {
                                'winnerPlayer' : response.data[i].winnerPlayer,
                                'loserPlayer' : response.data[i].loserPlayer,
                                'score' : response.data[i].score,
                                'matchStatistics' : response.data[i].matchStatistics,
                                'matchNum' : response.data[i].match_num,
                                'matchDate' : date
                            }
                            $scope.tourneyMatchsArray.push(match);
                        }
                        $scope.tourneyMatchName = '';
                        $http({
                            url: 'api/common/tourney_id',
                            method: 'GET',
                            params: {
                                tourney_id: tourneyMatchId
                            }
                        }).then(function (response){
                            $scope.tourneyMatchName = response.data.tourneyName;
                        });
                    }

                }else{
                    $scope.showError(response.status);
                }
            })
        }

        $scope.submitPlayerToUrl = function (playerName,uploadUrl){

            $http({
                url: uploadUrl,
                method: 'GET',
                params: {
                    player_name: playerName
                }
            }).then(function (response){
                if( response.status === 200){
                    $scope.errorMessage = null;
                    var yy = response.data.birth_date.slice(0,4);
                    var mm = response.data.birth_date.slice(5,6);
                    var dd = response.data.birth_date.slice(7,8);

                    var date = new Date(parseInt(yy),parseInt(mm),parseInt(dd));

                    $scope.player = {
                        'player' :  response.data.playerName,
                        'hand' :  response.data.hand,
                        'birthDate': date,
                        'countryCode': response.data.country_code
                    };
                }else{
                    $scope.showError(response.status);
                }
            })

        }

        $scope.submitTourneyToUrl = function (tourneyName, uploadUrl){

            $http({
                url: uploadUrl,
                method: 'GET',
                params: {
                    tourney_name: tourneyName
                }
            }).then(function (response){
                if( response.status === 200){
                    $scope.errorMessage = null;
                    $scope.tourney = [];
                    if(response.data){
                        for(var i = 0; i < response.data.length ; i++){

                            var yy = response.data[i].tourney_date.slice(0,4);
                            var mm = response.data[i].tourney_date.slice(5,6);
                            var dd = response.data[i].tourney_date.slice(7,8);
                            var date = new Date(parseInt(yy),parseInt(mm),parseInt(dd));
                            var edition = {
                                'winnerName' : response.data[i].winnerName,
                                'loserName' : response.data[i].loserName,
                                'tourneyDate' : date
                            }
                            $scope.tourney.push(edition)
                        }
                    }
                }else {
                    $scope.showError(response.status);
                }
            })

        }

        $scope.submitGoldenRegisterToUrl = function (tourneyName, uploadUrl){

            $http({
                url: uploadUrl,
                method: 'GET',
                params: {
                    tourney_name: tourneyName
                }
            }).then(function (response){
                if( response.status === 200){
                    $scope.errorMessage = null;
                    $scope.goldenRegisterArray = [];
                    if(response.data){
                        for(var i = 0; i < response.data.length ; i++){

                            var yy = response.data[i].tourney_date.slice(0,4);
                            var mm = response.data[i].tourney_date.slice(5,6);
                            var dd = response.data[i].tourney_date.slice(7,8);
                            var date = new Date(parseInt(yy),parseInt(mm),parseInt(dd));
                            var edition = {
                                'winnerName' : response.data[i].winner_name,
                                'loserName' : response.data[i].loser_name,
                                'tourneyDate' : date,
                                'surface' : response.data[i].surface
                            }
                            $scope.goldenRegisterArray.push(edition)
                        }
                    }
                }else {
                    $scope.showError(response.status);
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
