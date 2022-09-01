package routedir;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 */
public class Coordinates implements Serializable {
    /**
     * Координата x
     */
    private long x;
    /**
     * Координата y
     */
    private Double y;
    public Coordinates(){
        x=ReadX();
        y=ReadY();
    }

    /**
     *
     * @param x Координата x
     * @param y Координата y
     */
    public Coordinates(long x,Double y){
        this.x=x;
        this.y=y;
    }

    /**
     * @return Чтение x из промежутка [-2^63;2^63-1] .
     */
    private long ReadX() {
        long t;
        System.out.println("Введите координату x - Целое число. -2^63≤x≤2^63-1");
        Scanner in = new Scanner(System.in);
        try{ t=Long.parseLong(in.nextLine());}
        catch (InputMismatchException|NumberFormatException e){
            System.out.println("Ошибочный ввод");
          t=ReadX();
        }
        return t;
    }

    /**
     * @return Чтение y из промежутка [-2^1024;2^1024-1], 100 знаков после запятой
     */
    private double ReadY(){
        double t;
        System.out.println("Введите координату y - десятичную дробь. -2^1024≤2^1024-1.  ");
        Scanner in = new Scanner(System.in);
        try{ t=Double.parseDouble(in.nextLine());}
        catch (InputMismatchException|NumberFormatException e){
            System.out.println("Ошибочный ввод");
            t=ReadY();
        }
        return t;
    }

    /**
     *
     * @return y
     */
    public Double getY() {
        return y;
    }

    /**
     *
     * @return x
     */
    public long getX() {
        return x;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
