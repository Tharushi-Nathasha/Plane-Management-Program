//Part B Task 07
public class Person {

    private   String name ;
    private String surname ;
    private String email;

    //Constructors
    public Person(String name, String surname, String email) {
        this.name=name;
        this.surname = surname;
        this.email=email;
    }
    //Getters and setters
    public Person() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //Method to print personal information
    public void personal_info (){
        System.out.println("\nPersonal information :");
        System.out.println("Name :" + name);
        System.out.println("Surname :" + surname);
        System.out.println("Email :"+ email);
    }

}
