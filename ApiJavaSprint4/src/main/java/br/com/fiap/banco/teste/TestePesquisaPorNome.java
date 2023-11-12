package br.com.fiap.banco.teste;

import java.util.List;

import br.com.fiap.banco.model.Caso;
import br.com.fiap.banco.service.CasoService;

public class TestePesquisaPorNome {

	// Testar a pesquisa por nome
	public static void main(String[] args) {
		try {
			CasoService service = new CasoService();

			// Pesquisar as casos por nome
			List<Caso> lista = service.pesquisarPorNome("Augusto");

			// Exibir todos os nomes das casos retornados
			for (Caso item : lista) {
				System.out.print(item.getEmail() + " - ");
				System.out.println(item.getDataCriacao());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("=====================================");

		// testar pesquisa por status
		try {
			CasoService service = new CasoService();

			// Pesquisar as casos por nome
			List<Caso> lista = service.pesquisarPorStatus("Aberto");

			// Exibir todos os nomes das casos retornados
			for (Caso item : lista) {
				System.out.print(item.getEmail() + " - ");
				System.out.println(item.getDataCriacao());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}