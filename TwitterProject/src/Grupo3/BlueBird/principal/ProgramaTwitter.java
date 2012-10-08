package Grupo3.BlueBird.principal;

import Grupo3.BlueBird.igu.Janela;
import Grupo3.BlueBird.logica.Autenticacao;

public class ProgramaTwitter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Autenticacao autenticacao = new Autenticacao();
		Janela janela = new Janela(autenticacao);
		janela.executa();
	}

}
