package clinica.medica.ApiRest.infra.errores;

import org.springframework.validation.FieldError;

public record DatosErrores(String campo, String error) {
    public DatosErrores(FieldError fieldError){
        this(fieldError.getField(), fieldError.getDefaultMessage());
    }
}
