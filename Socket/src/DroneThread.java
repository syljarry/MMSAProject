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
		    Thread.sleep(1000);
		    sock.sendCommande(commande.initialisation("prof", "ses", "app"));
		    /*sock.sendCommande(commande.move(0,0,0,0,0));
		    sock.sendCommande(commande.move(1,0,5,0,0));
		    sock.sendCommande(commande.move(1,4,0,0,0));
		    sock.sendCommande(commande.move(1,4,0,0,1));*/
		    int compteur = 0;
		    while(compteur!= 20){
			    sock.sendCommande(commande.configIDS("prof", "ses", "app"));
		    	sock.sendCommande(commande.camFrontal());
		    	Thread.sleep(1000);
			    sock.sendCommande(commande.configIDS("prof", "ses", "app"));
		    	sock.sendCommande(commande.camVertical());
		    	Thread.sleep(1000);
		    	compteur++;
		    }
		    
		    sock.close();
		    System.out.println("FIN");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}


}




	
	


