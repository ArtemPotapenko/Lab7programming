package request;

import routedir.Coordinates;
import routedir.FloatLocation;
import routedir.IntLocation;
import routedir.Location;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Класс элементов коллекции.
 * @version 1.0
 */
public class RequestRoute extends Request implements Serializable {



    /**
     * Имя элемента
     */
    private String name;
    /**
     * Координаты пути
     */
    private Coordinates coordinates;
    /**
     * Дата создания маршрута.
     */

    private Location from;
    /**
     * Локация приезда.
     */
    private Location to;
    /**
     * Пройденная дистанция.
     */
    private Long distance;
        public RequestRoute(Coordinates coordinates,String name,Location from, Location to, Long distance){

            this.name =name;
            this.coordinates=coordinates;

            this.from=from;
            this.to = to;
            this.distance = distance;
        }

    /**
     * Конструтктор для создания элемента.
     */
    public RequestRoute(){
        System.out.println();
            Scanner in = new Scanner(System.in);
            System.out.println("Введите имя (непустое):");
            name=in.nextLine();
            while (name.trim().length()==0 || ",".contains(name)){
                System.out.println("Введите НЕПУСТОЕ имя:");
                name=in.nextLine();
            }
            System.out.println("Введите координаты");
            coordinates=new Coordinates();
            System.out.println("Выдерете вариант задания локации, их которой строится маршрут (или путой)");
            System.out.println("   IntLocation(для ввода целых координат)");
            System.out.println("   FloatLocation(для ввода дробных координат)");
           String check;
            while( true){
                check=in.nextLine();
                if (Pattern.compile("IntLocation").matcher(check).matches()){from = new IntLocation();
                    break;}
                else if (Pattern.compile("FloatLocation").matcher(check).matches()){from = new FloatLocation();break;} else if(check.trim().equals("")){
                    from=null;

                    System.out.println("Локации, из которой строится маршрут пустое");
                    break;
                }
                else {
                    System.out.println("Ошибка ввода. Выберете из двух вариантов.");
                }
            }
            System.out.println("Выдерете вариант задания локации, в которую строится маршрут ");
            System.out.println("   IntLocation(для ввода целых координат)");
            System.out.println("   FloatLocation(для ввода дробных координат)");

            while( true){
                check=in.nextLine();
                if (Pattern.compile("IntLocation").matcher(check).matches()){to = new IntLocation();
                    break;}
                else if (Pattern.compile("FloatLocation").matcher(check).matches()){to = new FloatLocation();break;}
                else {
                    System.out.println("Ошибка ввода. Выберете из двух вариантов.");
                }
            }
            distance = ReadDistance();



        }



    public  long ReadDistance(){
        long t;
        System.out.println("Введите дистанцию - Целое число. -2^63≤x≤2^63-1");
        Scanner in = new Scanner(System.in);
        try{ t=Long.parseLong(in.nextLine());}
        catch (InputMismatchException | NumberFormatException e){
            System.out.println("Ошибочный ввод");
            t=ReadDistance();
        }
        return t;
    }




    /**
     *
     * @return Имя
     */
    public String getName() {
        return name;
    }


    /**
     * Задание имени
     * @param name Имя
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Задание коордтинат
     * @param coordinates Координаты
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }





    /**
     * Задание дистанции
     * @param distance Дистация
     */
    public void setDistance(Long distance) {
        this.distance = distance;
    }

    /**
     * Задания локации выезда
     * @param from Локация выезда
     */
    public void setFrom(Location from) {
        this.from = from;
    }



    /**
     * Задание локации приезда
     * @param to локация приезда
     */
    public void setTo(Location to) {
        this.to = to;
    }

    /**
     *
     * @return Координаты
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }



    /**
     *
     * @return Локация выезда
     */
    public Location getFrom() {
        return from;
    }

    /**
     *
     * @return Локация приезда
     */
    public Location getTo() {
        return to;
    }

    /**
     *
     * @return Дистанция
     */
    public Long getDistance() {
        return distance;
    }


}

