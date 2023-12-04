package cat.udl.eps.softarch.myroutes.repository;

import cat.udl.eps.softarch.myroutes.domain.Route;
import cat.udl.eps.softarch.myroutes.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
@RepositoryRestResource
public interface RouteRepository extends CrudRepository<Route, Long>, PagingAndSortingRepository<Route, Long> {
    List<Route> findByCreatedBy(@Param("creator") User creator);
    List<Route> findByTitle(@Param("title") String title);
    List<Route> findByTitleContainingIgnoreCase(@Param("title") String title);
    List<Route> findByType(@Param("type") Route.Type type);
}
