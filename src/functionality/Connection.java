package functionality;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicBoolean;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Connection {
	
	
	/*
	 * Denne login-metode er 'stjaalet' fra gruppens APP, smaa aendringer er tilfoejet
	 */
	public boolean login(String username, String password){
		
		AtomicBoolean success = new AtomicBoolean(false);
		AtomicBoolean done = new AtomicBoolean(false);
		
		System.out.println("preThread - Login");
		new Thread(new Runnable() {
			
            public void run() {
                try {
                    JSONObject send = new JSONObject();
                    try {
                        send.put("TASK", "loginauth");
                        send.put("USERNAME", username);
                        send.put("PASSWORD", password);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    String combinedMessage = "?logininfo=" + send.toString();
                    System.out.println("CombinedMessage"+ combinedMessage);
                    URL url = new URL("http://52.58.112.107:8080/HelpingTeacherServer2/HTSservlet"+combinedMessage);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    String returnString = "";
                    returnString = in.readLine();
                    
                    JSONObject recieve = new JSONObject();
                    JSONParser parser = new JSONParser();
                    recieve = (JSONObject) parser.parse(returnString);
                    
                    System.out.println("returnString: "+returnString);
                    
                    if (recieve.get("REPLY").toString().equals("succes")){
                    	System.out.println("Retur-stringen er kommet");
                    	success.set(true);
                    	done.set(true);
                    } else {
                        System.out.printf("CreateUserERROR:", "Something went wrong in server");
                        success.set(false);
                        done.set(true);
                    }
                    System.out.printf("ReturnMessage:", returnString);
                    in.close();

                } catch (Exception e) {
                	e.printStackTrace();    
                	}
            }
		}).start();
	
		while(!done.get()){
			System.out.println("While-løkken virker");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return success.get();
	}
}
	
