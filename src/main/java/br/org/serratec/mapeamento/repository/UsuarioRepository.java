package br.org.serratec.mapeamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.serratec.mapeamento.domain.Cliente;

@Repository
public interface UsuarioRepository extends JpaRepository<Cliente, Long>{

}
