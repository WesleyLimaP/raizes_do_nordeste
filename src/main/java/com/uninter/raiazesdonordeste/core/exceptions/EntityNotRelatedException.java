package com.uninter.raiazesdonordeste.core.exceptions;

import lombok.Data;
import lombok.Getter;

@Data
public class EntityNotRelatedException extends RuntimeException {
    private Long id;
    private Long relatedId;
    public EntityNotRelatedException(String message,Long id, Long relatedId ) {
        this.id = id;
        this.relatedId = relatedId;
        super(message);
    }
}
