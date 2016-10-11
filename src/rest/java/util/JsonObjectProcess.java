package util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import dto.PlayersDTO;

public class JsonObjectProcess {

	private static String PLAYER="player";
	private static String NAME="name";
	private final static String NUMBER="number";
	
	public static List<PlayersDTO> JsonToPlayers(JSONArray obj,String team) {
        
		List<PlayersDTO> lista=new ArrayList<>();
		for(int i=0;i<obj.length();i++){
			PlayersDTO player=new PlayersDTO();
			JSONObject pls=(JSONObject) obj.getJSONObject(i).get(PLAYER);
			player.setName(pls.get(NAME)==null?"":(String)pls.get(NAME));
			player.setNumber(pls.get(NUMBER)==null?null:(int)pls.get(NUMBER));
			player.setTeam(team);
		}
		
		return lista;
	}
}
