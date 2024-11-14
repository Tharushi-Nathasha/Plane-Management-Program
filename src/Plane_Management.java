//w20511691-UOW Id
//20230577 -IIT Id
//2023/03/23 - Submission date

import java.util.InputMismatchException;
import java.util.Scanner;

public class Plane_Management {
    //Part B - Task 09
    static Ticket[] ticketsSold = new Ticket[100]; // Assuming the size of ticket to 100
    static int ticketsSoldCount = 0; //Assumed ticketSoldCount to 0

    public static void main(String[] args) {

        //Part A -Task 01
        int[] A = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] B = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] C = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] D = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,};

        outerLoop: //References: Stack overflow (Exiting from a nested loop)
        while (true) {
            System.out.println("Welcome to Plane Management application");//Prompting the welcome message
            // Part A-Task 02
            for (int i = 0; i < 40; i++) {System.out.print("*");}

            System.out.println("\n*             MENU OPTIONS             * ");

            for (int i = 0; i < 40; i++) {System.out.print("*");}
            //Display menu options
            System.out.println();
            System.out.println("     1) Buy a seat");
            System.out.println("     2) Cancel a seat");
            System.out.println("     3) Find first available seat");
            System.out.println("     4) Show seating plan");
            System.out.println("     5) Print tickets information and total sales");
            System.out.println("     6) Search ticket");
            System.out.println("     0) Quit");

            for (int i = 0; i < 40; i++) {System.out.print("*");}
//Letting user to select an option
            Scanner input = new Scanner(System.in);
            int option;
            try {
                do {
                    System.out.println();
                    System.out.print("Please select an option :");
                    option = input.nextInt();
                    if (option == 0) {
                        System.out.println("\nQuiting the program...!");
                        break outerLoop;
                    } else {
                        switch (option) {
                            case 1: buy_seat(A, B, C, D);
                                break;
                            case 2: cancel_seat(A, B, C, D);
                                break;
                            case 3: find_first_available(A, B, C, D);
                                break;
                            case 4: show_seating_plan(A, B, C, D);
                                break;
                            case 5: print_ticket_info();
                                break;
                            case 6:search_ticket();
                                break ;
                        }
                    }
                } while (true);
            }   catch (InputMismatchException e) {
                System.out.println("Incorrect input");
            }
        }
    }


    // Part A-Task 03
//Creating the method buy_seat
    public static void buy_seat(int[] A, int[] B, int[] C, int[] D) {
        Scanner scan = new Scanner(System.in);

        //instance creation for a person
        Person person = new Person();

        //Taking user inputs for personal details
        //Asking the user to enter name
        while (true) {
            System.out.print("Enter your name: ");
            String name = scan.next();
            if (name.matches("[a-zA-Z]+")) {
                person.setName(name);
                break;
            } else {
                System.out.println("Invalid name. Please enter again.");
            }
        }


        //Asking the user to enter surname
        while (true){
            System.out.print("Enter your surname :");
            String surname = scan.next();
            if (surname.matches("[a-zA-Z]+")) {
                person.setSurname(surname);
                break;
            }else {
                System.out.println("Invalid surname.Please enter again.");
            }
        }


        //Asking the user to enter an email
        while(true) {
            System.out.print("Enter your email address :");
            String email = scan.next();
            if (email.contains("@gmail.com")) {
                person.setEmail(email);
                break;
            } else {
                System.out.println("Invalid email.Please enter again.");
            }
        }

        //Instance creation for Ticket
        Ticket ticket = new Ticket();

        //Taking inputs from the user to enter seat details
        System.out.print("\nEnter the row letter (A,B,C,D):");
        String row = scan.next().toUpperCase();

        ticket.setRow(row);

        int[] selectedRow;

        while(true) {
            switch (row) {
                case "A":
                    selectedRow = A;
                    break;
                case "B":
                    selectedRow = B;
                    break;
                case "C":
                    selectedRow = C;
                    break;
                case "D":
                    selectedRow = D;
                    break;
                default:
                    System.out.println("Invalid row letter.");
                    System.out.print("Enter the row letter (A,B,C,D):");
                    row = scan.next().toUpperCase();
                    continue;
            }
            break;
        }
        ticket.setRow(row);

        //Asking the user to enter a seat number
        System.out.print("Enter seat number :");
        int seat_no;

        while (true) {
            seat_no = scan.nextInt();
            if (seat_no < 0 || seat_no > selectedRow.length) {//If user input is invalid
                System.out.println("Invalid seat number!Enter a valid seat number");
                System.out.print("Enter a seat number :");
            } else if (selectedRow[seat_no - 1] == 1) { //If seat is already occupied
                System.out.println("Seat already occupied! Enter a seat number again.");
                System.out.print("Enter a seat number :");
            } else {
                ticket.setSeat(seat_no);
                ticket.ticket_price();
                selectedRow[seat_no - 1] = 1;
                System.out.println("Seat reserved successfully !");

                //Combining person object with ticket object
                ticket.setPerson(person);

                //Part B - Task 12
                //Saving to files
                ticket.save(row,seat_no);

                //Part B - Task 09.b
                //Displaying ticket information
                System.out.println("\nTicket information :");
                ticket.Ticket_info();

                //Part B - Task 09.1
                //Adding ticket to new array
                ticketsSold[ticketsSoldCount] = ticket;
                ticketsSoldCount++;
                break;

            }

        }

    }



    //Part A - Task 04
//Creating the method cancel_seat
    public static void cancel_seat(int[] A, int[] B, int[] C, int[] D) {
        Scanner scan = new Scanner(System.in);
        // Asking the user to enter a row letter to cancel the seat
        System.out.print("Enter row letter to find the seat to cancel: ");
        String row = scan.next().toUpperCase();

        int[] selectedRow = null;
        while (true) {
            switch (row) {
                case "A":
                    selectedRow = A;
                    break;
                case "B":
                    selectedRow = B;
                    break;
                case "C":
                    selectedRow = C;
                    break;
                case "D":
                    selectedRow = D;
                    break;
                default:
                    System.out.print("Invalid row letter!\nEnter a valid letter (A, B, C, D): ");
                    row = scan.next().toUpperCase();
                    continue;
            }
            break;
        }

        //Asking the user to enter a seat number
        int seat_no;
        while (true) {
            System.out.print("Enter seat number: ");
            if (!scan.hasNextInt()) {
                System.out.print("Invalid input!\nPlease enter a valid integer seat number.");
                scan.next(); // Consume invalid input
                continue;
            }

            seat_no = scan.nextInt();
            if (seat_no < 1 || seat_no > selectedRow.length) {//If seat number is invalid
                System.out.println("Invalid seat number! Enter a valid seat number.");
                continue;
            } else if (selectedRow[seat_no - 1] == 0) {//If seat is already occupied
                System.out.println("Seat is already available! It cannot be cancelled.");
                return; // Return from method if seat is available
            } else {
                selectedRow[seat_no - 1] = 0;
                System.out.println("Seat cancellation is successful!");
                //Deleting the canceled file ( this is an additional method i used )


                // Part B - Task 09.2
                // Removing cancelled ticket from array
                for (int i = 0; i < ticketsSold.length; i++) {
                    if (ticketsSold[i] != null && ticketsSold[i].getRow().equals(row) && ticketsSold[i].getSeat() == seat_no) {
                        Ticket cancelledTicket = ticketsSold[i];//created a variable to hold the object ticket
                        ticketsSold[i] = null;
                        ticketsSoldCount--;
                        //This is an additional method I used
                        cancelledTicket.deleteFile();//calling the deletefile method to delete cancelled files
                        break;
                    }
                }
                return; // Return from method after successful cancellation
            }
        }
    }



    // part A-Task 05
// Creating the method find_first_available
    public static void find_first_available(int[]A,int[]B,int[]C,int[]D){
//Declaration and initializing two variables to -1 as array element counts from 0
        int row = -1 ;
        int seat = -1;
//Checking the availability of a seat
        for (int i = 0; i < A.length ; i++) {
            if (A[i]==0) {
                row = 0;
                seat = i;
                break;
            } else if (B[i]==0) {
                row=1;
                seat = i ;
                break;
            } else if (C[i]==0) {
                row= 2;
                seat = i;
                break;
            } else if (D[i]==0) {
                row= 3 ;
                seat = i ;
                break;
            }
        }
//Printing the first available seat
        if (row != -1){
            char row_letter = (char)('A' + row);
            System.out.println("First available seat : "+ row_letter +(seat+1));
        }else{
            System.out.println("There are no available seats");
        }
    }



    //part A -Task 06
//Creating the method show_seating_plan
    public static void show_seating_plan(int[]A,int[]B,int[]C,int[]D){
        System.out.println("Seating Plan");

        System.out.println();
        for (int seat : A) {
            if (seat==0){
                System.out.print("O");
            }else{
                System.out.print("X");
            }
        }

        System.out.println();
        for (int seat : B) {
            if (seat==0){
                System.out.print("O");
            }else{
                System.out.print("X");
            }
        }

        System.out.println();
        for (int seat : C) {
            if (seat==0){
                System.out.print("O");
            }else{
                System.out.print("X");
            }
        }

        System.out.println();
        for (int seat : D) {
            if (seat==0){
                System.out.print("O");
            }else{
                System.out.print("X");
            }
        }
        System.out.println();

    }


    //Part B - Task 10
    public static void print_ticket_info() {
        int ticketsSoldCount = 0; // Variable to count the number of tickets sold
        double total_sold = 0; // Variable to calculate total sales

        System.out.println("Tickets sold:");
        for (Ticket ticket : ticketsSold) {
            if (ticket != null) {
                System.out.println("Ticket: " + ticket.getRow() + (ticket.getSeat() ) + " Price: f" + ticket.getPrice());
                total_sold += ticket.getPrice();
                ticketsSoldCount++; // Incrementing tickets sold count
            }
        }
        System.out.println("Total number of tickets sold: " + ticketsSoldCount);
        System.out.println("Total sales: f" + total_sold);//Printing total sales
    }



    //Part B - Task 11
    public static void search_ticket() {
        //Asking the user to enter a row letter
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter row letter: ");
        String row = scan.next().toUpperCase();

        //Checking the validity of the row
        while (!(row.equals("A") || row.equals("B") || row.equals("C") || row.equals("D"))) {
            System.out.print("Invalid row!\nEnter A, B, C, or D: ");
            row = scan.next().toUpperCase();
        }

        //Asking the user to enter seat number
        int seatNo;
        do {
            System.out.print("Enter seat number: ");
            seatNo = scan.nextInt();
            if (seatNo < 1 || seatNo > 14) {//Checking the validity of seat number
                System.out.println("Invalid seat number. Please enter a valid seat number.");
            }
        } while (seatNo < 1 || seatNo > 14);

        System.out.println("Checking availability of the seat");

        boolean found = false;
        for (Ticket ticket : ticketsSold) {//Checking the availability of the seat
            if (ticket != null && row.equals(ticket.getRow()) && seatNo == ticket.getSeat()) {

                System.out.println("This seat has been booked already...");
                // Printing ticket row and seat
                System.out.println("\nRow: " + row);
                System.out.println("Seat: " + seatNo);
                System.out.println("Seat: " + row + seatNo);

                // Printing personal information
                System.out.println("Name: " + ticket.getPerson().getName());
                System.out.println("Surname: " + ticket.getPerson().getSurname());
                System.out.println("Email: " + ticket.getPerson().getEmail());

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("This seat is available!");
        }
    }

}

