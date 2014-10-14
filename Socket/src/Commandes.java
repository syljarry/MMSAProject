import java.util.ArrayList;
import java.util.zip.CRC32;
import java.util.zip.Checksum;


public class Commandes {
	static int seq = 0;
	public Commandes(){
		}
	
	  public int conversionFloat754(Float var){
		  return Float.floatToRawIntBits(var);
	  }
	  public String conversionCrc32(String str){
			byte bytes[] = str.getBytes();
			Checksum checksum = new CRC32();
			checksum.update(bytes,0,bytes.length);
			long lngChecksum = checksum.getValue();
			String ret = Long.toHexString(lngChecksum);
			return ret;
		  
	  }
	  
	  
	  public int seqNum(){
		  return ++seq;
	  }
	  
	  public String takeOff(){
		  return "AT*REF=" + seqNum() +  ",290718208" + slash();
	  }
	  
	  public String landing(){
		  return "AT*REF=" + seqNum() +  ",290717696" + slash();
	  }
	  
	  public String stable(){
		  return "AT*FTRIM=" + seqNum() +  slash();
	  }
	  
	  public String move(int id, float roll, float pitch, float throttle, float yaw){
		  return "AT*PCMD=" + seqNum() + "," +  id + "," + conversionFloat754(roll) + "," + conversionFloat754(pitch) + "," + conversionFloat754(throttle) + "," + conversionFloat754(yaw) + slash();
	  } 

	  public String configIDS(String p, String s, String a){
		  String prof = conversionCrc32(p);
		  String ses = conversionCrc32(s);
		  String app = conversionCrc32(a);
		  return "AT*CONFIG_IDS=" + seqNum() +",\"" + ses + "\",\"" + prof + "\",\"" + app + "\"" + slash();
	  }
	  public String changmentCodec(){
		  return "AT*CONFIG="+ seqNum() + ",\"video:video_codec\",\"129\"" + slash();
	  }
	  
	  public String camFrontal(){
		  return "AT*CONFIG="+ seqNum() + ",\"video:video_channel\",\"0\"" + slash();
	  }
	  public String camVertical(){
		  return "AT*CONFIG="+ seqNum() + ",\"video:video_channel\",\"1\"" + slash();
	  }
	  
	  public ArrayList<String> initialisation(String p, String s, String a){
		  ArrayList<String> array = new ArrayList<String>();
		  String prof = conversionCrc32(p);
		  String ses = conversionCrc32(s);
		  String app = conversionCrc32(a);
		  
		  array.add(configIDS(p,s,a));
		  array.add("AT*CONFIG=" + seqNum() + ",\"custom:session_id\",\"-all\""+slash());
		  
		  array.add(configIDS(p,s,a));
		  array.add("AT*CONFIG=" + seqNum() + ",\"custom:profile_id\",\"" + "-" + prof + "\"" +slash());

		  array.add(configIDS(p,s,a));
		  array.add("AT*CONFIG=" + seqNum() + ",\"custom:application_id\",\"" + "-" + app + "\"" +slash());
		  
		  array.add(configIDS(p,s,a));
		  array.add("AT*CONFIG=" + seqNum() + ",\"custom:session_id\",\"" + ses + "\"" +slash());
		  
		  array.add(configIDS(p,s,a));
		  array.add("AT*CONFIG=" + seqNum() + ",\"custom:application_id\",\"" + app + "\"" +slash());
		  
		  array.add(configIDS(p,s,a));
		  array.add("AT*CONFIG=" + seqNum() + ",\"custom:profile_id\",\"" + prof + "\"" +slash());
		  
		  array.add(configIDS(p,s,a));
		  array.add("AT*CONFIG=" + seqNum() + ",\"custom:application_desc\",\"" + a + "\"" +slash());
		  
		  array.add(configIDS(p,s,a));
		  array.add("AT*CONFIG=" + seqNum() + ",\"custom:profile_desc\",\"" + p + "\"" +slash());
		  
		  array.add(configIDS(p,s,a));
		  array.add("AT*CONFIG=" + seqNum() + ",\"custom:session_desc\",\"" + s + "\"" +slash());
		  
		  return array;
	  }
	  public static String slash(){
		  return "\n";
	  }

}
