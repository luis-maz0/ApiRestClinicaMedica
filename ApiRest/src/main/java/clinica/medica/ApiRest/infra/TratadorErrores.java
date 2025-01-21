package clinica.medica.ApiRest.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorErrores {

    //Cuando se identifica una excepcion del tipo notFoundException, el exceptionHandler lo atrapará y retornará la respuesta.
    @ExceptionHandler( EntityNotFoundException.class )
    public ResponseEntity tratarError404(){
        return  ResponseEntity.notFound().build();
    }
    //Error 400
    @ExceptionHandler( MethodArgumentNotValidException.class )
    public ResponseEntity tratarError400( MethodArgumentNotValidException e){
        //Se envia logs de errores (DTO).
        var errores = e.getFieldErrors().stream().map( error -> new DatosErrores(error) ).toList();
        return  ResponseEntity.badRequest().body(errores);
    }
}
