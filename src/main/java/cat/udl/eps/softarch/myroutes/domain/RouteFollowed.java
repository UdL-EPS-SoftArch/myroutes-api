package cat.udl.eps.softarch.myroutes.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Duration;
import java.time.ZonedDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class RouteFollowed extends UriEntity<Long> {

    @Id
    @GeneratedValue
    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @NotNull
    private ZonedDateTime date;

    @NotNull
    private Duration duration = Duration.parse("P1DT1H10M10.5S");

    @NotBlank
    private String levelUp;

    @NotBlank
    private String levelDown;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @NotNull
    @ManyToOne
    public User createdBy;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @NotNull
    @ManyToOne
    public Route routeOrigin;

}
