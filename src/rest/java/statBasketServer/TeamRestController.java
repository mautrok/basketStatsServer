package statBasketServer;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import dto.PlayersDTO;
import it.mauro.basketstatjpa.Games;
import it.mauro.basketstatjpa.GamesPK;
import it.mauro.basketstatjpa.History;
import it.mauro.basketstatjpa.Players;
import it.mauro.basketstatjpa.Teams;
import risposte.RispostaHistory;
import risposte.RispostaListaSquadre;
import risposte.RispostaSquadra;
import statBasketJPA.GamesRepository;
import statBasketJPA.HistoryRepository;
import statBasketJPA.PlayersRepository;
import statBasketJPA.TeamRepository;
import util.JsonObjectProcess;

@Controller
@CrossOrigin
@RequestMapping("/team/")
public class TeamRestController {
	
	private final String PLAYERS="players";
	
    private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PlayersRepository playerRepository;
	@Autowired
	private TeamRepository teamRepository;
	@Autowired
	private HistoryRepository historyRepository;
	@Autowired
	private GamesRepository gamesRepository;
	
	@RequestMapping(value="/getteams", method=RequestMethod.GET,produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public HttpEntity<RispostaListaSquadre> getTeams() throws Exception {
		RispostaListaSquadre response=new RispostaListaSquadre();
		
		List<Teams>teamsList=teamRepository.selectAll();
		
		response.setTeamsList(teamsList);
	
		return new ResponseEntity<RispostaListaSquadre>(response,HttpStatus.OK);
	}
	
	@RequestMapping(value="{team}", method=RequestMethod.GET,produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public HttpEntity<RispostaSquadra> getTeam(@PathVariable String team) throws Exception {
		RispostaSquadra response=new RispostaSquadra();
		
		List<Players>playerList=playerRepository.selectByTeamAll(team);
		
		List<PlayersDTO> list=new ArrayList<PlayersDTO>();
		for(Players p: playerList){
			PlayersDTO pDTO= new PlayersDTO();
			pDTO.setName(p.getId().getPlayer());
			pDTO.setNumber(p.getNumber());
			pDTO.setTeam(p.getId().getTeam());
			list.add(pDTO);
		}
		response.setPlayerList(list);
	
		return new ResponseEntity<RispostaSquadra>(response,HttpStatus.OK);
	}
	
	@RequestMapping(value="saveteam/{team}", method=RequestMethod.POST,produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public HttpEntity<RispostaSquadra> saveTeam(@RequestBody(required=true) String body,@PathVariable("team")String team) throws Exception {
		RispostaSquadra response=new RispostaSquadra();
        JSONObject json=new JSONObject(body);
        List<Players> lista=JsonObjectProcess.JsonToPlayersJPA(json.getJSONArray(PLAYERS),team);
        
        for(Players p:lista){
        	log.debug(p.getId().getPlayer());
    		playerRepository.save(p);
    		log.debug(p.getId().getPlayer()+" salvato");
         }

    	Teams t=new Teams();
    	t.setTeam(team);
    	teamRepository.save(t);
    	
    	response.setMessage(team+" salvato correttamente");
		return new ResponseEntity<RispostaSquadra>(response,HttpStatus.OK);
	   }
	
	@RequestMapping(value="savegame/{team}", method=RequestMethod.POST,produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public HttpEntity<RispostaHistory> saveGame(@RequestBody(required=true) String body,@PathVariable("team")String team,@RequestParam(value="home",required=true)boolean home) throws Exception {
		RispostaHistory response=new RispostaHistory();

		JSONObject json=new JSONObject(body);
		
        List<History> lista=JsonObjectProcess.JsonToHistoryJPA(json.getJSONArray(PLAYERS),team,home);
        
        for(History h:lista){
        	log.debug(h.getId().getPlayer());
    		historyRepository.save(h);
    		log.debug(h.getId().getPlayer()+" salvato");
         }
        

    	Games g=new Games();
    	GamesPK gKey=new GamesPK();
    	g.setHome(home);
    	gKey.setDate(new Date());
    	gKey.setTeam(team);
    	g.setId(gKey);
    	gamesRepository.save(g);
    	
    	response.setMessage(team+" salvato correttamente");
		return new ResponseEntity<RispostaHistory>(response,HttpStatus.OK);
	   }
	
}
