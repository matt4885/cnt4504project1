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

public class Client implements Runnable {
	private static Scanner scan;
	private static String serverIP = null;
	private static int portNumber = 0;
	private static long total = 0;

	public static void main(String[] args) throws IOException {
		System.out.println();
		scan = new Scanner(System.in);
		/* Local variables */
		byte[] ipAddress = new byte[4]; // byte array to store the destination array
		serverIP = args[0];
		int numberOfClients = 0;
		double average = 0;
		
		/*
		 * Grab the server IP
		 */
		if (args.length > 0) {
			try {
				String tempString[] = args[0].split(".");
				if (tempString.length == 4) {
					for (int i = 0; i < tempString.length; i++) { // iterate through the server ipAddressess array
						byte b = Byte.parseByte(tempString[i]); // convert the octet to a byte
						ipAddress[i] = b; // store byte in appropriate ipAddress array element
					}
				}
			} catch (NumberFormatException e) {
				System.err.println("Argument" + args[0] + " must be an integer.");
				System.exit(1);
			}
		}

		/*
		 * Grab the port number
		 */
		if (args.length > 1) {
			try {
				portNumber = Integer.parseInt(args[1]);
			} catch (NumberFormatException e) {
				System.err.println("Argument" + args[1] + "must be an integer.");
			}
		}
		
		/*
		 * Grab the number of clients
		 */
		if (args.length > 2) {
			try {
				numberOfClients = Integer.parseInt(args[2]);
			} catch (NumberFormatException e) {
				System.err.println("Argument" + args[2] + " must be an integer.");
				System.exit(1);
			}
		}
		
		Thread tclients[] = new Thread[numberOfClients];
		
		for (int i = 0; i < numberOfClients; i++) {
			tclients[i] = new Thread(new Client());
		}
		for (int j = 0; j < numberOfClients; j++) {
			tclients[j].start();
		}

		try {
			Thread.sleep(10000);   // pause for threads to send so the MRT print is after
		} catch (InterruptedException e) {
		} 
		average = total / (double) numberOfClients; // calculate the mean
													// response time
		System.out.println("\nThe mean response time: " + average); // print MRT
	} // main

	private static void sendCmd(int choice, int portNumber, String serverIP)
			throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out = null;
		Socket socket = null;
		BufferedReader in = null;
		String temp = "";
		long t1, t2, tsub;
		 try {
		socket = new Socket(serverIP, portNumber);
		 } catch (ConnectException serverDown) {
			 System.out.println("Cannot connect to server...");
			 System.out.println("Quitting now...");
			 System.exit(1);
		 }
		out = new PrintWriter(socket.getOutputStream(), true);
		t1 = System.currentTimeMillis();
		out.println(choice);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		do {
			if (choice != 7)
				temp = in.readLine();
			else
				System.exit(1);
			if (temp.equals("\001\001\002"))
				break;
			else
				System.out.print(temp + "\n");
		} while (temp != null); // do while
		out.close();
		socket.close();
		t2 = System.currentTimeMillis();
		tsub = t2 - t1;
		System.out.println(tsub);
		total = tsub + total;
	} // sendCmd()

	public static int userMenu() {
		int choice = 0;
		boolean parsable = false;
		Scanner input = new Scanner(System.in);
		String temp = "";
		do {
			parsable = true;
			System.out.println("\n\nSelect a function:");
			System.out.println("1. Host current Date and Time");
			System.out.println("2. Host uptime");
			System.out.println("3. Host memory use");
			System.out.println("4. Host Netstat");
			System.out.println("5. Host current users");
			System.out.println("6. Host running processes");
			System.out.println("7. Quit");
			try {
				temp = input.next();
				choice = Integer.parseInt(temp);
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please try again (1-7).");
			} finally {
				parsable = false;
			}
		} while (parsable);
		// input.close();
		input.close();
		return choice;
		
	} // end userMenu()

	public void run() {
		try {
			sendCmd(4, portNumber, serverIP);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
} // end class Client
