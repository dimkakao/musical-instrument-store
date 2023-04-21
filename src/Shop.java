import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Shop implements Serializable,ShopActions{

    @Serial
    private static final long serialVersionUID = 1L;

    private Seller seller;
    private double allBank = 10000;

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    private final ArrayList<MusicDisk> allItems;
    private final ArrayList<SoldItem> soldItems;
    private final ArrayList<RentItem> rentItems;
    private final ArrayList<Purchaser> purchasers;
    private final ArrayList<Seller> sellers;


    public ArrayList<Purchaser> getPurchasers() {
        return purchasers;
    }
    public ArrayList<Seller> getSellers() {
        return sellers;
    }
    public ArrayList<SoldItem> getSoldItems() {
        return soldItems;
    }
    public ArrayList<RentItem> getRentItems() {
        return rentItems;
    }
    public ArrayList<MusicDisk> getAllItems() {
        return allItems;
    }
    public double getAllBank() {
        return allBank;
    }
    public Seller getSeller() {
        return seller;
    }

    public Shop() {

        allItems = new ArrayList<>();
        soldItems = new ArrayList<>();
        rentItems = new ArrayList<>();
        purchasers = new ArrayList<>();
        sellers = new ArrayList<>();
    }



    @Override
    public void addItem(MusicDisk newItem) {
        allItems.add(newItem);
    }

    @Override
    public void addSeller(Seller newSeller) {
        sellers.add(newSeller);
    }

    @Override
    public void deleteItem(MusicDisk deleteItem) {
        allItems.remove(deleteItem);
    }

    @Override
    public Seller createSeller() {
        return Seller.createSeller(Person.createPerson());
    }

    @Override
    public void addPurchaser() {
        Purchaser newPurchaser = Purchaser.createPurchaser(Person.createPerson());
        purchasers.add(newPurchaser);
    }

    @Override
    public void changeSeller() {
        System.out.println("Sellers at work: ");
        boolean isSellers = false;
        for (Seller tmpSeller: sellers){
            tmpSeller.sellerInfo();
            isSellers = true;
        }
        if (!isSellers) {
            System.out.println("There is no one in the store. Hire someone to work.");
            return;
        }
        setSeller(Seller.findSeller(sellers));
        System.out.println("The change has been successfully completed!");
    }

    @Override
    public void sellItem(MusicDisk disk, SoldItem soldItem) {
        for (SoldItem tmpItem: soldItems){
            if (tmpItem.getItem().getId() == disk.getId()){
                allBank += disk.getCost() * soldItem.getQuantityOfSold();
                tmpItem.addQuantityOfDisks(soldItem.getQuantityOfSold());
                return;
            }
        }
        soldItems.add(soldItem);
        allBank += disk.getCost() * soldItem.getQuantityOfSold();
    }

    @Override
    public void rentItem(MusicDisk disk, RentItem rentItem) {
        for (RentItem tmpItem: rentItems){
            if (tmpItem.getItem().getId() == disk.getId()){
                allBank += disk.getCost() * rentItem.getQuantityOfSold();
                tmpItem.addQuantityOfDisks(rentItem.getQuantityOfSold());
                return;
            }
        }
        rentItems.add(rentItem);
        allBank += rentItem.getAdvance() * rentItem.getQuantityOfSold();
    }

    @Override
    public MusicDisk searchItemInAvailable(int id) {

        MusicDisk weNeed = null;
        for (MusicDisk item: allItems) {
            if (item.getId() == id){
                weNeed = item;
                break;
            }
        }
        return weNeed;
    }

    public MusicDisk searchItemInARent(int id) {

        MusicDisk weNeed = null;
        for (RentItem item: rentItems) {
            if (item.getItem().getId() == id){
                weNeed = item.getItem();
                break;
            }
        }
        return weNeed;
    }

    @Override
    public void returnItemToShop(MusicDisk diskToReturn) {

        for (RentItem item: rentItems) {
            if (item.getItem() == diskToReturn){
                rentItems.remove(item);
                allBank += item.getPriceForTerm()-item.getAdvance();
                return;
            }
        }
    }
}
