package fabflix.beans;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;

public class Genre implements Serializable {
    private int id;
    private String name;

    public Genre() {
        id = 0;
        name = "";
    }

    /* Code borrowed from 
     * http://stackoverflow.com/questions/8180430/how-to-override-equals-method-in-java
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Genre.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Genre other = (Genre) obj;

        if (this.id != other.getId()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.id;
        return hash;
    }
    /* End of borrowed code */

    public Genre(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public void setID(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }
}