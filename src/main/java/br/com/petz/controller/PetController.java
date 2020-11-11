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

import br.com.petz.controller.request.PetAlteracaoRequest;
import br.com.petz.controller.request.PetRequest;
import br.com.petz.service.PetService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/pet")
public class PetController {
	
	@Autowired
	private PetService service;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value="Salvar Pet", response=ResponseEntity.class, responseContainer = "Set")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Pet salvo com sucesso."),
			@ApiResponse(code = 400, message = "Erro ao salvar Pet.")
	})
	@PostMapping
	public ResponseEntity salvar(@RequestBody PetRequest pet) {
		
		try {
			service.salvarOuAtualizar(pet);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(Boolean.FALSE, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity(Boolean.TRUE, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value="Alterar Pet", response=ResponseEntity.class, responseContainer = "Set")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Pet alterado com sucesso."),
			@ApiResponse(code = 400, message = "Erro ao alterar Pet.")
	})
	@PutMapping
	public ResponseEntity alterar(@RequestBody PetAlteracaoRequest pet) {
		
		try {
			service.salvarOuAtualizar(pet);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(Boolean.FALSE, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity(Boolean.TRUE, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value="Listar Pets", response=ResponseEntity.class, responseContainer = "Set")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Pets listados com sucesso."),
			@ApiResponse(code = 400, message = "Erro ao buscar Pets.")
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
	@ApiOperation(value="Excluir Pet", response=ResponseEntity.class, responseContainer = "Set")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Pet excluído com sucesso."),
			@ApiResponse(code = 400, message = "Erro ao excluir Pet.")
	})
	@DeleteMapping
	public ResponseEntity excluir(@RequestParam(value="idPet", required=true) BigInteger idPet) {
		try {
			service.excluir(idPet);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(Boolean.FALSE, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(Boolean.TRUE, HttpStatus.OK);
	}	
}
