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
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {

		/* System objects */
		Scanner scan = new Scanner(System.in);

		/* Local variables */
		int choice = 0; // Used in switch statement for menu choice

		// choice = scan.nextInt();

		
		
		/* 
		 * Grab the server IP
		 */
		
		String serverIP;   

		if (args.length > 1) {
			try {
				String tempString[] = args[0].split(".");
				if (tempString.length == 4) {
					for (int i = 0; i < tempString.length; i++) {
						int splitter = Integer.parseInt(tempString[i]);

					}
				}
			} catch (NumberFormatException e) {
				System.err.println("Argument" + args[0]
						+ " must be an integer.");
				System.exit(1);

			}
		}

		
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
		} catch () {
			
		}
		
		
		while (true) {

			switch (choice) {
			case 1:

				break;
			case 2:

				break;
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
