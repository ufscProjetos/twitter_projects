package Grupo3.BlueBird.principal;

import Grupo3.BlueBird.igu.Janela;
import Grupo3.BlueBird.logica.MeuTwitter;

public class ProgramaTwitter {
	/**
	 * o método main instancia a classe Autenticacao e a classe Janela e logo após chama o método executa da classe janela
	 */
	public static void main(String[] args) {
		MeuTwitter mt = new MeuTwitter();
		Janela janela = new Janela(mt);
		janela.executa();
	}

}
