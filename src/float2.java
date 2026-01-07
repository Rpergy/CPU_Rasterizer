public class float2 {
    double x, y;

    public float2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public void normalize() {
        double magnitude = this.magnitude();
        x = x / magnitude;
        y = y / magnitude;
    }

    public float2 add(float2 v) { return new float2(x + v.x, y + v.y); }
    public float2 sub(float2 v) { return new float2(x - v.x, y - v.y); }
    public float2 mul(double s) { return new float2(x * s, y * s); }
    public float2 div(double s) { return new float2(x / s, y / s); }

    public static double dot(float2 v1, float2 v2) {
        return v1.x * v2.x + v1.y * v2.y;
    }

    public static float2 perp(float2 v) {
        return new float2(v.y, -v.x);
    }
}
