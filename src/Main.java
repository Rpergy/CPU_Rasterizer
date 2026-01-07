import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.core.CvType;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.load("C:/opt/opencv/build/java/x64/opencv_java4120.dll");

        float2 a = new float2(100, 100);
        float2 b = new float2(500, 100);
        float2 c = new float2(300, 200);

        float2 augment = new float2(10, 0);

        Mesh cube = new Mesh("C:/Users/rperg/IdeaProjects/CPU Rasterizer/src/meshes/cube.obj");

        while (true) {
            Frame frame = new Frame(1080, 700);

            frame.renderMesh(cube);

            HighGui.namedWindow("Window", HighGui.WINDOW_AUTOSIZE);
            HighGui.imshow("Window", frame.getMat());

            HighGui.waitKey(1);

            if (HighGui.n_closed_windows == 1)
                break;
        }

        System.out.println("Exited");
        HighGui.destroyAllWindows();
    }
}
