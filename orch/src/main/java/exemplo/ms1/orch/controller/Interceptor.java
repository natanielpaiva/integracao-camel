package exemplo.ms1.orch.controller;

import exemplo.ms1.orch.controller.response.RespostaComErro;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelExecutionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class Interceptor {

    @ExceptionHandler
    public ResponseEntity<RespostaComErro> erroInesperado(Exception e) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        RespostaComErro errorResponse = RespostaComErro
                .builder()
                .mensagem(e.getMessage())
                .status(httpStatus.value())
                .build();
        log.error("Ocorreu um erro inesperado", e);
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    @ExceptionHandler
    public ResponseEntity<RespostaComErro> erroPrevisto(CamelExecutionException e) {
        HttpStatus httpStatus = HttpStatus.SERVICE_UNAVAILABLE;
        RespostaComErro errorResponse = RespostaComErro
                .builder()
                .mensagem(e.getCause().getMessage())
                .status(httpStatus.value())
                .build();
        log.error("Ocorreu um erro", e);
        return new ResponseEntity<>(errorResponse, httpStatus);
    }


}
