import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class Frame {
    private Mat frame;

    public Frame(int width, int height) {
         frame = new Mat(height, width, CvType.CV_8UC3);
    }

    public void set(int x, int y, Color newPixel) {
        frame.put(y, x, newPixel.toArray());
    }

    public Color get(int x, int y) {
        return new Color(frame.get(y, x));
    }

    public Mat getMat() { return frame; }

    public int width() { return frame.cols(); }
    public int height() { return frame.rows(); }

    public void renderMesh(Mesh m) {
        for (int i = 0; i < m.faces.size(); i++) {
            float3 face = m.faces.get(i);
            renderTriangle(m.vertices.get((int)face.x-1).tofloat2(), m.vertices.get((int)face.y-1).tofloat2(), m.vertices.get((int)face.z-1).tofloat2());
        }
    }

    public void renderTriangle(float2 a, float2 b, float2 c) {
        int xMin = (int)Math.min(Math.min(a.x, b.x), c.x);
        int xMax = (int)Math.max(Math.max(a.x, b.x), c.x);
        int yMin = (int)Math.min(Math.min(a.y, b.y), c.y);
        int yMax = (int)Math.max(Math.max(a.y, b.y), c.y);

        for (int x = xMin; x < xMax; x++) {
            for (int y = yMin; y < yMax; y++) {
                if (pointInsideTriangle(a, b, c, new float2(x, y)))
                    set(x, y, new Color(255, 255, 255));
            }
        }
    }

    public static boolean pointInsideTriangle(float2 a, float2 b, float2 c, float2 p) {
        float2 AtoB = b.sub(a);
        float2 BtoC = c.sub(b);
        float2 CtoA = a.sub(c);

        float2 AtoP = p.sub(a);
        float2 BtoP = p.sub(b);
        float2 CtoP = p.sub(c);

        double line1 = float2.dot(AtoB, float2.perp(AtoP));
        double line2 = float2.dot(BtoC, float2.perp(BtoP));
        double line3 = float2.dot(CtoA, float2.perp(CtoP));

        return line1 >= 0 && line2 >= 0 && line3 >= 0;
    }
}
