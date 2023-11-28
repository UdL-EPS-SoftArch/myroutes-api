package cat.udl.eps.softarch.myroutes.steps;

import cat.udl.eps.softarch.myroutes.domain.Route;
import cat.udl.eps.softarch.myroutes.domain.RouteFollowed;
import cat.udl.eps.softarch.myroutes.domain.User;
import cat.udl.eps.softarch.myroutes.repository.RouteFollowedRepository;
import cat.udl.eps.softarch.myroutes.repository.RouteRepository;
import cat.udl.eps.softarch.myroutes.repository.UserRepository;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class DeleteRouteFollowedStepDefs {
    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RouteFollowedRepository routeFollowedRepository;
    @When("I delete RouteFollowed with a date {string}, duration {string}, levelUp {string}, levelDown {string}, by user username {string} and route title {string}")
    public void iDeleteRouteFollowedWithADateDurationLevelUpLevelDownByUserUsernameAndRouteTitle(String arg0, String arg1, String arg2, String arg3, String arg4, String arg5) {
    }

    @When("I try to delete RouteFollowed with id {long}")
    public void iTryToDeleteRouteFollowedWithUserAndRouteTitle(Long routeId) throws Throwable {
        RouteFollowed routeFollowed = RouteFollowedUtil.getRouteFollowed(routeFollowedRepository, routeId);
        stepDefs.result = stepDefs.mockMvc.perform(
                delete(routeFollowed.getUri())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @Then("I should have {int} RouteFollowed")
    public void iShouldHaveRouteFollowed(int nRouteFollowed) {

    }



    @When("I try to delete RouteFollowed with user {string} and route {string}")
    public void iTryToDeleteRouteFollowedWithUserAndRoute(String userId, String routeTitle) throws Throwable {
        User user = userRepository.findById(userId).get();
        Route route = RouteUtil.getRouteByTitle(routeRepository, routeTitle);
        RouteFollowed routeFollowed = RouteFollowedUtil.getRouteFollowed(routeFollowedRepository, route, user);
        stepDefs.result = stepDefs.mockMvc.perform(
                        delete(routeFollowed.getUri())
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }
}
