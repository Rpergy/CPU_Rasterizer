public class Color {
    double[] cols;

    public Color(double r, double g, double b) {
        cols = new double[3];
        cols[0] = b;
        cols[1] = g;
        cols[2] = r;
    }

    public Color(double[] in) {
        cols = in;
    }

    public String toString() {
        return "R: " + cols[2] + " G: " + cols[1] + " B: " + cols[0];
    }

    public double[] toArray() {
        return cols;
    }
}
