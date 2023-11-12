package br.com.fiap.models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Sistema {
    private Categoria categoria;
    private List<Caso> casos = new ArrayList<Caso>();
    private List<Usuario> usuarios = new ArrayList<Usuario>();

    public Sistema() {
        this.categoria = new Categoria();
    }

    public List<Caso> getCasos() {
        return casos;
    }

    public void addCaso(Caso caso) {
        casos.add(caso);
    }

    public void saveData() {
        JSONArray json = new JSONArray();
        for (Usuario usuario : usuarios) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("nome", usuario.getNome());
                obj.put("email", usuario.getEmail());
                obj.put("senha", usuario.getSenha());
                obj.put("fotopath", usuario.getFotopath());
                json.put(obj);
            } catch (JSONException e) {
                System.out.println("Erro ao salvar arquivo JSON: " + e.getMessage());
            }
        }
        try {
            File file = new File("data.json");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(json.toString());
            bw.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo JSON: " + e.getMessage());
        }

        JSONArray jsonCasos = new JSONArray();
        for (Caso caso : casos) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("acidente", caso.getAcidente());
                obj.put("local", caso.getLocal());
                obj.put("dataHora", caso.getDataHora());
                obj.put("tipo", caso.getTipo());
                obj.put("status", caso.getStatus());
                obj.put("usuario", caso.getUsuario().getEmail());
                jsonCasos.put(obj);
            } catch (JSONException e) {
                System.out.println("Erro ao salvar arquivo JSON: " + e.getMessage());
            }
        }
        try {
            File file = new File("casos.json");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(jsonCasos.toString());
            bw.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo JSON: " + e.getMessage());
        }
    }

    public void loadData() {
        try {
            File file = new File("data.json");
            if (!file.exists()) {
                System.out.println("Arquivo não encontrado!");
                return;
            }
            String jsonStr = new String(java.nio.file.Files.readAllBytes(file.toPath()));
            JSONArray json = new JSONArray(jsonStr);
            for (int i = 0; i < json.length(); i++) {
                JSONObject obj = json.getJSONObject(i);
                Usuario usuario = new Usuario(obj.getString("nome"), obj.getString("email"), obj.getString("senha"),
                        obj.getString("fotopath"));
                usuarios.add(usuario);
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar arquivo JSON: " + e.getMessage());
        }

        try {
            File file = new File("casos.json");
            if (!file.exists()) {
                System.out.println("Arquivo não encontrado!");
                return;
            }
            String jsonStr = new String(java.nio.file.Files.readAllBytes(file.toPath()));
            JSONArray json = new JSONArray(jsonStr);
            for (int i = 0; i < json.length(); i++) {
                JSONObject obj = json.getJSONObject(i);
                Usuario usuario = buscarUsuario(obj.getString("usuario"));
                Caso caso = new Caso(obj.getString("acidente"), obj.getString("local"), obj.getString("dataHora"),
                        obj.getString("tipo"), obj.getString("status"), usuario);
                casos.add(caso);
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar arquivo JSON: " + e.getMessage());
        }
    }

    public void addUsuario(String nome, String email, String senha) {
        Usuario usuario = new Usuario(nome, email, senha);
        usuarios.add(usuario);
    }

    public void addUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public Usuario buscarUsuario(String email) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email)) {
                return usuario;
            }
        }
        return null;
    }

    public Categoria getCategoria() {
        return categoria;
    }
}
