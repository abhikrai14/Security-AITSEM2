import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class question3 {

	static Object readFromFile(String filename) throws Exception 
	{
		FileInputStream fin = new FileInputStream(filename);
		ObjectInputStream oin = new ObjectInputStream(fin);
		Object object = oin.readObject();
		oin.close();
		return object;
	}

	public static void main(String[] args) {

	String encodedKey;
	try {
		
		//reading and decoding secret Key from the file
		encodedKey = (String) readFromFile("C:\\Users\\a00258752\\Security\\data\\secretKey.txt");
		byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
		SecretKey sk = new SecretKeySpec(decodedKey, 0, decodedKey.length,"HmacSHA256");
	
	
		//reading text and hmac from the file
		String msg = (String) readFromFile("C:\\Users\\a00258752\\Security\\data\\sendText.txt");	
		String HmacfromFile = (String) readFromFile("C:\\Users\\a00258752\\Security\\data\\hmac.txt");
		byte[] encodedHmac = HmacfromFile.getBytes();
	
		//decoding hmac received from file
		byte[] decodedHmac = Base64.getDecoder().decode(encodedHmac);
	
		//creating hmac for text
		Mac mac = Mac.getInstance("HmacSHA256");	
		mac.init(sk);	
		byte[] hmacText = mac.doFinal(msg.getBytes());
	
		
		//checking both msg
		System.out.println("Check: " + Arrays.equals(decodedHmac, hmacText));
		
		} 
	
		catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
}

}