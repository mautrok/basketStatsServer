package statBasketJPA;
 
import java.io.Serializable;
import java.util.Optional;
 
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;
 

public interface BaseRepository<T, ID extends Serializable> extends Repository<T, ID> {
 
   /**
    * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the
    * entity instance completely.
    * 
    * @param entity
    * @return the saved entity
    */
   <S extends T> S save(S entity);
 
   /**
    * Saves all given entities.
    * 
    * @param entities
    * @return the saved entities
    * @throws IllegalArgumentException in case the given entity is {@literal null}.
    */
   <S extends T> Iterable<S> save(Iterable<S> entities);
 
   /**
    * Retrieves an entity by its id.
    * 
    * @param id must not be {@literal null}.
    * @return the entity with the given id or {@literal null} if none found
    * @throws IllegalArgumentException if {@code id} is {@literal null}
    */
   @Transactional(readOnly=true)
   Optional<T> findOne(ID id);
 
   /**
    * Returns whether an entity with the given id exists.
    * 
    * @param id must not be {@literal null}.
    * @return true if an entity with the given id exists, {@literal false} otherwise
    * @throws IllegalArgumentException if {@code id} is {@literal null}
    */
   @Transactional(readOnly=true)
   boolean exists(ID id);
 
   /**
    * Returns all instances of the type.
    * 
    * @return all entities
    */
   @Transactional(readOnly=true)
   Iterable<T> findAll();
 
   /**
    * Returns all instances of the type with the given IDs.
    * 
    * @param ids
    * @return
    */
   @Transactional(readOnly=true)
   Iterable<T> findAll(Iterable<ID> ids);
 
   /**
    * Returns the number of entities available.
    * 
    * @return the number of entities
    */
   @Transactional(readOnly=true)
   long count();
 
   /**
    * Deletes the entity with the given id.
    * 
    * @param id must not be {@literal null}.
    * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
    */
   void delete(ID id);
 
   /**
    * Deletes a given entity.
    * 
    * @param entity
    * @throws IllegalArgumentException in case the given entity is {@literal null}.
    */
   void delete(T entity);
 
   /**
    * Deletes the given entities.
    * 
    * @param entities
    * @throws IllegalArgumentException in case the given {@link Iterable} is {@literal null}.
    */
   void delete(Iterable<? extends T> entities);
 
   /**
    * Deletes all entities managed by the repository.
    */
   void deleteAll();
 
   /**
    * Returns all entities sorted by the given options.
    * 
    * @param sort
    * @return all entities sorted by the given options
    */
   Iterable<T> findAll(Sort sort);
 
   /**
    * Returns a {@link Page} of entities meeting the paging restriction provided in the {@code Pageable} object.
    * 
    * @param pageable
    * @return a page of entities
    */
   Page<T> findAll(Pageable pageable);
 
}