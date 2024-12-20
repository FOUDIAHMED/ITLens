package ahmed.foudi.itlens.dto.errordto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ErrorDTO {
    private int statusCode;
    private String error;
    private String message;
    private LocalDateTime timestamp;
    private String path;
    private String className;
}
