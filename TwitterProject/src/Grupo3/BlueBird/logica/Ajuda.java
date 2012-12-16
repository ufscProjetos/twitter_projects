package Grupo3.BlueBird.logica;

public class Ajuda {
	
	public String getComoVisualizarTimeline(){
		return "Logo que você abre o Blue Bird é disponibilizada a visualização\n da sua timeline ou a timeline de um Tweeter. \n" +
				"Os Tweets mais recentes dos seu amigos são\n exibidos em ordem cronológica.\n Você pode ainda pesquisar por Tweets\n" +
				"ou por pessoas, para isso veja o item \"Pesquisar\", do menu.";
	}
	
	public String getComoTweetar(){
		return "O Blue Bird permite que você poste Tweets, que aparecerão na\n sua linha do tempo do Twitter. " +
				"Postar um Tweet é muito fácil.\n No campo de texto, localizado acima da visualização da timeline,\n é possível escrever " +
				"qualquer coisa, dentro de limite de 140\n caracteres, estabelecido pelo próprio Twitter, e enviar à sua\n timeline " +
				"clicando no botão \"Tweetar\". " +
				"Por segurança, o botão\n fica desabilitado até que você digite alguma coisa e sempre\n que ultrapassar o limite de 140 " +
				"caracteres, evitando que o\n Twitter recuse sua atualização.";
	}
	
	public String getQuantosMeSeguem(){
		return "O Blue Bird permite que você saiba quantos seguidores têm.\n Na lateral esquerda, logo abaixo do logo do Twitter é " +
				"exibida a\n informação, com o número exato seguido da palavra \"Followers\"";
	}
	
	public String getQuantosEuSigo(){
		return "O Blue Bird permite que você saiba quantos usuários do Twitter\n você segue. Na lateral esquerda, logo abaixo do total " +
				"de seus\n seguidores é exibida o número exato de usuários seguidos por\n você, com da palavra \"Following\"";
	}
	
	public String getComoPesquisar(){
		return "Na parte superior do Blue Bird fica localizado o campo de busca.\n A função de pesquisa permite tanto localizar pessoas " +
				"como\n também tweets, para isso basta digitar o nome da pessoa ou um\n palavra a qual você quer pesquisar. Logo após clique " +
				"na lupa\n ao lado para realizar a busca. Será criada uma janela contendo\n os resultados.";
	}
	
	public String getSobreMim(){
		return "O Blue Bird é um software que permite ao usuário utilizar os\n serviços do Twitter sem a necessidade de intermédio de\n " +
				"navegadores de internet.\n\n" +
				"Versão 2.0 - 20/11/2012\n\n" +
				"ine5605.grupo3@gmail.com ";
	}

	public String getFazerRefreshTimeline() {
		return "Para que você possa visualizar os últimos Tweets dos seus\n amigos, ou mesmo o que você acabou de postar, é preciso que " +
				"\nvocê atualize a visualização da sua Timeline.\n Fazer isso é muito simples: no menu, escolha opções e depois\n \"Refresh Timeline\". " +
				"Feito isso, os tweets mais recentes serão\n exibidos na sua linha do tempo.";
	}


}
