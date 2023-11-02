package cat.udl.eps.softarch.myroutes.steps;

import cat.udl.eps.softarch.myroutes.domain.Route;
import cat.udl.eps.softarch.myroutes.domain.RouteFollowed;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Date;

public class RouteFollowedUtil {
    public static RouteFollowed buildRoute(String date, String duration, String levelUp, String levelDown){
        return setValuesToRoute(new RouteFollowed(),date, duration, levelUp, levelDown);
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
}
