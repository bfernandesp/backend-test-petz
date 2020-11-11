package br.com.petz.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.petz.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, BigInteger> {

	@Query("SELECT c FROM Cliente c WHERE c.nome = :nome")
	public List<Cliente> findByNome(@Param("nome") String nome);

}
