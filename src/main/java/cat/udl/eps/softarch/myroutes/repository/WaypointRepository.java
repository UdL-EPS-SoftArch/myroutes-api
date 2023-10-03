package cat.udl.eps.softarch.myroutes.repository;

import cat.udl.eps.softarch.myroutes.domain.Waypoint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface WaypointRepository extends CrudRepository<Waypoint, Long>, PagingAndSortingRepository<Waypoint, Long> {
}
