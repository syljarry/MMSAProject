import java.io.IOException;


public class DroneThread extends Thread{

	private SocketParrot sock;
	public DroneThread(String name, SocketParrot sop) {
		super(name);
		this.sock = sop;
	}
	
	
	public void run() {
		try {			
			Commandes commande = new Commandes();
			sock.sendCommande(commande.stable());
			System.out.println("coucou");
		    Thread.sleep(1000);
		    sock.sendCommande(commande.move(0, 0, 0, 0, 0));
		    sock.sendCommande(commande.move(1, 0, 0, -0.2f, 0));
		    sock.sendCommande(commande.move(0, 0, 0, 0, 0));
		    sock.sendCommande(commande.landing());
		    sock.sendCommande(commande.initialisation("prof", "ses", "app"));
		    sock.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}


}




	
	


