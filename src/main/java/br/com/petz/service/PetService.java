package br.com.petz.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.petz.controller.request.PetAlteracaoRequest;
import br.com.petz.controller.request.PetRequest;
import br.com.petz.entity.Pet;
import br.com.petz.repository.PetRepository;

@Service
public class PetService {
	
	@Autowired
	private PetRepository repository;
	
	@Autowired
	private ClienteService clienteService;
	
	public Pet obterPorId(BigInteger idCliente){
		Optional<Pet> opPet = repository.findById(idCliente);
		return opPet != null && opPet.isPresent() ? opPet.get() : null;
	} 

	public List<Pet> obterPorNome(String nome){
		return repository.findByNome(nome);
	} 
	
	public void salvarOuAtualizar(Object pet) {
		if (pet instanceof PetRequest) {
			//Salvar novo
			Pet entity = new Pet();
			entity.setNome(((PetRequest) pet).getNome());
			entity.setIdCliente(clienteService.obterPorId(((PetRequest) pet).getIdCliente()).getId());
			entity.setRaca(((PetRequest) pet).getRaca());
			repository.save(entity);
		}else {
			//Alterar
			Pet entity = new Pet();
			entity.setId(((PetAlteracaoRequest) pet).getIdPet());
			entity.setNome(((PetAlteracaoRequest) pet).getNome());
			entity.setIdCliente(clienteService.obterPorId(((PetAlteracaoRequest) pet).getIdCliente()).getId());
			entity.setRaca(((PetAlteracaoRequest) pet).getRaca());
			repository.save(entity);
		}
	}
	
	public void excluir(BigInteger idCliente) {
		repository.deleteById(idCliente);
	}
	
	public List<Pet> listarTodos(){
		return repository.findAll();
	}
}
