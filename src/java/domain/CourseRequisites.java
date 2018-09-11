package domain;

import java.util.*;
import java.util.Map.*;

public class CourseRequisites{

    private HashMap<String, String> constSet;
    private final String root;
    public CourseRequisites(String lhs, String type, String rhs) {
        root = lhs;
        constSet = new HashMap<>();
        constSet.put(rhs, type);
    }
    public boolean contains(String lhs) {
        return root.equals(lhs) || constSet.containsKey(lhs);
    }
    public CourseRequisites(CourseRequisites other) {
        this.root = other.root;
        this.constSet = new HashMap<>();
        this.constSet.putAll(other.constSet);
    }
    public void add(String lhs, String type, String rhs) {
        constSet.put(rhs, type);
    }
    public String getRoot() {
        return root;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Entry<String, String> e : constSet.entrySet()) {
            sb.append(e.getValue() + " " + e.getKey() + " ");
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( (root == null) ? 0 : root.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        CourseRequisites other = (CourseRequisites) obj;
        if ( root == null ) {
            if ( other.root != null )
                return false;
        } else if ( !root.equals(other.root) )
            return false;
        return true;
    }

}
