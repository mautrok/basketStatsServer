var routeservice=angular.module('routeservice',[
    'ui.router']);
routeservice.config(
    function ($stateProvider, $urlRouterProvider, $httpProvider) {
 
        $httpProvider.defaults.withCredentials = true;
        $urlRouterProvider.
            when('/', '/team').when('', '/team');
 
        $stateProvider
            .state('team',{
                url:'/team',
                templateUrl:'app/views/game.html',
                controller:'gameCtrl'
            })
            .state('teamMod',{
                url:'/modificateam:teamnature',
                templateUrl:'app/views/modteam.html',
                controller:'modificaCtrl'
            })
    });