package exemplo.ms2.usuario.controller;


import exemplo.ms2.usuario.domain.Usuario;
import exemplo.ms2.usuario.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("usuario")
@Slf4j
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> listUsuario() throws Exception {
        log.info("ENDPOINTUSUARIO");
        throw new Exception();
//        return usuarioRepository.findAll();
    }

}
