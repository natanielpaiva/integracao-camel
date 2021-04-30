package exemplo.ms1.orch.camel;

import exemplo.ms1.orch.repository.ImovelRepository;
import exemplo.ms1.orch.repository.UsuarioRepository;
import feign.FeignException;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExemploCamelRouter extends RouteBuilder {

    public static final String USUARIO = "direct:usuario";
    public static final String IMOVEL = "direct:imovel";
    public static final String USUARIOCAIU = "direct:usuarioCaiu";

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ImovelRepository imovelRepository;

    @Override
    public void configure() throws Exception {
        this.configExceptions();
        this.acessarEndpointComOpenFeignClient();
        this.imovel();
        this.usuarioCaiu();
    }

    private void configExceptions() {
        onException(FeignException.class)
                .maximumRedeliveries(2)
                .to(ExemploCamelRouter.USUARIOCAIU)
                .process(new ThrowExceptionProcessor())
                .end();
    }

    private void usuarioCaiu(){
        from(ExemploCamelRouter.USUARIOCAIU)
                .log("----> ${body}")
                .process(new ThrowExceptionProcessor())
                .log("error ->${body}")
                .end();
    }

    private void acessarEndpointComOpenFeignClient() {
        from(ExemploCamelRouter.USUARIO)
                .bean(usuarioRepository, "retornaUsuarios()")
                .log("----> ${body}")
                .process(new UsuarioProcessor())
                .log("->${body}")
                .to(ExemploCamelRouter.IMOVEL)
                .process(new ImovelProcessor())
                .log("->imovel")
                .end();
    }

    private void imovel(){
        from(ExemploCamelRouter.IMOVEL)
                .bean(imovelRepository, "retornaImovel()")
                .log("----> Imovel ${body}")
                .log("->${body}")
                .end();
    }

}
