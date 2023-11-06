package cat.udl.eps.softarch.myroutes.domain;


import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.*;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Waypoint extends UriEntity<Long> {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @NotBlank
    //@Column(unique = true)
    private String title;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @NotBlank
    private String type;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    private Coordinate coordinate;
}