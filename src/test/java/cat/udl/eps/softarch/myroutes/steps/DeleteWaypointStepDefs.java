package cat.udl.eps.softarch.myroutes.steps;

import cat.udl.eps.softarch.myroutes.domain.Waypoint;
import cat.udl.eps.softarch.myroutes.repository.WaypointRepository;
import io.cucumber.java.en.When;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

public class DeleteWaypointStepDefs {
    @Autowired
    private StepDefs stepDefs;
    @Autowired
    private WaypointRepository waypointRepository;

    @Transactional
    @When("I delete the Waypoint with title {string}")
    public void iDeleteWaypointWithId(String title) throws Throwable  {
        Optional<Waypoint> optionalWaypoint = waypointRepository.findByTitle(title);
        stepDefs.result = stepDefs.mockMvc.perform(
                delete("/waypoints/{id}", optionalWaypoint.isPresent() ? optionalWaypoint.get().getId() : "999")
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate())
        ).andDo(print());
    }
}
