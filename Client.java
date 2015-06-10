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
 * * Nicholas Hecht   *
 * * Matthew Kempey   *
 * * Casey Dotson     *
 * * Braden Weaver    *
 * * Michael Williams *
 * ********************
 *
 * Other Notes:
 * args[0] = SERVER IP 
 * args[1] = SERVER PORT
 * args[2] = NUMBER OF CLIENTS
 * args[3] = SLEEP TIME (TIME IN MS FOR MAIN TO WAIT FOR THREADS TO COMPLETE)
 */

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client implements Runnable{	
	private static Scanner input = new Scanner(System.in);
	private static String serverIP = null;
	private static int portNumber = 0;
	private static long total = 0;
	private static int choice = 0;
	private static int sleepTime = 50;
	
	public static void main(String[] args) throws IOException {		

		System.out.println();
		
		/* Local variables */		
		int numberOfClients = 0;
		double average = 0;
		
		/*
		 * Grab the server IP
		 */
		serverIP = args[0];
		
		/*
		 * Grab server port
		 */
		if (args.length > 1) {
			try {
				portNumber = Integer.parseInt(args[1]);
			} catch (NumberFormatException e) {
				System.err.println("Argument" + args[1]
						+ " must be an integer.");
				System.exit(1);
			}
		}
		
		/*
		 * Grab the number of clients
		 */
		if (args.length > 2) {
			try {
				numberOfClients = Integer.parseInt(args[2]);
			} catch (NumberFormatException e) {
				System.err.println("Argument" + args[2]
						+ " must be an integer.");
				System.exit(1);
			}
		}
		
		/*
		 * Grab thread sleep time
		 */
		sleepTime = Integer.parseInt(args[3]);
		if (args.length > 3) {
			try {
				sleepTime = Integer.parseInt(args[3]);
			} catch (NumberFormatException e) {
				System.err.println("Argument" + args[3]
						+ " must be an integer.");
				System.exit(1);
			}
		}
		
		do {
			choice = userMenu();
			if (choice >= 1 && choice < 7) {
				Thread tclients[] = new Thread[numberOfClients];
				for(int i = 0; i < numberOfClients; i++){
					tclients[i] = new Thread(new Client());
				}
				
				for(int j = 0; j< numberOfClients; j++){
					tclients[j].start();
				}				
				
				try {
				    Thread.sleep(sleepTime);
				} catch (InterruptedException e) {}   // pause for threads to send so the MRT print is after
				
				average = total/(double)numberOfClients; // calculate the mean response time
				
				System.out.println("\nThe mean response time: " + average +" ms"); // print MRT
				average = 0;
				total = 0;
				choice = 0;
			}
		} while (choice != 7);
		System.out.println("Session terminated");
		input.close();
		System.exit(1);
	} // main

	private static void sendCmd() throws IOException {		
		PrintWriter out = null;
		Socket kkSocket = null;
		BufferedReader in = null;		
		String temp = "";
		long t1, t2, tsub;		

		
		kkSocket = new Socket(serverIP, portNumber);
		out = new PrintWriter(kkSocket.getOutputStream(), true);
		t1 = System.currentTimeMillis();
		out.println(choice);
		in = new BufferedReader(
				new InputStreamReader(kkSocket.getInputStream()));
		do {
			temp = in.readLine();
			if (temp.equals("\001\001\002"))
				break;
			else
				System.out.print(temp + "\n");
		} while (temp != null);  //do while
		out.close();
		kkSocket.close();
		t2= System.currentTimeMillis();
		tsub = t2 - t1;
		System.out.println("\nResponse time: " +tsub +" ms");
		total = tsub + total;
	} //sendCmd()

	public static int userMenu() {		
		boolean parsable = true;		
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
				parsable = false;
			}
			if (choice > 7 || choice <= 0)
			{
				System.out.println("Invalid input. Please try again (1-7).");
				parsable = false;
			}
		} while (!parsable);		
		return choice;
	} // end userMenu()

	@Override
	public void run() {		
		try {
			sendCmd();
		} catch (IOException e) {			
			e.printStackTrace();
		}		
	}
} // end class Client
