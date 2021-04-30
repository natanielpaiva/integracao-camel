package exemplo.ms1.orch.repository.response;

import exemplo.ms1.orch.repository.response.ImovelResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UsuarioResponse {

    private Long id;

    private String nome;

    private String email;

    private List<ImovelResponse> imoveis;

}
