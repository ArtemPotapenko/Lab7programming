package routedir;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Целочисленная локация
 *
 * @version 1.0
 */
public class IntLocation extends Location {
    /**
     * Координата x
     */
    private Integer x;
    /**
     * Координата y
     */
    private long y;
    /**
     * Имя
     */
    private String name;

    /**
     * @param x  координата x
     * @param y  координата y
     * @param name имя
     * Конструнтор для создания локации, используя все поля
     */
    public IntLocation(Integer x, long y, String name) {
        this.x = x;
        this.name = name;
        this.y = y;
    }

    public IntLocation() {
        x = ReadX();
        y = ReadY();
        name = ReadName();
    }

    /**
     * @return Координата x
     */
    public Integer ReadX() {
        int t;
        System.out.println("Введите координату x - целое число. -2^31≤x≤2^31-1");
        Scanner in = new Scanner(System.in);
        try {
            t = Integer.parseInt(in.nextLine());
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Ошибочный ввод");
            t = ReadX();
        }
        return (Integer) t;
    }

    /**
     * @return Координата y
     */
    public long ReadY() {
        long t;
        System.out.println("Введите координату y - Целое число. -2^63≤x≤2^63-1");
        Scanner in = new Scanner(System.in);
        try {
            t = Long.parseLong(in.nextLine());
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Ошибочный ввод");
            t = ReadY();
        }
        return t;
    }

    /**
     * @return name
     */
    public String ReadName() {
        String s;
        System.out.println("Введите непустое имя диной не больше 350");
        Scanner in = new Scanner(System.in);
        s = in.nextLine();
        if (s.length() > 350 || s.trim().length() == 0 || ",".contains(s)) {
            System.out.println("Ошибочный ввод");
            s = ReadName();
        }
        return s;
    }

    @Override
    public String getAll() {
        return getX() + "," + getY() + "," + getName();
    }

    @Override
    public String toString() {
        return "IntLocation{" +
                "x=" + x +
                ", y=" + y +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * @return Имя
     */
    public String getName() {
        return name;
    }

    /**
     * @return x
     */
    public Integer getX() {
        return x;
    }


    /**
     * @return y
     */
    public long getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntLocation that = (IntLocation) o;
        return y == that.y && Objects.equals(x, that.x) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, name);
    }
}
