package exemplo.ms1.orch.controller;

import exemplo.ms1.orch.repository.response.UsuarioResponse;
import exemplo.ms1.orch.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("exemplo")
public class ExemploController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioResponse> getUser(){
        return usuarioService.listaUsuarios();
    }

}
