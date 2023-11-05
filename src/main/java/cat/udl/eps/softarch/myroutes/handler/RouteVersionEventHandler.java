package cat.udl.eps.softarch.myroutes.handler;

import cat.udl.eps.softarch.myroutes.domain.Route;
import cat.udl.eps.softarch.myroutes.domain.RouteVersion;
import cat.udl.eps.softarch.myroutes.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
@RepositoryEventHandler
public class RouteVersionEventHandler {

    final Logger logger = LoggerFactory.getLogger(Route.class);

    @HandleBeforeCreate
    public void handleRouteVersionPreCreate(RouteVersion routeVersion) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        routeVersion.setCreatedBy(user);
        routeVersion.setCreationDate(ZonedDateTime.now());
        logger.info("Creation of new routeVersion: {}", routeVersion);
    }

}
