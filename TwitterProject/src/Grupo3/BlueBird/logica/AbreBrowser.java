package Grupo3.BlueBird.logica;

import java.io.IOException;

/**
 * Classe responsável por abrir o navegador de internet para autenticação.
 * Recebo como parâmentro a url requisitada na classe Autenticacao
 */
public class AbreBrowser{	
	
    public void abreBrowser(String url){    	
    	try {      		
    		Runtime.getRuntime().exec("firefox " + url); 
    	}
    	catch(IOException ioe) {  
            ioe.printStackTrace();  
    	}   	
    }	  
}
