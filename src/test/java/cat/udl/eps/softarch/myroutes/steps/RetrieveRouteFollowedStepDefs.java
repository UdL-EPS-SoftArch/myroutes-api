package cat.udl.eps.softarch.myroutes.steps;

import cat.udl.eps.softarch.myroutes.domain.Route;
import cat.udl.eps.softarch.myroutes.domain.RouteFollowed;
import cat.udl.eps.softarch.myroutes.domain.User;
import cat.udl.eps.softarch.myroutes.domain.Waypoint;
import cat.udl.eps.softarch.myroutes.repository.RouteFollowedRepository;
import cat.udl.eps.softarch.myroutes.repository.RouteRepository;
import cat.udl.eps.softarch.myroutes.repository.UserRepository;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class RetrieveRouteFollowedStepDefs {
    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RouteFollowedRepository routeFollowedRepository;
    @When("I try to retrieve a RouteFollowed with user {string} and route {string}")
    public void iTryToRetrieveARouteFollowedWithUserAndRoute(String userId, String routeTitle) throws Throwable {
        Route route = RouteUtil.getRouteByTitle(routeRepository,routeTitle);
        User user = userRepository.findById(userId).get();
        RouteFollowed routeFollowed = RouteFollowedUtil.getRouteFollowed(routeFollowedRepository,route,user);
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/routeFolloweds/{id}", routeFollowed.getId())
                        .with(AuthenticationStepDefs.authenticate())
                        .accept(MediaType.APPLICATION_JSON));
    }

    @When("I try to retrieve a routeFollowed with user {string} and route {string}")
    public void iTryToRetrieveAWaypointWithUserAndRoute(String userId, String routeTitle) throws Throwable {
        Route route = RouteUtil.getRouteByTitle(routeRepository,routeTitle);
        User user = userRepository.findById(userId).get();
        Optional<RouteFollowed> optionalRouteFollowed = Optional.ofNullable(RouteFollowedUtil.getRouteFollowed(routeFollowedRepository, route, user));
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/waypoints/{id}", optionalRouteFollowed.isPresent() ? optionalRouteFollowed.get().getId() : "999")
                        .with(AuthenticationStepDefs.authenticate())
                        .accept(MediaType.APPLICATION_JSON));
    }
}
