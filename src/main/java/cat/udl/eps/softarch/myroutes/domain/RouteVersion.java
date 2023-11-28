package cat.udl.eps.softarch.myroutes.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
public class RouteVersion extends UriEntity<Long>{

    @Id
    @GeneratedValue()
    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @NotNull
    private ZonedDateTime creationDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ManyToOne
    @NotNull
    private User createdBy;

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    private Route versionOf;

}