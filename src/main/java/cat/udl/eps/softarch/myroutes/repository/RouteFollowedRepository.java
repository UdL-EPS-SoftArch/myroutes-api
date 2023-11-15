package cat.udl.eps.softarch.myroutes.repository;

import cat.udl.eps.softarch.myroutes.domain.Route;
import cat.udl.eps.softarch.myroutes.domain.RouteFollowed;
import cat.udl.eps.softarch.myroutes.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RouteFollowedRepository extends CrudRepository<RouteFollowed, Long>, PagingAndSortingRepository<RouteFollowed, Long> {
    List<RouteFollowed> findByIdContaining(@Param("long") Long id);
    List<Route> findByCreatedBy(@Param("creator") User creator);

    List<User> findByRouteOrigin(@Param("origin") Route origin);
}
