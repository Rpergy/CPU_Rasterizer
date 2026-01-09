public class float3 {
    double x, y, z;

    public float3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public void normalize() {
        double magnitude = this.magnitude();
        x = x / magnitude;
        y = y / magnitude;
        z = z / magnitude;
    }

    public float3 add(float3 v) { return new float3(x + v.x, y + v.y, z + v.z); }
    public float3 sub(float3 v) { return new float3(x - v.x, y - v.y, z - v.z); }
    public float3 mul(double s) { return new float3(x * s, y * s, z * s); }
    public float3 div(double s) { return new float3(x / s, y / s, z / s); }

    public static double dot(float3 v1, float3 v2) {
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
    }

    public static float3 cross(float3 v1, float3 v2) {
        double x = v1.y * v2.z - v1.z * v2.y;
        double y = v1.z * v2.x - v1.x * v2.z;
        double z = v1.x * v2.y - v1.y * v2.x;
        return new float3(x, y, z);
    }

    public float3 worldToScreen(Transform transform, double fov) {
        float3 worldPoint = Transform.pointToWorld(this, transform);

        float2 resolution = new float2(1080, 700);

        double screenHeight_world = Math.tan(Math.toRadians(fov) / 2) * 2;
        double pixelsPerWorldUnit = resolution.y / screenHeight_world / worldPoint.z;

        float2 pixelOffset = new float2(worldPoint.x, worldPoint.y).mul(pixelsPerWorldUnit);
        float2 vertexScreen = resolution.div(2).add(pixelOffset);
        float3 vertexWorld = new float3(vertexScreen.x, vertexScreen.y, worldPoint.z);
        return vertexWorld;
    }

    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

    public float2 tofloat2() {
        return new float2(x, y);
    }
}
