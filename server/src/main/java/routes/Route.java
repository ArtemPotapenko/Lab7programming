package routes;


import request.Request;
import request.RequestRoute;
import routedir.Coordinates;
import routedir.Location;
import transfer.PrintToClient;
import transfer.States;

import java.io.IOException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Класс элементов коллекции.
 * @version 1.0
 */
public class Route implements Comparable<Route> {


    /**
     * Id элепмента. Его уникальный номер
     */
    private long id;
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
    private Date creationDate;
    /**
     * Локация отъезда.
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

    public Route(long id, Date creationDate, Coordinates coordinates, String name, Location from, Location to, Long distance){
        this.id= id;
        this.name =name;
        this.coordinates=coordinates;
        this.creationDate = creationDate;
        this.from=from;
        this.to = to;
        this.distance = distance;
    }

    /**
     * Создать объект по запросу и id
     * @param route запрос клиента
     * @param id индикационный номер
     */
    public Route(RequestRoute route,long id){
        name=route.getName();
        this.id=id;
        coordinates=route.getCoordinates();
        creationDate=new Date();
        from=route.getFrom();
        to=route.getTo();
        distance=route.getDistance();
    }


    /**
     *  Создать объект по сериализованным данным и id
     * @param buf сериализованные данные
     * @param id индикационный номер
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Route(byte[] buf,long id) throws IOException, ClassNotFoundException {
        RequestRoute route = (RequestRoute) Request.convertFromBytes(buf);
        this.id=id;
        name=route.getName();
        coordinates=route.getCoordinates();
        creationDate=new Date();
        from=route.getFrom();
        to=route.getTo();
        distance=route.getDistance();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(name, route.name) && Objects.equals(coordinates, route.coordinates) && Objects.equals(creationDate, route.creationDate) && Objects.equals(from, route.from) && Objects.equals(to, route.to) && Objects.equals(distance, route.distance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, coordinates, creationDate, from, to, distance);
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

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", from=" + from +
                ", to=" + to +
                ", distance=" + distance +
                '}';
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
     * Задание даты
     * @param creationDate Дата
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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
     * Задание id
     * @param id id
     */
    public void setId(long id) {
        this.id = id;
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
     * @return Дата
     */
    public Date getCreationDate() {
        return creationDate;
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

    /**
     *
     * @return id
     */
    public long getId() {
        return id;
    }

    @Override
    public int compareTo(Route o) {
        return to.compareTo(o.to);
    }

    /**
     * Вывод элемента
     */
    public States ShowPrint(){
        States states=new States();

        states.print( "Имя:  "+ name);
        states.print("  Id: " + id);
        states.print("  Координата x: "+coordinates.getX());
        states.print("  Координата Y:" + coordinates.getY());
        try{
            states.print("  Локация, из которой совершается путь :"+getFrom().getClass().toString() + ". Данные: "+getFrom().getAll());}
        catch (NullPointerException e){
            states.print("  Локация, из которой совершается путь : null");
        }
        states.print("  Локация, в которую совершается путь :"+getTo().getClass() .toString()+ ". Данные: "+getTo().getAll());
        states.print("  Дата: "+creationDate.toString());
        states.print("  Дистанция:"+ distance.toString());
        states.print("");
        return states;

    }
}