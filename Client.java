/*  
 * UNIVERSITY OF NORTH FLORIDA COMPUTER SCIENCE
 * CNT 4504 - COMPUTER NETWORKS AND DISTRIBUTED PROCESSING
 * Project 1
 * Group   5
 * Date Started: 05/14/2015
 * 
 * Group Machines Used
 * Client: 192.168.100.109
 * Server: 192.168.100.110
 * Port:   1234
 * ********************
 * * Nicholas Hecht   *
 * * Matthew Kempey   *
 * * Casey Dotson     *
 * * Braden Weaver    *
 * * Michael Williams * 
 * ********************
 *
 * Other Notes: 
 *
 *
 */

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {

		/* System objects *///
		Scanner scan = new Scanner(System.in);

		/* Local variables */
		int choice = 0; // Used in switch statement for menu choice
		byte[] addr = new byte[4]; // byte array to store the destination array
		
		// choice = scan.nextInt();
		/* 
		 * Grab the server IP
		 */
		
		String serverIP = null;   

		if (args.length > 1) {
			try {
				String tempString[] = args[0].split(".");
				if (tempString.length == 4) {
					for (int i = 0; i < tempString.length; i++) { // iterate through the server address array
						byte b = Byte.parseByte(tempString[i]); // convert the octet to a byte
						addr[i] = b; // store byte in appropriate addr array element
					}
				}
			} catch (NumberFormatException e) {
				System.err.println("Argument" + args[0]
						+ " must be an integer.");
				System.exit(1);

			}
		}

		try {
			InetAddress test = InetAddress.getByAddress(addr);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} // inetaddress object for use with Socket connections
		/* 
		 *  Grab the number of clients 
		 */
		
		int numberOfClients;
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

		try {
		    Socket kkSocket = new Socket(serverIP, portNumber);
		    PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
		    BufferedReader in = new BufferedReader( new InputStreamReader(kkSocket.getInputStream()));
			} 
		catch(Exception ex) {}
		
		
		while (true) {

			switch (choice) {
			/* date */
			case 1:

				break;
			/* netstat */
			case 2:
			
				break;
			/* ps -e / ps -aux */
			case 3:

				break;
			
			case 4:

				break;
			
			case 5:

				break;
			
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

		} // end of while loop

	} // main
} // end class Client
