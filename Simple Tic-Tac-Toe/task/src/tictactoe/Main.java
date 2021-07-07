package tictactoe;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
        char[][] chars = new char[3][3];
        int[] coordinates;
        boolean isXOnMove = true;
//        char[] tempChars = scanner.next().toCharArray();
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++){
                chars[i][j] = '_';
            }
        printTable(chars);
        while (true){
            coordinates = takeCoordinates(chars);
            if (isXOnMove){
                chars[coordinates[0]][coordinates[1]] = 'X';
                isXOnMove = false;
            } else {
                chars[coordinates[0]][coordinates[1]] = 'O';
                isXOnMove = true;
            }
            printTable(chars);
            if (analyze(chars).equals("X")) {
                System.out.println("X wins");
                break;
            }
            if (analyze(chars).equals("O")) {
                System.out.println("O wins");
                break;
            }
            if (analyze(chars).equals("Draw")) {
                System.out.println("Draw");
                break;
            }
        }
    }

//    private static void analyze(char[][] chars){
//        boolean isPossible = isPossible(chars);
//        boolean isFinished = isFinished(chars);
//        boolean isWin = false;
////        if (!isPossible) System.out.println("Impossible");
//        if(whoWon(chars) == 'X' && isPossible) {
//            System.out.println("X wins");
//            isWin = true;
//        }
//        if(whoWon(chars) == 'O'  && isPossible){
//            System.out.println("O wins");
//            isWin = true;
//        }
////        if (!isFinished && isPossible && !isWin) System.out.println("Game not finished");
//        if (isFinished && isPossible && !isWin) return "Draw";
//    }

    private static String analyze(char[][] chars){
        boolean isFinished = isFinished(chars);
        if(whoWon(chars) == 'X') {
            return "X";
        }
        if(whoWon(chars) == 'O'){
            return "O";
        }
        if (isFinished) return "Draw";
        return "In progress";
    }

    private static char whoWon(char[][] chars){
        if (chars[0][0] == chars[0][1] && chars[0][1] == chars[0][2]) return chars[0][0];
        if (chars[1][0] == chars[1][1] && chars[1][1] == chars[1][2]) return chars[1][0];
        if (chars[2][0] == chars[2][1] && chars[2][1] == chars[2][2]) return chars[2][0];
        if (chars[0][0] == chars[1][0] && chars[1][0] == chars[2][0]) return chars[0][0];
        if (chars[0][1] == chars[1][1] && chars[1][1] == chars[2][1]) return chars[0][1];
        if (chars[0][2] == chars[1][2] && chars[1][2] == chars[2][2]) return chars[0][2];
        if (chars[0][0] == chars[1][1] && chars[1][1] == chars[2][2]) return chars[0][0];
        if (chars[0][2] == chars[1][1] && chars[1][1] == chars[2][0]) return chars[0][2];
        return 'N';
    }

    private static boolean isPossible(char[][] chars){
        int howManyX = 0;
        int howManyO = 0;
        int howManyWinningCombinations = 0;
        for (int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if (chars[i][j] == 'X') howManyX++;
                if (chars[i][j] == 'O') howManyO++;
            }
        }
        if (Math.abs((howManyX - howManyO)) > 1) return false;                                       //If function return false
        if (chars[0][0] == chars[0][1] && chars[0][1] == chars[0][2] && chars[0][0] != '_') howManyWinningCombinations++;  //it means impossible state
        if (chars[1][0] == chars[1][1] && chars[1][1] == chars[1][2] && chars[1][0] != '_') howManyWinningCombinations++;  //had occurred
        if (chars[2][0] == chars[2][1] && chars[2][1] == chars[2][2] && chars[2][0] != '_') howManyWinningCombinations++;
        if (chars[0][0] == chars[1][0] && chars[1][0] == chars[2][0] && chars[0][0] != '_') howManyWinningCombinations++;
        if (chars[0][1] == chars[1][1] && chars[1][1] == chars[2][1] && chars[0][1] != '_') howManyWinningCombinations++;
        if (chars[0][2] == chars[1][2] && chars[1][2] == chars[2][2] && chars[0][2] != '_') howManyWinningCombinations++;
        if (chars[0][0] == chars[1][1] && chars[1][1] == chars[2][2] && chars[0][0] != '_') howManyWinningCombinations++;
        if (chars[0][2] == chars[1][1] && chars[1][1] == chars[2][0] && chars[0][2] != '_') howManyWinningCombinations++;
        return howManyWinningCombinations <= 1;
    }

    private static boolean isFinished(char[][] chars){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j <3; j++) if (chars[i][j] == '_') return false;
        }
        return true;
    }

    private static int[] takeCoordinates(char[][] table){
        Scanner scanner = new Scanner(System.in);
        char[] chars = new char[2];
        while(true){
            System.out.println("Enter the coordinates: ");
            for (int i = 0; i < 2; i++) chars[i] = scanner.next().charAt(0);
//            for (int i = 0; i < 2; i++) if (!Character.isDigit(chars[i])){
//                System.out.println("You should enter numbers!");
//                continue;
//            } else{
//                int X = (int)chars[0];
//                int Y = (int)chars[1];
//            }
            if (!Character.isDigit(chars[0]) || !Character.isDigit(chars[1])){
                System.out.println("You should enter numbers!");
                continue;
            }
            int X = Character.getNumericValue(chars[0]);
            int Y = Character.getNumericValue(chars[1]);
            if (X < 1 || X > 3 || Y < 1 || Y > 3){
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            if (!(table[X - 1][Y - 1] == '_')){
                System.out.println("This cell is occupied! Choose another one! ");
                continue;
            }
            int[] ints = {X - 1, Y - 1};
            return ints;
        }
    }

    private static void printTable(char[][] chars){
        System.out.println("---------");
        System.out.println("| " + chars[0][0] + " " + chars[0][1] + " " + chars[0][2] + " |");
        System.out.println("| " + chars[1][0]+ " " + chars[1][1] + " " + chars[1][2] + " |");
        System.out.println("| " + chars[2][0] + " " + chars[2][1] + " " + chars[2][2] + " |");
        System.out.println("---------");
    }
}