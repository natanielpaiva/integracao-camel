package exemplo.ms1.orch.repository;

import exemplo.ms1.orch.repository.response.ImovelResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "imovel", url = "http://localhost:9091/imovel")
public interface ImovelRepository {
    @RequestMapping(method = RequestMethod.GET, value = "/")
    List<ImovelResponse> retornaImovel();
}
