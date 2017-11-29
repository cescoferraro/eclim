package main.app;

import main.tree.Tree;

public class App {

  public static void main(String[] args) {
    System.out.println("sdkfjn");
    Tree tree = new Tree();
    try {
      tree.insert(5);
      tree.insert(3);
      tree.insert(4);
      tree.insert(9);
      tree.insert(10);
      tree.displayTree();
      System.out.println();
      tree.preorder();
      System.out.println();
      tree.postorder();
      System.out.println();
      tree.inorder();
      System.out.println();
      System.out.println(tree.count());
      System.out.println(tree.countFolhas());
      System.out.println(tree.media());
      tree.clone().displayTree();
      // System.out.println(tree.height());
    } catch (Exception ee) {
      System.out.println(ee);
    }
  }
}
