package br.com.petz.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 7393574876270238353L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private BigInteger id;
	private String nome;
	private String cpf;
	private String endereco;
	private String telefone;

	
}
