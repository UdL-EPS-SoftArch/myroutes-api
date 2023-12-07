package cat.udl.eps.softarch.myroutes.repository;

import cat.udl.eps.softarch.myroutes.domain.Coordinate;
import cat.udl.eps.softarch.myroutes.domain.RouteVersion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CoordinateRepository extends CrudRepository<Coordinate, Long>, PagingAndSortingRepository<Coordinate, Long> {
    List<Coordinate> findByRouteVersion(@Param("routeVersion") RouteVersion routeVersion);
    List<Coordinate> findByCoordinate(@Param("coordinate") String coordinate);

}
