package ChatApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class ChatServer {

	ArrayList clientOutputStreams;
	
	public class ClientHandler implements Runnable{

		BufferedReader bufferedReader;
		Socket sock;
		
		public ClientHandler(Socket clientSocket){
			sock = clientSocket;
			try {
				InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
				bufferedReader = new BufferedReader(streamReader);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		@Override
		public void run() {
			String message;
			try {
				while((message = bufferedReader.readLine()) != null){
					System.out.println("Received message : " + message);
					tellEveryClient(message);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
		ChatServer chatServer = new ChatServer();
		chatServer.go();

	}
	
	public void go(){
		clientOutputStreams = new ArrayList();
		try {
			ServerSocket serverSocket = new ServerSocket(5000);
			
			while(true){
				
				Socket clientSocket = serverSocket.accept();
				PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
				
				Thread clientThread = new Thread(new ClientHandler(clientSocket));
				clientOutputStreams.add(writer);
				clientThread.start();
				System.out.println("Got a connection.");
				
			}
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	}
	
	public void tellEveryClient(String message){
		Iterator it = clientOutputStreams.iterator();
		
		while(it.hasNext()){
			try{
				PrintWriter wr = (PrintWriter) it.next();
				wr.println(message);
				wr.flush();
			} catch (Exception ex){
				ex.printStackTrace();
			}
		}
	}

}
