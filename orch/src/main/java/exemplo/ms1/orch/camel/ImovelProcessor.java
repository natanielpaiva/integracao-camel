package exemplo.ms1.orch.camel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exemplo.ms1.orch.repository.response.ImovelResponse;
import exemplo.ms1.orch.repository.response.UsuarioResponse;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;


public class ImovelProcessor implements Processor {


    @Override
    public void process(Exchange exchange) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        List<UsuarioResponse> body = exchange.getProperty("usuario", List.class);

        List<ImovelResponse> imoveis = mapper
                .readValue(exchange.getIn().getBody(String.class), new TypeReference<List<ImovelResponse>>(){});
        body.forEach(usuarioResponse -> {
            usuarioResponse.setImoveis(imoveis.stream()
                    .filter(imovelResponse -> imovelResponse.getUsuarioId().equals(usuarioResponse.getId()))
                    .collect(Collectors.toList()));
        });
        exchange.setProperty("imovel", body);
        exchange.getIn().setBody(body);
    }

}
