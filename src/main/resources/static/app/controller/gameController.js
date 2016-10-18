var teamController =angular.module('gameController',[])

teamController.controller('gameCtrl',function(constants,$scope,teamService){
    $scope.start=false;
    $scope.stop=true;
    $scope.fields=[
        constants.PLAYER_FIELD,
        constants.POINT_FIELD,
        constants.THREE_POINT_SHOOT_FIELD,
        constants.ASSIST_FIELD,
        constants.DEF_REBOUND_FIELD,
        constants.OFF_REBOUND_FIELD,
        constants.BALL_STEAL_FIELD,
        constants.BALL_LOOSE_FIELD,
        constants.BLOCK_FIELD,
        constants.TURNOVER_FIELD,
        constants.FOULS_FIELD
    ]
    
    $scope.team=teamService.getTeam();
    $scope.teamAdv=teamService.getAdv();

    $scope.save=function(){
        console.debug('salva')
        teamService.saveHistory($scope.team,$scope.teamAdv)
    }
    var startTime;
    $scope.startGame=function(){
        $scope.start=true;
        $scope.stop=false;
        if(playerOnField.length==5 && advOnField.length==5){
            var today=new Date()
            startTime=today.getTime()
            for(pl of $scope.team.players){
                if(playerOnField.indexOf(pl.name)!=-1){
                    pl.startTime=startTime;
                }
            }
            for(pl of $scope.teamAdv.players){
                if(advOnField.indexOf(pl.name)!=-1){
                    pl.startTime=startTime;
                }
            }
        }
        else{
            alert('Ogni squadra deve avere cinque giocatori in campo per iniziare')
            console.error(playerOnField,advOnField)
        } 
    }
    $scope.stopGame=function(){
        $scope.start=false;
        $scope.stop=true;
        var today=new Date()
        now=today.getTime()
        for(pl of $scope.team.players){
            if(pl.startTime){
                if(pl.minutes==undefined)
                    pl.minutes=0;
                pl.minutes=parseInt(pl.minutes)+(now-pl.startTime)
            }
        }
        for(pl of $scope.teamAdv.players){
            if(pl.startTime){
                if(pl.minutes==undefined)
                    pl.minutes=0;
                pl.minutes=parseInt(pl.minutes)+(now-pl.startTime)
            }
        }
    }
    
    var playerOnField=[]
    var advOnField=[]
    $scope.addPlayerOnField=function(player,teamOrAdv){
        if(playerOnField==undefined)
            playerOnField=[]
        if(advOnField==undefined)
            advOnField=[]    
        if(player.active){
           if(player.active==true){
               return removePLayerFromField(player,teamOrAdv)
           }
        }
        if(teamOrAdv==0){
            if(playerOnField.length<5){
                playerOnField.push(player.name)
                player.active=true;
            }
            else alert('I giocatori in campo possono essere al massimo 5')
        }
        else{
            if(advOnField.length<5){
                advOnField.push(player.name)
                player.active=true;
            }
            else alert('I giocatori in campo possono essere al massimo 5')
        }
    }
    var removePLayerFromField=function(player,teamOrAdv){
        if(teamOrAdv==0) playerOnField=playerOnField.slice(0,playerOnField.indexOf(player.name)).concat(playerOnField.slice(playerOnField.indexOf(player.name)+1,playerOnField.length))
        else advOnField=advOnField.slice(0,advOnField.indexOf(player.name)).concat(advOnField.slice(advOnField.indexOf(player.name)+1,advOnField.length))   
        var now=new Date().getTime();
        player.minutes=player.minutes+(now-player.startTime)
        player.active=false;
    }
    
})