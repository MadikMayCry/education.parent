// angular.module('MyApp', ['ui.router','ngCookies','btford.socket-io', 'ngMessages','mgcrea.ngStrap'])
angular.module('MyApp', ['ui.router'])
    .config(['$locationProvider','$urlRouterProvider', '$stateProvider' ,function($locationProvider,$urlRouterProvider,$stateProvider) {
        console.log("We are here");
        $locationProvider.html5Mode(true);
        $urlRouterProvider.otherwise('/');
        $stateProvider
            .state('any', {
                url: '/',
                templateUrl: '../views/users.html',
                controller: function($scope,$rootScope,$state,$http){
                    $scope.mainSelectedId = null;
                    $scope.disButtons = true;
                    $scope.list=function(){
                        $http({
                            method: 'POST',
                                url: 'http://localhost:1314/education/api/mycode/list'
                        }).then(function successCallback(response) {
                            console.log("Http request run");


                            $scope.data = response.data;

                            console.log(response.data);
                        }, function errorCallback(response) {
                        });
                    }

                    $scope.page = function (path) {
                        $state.go(path);
                    }

                    $scope.myFunc = function(id) {
                        if($scope.mainSelectedId==id){
                            $scope.mainSelectedId=null;
                            $scope.selectedBirthDate=null;
                            $scope.selectedSurname=null;
                            $scope.selectedName=null;
                            $scope.selectedTelephone=null;
                            $scope.selectedPassword=null;
                            $scope.selectedAddress=null;
                            $scope.selectedEmail=null;
                            $scope.disButtons=true;
                        }
                        else {
                            $scope.mainSelectedId = id;
                            $scope.disButtons = false;
                            for (var i = 0; i < $scope.data.length; i++) {
                                if ($scope.data[i].id == $scope.mainSelectedId) {
                                    $scope.mainSelectedId=$scope.data[i].id;
                                    $scope.selectedBirthDate=$scope.data[i].birthDate;
                                    $scope.selectedSurname=$scope.data[i].surname;
                                    $scope.selectedName=$scope.data[i].name;
                                    $scope.selectedTelephone=$scope.data[i].telephone;
                                    $scope.selectedAddress=$scope.data[i].address;
                                    $scope.selectedEmail=$scope.data[i].email;
                                    $scope.selectedPassword=$scope.data[i].password;
                                }
                            }
                        }
                        console.log("We select id: "+id);
                    }
                    $scope.delete = function (id) {
                        var data = {
                            "id" : ""+id
                        };

                        $http.post("http://localhost:1314/education/api/mycode/delete",data,{
                                headers:{
                                    'Content-Type': "x-www-form-urlencoded"
                                }
                        }).then(function(response){
                            console.log(data);
                            console.log(response.data);
                            $scope.mainSelectedId=null;
                            $scope.selectedBirthDate=null;
                            $scope.selectedSurname=null;
                            $scope.selectedName=null;
                            $scope.selectedTelephone=null;
                            $scope.selectedAddress=null;
                            $scope.selectedEmail=null;
                            $scope.selectedPassword=null;
                            $scope.disButtons=true;
                            $scope.list();
                        });
                    }
                    $scope.add = function () {
                        var data = {
                            "id" : "",
                            "name" : $scope.selectedName,
                            "surname" : $scope.selectedSurname,
                            "birthDate" : $scope.selectedBirthDate,
                            "telephone" : $scope.selectedTelephone,
                            "email" : $scope.selectedEmail,
                            "address" : $scope.selectedAddress,
                            "password" : $scope.selectedPassword
                        };

                        $http.post("http://localhost:1314/education/api/mycode/add",data,{
                            headers:{
                                'Content-Type': "x-www-form-urlencoded"
                            }
                        }).then(function(response){
                            console.log(data);
                            console.log(response.data);
                            $scope.mainSelectedId=null;
                            $scope.selectedBirthDate=null;
                            $scope.selectedSurname=null;
                            $scope.selectedName=null;
                            $scope.selectedTelephone=null;
                            $scope.selectedAddress=null;
                            $scope.selectedEmail=null;
                            $scope.selectedPassword = null;
                            $scope.disButtons=true;
                            $scope.list();
                        });
                    }
                    $scope.edit = function () {
                        var data = {
                            "password": $scope.selectedPassword,
                            "id" : $scope.mainSelectedId,
                            "name" : $scope.selectedName,
                            "surname" : $scope.selectedSurname,
                            "birthDate" : $scope.selectedBirthDate,
                            "telephone" : $scope.selectedTelephone,
                            "email" : $scope.selectedEmail,
                            "address" : $scope.selectedAddress
                        };

                        $http.post("http://localhost:1314/education/api/mycode/add",data,{
                            headers:{
                                'Content-Type': "x-www-form-urlencoded"
                            }
                        }).then(function(response){
                            console.log(data);
                            console.log(response.data);
                            $scope.mainSelectedId=null;
                            $scope.selectedBirthDate=null;
                            $scope.selectedSurname=null;
                            $scope.selectedName=null;
                            $scope.selectedTelephone=null;
                            $scope.selectedAddress=null;
                            $scope.selectedEmail=null;
                            $scope.selectedPassword=null;
                            $scope.disButtons=true;
                            $scope.list();
                        });
                    }

                    $scope.mainSelectedId=null;
                    $scope.selectedBirthDate=null;
                    $scope.selectedSurname=null;
                    $scope.selectedName=null;
                    $scope.selectedTelephone=null;
                    $scope.selectedAddress=null;
                    $scope.selectedPassword=null;
                    $scope.selectedEmail=null;

                    $scope.list();
                    $scope.page = function (path) {
                        $state.go(path);
                    }
                }
            }).state('login',{
                url: '/login',
                templateUrl: '../views/login.html',
                controller: function($scope,$rootScope,$state,$http){
                        $scope.email = "";
                        $scope.password = "";

                        $scope.inputType = 'password';

                        $scope.isActive = false;


                        $scope.hideShowPassword = function(){
                            if ($scope.inputType == 'password')
                                $scope.inputType = 'text';

                            else
                                $scope.inputType = 'password';

                            $scope.isActive = !$scope.isActive;
                        };

                        $scope.change = function (path) {
                            $state.go(path);
                        }
                        $scope.loginUser = function () {
                            var data = {
                                'email':""+$scope.email,
                                'password':""+$scope.password
                            };
                            $http.post("http://localhost:1314/education/api/user/login",data,{
                               headers:{
                                   'Content-Type':"x-www-form-urlencoded"
                               }
                            }).then(function (response) {

                                $scope.data = response.data;
                                $rootScope.id = $scope.data.id;
                                $rootScope.type_id = $scope.data.type_id;
                                console.log($scope.data);
                                if($scope.data.type_id == "1"){
                                    $state.go('admin');
                                }
                                else if($scope.data.type_id == "2"){
                                    $state.go('client');
                                }
                            });
                        }

                }
            }).state('admin',{
                url : '/admin',
                templateUrl : '../views/admin.html',
                controller: function ($scope,$rootScope,$state,$http) {
                    if ($rootScope.type_id != "1"){
                        $state.go('client');
                    }
                }
            }).state('client',{
                url : '/client',
                templateUrl : '../views/client.html',
                controller: function ($scope,$rootScope,$state,$http) {
                    console.log("HI GITLER");
                }
            }).state('register',{
                url : '/register',
                templateUrl : '../views/reg.html',
                controller : function ($scope,$rootScope,$state,$http) {
                    $scope.selectedBirthDate=null;
                    $scope.selectedSurname=null;
                    $scope.selectedName=null;
                    $scope.selectedTelephone=null;
                    $scope.selectedAddress=null;
                    $scope.selectedEmail=null;
                    $scope.selectedPassword = null;



                    $scope.reg = function () {
                        var data = {
                            "id" : "",
                            "name" : $scope.selectedName,
                            "surname" : $scope.selectedSurname,
                            "birthDate" : $scope.selectedBirthDate,
                            "telephone" : $scope.selectedTelephone,
                            "email" : $scope.selectedEmail,
                            "address" : $scope.selectedAddress,
                            "password" : $scope.selectedPassword
                        };
                        $http.post("http://localhost:1314/education/api/mycode/add",data,{
                            headers:{
                                'Content-Type':"x-www-form-urlencoded"
                            }
                        }).then(function (response) {
                            console.log(response.data);
                        });
                    }
                }
            });
    }]);
