package Grupo3.BlueBird.igu.menu;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import Grupo3.BlueBird.igu.Janela;

/**
 * Classe responsável por definir o menu 
 */
public class BarraMenu extends JMenuBar {
	
	JMenu menu;
	JMenuItem item;
	
	public BarraMenu(Janela janela) {
		menu = new JMenu("Opções"); // define a primeira opção do menu
		
		item = new JMenuItem("Refresh Timeline");
		item.setActionCommand(Opcao.REFRESHTIMELINE.name());
		item.addActionListener(janela);
		menu.add(item);

		menu.add(new JSeparator());
		
		item = new JMenuItem("Refresh User Timeline");
		item.setActionCommand(Opcao.REFRESHUSERTIMELINE.name());
		item.addActionListener(janela);
		menu.add(item);

		menu.add(new JSeparator());
		
		item = new JMenuItem("Sair"); // define o item desta opção
		item.setActionCommand(Opcao.SAIR.name()); // inclui o evento desta opção do menu à classe ActionListener
		item.addActionListener(janela); // Informa onde será capturado o evento (em qual classe) está implementado o metodo actionPerformed 
		menu.add(item);  // adiciona o item ao menu 
		
		add(menu); // adiciona o menu à barra

		menu = new JMenu("Ajuda");
		
		item = new JMenuItem("Funcionalidades TF1");
		item.setEnabled(false);
		menu.add(item);

		menu.add(new JSeparator());
		
		item = new JMenuItem("Atualizar a visualização da timeline");
		item.setActionCommand(Opcao.AJREFRESHTIMELINE.name());
		item.addActionListener(janela);
		menu.add(item);
		
		item = new JMenuItem("Ver minha Timeline");
		item.setActionCommand(Opcao.VERTIMELINE.name());
		item.addActionListener(janela);
		menu.add(item);
		
		item = new JMenuItem("Postar um Tweet");
		item.setActionCommand(Opcao.TWEETAR.name());
		item.addActionListener(janela);
		menu.add(item);
		
		menu.add(new JSeparator());
		
		item = new JMenuItem("Funcionalidades TF2");
		item.setEnabled(false);
		menu.add(item);
		
		menu.add(new JSeparator());
				
		item = new JMenuItem("Pesquisar");
		item.setActionCommand(Opcao.PESQUISAR.name());
		item.addActionListener(janela);
		menu.add(item);
		
		item = new JMenuItem("Quantos me seguem");
		item.setActionCommand(Opcao.QUANTOSMESEGUEM.name());
		item.addActionListener(janela);
		menu.add(item);
		
		item = new JMenuItem("Quantos eu sigo");
		item.setActionCommand(Opcao.QUANTOSSIGO.name());
		item.addActionListener(janela);
		menu.add(item);
		
		menu.add(new JSeparator());
		
		item = new JMenuItem("Funcionalidades TF3");
		item.setEnabled(false);
		menu.add(item);

		menu.add(new JSeparator());
		
		item = new JMenuItem("Sobre");
		item.setActionCommand(Opcao.SOBRE.name());
		item.addActionListener(janela);
		menu.add(item);
		add(menu);
	}

}
