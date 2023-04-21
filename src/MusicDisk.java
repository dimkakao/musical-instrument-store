import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class MusicDisk implements Serializable, ActionItem {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String name;
    private final String band;
    private final String genres;
    private final int yearOfRelease;
    private final int id;
    private final double cost;

    public String getName() {
        return name;
    }
    public String getBand() {
        return band;
    }
    public String getGenres() {
        return genres;
    }
    public int getYearOfRelease() {
        return yearOfRelease;
    }
    public double getCost() {
        return cost;
    }
    public int getId() {
        return id;
    }


    public MusicDisk(String name, String band, String genres, int yearOfRelease, double cost, int id) {
        this.name = name;
        this.band = band;
        this.genres = genres;
        this.yearOfRelease = yearOfRelease;
        this.cost = cost;
        this.id = id;
    }

    public static MusicDisk createDisk() {

        System.out.println("Enter the name of the disk");
        String tmpName = MyUtilFunctions.enteringString();
        System.out.println("Enter the name of the artist or group");
        String tmpBand = MyUtilFunctions.enteringString();
        System.out.println("Enter the genre(s) to which the disk belongs");
        String tmpGenres = MyUtilFunctions.enteringString();
        System.out.println("Enter the year of the disk release");
        int tmpYearOfRelease = MyUtilFunctions.enteringIntWithConditions(1800, LocalDateTime.now().getYear(), "Enter the correct year of release");
        System.out.println("Enter the cost of the disk");
        double tmpCost = MyUtilFunctions.enteringDoubleWithConditions(0.1, 15000);
        int tmpId = (int) (Math.random() * 10000);

        return new MusicDisk(tmpName, tmpBand, tmpGenres, tmpYearOfRelease, tmpCost, tmpId);
    }

    public void printDiskInfo() {

        System.out.println("ID: " + this.id);
        System.out.println("Name: " + this.name);
        System.out.println("Artist/Band: " + this.band);
        System.out.println("Genre(s): " + this.genres);
        System.out.println("Release Year: " + this.yearOfRelease);
        System.out.println("Cost: " + this.cost);

    }

    public static void printDiskInfo(Object o) {
        MusicDisk obj = (MusicDisk) o;
        System.out.println("ID: " + obj.id);
        System.out.println("Name: " + obj.name);
        System.out.println("Artist/Band: " + obj.band);
        System.out.println("Genre(s): " + obj.genres);
        System.out.println("Release Year: " + obj.yearOfRelease);
        System.out.println("Cost: " + obj.cost);
    }
}

