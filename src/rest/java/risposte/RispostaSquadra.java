package risposte;

import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import dto.PlayersDTO;

public class RispostaSquadra extends RispostaGenerica{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<PlayersDTO> playerList;

	public List<PlayersDTO> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(List<PlayersDTO> playerList) {
		this.playerList = playerList;
	}
	
	
}
