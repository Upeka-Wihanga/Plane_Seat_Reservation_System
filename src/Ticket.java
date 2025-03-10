import java.io.FileWriter;
import java.io.IOException;
public class Ticket {
    private char row;
    private int seat;
    private double price;
    private Person person;

    public Ticket(char row , int seat , double price , Person person){
        this.row= row;
        this.seat= seat;
        this.price= price;
        this.person= person;
    }
    public char getRow(){
        return row;
    }
    public int getSeat(){
        return seat;
    }
    public double getPrice(){
        return price;
    }
    public Person getPerson(){
        return person;
    }
    public void setRow(char row){
        this.row = row;
    }
    public void setSeat(int seat){
        this.seat = seat;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public void setPerson(Person person){
        this.person = person;
    }

    public void print_ticket_info(){
        System.out.println("Your ticket for seat :- " + row + ":" + seat );
        System.out.println("Price :- " + price );
        person.print_person_info();
    }

    public void save(){
        String FileName = row + String.valueOf(seat) + ".text";
        try(FileWriter writer = new FileWriter(FileName)){
            writer.write("Ticket Information : \n");
            writer.write("Row: " + row + "\n");
            writer.write("Seat: " + seat +"\n");
            writer.write("Price: " + price +"\n");
            writer.write("Name: " + person.getName() +"\n");
            writer.write("Surname: " + person.getSurname() +"\n");
            writer.write("Email: " + person.getEmail() +"\n");
        }
        catch(IOException e){
            System.out.println("There is a problem while saving ticket details to " + FileName);
            e.printStackTrace();
        }
    }
}
