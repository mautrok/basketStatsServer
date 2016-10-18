var constants=angular.module('Constants',[])

constants.service('constants',function(){
    
    //field in team table
    this.PLAYER_FIELD={title:"Giocatori",field:"players"}
    
    this.POINT_FIELD={title:"Punti",field:"point"}
    this.ASSIST_FIELD={title:"Assist",field:"assist"}
    this.DEF_REBOUND_FIELD={title:"Rim.Dif.",field:"defreb"}
    this.OFF_REBOUND_FIELD={title:"Rim.Off.",field:"offreb"}
    this.BALL_STEAL_FIELD={title:"Rubate",field:"ballstealed"}
    this.BALL_LOOSE_FIELD={title:"Perse",field:"ballloosed"}
    this.BLOCK_FIELD={title:"Blocchi",field:"blocks"}
    this.TURNOVER_FIELD={title:"Turnover",field:"turnover"}
    this.SHOOT_FIELD={title:"Tiri",field:"shoot"}
    this.FOULS_FIELD={title:"Falli",field:"fouls"}
    this.THREE_POINT_SHOOT_FIELD={title:"3 punti",field:"threepoints"}
    
    //server
    this.SERVERADDRESS='http://localhost:8080/statBasketServer/team'
    this.SERVERADDRESSSPRING='http://localhost:8080/team'
    
    //error message
    this.TEAM_NAME_EMPTY="Il nome della squadra non deve essere vuoto!"
    
})