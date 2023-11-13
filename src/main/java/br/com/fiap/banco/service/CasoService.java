package br.com.fiap.banco.service;

// CREATE TABLE TDSS_TB_CASOS (
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
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.banco.dao.CasosDao;
import br.com.fiap.banco.dao.UserDao;
import br.com.fiap.banco.exception.BadInfoException;
import br.com.fiap.banco.exception.IdNotFoundException;
import br.com.fiap.banco.factory.ConnectionFactory;
import br.com.fiap.banco.model.Caso;

public class CasoService {

    private CasosDao casosDao;
    private UserDao userDao;

    public CasoService() throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionFactory.getConnection();
        casosDao = new CasosDao(conn);
        userDao = new UserDao(conn);
    }

    public void cadastrar(Caso caso) throws ClassNotFoundException, SQLException, BadInfoException {
        validar(caso);
        casosDao.cadastrar(caso);
    }

    private void validar(Caso caso) throws BadInfoException, SQLException {
        // Implementar algumas regras:
        // - O nome deve ter no minimo 5 caracteres
        // - nenhum atributo pode ser nulo
        if (caso.getMarca() == null) {
            throw new BadInfoException("A marca não pode ser nula");
        }
        if (caso.getModelo() == null) {
            throw new BadInfoException("O modelo não pode ser nulo");
        }
        if (caso.getEndereco() == null) {
            throw new BadInfoException("O endereco não pode ser nulo");
        }
        if (caso.getStatus() == null) {
            throw new BadInfoException("O status não pode ser nulo");
        }
        if (caso.getImagem() == null) {
            throw new BadInfoException("A imagem não pode ser nula");
        }
        if (caso.getDataCriacao() == null) {
            throw new BadInfoException("A data de criação não pode ser nula");
        }

    }

    public void atualizar(Caso caso)
            throws ClassNotFoundException, SQLException, IdNotFoundException, BadInfoException {
        validar(caso);
        casosDao.atualizar(caso);
    }

    public void remover(int caso) throws ClassNotFoundException, SQLException, IdNotFoundException {
        casosDao.remover(caso);
    }

    public List<Caso> listar() throws ClassNotFoundException, SQLException {
        return casosDao.listar();
    }

    public Caso pesquisar(int codigo) throws ClassNotFoundException, SQLException, IdNotFoundException {
        Caso p = casosDao.pesquisar(codigo);
        return p;
    }

    public List<Caso> pesquisarPorStatus(String status) throws SQLException {
        return casosDao.pesquisarPorStatus(status);
    }

    public List<Caso> pesquisarPorUser(int codigo) throws ClassNotFoundException, SQLException, IdNotFoundException {
        if (userDao.pesquisar(codigo) == null) {
            throw new IdNotFoundException("Usuário nao encontrada");
        }
        return casosDao.pesquisarPorUser(codigo);
    }

    public List<Caso> pesquisarPorNomeUser(String nome) throws ClassNotFoundException, SQLException {
        return casosDao.pesquisarPorNomeUser(nome);
    }

}