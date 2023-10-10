package cat.udl.eps.softarch.myroutes.steps;

import cat.udl.eps.softarch.myroutes.domain.Coordinate;
import cat.udl.eps.softarch.myroutes.repository.CoordinateRepository;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class CoordinateStepDefs {

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private CoordinateRepository coordinateRepository;

    @When("I create a new coordinate with value {string}")
    public void iCreateANewCoordinateWithValue(String s_coordinate) throws Throwable {

        Coordinate coordinate = new Coordinate();
        coordinate.setCoordinate(s_coordinate);

        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/coordinate")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new JSONObject(
                                        stepDefs.mapper.writeValueAsString(coordinate)
                                ).toString())
                                .characterEncoding(StandardCharsets.UTF_8)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());

    }
}
