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
 * args[0] = SERVER IP 
 * args[1] = SERVER PORT
 * args[2] = NUMBER OF CLIENTS
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
	private static Scanner scan;

	public static void main(String[] args) throws IOException {
		
		 String s = null;
		 /*
	        try {
	             
	        	// run the Unix "ps -ef" command
	            // using the Runtime exec method:
	            Process p = Runtime.getRuntime().exec("netstat");
	             
	            BufferedReader stdInput = new BufferedReader(new
	                 InputStreamReader(p.getInputStream()));
	 
	            BufferedReader stdError = new BufferedReader(new
	                 InputStreamReader(p.getErrorStream()));
	 
	            // read the output from the command
	            System.out.println("Here is the standard output of the command:\n");
	            while ((s = stdInput.readLine()) != null) {
	                System.out.println(s);
	            }
	             
	            // read any errors from the attempted command
	           // System.out.println("Here is the standard error of the command (if any):\n");
	            while ((s = stdError.readLine()) != null) {
	                System.out.println(s);
	            }
	             
	            System.exit(0);
	        }
	        catch (IOException e) {
	            System.out.println("exception happened - here's what I know: ");
	            e.printStackTrace();
	            System.exit(-1);
	        }
*/
		
		System.out.println();
		scan = new Scanner(System.in);
		/* Local variables */
		int choice = 0; // Used in switch statement for menu choice
		byte[] addr = new byte[4]; // byte array to store the destination array
		String serverIP = args[0];
		int numberOfClients = Integer.parseInt(args[2]);
		// choice = scan.nextInt();
		/*
		 * Grab the server IP
		 */
		if (args.length > 1) {
			try {
				String tempString[] = args[0].split(".");
				if (tempString.length == 4) {
					for (int i = 0; i < tempString.length; i++) { // iterate
					// through
					// the
					// server
					// address
					// array
						byte b = Byte.parseByte(tempString[i]); // convert the
						// octet to a
						// byte
						addr[i] = b; // store byte in appropriate addr array
						// element
					}
				}
			} catch (NumberFormatException e) {
				System.err.println("Argument" + args[0]
						+ " must be an integer.");
				System.exit(1);
			}
		}
		
		/*
		 * Grab the number of clients
		 */
		if (args.length > 1) {
			try {
				numberOfClients = Integer.parseInt(args[1]);
			} catch (NumberFormatException e) {
				System.err.println("Argument" + args[1]
						+ " must be an integer.");
				System.exit(1);
			}
		}
		int portNumber = Integer.parseInt(args[1]);
		do {
			choice = userMenu();
			if (choice >= 1 && choice <= 6) {
				sendCmd(choice, portNumber, serverIP);
			}
		} while (choice != 7);
	} // main

	private static void sendCmd(int choice, int portNumber, String serverIP) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out = null;
		Socket kkSocket = null;
		BufferedReader in = null;
		int fromServer = 0;
		
		//try {
			kkSocket = new Socket(serverIP, portNumber);
			out = new PrintWriter(kkSocket.getOutputStream(), true);
			out.println(choice);
			in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
			String temp = in.readLine();
			System.out.print(temp);
			//catch (Exception ex) {
		
		
	
	/*	try {
			fromServer = in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // catch4
		
		*/
		
	}

	public static int userMenu() {
		System.out.println("Select a function:");
		System.out.println("1. Host current Date and Time");
		System.out.println("2. Host uptime");
		System.out.println("3. Host memory use");
		System.out.println("4. Host Netstat");
		System.out.println("5. Host current users");
		System.out.println("6. Host running processes");
		System.out.println("7. Quit");
		return scan.nextInt();
	} // end userMenu*(
} // end class Client
