package ChatApplication;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class ChatClient {
	
	JTextArea incoming;
	JTextField outgoing;
	BufferedReader bufferedReader;
	PrintWriter printWriter;
	Socket sock;

	public static void main(String[] args) {
		ChatClient chatClientObj = new ChatClient();
		chatClientObj.go();

	}

	public void go(){
		JFrame frame = new JFrame("Multithreaded Chat Client");
		JPanel mainPanel = new JPanel();
		incoming = new JTextArea(15, 50);
		incoming.setLineWrap(true);
		incoming.setWrapStyleWord(true);
		incoming.setEditable(false);
		JScrollPane scroller = new JScrollPane(incoming);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		outgoing = new JTextField(20);
		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(new ButtonClickListener());
		mainPanel.add(scroller);
		mainPanel.add(outgoing);
		mainPanel.add(sendButton);
		
		setUpNetworking();
		
		Thread thread = new Thread(new IncomingReader());  //start a new thread to read incoming messages from the server
		thread.start();
		
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.setSize(400, 500);
		frame.setVisible(true);
		
	} //close go
	
	public void setUpNetworking(){
		
		try {
			sock = new Socket("127.0.0.1", 5000);
			InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
			bufferedReader = new BufferedReader(streamReader);
			
			printWriter = new PrintWriter(sock.getOutputStream());
			
			System.out.println("Connection Established!");
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}  //close setUpNetworking
	
	
	public class ButtonClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				printWriter.println(outgoing.getText());
				printWriter.flush();
			} catch(Exception ex){
				ex.printStackTrace();
			}
			
			outgoing.setText("");
			outgoing.requestFocus();
		}
		
	} //close inner class
	
	
	public class IncomingReader implements Runnable{  //Job of the thread: to read incoming messages from the server

		@Override
		public void run() {
			
			try {
				String message;
				while( (message=bufferedReader.readLine()) != null){
					System.out.println("read : " + message);
					incoming.append(message);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} //close run
		
	} //close inner class
}
