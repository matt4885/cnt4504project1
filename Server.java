/*
 * UNIVERSITY OF NORTH FLORIDA COMPUTER SCIENCE
 * CNT 4504 - COMPUTER NETWORKS AND DISTRIBUTED PROCESSING
 * Project 1/2
 * Group 5
 * Date Started:  05/14/2015
 * Date Finished: 05/20/2015
 * Group Machines Used
 * Client: 192.168.100.109
 * Server: 192.168.100.110
 * Port: 1234
 * ********************
 * * Nicholas Hecht   *
 * * Matthew Kempey   *
 * * Casey Dotson     *
 * * Braden Weaver    *
 * * Michael Williams *
 * ********************
 *
 * Other Notes:
 * args[0] = PORT NUMBER
 * args[1] = SERVER TYPE (0 = ITERATIVE, 1 = CONCURRENT)
 */
import java.io.*;
import java.net.*;

public class server{	
	public static void main(String[] args) throws IOException, InterruptedException{
		/* System objects */
		int portNumber = 0;		
		int serverType = 0;
		
		/* Port Number*/
		try {
			portNumber = Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
			System.out.println("Invalid port number. Quitting now...");
			System.exit(2);
		}
		try {
			/* Server Type: Iterative versus Concurrent */
			/* Iterative = 0 Concurrent = 1 */
			serverType = Integer.parseInt(args[1]);

		} catch (NumberFormatException e) {
			System.out.println("Invalid server type. Quitting now...");
			System.exit(3);
		}
		
		if (serverType == 0){
			iterativeServer(portNumber);
		}else if (serverType == 1){
			concurrentServer(portNumber);
		}else{
			System.out.println("Invalid server type. Quitting now...");
			System.exit(3);
		}
	}//end main
	
	
		@SuppressWarnings("resource")
		public static void iterativeServer(int portNumber)throws IOException, InterruptedException{			
			
			boolean listening = true;
			System.out.println("Server listening on port " + portNumber + "...");
	        
	        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
	        	
	            while (listening) {
	            	MultiServerThread t = new MultiServerThread(serverSocket.accept());
		        	t.start();
		        	t.join();
	            }
	        } catch (IOException e) {
	            System.err.println("Could not listen on port " + portNumber);
	            System.exit(-1);
	        }
		} // end concurrentServer
		
		public static void concurrentServer(int portNumber) throws IOException {
						
			boolean listening = true;
			System.out.println("Server listening on port " + portNumber + "...");
	        
	        try (ServerSocket serverSocket = new ServerSocket(portNumber)) { 
	            while (listening) {
	            	new MultiServerThread(serverSocket.accept()).start();
	            }
	        } catch (IOException e) {
	            System.err.println("Could not listen on port " + portNumber);
	            System.exit(-1);
	        }
		} // end iterativeServer	
	} // end class Server

class MultiServerThread extends Thread {
    private Socket socket = null;
 
    public MultiServerThread(Socket socket) {
        super("MultiServerThread");
        this.socket = socket;
    }
     
    public void run() {    	
		PrintWriter out = null;
		BufferedReader in = null;
		String s = null;
		Process p = null;
		BufferedReader stdInput;
		int choice = 0;
		String temp = null;
		
		try{
			System.out.println("  Client connected: ");
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			out.checkError();

			temp = in.readLine(); //.trim();

			try{
				choice = Integer.parseInt(temp);
			}catch(NumberFormatException e){
				
			}

			switch (choice) {
			/* Host current date and time */
			case 1:
				System.out.println("'date' requested");
				p = Runtime.getRuntime().exec("date");
				stdInput = new BufferedReader(new InputStreamReader(
						p.getInputStream()));
				while ((s = stdInput.readLine()) != null) {
					out.println(s);
				}
				break;
			/* Host uptime */
			case 2:
				System.out.println("'uptime' requested");
				p = Runtime.getRuntime().exec("uptime");
				stdInput = new BufferedReader(new InputStreamReader(
						p.getInputStream()));
				while ((s = stdInput.readLine()) != null) {
					out.println(s);
				}
				break;
			/* Host memory use */
			case 3:
				System.out.println("'free' requested");
				p = Runtime.getRuntime().exec("free");
				stdInput = new BufferedReader(new InputStreamReader(
						p.getInputStream()));
				while ((s = stdInput.readLine()) != null) {
					out.println(s);
				}
				break;
			/* Host Netstat */
			case 4:
				System.out.println("'netstat' requested");
				p = Runtime.getRuntime().exec("netstat");
				stdInput = new BufferedReader(new InputStreamReader(
						p.getInputStream()));
				while ((s = stdInput.readLine()) != null) {
					out.println(s);

				}
				break;
			/* Host current users */
			case 5:
				System.out.println("'who' requested");
				p = Runtime.getRuntime().exec("who");
				stdInput = new BufferedReader(new InputStreamReader(
						p.getInputStream()));
				while ((s = stdInput.readLine()) != null) {
					out.println(s);
				}
				break;
			/* Host running processes */
			case 6:
				System.out.println("'ps -c' requested");
				p = Runtime.getRuntime().exec("ps -c");
				stdInput = new BufferedReader(new InputStreamReader(
						p.getInputStream()));
				while ((s = stdInput.readLine()) != null) {
					out.println(s);
				}
				break;			
			default :
				break;
			} // end of switch

			out.println("\001\001\002"); // session termination sequence
 
		}catch (IOException e) {
            e.printStackTrace();
        }
		finally{
			try {
				out.close();
				in.close();
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
}

