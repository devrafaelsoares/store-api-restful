package br.devrafaelsoares.storeapirestful.domain.authentication.dto;

import br.devrafaelsoares.storeapirestful.domain.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponse implements Serializable {

    private ZonedDateTime moment;

    private String message;

    private String username;

    private String email;

    private Role role;

}
