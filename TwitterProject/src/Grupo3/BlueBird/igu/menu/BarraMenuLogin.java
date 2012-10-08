package Grupo3.BlueBird.igu.menu;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import Grupo3.BlueBird.igu.Janela;

public class BarraMenuLogin extends JMenuBar {
	
	JMenu menu;
	JMenuItem item;
	
	public BarraMenuLogin(Janela janela) {
		menu = new JMenu("Opções");
		item = new JMenuItem("Sair");
		item.setActionCommand(Opcao.SAIR.name());
		item.addActionListener(janela);
		menu.add(item);
		add(menu);
		
		menu = new JMenu("Ajuda");
		item = new JMenuItem("Utilização");
		item.setActionCommand(Opcao.UTILIZACAO.name());
		item.addActionListener(janela);
		menu.add(item);
		menu.add(new JSeparator());
		
		item = new JMenuItem("Sobre");
		item.setActionCommand(Opcao.SOBRE.name());
		item.addActionListener(janela);
		menu.add(item);
		add(menu);
	}

}
