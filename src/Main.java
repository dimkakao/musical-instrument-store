import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {

        Shop myShop = null;
        System.out.println("1 - Read from file. 2 - Enter everything from the beginning");
        if (MyUtilFunctions.enteringInt() == 1)
            myShop = (Shop) ReadWriteObject.objectRead("Data.dat");

        if (null == myShop) myShop = new Shop();

        while (true){
            System.out.printf("\nBalance: %.2f ₴", myShop.getAllBank());
            System.out.println('\n'+"""
                    1 - Add a disk
                    2 - Sell a disk
                    3 - Remove a disk from availability
                    4 - Rent a disk
                    5 - Return the disk
                    6 - Find a disk
                    7 - Display information about disks
                    8 - View the list of sellers
                    9 - View the list of buyers
                    10 - Add a seller
                    11 - Change seller
                    0 - Exit""");

            int choiceAction = MyUtilFunctions.enteringInt();
            switch (choiceAction){
                case 1-> myShop.addItem(MusicDisk.createDisk());
                case 2 -> {
                    Seller currentSeller = myShop.getSeller();
                    if (currentSeller == null){
                        System.out.println("The seller is not in the database. Please add him");
                        Seller newSeller = myShop.createSeller();
                        myShop.addSeller(newSeller);
                        myShop.setSeller(newSeller);
                        break;
                    }
                    System.out.println("Enter the disk ID");
                    int id = MyUtilFunctions.enteringInt();
                    MusicDisk diskToSell = myShop.searchItemInAvailable(id);
                    if (null == diskToSell){
                        System.out.println("This disk was not found");
                        break;
                    }
                    System.out.println("1 - Find a purchaser\n2 - New purchaser");
                    Purchaser currentPurchaser = null;
                    switch (MyUtilFunctions.enteringIntWithConditions(1, 2, "")) {
                        case 1 -> {
                            currentPurchaser = Purchaser.findPurchaser(myShop.getPurchasers());
                            if (null == currentPurchaser);
                        }
                        case 2 -> {
                            currentPurchaser = Purchaser.createPurchaser(Person.createPerson());
                            myShop.getPurchasers().add(currentPurchaser);
                        }
                    }
                    if (null == currentPurchaser) break;
                    myShop.sellItem(diskToSell, SoldItem.sellItem(ActionWithItem.createAction(currentSeller,currentPurchaser,diskToSell)));
                }
                case 3 -> {
                    System.out.println("Enter the ID of the song you want to delete");
                    int id = MyUtilFunctions.enteringInt();
                    MusicDisk diskToDelete = myShop.searchItemInAvailable(id);
                    if (null == diskToDelete){
                        System.out.println("This disk was not found");
                        break;
                    }
                    myShop.deleteItem(diskToDelete);}
                case 4 -> {
                    Seller currentSeller = myShop.getSeller();
                    if (currentSeller == null){
                        System.out.println("Seller is absent. Select a seller");
                        break;
                    }
                    System.out.println("Enter the song ID");
                    int id = MyUtilFunctions.enteringInt();
                    MusicDisk diskToSell = myShop.searchItemInAvailable(id);
                    if (null == diskToSell){
                        System.out.println("This disk was not found");
                        break;
                    }
                    System.out.println("1 - Find a buyer\n2 - New customer");
                    Purchaser currentPurchaser = null;
                    switch (MyUtilFunctions.enteringIntWithConditions(1, 2, "")) {
                        case 1 -> {
                            System.out.println("Purchasers: \n");
                            myShop.getPurchasers().forEach(Purchaser::purchaserInfo);
                            currentPurchaser = Purchaser.findPurchaser(myShop.getPurchasers());
                        }
                        case 2 -> {
                            currentPurchaser = Purchaser.createPurchaser(Person.createPerson());
                            myShop.getPurchasers().add(currentPurchaser);
                        }
                    }
                    if (null == currentPurchaser) break;
                    myShop.rentItem(diskToSell,RentItem.rentItem(ActionWithItem.createAction(currentSeller,currentPurchaser,diskToSell)));}
                case 5 -> {
                    System.out.println("Enter the ID of the disk you want to return");
                    int id = MyUtilFunctions.enteringInt();
                    MusicDisk diskToReturn = myShop.searchItemInARent(id);
                    if (null == diskToReturn){
                        System.out.println("\"This disk was not found\"");
                        break;
                    }
                    myShop.returnItemToShop(diskToReturn);}
                case 6 -> findItems(myShop);
                case 7 -> {
                    System.out.println("""
                            1 - Displays available disks
                            2 - Displaying sold disks
                            3 - Displays disks for rent""");
                    int choicePrint = MyUtilFunctions.enteringInt();
                    switch (choicePrint) {
                        case 1 -> printInfoAboutDisks(MusicDisk::printDiskInfo, myShop.getAllItems());
                        case 2 -> printInfoAboutDisks(SoldItem::printSoldDiskInfo, myShop.getSoldItems());
                        case 3 -> printInfoAboutDisks(RentItem::printRentDiskInfo,myShop.getRentItems());
                    }}
                case 8  -> myShop.getSellers().forEach(Seller::sellerInfo);
                case 9  -> myShop.getPurchasers().forEach(Purchaser::purchaserInfo);
                case 10 -> myShop.addSeller(myShop.createSeller());
                case 11 -> myShop.changeSeller();
                case 0  -> {
                    System.out.println("Do you want to save it?\n1 - Yes. 2 - No.");
                    if (MyUtilFunctions.enteringInt() == 1)
                        ReadWriteObject.objectWrite(myShop,"Data.dat");
                    return;}
                default-> System.out.println("You made an incorrect selection. Please try again");
            }
        }
    }

    public static void printInfoAboutDisks(Consumer<ActionItem> consumer, ArrayList<? extends ActionItem> list) {
        int i = 0;
        for (ActionItem o : list) {
            System.out.println("\n");
            System.out.print((i + 1) + ") ");
            consumer.accept(o);
            i++;
        }
        if (i == 0) System.out.println("\nThe specified disks were not found");
    }

    public static void printInfoAboutDisksInSearch(Consumer<ActionItem> consumer, ArrayList<? extends ActionItem> list, Predicate<MusicDisk> predicate){
        int i = 0;
        for (ActionItem o : list) {
            if (predicate.test((MusicDisk) o)) {
                System.out.println("\n");
                System.out.print((i + 1) + ") ");
                consumer.accept(o);
                i++;
            }
        }
        if (i == 0) System.out.println("The specified disks were not found");
    }

    public static void findItems(Shop myShop){

        System.out.println("""
                1 - Search by song title or part of name.
                2 - Search by band name.
                3 - Search by genre.
                4 - Search by year of release.
                5 - Search by price segment.""");
        int choicePrint = MyUtilFunctions.enteringInt();
        switch (choicePrint) {
            case 1 -> {
                System.out.println("Enter the name or a fragment of the name");
                String tmpName = MyUtilFunctions.enteringString();
                printInfoAboutDisksInSearch(MusicDisk::printDiskInfo,myShop.getAllItems(),(MusicDisk x) ->
                        x.getName().toLowerCase().contains(tmpName.toLowerCase()));
            }
            case 2 -> {
                System.out.println("Enter the name of the band or artist");
                String tmpBand = MyUtilFunctions.enteringString();
                printInfoAboutDisksInSearch(MusicDisk::printDiskInfo,myShop.getAllItems(),(MusicDisk x) ->
                        x.getBand().toLowerCase().contains(tmpBand.toLowerCase()));
            }
            case 3 -> {
                System.out.println("Enter the genre");
                String tmpGenres = MyUtilFunctions.enteringString();
                printInfoAboutDisksInSearch(MusicDisk::printDiskInfo,myShop.getAllItems(),(MusicDisk x) ->
                        x.getGenres().toLowerCase().contains(tmpGenres.toLowerCase()));
            }
            case 4 -> {
                System.out.println("Enter the year of release");
                int tmpYear = MyUtilFunctions.enteringIntWithConditions(1800, LocalDateTime.now().getYear(),
                        "Enter the correct year of release");
                printInfoAboutDisksInSearch(MusicDisk::printDiskInfo,myShop.getAllItems(),(MusicDisk x) ->
                        x.getYearOfRelease() == tmpYear);
            }
            case 5 -> {
                System.out.println("Enter the maximum price you are looking for");
                double tmpHighPrice = MyUtilFunctions.enteringDoubleWithConditions(0, 100000);
                System.out.println("Enter the minimum price you are looking for");
                double tmpLowPrice = MyUtilFunctions.enteringDoubleWithConditions(0, tmpHighPrice);
                printInfoAboutDisksInSearch(MusicDisk::printDiskInfo,myShop.getAllItems(),(MusicDisk x) ->
                        x.getCost() <= tmpHighPrice && x.getCost() >= tmpLowPrice);
            }
            default -> System.out.println("Wrong choice. Try again");
        }
    }
}