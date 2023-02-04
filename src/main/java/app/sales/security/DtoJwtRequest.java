package app.sales.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Access;
import java.io.Serializable;

@AllArgsConstructor
@Getter@Setter
public class DtoJwtRequest implements Serializable {
    private String username;
    private String password;
}
