package br.com.petz.controller;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.petz.controller.request.ClienteRequest;
import br.com.petz.entity.Cliente;
import br.com.petz.service.ClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService service;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value="Salvar Cliente", response=ResponseEntity.class, responseContainer = "Set")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Cliente salvo com sucesso."),
			@ApiResponse(code = 400, message = "Erro ao salvar Cliente.")
	})
	@PostMapping
	public ResponseEntity salvar(@RequestBody ClienteRequest cliente) {
		
		try {
			service.salvarOuAtualizar(cliente);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(Boolean.FALSE, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity(Boolean.TRUE, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value="Alterar Cliente", response=ResponseEntity.class, responseContainer = "Set")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Cliente alterado com sucesso."),
			@ApiResponse(code = 400, message = "Erro ao alterar Cliente.")
	})
	@PutMapping
	public ResponseEntity alterar(@RequestBody Cliente cliente) {
		
		try {
			service.salvarOuAtualizar(cliente);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(Boolean.FALSE, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity(Boolean.TRUE, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value="Listar Clientes", response=ResponseEntity.class, responseContainer = "Set")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Clientes listados com sucesso."),
			@ApiResponse(code = 400, message = "Erro ao buscar Clientes.")
	})
	@GetMapping
	public ResponseEntity listarTodos() {
		
		try {
			return new ResponseEntity(service.listarTodos(), HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(Boolean.FALSE, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value="Excluir Cliente", response=ResponseEntity.class, responseContainer = "Set")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Cliente excluído com sucesso."),
			@ApiResponse(code = 400, message = "Erro ao excluir Cliente.")
	})
	@DeleteMapping
	public ResponseEntity excluir(@RequestParam(value="idCliente", required=true) BigInteger idCliente) {
		try {
			service.excluir(idCliente);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(Boolean.FALSE, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(Boolean.TRUE, HttpStatus.OK);
	}	
}
