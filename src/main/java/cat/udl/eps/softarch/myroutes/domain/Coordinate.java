package cat.udl.eps.softarch.myroutes.domain;


import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Coordinate extends UriEntity<Long> {

    @Id
    @GeneratedValue()
    private Long id;

    @NotBlank
    @NotNull
    @Pattern(regexp = "(-?([0-8]?[0-9](\\.\\d+)?|89(.[0]+)?)[,])+(-?([1]?[0-7]?[0-9](\\.\\d+)?|179((.[0]+)?)))", message = "Invalid format for Coordinate")
    private String coordinate;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    private RouteVersion routeVersion;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @GeneratedValue()
    @NotNull
    private ZonedDateTime creationDate = ZonedDateTime.now();

}
