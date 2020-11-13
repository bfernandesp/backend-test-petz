package br.com.petz.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.google.gson.Gson;

import br.com.petz.controller.request.ClienteRequest;
import br.com.petz.entity.Cliente;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ClienteControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test 
	public void salvar() throws Exception {
		
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/cliente")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(obterClienteRequestMock())));
		
		assertNotNull(new Gson().fromJson(result.andReturn().getResponse().getContentAsString(), Cliente.class).getId());
	}
	
	@Test 
	public void alterar() throws Exception {
		Cliente cliente = obterClienteMok();
		cliente.setId(BigInteger.ONE);
		
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put("/cliente")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(cliente)));
		
		assertNotNull(new Gson().fromJson(result.andReturn().getResponse().getContentAsString(), Cliente.class).getId());
	}
	
	@Test 
	public void listarTodos() throws Exception {
		salvar();
		
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/cliente")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print());
		
		@SuppressWarnings("unchecked")
		List<Cliente> listaRetorno = new ArrayList<Cliente>(new Gson().fromJson(result.andReturn().getResponse().getContentAsString(), ArrayList.class));
		
		assertTrue(listaRetorno != null && !listaRetorno.isEmpty());
	}
	
	@Test 
	public void excluir() throws Exception {
		salvar();
		
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.delete("/cliente")
				.param("idCliente", String.valueOf(BigInteger.ONE))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		assertEquals(result.andReturn().getResponse().getContentAsString(), "true");
	}	
	
	private Cliente obterClienteMok() {
		Cliente cliente = Cliente.builder()
				.nome("Bruno Fernandes")
				.cpf("123.123.123-87")
				.endereco("Rua Marte, Jardim Tupanci - 429 - Barueri-SP").build();
		return cliente;
	}
	
	private ClienteRequest obterClienteRequestMock() {
		ClienteRequest clienteRequest = new ClienteRequest();
		clienteRequest.setNome("Bruno Fernandes");
		clienteRequest.setCpf("123.123.123-87");
		clienteRequest.setEndereco("Rua Marte, Jardim Tupanci - 429 - Barueri-SP");
		
		return clienteRequest;
	}
}
