package exemplo.ms1.orch.controller.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class RespostaComErro {
    private String mensagem;
    private Integer status;
}