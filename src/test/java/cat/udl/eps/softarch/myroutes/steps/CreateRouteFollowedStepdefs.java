package cat.udl.eps.softarch.myroutes.steps;

import cat.udl.eps.softarch.myroutes.domain.Route;
import cat.udl.eps.softarch.myroutes.domain.RouteFollowed;
import cat.udl.eps.softarch.myroutes.domain.User;
import cat.udl.eps.softarch.myroutes.repository.RouteFollowedRepository;
import cat.udl.eps.softarch.myroutes.repository.RouteRepository;
import cat.udl.eps.softarch.myroutes.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.ZonedDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class CreateRouteFollowedStepdefs {

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private UserRepository userRepository;

    @When("I create a route-followed with date {string}, duration {string}, levelUp {string} and a levelDown {string}")
    public void iCreateARoutefollowedWithDateDurationLevelupAndALeveldown(String date, String duration, String levelUp, String levelDown) throws Exception {
        RouteFollowed route = RouteFollowedUtil.buildRoute(date,duration, levelUp, levelDown);
        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/routeFolloweds")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(route))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }
}
