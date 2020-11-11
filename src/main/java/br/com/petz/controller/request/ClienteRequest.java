package br.com.petz.controller.request;

import lombok.Data;

@Data
public class ClienteRequest {
	private String nome;
	private String cpf;
	private String endereco;
	private String telefone;
}
