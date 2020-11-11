package br.com.petz.controller.request;

import java.math.BigInteger;

import lombok.Data;

@Data
public class PetAlteracaoRequest {
	private BigInteger idPet;
	private BigInteger idCliente;
	private String nome;
	private String raca;
}
