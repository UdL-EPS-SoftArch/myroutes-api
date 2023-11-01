package cat.udl.eps.softarch.myroutes.steps;

import cat.udl.eps.softarch.myroutes.domain.Coordinate;
import cat.udl.eps.softarch.myroutes.domain.Waypoint;
import cat.udl.eps.softarch.myroutes.repository.WaypointRepository;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class CreateWaypointStepDefs {

    @Autowired
    private StepDefs stepDefs;
    @Autowired
    private WaypointRepository waypointRepository;
    public static String newResourceUri;

    @When("I create a new Waypoint with title \"([^\"]*)\" description \"([^\"]*)\" and type \"([^\"]*)\"$")
    public void iCreateANewWaypointWithTitleDescriptionAndType(String title, String description, String type) throws Throwable {
        Waypoint newWaypoint = new Waypoint();
        newWaypoint.setTitle(title);
        newWaypoint.setDescription(description);
        newWaypoint.setType(type);

        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/waypoints")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(newWaypoint))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @When("I create a new Waypoint with title \"([^\"]*)\" description \"([^\"]*)\" type \"([^\"]*)\" and coordinate {string}$")
    public void iCreateANewWaypointWithTitleDescriptionTypeAndCoordinate(String title, String description, String type, Coordinate coordinate) throws Throwable {
        Waypoint newWaypoint = new Waypoint();
        newWaypoint.setTitle(title);
        newWaypoint.setDescription(description);
        newWaypoint.setType(type);
        newWaypoint.setCoordinate(coordinate);

        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/waypoints")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(newWaypoint))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("There is (\\\\d+) Waypoint created$")
    public void thereIsWaypointCreated(int waypointsCreatedNum) {
        Assert.assertEquals(waypointsCreatedNum, waypointRepository.count());
    }

    @And("I try to retrieve that Waypoint")
    public void iTryToRetrieveThatWaypoint() throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                get(newResourceUri)
                        .with(AuthenticationStepDefs.authenticate())
                        .accept(MediaType.APPLICATION_JSON));
    }

    @And("There is already a Waypoint with title \"([^\"]*)\" description \"([^\"]*)\" and type \"([^\"]*)\"$")
    public void thereIsAlreadyAWaypointWithTitleDescriptionAndType(String title, String description, String type) {
        Waypoint waypoint = new Waypoint();
        waypoint.setTitle(title);
        waypoint.setDescription(description);
        waypoint.setType(type);
        waypointRepository.save(waypoint);
    }
}
