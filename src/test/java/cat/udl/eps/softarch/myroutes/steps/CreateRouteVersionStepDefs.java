package cat.udl.eps.softarch.myroutes.steps;

import cat.udl.eps.softarch.myroutes.domain.Route;
import cat.udl.eps.softarch.myroutes.domain.RouteVersion;
import cat.udl.eps.softarch.myroutes.domain.User;
import cat.udl.eps.softarch.myroutes.repository.RouteRepository;
import cat.udl.eps.softarch.myroutes.repository.RouteVersionRepository;
import cat.udl.eps.softarch.myroutes.repository.UserRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class CreateRouteVersionStepDefs {
    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private RouteVersionRepository routeVersionRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private UserRepository userRepository;

    protected ResultActions result;
    @When("I create a new route version of the route with title {string}")
    public void iCreateANewCorrectRouteVersion(String routeTitle) throws Throwable {
        Route route = routeRepository.findByTitle(routeTitle).get(0);
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


    @Given("There is a route version of a route with title {string}")
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

    @Then("The number of versions for the route with the title {string} is {int}")
    public void theNumberOfVersionsForTheRouteWithTheTitleIs(String routeTitle, int number) {
        Route route = routeRepository.findByTitle(routeTitle).get(0);
        if (route != null) {
            List<RouteVersion> routeVersions = routeVersionRepository.findByversionOf(route);
            Assert.assertEquals(number, routeVersions.size());
        }
    }
}
