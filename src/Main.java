import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.core.CvType;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.load("C:/opt/opencv/build/java/x64/opencv_java4120.dll");

        Mesh cube = new Mesh("C:/Users/rperg/IdeaProjects/CPU Rasterizer/src/meshes/monkey.obj");

        Transform transform = new Transform();
        transform.position.z = 5;

        while (true) {
            long start = System.nanoTime();

            Frame frame = new Frame(1080, 700);
            frame.renderMesh(cube, transform, 60.0);

            transform.rotation.x += Math.toRadians(1);

            HighGui.imshow("Window", frame.getMat());

            int input = HighGui.waitKey(1);

            if (input == 83) {
                transform.position.z -= 0.1;
            }
            else if (input == 87) {
                transform.position.z += 0.1;
            }
            else if (input == 27) {
                break;
            }

            long end = System.nanoTime();
            long duration = java.util.concurrent.TimeUnit.NANOSECONDS.toMillis(end - start);
            System.out.println((1.0 / duration * 1000) + "ms");
        }

        System.out.println("Exited");
        HighGui.destroyAllWindows();
    }
}
