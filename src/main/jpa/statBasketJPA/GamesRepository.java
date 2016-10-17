package statBasketJPA;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import it.mauro.basketstatjpa.Games;
import it.mauro.basketstatjpa.GamesPK;

public interface GamesRepository extends BaseRepository<Games, GamesPK>{
	
	@Query("SELECT g FROM Games g")
	List<Games> selectAll();
}
