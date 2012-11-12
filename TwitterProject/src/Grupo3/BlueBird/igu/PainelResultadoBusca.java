package Grupo3.BlueBird.igu;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Tweet;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;
import Grupo3.BlueBird.logica.Pesquisador;

public class PainelResultadoBusca extends JPanel {
	
	Pesquisador busca;
	String texto;
	JLabel imagem, usuario;
	ResponseList<User> user;
	List<Tweet> tweet;
	Status status;
	
	public PainelResultadoBusca(Twitter twitter, String texto) {
		this.busca = new Pesquisador(twitter);
		this.texto = texto;
		defineComponentes();
		organizaComponentes();
	}
	
	private void defineComponentes() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(Color.WHITE);
		try {
			busca.buscaUsuario(texto);
		} catch (TwitterException e) {
			JOptionPane.showMessageDialog(this, "Ocorreu um problema na sua busca.\n" +
					" Tente novamente mais tarde!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		user = busca.getUsuarioPesquisado();
		for (User usuario_retornado : user) {
			JPanel painelUser = new JPanel(new FlowLayout(FlowLayout.LEFT));
			painelUser.add(new JLabel(new ImageIcon(usuario_retornado.getProfileImageURL())));
			painelUser.add(new JLabel("@" + usuario_retornado.getScreenName()));
			status = usuario_retornado.getStatus();
			painelUser.add(new JLabel(status.getText()));
			painelUser.add(new BotaoPersonalizado("Seguir"));
			add(painelUser);
		}
	}
	
	private void organizaComponentes() {
		
		//add(imagem);
		//add(usuario);
		
	}

}
