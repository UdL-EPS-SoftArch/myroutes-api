package cat.udl.eps.softarch.myroutes.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Coordinate extends UriEntity<String> {

    @Id
    @GeneratedValue()
    private Long id;

    @NotBlank
    private String coordinate;

    @Override
    public String getId() {
        return null;
    }

}
