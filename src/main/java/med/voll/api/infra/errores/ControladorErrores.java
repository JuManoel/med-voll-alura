package med.voll.api.infra.errores;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ControladorErrores {

    @ExceptionHandler({RuntimeException.class, EntityNotFoundException.class})
    public ResponseEntity tratarError404(){
        return ResponseEntity.notFound().build();
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e){
        var errores = e.getFieldErrors().stream().map(ErrorDTO::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }

    private record ErrorDTO(String campo, String error){
        public ErrorDTO(FieldError error){
            this(error.getField(),error.getDefaultMessage());
        }
    }

}
