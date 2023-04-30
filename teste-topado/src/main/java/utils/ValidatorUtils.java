package utils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.Set;

@ApplicationScoped
public class ValidatorUtils {
    @Inject
    Validator validator;

    @Inject
    ValidateErrors validateErrors;

    public <T> void validators(T element) {
        Set<ConstraintViolation<T>> validations = this.validator.validate(element);
        if (!validations.isEmpty()) {
            String message = this.validateErrors.format(validations);
            throw new WebApplicationException(message, Response.Status.BAD_REQUEST);
        }
    }
}
