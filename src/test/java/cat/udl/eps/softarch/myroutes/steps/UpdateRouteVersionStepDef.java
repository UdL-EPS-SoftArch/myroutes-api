package cat.udl.eps.softarch.myroutes.steps;

import cat.udl.eps.softarch.myroutes.domain.Route;
import cat.udl.eps.softarch.myroutes.domain.RouteVersion;
import cat.udl.eps.softarch.myroutes.domain.User;
import cat.udl.eps.softarch.myroutes.repository.RouteRepository;
import cat.udl.eps.softarch.myroutes.repository.RouteVersionRepository;
import io.cucumber.java.en.When;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class UpdateRouteVersionStepDef {
    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private RouteVersionRepository routeVersionRepository;

    @Autowired
    private RouteRepository routeRepository;
    @When("I update a creationDate RouteVersion whit a Route title {string}")
    public void iUpdateARouteVersionWhitARouteTitle(String routeTitle) throws Exception {
        Route route = routeRepository.findByTitle(routeTitle).get(0);
        RouteVersion routeVersion = routeVersionRepository.findByRoute(route.getId()).get(0);

        stepDefs.result = stepDefs.mockMvc.perform(patch(routeVersion.getUri())//"/routes/"+route.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new JSONObject().put("creationDate","2022-10-25T17:27:00Z").toString())
                        .characterEncoding(StandardCharsets.UTF_8)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                        .andDo(print());


    }
}
