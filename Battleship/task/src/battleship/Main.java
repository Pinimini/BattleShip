package battleship;

public class Main {
    public static Player player1 = new Player("Player 1");
    public static Player player2 = new Player("Player 2");
    public static void main(String[] args) {

        player1.initialField();
        player2.initialField1();

        do {
            player1.printField(player1.enemyField);
            System.out.println("---------------------");
            player1.printField(player1.playerField);

            player1.fight();
            player2.printField(player2.enemyField);
            System.out.println("---------------------");
            player2.printField(player2.playerField);

            player2.fight();
        } while (Ships.allShot != 17);
    }


}


