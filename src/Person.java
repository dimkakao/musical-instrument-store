import java.io.Serial;
import java.io.Serializable;

public class Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String name;
    private final String surname;
    private final String fathersName;
    private final String phoneNumber;

    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getFathersName() {
        return fathersName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Person(String name, String surname, String fathersName, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.fathersName = fathersName;
        this.phoneNumber = phoneNumber;
    }

    public static Person createPerson(){
        System.out.println("Enter surname");
        String tmpSurname = MyUtilFunctions.enteringString();
        System.out.println("Enter name");
        String tmpName = MyUtilFunctions.enteringString();
        System.out.println("Enter middle name");
        String tmpFathersName = MyUtilFunctions.enteringString();
        String tmpPhoneNumber;
        while (true){
            //Ukrainian phone-code
            System.out.println("Enter the phone number in the format +380XX-XX-XX-XXX");
            tmpPhoneNumber = MyUtilFunctions.enteringString();
            if (tmpPhoneNumber.length()!=13)
                System.out.println("Phone number entered incorrectly");
            else if (!tmpPhoneNumber.contains("+380"))
                System.out.println("Phone number entered incorrectly");
            else break;
        }
        return new Person(tmpName,tmpSurname,tmpFathersName,tmpPhoneNumber);
    }

    public void personInfo(){
        System.out.println("\n\nSurname: "+this.surname);
        System.out.println("Name: "+this.name);
        System.out.println("Middle name: "+this.fathersName);
        System.out.println("Phone number: "+this.phoneNumber);
    }
}
