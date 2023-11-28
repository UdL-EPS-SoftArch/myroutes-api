package cat.udl.eps.softarch.myroutes.steps;

import cat.udl.eps.softarch.myroutes.domain.Route;
import cat.udl.eps.softarch.myroutes.domain.RouteFollowed;
import cat.udl.eps.softarch.myroutes.domain.User;
import cat.udl.eps.softarch.myroutes.repository.RouteFollowedRepository;
import cat.udl.eps.softarch.myroutes.repository.RouteRepository;
import cat.udl.eps.softarch.myroutes.repository.UserRepository;
import io.cucumber.java.en.When;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class UpdateRouteFollowedStepDefs {

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RouteFollowedRepository routeFollowedRepository;
    @When("I update a routeFollowed with date {string}, duration {string}, levelUp {string} and a levelDown {string}, by user username {string} and route title {string}")
    public void iUpdateARouteFollowedWithDateDurationLevelUpAndALevelDownByUserUsernameAndRouteTitle(String date, String duration, String levelUp, String levelDown, String userId, String routeTitle) throws Throwable {
        Route route = RouteUtil.getRouteByTitle(routeRepository,routeTitle);
        User user = userRepository.findById(userId).get();
        RouteFollowed routeFollowed = RouteFollowedUtil.getRouteFollowed(routeFollowedRepository,route,user);
        if(routeFollowed == null)
            routeFollowed = ((List<RouteFollowed>) routeFollowedRepository.findAll()).get(0);
        stepDefs.result = stepDefs.mockMvc.perform(patch(routeFollowed.getUri())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new JSONObject().put("date", routeFollowed.getDate()).put("duration", routeFollowed.getDuration()).put("levelUp",levelUp).put("levelDown",levelDown).toString())
                        .characterEncoding(StandardCharsets.UTF_8)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());

        stepDefs.result.andReturn().getResponse().getHeader("Location");
    }
}
