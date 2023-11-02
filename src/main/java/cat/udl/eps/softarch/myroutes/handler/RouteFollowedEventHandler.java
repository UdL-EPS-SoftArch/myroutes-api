package cat.udl.eps.softarch.myroutes.handler;

import cat.udl.eps.softarch.myroutes.domain.Route;
import cat.udl.eps.softarch.myroutes.domain.RouteFollowed;
import cat.udl.eps.softarch.myroutes.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.ZonedDateTime;

@RepositoryEventHandler
public class RouteFollowedEventHandler {
    final Logger logger = LoggerFactory.getLogger(RouteFollowed.class);

    @HandleBeforeCreate
    public void handleRoutePreCreate(RouteFollowed routeFollowed) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Route route = (Route) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        routeFollowed.setCreatedBy(user);
        routeFollowed.setRouteOrigin(route);
        routeFollowed.setDate(ZonedDateTime.now());
        logger.info("Creation of new routeFollowed: {}", route);
    }
}
