package exemplo.ms3.imoveis.repository;

import exemplo.ms3.imoveis.domain.Imovel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImovelRepository extends JpaRepository<Imovel, Long> {
}
