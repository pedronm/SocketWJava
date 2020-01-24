package br.com.javalab.socket.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class RequestHandler extends Thread {
	
   private Socket socket;
   public RequestHandler( Socket socket ){
	 this.socket = socket;
   }
   
   public void run(){
	   
	   try{
		   System.out.println("Recebendo conex�o");
		   
		   //Dados de entrada e sa�da
		   BufferedReader entrada = new BufferedReader( new InputStreamReader(socket.getInputStream()));
		   PrintWriter saida = new PrintWriter(socket.getOutputStream());
		   
		   //Escreve cabe�alho no cliente
		   saida.println("Echo Server 1.0");
		   saida.flush();
		   
		   // retorna as linhas ao cliente at� ele fechar conex�o ou at� que aqui receba uma linha vazia
		   String linha = entrada.readLine();
		   while(linha != null && linha.length() > 0){
			   saida.println("Echo: " + linha);
			   saida.flush();
			   linha = entrada.readLine();
		   }
		   
		   // Sanitiza��o
		   entrada.close();
		   saida.close();
		   socket.close();
		   
		   System.out.println("Fechando conex�o");
		   
	   }catch(Exception e ){
		   e.printStackTrace();
	   }
   }

}
