package br.com.fiap.banco.dao;

// CREATE TABLE TDSS_TB_CASO (
// 	CD_CASO NUMBER (10) NOT NULL,
// 	NM_COMPLETO VARCHAR2 (100) NOT NULL,
// 	DS_EMAIL VARCHAR2 (100) NOT NULL,
// 	DS_MARCA VARCHAR2 (100) NOT NULL,
// 	DS_MODELO VARCHAR2 (100) NOT NULL,
// 	DS_IMAGEM BLOB NOT NULL,
// 	DS_ENDERECO VARCHAR2 (100) NOT NULL,
// 	DT_CRIACAO DATE NOT NULL,
// 	DS_STATUS VARCHAR2 (100) NOT NULL,
// 	CONSTRAINT TDSS_TB_CASOS_PK PRIMARY KEY (CD_CASO)
// );

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import br.com.fiap.banco.exception.IdNotFoundException;
import br.com.fiap.banco.model.Caso;

//Realiza as acoes de CRUD (Create, Read, Update, Delete) no banco de dados
public class CasosDao {

	private Connection conn;

	public CasosDao(Connection conn) {
		this.conn = conn;
	}

	public List<Caso> pesquisarPorNome(String titulo) throws SQLException {
		// Criar o objeto com o comando SQL
		PreparedStatement stm = conn.prepareStatement("select * from TDSS_TB_CASO where nm_completo like ?");
		// Setar o parametro no comando SQL
		stm.setString(1, "%" + titulo + "%");
		// Executar o comando SQL
		ResultSet result = stm.executeQuery();
		// Criar a lista de vagas
		List<Caso> lista = new ArrayList<>();
		// Recuperar os vagas encontrados e adicionar na lista
		while (result.next()) {
			Caso vaga = parse(result);
			lista.add(vaga);
		}
		// Retornar a lista
		return lista;
	}

	public void cadastrar(Caso caso) throws ClassNotFoundException, SQLException {

		// Criar o objeto com o comando SQL configuravel
		PreparedStatement stm = conn.prepareStatement(
				"INSERT INTO TDSS_TB_CASO (cd_caso, nm_completo, ds_email, ds_marca, ds_modelo, ds_endereco, ds_imagem, ds_status, dt_criacao) "
						+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?)");

		// Setar os valores no comando SQL
		stm.setInt(1, caso.getCodigo());
		stm.setString(2, caso.getNomeCompleto());
		stm.setString(3, caso.getEmail());
		stm.setString(4, caso.getMarca());
		stm.setString(5, caso.getModelo());
		stm.setString(6, caso.getEndereco());
		stm.setString(7, caso.getImagem());
		stm.setString(8, caso.getStatus());
		stm.setDate(9, new java.sql.Date(caso.getDataCriacao().getTime()));

		// Executar o comando SQL
		stm.executeUpdate();
	}

	public List<Caso> listar() throws ClassNotFoundException, SQLException {

		// Criar o comando SQL
		PreparedStatement stm = conn.prepareStatement("select * from TDSS_TB_CASO");

		// Executar o comando SQL
		ResultSet result = stm.executeQuery();

		// Criar a lista de vagas
		List<Caso> lista = new ArrayList<Caso>();

		// Percorrer todos os registros encontrados
		while (result.next()) {
			Caso prod = parse(result);
			// Adicionar na lista
			lista.add(prod);
		}
		// Retornar a lista de vaga
		return lista;
	}

	public Caso pesquisar(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {

		PreparedStatement stm = conn.prepareStatement("select * from TDSS_TB_CASO where cd_caso = ?");

		// Setar o id no comando sql (select)
		stm.setInt(1, id);

		// Executar o comando SQL
		ResultSet result = stm.executeQuery();

		// Verifica se encontrou o vaga
		if (!result.next()) {
			// Lança uma exception pois o vaga não foi encontrado
			throw new IdNotFoundException("Caso não encontrado");
		}
		Caso caso = parse(result);
		// Retornar o vaga
		return caso;
	}

	// Método auxiliar que recebe o resultado do banco e retorna o objeto vaga
	private Caso parse(ResultSet result) throws SQLException {
		// Obter os valores do caso
		int codigo = result.getInt("CD_CASO");
		String nomeCompleto = result.getString("NM_COMPLETO");
		String email = result.getString("DS_EMAIL");
		String marca = result.getString("DS_MARCA");
		String modelo = result.getString("DS_MODELO");
		String endereco = result.getString("DS_ENDERECO");
		String imagem = result.getString("DS_IMAGEM");
		String status = result.getString("DS_STATUS");
		Date dataCriacao = result.getDate("DT_CRIACAO");

		// Criar o objeto caso

		Caso caso = new Caso(codigo, nomeCompleto, email, marca, modelo, endereco, status, imagem, dataCriacao);

		return caso;
	}

	public void atualizar(Caso caso) throws ClassNotFoundException, SQLException, IdNotFoundException {

		// PreparedStatement
		PreparedStatement stm = conn.prepareStatement(
				"update TDSS_TB_CASO set nm_completo = ?, ds_email = ?, ds_marca = ?, ds_modelo = ?, ds_endereco = ?, ds_imagem = ?, ds_status = ?, dt_criacao = ? where cd_caso = ?");

		// Setar os parametros na Query
		stm.setString(1, caso.getNomeCompleto());
		stm.setString(2, caso.getEmail());
		stm.setString(3, caso.getMarca());
		stm.setString(4, caso.getModelo());
		stm.setString(5, caso.getEndereco());
		stm.setString(6, caso.getImagem());
		stm.setString(7, caso.getStatus());
		stm.setDate(8, new java.sql.Date(caso.getDataCriacao().getTime()));
		stm.setInt(9, caso.getCodigo());

		// Executar a Query
		int linha = stm.executeUpdate();
		if (linha == 0)
			throw new IdNotFoundException("Caso não encontrado para atualizar");
	}

	public void remover(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {

		// PreparedStatement
		PreparedStatement stm = conn.prepareStatement("delete from TDSS_TB_CASO where cd_caso = ?");
		// Setar os parametros na Query
		stm.setInt(1, id);
		// Executar a Query
		int linha = stm.executeUpdate();
		if (linha == 0)
			throw new IdNotFoundException("vaga não encontrado para remoção");
	}

	public List<Caso> pesquisarPorStatus(String status) throws SQLException {
		// Criar o objeto com o comando SQL
		PreparedStatement stm = conn.prepareStatement("select * from TDSS_TB_CASO where ds_status like ?");
		// Setar o parametro no comando SQL, ignorando case sensitive
		stm.setString(1, "%" + status.toUpperCase() + "%");
		// Executar o comando SQL
		ResultSet result = stm.executeQuery();
		// Criar a lista de vagas
		List<Caso> lista = new ArrayList<>();
		// Recuperar os vagas encontrados e adicionar na lista
		while (result.next()) {
			Caso vaga = parse(result);
			lista.add(vaga);
		}
		// Retornar a lista
		return lista;
	}
}