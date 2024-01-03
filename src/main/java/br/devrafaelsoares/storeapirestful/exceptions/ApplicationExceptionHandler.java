package br.devrafaelsoares.storeapirestful.exceptions;

import br.soares.api.springsecurity.exceptions.auth.UserExistsException;
import br.soares.api.springsecurity.exceptions.auth.UserNotFoundException;
import br.soares.api.springsecurity.exceptions.product.FieldValidationStructureException;
import br.soares.api.springsecurity.exceptions.product.ValidationStructureException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ServerErrorException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.security.SignatureException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<AuthStructureException> handlerUserNotFoundException(
            UserNotFoundException exception, HttpServletRequest request
    ) {

        AuthStructureException structureException = AuthStructureException
                .builder()
                .moment(Instant.now().atZone(ZoneId.systemDefault()))
                .status(HttpStatus.UNAUTHORIZED.value())
                .error("Usuário ou senha inválidos")
                .message("Usuário ou senha informados são inválidos")
                .path(request.getServletPath())
                .build();

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(structureException);
    }

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<AuthStructureException> handlerUserExistsException(
            UserExistsException exception, HttpServletRequest request
    ) {

        AuthStructureException structureException = AuthStructureException
                .builder()
                .moment(Instant.now().atZone(ZoneId.systemDefault()))
                .status(HttpStatus.CONFLICT.value())
                .error("Usuário já cadastrado")
                .message("Usuário já se encontra cadastrado no sistema")
                .path(request.getServletPath())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(structureException);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<AuthStructureException> handlerAuthenticationException(
            AuthenticationException exception, HttpServletRequest request
    ) {

        AuthStructureException structureException = AuthStructureException
                .builder()
                .moment(Instant.now().atZone(ZoneId.systemDefault()))
                .status(HttpStatus.UNAUTHORIZED.value())
                .error("Usuário ou senha inválidos")
                .message("Usuário ou senha informados são inválidos")
                .path(request.getServletPath())
                .build();

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(structureException);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<AuthStructureException> handlerTokenExpiredException(
            TokenExpiredException exception, HttpServletRequest request
    ) {

        AuthStructureException structureException = AuthStructureException
                .builder()
                .moment(Instant.now().atZone(ZoneId.systemDefault()))
                .status(HttpStatus.UNAUTHORIZED.value())
                .error("Token expirado ou inválido")
                .message("O token informado está expirado ou inválido")
                .path(request.getServletPath())
                .build();

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(structureException);
    }

    @ExceptionHandler(InsufficientAuthenticationException.class)
    public ResponseEntity<AuthStructureException> handlerInsufficientAuthenticationException(
            InsufficientAuthenticationException exception, HttpServletRequest request
    ) {

        AuthStructureException structureException = AuthStructureException
                .builder()
                .moment(Instant.now().atZone(ZoneId.systemDefault()))
                .status(HttpStatus.FORBIDDEN.value())
                .error("Acesso negado")
                .message("Você não possui autorização prévia para acessar esse recurso")
                .path(request.getServletPath())
                .build();

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(structureException);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<AuthStructureException> handlerSignatureException(
            SignatureException exception, HttpServletRequest request
    ) {

        AuthStructureException structureException = AuthStructureException
                .builder()
                .moment(Instant.now().atZone(ZoneId.systemDefault()))
                .status(HttpStatus.UNAUTHORIZED.value())
                .error("Token expirado ou inválido")
                .message("O token informado está expirado ou inválido")
                .path(request.getServletPath())
                .build();

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(structureException);
    }

    @ExceptionHandler(JWTDecodeException.class)
    public ResponseEntity<AuthStructureException> handlerJWTDecodeException(
            JWTDecodeException exception, HttpServletRequest request
    ) {

        AuthStructureException structureException = AuthStructureException
                .builder()
                .moment(Instant.now().atZone(ZoneId.systemDefault()))
                .status(HttpStatus.UNAUTHORIZED.value())
                .error("Token expirado ou inválido")
                .message("O token informado está expirado ou inválido")
                .path(request.getServletPath())
                .build();

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(structureException);
    }

    @ExceptionHandler(SignatureVerificationException.class)
    public ResponseEntity<AuthStructureException> handlerSignatureVerificationException(
            SignatureVerificationException exception, HttpServletRequest request
    ) {

        AuthStructureException structureException = AuthStructureException
                .builder()
                .moment(Instant.now().atZone(ZoneId.systemDefault()))
                .status(HttpStatus.UNAUTHORIZED.value())
                .error("Token expirado ou inválido")
                .message("O token informado está expirado ou inválido")
                .path(request.getServletPath())
                .build();

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(structureException);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<AuthStructureException> handlerDataIntegrityViolationException(
            DataIntegrityViolationException exception, HttpServletRequest request
    ) {

        AuthStructureException structureException = AuthStructureException
                .builder()
                .moment(Instant.now().atZone(ZoneId.systemDefault()))
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Erro de integridade")
                .message("Ocorreu um erro no processo de registro da entidade")
                .path(request.getServletPath())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(structureException);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<AuthStructureException> handlerNoResourceFoundException(
            NoResourceFoundException exception, HttpServletRequest request
    ) {

        AuthStructureException structureException = AuthStructureException
                .builder()
                .moment(Instant.now().atZone(ZoneId.systemDefault()))
                .status(HttpStatus.NOT_FOUND.value())
                .error("Recurso não encontrado")
                .message("Não foi possível encontrar o recurso solicitado")
                .path(request.getServletPath())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structureException);
    }

    @ExceptionHandler(ServerErrorException.class)
    public ResponseEntity<AuthStructureException> handlerServerErrorException(
            ServerErrorException exception, HttpServletRequest request
    ) {

        AuthStructureException structureException = AuthStructureException
                .builder()
                .moment(Instant.now().atZone(ZoneId.systemDefault()))
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Erro no processamento da solicitação")
                .message("Não foi possível processar a solicitação")
                .path(request.getServletPath())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(structureException);
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<AuthStructureException> handlerEntityExistsException(
            EntityExistsException exception, HttpServletRequest request
    ) {

        AuthStructureException structureException = AuthStructureException
                .builder()
                .moment(Instant.now().atZone(ZoneId.systemDefault()))
                .status(HttpStatus.CONFLICT.value())
                .error("Entidade já existe")
                .message(exception.getMessage())
                .path(request.getServletPath())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(structureException);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<AuthStructureException> handlerEntityNotFoundException(
            EntityNotFoundException exception, HttpServletRequest request
    ) {

        AuthStructureException structureException = AuthStructureException
                .builder()
                .moment(Instant.now().atZone(ZoneId.systemDefault()))
                .status(HttpStatus.NOT_FOUND.value())
                .error("Entidade não existe")
                .message(exception.getMessage())
                .path(request.getServletPath())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structureException);
    }

    @ExceptionHandler(ForeignKeyAssociationException.class)
    public ResponseEntity<AuthStructureException> handlerForeignKeyAssociationException(
            ForeignKeyAssociationException exception, HttpServletRequest request
    ) {

        AuthStructureException structureException = AuthStructureException
                .builder()
                .moment(Instant.now().atZone(ZoneId.systemDefault()))
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .error("Erro de integridade")
                .message(exception.getMessage())
                .path(request.getServletPath())
                .build();

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(structureException);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<AuthStructureException> handlerMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException exception, HttpServletRequest request
    ) {

        AuthStructureException structureException = AuthStructureException
                .builder()
                .moment(Instant.now().atZone(ZoneId.systemDefault()))
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Erro na requisição")
                .message("O ID informado não corresponde com o formato esperado")
                .path(request.getServletPath())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(structureException);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<AuthStructureException> handlerHttpMessageNotReadableException(
            HttpMessageNotReadableException exception, HttpServletRequest request
    ) {

        AuthStructureException structureException = AuthStructureException
                .builder()
                .moment(Instant.now().atZone(ZoneId.systemDefault()))
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Campos inválidos")
                .message("Campos informados são inválidos")
                .path(request.getServletPath())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(structureException);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationStructureException> handlerMethodArgumentNotValidException(
            MethodArgumentNotValidException exception,
            HttpServletRequest request
    ) {
        List<FieldValidationStructureException> fieldValidationExceptions = exception
                .getBindingResult()
                .getFieldErrors()
                .stream().map(fieldError ->
                        FieldValidationStructureException
                        .builder()
                            .field(fieldError.getField())
                            .message(fieldError.getDefaultMessage())
                        .build()).toList();

       ValidationStructureException validationStructureException = ValidationStructureException
               .builder()
                   .moment(Instant.now().atZone(ZoneId.systemDefault()))
                   .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                   .errors(fieldValidationExceptions)
                   .path(request.getServletPath())
               .build();

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(validationStructureException);
    }
}
