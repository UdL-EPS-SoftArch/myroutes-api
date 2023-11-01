package cat.udl.eps.softarch.myroutes.repository;

import cat.udl.eps.softarch.myroutes.domain.Waypoint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;


@RepositoryRestResource
public interface WaypointRepository extends CrudRepository<Waypoint, Long>, PagingAndSortingRepository<Waypoint, Long> {
    Optional<Waypoint> findByTitle(@Param("title") String title);
    List<Waypoint> findByTitleContaining(@Param("text") String text);
}
