package cat.udl.eps.softarch.myroutes.repository;

import cat.udl.eps.softarch.myroutes.domain.RouteFollowed;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RouteFollowedRepository extends CrudRepository<RouteFollowed, String>, PagingAndSortingRepository<RouteFollowed, String> {
}
