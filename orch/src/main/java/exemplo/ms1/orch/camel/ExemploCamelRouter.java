package exemplo.ms1.orch.camel;

import exemplo.ms1.orch.exceptions.DeuRuimCamelExcpetion;
import exemplo.ms1.orch.repository.ImovelRepository;
import exemplo.ms1.orch.repository.UsuarioRepository;
import feign.FeignException;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
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
        this.acessarServicos();
        this.imovel();
        this.usuarioCaiu();
    }

    private void configExceptions() {
        onException(FeignException.class)
                .maximumRedeliveries(2)
                .to(ExemploCamelRouter.USUARIOCAIU)
                .end();
    }

    private void usuarioCaiu() {
        from(ExemploCamelRouter.USUARIOCAIU)
                .log("----> ${body}")
                .throwException(new DeuRuimCamelExcpetion("Usuário caiu"))
                .log("error ->${body}")
                .end();
    }

    private void acessarServicos() {
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

    private void imovel() {
        from(ExemploCamelRouter.IMOVEL)
                .routeId("chamaImovelCamel")
                .setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.GET))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .toD("http://{{imovel.url}}/imovel")
                .log("----> Imovel ${body}")
                .log("->${body}")
                .end();
    }

}
