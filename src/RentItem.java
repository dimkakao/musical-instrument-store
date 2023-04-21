import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RentItem extends ActionWithItem {
    @Serial
    private static final long serialVersionUID = 1L;

    private final LocalDateTime lastRentDate;
    private final double advance;
    private final double priceForTerm;

    public RentItem(Seller seller, Purchaser purchaser, MusicDisk item, int quantityOfSold, double advance, double priceForTerm, LocalDateTime rentDate) {

        super(seller, purchaser, item, quantityOfSold);
        this.advance = advance;
        this.priceForTerm = priceForTerm;
        this.lastRentDate = rentDate.plusDays(60);
    }

    public static RentItem rentItem(ActionWithItem actionWithItem){

        double tmpAdvance = (actionWithItem.getItem().getCost())/2;
        double tmpPriceForTerm = tmpAdvance * 1.5;
        return new RentItem(actionWithItem.getSeller(), actionWithItem.getPurchaser(), actionWithItem.getItem(),
                actionWithItem.getQuantityOfSold(), tmpAdvance, tmpPriceForTerm, actionWithItem.getDateTime());
    }

    public static void printRentDiskInfo(Object o){

        RentItem obj = (RentItem)o;
        MusicDisk disk = obj.getItem();
        disk.printDiskInfo();
        System.out.println("Seller: "+ obj.getSeller().getSurname() +" "+ obj.getSeller().getName() +" "+ obj.getSeller().getFathersName());
        System.out.println("Purchaser: "+ obj.getPurchaser().getSurname() +" "+ obj.getPurchaser().getName() +" "+ obj.getPurchaser().getFathersName());
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String rentFirstDate = obj.getDateTime().format(format);
        String rentLastDate = obj.lastRentDate.format(format);
        System.out.println("Date of sale: " + rentFirstDate);
        System.out.println("The final date of the rental: " + rentLastDate);
    }

    public void printRentDiskInfo(){

        MusicDisk disk = super.getItem();
        disk.printDiskInfo();
        System.out.println("Seller: "+ getSeller().getSurname() +" "+ getSeller().getName() +" "+ getSeller().getFathersName());
        System.out.println("Purchaser: "+ getPurchaser().getSurname() +" "+ getPurchaser().getName() +" "+ getPurchaser().getFathersName());
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String rentFirstDate = super.getDateTime().format(format);
        String rentLastDate = lastRentDate.format(format);
        System.out.println("Date of sale: " + rentFirstDate);
        System.out.println("The final date of the rental: " + rentLastDate);
    }

    public double getPriceForTerm() {
        return priceForTerm;
    }
    public double getAdvance() {
        return advance;
    }
}
