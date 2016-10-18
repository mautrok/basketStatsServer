var teamService=angular.module('teamService',[])

teamService.service('teamService',function($http,constants){
    
    var team=[];
    var team_name;
    var teamAdv=[];
    var teamAdv_name;
    
    var getTeam_name=function(){
        return team;
    }
    var getAdv_name=function(){
        return teamAdv;
    }
    var setTeamAdv_name=function(arg0){
        teamAdv_name=arg0;
    }
    var setTeam_name=function(arg0){
        team_name=arg0;
    }
    this.getTeam=function(){
        return team;
    }
    this.getAdv=function(){
        return teamAdv;
    }
    this.setTeamAdv=function(arg0,arg1){
        teamAdv=arg0;
        setTeamAdv_name=arg1;
    }
    this.setTeam=function(arg0,arg1){
        team=arg0;
        setTeam_name=arg1;
    }
    
    this.saveTeam=function(arg0,arg1){
        return $http.post(constants.SERVERADDRESSSPRING+'/saveteam/'+arg1,arg0)
    }
    
    this.saveHistory=function(arg0,arg1){
        console.log('salvo',arg0,arg1)
        historyFormat(arg0)
        historyFormat(arg1)
        console.log('squadre formattate',arg0,arg1)
        $http.post(constants.SERVERADDRESSSPRING+'/savegame/'+getTeam_name()+'?home=true',arg0)
        $http.post(constants.SERVERADDRESSSPRING+'/savegame/'+getAdv_name()+'?home=false',arg1)
    }
    
    this.downloadTeam=function(arg0){
        return $http.get(constants.SERVERADDRESSSPRING+'/'+arg0)
    }
    this.downloadTeams=function(){
        return $http.get(constants.SERVERADDRESSSPRING+'/getteams')
    }
    
    historyFormat=function(arg0){
        for(pl of arg0.players){
            console.log('formatto correttamente',pl.name)
            formatTwoPoint(pl)
            formatThreePoint(pl)
            formatMinutes(pl)
        }
    }
    
    formatTwoPoint=function(pl){
        var percent=0;
        if(pl.twopoint){
            if(pl.twopoint.miss) percent=(pl.twopoint.done/(pl.twopoint.done+pl.twopoint.miss))*100
            else percent=100
        }
        pl.twopoint=percent;
    }
    formatThreePoint=function(pl){
        var percent=0;
        if(pl.threepoint){
            if(pl.threepoint.miss) percent=(pl.threepoint.done/(pl.threepoint.done+pl.threepoint.miss))*100
            else percent=100
        }
        pl.threepoint=percent;
    }
    formatMinutes=function(pl){
        var seconds=0
        var minutes=0
        if(pl.minutes){
            seconds=Math.round(pl.minutes/1000)
            console.log(seconds)
            if(seconds>60)minutes=Math.round(seconds%60)
            else minutes="00"
            seconds=Math.round(seconds-(60*parseInt(minutes)))
            console.log(seconds)
        }
        if(String(minutes).length<2){
            minutes="0"+String(minutes)
        }
        if(String(seconds)<2){
            seconds="0"+String(seconds)
        }
        pl.minutes=String(minutes)+":"+String(seconds)        
    }
})