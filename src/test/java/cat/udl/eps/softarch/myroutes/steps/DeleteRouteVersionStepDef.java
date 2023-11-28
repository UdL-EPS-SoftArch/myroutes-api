package cat.udl.eps.softarch.myroutes.steps;

import cat.udl.eps.softarch.myroutes.domain.Route;
import cat.udl.eps.softarch.myroutes.domain.RouteVersion;
import cat.udl.eps.softarch.myroutes.repository.RouteRepository;
import cat.udl.eps.softarch.myroutes.repository.RouteVersionRepository;
import io.cucumber.java.en.When;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class DeleteRouteVersionStepDef {
    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private RouteVersionRepository routeVersionRepository;

    @Autowired
    private RouteRepository routeRepository;


    @When("I delete a RouteVersion whit a Route title {string}")
    public void iUpdateARouteVersionWhitARouteTitle(String routeTitle) throws Exception {
        Route route = routeRepository.findByTitle(routeTitle).get(0);
        RouteVersion routeVersion = routeVersionRepository.findByversionOf(route).get(0);

        stepDefs.result = stepDefs.mockMvc.perform(
                        delete((route == null?"":routeVersion.getUri()))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                                .andDo(print());


    }

}
