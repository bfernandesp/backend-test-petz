package br.com.petz.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.petz.entity.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, BigInteger> {

	@Query("SELECT p FROM Pet p WHERE p.nome = :nome")
	public List<Pet> findByNome(@Param("nome") String nome);

}
