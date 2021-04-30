package exemplo.ms1.orch.service;

import exemplo.ms1.orch.camel.ExemploCamelRouter;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private ProducerTemplate template;

    public List listaUsuarios(){
        return template.requestBody(ExemploCamelRouter.USUARIO, null, List.class);
    }

}
