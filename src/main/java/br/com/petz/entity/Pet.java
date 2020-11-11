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
@Table(name = "pet")
public class Pet implements Serializable {

	private static final long serialVersionUID = 7010000576987220626L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private BigInteger id;
	
	private BigInteger idCliente;
	
	private String nome;
	private String raca;
	
}
