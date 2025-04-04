package classAssignments;

import java.util.Scanner;

import linkedStack.LinkedStack;
import linkedStack.Node;

public class TowerOfHanoi {
	public static void main(String[] args) {
		//Create three pegs
		LinkedStack<Integer> pegA = new LinkedStack<Integer>();
		LinkedStack<Integer> pegB = new LinkedStack<Integer>();
		LinkedStack<Integer> pegC = new LinkedStack<Integer>();
		
		//Have the user enter a number of disks to move
		System.out.println("Enter # of disks: ");
		Scanner userInput = new Scanner(System.in);
		int diskNumber = userInput.nextInt();
		
		//Put the number of disks the user enters on the first Peg
		for(int i = diskNumber; i > 0; i--) {
			addDisk(pegA, i);
		}
		
		//Print the pegs before to show original states
		System.out.println("Pegs Before: ");
		printPegs(pegA, pegB, pegC);
		System.out.println("");
		
		//Show steps to solve the TowerOfHanoi
		//Calculate and print # of steps it should take
		int moves = 2;
		for(int i = 1; i < diskNumber; i++) {
			moves *= 2;
		}
		moves--;
		System.out.println("Steps to Solve (# of steps: " + moves + "): ");
		moveDisks(pegA, pegB, pegC, diskNumber, "Peg A", "Peg B", "Peg C");
		System.out.println("");
		
		//Print the pegs after the disks have been moved
		System.out.println("Pegs After: ");
		printPegs(pegA, pegB, pegC);
	}
	
	public static void addDisk(LinkedStack<Integer> peg, int disk) {
		//If peg is empty, just add disk
		if(peg.size() == 0) {
			peg.push(disk);
		}
		//If not, check if the disk at the top is bigger than the disk to be added, 
		//if so add it, if not then you cannot because a disk cannot be placed on top of a smaller disk
		else {
			int topDisk = peg.peek();
			if(topDisk > disk) {
				peg.push(disk);
			}
			else {
				System.out.println("Cannot add disk above a disk of smaller size");
			}
		}
	}
	
	public static void printDisks(LinkedStack<Integer> peg) {
		//Clone the peg you want to print so you do not mess with the order of the original
		LinkedStack<Integer> pegCopy = peg.clone();
		//If the peg has no disks, print Empty Peg
		if(pegCopy.isEmpty()) {
			System.out.println("Empty Peg");
			return;
		}
		//Otherwise, go through the peg and print the disks in order until the peg is empty
		int disk = pegCopy.pop();
		while(!pegCopy.isEmpty()) {
			System.out.println("Disk " + disk);
			disk = pegCopy.pop();
			if(pegCopy.isEmpty()) {
				System.out.println("Disk " + disk);
				break;
			}
		}
	}
	
	public static void printPegs(LinkedStack<Integer> pegA, LinkedStack<Integer> pegB, LinkedStack<Integer> pegC) {
		//Print all of the pegs and whatever disks they have in them
		System.out.println("Peg A: ");
		printDisks(pegA);
		System.out.println("");
		
		System.out.println("Peg B: ");
		printDisks(pegB);
		System.out.println("");
		
		System.out.println("Peg C: ");
		printDisks(pegC);
	}
	
	public static void moveDisks(LinkedStack<Integer> source, LinkedStack<Integer> extra, LinkedStack<Integer>destination, int disks, String sourceTxt, String extraTxt, String destinationTxt) {		
		//If there is only one disk to move, move it directly from source to destination
		if (disks == 1) {
            // Move one disk directly
            int disk = source.pop();
            destination.push(disk);
            System.out.println("Move disk " + disk + " from " + sourceTxt + " to " + destinationTxt);
            return;
        }

        // Step 1: Move n-1 disks from source peg to extra peg
        moveDisks(source, destination, extra, disks - 1, sourceTxt, destinationTxt, extraTxt);

        // Step 2: Move the nth disk from source peg to destination peg
        int disk = source.pop();
        destination.push(disk);
        System.out.println("Move disk " + disk + " from " + sourceTxt + " to " + destinationTxt);

        // Step 3: Move the n-1 disks from extra peg to destination peg
        moveDisks(extra, source, destination, disks - 1, extraTxt, sourceTxt, destinationTxt);
	}
}