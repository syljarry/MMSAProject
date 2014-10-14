import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;


public class SocketParrot {
	
	private int port;
	private String addr;
	private DatagramSocket socket;
	
	public SocketParrot(int port, String addr) throws SocketException{
		this.port = port;
		this.addr = addr;
		this.socket = new DatagramSocket();
		}

	
	public void sendCommande(String message) throws IOException, InterruptedException{
		  InetAddress serveur = InetAddress.getByName(this.addr);
		  byte buffer[] = message.getBytes();
	      int length = message.length();
	      DatagramPacket dataSent = new DatagramPacket(buffer,length,serveur,port);
	      this.socket.send(dataSent);
	      Thread.sleep(200);
	}
	
	public void sendCommande(ArrayList<String> array) throws IOException, InterruptedException{
		int compteur = 0; //compteur de doublet
		for(int i = 0; i<array.size() ; i++){
			sendCommande(array.get(i));
			compteur++;
			if(compteur == 2){
				Thread.sleep(200);
				compteur = 0;
			}
		}
	}
	
	public void close(){
		this.socket.close();
	}
}
