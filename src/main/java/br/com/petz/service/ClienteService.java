package br.com.petz.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.petz.controller.request.ClienteRequest;
import br.com.petz.entity.Cliente;
import br.com.petz.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	public Cliente obterPorId(BigInteger idCliente){
		Optional<Cliente> opCliente = repository.findById(idCliente);
		return opCliente != null && opCliente.isPresent() ? opCliente.get() : null;
	} 

	public List<Cliente> obterPorNome(String nome){
		return repository.findByNome(nome);
	} 
	
	public Cliente salvarOuAtualizar(Object cliente) {
		if (cliente instanceof ClienteRequest) {
			Cliente entity = Cliente.builder()
			.nome(((ClienteRequest) cliente).getNome())
			.cpf(((ClienteRequest) cliente).getCpf())
			.endereco(((ClienteRequest) cliente).getEndereco())
			.telefone(((ClienteRequest) cliente).getTelefone()).build();
			return repository.save(entity);
		}else {
			return repository.save((Cliente)cliente);
		}
	}
	
	public void excluir(BigInteger idCliente) {
		repository.deleteById(idCliente);
	}
	
	public List<Cliente> listarTodos(){
		return repository.findAll();
	}
}
