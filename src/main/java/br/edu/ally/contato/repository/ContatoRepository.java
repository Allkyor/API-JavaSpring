package br.edu.ally.contato.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.ally.contato.entity.Contato;
public interface ContatoRepository extends JpaRepository<Contato, Long> {}
