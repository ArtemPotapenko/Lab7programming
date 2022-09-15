package routes;



import routedir.Coordinates;
import routedir.FloatLocation;
import routedir.IntLocation;
import routedir.Location;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

/**
 * Класс обертка Route
 * @version 1.0
 * @see Route
 */
public class Routes implements Set<Route>{
    /**
     * Поле управления бд
     */
    protected Connection connection;
    /**
     * Логин
     */
    protected String login;

    public Routes(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int size() {
        try {
            ResultSet resultSet=connection.createStatement().executeQuery("SELECT COUNT(*) FROM routes;");
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean isEmpty() {
        return getSet().isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return getSet().contains(o);
    }

    @Override
    public Iterator<Route> iterator() {
        return getSet().iterator();
    }

    @Override
    public Object[] toArray() {
        return getSet().toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return getSet().toArray(a);
    }

    @Override
    public boolean add(Route route) {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO routes VALUES  (?,?,?,?,?,?,?);");
            preparedStatement.setLong(1,route.getId());
            preparedStatement.setString(2,route.getName());
            preparedStatement.setDate(3, new Date(route.getCreationDate().getTime()));
            PreparedStatement preparedStatementCoordinates=connection.prepareStatement("INSERT INTO coordinates VALUES (?,?,?);");
            preparedStatementCoordinates.setLong(1,route.getId());
            preparedStatementCoordinates.setDouble(2,route.getCoordinates().getX());
            preparedStatementCoordinates.setDouble(3,route.getCoordinates().getY());
            preparedStatementCoordinates.execute();
            if (route.getFrom()==null){preparedStatement.setNull(4,4);}else
            if (route.getFrom() instanceof IntLocation){
                PreparedStatement intprepared=connection.prepareStatement("INSERT INTO intlocations VALUES (?,?,?,?);");
                preparedStatement.setBoolean(4,true);
                intprepared.setLong(1,route.getId());
                IntLocation from=(IntLocation) route.getFrom();
                intprepared.setInt(2,from.getX());
                intprepared.setLong(3,from.getY());
                intprepared.setString(4, from.getName());
                intprepared.execute();
            }
            if (route.getFrom() instanceof FloatLocation) {
                PreparedStatement floatprepared = connection.prepareStatement("INSERT INTO floatlocations VALUES (?,?,?,?);");
                preparedStatement.setBoolean(4, false);
                floatprepared.setLong(1, route.getId());
                FloatLocation from = (FloatLocation) route.getFrom();
                floatprepared.setFloat(2, from.getX());
                floatprepared.setFloat(3, from.getY());
                floatprepared.setInt(4, from.getZ());
                floatprepared.execute();
            }
            if (route.getTo() instanceof IntLocation){
                PreparedStatement intprepared=connection.prepareStatement("INSERT INTO intlocations VALUES (?,?,?,?);");
                preparedStatement.setBoolean(5,true);
                intprepared.setLong(1,route.getId()+1);
                IntLocation from=(IntLocation) route.getFrom();
                intprepared.setInt(2,from.getX());
                intprepared.setLong(3,from.getY());
                intprepared.setString(4, from.getName());
                intprepared.execute();
            }
            if (route.getTo() instanceof FloatLocation){
                PreparedStatement floatprepared=connection.prepareStatement("INSERT INTO floatlocations VALUES (?,?,?,?);");
                preparedStatement.setBoolean(5,false);
                floatprepared.setLong(1,route.getId()+1);
                FloatLocation from=(FloatLocation) route.getTo();
                floatprepared.setFloat(2,from.getX());
                floatprepared.setFloat(3,from.getY());
                floatprepared.setInt(4,from.getZ());
                floatprepared.execute();
            }
            preparedStatement.setLong(6,route.getDistance());
            preparedStatement.setString(7,login);
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
     public long getUniqueID(){
         try {
             ResultSet resultSet=connection.createStatement().executeQuery("SELECT MAX(routeid) FROM routes;");
             resultSet.next();
             return resultSet.getLong(1)+2;
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }
     }

    @Override
    public boolean remove(Object o) {
        long intId=((Route)o).getId();
        try {
            ResultSet resultSet=connection.createStatement().executeQuery("SELECT intfrom,itto,userlogin FROM routes WHERE routeid="+intId+";");
            resultSet.next();
            Boolean from=resultSet.getBoolean(1);
            Boolean to=resultSet.getBoolean(2);
            if (!resultSet.getString(3).equals(login)){
                return false;
            }
            if (from){
                connection.createStatement().execute("DELETE FROM intlocations WHERE intlocationid="+intId+";");
            }
            if (!from){
                connection.createStatement().execute("DELETE FROM floatlocations WHERE floatlocationid="+intId+";");
            }
            connection.createStatement().execute("DELETE FROM routes WHERE routeid="+intId+";");
            connection.createStatement().execute("DELETE  FROM coordinates WHERE coordinateid="+intId+";");
            long anotherId=intId+1;
            if (to){
                connection.createStatement().execute("DELETE FROM intlocations WHERE intlocationid="+anotherId+";");
            }
            if (!to){
                connection.createStatement().execute("DELETE FROM floatlocations WHERE floatlocationid="+anotherId+";");
            }
        } catch (SQLException e) {
            return false;
        }


        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return getSet().containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Route> c) {
        for (Route x: c){
            add(x);
        }
        return true;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return getSet().retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean b= getSet().removeAll(c);
        return b;
    }

    @Override
    public void clear() {
            for (Route x :getSet()){
                remove(x);}




    }
    private Location getLocationByCheckAndId(Boolean check,Integer id) throws SQLException {
        if (check==null){
            return null;
        }
        if (check){
            ResultSet resultSet=connection.createStatement().executeQuery("SELECT * FROM intlocations  WHERE intlocationid="+id+";");
            resultSet.next();
            return new IntLocation(resultSet.getInt(2),resultSet.getInt(3),resultSet.getString(4));
        }
        ResultSet resultSet=connection.createStatement().executeQuery("SELECT * FROM floatlocations  WHERE floatlocationid="+id+";");
        resultSet.next();
        return new FloatLocation(resultSet.getFloat(2),resultSet.getFloat(3),resultSet.getInt(4));

    }


    /**
     *
     * @return множество элементов.
     */
    public LinkedHashSet<Route> getSet() {
        try {
            ResultSet resultSet=connection.createStatement().executeQuery("SELECT * FROM routes;");
            TreeSet<Route> routes=new TreeSet<>();
            while (resultSet.next()){
                String name=resultSet.getString(2);
                int id=resultSet.getInt(1);
                Location from=getLocationByCheckAndId(resultSet.getBoolean(4),id);
                Location to=getLocationByCheckAndId(resultSet.getBoolean(5),id+1);
                java.util.Date date=resultSet.getDate(3);
                Coordinates coordinates=getCoordinateById(id);

                Route route=new Route(id,date,coordinates,name,from,to,resultSet.getLong(6));
                routes.add(route);



            }

            return new LinkedHashSet<>(routes);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private Coordinates getCoordinateById(int id) throws SQLException {
        ResultSet resultSet=connection.createStatement().executeQuery("SELECT * FROM coordinates WHERE  coordinateid="+id+";");
        resultSet.next();
        return new Coordinates(resultSet.getLong(2),resultSet.getDouble(3));
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
