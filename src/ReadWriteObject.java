import java.io.*;

public class ReadWriteObject {

    public static Object objectRead(String filename) {
        Object o;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filename))) {
            o = objectInputStream.readObject();
        } catch (IOException e) {
            System.out.println("Reading error!");
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return o;
    }

    public static void objectWrite(Object o, String filename) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            objectOutputStream.writeObject(o);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("Writing error!");
        }
    }
}