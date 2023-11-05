package cat.udl.eps.softarch.myroutes.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.ZonedDateTime;

@Entity
@Data
public class RouteVersion extends UriEntity<Long>{

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @NotNull
    private ZonedDateTime creationDate;

    @ManyToOne
    @NotNull
    private User createdBy;

    @ManyToOne
    @NotNull
    private Route versionOf;

}