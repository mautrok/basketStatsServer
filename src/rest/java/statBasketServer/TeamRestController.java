package statBasketServer;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.PlayersDTO;
import it.mauro.basketstatjpa.Players;
import risposte.RispostaSquadra;
import statBasketJPA.PlayersRepository;
import util.JsonObjectProcess;

@Controller
@RequestMapping("/team/")
public class TeamRestController {
	
	private final String PLAYERS="players";
	
	@Autowired
	private PlayersRepository playerRepository;
	
	@RequestMapping(value="{team}", method=RequestMethod.GET,produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public HttpEntity<RispostaSquadra> getTeam(@PathVariable String team) throws Exception {
		RispostaSquadra response=new RispostaSquadra();
		
		List<Players>playerList=playerRepository.selectByTeamAll(team);
		
		List<PlayersDTO> list=new ArrayList<PlayersDTO>();
		for(Players p: playerList){
			PlayersDTO pDTO= new PlayersDTO();
			BeanUtils.copyProperties(p, pDTO);
			pDTO.setTeam(p.getId().getTeam());
			list.add(pDTO);
		}
		response.setPlayerList(list);
	
		return new ResponseEntity<RispostaSquadra>(response,HttpStatus.OK);
	   }
	
	/*@RequestMapping(value="/savegame", method=RequestMethod.POST,produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public HttpEntity<RispostaSquadra> savegame(@PathVariable String team) throws Exception {
	
		return new ResponseEntity<RispostaSquadra>(response,HttpStatus.OK);
	}*/
	
	@CrossOrigin
	@RequestMapping(value="saveteam/{team}", method=RequestMethod.POST,produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public HttpEntity<RispostaSquadra> saveTeam(@RequestBody(required=true) String body,@PathVariable("team")String team) throws Exception {
		RispostaSquadra response=new RispostaSquadra();
        JSONObject json=new JSONObject(body);
        List<PlayersDTO> lista=JsonObjectProcess.JsonToPlayers(json.getJSONArray(PLAYERS),team);
        
        response.setPlayerList(lista);
        
		return new ResponseEntity<RispostaSquadra>(response,HttpStatus.OK);
	   }
	
}
