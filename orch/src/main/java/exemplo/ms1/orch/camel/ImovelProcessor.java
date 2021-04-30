package exemplo.ms1.orch.camel;

import exemplo.ms1.orch.repository.response.ImovelResponse;
import exemplo.ms1.orch.repository.response.UsuarioResponse;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.List;
import java.util.stream.Collectors;

public class ImovelProcessor implements Processor {

    @Override
    public void process(Exchange exchange) {
        List<UsuarioResponse> body = exchange.getProperty("usuario", List.class);
        List<ImovelResponse> imoveis = exchange.getIn().getBody(List.class);
        body.forEach(usuarioResponse -> {
            usuarioResponse.setImoveis(imoveis.stream()
                    .filter(imovelResponse -> imovelResponse.getUsuarioId().equals(usuarioResponse.getId()))
                    .collect(Collectors.toList()));
        });
        exchange.setProperty("imovel", body);
        exchange.getIn().setBody(body);
    }

}
