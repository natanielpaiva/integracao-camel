package exemplo.ms1.orch.repository.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImovelResponse {

    private Long id;

    private Long usuarioId;

    private String descricao;
}
