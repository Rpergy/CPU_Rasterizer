public class Face {
    float3 vertices;
    Color color;

    public Face(float3 vertices, Color color) {
        this.vertices = vertices;
        this.color = color;
    }

    public float3 getVertices() { return vertices; }
    public Color getColor() { return color; }
}
