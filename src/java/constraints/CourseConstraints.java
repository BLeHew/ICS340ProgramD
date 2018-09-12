package constraints;

import java.util.*;

import domain.*;

public class CourseConstraints extends HashMap<String, HashMap<String,Node>>{


    HashMap<String,HashMap<String,String>> constraintsMap = new HashMap<>();

    public void addConstraint(String lhs,String type, String rhs) {
        if(!constraintsMap.containsKey(lhs)) {
            constraintsMap.put(lhs,new HashMap<String,String>());
            constraintsMap.get(lhs).put(rhs, type);
        }else {
            constraintsMap.get(lhs).put(rhs, type);
        }
    }

    public void constructConstraintTrees() {

        for(String lhs : constraintsMap.keySet()) {
            put(lhs, new HashMap<String,Node>());
        }

        for(Entry<String,HashMap<String,String>> e : constraintsMap.entrySet()) {

            for(Entry<String,String> cs : e.getValue().entrySet()) {

                get(e.getKey()).put(cs.getKey(),new Node(cs.getKey()));
                get(e.getKey()).get(e.getKey()).add
            }

        }


    }


    public void print() {
        constructConstraintTrees();
        //printMap();



        }

    private void printMap() {
        for(Entry<String,HashMap<String,String>> e : constraintsMap.entrySet()) {
            System.out.print(e.getKey() + " ");
            for(Entry<String,String> n : e.getValue().entrySet()) {
                System.out.print(n.getKey() + " ");
            }
            System.out.println();
        }
    }
    }


