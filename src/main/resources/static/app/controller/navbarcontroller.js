var navbarcontroller=angular.module('NavBarController',[])

navbarcontroller.controller('navbarCtrl',function($scope,$state){
    $scope.items = [
        'Carica squadra',
        'Aggiungi Giocatore'
    ];
    
    $scope.status = {
        isopen: false
    };

    $scope.toggled = function(open) {
        //console.log('Dropdown is now: ', open);
    };

    $scope.toggleDropdown = function($event) {
        $event.preventDefault();
        $event.stopPropagation();
        $scope.status.isopen = !$scope.status.isopen;
    };

    $scope.modTeam=function(arg0){
        $state.go('teamMod',{'teamnature':arg0},{reload:true})
    }
})