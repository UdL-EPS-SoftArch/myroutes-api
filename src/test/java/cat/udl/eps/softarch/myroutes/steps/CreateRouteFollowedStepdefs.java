package cat.udl.eps.softarch.myroutes.steps;

import cat.udl.eps.softarch.myroutes.domain.Route;
import cat.udl.eps.softarch.myroutes.domain.RouteFollowed;
import cat.udl.eps.softarch.myroutes.domain.User;
import cat.udl.eps.softarch.myroutes.repository.RouteFollowedRepository;
import cat.udl.eps.softarch.myroutes.repository.RouteRepository;
import cat.udl.eps.softarch.myroutes.repository.UserRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class CreateRouteFollowedStepdefs {

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RouteFollowedRepository routeFollowedRepository;

    @When("I create a routeFollowed with date {string}, duration {string}, levelUp {string} and a levelDown {string}")
    public void iCreateARouteFollowedWithDateDurationLevelUpAndALevelDown(String date, String duration, String levelUp, String levelDown) throws Exception {
        RouteFollowed routeFollowed = RouteFollowedUtil.buildRoute(date,duration, levelUp, levelDown);
        Iterable<Route> routesList = routeRepository.findAll();
        Iterable<User> usersList = userRepository.findAll();
        Route route = routesList.iterator().next();
        User user = usersList.iterator().next();
        routeFollowed.setFollows(route);
        routeFollowed.setCreatedBy(user);
        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/routeFolloweds")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(routeFollowed))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("I don't have any RouteFollowed")
    public void iDonTHaveAnyRouteFollowed() {
        routeFollowedRepository.deleteAll();
    }

    @Given("There is a routeFollowed with date {string}, duration {string}, levelUp {string} and a levelDown {string}, by user username {string} and route title {string}")
    public void thereIsARouteFollowedWithDateDurationLevelUpAndALevelDownByUserUsernameAndRouteTitle(String date, String duration, String levelUp, String levelDown, String user, String route) {
        RouteFollowed routeFollowed = RouteFollowedUtil.buildRoute(date,duration,levelUp,levelDown);
        routeFollowed.setCreatedBy(userRepository.findById(user).get());
        routeFollowed.setFollows(routeRepository.findByTitle(route).get(0));
        routeFollowedRepository.save(routeFollowed);
    }
}
