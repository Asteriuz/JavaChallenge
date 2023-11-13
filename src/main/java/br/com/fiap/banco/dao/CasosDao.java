package br.com.fiap.banco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import br.com.fiap.banco.exception.IdNotFoundException;
import br.com.fiap.banco.model.Caso;
import br.com.fiap.banco.model.User;

//Realiza as acoes de CRUD (Create, Read, Update, Delete) no banco de dados
public class CasosDao {

	private Connection conn;

	public CasosDao(Connection conn) {
		this.conn = conn;
	}

	public void cadastrar(Caso caso) throws ClassNotFoundException, SQLException {

		// Criar o objeto com o comando SQL configuravel
		PreparedStatement stm = conn.prepareStatement(
				"insert into TDSS_TB_CASO (CD_CASO, DS_MARCA, DS_MODELO, DS_IMAGEM, DS_ENDERECO, DT_CRIACAO, DS_STATUS, CD_USER) values (TDSS_TB_CASO_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)");

		// Setar os parametros na Query
		stm.setString(1, caso.getMarca());
		stm.setString(2, caso.getModelo());
		stm.setString(3, caso.getImagem());
		stm.setString(4, caso.getEndereco());
		stm.setDate(5, new java.sql.Date(caso.getDataCriacao().getTime()));
		stm.setString(6, caso.getStatus());
		stm.setInt(7, caso.getUser().getCodigo());

		// Executar o comando SQL
		stm.executeUpdate();
	}

	public List<Caso> listar() throws ClassNotFoundException, SQLException {

		// Criar o comando SQL
		PreparedStatement stm = conn.prepareStatement("select * from TDSS_TB_CASO");

		// Executar o comando SQL
		ResultSet result = stm.executeQuery();

		// Criar a lista de casos
		List<Caso> lista = new ArrayList<Caso>();

		// Percorrer todos os registros encontrados
		while (result.next()) {
			Caso prod = parse(result);
			// Adicionar na lista
			lista.add(prod);
		}
		// Retornar a lista de caso
		return lista;
	}

	public Caso pesquisar(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {

		PreparedStatement stm = conn.prepareStatement("select * from TDSS_TB_CASO where cd_caso = ?");

		// Setar o id no comando sql (select)
		stm.setInt(1, id);

		// Executar o comando SQL
		ResultSet result = stm.executeQuery();

		// Verifica se encontrou o caso
		if (!result.next()) {
			// Lança uma exception pois o caso não foi encontrado
			throw new IdNotFoundException("Caso não encontrado");
		}
		Caso caso = parse(result);
		// Retornar o caso
		return caso;
	}

	// Método auxiliar que recebe o resultado do banco e retorna o objeto caso
	private Caso parse(ResultSet result) throws SQLException {
		// Recuperar os atributos do caso
		int codigo = result.getInt("cd_caso");
		String marca = result.getString("ds_marca");
		String modelo = result.getString("ds_modelo");
		String endereco = result.getString("ds_endereco");
		String status = result.getString("ds_status");
		String imagem = result.getString("ds_imagem");
		Date dataCriacao = result.getDate("dt_criacao");
		int codigoUser = result.getInt("cd_user");

		// get empresa by codigo
		UserDao dao = new UserDao(conn);
		User user = null;
		try {
			user = dao.pesquisar(codigoUser);
		} catch (IdNotFoundException e) {
			e.printStackTrace();
		}

		// Criar o objeto caso
		Caso caso = new Caso(codigo, marca, modelo, endereco, status, imagem, dataCriacao, user);

		return caso;
	}

	public void atualizar(Caso caso) throws ClassNotFoundException, SQLException, IdNotFoundException {

		// PreparedStatement
		PreparedStatement stm = conn.prepareStatement(
				"update TDSS_TB_CASO set DS_MARCA = ?, DS_MODELO = ?, DS_IMAGEM = ?, DS_ENDERECO = ?, DT_CRIACAO = ?, DS_STATUS = ?, CD_USER = ? where cd_caso = ?");

		// Setar os parametros na Query
		stm.setString(1, caso.getMarca());
		stm.setString(2, caso.getModelo());
		stm.setString(3, caso.getImagem());
		stm.setString(4, caso.getEndereco());
		stm.setDate(5, new java.sql.Date(caso.getDataCriacao().getTime()));
		stm.setString(6, caso.getStatus());
		stm.setInt(7, caso.getUser().getCodigo());
		stm.setInt(8, caso.getCodigo());
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
			throw new IdNotFoundException("caso não encontrado para remoção");
	}

	public List<Caso> pesquisarPorStatus(String status) throws SQLException {
		// Criar o objeto com o comando SQL
		PreparedStatement stm = conn.prepareStatement("select * from TDSS_TB_CASO where ds_status like ?");
		// Setar o parametro no comando SQL, ignorando case sensitive
		stm.setString(1, "%" + status.toUpperCase() + "%");
		// Executar o comando SQL
		ResultSet result = stm.executeQuery();
		// Criar a lista de casos
		List<Caso> lista = new ArrayList<>();
		// Recuperar os casos encontrados e adicionar na lista
		while (result.next()) {
			Caso caso = parse(result);
			lista.add(caso);
		}
		// Retornar a lista
		return lista;
	}

	public List<Caso> pesquisarPorUser(int codigoUser) throws SQLException {
		// Criar o objeto com o comando SQL
		PreparedStatement stm = conn.prepareStatement("select * from TDSS_TB_CASO where cd_user = ?");
		// Setar o parametro no comando SQL
		stm.setInt(1, codigoUser);
		// Executar o comando SQL
		ResultSet result = stm.executeQuery();
		// Criar a lista de Casos
		List<Caso> lista = new ArrayList<>();
		// Recuperar os Casos encontrados e adicionar na lista
		while (result.next()) {
			Caso vaga = parse(result);
			lista.add(vaga);
		}
		// Retornar a lista
		return lista;
	}

	public List<Caso> pesquisarPorNomeUser(String nome) throws SQLException {
		// Criar o objeto com o comando SQL
		PreparedStatement stm = conn.prepareStatement(
				"select * from TDSS_TB_CASO where cd_user in (select cd_user from TDSS_TB_USER where nm_user like ?)");
		// Setar o parametro no comando SQL
		stm.setString(1, "%" + nome + "%");
		// Executar o comando SQL
		ResultSet result = stm.executeQuery();
		// Criar a lista de Casos
		List<Caso> lista = new ArrayList<>();
		// Recuperar os Casos encontrados e adicionar na lista
		while (result.next()) {
			Caso vaga = parse(result);
			lista.add(vaga);
		}
		// Retornar a lista
		return lista;
	}
}