package exemplo.ms3.imoveis.config;

import exemplo.ms3.imoveis.repository.ImovelRepository;
import exemplo.ms3.imoveis.domain.Imovel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ImovelInit implements ApplicationRunner {

    @Autowired
    ImovelRepository imovelRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var imovel = Imovel.builder()
                .usuarioId(1L)
                .descricao("Apartemento X")
                .build();
        imovelRepository.save(imovel);

        var imovel2 = Imovel.builder()
                .usuarioId(1L)
                .descricao("Apartemento y")
                .build();

        imovelRepository.save(imovel2);

        var imovel3 = Imovel.builder()
                .usuarioId(2L)
                .descricao("Apartemento z")
                .build();

        imovelRepository.save(imovel3);
    }
}
