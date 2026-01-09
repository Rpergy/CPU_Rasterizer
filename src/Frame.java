import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class Frame {
    private Mat frame;
    private Mat depthBuffer;

    public Frame(int width, int height) {
         frame = new Mat(height, width, CvType.CV_8UC3);
         depthBuffer = new Mat(height, width, CvType.CV_64FC1);
    }

    public void setDepthBuffer(int x, int y, double depth) {
        depthBuffer.put(y, x, depth);
    }

    public void setColor(int x, int y, Color newPixel) {
        frame.put(y, x, newPixel.toArray());
    }

    public Color get(int x, int y) {
        return new Color(frame.get(y, x));
    }

    public Mat getMat() { return frame; }
    public Mat getDepthBuffer() { return depthBuffer; }

    public int width() { return frame.cols(); }
    public int height() { return frame.rows(); }

    public void renderMesh(Mesh mesh, Transform transform, double fov) {
        for (int i = 0; i < mesh.faces.size(); i++) {
            float3 face = mesh.faces.get(i).getVertices();
            float3 p1 = mesh.vertices.get((int)face.x-1).worldToScreen(transform, fov);
            float3 p2 = mesh.vertices.get((int)face.y-1).worldToScreen(transform, fov);
            float3 p3 = mesh.vertices.get((int)face.z-1).worldToScreen(transform, fov);
            renderTriangle(p1, p2, p3, mesh.faces.get(i).getColor());
        }
    }

    public void renderTriangle(float3 a, float3 b, float3 c, Color col) {
        int xMin = (int)Math.min(Math.min(a.x, b.x), c.x);
        int xMax = (int)Math.max(Math.max(a.x, b.x), c.x);
        int yMin = (int)Math.min(Math.min(a.y, b.y), c.y);
        int yMax = (int)Math.max(Math.max(a.y, b.y), c.y);

        if (xMin < 0 || xMax >= frame.cols() || yMin < 0 || yMax >= frame.rows()) return;

        for (int x = xMin; x < xMax; x++) {
            for (int y = yMin; y < yMax; y++) {
                if (pointInsideTriangle(a.tofloat2(), b.tofloat2(), c.tofloat2(), new float2(x, y))) {
                    double depth = interpolateDepth(a, b, c, new float2(x, y));
//                    depth = 182.1*(depth-3.5);

                    if (depth < depthBuffer.get(y, x)[0] || depthBuffer.get(y, x)[0] == 0.0) {
                        setColor(x, y, col);
                        setDepthBuffer(x, y, depth);
                    }
                }
            }
        }
    }

    public static double interpolateDepth(float3 A, float3 B, float3 C, float2 p) {
        float2 a = new float2(A.x, A.y);
        float2 b = new float2(B.x, B.y);
        float2 c = new float2(C.x, C.y);

        float2 AB = b.sub(a);
        float2 BC = c.sub(b);
        float2 CA = a.sub(c);

        float2 AP = p.sub(a);
        float2 BP = p.sub(b);
        float2 CP = p.sub(c);

        double aPercent = float2.cross(BC, BP).magnitude() / float2.cross(BC, AB).magnitude();
        double bPercent = float2.cross(CA, CP).magnitude() / float2.cross(CA, BC).magnitude();
        double cPercent = float2.cross(AB, AP).magnitude() / float2.cross(AB, CA).magnitude();

        return aPercent * A.z + bPercent * B.z + cPercent * C.z;
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

        boolean l1 = line1 >= 0;
        boolean l2 = line2 >= 0;
        boolean l3 = line3 >= 0;

        return l1 == false && l1 == l2 && l2 == l3;
    }
}
