package cat.udl.eps.softarch.myroutes.steps;

import cat.udl.eps.softarch.myroutes.domain.Admin;
import cat.udl.eps.softarch.myroutes.domain.Route;
import cat.udl.eps.softarch.myroutes.domain.User;
import cat.udl.eps.softarch.myroutes.repository.AdminRepository;
import cat.udl.eps.softarch.myroutes.repository.RouteRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class DeleteRouteStepdefs{
    private CreateRouteStepdefs createSteps;
    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private AdminRepository adminRepository;

    @When("I try to delete Route with title {string}")
    public void iTryToDeleteRouteWithTitle(String title) throws Exception {
        Route route = RouteUtil.getRouteByTitle(routeRepository,title);
        stepDefs.result = stepDefs.mockMvc.perform(
                delete("/routes/"+(route == null?"":route.getId()))
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate())
        );
    }

    @Given("There is a registered admin with name {string} and password {string} and email {string}")
    public void thereIsARegisteredAdminWithNameAndPasswordAndEmail(String name, String pw, String email) throws Exception {
        Admin admin = new Admin();
        admin.setEmail(email);
        admin.setId(name);

        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/admins")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new JSONObject(
                                        stepDefs.mapper.writeValueAsString(admin)
                                ).put("password", pw).toString())
                                .characterEncoding(StandardCharsets.UTF_8)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }
}
