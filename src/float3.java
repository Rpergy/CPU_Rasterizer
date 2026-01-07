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

    public static double dot(float3 v1, float3 v2) {
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
    }

    public float2 worldToScreen() {
        double screenX = x * 200 + 540;
        double screenY = y * 200 + 350;
        return new float2(screenX, screenY);
    }

    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

    public float2 tofloat2() {
        return new float2(x, y);
    }
}
