package risposte;

import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import dto.PlayersDTO;
import it.mauro.basketstatjpa.Teams;

public class RispostaListaSquadre extends RispostaGenerica{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Teams> teamsList;
	public List<Teams> getTeamsList() {
		return teamsList;
	}
	public void setTeamsList(List<Teams> teamsList) {
		this.teamsList = teamsList;
	}

	
}
