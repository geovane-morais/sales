package app.sales.model.controlers;

import app.sales.security.DtoJwtRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import app.sales.security.JwtUtils;

@AllArgsConstructor
@RestController
public class JwtAuthenticatorControler {
    /*private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;

    private JwtUser

    @PostMapping("/Auth")
    public ResponseEntity<?> createAuthenticatorToken(@RequestBody DtoJwtRequest dtoJwtRequest){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                        dtoJwtRequest.getUsername()
                        ,dtoJwtRequest.getPassword()
                    )
            );

        }catch (DisabledException e){
            throw new RuntimeException("USER_DISABLED", e);
        }catch (BadCredentialsException e){
            throw new RuntimeException("INVALID_CREDENTIAL", e);
        }
        final UserDetails userDetails = userDetailService
    }*/

}
