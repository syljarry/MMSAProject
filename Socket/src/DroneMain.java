import java.net.SocketException;


public class DroneMain {

	public static void main(String[] args) throws SocketException {
		// TODO Auto-generated method stub
		
		SocketParrot sock1 = new SocketParrot(7000, "127.0.0.1");
		SocketParrot sock2 = new SocketParrot(5556,"192.168.1.31");
		
		DroneThread t1 = new DroneThread("FakeDrone", sock1);
		DroneThread t2 = new DroneThread("RealDrone", sock2);
		
		t1.start();
		t2.start();

	}

}
