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
		int portNumber = 1234; // Integer.parseInt(args[0]);
		Socket clientSocket = null;
		ServerSocket serverSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		String s = null;
		Process p = null;
		BufferedReader stdInput;

		System.out.println("Server listening on port " + portNumber + "...");

		serverSocket = new ServerSocket(portNumber);

		while (true) {

			clientSocket = serverSocket.accept();
			System.out.print("  Client connected: ");
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			String temp = in.readLine();
			// System.out.println(temp);
			int choice = Integer.parseInt(temp);

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
			case 7:
				System.out.println("Quitting...");
				System.exit(1);
				break;
			} // end of switch

			out.println("\000\001\002"); // session termination sequence

		} // end while

	} // main
} // end class Server
