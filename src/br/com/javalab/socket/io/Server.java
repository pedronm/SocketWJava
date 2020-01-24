package br.com.javalab.socket.io;


import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
	
	  private ServerSocket serverSocket;
	  private int porta;
	  private boolean isRodando = false;
	  
	  public Server(int porta){
		  this.porta = porta;
	  }
	  
	  public void iniciarServidor(){
		  try{
			  serverSocket = new ServerSocket(this.porta);
			  this.start();
		  }
		  catch(Exception e ){
			  e.printStackTrace();
		  }
	  }
	  
	  public void pararServidor(){
		  this.isRodando = false;
		  this.interrupt();
	  }
	  
	  public void run(){
		  isRodando = true;
		  while(isRodando){
			  try{
				  System.out.println("Esperando uma conexão");
				  
				  //chama o accept() para receber a proxima conexão
				  Socket socket = serverSocket.accept();
				  
				  //encaminha o socket para o thread de processamento de requisição.
				  RequestHandler requestHandler = new RequestHandler(socket);
				  requestHandler.start();
				  
			  }catch(Exception e ){
				  e.printStackTrace();
			  }
		  }
	  }
	  
	  public static void main(String[] args) {
		
		  if(args.length == 0){
			  System.out.println( "Usage: SimpleSocketServer <port>" );
              System.exit( 0 );
		  }
		  int porta = Integer.parseInt(args[0]);
		  System.out.println("Iniciar o servidor na porta: "+ args[0]);
		  
		  Server server = new Server(porta);
		  server.iniciarServidor();
		  
		  //desliga automaticamente depois de 1 minuto
		  try{
			  Thread.sleep(6000);
		  }catch(Exception e ){
			  e.printStackTrace();
		  }
		  server.pararServidor();
	}	
}
