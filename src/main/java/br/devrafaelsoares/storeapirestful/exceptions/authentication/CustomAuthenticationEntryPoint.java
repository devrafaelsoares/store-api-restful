package br.devrafaelsoares.storeapirestful.exceptions.authentication;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        ObjectMapper structureException = new ObjectMapper();
        JsonNode jsonNode = structureException.createObjectNode()
                .put("moment", Instant.now().atZone(ZoneId.systemDefault()).toString())
                .put("status", HttpStatus.FORBIDDEN.value())
                .put("error", "Acesso negado")
                .put("message", "Você não possue autorização prévia para acessar esse recurso");


        String jsonString = structureException.writeValueAsString(jsonNode);

        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json");
        response.getWriter().write(jsonString);
    }
}
