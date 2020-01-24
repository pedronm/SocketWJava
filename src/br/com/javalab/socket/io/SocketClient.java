package br.com.javalab.socket.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {
	
	public static void main(String[] args) {
		
		try {
			String server = args[0];
			String path = args[1];
			
			System.out.println(" Imprimindo conteúdo da URL: " + server);
			
			// conectar ao servidor
			 Socket socket = new Socket( server, 80 );
			 
			 // criar entrada e saída para ler e escrever no servidor.
			 PrintStream out = new PrintStream(socket.getOutputStream());
			 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			 
			 // segue o protocolo http GET <caminho/url> Http/1.0 seguido de uma linha vazia.
			 out.println("GET" + path + "HTTP/1.0");
			 out.println();
			 
			 // lê os dados recebidos 
			 String line = in.readLine();
			 while(line != null){
				 
				 System.out.println(line);
				 line = in.readLine();
				 
			 }
			 
			 // sanitização
			 in.close();
			 out.close();
			 socket.close();
			
		} catch (Exception e ){
			e.printStackTrace();
		}

	}
}
