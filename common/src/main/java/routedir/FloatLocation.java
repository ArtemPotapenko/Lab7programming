package routedir;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class FloatLocation extends Location{
    /**
     * Координата x
     */
    private Float x;
    /**
     * Координата y
     */
    private Float y;
    /**
     * Координата z
     */
    private int z;

    /**
     * Конструнтор для создания локации, используя все поля
     * @param x Координата x
     * @param y Координата z
     * @param z Координата z
     */
    public FloatLocation(Float x,Float y,int z){
        this.x=x;
        this.y=y;
        this.z=z;
    }
    public FloatLocation(){
        System.out.println("X:");
        x=ReadX();
        System.out.println("Y:");
        y=ReadX();
        z=ReadZ();
    }

    @Override
    public String getAll() {
        return getX()+","+getY()+","+getZ();
    }


    /**
     * Чтение x
     * @return x
     */
    private Float ReadX() {
        Float t;
        System.out.println("Введите координату - дробное число. -2^127≤x≤2^127-1");
        Scanner in = new Scanner(System.in);
        try{ t=Float.parseFloat(in.nextLine());}
        catch (InputMismatchException | NumberFormatException e){
            System.out.println("Ошибочный ввод");
            t=ReadX();
        }
        return t;}

    /**
     * Чтение z
     * @return z
     */
    private int ReadZ(){
        int t;
        System.out.println("Введите координату z - целое число. -2^31≤x≤2^31-1");
        Scanner in = new Scanner(System.in);
        try{ t=Integer.parseInt(in.nextLine());}
        catch (InputMismatchException|NumberFormatException e){
            System.out.println("Ошибочный ввод");
            t=ReadZ();}
        return t;
    }

    @Override
    public String toString() {
        return "FloatLocation{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    /**
     *
     * @return x
     */
    public Float getX() {
        return x;
    }

    /**
     *
     * @return y
     */
    public Float getY() {
        return y;
    }

    /**
     *
     * @return z
     */
    public int getZ() {
        return z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FloatLocation that = (FloatLocation) o;
        return z == that.z && Objects.equals(x, that.x) && Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
