package cat.udl.eps.softarch.myroutes.steps;

import cat.udl.eps.softarch.myroutes.domain.Route;
import cat.udl.eps.softarch.myroutes.repository.RouteRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class RouteStepdefs {

    @Autowired
    private StepDefs stepDefs;
    @Autowired
    private RouteRepository routeRepository;

    @When("I create a route with a title {string}, description {string}, type {string} and a creationDate {string}")
    public void iCreateARouteWithATitleDescriptionTypeAndACreationDate(String title, String description, String type, String creationDate) throws Exception {
        Route route = buildRoute(title,description,type,creationDate);
        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/routes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(route))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                                .andDo(print());
                                //

        String newResourceUri = stepDefs.result.andReturn().getResponse().getHeader("Route");
    }

    private Route buildRoute(String title, String description, String type, String sCreationDate){
        Route route = new Route();
        if(!sCreationDate.isEmpty()){
            ZonedDateTime creationDate = ZonedDateTime.parse(sCreationDate);
            route.setCreationDate(creationDate);
        }
        if(!title.isEmpty())
            route.setTitle(title);
        if(!description.isEmpty())
            route.setDescription(description);
        if(!type.isEmpty()){
            try{
                Route.Type value = Route.Type.valueOf(type);
                route.setType(value);
            }catch (Exception e){
                System.out.println("Error on Type parse");
            }
        }
        return route;
    }

    @And("I don't have any route")
    public void iDonTHaveAnyRoute() {
        routeRepository.deleteAll();
    }


}
