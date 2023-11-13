package br.com.fiap.banco.model;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.fiap.banco.serializer.CustomDateSerializer;

public class Caso {

	private int codigo;
	private String marca;
	private String modelo;
	private String endereco;
	private String imagem;
	private String status;
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date dataCriacao;

	private User user;

	public Caso() {
	}

	public Caso(int codigo, String marca, String modelo, String endereco,
			String status, String imagem, Date dataCriacao, User user) {
		this.codigo = codigo;
		this.marca = marca;
		this.modelo = modelo;
		this.endereco = endereco;
		this.status = status;
		this.imagem = imagem;
		this.dataCriacao = dataCriacao;
		this.user = user;

	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
