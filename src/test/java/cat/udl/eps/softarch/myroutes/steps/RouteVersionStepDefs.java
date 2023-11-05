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
    private CreateRouteStepdefs createRouteStepdefs;

    @Autowired
    private RouteVersionRepository routeVersionRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private UserRepository userRepository;

    @When("I create a new correct RouteVersion")
    public void iCreateANewCorrectRouteVersion() throws Throwable {
        Route route = RouteUtil.buildRoute("Route1","Difficult",Route.Type.Walking.toString(), ZonedDateTime.now().toString());
        route.setCreatedBy(userRepository.findById("username").get());
        routeRepository.save(route);

        RouteVersion routeVersion = new RouteVersion();
        assert route.getId() != null;
        route = routeRepository.findById(route.getId()).get();
        routeVersion.setVersionOf(route);
        // routeVersion.setCreatedBy(userRepository.findById("username").get());
        // routeVersion.setCreationDate(ZonedDateTime.now());

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
