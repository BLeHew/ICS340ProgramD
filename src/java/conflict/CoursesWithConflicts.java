package conflict;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class CoursesWithConflicts {
    private HashMap<String,HashSet<String>> courses = new HashMap<>();

    public void addConflict(String course, String conflict){
        if(!courses.containsKey(course)){
            courses.put(course, new HashSet<>());
        }
        courses.get(course).add(conflict);
    }
    public Set<String> keySet(){
        return courses.keySet();
    }
    public boolean contains(String course){
        return courses.containsKey(course);
    }
    public boolean isEmpty(){
        return courses.isEmpty();
    }
    public void removeConflict(String course, String conflict){
        if(courses.containsKey(course)) {
            courses.get(course).remove(conflict);
            if(courses.get(course).isEmpty()){
                courses.remove(course);
            }
        }
    }

}
