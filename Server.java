/*
 * UNIVERSITY OF NORTH FLORIDA COMPUTER SCIENCE
 * CNT 4504 - COMPUTER NETWORKS AND DISTRIBUTED PROCESSING
 * Project 1
 * Group 5
 * Date Started: 05/14/2015
 *
 * Group Machines Used
 * Client: 192.168.100.109
 * Server: 192.168.100.110
 * Port: 1234
 * ********************
 * * Nicholas Hecht *
 * * Matthew Kempey *
 * * Casey Dotson *
 * * Braden Weaver *
 * * Michael Williams *
 * ********************
 *
 * Other Notes:
 *
 *
 */
import java.io.*;
import java.util.Scanner;
import java.net.*;

public class server {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		/* System objects */
		int portNumber = 1234; //Integer.parseInt(args[0]);
		Socket clientSocket = null;
		ServerSocket serverSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		String inputLine = null;
		String outputLine = null;
		String s = null;
		Process p = null;
		BufferedReader stdInput;
		int i = 0;
		

		System.out.println("Server listening on port " + portNumber + "...");

	    serverSocket = new ServerSocket(portNumber);
	    
		while (true) {
			
			clientSocket = serverSocket.accept();
			System.out.println("  Client connection established");
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String temp = in.readLine();
			System.out.println(temp);
			int choice = Integer.parseInt(temp);
			
			switch (choice) {
			/* Host current date and time */
			case 1:
				p = Runtime.getRuntime().exec("date");
				stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
				while((s = stdInput.readLine()) != null){
					out.println(s);
				}
			break;
			/* Host uptime */
			case 2:
			break;
			/* Host memory use */
			case 3:
			break;
			/* Host Netstat */
			case 4:
				p = Runtime.getRuntime().exec("netstat");
				stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
				while((s = stdInput.readLine()) != null){
					i++;
					if(i == 81){
						out.println();
						i = 0;
					}
					out.print(s);
				}
				i = 0;
			break;
			/* Host current users */
			case 5:
			break;
			/* Host running processes */
			case 6:
			break;
			case 7:
			System.out.println("Quitting...");
			System.exit(1);
			break;
			default:
			System.out.println("Invalid menu choice. Please enter 1-7.");
			break;
			} // end of switch
			
			try {
				    out = new PrintWriter(clientSocket.getOutputStream(), true);
				    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		            
				    // Initiate conversation with client		    
				    out.println("I see you");
	
				   /* while ((inputLine = in.readLine()) != null) {
				        
				        out.println(outputLine);
				        if (outputLine.equals("Bye."))
				            break;
				    }*/
			} 
			catch (Exception ex){}
		} // end while
		
	} // main
} // end class Server
	
