package ahmed.foudi.itlens.utils;


import ahmed.foudi.itlens.dto.errordto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleAllExceptions(Exception ex, WebRequest request) {
        ErrorDTO errorDTO = new ErrorDTO();

        errorDTO.setTimestamp(LocalDateTime.now());
        errorDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorDTO.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        errorDTO.setMessage(ex.getMessage());
        errorDTO.setPath(request.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
