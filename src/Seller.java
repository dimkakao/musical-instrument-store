import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Seller extends Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final int workAge;
    private final String position;

    public Seller(Person person, int workAge, String position) {
        super(person.getName(), person.getSurname(), person.getFathersName(), person.getPhoneNumber());
        this.workAge = workAge;
        this.position = position;
    }

    public static Seller createSeller(Person person){

        System.out.println("Enter number of years of experience");
        int tmpWorkAge = MyUtilFunctions.enteringIntWithConditions(0,15,"Наш салон працює 15 років. Перевірте Вашу інформацію");
        System.out.println("Enter the name of the position in which the person works");
        String tmpPosition = MyUtilFunctions.enteringString();

        return new Seller(person,tmpWorkAge,tmpPosition);
    }

    public static Seller findSeller(ArrayList<Seller> sellers) {

        System.out.println("\nEnter surname");
        String tmpSurname = MyUtilFunctions.enteringString();
        System.out.println("Enter name");
        String tmpName = MyUtilFunctions.enteringString();

        for (Seller seller : sellers) {
            if ((seller.getSurname().equals(tmpSurname)) && (seller.getName().equals(tmpName)))
                return seller;
        }
        System.out.println("There is no such seller in the database");
        return null;
    }

    public void sellerInfo(){
        this.personInfo();
        System.out.println("Position: " + this.position);
        System.out.println("Number of years of experience: "+ this.workAge);
    }
}
