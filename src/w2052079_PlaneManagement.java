import java.util.*;
public class w2052079_PlaneManagement {
    private static final int AVAILABLE = 0;
    private static final int BOOKED = 1;
    private static ArrayList<Ticket> Soldtickets = new ArrayList<>();

    public static void main(String[] args) {
       System.out.println("          Welcome to the Plane Management application             ");
       int[][] seats = new int[4][];
       char rowLetter = 'A';
       int rowIndex = rowLetter - 'A';
       seats[0] = new int[14];
       seats[1] = new int[12];
       seats[2] = new int[12];
       seats[3] = new int[14];
       for (int i = 0 ; i < seats.length ; i++) {
           for (int j = 0 ; j < seats[i].length ; j++ ) {
               seats[i][j] = AVAILABLE;
           }
       }
       Scanner input = new Scanner (System.in);
       boolean quit = false;
       while (!quit) {
           System.out.println("******************************************************");
           System.out.println("*                    MENU OPTIONS                    *");
           System.out.println("******************************************************");
           System.out.println("         1) Buy a seat");
           System.out.println("         2) Cancel a seat");
           System.out.println("         3) Find first available seat");
           System.out.println("         4) Show seating plan");
           System.out.println("         5) Print tickets information and total sales");
           System.out.println("         6) Search ticket");
           System.out.println("         0) Quit");
           System.out.println("******************************************************");
           System.out.print("Please select an option : ");

           int option = input.nextInt();
           switch (option) {
               case 1:
                   buy_seat(seats, input);
                   break;
               case 2:
                   cancel_seat(seats, input);
                   break;
               case 3:
                   find_first_available(seats);
                   break;
               case 4:
                   show_seating_plan(seats);
                   break;
               case 5:
                   print_tickets_info();
                   break;
               case 6:
                   search_ticket(seats, input);
                   break;
               case 0:
                   quit = true;
                   System.out.println("Thank You for Cooperation ! Good Bye !");
                   break;
               default :
                   System.out.println("Invalid option ! Please Try Again !");

           }

       }

    }
    public static void buy_seat(int[][] seats , Scanner input) {
        System.out.println("Please enter row letter ( A - D ) : ");
        String row = input.next().toUpperCase();
        if (row.length() != 1 || row.charAt(0) < 'A' || row.charAt(0) > 'D') {
            System.out.println("Invalid row letter. Please enter row letter between A and D !");
            return;
        }

        int row_s = row.charAt(0)-'A';

        System.out.println("Please enter seat number: ");
        int seat_number = input.nextInt();
        if (seat_number < 1 || seat_number > seats[row_s].length ){
            System.out.println("Invalid seat number. Please enter valid seat number for selected row !");
            return;
        }
        seat_number = seat_number - 1;

        if (seats[row_s][seat_number] == BOOKED){
            System.out.println("This seat is already booked. Please consider another one ! ");
        }
        else {
            System.out.println("Please enter your name: ");
            String name = input.next();

            System.out.println("Please enter your surname: ");
            String surname = input.next();

            System.out.println("Please enter your email: ");
            String email = input.next();

            Person person = new Person(name , surname , email);
            double price;
            if (seat_number >= 0 && seat_number <= 4){
                price = 200;
            }
            else if (seat_number >= 5 && seat_number <= 8){
                price = 150;
            }
            else {
                price = 180;
            }
            Ticket ticket = new Ticket((char)('A'+ row_s) , seat_number + 1 , price , person  );

            seats[row_s][seat_number] = BOOKED;
            Soldtickets.add(ticket);
            ticket.save();
            System.out.println("Your booking is Completed ! ");
        }
    }

    public static void cancel_seat(int[][] seats , Scanner input) {
        System.out.println("Please enter row letter of your seat( A - D ) : ");
        String row = input.next().toUpperCase();
        if (row.length() != 1 || row.charAt(0) < 'A' || row.charAt(0) > 'D') {
            System.out.println("Invalid row letter. Please enter row letter between A and D !");
            return;
        }

        int row_s = row.charAt(0)-'A';

        System.out.println("Please enter seat number: ");
        int seat_number = input.nextInt();
        if (seat_number < 1 || seat_number > seats[row_s].length ){
            System.out.println("Invalid seat number. Please enter valid seat number for selected row !");
            return;
        }

        seat_number = seat_number - 1;

        if (seats[row_s][seat_number] == BOOKED ) {
            seats[row_s][seat_number] = AVAILABLE;
            Ticket ticket_to_remove = null;
            for (Ticket ticket : Soldtickets) {
                if (ticket.getRow() == row.charAt(0) && ticket.getSeat() == seat_number + 1) {
                    ticket_to_remove = ticket;
                    break;
                }
            }
            if (ticket_to_remove != null) {
                Soldtickets.remove(ticket_to_remove);
                System.out.println("Your cancellation is Successful and the ticket has been also removed ! ");
            } else {
                System.out.println("Error ! There is no matching ticket found ");
            }
        }

        else {
            System.out.println("Your seat " + seats[row_s][seat_number] + "is not booked ! Please check Again !" );
        }
    }

    public static void find_first_available(int[][] seats){
        for (int rowt = 0; rowt < seats.length ; rowt++){
            for (int seat = 0 ; seat < seats[rowt].length ; seat++){
                if (seats[rowt][seat] == AVAILABLE ){
                    char rowL = (char)( 'A' + rowt);
                    System.out.println("Congratulations ! You just found the first available seat and your seat is : " + "Row = " + rowL + " seat = " + (seat + 1));
                    return;
                }
            }
        }
        System.out.println("Sorry ! There are not any available seats for the moment.");
    }

    public static void show_seating_plan(int[][] seats){
        System.out.println("                       Seating Plan                    ");
        System.out.println("  1 2 3 4 5 6 7 8 9 10 11 12 13 14");
        char letter = 'A';
        for (int i = 0 ; i < seats.length ; i++ , letter++){
            System.out.print(letter + " ");
            for (int j = 0 ; j < seats[i].length ; j++){
                if (seats[i][j] == AVAILABLE ){
                    System.out.print("O ");
                }
                else if (seats[i][j] == BOOKED){
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
        System.out.println("              Seat Prices                ");
        System.out.println("Seat No: 1-5 :- 200$");
        System.out.println("Seat No: 6-9 :- 150$");
        System.out.println("Seat No: 10-12 / 10-14 :- 180$");
    }

    public static void print_tickets_info(){
        double Totalsold = 0;
        System.out.println("                 Information of tickets and Total sales                  ");
        for (Ticket ticket : Soldtickets){
            ticket.print_ticket_info();
            Totalsold = Totalsold + ticket.getPrice();
        }
        System.out.println("Total Sold tickets sale :- " + Totalsold + "$");
    }

    public static void search_ticket(int[][] seats , Scanner input){
        System.out.println("Please enter the exact row letter for your searching ticket (A-D) : ");
        String row = input.next().toUpperCase();
        if (row.length() != 1 || row.charAt(0) < 'A' || row.charAt(0) > 'D'){
            System.out.println("Invalid row letter ! Please enter a row letter between A and D ");
            return;
        }
        int row_s =row.charAt(0)-'A';

        System.out.println("Please enter your seat number : ");
        int seat_number = input.nextInt();
        if(seat_number < 1 || seat_number > seats[row_s].length){
            System.out.println("Invalid seat number ! Please enter a correct and valid seat number.");
            return;
        }
        boolean found = false;
        for(Ticket ticket : Soldtickets){
            if(ticket.getRow() == row.charAt(0) && ticket.getSeat() == seat_number){
                System.out.println("The Exact ticket found ! Information :- ");
                ticket.print_ticket_info();
                found = true;
                break;
            }
        }
        if (!found){
            System.out.println("This seat is Available !");
        }
    }
}
