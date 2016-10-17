package statBasketJPA;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import it.mauro.basketstatjpa.History;
import it.mauro.basketstatjpa.HistoryPK;
import it.mauro.basketstatjpa.Teams;

public interface HistoryRepository extends BaseRepository<History, HistoryPK>{
	
	@Query("SELECT h FROM History h")
	List<History> selectAll();
}
