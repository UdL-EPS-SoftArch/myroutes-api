package cat.udl.eps.softarch.myroutes.steps;

import cat.udl.eps.softarch.myroutes.domain.Waypoint;
import cat.udl.eps.softarch.myroutes.repository.WaypointRepository;
import io.cucumber.java.en.When;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

public class UpdateWaypointStepDefs {
    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private WaypointRepository waypointRepository;


    @When("I update Waypoint with title \"([^\"]*)\" by changing it to \"([^\"]*)\" and description to \"([^\"]*)\"")
    public void iUpdateWaypointWithTitleByChangingItToAndDescriptionTo
            (String title, String newTitle, String description)
            throws Throwable {
        Optional<Waypoint> optionalWaypoint= waypointRepository.findByTitle(title);

        JSONObject modifyWaypoint = new JSONObject();
        modifyWaypoint.put("title", newTitle);
        if (description != null)
            modifyWaypoint.put("description", new JSONArray(title.split(", ", -1)));

        stepDefs.result = stepDefs.mockMvc.perform(
                patch("/waypoints/{id}", optionalWaypoint.isPresent() ? optionalWaypoint.get().getId() : "999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(modifyWaypoint.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .with(AuthenticationStepDefs.authenticate())).andDo(print());

        if (stepDefs.result.andReturn().getResponse().getStatus() == 200) {
            JSONObject updateWaypointJSON = new JSONObject(stepDefs.result.andReturn().getResponse().getContentAsString());
            Assert.assertEquals(newTitle, updateWaypointJSON.get("title"));
            if (description != null)
                Assert.assertEquals(new JSONArray(description.split(", ", -1)), updateWaypointJSON.get("description"));
        }
    }

    @When("I update Waypoint with title \"([^\"]*)\" by changing it to \"([^\"]*)\"$")
    public void iUpdateWaypointWithTitleByChangingItTo(String title, String newTitle) throws Throwable {
        iUpdateWaypointWithTitleByChangingItToAndDescriptionTo(title, newTitle, null);
    }
}
