package br.com.petz.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.petz.entity.Cliente;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ClienteRepositoryTest {
	
	@Autowired
	private ClienteRepository repository;
	
	@Test
	public void salvarBuscarAlterarDeletarPessoa() {
		Cliente cliente = new Cliente();
		cliente.setNome("Breno");
		cliente.setCpf("123.123.123-87");
		cliente.setTelefone("(11) 99345-1234");
		repository.saveAndFlush(cliente);
		
		//Salvando
		assertThat(cliente.getId()).isNotNull();
		assertThat(cliente.getNome()).isEqualTo("Breno");
		assertThat(cliente.getCpf()).isEqualTo("123.123.123-87");
		assertThat(cliente.getTelefone()).isEqualTo("(11) 99345-1234");
		
		//Buscando
		cliente = repository.getOne(cliente.getId());
		assertThat(cliente.getId()).isNotNull();
		assertThat(cliente.getNome()).isEqualTo("Breno");
		assertThat(cliente.getCpf()).isEqualTo("123.123.123-87");
		assertThat(cliente.getTelefone()).isEqualTo("(11) 99345-1234");
		
		//Alterando
		cliente.setNome("Breno Nunes Fernandes");
		cliente.setCpf("123.456.789-34");
		cliente.setTelefone("(11) 98540-3456");
		repository.saveAndFlush(cliente);
		assertThat(cliente.getId()).isNotNull();
		assertThat(cliente.getNome()).isEqualTo("Breno Nunes Fernandes");
		assertThat(cliente.getCpf()).isEqualTo("123.456.789-34");
		assertThat(cliente.getTelefone()).isEqualTo("(11) 98540-3456");
		
		//Deletando
		repository.delete(cliente);
	}
}
