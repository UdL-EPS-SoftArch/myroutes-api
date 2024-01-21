package cat.udl.eps.softarch.myroutes.repository;

import cat.udl.eps.softarch.myroutes.domain.Route;
import cat.udl.eps.softarch.myroutes.domain.RouteVersion;
import cat.udl.eps.softarch.myroutes.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
@RepositoryRestResource
public interface RouteVersionRepository extends CrudRepository<RouteVersion, Long>, PagingAndSortingRepository<RouteVersion, Long> {
    List<RouteVersion> findByversionOf(Route route);
    List<RouteVersion> findByCreatedBy(@Param("creator") User creator);
}
