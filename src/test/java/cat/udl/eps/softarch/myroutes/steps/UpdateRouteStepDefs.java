package cat.udl.eps.softarch.myroutes.steps;

import cat.udl.eps.softarch.myroutes.domain.Route;
import cat.udl.eps.softarch.myroutes.repository.RouteRepository;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UpdateRouteStepDefs{
    @Autowired
    private StepDefs stepDefs;
    @Autowired
    private RouteRepository routeRepository;

    @When("I update a route with a title {string}, description {string}, type {string} and a creationDate {string}")
    public void iUpdateARouteWithATitleDescriptionTypeAndACreationDate(String title, String description, String type, String creationDate) throws Exception {
        Route route = RouteUtil.getRouteByTitle(routeRepository,title);
        if(route == null)
            route = ((List<Route>) routeRepository.findAll()).get(0);
        stepDefs.result = stepDefs.mockMvc.perform(patch(route.getUri())//"/routes/"+route.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new JSONObject().put("title",title).put("description",description).put("type",type).put("creationDate",creationDate).toString())
                .characterEncoding(StandardCharsets.UTF_8)
                .accept(MediaType.APPLICATION_JSON)
                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());

        stepDefs.result.andReturn().getResponse().getHeader("Location");
    }

    @Then("This route is as expected to be title {string}")
    public void thisRouteIsAsExpectedToBeTitleDescriptionTypeAndACreationDate(String title) throws Exception {
        Route route = RouteUtil.getRouteByTitle(routeRepository,title);
        Optional<Route> optionalRoute = routeRepository.findById(route.getId());
        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/routes/{id}", optionalRoute.isPresent() ? optionalRoute.get().getId() : "1")
                                .with(AuthenticationStepDefs.authenticate())
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uri").exists())
                .andExpect(jsonPath("$.uri").isNotEmpty());

    }
}
