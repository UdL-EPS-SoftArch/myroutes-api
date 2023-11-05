package cat.udl.eps.softarch.myroutes.steps;

import cat.udl.eps.softarch.myroutes.domain.Coordinate;
import cat.udl.eps.softarch.myroutes.domain.Route;
import cat.udl.eps.softarch.myroutes.domain.RouteVersion;
import cat.udl.eps.softarch.myroutes.domain.User;
import cat.udl.eps.softarch.myroutes.repository.CoordinateRepository;
import cat.udl.eps.softarch.myroutes.repository.RouteRepository;
import cat.udl.eps.softarch.myroutes.repository.RouteVersionRepository;
import cat.udl.eps.softarch.myroutes.repository.UserRepository;
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
        User userAuthenticated = userRepository.findById(AuthenticationStepDefs.currentUsername).get();
        Route route = new Route();
        route.setTitle("Route1");
        route.setDescription("Difficult");
        route.setType(Route.Type.Walking);
        route.setCreatedBy(userAuthenticated);
        route.setCreationDate(ZonedDateTime.parse("2023-10-25T17:27:00Z"));
        routeRepository.save(route);
        RouteVersion routeVersion = new RouteVersion();
        routeVersion.setVersionOf(route);
        routeVersion.setCreatedBy(userAuthenticated);

        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/routeVersions")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(routeVersion))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }
}
