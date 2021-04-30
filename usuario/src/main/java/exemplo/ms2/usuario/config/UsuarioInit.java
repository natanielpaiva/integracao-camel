package exemplo.ms2.usuario.config;

import exemplo.ms2.usuario.domain.Usuario;
import exemplo.ms2.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class UsuarioInit implements ApplicationRunner {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var usuario = Usuario.builder()
                .nome("Nataniel Paiva")
                .email("nataniel.paiva@gmail.com")
                .build();
        usuarioRepository.save(usuario);
        var usuario2 = Usuario.builder()
                .nome("Nataniel Amorim")
                .email("nataniel.paiva@gmail.com")
                .build();
        usuarioRepository.save(usuario2);
    }
}
