package battleship;

import java.util.Scanner;

public class Player {

    static Scanner sc = new Scanner(System.in);
    public String[][] playerField = new String[12][12];
    public String[][] enemyField = new String[12][12];
    static String alphabet = "ABCDEFGHIJ";
    private final String name;

    Player(String name) {
        this.name = name;
    }


    public void createField(String[][] field) {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                if (i == 11 || j == 11) {
                    field[i][j] = " ";
                } else if (i != 0 && j !=0) {
                    field[i][j] = "~ ";
                } else if (i == 0 && j == 0) {
                    field[i][j] = "  ";
                } else if (i == 0) {
                    field[i][j] = j + " ";
                } else {
                    field[i][j] = alphabet.charAt(i - 1) + " ";
                }
            }
        }
    }

    public void printField(String[][] field) {
        System.out.println();
        for (String[] mas :
                field) {
            for (String ch :
                    mas) {
                System.out.print(ch);
            }
            System.out.println();
        }
    }


    public void shipPlacement(String coordinate, Ships ship) {
        String[] coordinate1 = coordinate.split(" ");
        StringBuilder firstCoordinate = new StringBuilder(coordinate1[0]);
        StringBuilder secondCoordinate = new StringBuilder(coordinate1[1]);
        int firstCoordinateX = alphabet.indexOf(firstCoordinate.charAt(0)) + 1;
        int firstCoordinateY = Integer.parseInt(firstCoordinate.substring(1));
        int secondCoordinateX = alphabet.indexOf(secondCoordinate.charAt(0)) + 1;
        int secondCoordinateY = Integer.parseInt(secondCoordinate.substring(1));
        ship.checkShip(firstCoordinateX, firstCoordinateY, secondCoordinateX, secondCoordinateY, playerField);
        if(ship.isOk) {
            if (firstCoordinateX == secondCoordinateX && secondCoordinateY > firstCoordinateY) {
                for (int i = firstCoordinateY; i <= secondCoordinateY; i++) {
                    playerField[secondCoordinateX][i] = "O ";
                    ship.coordShipsX.add(secondCoordinateX);
                    ship.coordShipsY.add(i);
                }
            } else if (firstCoordinateY == secondCoordinateY && secondCoordinateX > firstCoordinateX) {
                for (int i = firstCoordinateX; i <= secondCoordinateX; i++) {
                    playerField[i][secondCoordinateY] = "O ";
                    ship.coordShipsX.add(i);
                    ship.coordShipsY.add(secondCoordinateY);
                }
            } else if (firstCoordinateX == secondCoordinateX && firstCoordinateY > secondCoordinateY) {
                for (int i = secondCoordinateY; i <= firstCoordinateY; i++) {
                    playerField[secondCoordinateX][i] = "O ";
                    ship.coordShipsX.add(secondCoordinateX);
                    ship.coordShipsY.add(i);
                }
            } else if (firstCoordinateY == secondCoordinateY && firstCoordinateX > secondCoordinateX) {
                for (int i = secondCoordinateX; i <= firstCoordinateX; i++) {
                    playerField[i][secondCoordinateY] = "O ";
                    ship.coordShipsX.add(i);
                    ship.coordShipsY.add(secondCoordinateY);
                }
            }
        }
    }

    public void initialField() {
        System.out.println(name + ", place your ships on the game field");
        createField(playerField);
        createField(enemyField);
        printField(playerField);
        setShips(Ships.AIRCRAFT_CARRIER);
        setShips(Ships.BATTLESHIP);
        setShips(Ships.SUBMARINE);
        setShips(Ships.CRUISER);
        setShips(Ships.DESTROYER);
        Ships.AIRCRAFT_CARRIER.isOk = false;
        Ships.BATTLESHIP.isOk = false;
        Ships.SUBMARINE.isOk = false;
        Ships.CRUISER.isOk = false;
        Ships.DESTROYER.isOk = false;
        System.out.println("Press Enter and pass the move to another player");
        System.out.print("...");
        sc.nextLine();
    }

    public void initialField1() {
        System.out.println(name + ", place your ships on the game field");
        createField(playerField);
        createField(enemyField);
        printField(playerField);
        setShips(Ships.AIRCRAFT_CARRIER1);
        setShips(Ships.BATTLESHIP1);
        setShips(Ships.SUBMARINE1);
        setShips(Ships.CRUISER1);
        setShips(Ships.DESTROYER1);
        Ships.AIRCRAFT_CARRIER1.isOk = false;
        Ships.BATTLESHIP1.isOk = false;
        Ships.SUBMARINE1.isOk = false;
        Ships.CRUISER1.isOk = false;
        Ships.DESTROYER1.isOk = false;
        System.out.println("Press Enter and pass the move to another player");
        System.out.print("...");
        sc.nextLine();
    }

    public void setShips(Ships ship) {
        System.out.println("Enter the coordinates of the " + ship.name + " (" + ship.length + " cells):\n");
        while (!ship.isOk) {
            String coor = sc.nextLine();
            shipPlacement(coor, ship);
        }
        printField(playerField);
    }

    public void fight() {

        System.out.println(this.name + ", it's your turn:\n");
        boolean isShot = false;
        while (!isShot) {
            String coordinate = sc.nextLine();
            String[] coordinate1 = coordinate.split(" ");
            StringBuilder firstCoordinate = new StringBuilder(coordinate1[0]);
            int CoordinateX = alphabet.indexOf(firstCoordinate.charAt(0)) + 1;
            int CoordinateY = Integer.parseInt(firstCoordinate.substring(1));
            if (alphabet.indexOf(firstCoordinate.charAt(0)) == -1 || CoordinateY < 1 || CoordinateY > 10) {
                System.out.println("\nError! You entered the wrong coordinates! Try again:\n");
            } else {
                if (this.name.equals("Player 1")) {
                    if (Main.player2.playerField[CoordinateX][CoordinateY].equals("O ") || Main.player1.playerField[CoordinateX][CoordinateY].equals("X ")) {
                        Main.player2.playerField[CoordinateX][CoordinateY] = "X ";
                        enemyField[CoordinateX][CoordinateY] = "X ";
                        checkShipDamage(CoordinateX, CoordinateY, Ships.AIRCRAFT_CARRIER1);
                        checkShipDamage(CoordinateX, CoordinateY, Ships.BATTLESHIP1);
                        checkShipDamage(CoordinateX, CoordinateY, Ships.SUBMARINE1);
                        checkShipDamage(CoordinateX, CoordinateY, Ships.CRUISER1);
                        checkShipDamage(CoordinateX, CoordinateY, Ships.DESTROYER1);
                        printField(enemyField);
                        if (sankShip(Ships.AIRCRAFT_CARRIER1) || sankShip(Ships.BATTLESHIP1) || sankShip(Ships.CRUISER1) || sankShip(Ships.SUBMARINE1) || sankShip(Ships.DESTROYER1)) {
                            if (Ships.AIRCRAFT_CARRIER1.isSank && Ships.BATTLESHIP1.isSank && Ships.CRUISER1.isSank && Ships.SUBMARINE1.isSank && Ships.DESTROYER1.isSank){
                                System.out.println("You sank the last ship. You won. Congratulations!");
                                System.exit(0);
                            } else {
                                System.out.println("You sank a ship!\n");
                            }
                        } else {
                            System.out.println("You hit a ship!\n");
                        }
                    } else {
                        Main.player2.playerField[CoordinateX][CoordinateY] = "M ";
                        enemyField[CoordinateX][CoordinateY] = "M ";
                        System.out.println("\nYou missed!");

                    }
                }
                if (this.name.equals("Player 2")) {
                    if (Main.player1.playerField[CoordinateX][CoordinateY].equals("O ") || Main.player1.playerField[CoordinateX][CoordinateY].equals("X ")) {
                        Main.player1.playerField[CoordinateX][CoordinateY] = "X ";
                        enemyField[CoordinateX][CoordinateY] = "X ";
                        checkShipDamage(CoordinateX, CoordinateY, Ships.AIRCRAFT_CARRIER);
                        checkShipDamage(CoordinateX, CoordinateY, Ships.BATTLESHIP);
                        checkShipDamage(CoordinateX, CoordinateY, Ships.SUBMARINE);
                        checkShipDamage(CoordinateX, CoordinateY, Ships.CRUISER);
                        checkShipDamage(CoordinateX, CoordinateY, Ships.DESTROYER);
                        printField(enemyField);


                        if (sankShip(Ships.AIRCRAFT_CARRIER) || sankShip(Ships.BATTLESHIP) || sankShip(Ships.CRUISER) || sankShip(Ships.SUBMARINE) || sankShip(Ships.DESTROYER)) {
                            if (Ships.AIRCRAFT_CARRIER.isSank && Ships.BATTLESHIP.isSank && Ships.CRUISER.isSank && Ships.SUBMARINE.isSank && Ships.DESTROYER.isSank) {
                                System.out.println("You sank the last ship. You won. Congratulations!");

                                System.exit(0);
                            } else {
                                System.out.println("You sank a ship!\n");
                            }
                        } else {
                            System.out.println("You hit a ship!\n");
                        }
                    } else {
                        Main.player1.playerField[CoordinateX][CoordinateY] = "M ";
                        enemyField[CoordinateX][CoordinateY] = "M ";
                        System.out.println("\nYou missed!");

                    }
                }
                isShot = true;
            }
        }
        System.out.println("Press Enter and pass the move to another player");
        System.out.print("...");
        sc.nextLine();
    }

    public static void checkShipDamage(int ShipX, int ShipY, Ships shippy) {
        if (shippy.coordShipsX.contains(ShipX) && shippy.coordShipsY.contains(ShipY)){
            shippy.countShot++;
        }
    }
    public static boolean sankShip(Ships shippy) {
        if (shippy.countShot == shippy.length) {
            Ships.allShot += shippy.countShot;
            shippy.isSank = true;
            shippy.countShot = 0;
            return true;
        } else return false;
    }
}
