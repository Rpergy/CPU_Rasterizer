public class Transform {
    float3 position;
    float3 rotation;

    public Transform() {
        position = new float3(0, 0, 0);
        rotation = new float3(0, 0, 0);
    }

    public static float3 pointToWorld(float3 point, Transform transform) {
        float3 iHat = new float3(Math.cos(transform.rotation.x), 0, Math.sin(transform.rotation.x));
        float3 jHat = new float3(-Math.sin(transform.rotation.x), 0, Math.cos(transform.rotation.x));

        float3 rotated = iHat.mul(point.x).add(jHat.mul(point.z)).add(new float3(0, point.y, 0));

        return rotated.add(transform.position);
    }
}
