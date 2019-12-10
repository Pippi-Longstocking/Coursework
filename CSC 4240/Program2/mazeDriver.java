import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class mazeDriver {

	public static void main (String[] args) throws Exception {
		
		String fileName = args[0];
		int numActions;
		int numStates;
		float gamma;
		float reward;
		boolean terminalState;
		String stateFile;
		int statesCounter = 0;
		//change if necessary
		final int NUM_ITERATIONS = 20;

		ArrayList<State> states = new ArrayList<State>();
		File inputFile = new File (fileName); 
		Scanner scan = new Scanner(inputFile);
		
		/*
		Reads in initial information (number of actions, number of states, and gamma) from command line argument file.
		*/
		numActions = scan.nextInt();
		numStates = scan.nextInt();
		gamma = scan.nextFloat();

		/*
			Reads in reward and text files for each state.
		*/
		while (scan.hasNextLine()) {
			reward = scan.nextFloat();
			terminalState = (scan.nextInt() == 1);
			stateFile = scan.next();
			try {
				states.add(new State(stateFile, reward, terminalState));
			}
			catch (Exception e)
				{
					System.out.println("File not found for state " + (statesCounter + 1) + ". Creating terminal state.");
					states.add(new State(reward, terminalState));
				}
				statesCounter++;
		}

		utilityCalculator utilCalculator = new utilityCalculator(states, gamma);
        utilCalculator.computeUtilities(NUM_ITERATIONS);
        utilCalculator.computeActions();
        utilCalculator.getInfo();
		}
	}
