package Grupo3.MyTwitter.principal;

import Grupo3.MyTwitter.igu.Janela;
import Grupo3.MyTwitter.logica.Autenticacao;

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
