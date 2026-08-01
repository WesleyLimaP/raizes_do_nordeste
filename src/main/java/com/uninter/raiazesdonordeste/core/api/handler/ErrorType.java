package com.uninter.raiazesdonordeste.core.api.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorType {
    INDAVLID_FORMAT("/formato-invalido", "erro sintatico", HttpStatus.BAD_REQUEST.value() ),
    ENTITY_NOT_FOUND("/entidade-nao-encontrada", "entidade não encontrada", HttpStatus.NOT_FOUND.value()),
    ENTITY_NOT_RELATED("/entidade-nao-relacionada", "entidade não relacionada",HttpStatus.BAD_REQUEST.value()),
    MESSAGE_NOT_READABLE("/corpo-mau-formatado", "corpo do json mal formatado", HttpStatus.BAD_REQUEST.value()),
    NO_RESOURCE_FOUND("/recurso-nao-encontrado", "recurso não encontrado", HttpStatus.NOT_FOUND.value()),
    METHOD_ARGUMENT_TYPE_MISMATCH("/tipo-de-argumento-invalido", "tipo de argumento inválido", HttpStatus.BAD_REQUEST.value()),
    METHOD_ARGUMENT_NOT_VALID("/argumento-invalido", "argumento inválido", HttpStatus.BAD_REQUEST.value()),
    INVALID_DATE_RANGE("/intervalo-de-datas-invalido", "intervalo de datas inválido", HttpStatus.BAD_REQUEST.value());
    private  String uri = "localhost:8080";
    private final String title;
    private final int status;

    ErrorType(String path, String title, int status) {
        this.uri += path;
        this.title = title;
        this.status = status;
    }
}
