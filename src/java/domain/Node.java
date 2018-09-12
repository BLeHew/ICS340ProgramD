package domain;

import java.util.*;

public class Node{

    private String head;
    private HashMap<String, Node> leafs = new HashMap<>();
    private Node parent = null;

    public Node(String head, String leaf) {
        this.head = head;
        addConstraint(head,leaf);
    }
    public Node(String head) {
        this.head = head;
    }
    public boolean isRoot() {
        return parent == null;
    }
    public boolean isLeaf() {
        return leafs.size() == 0;
    }
    public int lengthToLeaf(Node cr) {
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

    public void addConstraint(String root, String leaf) {
       addConstraint(root).addConstraint(leaf);
    }

    public Node addConstraint(String leaf) {
        Node cr = new Node(leaf);
        leafs.put(leaf, cr);
        cr.parent = this;
        return cr;
      }
      @Override
      public String toString() {
          return head;
      }
      public String getHead() {
        return head;
      }
      public Node getParent() {
        return parent;
      }

      public Collection<Node> getSubTrees() {
        return leafs.values();
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
    }

