package br.com.fiap.models;

import java.util.HashMap;
import java.util.Map;

public class Categoria {
    private Map<Integer, String> categoriasNormal;
    private Map<Integer, String> categoriasImportado;
    private Map<Integer, String> categoriasEspecial;

    public Categoria() {
        categoriasNormal = new HashMap<>();
        categoriasImportado = new HashMap<>();
        categoriasEspecial = new HashMap<>();

        categoriasNormal.put(10, "PASSEIO");
        categoriasNormal.put(14, "PICK-UP'S LEVES");
        categoriasNormal.put(16, "ESPORTIVOS");
        categoriasNormal.put(18, "MODELOS ESPECIAIS (PASSEIO)");
        categoriasNormal.put(20,
                "PICK- UP'S PESADAS - CARGA: FORD: F100, F1000 e RANGER (Cabine Simples ou Supercab) GM: ACD/10 e 20 , SILVERADO e S10 (Cabine Simples ou Estendida) TOYOTA PICK- UP E DODGE DAKOTA (Cabine Simples)");
        categoriasNormal.put(20,
                "PICK- UP'S PESADAS - PESSOAS: FORD: F100, F1000, F250 e RANGER (Cabine Dupla) GM: ACD/10 e 22 S10 e SILVERADO (Cabine Dupla), BONANZA, S10 BLAZER e VERANEIO. TOYOTA JEEP e GURGEL CARAJÁS DODGE DAKOTA CLUB CAB (Cabine Estendida) DERIVADAS DE PICK- UP PESADAS FORD e GM");
        categoriasNormal.put(30,
                "BICICLETAS MOTORIZADAS, MOTOCICLETAS, MOTONETAS COM REBOQUE OU “SIDE- CAR”, VEÍCULOS “ROMI- ISETTA” e VESPACAR.");
        categoriasNormal.put(40, "CAMINHÕES LEVES (PBT até 40 toneladas)");
        categoriasNormal.put(42, "CAMINHÕES PESADOS (PBT* acima de 42 toneladas, inclusive)");
        categoriasNormal.put(50, "REBOCADORES");
        categoriasNormal.put(52, "REBOQUES E SEMI-REBOQUES");
        categoriasNormal.put(58, "ÔNIBUS E MICROÔNIBUS COM COBRANÇA DE FRETE (exceto urbano com linha regular)");
        categoriasNormal.put(60, "ÔNIBUS, MICROÔNIBUS COM COBRANÇA DE FRETE (urbano com linha regular)");
        categoriasNormal.put(62, "ÔNIBUS, MICROÔNIBUS SEM COBRANÇA DE FRETE");
        categoriasNormal.put(68, "TRATORES E MÁQUINAS DE USO URBANO");
        categoriasNormal.put(70, "TRATORES E MÁQUINAS DE USO RURAL");
        categoriasNormal.put(72, "GUINCHOS (carro socorro)");
        categoriasNormal.put(80, "TÁXI");
        categoriasNormal.put(82, "LOTAÇÃO");
        categoriasNormal.put(84, "VEÍCULO ESCOLAR (transporte escolar)");
        categoriasNormal.put(88, "VIAGEM DE ENTREGA DENTRO DO TERRITÓRIO NACIONAL");
        categoriasNormal.put(89, "VIAGEM DE ENTREGA EXCLUSIVAMENTE PARA PAÍSES DA AMÉRICA DO SUL");
        categoriasNormal.put(90, "LOCADORAS");
        categoriasNormal.put(92, "VEÍCULOS FUNERÁRIOS");

        categoriasImportado.put(11, "PASSEIO");
        categoriasImportado.put(15, "PICK-UP'S LEVES");
        categoriasImportado.put(17, "ESPORTIVOS");
        categoriasImportado.put(19, "MODELOS ESPECIAIS (PASSEIO)");
        categoriasImportado.put(21,
                "PICK- UP'S PESADAS - CARGA: FORD: F100, F1000 e RANGER (Cabine Simples ou Supercab) GM: ACD/10 e 20, SILVERADO e S10 (Cabine Simples ou Estendida) TOYOTA PICK- UP E DODGE DAKOTA (Cabine Simples)");
        categoriasImportado.put(23,
                "PICK- UP'S PESADAS - PESSOAS: FORD: F100, F1000, F250 e RANGER (Cabine Dupla) GM: ACD/10 e 20, S10 e SILVERADO (Cabine Dupla), BONANZA, S10 BLAZER e VERANEIO. TOYOTA JEEP e GURGEL CARAJÁS DODGE DAKOTA CLUB CAB (Cabine Estendida) DERIVADAS DE PICK- UP PESADAS FORD e GM");
        categoriasImportado.put(31,
                "BICICLETAS MOTORIZADAS, MOTOCICLETAS, MOTONETAS COM REBOQUE OU “SIDE-CAR”, VEÍCULOS “ROMI-ISETTA” e VESPACAR.");
        categoriasImportado.put(41, "CAMINHÕES LEVES (PBT até 10 toneladas)");
        categoriasImportado.put(43, "CAMINHÕES PESADOS (PBT* acima de 10 toneladas, inclusive)");
        categoriasImportado.put(51, "REBOCADORES");
        categoriasImportado.put(53, "REBOQUES E SEMI-REBOQUES");
        categoriasImportado.put(59, "ÔNIBUS E MICROÔNIBUS COM COBRANÇA DE FRETE (exceto urbano com linha regular)");
        categoriasImportado.put(61, "ÔNIBUS, MICROÔNIBUS COM COBRANÇA DE FRETE (urbano com linha regular)");
        categoriasImportado.put(63, "ÔNIBUS, MICROÔNIBUS SEM COBRANÇA DE FRETE");
        categoriasImportado.put(69, "TRATORES E MÁQUINAS DE USO URBANO");
        categoriasImportado.put(71, "TRATORES E MÁQUINAS DE USO RURAL");
        categoriasImportado.put(73, "GUINCHOS (carro socorro)");
        categoriasImportado.put(81, "TÁXI");
        categoriasImportado.put(83, "LOTAÇÃO");
        categoriasImportado.put(85, "VEÍCULO ESCOLAR (transporte escolar)");
        categoriasImportado.put(88, "VIAGEM DE ENTREGA DENTRO DO TERRITÓRIO NACIONAL");
        categoriasImportado.put(89, "VIAGEM DE ENTREGA EXCLUSIVAMENTE PARA PAÍSES DA AMÉRICA DO SUL");
        categoriasImportado.put(91, "LOCADORAS");
        categoriasImportado.put(93, "VEÍCULOS FUNERÁRIOS");

        categoriasEspecial.put(94, "AMBULÂNCIAS");
        categoriasEspecial.put(95, "AUTO-ESCOLAS");
        categoriasEspecial.put(96, "BOMBEIROS");
        categoriasEspecial.put(97, "POLICIAMENTO");
        categoriasEspecial.put(98,
                "CASAS VOLANTES, TRAILERS, VEÍCULOS BAR, OFICINAS VOLANTES, VEÍCULOS PAGADO- RES OU PARA TRANSPORTE DE VALORES, HOSPITAIS VOLANTES, VEÍCULOS COM PLATAFORMA ELEVATÓRIA PARA REPAROS EM REDE ELÉTRICA E OUTROS SERVIÇOS, CAMINHÃO ESPARGIDOR DE ASFALTO, VARREDORA MECÂNICA E DESENTUPIDOR DE ESGOTOS E CANOS, VEÍCULOS PARA EXPOSIÇÃO DE PRODUTOS OU FINS PUBLICITÁRIOS");
        categoriasEspecial.put(99, "CHAPA DE FABRICANTE");
    }

    public void addCategoriaNormal(int number, String categoria) {
        categoriasNormal.put(number, categoria);
    }

    public String getCategoriaNormal(int number) {
        return categoriasNormal.get(number);
    }

    public void removeCategoriaNormal(int number) {
        categoriasNormal.remove(number);
    }

    public void addCategoriaImportado(int number, String categoria) {
        categoriasImportado.put(number, categoria);
    }

    public String getCategoriaImportado(int number) {
        return categoriasImportado.get(number);
    }

    public void removeCategoriaImportado(int number) {
        categoriasImportado.remove(number);
    }

    public void addCategoriaEspecial(int number, String categoria) {
        categoriasEspecial.put(number, categoria);
    }

    public String getCategoriaEspecial(int number) {
        return categoriasEspecial.get(number);
    }

    public void removeCategoriaEspecial(int number) {
        categoriasEspecial.remove(number);
    }

    public String getCategoria(int number) {
        String categoria = getCategoriaNormal(number);
        if (categoria != null) {
            return categoria;
        }
        categoria = getCategoriaImportado(number);
        if (categoria != null) {
            return categoria;
        }
        categoria = getCategoriaEspecial(number);
        if (categoria != null) {
            return categoria;
        }
        return null;
    }

    public HashMap<Integer, String> listarCategorias() {
        HashMap<Integer, String> categorias = new HashMap<Integer, String>();
        categorias.putAll(categoriasNormal);
        categorias.putAll(categoriasImportado);
        categorias.putAll(categoriasEspecial);
        return categorias;
    }

    public String getTipoCodigo(int number) {
        if (categoriasNormal.containsKey(number)) {
            return "NACIONAL";
        } else if (categoriasImportado.containsKey(number)) {
            return "IMPORTADO";
        } else if (categoriasEspecial.containsKey(number)) {
            return "ESPECIAL";
        } else {
            return null;
        }
    }

    public HashMap<Integer, String> listarCategoriasSemRepeticao() {
        HashMap<Integer, String> categorias = new HashMap<Integer, String>();
        categorias.putAll(categoriasNormal);
        categorias.putAll(categoriasImportado);
        categorias.putAll(categoriasEspecial);
        HashMap<Integer, String> categoriasSemRepeticao = new HashMap<Integer, String>();
        for (Map.Entry<Integer, String> entry : categorias.entrySet()) {
            if (!categoriasSemRepeticao.containsValue(entry.getValue())) {
                categoriasSemRepeticao.put(entry.getKey(), entry.getValue());
            }
        }
        return categoriasSemRepeticao;
    }



    public void removeCategoria(int number) {
        removeCategoriaNormal(number);
        removeCategoriaImportado(number);
        removeCategoriaEspecial(number);
    }

}