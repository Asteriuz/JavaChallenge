package br.com.fiap.banco.teste;

import java.util.List;

import br.com.fiap.banco.model.Caso;
import br.com.fiap.banco.service.CasoService;

public class TestePesquisaPorNome {

	// Testar a pesquisa por nome de user
	public static void main(String[] args) {

		try {
			CasoService service = new CasoService();
			List<Caso> casos = service.pesquisarPorNomeUser("Gabriel");
			for (Caso caso : casos) {
				System.out.println(caso.getMarca());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}