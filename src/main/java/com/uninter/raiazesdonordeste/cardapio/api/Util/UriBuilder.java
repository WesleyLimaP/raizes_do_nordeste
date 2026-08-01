package com.uninter.raiazesdonordeste.cardapio.api.Util;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public final class UriBuilder {

        public static URI create(Object id){
            return ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(id) // substitui {id}
                    .toUri();

    }

}
