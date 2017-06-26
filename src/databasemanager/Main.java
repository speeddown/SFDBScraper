package databasemanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;
import java.awt.*;

public class Main{
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static String authEndpoint = "https://login.salesforce.com/services/Soap/c/39.0/";
	private static String securityToken =  "i90614PrkpUifnY2NO596PQM";
	public static EnterpriseConnection connection;
	private static ConnectorConfig config;
	private static String username = null;
	private static String password = null;
	
	//components
	
	public static void main(String[] args){
		
            boolean loggedIn = false;

            while(authEndpoint.compareTo("") == 0){
                    SetAuthEndpoint();
            }

            while(loggedIn == false){
                loggedIn = Login();
                if(loggedIn){
                    try{
                            WriteLine("\nLogin successful!");
                    } catch (IOException e){
                            e.printStackTrace();
                    }
                }
            }
	}
	
	public static void ShowSplash(){
            Label title = new Label("Database Manager");
            title.setSize(80, 30);
            title.setBounds(360,100,80,30);
	}
	
	public static void SetAuthEndpoint(){
            try{
                WriteLine("\nNo AuthEndpoint was passed as argument, unable to establish connection!");
                WriteLine("\nUsually, the AuthEndpoint for an Enterprise WSDL is formatted like so");
                WriteLine("\thttps://login.salesforce.com/services/Soap/c/XX.X/");
                WriteLine("Where 'XX.X' is the version of the API in use. Contact your Salesforce developer for version number.");
                Write("\nEnter AuthEndpoint: ");
                authEndpoint = GetInput();
            } catch (IOException e){
                authEndpoint = "";
                e.printStackTrace();
            }
	}
	
	public static boolean Login(){
            boolean success = false;
            String userName = "";
            String passWord = "";

            System.out.println("Salesforce SOAP API Login\n");

            System.out.println("\n  - Accepts your normal Salesforce login credentials"
                             + "\n  - Some users may need their permissions elevated to perform certain actions"
                             + "\n    on the organization objects; Namely write actions"
                             + "\n  - Currently, the only fully implemented feature is the unguided query"
                             + "\n    and printing of records"
                             + "\n  - You can also try the guided query which in the future will allow even"
                             + "\n    users who have no SQL experience enter searches using a predetermined"
                             + "\n    list of commands."
                             + "\n  - Guided query may fail unexpectedly as it has not been thoroughly tested"
                             + "\n  - SQL and database operations experience required to take full advantage of this "
                             + "\n    program");
            try{
                Write("\nEnter Username: ");
                if(username == null){
                    userName = GetInput();
                } else {
                    userName = username;
                    SkipLine();
                }
			
                Write("Enter Password: ");
                if(password == null){
                        passWord = GetInput();
                } else {
                        passWord = password;
                        SkipLine();
                }
			
                success = InitConfig(userName, passWord);
			
		} catch (IOException e){
                    try {
                        WriteLine("\nLogin failed");
                    } catch (IOException e1) {}
		}
		
            return success;
	}
	
	public static boolean InitConfig(String userName, String password){
            config = new ConnectorConfig();
            config.setUsername(userName);
            config.setPassword(password + securityToken);
            config.setAuthEndpoint(authEndpoint);

            try{
                connection = new EnterpriseConnection(config);
                connection.login(userName, password);
                return true;
            } catch (ConnectionException ce){
                ce.printStackTrace();
                return false;
            }
		
	}
	
	public static void Logout(){
            try {
                connection.logout();
            } catch (ConnectionException e) {
                e.printStackTrace();
                try {
                    WriteLine("\n\nUnable to logout!!!");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
	}
	
	public static String GetInput(){
            String input = "";
            try{
                input = reader.readLine();
                return input;
            } catch (Exception e){
                e.printStackTrace();
            }
            return input;
	}
	
	public static void WriteLine(String message) throws IOException{
            System.out.println(message);
	}
	
	public static void Write(String message) throws IOException{
            System.out.print(message);
	}
	
	public static void SkipLine(){
            System.out.println("");
	}
}
