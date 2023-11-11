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
import br.com.fiap.banco.exception.BadInfoException;
import br.com.fiap.banco.exception.IdNotFoundException;
import br.com.fiap.banco.factory.ConnectionFactory;
import br.com.fiap.banco.model.Caso;

public class CasoService {

    private CasosDao casosDao;

    public CasoService() throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionFactory.getConnection();
        casosDao = new CasosDao(conn);
    }

    public void cadastrar(Caso vaga) throws ClassNotFoundException, SQLException, BadInfoException {
        validar(vaga);
        casosDao.cadastrar(vaga);
    }

    private void validar(Caso caso) throws BadInfoException, SQLException {
        // Implementar algumas regras:
        // - O nome deve ter no minimo 5 caracteres
        // - nenhum atributo pode ser nulo

        if (caso.getNomeCompleto().length() < 5) {
            throw new BadInfoException("O nome deve ter no minimo 5 caracteres");
        }
        if (caso.getEmail() == null) {
            throw new BadInfoException("O email não pode ser nulo");
        }
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

    public void atualizar(Caso vaga)
            throws ClassNotFoundException, SQLException, IdNotFoundException, BadInfoException {
        validar(vaga);
        casosDao.atualizar(vaga);
    }

    public void remover(int vaga) throws ClassNotFoundException, SQLException, IdNotFoundException {
        casosDao.remover(vaga);
    }

    public List<Caso> listar() throws ClassNotFoundException, SQLException {
        return casosDao.listar();
    }

    public List<Caso> pesquisarPorNome(String nome) throws SQLException {
        return casosDao.pesquisarPorNome(nome);
    }

    public Caso pesquisar(int codigo) throws ClassNotFoundException, SQLException, IdNotFoundException {
        Caso p = casosDao.pesquisar(codigo);
        return p;
    }

    public List<Caso> pesquisarPorStatus(String status) throws SQLException {
        return casosDao.pesquisarPorStatus(status);
    }
}