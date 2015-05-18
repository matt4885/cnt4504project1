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
import java.util.Scanner;

public class Server {
	public static void main(String[] args) {

		/* System objects */
		Scanner scan = new Scanner(System.in);

		/* Local variables */
		int choice; // Used in switch statement for menu choice

		while (true) {

			switch (choice) {
			/* Host current date and time */
			case 1:

				break;
			/* Host uptime */
			case 2:
			
				break;
			/* Host memory use */
			case 3:

				break;
			/* Host Netstat */
			case 4:

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

		} // end of while loop

	} // main

} // end class Server
