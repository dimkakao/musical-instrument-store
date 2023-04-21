import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class ActionWithItem implements Serializable, ActionItem {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Seller seller;
    private final Purchaser purchaser;
    private final MusicDisk item;
    private int quantityOfSold;

    public Seller getSeller() {
        return seller;
    }

    public Purchaser getPurchaser() {
        return purchaser;
    }
    public MusicDisk getItem() {
        return item;
    }
    public int getQuantityOfSold() {
        return quantityOfSold;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void addQuantityOfDisks(int quantity){
        this.quantityOfSold += quantity;
    }

    private final LocalDateTime dateTime;

    public ActionWithItem(Seller seller, Purchaser purchaser, MusicDisk disk, int quantityOfSold) {

        this.seller = seller;
        this.purchaser = purchaser;
        this.item = disk;
        this.quantityOfSold = quantityOfSold;
        dateTime = LocalDateTime.now();
    }

    public static ActionWithItem createAction(Seller seller, Purchaser purchaser, MusicDisk disk){

        System.out.println("Enter the number of disks you want to buy");
        int tmpQuantity = MyUtilFunctions.enteringIntWithConditions(1,100,"Sorry, we don't have that many disks");
        return new ActionWithItem(seller,purchaser,disk,tmpQuantity);
    }
}
