import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;

public class Toy {
    public static int[] ids;
    public static String[] names;
    public static int[] weights;
    private PriorityQueue<ToyData> queue;

    public Toy(String id1, String name1, String weight1,
               String id2, String name2, String weight2,
               String id3, String name3, String weight3) {
        ids = new int[]{Integer.parseInt(id1), Integer.parseInt(id2), Integer.parseInt(id3)};
        names = new String[]{name1, name2, name3};
        weights = new int[]{Integer.parseInt(weight1), Integer.parseInt(weight2), Integer.parseInt(weight3)};
        queue = new PriorityQueue<>();
        for (int i = 0; i < ids.length; i++) {
            queue.add(new ToyData(ids[i], names[i], weights[i]));
        }
    }

    public void getAll() {
        for (int i = 0; i < 10; i++) {
            if (queue.isEmpty()) {
                System.out.println("Queue is empty");
                break;
            }
            ToyData data = queue.poll();
            System.out.println(data.getId() + " " + data.getName() + " " + data.getWeight());
            writeDataToFile(data.getId(), data.getName(), data.getWeight());
        }
    }

    private void writeDataToFile(int id, String name, int weight) {
        try {
            FileWriter fileWriter = new FileWriter("output.txt", true);
            fileWriter.write(id + " " + name + " " + weight + "\n");
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Toy toy = new Toy("1", "Doll", "5",
                "2", "Bear", "10",
                "3", "Car", "7");
        toy.getAll();
    }
}

class ToyData implements Comparable<ToyData> {
    private int id;
    private String name;
    private int weight;

    public ToyData(int id, String name, int weight) {
        this.id = id;
        this.name = name;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(ToyData o) {
        return Integer.compare(weight, o.weight);
    }
}