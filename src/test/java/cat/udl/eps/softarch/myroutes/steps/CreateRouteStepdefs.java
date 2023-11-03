package cat.udl.eps.softarch.myroutes.steps;

import cat.udl.eps.softarch.myroutes.domain.Route;
import cat.udl.eps.softarch.myroutes.domain.User;
import cat.udl.eps.softarch.myroutes.repository.RouteRepository;
import cat.udl.eps.softarch.myroutes.repository.UserRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class CreateRouteStepdefs{

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private UserRepository userRepository;

    @When("I create a route with a title {string}, description {string}, type {string} and a creationDate {string}")
    public void iCreateARouteWithATitleDescriptionTypeAndACreationDate(String title, String description, String type, String creationDate) throws Exception {
        Route route = RouteUtil.buildRoute(title,description,type,creationDate);
        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/routes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(route))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                                .andDo(print());
            }

    @And("I don't have any route")
    public void iDonTHaveAnyRoute() {
        routeRepository.deleteAll();
    }


    @Given("There is a route with a title {string}, description {string}, type {string} and a creationDate {string} by user username {string}")
    public void thereIsARouteWithATitleDescriptionTypeAndACreationDateByUserUsernameAndPassword(String title, String description, String type, String creationDate, String username) {
        Route route = RouteUtil.buildRoute(title,description,type,creationDate);
        route.setCreatedBy(userRepository.findById(username).get());
        routeRepository.save(route);
    }
}
