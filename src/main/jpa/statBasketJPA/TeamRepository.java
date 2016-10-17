package statBasketJPA;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import it.mauro.basketstatjpa.Teams;

public interface TeamRepository extends BaseRepository<Teams, String>{
	
	@Query("SELECT t FROM Teams t")
	List<Teams> selectAll();
}
