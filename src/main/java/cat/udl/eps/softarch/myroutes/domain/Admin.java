package cat.udl.eps.softarch.myroutes.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;


import java.util.Collection;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Admin extends User {
    @Override
    @JsonValue(value = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ElementCollection
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN");
    }
}
