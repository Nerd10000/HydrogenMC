package dragon.me.HydrogenAPI.world.utils;

/**
 * A class for creating Vectors
 * 
 * Why is this different from location? <br>
 * </br>
 * 
 * This is was designed for math while location is generally better to use
 * except for Math
 * as it does not provide functions for advanced math.
 * 
 * <br</br>
 * This is mutable there is no function for making immutable. Maybe make it in
 * the future.
 * 
 * @author Dragon
 */
public class Vector {
    private double x, y, z;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void add(Vector otherVector) {
        this.x += otherVector.getX();
        this.y += otherVector.getY();
        this.z += otherVector.getZ();
    }

    public void add(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;
    }

    public void subtract(Vector otherVector) {
        this.x -= otherVector.getX();
        this.y -= otherVector.getY();
        this.z -= otherVector.getZ();
    }

    public void subtract(double x, double y, double z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
    }

    public void divide(Vector otherVector) {
        this.x /= otherVector.getX();
        this.y /= otherVector.getY();
        this.z /= otherVector.getZ();
    }

    public void divide(double x, double y, double z) {
        this.x /= x;
        this.y /= y;
        this.z /= z;
    }

    public void multiply(Vector otherVector) {
        this.x *= otherVector.getX();
        this.y *= otherVector.getY();
        this.z *= otherVector.getZ();
    }

    public void multiply(double x, double y, double z) {
        this.x *= x;
        this.y *= y;
        this.z *= z;
    }

    public void module(Vector otherVector) {
        this.x %= otherVector.getX();
        this.y %= otherVector.getY();
        this.z %= otherVector.getZ();
    }

    public void module(double x, double y, double z) {
        this.x %= x;
        this.y %= y;
        this.z %= z;
    }

    public void power(double x) {
        this.x = Math.pow(this.x, x);
        this.y = Math.pow(this.y, x);
        this.z = Math.pow(this.z, x);
    }

    public void power(double x, double y, double z) {
        this.x = Math.pow(this.x, x);
        this.y = Math.pow(this.y, y);
        this.z = Math.pow(this.z, z);
    }

    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public void normalize() {
        double len = length();
        this.x = len == 0 ? 0 : x / len;
        this.y = len == 0 ? 0 : y / len;
        this.z = len == 0 ? 0 : z / len;

    }

    public String toString() {
        return "Vector[x: " + x + ",y: " + y + ",z: " + z + "]";
    }

    public double getX() {

        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

}
