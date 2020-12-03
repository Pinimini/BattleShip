package battleship;

import java.util.ArrayList;

public enum Ships {
    AIRCRAFT_CARRIER(5, "Aircraft Carrier"),
    BATTLESHIP(4, "Battleship"),
    SUBMARINE(3, "Submarine"),
    CRUISER(3, "Cruiser"),
    DESTROYER(2, "Destroyer"),
    AIRCRAFT_CARRIER1(5, "Aircraft Carrier"),
    BATTLESHIP1(4, "Battleship"),
    SUBMARINE1(3, "Submarine"),
    CRUISER1(3, "Cruiser"),
    DESTROYER1(2, "Destroyer");
    int length;
    String name;
    boolean isOk;
    boolean isNotNear = true;
    boolean isSank;
    ArrayList<Integer> coordShipsX = new ArrayList<>();
    ArrayList<Integer> coordShipsY = new ArrayList<>();
    int countShot = 0;
    static int allShot = 0;
    static int allShot1 = 0;


    Ships(int length, String name) {
        this.length = length;
        this.name = name;
        this.isOk = false;
    }
    public void checkShip(int x1, int y1, int x2, int y2, String[][] playerField) {
        if (x1 != x2 && y1 != y2) {
            System.out.println("\nError! Wrong ship location! Try again:\n");
        } else if ((Math.abs(x2 - x1) + 1 != this.length && y1 == y2) || Math.abs(y2 - y1) + 1 != this.length && x1 == x2) {
            System.out.println("\nError! Wrong length of the " + this.name + "! Try again:\n");
        } else if (!checkIsNotNear(x1, y1, x2, y2, playerField)) {
            System.out.println("\nError! You placed it too close to another one. Try again:\n");
            this.isNotNear = true;
        } else {
            isOk = true;
        }
    }

    public boolean checkIsNotNear(int x1, int y1, int x2, int y2, String[][] playerField) {
        if (x1 == x2) {
            if (y2 > y1) {
                for (int k = y1; k <= y2; k++) {
                    if (playerField[x1][k].equals("O ") || playerField[x1 + 1][k].equals("O ") || playerField[x1 - 1][k].equals("O ") || playerField[x1][k + 1].equals("O ") || playerField[x1][k - 1].equals("O ") || playerField[x1 + 1][k + 1].equals("O ") || playerField[x1 + 1][k - 1].equals("O ") || playerField[x1 - 1][k + 1].equals("O ") || playerField[x1 - 1][k - 1].equals("O ")) {
                        isNotNear = false;
                        break;
                    }
                }
            } else {
                for (int k = y2; k <= y1; k++) {
                    if (playerField[x1][k].equals("O ") || playerField[x1 + 1][k].equals("O ") || playerField[x1 - 1][k].equals("O ") || playerField[x1][k + 1].equals("O ") || playerField[x1][k - 1].equals("O ") || playerField[x1 + 1][k + 1].equals("O ") || playerField[x1 + 1][k - 1].equals("O ") || playerField[x1 - 1][k + 1].equals("O ") || playerField[x1 - 1][k - 1].equals("O ")) {
                        isNotNear = false;
                        break;
                    }
                }
            }
        } else {
            if (x2 > x1) {
                for (int k = x1; k <= x2; k++) {
                    if (playerField[k][y1].equals("O ") || playerField[k + 1][y1].equals("O ") || playerField[k - 1][y1].equals("O ") || playerField[k][y1 + 1].equals("O ") || playerField[k][y1 - 1].equals("O ") || playerField[k + 1][y1 + 1].equals("O ") || playerField[k + 1][y1 - 1].equals("O ") || playerField[k - 1][y1 + 1].equals("O ") || playerField[k - 1][y1 - 1].equals("O ")) {
                        isNotNear = false;
                        break;
                    }
                }
            } else {
                for (int k = x2; k <= x1; k++) {
                    if (playerField[k][y1].equals("O ") || playerField[k + 1][y1].equals("O ") || playerField[k - 1][y1].equals("O ") || playerField[k][y1 + 1].equals("O ") || playerField[k][y1 - 1].equals("O ") || playerField[k + 1][y1 + 1].equals("O ") || playerField[k + 1][y1 - 1].equals("O ") || playerField[k - 1][y1 + 1].equals("O ") || playerField[k - 1][y1 - 1].equals("O ")) {
                        isNotNear = false;
                        break;
                    }
                }
            }
        }
        return isNotNear;
    }
}
