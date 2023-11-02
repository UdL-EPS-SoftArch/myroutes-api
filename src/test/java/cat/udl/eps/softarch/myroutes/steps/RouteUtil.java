package cat.udl.eps.softarch.myroutes.steps;

import cat.udl.eps.softarch.myroutes.domain.Route;
import cat.udl.eps.softarch.myroutes.repository.RouteRepository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public class RouteUtil {
    public static Route buildRoute(String title, String description, String type, String sCreationDate){
        Route route = new Route();
        route = setValuesToRoute(route,title,description,type,sCreationDate);
        return route;
    }

    public static Route updateRoute(RouteRepository routeRepository, String title, String description, String type, String sCreationDate){
        Route route = getRouteByTitle(routeRepository,title);
        if(route == null)
            return null;
        else
            route = RouteUtil.setValuesToRoute(route,title,description,type,sCreationDate);
        return route;
    }

    public static Route setValuesToRoute(Route route, String title, String description, String type, String sCreationDate){
        if(!sCreationDate.isEmpty()){
            ZonedDateTime creationDate = ZonedDateTime.parse(sCreationDate);
            route.setCreationDate(creationDate);
        }
        if(!title.isEmpty())
            route.setTitle(title);
        if(!description.isEmpty())
            route.setDescription(description);
        if(!type.isEmpty()){
            try{
                Route.Type value = Route.Type.valueOf(type);
                route.setType(value);
            }catch (Exception e){
                System.out.println("Error on Type parse");
            }
        }
        return route;
    }

    public static Route getRouteByTitle(RouteRepository routeRepository, String title){
        List<Route> routes = (List<Route>) routeRepository.findByTitle(title);
        Optional<Route> route_ = null;
        if(!routes.isEmpty())
            route_ = routes.stream().findFirst();
        if(!route_.isPresent())
            return  null;
        else
            return route_.get();
    }

}
