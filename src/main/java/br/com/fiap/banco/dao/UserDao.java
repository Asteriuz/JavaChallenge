package br.com.fiap.banco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.banco.exception.IdNotFoundException;
import br.com.fiap.banco.model.User;

public class UserDao {

	private Connection conn;

	public UserDao(Connection conn) {
		this.conn = conn;
	}

	public List<User> pesquisarPorNome(String nome) throws SQLException {
		// Criar o objeto com o comando SQL
		PreparedStatement stm = conn.prepareStatement("select * from TDSS_TB_USER where NM_USER like ?");
		// Setar o parametro no comando SQL
		stm.setString(1, "%" + nome + "%");
		// Executar o comando SQL
		ResultSet result = stm.executeQuery();
		// Criar a lista de vagas
		List<User> lista = new ArrayList<>();
		// Recuperar os vagas encontrados e adicionar na lista
		while (result.next()) {
			User user = parse(result);
			lista.add(user);
		}
		// Retornar a lista
		return lista;
	}

	public List<User> listar() throws ClassNotFoundException, SQLException {

		// Criar o comando SQL
		PreparedStatement stm = conn.prepareStatement("select * from TDSS_TB_USER");

		// Executar o comando SQL
		ResultSet result = stm.executeQuery();

		// Criar a lista de vagas
		List<User> lista = new ArrayList<User>();

		// Percorrer todos os registros encontrados
		while (result.next()) {
			User prod = parse(result);
			// Adicionar na lista
			lista.add(prod);
		}
		// Retornar a lista de vaga
		return lista;
	}

	private User parse(ResultSet result) throws SQLException {
		int id = result.getInt("cd_user");
		String nome = result.getString("nm_user");
		String email = result.getString("ds_email");
		User user = new User(id, nome, email);
		return user;
	}

	public User pesquisar(int codigo) throws SQLException, IdNotFoundException {
		PreparedStatement stm = conn.prepareStatement("select * from TDSS_TB_USER where cd_user = ?");
		stm.setInt(1, codigo);

		ResultSet result = stm.executeQuery();

		if (!result.next()) {
			throw new IdNotFoundException("Categoria não encontrada");
		}

		User user = parse(result);

		return user;
	}

	public void cadastrar(User user) throws SQLException {
		// PreparedStatement
		PreparedStatement stm = conn
				.prepareStatement("insert into TDSS_TB_USER values (TDSS_TB_USER_SEQ.NEXTVAL, ?, ?)");
		// Setar os parametros na Query
		stm.setString(1, user.getNome());
		stm.setString(2, user.getEmail());

		stm.executeUpdate();
	}

	public void atualizar(User user) throws ClassNotFoundException, SQLException, IdNotFoundException {
		// PreparedStatement
		PreparedStatement stm = conn
				.prepareStatement("update TDSS_TB_USER set nm_user = ?, ds_email = ? where cd_user = ?");
		// Setar os parametros na Query
		stm.setString(1, user.getNome());
		stm.setString(2, user.getEmail());
		stm.setInt(3, user.getCodigo());
		// Executar a Query
		int linha = stm.executeUpdate();
		if (linha == 0)
			throw new IdNotFoundException("Usuário não encontrado para atualização");

	}

	public void remover(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {

		PreparedStatement stm = conn.prepareStatement("delete from TDSS_TB_USER where cd_user = ?");
		stm.setInt(1, id);

		int linha = stm.executeUpdate();
		if (linha == 0)
			throw new IdNotFoundException("Usuário não encontrado para remoção");
	}

}