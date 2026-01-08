public class Transform {
    float3 position;
    double yaw, pitch, roll;

    public Transform() {
        position = new float3(0, 0, 0);
        yaw = 0.0;
        pitch = 0.0;
        roll = 0.0;
    }

    public static float3 pointToWorld(float3 point, Transform transform) {
        double xx = point.x * (Math.cos(transform.yaw) * Math.cos(transform.pitch));
        double xy = point.y * (Math.cos(transform.yaw) * Math.sin(transform.pitch) * Math.sin(transform.roll) - Math.sin(transform.yaw) * Math.cos(transform.roll));
        double xz = point.z * (Math.cos(transform.yaw) * Math.sin(transform.pitch) * Math.cos(transform.roll) + Math.sin(transform.yaw) * Math.sin(transform.roll));
        double x = xx + xy + xz;

        double yx = point.x * (Math.sin(transform.yaw) * Math.cos(transform.pitch));
        double yy = point.y * (Math.sin(transform.yaw) * Math.sin(transform.pitch) * Math.sin(transform.roll) + Math.cos(transform.yaw) * Math.cos(transform.roll));
        double yz = point.z * (Math.sin(transform.yaw) * Math.sin(transform.pitch) * Math.cos(transform.roll) - Math.cos(transform.yaw) * Math.sin(transform.roll));
        double y = yx + yy + yz;

        double zx = point.x * -Math.sin(transform.pitch);
        double zy = point.y * (Math.cos(transform.pitch) * Math.sin(transform.roll));
        double zz = point.z * (Math.cos(transform.pitch) * Math.cos(transform.roll));

        double z = zx + zy + zz;

        float3 rotated = new float3(x, y, z);

        return rotated.add(transform.position);
    }
}
