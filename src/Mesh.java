import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Mesh {
    public ArrayList<float3> vertices;
    public ArrayList<float3> faces;

    public Mesh(String filepath) {
        File file = new File(filepath);

        vertices = new ArrayList();
        faces = new ArrayList();

        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] splitData = data.split(" ");

                if (splitData[0].equals("v")) {
                    float3 v = new float3(Double.parseDouble(splitData[1]), Double.parseDouble(splitData[2]), Double.parseDouble(splitData[3]));
                    vertices.add(v);
                }
                else if (splitData[0].equals("f")) {
                    int firstIndex = Integer.parseInt(splitData[1].split("/")[0]);
                    for (int i = 2; i < splitData.length - 1; i++) {
                        int secondIndex = Integer.parseInt(splitData[i].split("/")[0]);
                        int thirdIndex = Integer.parseInt(splitData[i+1].split("/")[0]);
                        float3 face = new float3(firstIndex, secondIndex, thirdIndex);
                        faces.add(face);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
