public interface ShopActions {

     void addItem(MusicDisk newItem);

     void addSeller(Seller newSeller);
     void deleteItem(MusicDisk deleteItem);
     Seller createSeller();
     void addPurchaser();
     void changeSeller();
     void sellItem(MusicDisk disk, SoldItem soldItem);
     void rentItem(MusicDisk disk, RentItem rentItem);
     MusicDisk searchItemInAvailable(int id);
     void returnItemToShop(MusicDisk diskToReturn);

}
