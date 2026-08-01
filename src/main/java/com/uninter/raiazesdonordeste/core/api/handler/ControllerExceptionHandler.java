package com.uninter.raiazesdonordeste.core.api.handler;

import com.uninter.raiazesdonordeste.core.exceptions.EntityNotFoundException;
import com.uninter.raiazesdonordeste.core.exceptions.EntityNotRelatedException;
import com.uninter.raiazesdonordeste.core.exceptions.InvalidDateRangeException;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.exc.InvalidFormatException;

import java.time.Instant;
import java.util.HashMap;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        Error error = Error.builder()
                 .message(ex.getMessage())
                 .path(ErrorType.ENTITY_NOT_FOUND.getUri())
                 .timestamp(String.valueOf(Instant.now()))
                 .status(ErrorType.ENTITY_NOT_FOUND.getStatus())
                 .type(ErrorType.ENTITY_NOT_FOUND)
                 .details("a entidade sugerida não existe. tente outro id.")
                 .title(ErrorType.ENTITY_NOT_FOUND.getTitle())
                 .build();

        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.NOT_FOUND, request);


    }
    @ExceptionHandler(EntityNotRelatedException.class)
    public ResponseEntity<?> handleEntityNotRelatedException(EntityNotRelatedException ex, WebRequest request) {
        Error error = Error.builder()
                .message(ex.getMessage())
                .details("a entidade de id " + ex.getId() + " não está relacionada ao id " + ex.getRelatedId())
                .title(ErrorType.ENTITY_NOT_RELATED.getTitle())
                .path(ErrorType.ENTITY_NOT_RELATED.getUri())
                .timestamp(Instant.now().toString())
                .status(ErrorType.ENTITY_NOT_RELATED.getStatus())
                .type(ErrorType.ENTITY_NOT_RELATED)
                .build();
        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(InvalidDateRangeException.class)
    public ResponseEntity<?> handleInvalidDateRangeException(InvalidDateRangeException ex, WebRequest request) {
        Error error = Error.builder()
                .message(ex.getMessage())
                .details("verifique se a data de fim é maior que a data de início")
                .title(ErrorType.INVALID_DATE_RANGE.getTitle())
                .path(ErrorType.INVALID_DATE_RANGE.getUri())
                .timestamp(Instant.now().toString())
                .status(ErrorType.INVALID_DATE_RANGE.getStatus())
                .type(ErrorType.INVALID_DATE_RANGE)
                .build();
        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected @Nullable ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Error error = Error.builder()
                .message("recurso não encontrado")
                .details("o recurso '" + ex.getResourcePath() + "' solicitado não foi encontrado")
                .title(ErrorType.NO_RESOURCE_FOUND.getTitle())
                .path(ErrorType.NO_RESOURCE_FOUND.getUri())
                .timestamp(Instant.now().toString())
                .status(ErrorType.NO_RESOURCE_FOUND.getStatus())
                .type(ErrorType.NO_RESOURCE_FOUND)
                .build();
        return super.handleExceptionInternal(ex, error, headers, status, request);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request) {
        Error error = Error.builder()
                .message("tipo de argumento inválido")
                .details("o parâmetro '" + ex.getName() + "' deveria ser do tipo " + ex.getRequiredType().getSimpleName() + " mas recebeu: " + ex.getValue())
                .title(ErrorType.METHOD_ARGUMENT_TYPE_MISMATCH.getTitle())
                .path(ErrorType.METHOD_ARGUMENT_TYPE_MISMATCH.getUri())
                .timestamp(Instant.now().toString())
                .status(ErrorType.METHOD_ARGUMENT_TYPE_MISMATCH.getStatus())
                .type(ErrorType.METHOD_ARGUMENT_TYPE_MISMATCH)
                .build();
        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected @Nullable ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var fieldsError = new HashMap<String, String>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            fieldsError.put(error.getField(), error.getDefaultMessage());
        });
        Error error = Error.builder()
                .message("argumentos inválidos")
                .details("verifique os campos informados")
                .title(ErrorType.METHOD_ARGUMENT_NOT_VALID.getTitle())
                .path(ErrorType.METHOD_ARGUMENT_NOT_VALID.getUri())
                .timestamp(Instant.now().toString())
                .status(ErrorType.METHOD_ARGUMENT_NOT_VALID.getStatus())
                .type(ErrorType.METHOD_ARGUMENT_NOT_VALID)
                .fieldsError(fieldsError)
                .build();
        return super.handleExceptionInternal(ex, error, headers, status, request);
    }

    @Override
    protected @Nullable ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        if (ex.getRootCause() instanceof InvalidFormatException e) {
            return handleInvalidFormatException(e, headers, status, request);
        }
        Error error = Error.builder()
                .message(ex.getMessage())
                .details("erro sintatico na construção do json")
                .title(ErrorType.MESSAGE_NOT_READABLE.getTitle())
                .path(ErrorType.MESSAGE_NOT_READABLE.getUri())
                .timestamp(Instant.now().toString())
                .status(ErrorType.MESSAGE_NOT_READABLE.getStatus())
                .type(ErrorType.MESSAGE_NOT_READABLE)
                .build();
        return this.handleExceptionInternal(ex, error, headers, status, request);

    }


    public ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException e, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var campos = e.getPath().stream().map(JacksonException.Reference::getPropertyName).toList();
        var camposString = String.join(", ", campos);
        var valor = e.getValue();
        var tipo = e.getValue().getClass().getSimpleName();
        var tipoEsperado = e.getTargetType().getSimpleName();
        var fieldsError = new HashMap<String, String>();
        for (var campo : campos) {
            fieldsError.put(campo, valor.toString());
        }
        Error error = Error.builder()
                .message("0/s campo/s " + camposString + ", de valor " + tipo + " '"+valor+"'" + " nao pode ser convertido para "+ tipoEsperado)
                .details("erro de conversão de tipos")
                .title(ErrorType.INDAVLID_FORMAT.getTitle())
                .path(ErrorType.INDAVLID_FORMAT.getUri())
                .timestamp(Instant.now().toString())
                .status(ErrorType.INDAVLID_FORMAT.getStatus())
                .type(ErrorType.INDAVLID_FORMAT)
                .fieldsError(fieldsError)
                .build();

        return this.handleExceptionInternal(e, error, headers, status, request);

    }


    @Override
    protected @Nullable ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        printRootCause(ex);

        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    private static void printRootCause(Exception ex) {
        if (ex.getCause() != null) {
            System.out.println(ex.getCause().toString());;
        }
    }
}
