package br.com.fiap.models;

public class Caso {
    String acidente;
    String local;
    String dataHora;
    String tipo;
    String status;
    Usuario usuario;

 
    public Caso(String acidente, String local, String dataHora, String tipo, String status,
            Usuario usuario) {
        this.acidente = acidente;
        this.local = local;
        this.dataHora = dataHora;
        this.tipo = tipo;
        this.status = status;
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getAcidente() {
        return acidente;
    }

    public void setAcidente(String acidente) {
        this.acidente = acidente;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
