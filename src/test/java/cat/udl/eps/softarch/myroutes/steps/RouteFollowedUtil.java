package cat.udl.eps.softarch.myroutes.steps;

import cat.udl.eps.softarch.myroutes.domain.Route;
import cat.udl.eps.softarch.myroutes.domain.RouteFollowed;
import cat.udl.eps.softarch.myroutes.domain.User;
import cat.udl.eps.softarch.myroutes.repository.RouteFollowedRepository;
import cat.udl.eps.softarch.myroutes.repository.RouteRepository;
import cat.udl.eps.softarch.myroutes.repository.UserRepository;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

public class RouteFollowedUtil {
    public static RouteFollowed buildRoute(String date, String duration, String levelUp, String levelDown){
        RouteFollowed route = new RouteFollowed();
        route = setValuesToRoute(new RouteFollowed(),date, duration, levelUp, levelDown);
        return route;
    }

    public static RouteFollowed setValuesToRoute(RouteFollowed route, String date, String duration, String levelUp, String levelDown){
        if(!date.isEmpty()) {
            ZonedDateTime creationDate = ZonedDateTime.parse(date);
            route.setDate(creationDate);
        }

        if(!duration.isBlank())
            route.setDuration(Duration.parse(duration));

        if(!levelUp.isBlank())
            route.setLevelUp(levelUp);

        if(!levelDown.isBlank())
            route.setLevelDown(levelDown);
        return route;
    }

    public static RouteFollowed getRouteFollowed(RouteFollowedRepository rfRepo, Route route, User user){
        List<RouteFollowed> routeFollowedList = rfRepo.findByCreatedByAndRouteOrigin(user, route);
        return routeFollowedList.get(0);
    }
}
