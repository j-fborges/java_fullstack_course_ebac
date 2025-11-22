package br.com.j_fborges.dao;

import java.util.HashMap;
import java.util.Map;

public class SingletonGenericDAOMap {

    private static SingletonGenericDAOMap singletonGenericDaoMap;

    /**
     * Contais all application data.
     * Simulates DB
     */
    protected Map<Class, Map<?, ?>> map;

    private SingletonGenericDAOMap() {
        map = new HashMap<>();
    }

    /**
     * Method that ensures the return of only a single ocurrence of this class
     *
     * @return SingletonGenericDAOMap
     */

    public static SingletonGenericDAOMap getInstance() {
        if (singletonGenericDaoMap == null) {
            singletonGenericDaoMap = new SingletonGenericDAOMap();
        }
        return singletonGenericDaoMap;
    }

    public Map<Class, Map<?, ?>> getMap() {
        return this.map;
    }

    public static void printMap(){
        System.out.println(getInstance().toString());
    }

    @Override
    public String toString() {
        return "SingletonGenericDAOMap{" +
                "map=" + map +
                '}';
    }
}
