package cat.udl.eps.softarch.myroutes.steps;

import cat.udl.eps.softarch.myroutes.domain.Coordinate;
import cat.udl.eps.softarch.myroutes.repository.CoordinateRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


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
    public void iCreateANewValidCoordinate() throws Throwable {
        Coordinate coordinate = new Coordinate();
        coordinate.setCoordinate("41.40338,2.17403");
        coordinateRepository.save(coordinate);
        this.currentCoordinate = coordinate;
    }

    @When("I retrieve that Coordinate")
    public void iRetrieveThatCoordinate() throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/coordinates/{id}", this.currentCoordinate.getId())
                        .with(AuthenticationStepDefs.authenticate())
                        .accept(MediaType.APPLICATION_JSON)).andDo(print());
    }

    @When("I try to retrieve a Coordinate with id {long}")
    public void iTryToRetrieveACoordinateWithId(Long id) throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/coordinates/{id}", id)
                        .with(AuthenticationStepDefs.authenticate())
                        .accept(MediaType.APPLICATION_JSON)).andDo(print());
    }

    @When("I update that Coordinate with new value {string}")
    public void iUpdateThatCoordinateWithNewValue(String coordinate) throws Exception {
        this.currentCoordinate.setCoordinate(coordinate);
        JSONObject modifyCoordinate = new JSONObject();
        modifyCoordinate.put("coordinate", coordinate);

        stepDefs.result = stepDefs.mockMvc.perform(
                        patch("/coordinates/{id}", this.currentCoordinate.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate())
                                .content(modifyCoordinate.toString())
                                .characterEncoding(StandardCharsets.UTF_8)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @When("I delete that Coordinate")
    public void iDeleteThatCoordinate() throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                delete("/coordinates/{id}", this.currentCoordinate.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate())
        ).andDo(print());
    }

    @When("I delete unknown Coordinate")
    public void iDeleteUnknownCoordinate() throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                delete("/coordinates/{id}", (String) null)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate())
        ).andDo(print());
    }

    @And("The new Coordinate is updated with values {string}")
    public void theNewCoordinateIsUpdatedWithValues(String coordinate) throws Exception {
        Optional<Coordinate> updatedCoordinate = null;
        if (this.currentCoordinate.getId() != null) {
            updatedCoordinate = coordinateRepository.findById(this.currentCoordinate.getId());
        } else
            throw new Exception("Coordinate not found");

        if (updatedCoordinate.isPresent()) {
            if (!updatedCoordinate.get().getCoordinate().equals(coordinate)) {
                throw new Exception("Coordinate not updated");
            }
        } else
            throw new Exception("Coordinate not found");
    }

}
