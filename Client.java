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
	private static Scanner scan;


	public static void main(String[] args) {

		scan = new Scanner(System.in);

		/* Local variables */
		int choice = 0; // Used in switch statement for menu choice
		byte[] addr = new byte[4]; // byte array to store the destination array
		InetAddress serverIP = null;
		int numberOfClients = Integer.parseInt(args[2]);
		
		// choice = scan.nextInt();
		/* 
		 * Grab the server IP
		 */
		   

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
			serverIP = InetAddress.getByAddress(addr);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} // inetaddress object for use with Socket connections
		/* 
		 *  Grab the number of clients 
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
		   if(choice >= 1 && choice <= 6){
			   sendCmd(choice, portNumber, serverIP);
		   }
			
		}while(choice != 7);

	
	} // main


	private static void sendCmd(int choice, int portNumber, InetAddress serverIP) {
		// TODO Auto-generated method stub
		
		try {
		    Socket kkSocket = new Socket(serverIP, portNumber);
		    PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
		    BufferedReader in = new BufferedReader( new InputStreamReader(kkSocket.getInputStream()));
			} 
		catch(Exception ex) {}
		
	}


	public static int userMenu() {
		
		
		System.out.println("Select a function:");
		System.out.println("1.	Host current Date and Time");
		System.out.println("2.	Host uptime");
		System.out.println("3.	Host memory use");
		System.out.println("4.	Host Netstat");
		System.out.println("5.	Host current users");
		System.out.println("6.	Host running processes");
		System.out.println("7.	Quit");
		
		
		return scan.nextInt();
	}

} // end class Client
