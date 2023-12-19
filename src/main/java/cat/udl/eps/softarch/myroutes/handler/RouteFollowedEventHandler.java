package cat.udl.eps.softarch.myroutes.handler;

import cat.udl.eps.softarch.myroutes.domain.Route;
import cat.udl.eps.softarch.myroutes.domain.RouteFollowed;
import cat.udl.eps.softarch.myroutes.domain.User;
import cat.udl.eps.softarch.myroutes.repository.RouteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;

@Component
@RepositoryEventHandler
public class RouteFollowedEventHandler {
    final Logger logger = LoggerFactory.getLogger(RouteFollowed.class);
    final RouteRepository routeRepository;

    public RouteFollowedEventHandler(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }


    @HandleBeforeCreate
    public void handleRouteFollowedPreCreate(RouteFollowed routeFollowed) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        routeFollowed.setCreatedBy(user);
        List<Route> routeList = routeRepository.findByCreatedBy(user);
        if(!routeList.isEmpty())
            routeFollowed.setFollows(routeList.get(0).getUri());
        routeFollowed.setDate(ZonedDateTime.now());
        logger.info("Creation of new routeFollowed: {}", routeFollowed);
    }

}
