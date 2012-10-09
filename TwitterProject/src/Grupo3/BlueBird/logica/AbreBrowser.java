package Grupo3.BlueBird.logica;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Classe responsável por abrir o navegador de internet para autenticação.
 * Recebo como parâmentro a url requisitada na classe Autenticacao
 */
public class AbreBrowser{	
	Desktop desktop = Desktop.getDesktop();
    URI uri;     
	
    public void abreBrowser(String url){    	
    	try {      		
	    	uri = new URI(url);    
		    desktop.browse(uri);
    	}
    	catch(IOException ioe) {  
            ioe.printStackTrace();  
    	}  
		catch(URISyntaxException use) {  
		    use.printStackTrace();  
		}    	
    }	  
}
