package domain;

import java.util.*;
import java.util.Map.*;

import constraints.Constraint;

public class CourseRequisites{

    private String head;
    private ArrayList<CourseRequisites> leafs = new ArrayList<CourseRequisites>();
    private HashMap<String, CourseRequisites> locate = new HashMap<String, CourseRequisites>();
    private CourseRequisites parent = null;
    
    public CourseRequisites(String head, String leaf) {
        this.head = head;
        locate.put(head, this);
        addConstraint(head,leaf);
    }
    public CourseRequisites(String head) {
        this.head = head;
        locate.put(head, this);
    }
    public int getLongestLeaf(String item) {
        if(locate.containsKey(item)) {
            return lengthToLeaf(locate.get(item));
        }
        return 0;
    }
    public boolean isRoot() {
        return parent == null;
    }
    public boolean isLeaf() {
        return leafs.size() == 0;
    }
    public int lengthToLeaf(CourseRequisites cr) {
        if (cr.isLeaf())
            return 0;
        else {
            int maxLength = 0;
            for(int i = 0 ; i < cr.leafs.size(); i++){
            maxLength = Math.max(maxLength, lengthToLeaf(cr.leafs.get(i)));
            }
            return maxLength + 1;
        }
    }
    public boolean contains(String item) {
        return locate.containsKey(item);
    }
    public void addConstraint(String root, String leaf) {
        if(locate.containsKey(root)) {
            locate.get(root).addConstraint(leaf);
        }else {
            addConstraint(root).addConstraint(leaf);
        }
        if(root.equals("Writ131")) {
            System.out.println("adding: " + leaf);
            print();
            System.out.println();
        }
    }

    public CourseRequisites addConstraint(String leaf) {
        CourseRequisites cr = new CourseRequisites(leaf);
        leafs.add(cr);
        cr.parent = this;
        cr.locate = this.locate;
        locate.put(leaf, cr);
        return cr;
      }
      @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( (head == null) ? 0 : head.hashCode());
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
        if ( head == null ) {
            if ( other.head != null )
                return false;
        } else if ( !head.equals(other.head) )
            return false;
        return true;
    }
    @Override
      public String toString() {
          return head;
      }
      public String getHead() {
        return head;
      }

      public CourseRequisites getTree(String of) {
        return locate.get(of);
      }

      public CourseRequisites getParent() {
        return parent;
      }

      public Collection<String> getSuccessors(String of) {
        Collection<String> successors = new ArrayList<String>();
        CourseRequisites tree = getTree(of);
        if (null != tree) {
          for (CourseRequisites leaf : tree.leafs) {
            successors.add(leaf.head);
          }
        }
        return successors;
      }

      public Collection<CourseRequisites> getSubTrees() {
        return leafs;
      }

      public static Collection<String> getSuccessors(String of, Collection<CourseRequisites> in) {
        for (CourseRequisites tree : in) {
          if (tree.locate.containsKey(of)) {
            return tree.getSuccessors(of);
          }
        }
        return new ArrayList<String>();
      }

      public void print() {
          print("", true);
      }

      private void print(String prefix, boolean isTail) {
          System.out.println(prefix + (isTail ? "└── " : "├── ") + head);
          for (int i = 0; i < leafs.size() - 1; i++) {
              leafs.get(i).print(prefix + (isTail ? "    " : "│   "), false);
          }
          if (leafs.size() > 0) {
              leafs.get(leafs.size() - 1)
                      .print(prefix + (isTail ?"    " : "│   "), true);
          }
      }
    public void remove(String rhs) {
        locate.remove(rhs);
    }

    }

