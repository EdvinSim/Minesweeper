import java.util.Scanner;

public class MainMinesweeper {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Gamebord size: ");
        int size = Integer.parseInt(sc.nextLine());

        Gameboard board = new Gameboard(size);


        while(board.leftToBeOpened() > 0 && !board.gameOver()) {
            System.out.println(board);
            //System.out.println(board.toStringHiddenValues());

            System.out.print("open/flagg row col: ");
            String[] input = sc.nextLine().split(" ");

            try {
                if(input[0].equals("open")) {
                    board.open(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
                }
                else if(input[0].equals("flagg")) {
                    board.flagg(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
                }
                else {
                    System.out.println("\nIncorrect command!");    
                }
            }
            catch (Exception e) {
                System.out.println("\nIncorrect command!");
                // TODO: handle exception
            }

        }

        sc.close();

        System.out.println(board);

        if(board.gameOver()) {
            System.out.println("GAME OVER!");
        }
        else {
            System.out.println("CONGRATULATIONS!");
        }

    }
}
