package br.org.serratec.mapeamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.serratec.mapeamento.domain.Produto;

//jpa repository permite realizar os comandos no banco (recebe a classe e o tipo de dado do id)

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
