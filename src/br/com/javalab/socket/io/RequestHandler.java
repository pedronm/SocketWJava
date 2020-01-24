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
		   System.out.println("Recebendo conexão");
		   
		   //Dados de entrada e saída
		   BufferedReader entrada = new BufferedReader( new InputStreamReader(socket.getInputStream()));
		   PrintWriter saida = new PrintWriter(socket.getOutputStream());
		   
		   //Escreve cabeçalho no cliente
		   saida.println("Echo Server 1.0");
		   saida.flush();
		   
		   // retorna as linhas ao cliente até ele fechar conexão ou até que aqui receba uma linha vazia
		   String linha = entrada.readLine();
		   while(linha != null && linha.length() > 0){
			   saida.println("Echo: " + linha);
			   saida.flush();
			   linha = entrada.readLine();
		   }
		   
		   // Sanitização
		   entrada.close();
		   saida.close();
		   socket.close();
		   
		   System.out.println("Fechando conexão");
		   
	   }catch(Exception e ){
		   e.printStackTrace();
	   }
   }

}
