package cat.udl.eps.softarch.myroutes.steps;
import cat.udl.eps.softarch.myroutes.domain.Waypoint;
import cat.udl.eps.softarch.myroutes.repository.WaypointRepository;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class RetrieveWaypointStepDefs {
    @Autowired
    private StepDefs stepDefs;
    @Autowired
    private WaypointRepository waypointRepository;

    @When("^I try to retrieve (\\d+) Waypoint$")
    public void retrieveWaypoint(int id) throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/waypoint/{id}", id)
                        .with(AuthenticationStepDefs.authenticate())
                        .accept(MediaType.APPLICATION_JSON));
    }

    @When("I try to retrieve a Waypoint with title {string}")
    public void iTryToRetrieveAWaypointWithId(String title) throws Throwable {
        Optional<Waypoint> optionalWaypoint = waypointRepository.findByTitle(title);
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/waypoints/{id}", optionalWaypoint.isPresent() ? optionalWaypoint.get().getId() : "999")
                        .with(AuthenticationStepDefs.authenticate())
                        .accept(MediaType.APPLICATION_JSON));
    }
}
