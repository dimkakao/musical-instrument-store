import java.io.Serial;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;

public class SoldItem extends ActionWithItem {
    @Serial
    private static final long serialVersionUID = 1L;
    public SoldItem(Seller seller, Purchaser purchaser, MusicDisk item, int quantityOfSold) {
        super(seller, purchaser, item, quantityOfSold);
    }

    public static SoldItem sellItem(ActionWithItem actionWithItem){
        return new SoldItem(actionWithItem.getSeller(), actionWithItem.getPurchaser(), actionWithItem.getItem(), actionWithItem.getQuantityOfSold());
    }

    public static void printSoldDiskInfo(Object o) {
        SoldItem obj = (SoldItem) o;
        MusicDisk disk = obj.getItem();
        disk.printDiskInfo();
        System.out.println("Quantity of sold disks: " + obj.getQuantityOfSold());
        System.out.println("Seller: "+ obj.getSeller().getSurname() +" "+ obj.getSeller().getName() +" "+ obj.getSeller().getFathersName());
        System.out.println("Purchaser: "+ obj.getPurchaser().getSurname() +" "+ obj.getPurchaser().getName() +" "+ obj.getPurchaser().getFathersName());
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String soldDate = obj.getDateTime().format(format);
        System.out.println("Date of sale: " + soldDate);
    }

    public void printSoldDiskInfo(){

        MusicDisk disk = super.getItem();
        disk.printDiskInfo();
        System.out.println("Quantity of sold disks: " + getQuantityOfSold());
        System.out.println("Seller: "+ getSeller().getSurname() +" "+ getSeller().getName() +" "+ getSeller().getFathersName());
        System.out.println("Purchaser: "+ getPurchaser().getSurname() +" "+ getPurchaser().getName() +" "+ getPurchaser().getFathersName());
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String soldDate = super.getDateTime().format(format);
        System.out.println("Date of sale: " + soldDate);
    }
}
