import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

public class question2 {

	static void writeToFile(String filename, Object object) throws Exception {
		FileOutputStream fout = new FileOutputStream(filename);
		ObjectOutputStream oout = new ObjectOutputStream(fout);
		oout.writeObject(object);
		oout.close();
	}

public static void main(String[] args) {

	KeyGenerator kg;
	try {
		
		kg = KeyGenerator.getInstance("HmacSHA256");	
		SecretKey sk = kg.generateKey();
	
		//storing secret key to file
		String encodedKey = Base64.getEncoder().encodeToString(sk.getEncoded());
		System.out.println("Encoded Key :" + encodedKey);
		writeToFile("C:\\Users\\a00258752\\Security\\data\\secretKey.txt", encodedKey);
	
		//writing text to a file
		String msg = "Hi there, this is abhik. Wassup!!";
		writeToFile("C:\\Users\\a00258752\\Security\\data\\sendText.txt", msg);
	
	
		//storing encoded hmac to a file	
		Mac mac = Mac.getInstance("HmacSHA256");	
		mac.init(sk);
	
		byte[] hmacText = mac.doFinal(msg.getBytes());
		String encodedHmac = Base64.getEncoder().encodeToString(hmacText);	
		writeToFile("C:\\Users\\a00258752\\Security\\data\\hmac.txt", encodedHmac);	
	
		byte[] decodedHmac = Base64.getDecoder().decode(encodedHmac);
	
		} 
	
		catch (NoSuchAlgorithmException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	
		catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	}