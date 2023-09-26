package cat.udl.eps.softarch.myroutes.domain;

import jakarta.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class RouteFollowed extends UriEntity<Long> {

    @Id
    private Long id;


    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
