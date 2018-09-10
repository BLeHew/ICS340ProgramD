package domain;

import java.util.HashMap;

import course.Courses;

public class CourseDomains extends HashMap<String, CourseDomain> {
    private final int numEntries;
    
    public CourseDomains(int numEntries) {
        this.numEntries = numEntries;
    }
    public void add(String course, CourseDomain courseDomain) {
        put(course,courseDomain);
        if(this.size() == numEntries) {
            //constructInitialDomains();
        }
    }
    private void constructInitialDomains() {
        for(Entry<String, CourseDomain> courses : this.entrySet()) {
            courses.getValue().createInitial(courses.getKey());
        }
        
    }
}
