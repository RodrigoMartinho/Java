package cinema;
import java.util.Scanner;

public class Cinema {
    public static Scanner scanner = new Scanner(System.in);
    public static char[][] room;
    public static int soldTickets = 0;
    public static double percentage = 0;
    public static int totalVendido = 0;
    public static int total = 0;

    public static void main(String[] args) {
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int seatsRow = scanner.nextInt();

        room = new char[rows][seatsRow];

        for (int lin = 0; lin < rows; lin++) {
            for (int col = 0; col < seatsRow; col++) {
                room[lin][col] = 'S';
            }
        }

        showMenu();
    }

    public static void showMenu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        int choose = scanner.nextInt();

        if (choose == 1) {
            showSeats();
            showMenu();
        } else if (choose == 2) {
            sellTickets();
            showMenu();
        } else if (choose == 3) {
            showStatistics();
            showMenu();
        }
    }

    public static void showSeats() {
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int lin = 0; lin <= room.length; lin++) {

            if (lin > 0) System.out.print((lin) + " "); //imprime o nro da linha

            for (int col = 0; col < room[0].length; col++) {
                if (lin == 0) {
                    System.out.print ((col + 1) + " "); //imprime o nro da coluna
                } else {
                    System.out.print (room[lin - 1][col] + " ");
                }
            }
            System.out.println();
        }
    }

    public static void showStatistics() {

        if ((room.length * room[0].length) <= 60) {
            total = (room.length * room[0].length) * 10;
        } else {
            total = ((room.length / 2) * room[0].length) * 10;
            total += (((room.length / 2) + (room.length % 2)) * room[0].length) * 8;
        }

        percentage = (soldTickets * 100.0) / (room.length * room[0].length);

        System.out.println("Number of purchased tickets:" + soldTickets );
        System.out.println("Percentage: " + String.format("%.2f", percentage) + "%");
        System.out.println("Current income: $" + totalVendido);
        System.out.println("Total income: $" + total);
    }

    public static void sellTickets() {
        System.out.println("Enter a row number:");
        int row = scanner.nextInt();

        System.out.println("Enter a seat number in that row:");
        int seat = scanner.nextInt();

        //Verifica se o assento é válido
        if (((row) > room.length) || ((seat) > room[0].length)) {
            System.out.println("Wrong input!");
            sellTickets();
        } else {
            //Verifica se o assento está disponível
            if (room[row - 1][seat - 1] == 'B') {
                System.out.println("That ticket has already been purchased!");
                sellTickets();
            } else {
                double ticket;

                if ((room.length * room[0].length) <= 60) {
                    ticket = 10;
                } else {
                    if (row <= (room.length / 2)) {
                        ticket = 10;
                    } else {
                        ticket = 8;
                    }
                }

                System.out.println("Ticket price: $" + ticket);

                room[row - 1][seat - 1] = 'B';
                soldTickets++;
                totalVendido += ticket;

                showSeats();
            }
        }
    }
}