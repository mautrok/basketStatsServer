package util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import dto.PlayersDTO;
import it.mauro.basketstatjpa.History;
import it.mauro.basketstatjpa.HistoryPK;
import it.mauro.basketstatjpa.Players;
import it.mauro.basketstatjpa.PlayersPK;

public class JsonObjectProcess {

	private static String PLAYER="player";
	private static String NAME="name";
	private final static String ASSIST="assist";
	private final static String BALLLOOSE="ballloos";
	private final static String BALLSTEAL="ballsteal";
	private final static String BLOCK="block";
	private final static String DEFREB="defreb";
	private final static String FOUL="foul";
	private final static String NUMBER="number";
	private final static String OFFREB="offreb";
	private final static String POINT="point";
	private final static String THREEPOINT="threepoint";
	private final static String TWOPOINT="twopoint";
	
	
	public static List<PlayersDTO> JsonToPlayers(JSONArray obj,String team) {
        
		List<PlayersDTO> lista=new ArrayList<>();
		for(int i=0;i<obj.length();i++){
			PlayersDTO player=new PlayersDTO();
			JSONObject pls=(JSONObject) obj.getJSONObject(i);
			player.setName(pls.get(NAME)==null?"":(String)pls.get(NAME));
			player.setNumber(pls.get(NUMBER)==null?null:Integer.parseInt((String)pls.get(NUMBER)));
			player.setTeam(team);
		}
		
		return lista;
	}
	
	public static List<Players> JsonToPlayersJPA(JSONArray obj,String team) {
        
		List<Players> lista=new ArrayList<>();
		for(int i=0;i<obj.length();i++){
			Players player=new Players();
			JSONObject pls=(JSONObject) obj.getJSONObject(i);
			String playerName=pls.get(NAME)==null?"":(String)pls.get(NAME);
			player.setNumber(pls.get(NUMBER)==null?null:Integer.parseInt(pls.get(NUMBER).toString()));
			PlayersPK key=new PlayersPK();
			key.setTeam(team);
			key.setPlayer(playerName);
			player.setId(key);
			lista.add(player);
		}
		return lista;
	}
	
	public static List<History> JsonToHistoryJPA(JSONArray obj,String team,boolean home) {
        
		List<History> lista=new ArrayList<>();
		for(int i=0;i<obj.length();i++){
			History history=new History();
			JSONObject pls=(JSONObject) obj.getJSONObject(i);
			String playerName=pls.get(NAME)==null?"":(String)pls.get(NAME);
			HistoryPK key=new HistoryPK();
			key.setTeam(team);
			key.setPlayer(playerName);
			history.setId(key);
			if(pls.has(ASSIST)==true)
				history.setAssist(Integer.parseInt(pls.get(ASSIST).toString()));
			if(pls.has(BALLLOOSE)==true)
				history.setBallloose(Integer.parseInt(pls.get(BALLLOOSE).toString()));
			if(pls.has(BALLSTEAL)==true)
				history.setBallsteal(Integer.parseInt(pls.get(BALLSTEAL).toString()));
			if(pls.has(BLOCK)==true)
				history.setBlock(Integer.parseInt(pls.get(BLOCK).toString()));
			Date today=new Date();
			history.setDate(today);
			if(pls.has(DEFREB)==true)
				history.setDefreb(Integer.parseInt(pls.get(DEFREB).toString()));
			if(pls.has(FOUL)==true)
				history.setFoul(Integer.parseInt(pls.get(FOUL).toString()));
			if(pls.has(NUMBER)==true)
				history.setNumber(Integer.parseInt(pls.get(NUMBER).toString()));
			if(pls.has(OFFREB)==true)
				history.setOffreb(Integer.parseInt(pls.get(OFFREB).toString()));
			if(pls.has(POINT)==true)
				history.setPoint(Integer.parseInt(pls.get(POINT).toString()));
			if(pls.has(THREEPOINT)==true)
				history.setThreepoint(Integer.parseInt(pls.get(THREEPOINT).toString()));
			if(pls.has(TWOPOINT)==true)
				history.setTwopoint(Integer.parseInt(pls.get(TWOPOINT).toString()));
			lista.add(history);
		}
		return lista;
	}
	
}
