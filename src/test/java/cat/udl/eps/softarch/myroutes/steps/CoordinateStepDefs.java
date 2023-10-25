package cat.udl.eps.softarch.myroutes.steps;

import cat.udl.eps.softarch.myroutes.domain.Coordinate;
import cat.udl.eps.softarch.myroutes.repository.CoordinateRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


public class CoordinateStepDefs {

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private CoordinateRepository coordinateRepository;
    private Coordinate currentCoordinate;

    @When("I create a new Coordinate with value {string}")
    public void iCreateANewCoordinateWithValue(String s_coordinate) throws Throwable {

        Coordinate coordinate = new Coordinate();
        coordinate.setCoordinate(s_coordinate);

        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/coordinates")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(coordinate))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("I create a new valid Coordinate")
    public void iCreateANewValidCoordinate() throws Throwable{
        Coordinate coordinate = new Coordinate();
        coordinate.setCoordinate("41.40338,2.17403");
        coordinateRepository.save(coordinate);
        this.currentCoordinate = coordinate;
    }

    @When("I get that Coordinate")
    public void iGetThatCoordinate() throws Throwable{
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/coordinates/{id}", this.currentCoordinate.getId())
                        .with(AuthenticationStepDefs.authenticate())
                        .accept(MediaType.APPLICATION_JSON));
    }

    @When("I try to retrieve a Coordinate with id {long}")
    public void iTryToRetrieveACoordinateWithId(Long id) throws Throwable{
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/coordinates/{id}", id)
                        .with(AuthenticationStepDefs.authenticate())
                        .accept(MediaType.APPLICATION_JSON));
    }
}
