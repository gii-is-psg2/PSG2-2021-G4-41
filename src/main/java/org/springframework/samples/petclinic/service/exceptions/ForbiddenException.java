package org.springframework.samples.petclinic.service.exceptions;

public class ForbiddenException extends Exception {

    @Override
    public String getMessage() {
        return "This is a forbidden action for your role";
    }

}
