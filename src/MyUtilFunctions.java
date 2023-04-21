import java.util.Scanner;

public class MyUtilFunctions {

    public static int enteringInt (){
        Scanner scanner = new Scanner(System.in);
        int tmp;
        while (true){
            if (scanner.hasNextInt()){
                tmp = scanner.nextInt();
                break;
            }
            else System.out.println("Enter correct number");
            scanner.nextLine();
        }
        return tmp;
    }

    public static int enteringIntWithConditions(int low_limit, int high_limit, String message){
        int number;
        while(true) {
            number = enteringInt();
            if(number >= low_limit && number <= high_limit ) break;
            else System.out.println("\n" + message);
        }
        return number;
    }

    public static String enteringString (){
        Scanner scanner = new Scanner(System.in);
        String tmp;
        tmp = scanner.nextLine();
        return tmp;
    }

    public static double enteringDouble (){
        Scanner scanner = new Scanner(System.in);
        double tmp;
        while (true){
            if (scanner.hasNextDouble()){
                tmp = scanner.nextDouble();
                break;
            }
            else System.out.println("Enter correct number");
            scanner.nextLine();
        }
        return tmp;
    }

    public static double enteringDoubleWithConditions (double lowLimit, double highLimit){

        double tmp = enteringDouble();
        while (tmp < lowLimit || tmp > highLimit){
            System.out.println("Phone number entered incorrectly");
            tmp = enteringDouble();
        }
        return tmp;
    }
}
