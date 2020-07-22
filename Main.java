//refactor code
//make game show after last move, and then show print statement if i won, lost, or tied
//make multiple classes instead of multiple public static voids

package com.company;

import java.util.*;

public class Main {

    //global variables
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    //parameter must be char[][] gameBoard so that method knows what gameBoard is
    public static void printGameBoard(char[][] gameBoard){
        for(char[] row : gameBoard){ //for each row inside of a game board
            for(char symbol : row){ //for each symbol inside of a game board
                System.out.print(symbol);
            }
            System.out.println(); //to make the formatting of the game board
        }
    }

    public static void placePiece(char[][] gameBoard,int pos, String user){

        char symbol = ' ';

        //since it's a String, you must use .equals() and not ==
        if(user.equals("player")){
            symbol = 'X';
            playerPositions.add(pos);
        } else if(user.equals("cpu")){
            symbol = 'O';
            cpuPositions.add(pos);
        }

        switch(pos){ //'switch' acts like an if/else statement
            case 1:
                gameBoard[0][0] = symbol; //symbol can switch from X and O depending on user
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }

    public static String checkWinner(char[][] gameBoard){

        List topRow = Arrays.asList(1,2,3);
        List midRow = Arrays.asList(4,5,6);
        List botRow = Arrays.asList(7,8,9);
        List leftCol = Arrays.asList(1,4,7);
        List midCol = Arrays.asList(2,5,8);
        List rightCol = Arrays.asList(3,6,9);
        List cross1 = Arrays.asList(1,5,9);
        List cross2 = Arrays.asList(3,5,7);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        for(List l: winning){//for each list inside of a winning list
            if (playerPositions.containsAll(l)){
                return "Congratulations you won!";
            } else if (cpuPositions.contains(l)){
                return "Sorry, CPU wins.";
            }else if (playerPositions.size()+cpuPositions.size()==9){
                return "It's a tie!";
            }
        }


        return "";
    }


    public static void main(String[] args) {

        //create game board
        //2-D array is created by rows and column
        char[][] gameBoard = {{' ','|',' ','|',' '},
                              {'-','+','-','+','-'},
                              {' ','|',' ','|',' '},
                              {'-','+','-','+','-'},
                              {' ','|',' ','|',' '}};

        printGameBoard(gameBoard);

        //scans position of user and places position of cpu
        while(true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter your placement (1-9):");
            int playerPos = scan.nextInt(); //since we're asking for the user an integer from 1-9
            //while player does not input a correct position or tries to input a spot already taken
            while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)){
                System.out.println("Position taken! Enter a correct Position:");
                playerPos = scan.nextInt();
            }

            placePiece(gameBoard, playerPos, "player");

            String result = checkWinner(gameBoard);
            if(result.length()>0){
                System.out.println(result);
                break;
            }

            Random rand = new Random();
            //rewrite with a 'do' loop
            int cpuPos = rand.nextInt(9) + 1; //bound is technically 0-8 so +1 in order for bound to be 1-9
            while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)){
                cpuPos = rand.nextInt(9) + 1;
            }


            placePiece(gameBoard, cpuPos, "cpu");

            printGameBoard(gameBoard);

            result = checkWinner(gameBoard);
            if(result.length()>0){
                System.out.println(result);
                break;
            }
        }
    }
}
