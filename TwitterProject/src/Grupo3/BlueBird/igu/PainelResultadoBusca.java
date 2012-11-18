package Grupo3.BlueBird.igu;

import java.awt.Color;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import twitter4j.Tweet;
import twitter4j.TwitterException;
import Grupo3.BlueBird.logica.MeuTwitter;

public class PainelResultadoBusca extends JPanel {
	
	MeuTwitter mt;
	String texto;
	JLabel imagem, usuario;
	List<Tweet> tweet;
	
	public PainelResultadoBusca(MeuTwitter mt, String texto) {
		this.mt = mt;
		this.texto = texto;
		defineComponentes();
	}
	
	private void defineComponentes() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		defineExibicaoResultado();
	}
	
	private void defineExibicaoResultado(){
		try {
			
			mt.executaPesquisa(texto);
			tweet = mt.getResultPesquisa();
			
			for (Tweet tweet_retornado : tweet) {
				JPanel painelTweet = new JPanel();
				painelTweet.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0, 195, 248)));
				painelTweet.setBackground(Color.WHITE);
				JLabel imagem;
				try {
					imagem = new JLabel(new ImageIcon(new URL(tweet_retornado.getProfileImageUrl())));
				} catch (MalformedURLException e) {
					JOptionPane.showMessageDialog(this, "Ocorreu um problema  no carregamento\n das imagens de perfil.\n " +
							"Uma imagem padrão será exibida.", "Falha no carregamento das imagens", JOptionPane.INFORMATION_MESSAGE);
					imagem = new JLabel(new ImageIcon(getClass().getResource("/imagens/img_padrao.png")));
				}
				painelTweet.add(imagem);
				painelTweet.add(new JLabel("<html>@" + tweet_retornado.getFromUserName() + "<p style=\"width:300px; font-size:8px;\">"
						+ tweet_retornado.getText() + "</p>"));
				this.add(painelTweet);
			}
			
		} catch (TwitterException e) {
			JOptionPane.showMessageDialog(this, "Ocorreu um problema na sua busca.\n" +
					" Tente novamente mais tarde!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
}
