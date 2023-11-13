package br.com.fiap.banco.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.banco.dao.UserDao;
import br.com.fiap.banco.dao.CasosDao;
import br.com.fiap.banco.exception.BadInfoException;
import br.com.fiap.banco.exception.HasChildException;
import br.com.fiap.banco.exception.IdNotFoundException;
import br.com.fiap.banco.factory.ConnectionFactory;
import br.com.fiap.banco.model.User;

public class UserService {

    private UserDao userDao;
    private CasosDao casosDao;

    public UserService() throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionFactory.getConnection();
        userDao = new UserDao(conn);
        casosDao = new CasosDao(conn);
    }

    public void cadastrar(User user) throws ClassNotFoundException, SQLException, BadInfoException {
        validar(user);
        userDao.cadastrar(user);
    }

    private void validar(User user) throws BadInfoException, SQLException {
        // Implementar algumas regras:
        // - O nome deve ter no minimo 5 caracteres
        if (user.getNome().length() < 5) {
            throw new BadInfoException("O nome deve ter no minimo 5 caracteres");
        }
    }

    public void atualizar(User user)
            throws ClassNotFoundException, SQLException, IdNotFoundException, BadInfoException {
        validar(user);
        userDao.atualizar(user);
    }

    public void remover(int user)
            throws ClassNotFoundException, SQLException, IdNotFoundException, HasChildException {
        // checar se a empresa tem vagas
        if (!casosDao.pesquisarPorUser(user).isEmpty()) {
            throw new HasChildException("O Usuário possui casos cadastrados");
        }
        userDao.remover(user);
    }

    public List<User> listar() throws ClassNotFoundException, SQLException {
        return userDao.listar();
    }

    public List<User> pesquisarPorNome(String nome) throws SQLException {
        return userDao.pesquisarPorNome(nome);
    }

    public User pesquisar(int codigo) throws ClassNotFoundException, SQLException, IdNotFoundException {
        User p = userDao.pesquisar(codigo);
        return p;
    }

}