package utils;

import lombok.AllArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.ConstraintViolation;
import java.util.Set;

@AllArgsConstructor
@ApplicationScoped
public class ValidateErrors {
    public <T> String format(Set<javax.validation.ConstraintViolation<T>> violations) {
        StringBuilder message = new StringBuilder();
        for (ConstraintViolation<T> v : violations) {
            message.append(String.format("%s: %s", v.getPropertyPath(), v.getMessage()));
        }
        return message.toString();
    }
}