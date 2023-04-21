import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Purchaser extends Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String email;
    private final String homeAddress;

    public Purchaser(String name, String surname, String fathersName, String phoneNumber, String email, String homeAddress) {
        super(name, surname, fathersName, phoneNumber);
        this.email = email;
        this.homeAddress = homeAddress;
    }

    public Purchaser(Person person, String email, String homeAddress) {
        super(person.getName(), person.getSurname(), person.getFathersName(), person.getPhoneNumber());
        this.homeAddress = homeAddress;
        this.email = email;
    }

    public static Purchaser createPurchaser(Person person) {

        String tmpEmail;
        while (true){
            System.out.println("Enter e-mail");
            tmpEmail = MyUtilFunctions.enteringString();
            if (!tmpEmail.contains("@"))
                System.out.println("E-mail is entered incorrectly");
            else break;
        }
        System.out.println("Enter your home address in the format: City, street, house number, apartment number");
        String tmpHomeAddress = MyUtilFunctions.enteringString();
        return new Purchaser(person, tmpEmail, tmpHomeAddress);
    }

    public static Purchaser findPurchaser(ArrayList<Purchaser> purchasers) {

        System.out.println("Enter surname");
        String tmpSurname = MyUtilFunctions.enteringString();
        System.out.println("Enter name");
        String tmpName = MyUtilFunctions.enteringString();

        for (Purchaser p : purchasers) {
            if ((p.getSurname().equals(tmpSurname)) && (p.getName().equals(tmpName)))
                return p;
        }
        System.out.println("There is no such customer in the database");
        return null;
    }

    public void purchaserInfo(){
        this.personInfo();
        System.out.println("E-mail: " + this.email);
        System.out.println("Address "+ this.homeAddress);
    }
}
