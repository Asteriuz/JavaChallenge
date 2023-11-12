package br.com.fiap.banco.model;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.fiap.banco.serializer.CustomDateSerializer;

public class Caso {

	private int codigo;
	private String nomeCompleto;
	private String email;
	private String marca;
	private String modelo;
	private String endereco;
	private String imagem;
	private String status;
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date dataCriacao;

	public Caso() {
	}

	public Caso(int codigo, String nomeCompleto, String email, String marca, String modelo, String endereco,
			String status, String imagem, Date dataCriacao) {
		this.codigo = codigo;
		this.nomeCompleto = nomeCompleto;
		this.email = email;
		this.marca = marca;
		this.modelo = modelo;
		this.endereco = endereco;
		this.status = status;
		this.imagem = imagem;
		this.dataCriacao = dataCriacao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

}
