tennisScorerApp.controller('BaseFormController', ['$scope', '$http','md5', function ($scope, $http, $md5) {

        var fieldWithFocus;

        $scope.vm = {
            submitted: false,
            errorMessages: []
        };

        $scope.onLogin = function () {
            console.log('Attempting login with username ' + $scope.vm.username + ' and password ' + $scope.vm.password);

            $scope.vm.submitted = true;

            $scope.login();

        };

        $scope.onRegister = function () {
            console.log('Attempting login with username ' + $scope.vm.username + ' and password ' + $scope.vm.password);

            $scope.vm.submitted = true;

            $scope.register();

        };
        $scope.register = function (){
            var pdData = $scope.preparePostData()

            $http({
                method: 'POST',
                url: '/user/register',
                data: pdData,
                contentType: false,
                processData: false,
                headers : {
                    'Content-Type':  'application/x-www-form-urlencoded; charset=UTF-8'
                }
            }).then(function(response) {
                    console.log(response)
                    if (response.status == 200) {

                        window.location.replace('/home.html');
                    }
                    else {
                        $scope.vm.errorMessages = [];
                        $scope.vm.errorMessages.push({description: 'Access denied'});
                    }
                });
        }

        $scope.focus = function (fieldName) {
            fieldWithFocus = fieldName;
        };

        $scope.blur = function (fieldName) {
            fieldWithFocus = undefined;
        };

        $scope.isMessagesVisible = function (fieldName) {
            return fieldWithFocus === fieldName || $scope.vm.submitted;
        };

        $scope.preparePostData = function () {
            var username = $scope.vm.username != undefined ? $scope.vm.username : '';
            var password = $scope.vm.password != undefined ? $md5.createHash($scope.vm.password) : '';
            var email = $scope.vm.email != undefined ? $scope.vm.email : '';

            return 'username=' + username + '&password=' + password + '&email=' + email;
        }

        $scope.login = function () {

            var pdData = $scope.preparePostData()

            $http({
                method: 'POST',
                url: '/user/authenticate',
                data: pdData,
                contentType: false,
                processData: false,
                headers : {
                    'Content-Type':  'application/x-www-form-urlencoded; charset=UTF-8'
                }
            })
            .then(function(response) {
                if (response.status == 200) {
                    window.location.replace('/home.html');
                }
                else {
                    $scope.vm.errorMessages = [];
                    $scope.vm.errorMessages.push({description: 'Access denied'});
                }
            });
        }


    }])
