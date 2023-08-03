import java.util.Scanner;
public class Assignment1 {
	private char[][] outlook;
    private int runners_row;
    private int runners_col;
    private int steps_taken;
    private int highest_s;

    public Assignment1() {
    	outlook = new char[][]{
                {'#', '#', '#', '#', '#', '#', '#'},
                {'#', 'P', '.', '.', '.', '.', '#'},
                {'#', '#', '#', '#', '.', '#', '#'},
                {'#', '.', '.', '.', '.', '.', '#'},
                {'#', '.', '#', '#', '#', '#', '#'},
                {'#', '.', '.', '.', '.', 'E', '#'},
                {'#', '#', '#', '#', '#', '#', '#'}
        };
        runners_row = 1;
        runners_col = 1;
        steps_taken = 0;
        highest_s = 0;
    }
    
    //Prints maze in 2d array
    private void Print_maze() {
    	for (int r = 0; r < outlook.length; r++) {
            for (int c = 0; c < outlook[r].length; c++) {
                System.out.print(outlook[r][c] + " ");
            }
            System.out.println();
        }
    }

    // Check for step validity
    private boolean Valid_move(int NewX, int NewY) {
        return NewX >= 0 && NewX < outlook.length && NewY >= 0 
        		&& NewY < outlook[0].length && outlook[NewX][NewY] != '#';
    }

    //Initializing Keys
    private void Move_runner(char direction) {
        int horizontal = runners_row;
        int vertical = runners_col;

        switch (direction) {
            case 'A':
        	    vertical--;
                steps_taken++;
                break;
            case 'W':
            	horizontal--;
                steps_taken++;
                break;
            case 'D':
            	vertical++;
                steps_taken++;
                break;
            case 'S':
            	horizontal++;
                steps_taken++;
                break;
                
        }

        //Gives all info after reaching point (E)
        if (Valid_move(horizontal, vertical)) {
            if (outlook[horizontal][vertical] == 'E') {
                System.out.println("Congratulations! " + "\n" + "You have reached the exit. \n");
                System.out.println("Steps taken: " + steps_taken);
                if (steps_taken < highest_s || highest_s == 0) {
                	highest_s = steps_taken;
                }
                steps_taken = 0;
                Restart_game();
            } else {
            	outlook[runners_row][runners_col] = '.';
            	runners_row = horizontal;
            	runners_col = vertical;
                outlook[runners_row][runners_col] = 'P';
                Print_maze();
            }//If walled toward wall
        } else {
            System.out.println("You have walked into the wall!" + "\n" + "Try again.\n");
        }
    }

    // Restarts Game if player wants to continue playing
    private void Restart_game() {
    	Scanner Scan = new Scanner(System.in);
    	System.out.println("Do you wanna play again? (Y/N): " );
    	char option = Scan.nextLine().toUpperCase().charAt(0);
    	if(option == 'Y') {
    		runners_row = 1; //Initializing position of (P) back to its original position
        	runners_col = 1;
        	steps_taken = 0;
    		Play_game();
   
    	} else {
    		Exit_game();
    	}
    			
    }


    public void Play_game() {

        Scanner Scan = new Scanner(System.in);
        while (true) {
            System.out.print("\nPlayer Keys (A/W/D/S): ");
            char step = Scan.next().charAt(0);
            Move_runner(step);
        }
    }

    //Displays Instructions for player
    private void Show_instruction() {
        System.out.println("Instructions: \n" + 
                "Starting from point (P), find your way to the exit (E). \n" + 
        		"Try to avoid the walls. \n" + "\n" +
                "Use Keys:    In Manner: \n" + 
        		"    W            ^   \n" + "  A S D        < v >  \n");
    }

    //Displays Developers Credit
    private void Show_credit() {
        System.out.println("Credits: \n" + 
                "-----Maze Runner-----\n" +
                "    [1st Version]     \n" +
        		"  Developed by Hajra   \n");
    }

    
    //Displays far most Highest score
    private void Show_high_score() {
        System.out.println("Highest Score: " + highest_s);
    }

    //Exiting the Game
    private void Exit_game() {
        System.out.println("Hope you enjoyed the game! ^-^");
        System.exit(0);
    }

    //Displays Initial Menu
    private void mainMenu() {
        Scanner Scan = new Scanner(System.in);
        while (true) {
            System.out.println("\n        Main Menu       \n" +
            "--------------------------\n" +
            "a. Play Game \n" +
          	"b. Instructions \n" +
            "c. Credits \n" +
            "d. High Score \n" +
            "e. Exit \n" + 
            "Enter your choice: ");
            char option = Scan.next().charAt(0);

            switch (option) {
                case 'a':
                    System.out.println("The Game has Begin!");
                    Print_maze();
                    Play_game();
                    break;
                case 'b':
                	Show_instruction();
                    break;
                case 'c':
                	Show_credit();
                    break;
                case 'd':
                	Show_high_score();
                    break;
                case 'e':
                	Exit_game();
                    break;
                default:
                    System.out.println("Invalid option!\n" + "Please try again.");
            }
        }
    }

    //
    
    
    public static void main(String[] args) {
        Assignment1 The_maze = new Assignment1();
        System.out.println("Welcome to Maze Runner!");
        The_maze.mainMenu();
        The_maze.Print_maze();
        The_maze.Play_game();
    }
}


