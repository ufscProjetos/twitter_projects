package Grupo3.BlueBird.logica;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class AbreBrowser{	
	Desktop desktop = Desktop.getDesktop();
    URI uri;    
    public AbreBrowser(){    
    }  
	
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
