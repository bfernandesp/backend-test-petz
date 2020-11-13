package br.com.petz.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigInteger;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.google.gson.Gson;

import br.com.petz.controller.request.ClienteRequest;
import br.com.petz.entity.Cliente;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PetControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test 
	public void salvar() throws Exception {
		ClienteRequest clienteRequest = new ClienteRequest();
		clienteRequest.setNome("Bruno Fernandes");
		clienteRequest.setCpf("123.123.123-87");
		clienteRequest.setEndereco("Rua Marte, Jardim Tupanci - 429 - Barueri-SP");
		
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/cliente")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(clienteRequest)));
		
		assertEquals(result.andReturn().getResponse().getContentAsString(), Boolean.TRUE.toString());
	}
	
	@Test 
	public void alterar() throws Exception {
		Cliente cliente = obterClienteMok();
		cliente.setId(BigInteger.ONE);
		
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put("/cliente")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(cliente)));
		
		assertEquals(result.andReturn().getResponse().getContentAsString(), Boolean.TRUE.toString());
	}
	
	@Test 
	public void listarTodos() throws Exception {
		ClienteRequest clienteRequest = new ClienteRequest();
		clienteRequest.setNome("Bruno Fernandes");
		clienteRequest.setCpf("123.123.123-87");
		clienteRequest.setEndereco("Rua Marte, Jardim Tupanci - 429 - Barueri-SP");
		
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/cliente")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		assertEquals(result.andReturn().getResponse().getContentAsString(), new ArrayList<>().toString());
	}
	
	@Test 
	public void excluir() throws Exception {
		ClienteRequest clienteRequest = new ClienteRequest();
		clienteRequest.setNome("Bruno Fernandes");
		clienteRequest.setCpf("123.123.123-87");
		clienteRequest.setEndereco("Rua Marte, Jardim Tupanci - 429 - Barueri-SP");
		
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/cliente")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		assertEquals(result.andReturn().getResponse().getContentAsString(), new ArrayList<>().toString());
	}	
	
	private Cliente obterClienteMok() {
		Cliente cliente = Cliente.builder()
				.nome("Bruno Fernandes")
				.cpf("123.123.123-87")
				.endereco("Rua Marte, Jardim Tupanci - 429 - Barueri-SP").build();
		return cliente;
	}
}
