package statBasketJPA;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.mauro.basketstatjpa.Players;
import it.mauro.basketstatjpa.PlayersPK;
import it.mauro.basketstatjpa.Teams;

public interface PlayersRepository extends BaseRepository<Players, PlayersPK>{
	
	@Query("SELECT p FROM Players p")
	List<Players> selectAll();
	
	@Query("SELECT p FROM Players p where p.id.team=:team")
	List<Players> selectByTeamAll(@Param("team")String team);
}
