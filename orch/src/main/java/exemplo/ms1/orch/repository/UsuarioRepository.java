package exemplo.ms1.orch.repository;

import exemplo.ms1.orch.repository.response.UsuarioResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "usuario", url = "http://localhost:9090/usuario")
public interface UsuarioRepository {
    @RequestMapping(method = RequestMethod.GET, value = "/")
    List<UsuarioResponse> retornaUsuarios();
}
