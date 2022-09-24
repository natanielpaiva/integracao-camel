package exemplo.ms1.orch.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class DeuRuimCamelExcpetion extends RuntimeException{
    public DeuRuimCamelExcpetion(String message) {
        super(message);
    }
}
