import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//Part B Task 08
public class Ticket {

    String row;
    int seat;
    double price;
    Person person;


    //Constructors
    public Ticket(String row, int seat, double price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    //Getters and setters
    public Ticket() {

    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    //Creating a method to calculate ticket prices
    public void ticket_price() {
        if (seat > 0 && seat <= 5) {
            price = 200.00;
        } else if (seat > 5 && seat <= 9) {
            price = 150.00;
        } else if (seat > 9 && seat <= 14) {
            price = 180.00;
        } else {
            System.out.println("Invalid seat number");
        }

    }

    //Method to display ticket information
    public void Ticket_info() {
        System.out.println("Row letter :" + row);
        System.out.println("Seat number:" + seat);
        System.out.println("Price:f" + price);
        if (person != null) {
            person.personal_info();
        } else {
            System.out.println("No personal information found !");
        }
    }


    //Part B - Task 12
    //Method to save to files
    public void save(String row, int seat) {
        // Checking if the seat selection is valid or not
        if ((seat > 0 && seat <= 14) && (row.length() == 1 && (row.charAt(0) >= 'A' && row.charAt(0) <= 'D'))) {
            String fileName = row + seat + ".txt"; // Giving a file name

            try {
                FileWriter fileWriter = new FileWriter(fileName);
                {
                    fileWriter.write("Ticket Information:\n");//printing ticket information
                    fileWriter.write("Row:" + row + "\n");                              //printing row
                    fileWriter.write("Seat:" + seat + "\n");                            //printing seat
                    fileWriter.write("Ticket price: $" + getPrice() + "\n");            //printing price
                    fileWriter.write("\nPassenger Information\n");                      //printing personal information
                    fileWriter.write("Passenger name:" + person.getName() + "\n");      //printing name
                    fileWriter.write("Passenger surname:" + person.getSurname() + "\n");//printing surname
                    fileWriter.write("Passenger email:" + person.getEmail() + "\n");    //printing email

                    System.out.println("Ticket information is saved to file: " + fileName);
                    fileWriter.close();
                }
            } catch (IOException e) {
                System.out.println("An error occurred when saving details to file: " + e.getMessage());
            }
        } else {
            System.out.println("Invalid seat or row number. File not saved.");
        }
    }


    //Additional method to delete the saved files if it is cancelled by user
    public void deleteFile() {
        String fileName = row + seat + ".txt"; // assumption of file name
        File file = new File(fileName);

        if (file.exists()) {
            if (file.delete()) {
                System.out.println("File " + fileName + " deleted successfully.");
            } else {
                System.out.println("Failed to delete file " + fileName + ".");
            }
        } else {
            System.out.println("File " + fileName + " does not exist.");
        }
    }
}
