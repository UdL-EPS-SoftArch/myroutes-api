package cat.udl.eps.softarch.myroutes.steps;

import cat.udl.eps.softarch.myroutes.domain.Coordinate;
import cat.udl.eps.softarch.myroutes.domain.Route;
import cat.udl.eps.softarch.myroutes.domain.RouteVersion;
import cat.udl.eps.softarch.myroutes.domain.User;
import cat.udl.eps.softarch.myroutes.repository.CoordinateRepository;
import cat.udl.eps.softarch.myroutes.repository.RouteRepository;
import cat.udl.eps.softarch.myroutes.repository.RouteVersionRepository;
import cat.udl.eps.softarch.myroutes.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class RouteVersionStepDefs {
    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private RouteVersionRepository routeVersionRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private UserRepository userRepository;

    @When("I create a new correct RouteVersion")
    public void iCreateANewCorrectRouteVersion() throws Throwable {

        Route route = routeRepository.findByTitle("testRoute").get(0);
        RouteVersion routeVersion = new RouteVersion();
        routeVersion.setVersionOf(route);

        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/routeVersions")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(routeVersion))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                                .andDo(print());
    }


    @Given("I create a new correct RouteVersion whit a Route title {string}")
    public void iCreateANewCorrectRouteVersionWhitARouteTitle(String routeTitle) {
        Route route = routeRepository.findByTitle(routeTitle).get(0);
        if (route != null) {
            RouteVersion routeVersion = new RouteVersion();
            Optional<User> user = userRepository.findById("username");
            if(user != null)
                routeVersion.setCreatedBy(user.get());
            routeVersion.setVersionOf(route);
            routeVersion.setCreationDate(ZonedDateTime.now());
            routeVersionRepository.save(routeVersion);
        }
    }
}
