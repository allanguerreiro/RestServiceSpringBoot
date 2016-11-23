package org.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by allan on 18/11/16.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class SchoolNotFoundException extends RuntimeException {

    public SchoolNotFoundException(String mecCode, String password) {
        super("Could not find School '" + mecCode + "'.");
    }
}
