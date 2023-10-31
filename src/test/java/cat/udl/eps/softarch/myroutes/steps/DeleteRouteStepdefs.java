package cat.udl.eps.softarch.myroutes.steps;

import cat.udl.eps.softarch.myroutes.domain.Route;
import cat.udl.eps.softarch.myroutes.repository.RouteRepository;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

public class DeleteRouteStepdefs{
    private CreateRouteStepdefs createSteps;
    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private RouteRepository routeRepository;

    @When("I try to delete Route with title {string}")
    public void iTryToDeleteRouteWithTitle(String title) throws Exception {
        Route route = RouteUtil.getRouteByTitle(routeRepository,title);
        stepDefs.result = stepDefs.mockMvc.perform(
                delete("/routes/"+(route == null?"":route.getId()))
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate())
        );
    }
}
