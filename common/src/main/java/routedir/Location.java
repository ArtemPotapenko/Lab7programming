package routedir;


import java.io.Serializable;

/**
 * Адстактный класс локации
 * @version 1.0
 */
public abstract class  Location implements Comparable<Location>, Serializable {
    /**
     * Стандартный конструктор для ввода локации
      */
    public Location(){};

    /**
     *
     * @return Все параметры
     */
        public abstract String getAll();

    @Override
    public int compareTo(Location o) {
        return this.hashCode()-o.hashCode();
    }


}

