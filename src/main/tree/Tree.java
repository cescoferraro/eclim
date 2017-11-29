package main.tree;

public class Tree {

  public static final class TreeNode {

    public TreeNode father;
    public TreeNode left;
    public TreeNode right;
    public int key;

    public TreeNode(int key) {
      father = null;
      left = null;
      right = null;
      this.key = key;
    }

    public void setElement(int key) {
      this.key = key;
    }
  }

  protected TreeNode root = null;

  public Tree() {
    root = null;
  }

  public Tree(TreeNode yay) {
    root = yay;
  }

  public void clear() {
    root = null;
  }

  public boolean isEmpty() {
    return root == null;
  }

  public TreeNode getRootNode() {
    return root;
  }

  public boolean insert(int el) {
    TreeNode p = root, prev = null;
    // caso o valor já exista na árvore, não inserir e retornar false
    if (search(el) != null) return false;
    // procurando um lugar para colocar o novo nó
    while (p != null) {
      prev = p;
      if (el < p.key) p = p.left;
      else p = p.right;
    }
    // se árvore vazia
    if (root == null) root = new TreeNode(el);
    else if (prev.key < el) prev.right = new TreeNode(el);
    else prev.left = new TreeNode(el);
    return true;
  }

  public TreeNode search(int el) {
    return search(root, el);
  }

  protected TreeNode search(TreeNode p, int el) {
    while (p != null) {
      /* se valor procurado == chave do nó retorna referência ao nó */
      if (el == p.key) return p;
      /* se valor procurado < chave do nó, procurar na sub-árvore esquerda deste nó */
      else if (el < p.key) p = p.left;
      /* se valor procurado > chave do nó, procurar na sub-árvore direita deste nó */
      else p = p.right;
    }
    // caso chave não foi achada, retorna null
    return null;
  }

  /* métodos de busca recursivos */
  public TreeNode searchR(int el) {
    return searchR(root, el);
  }

  protected TreeNode searchR(TreeNode p, int el) {
    if (p == null || el == p.key) return p;
    else if (el < p.key) return searchR(p.left, el);
    else return searchR(p.right, el);
  }

  protected TreeNode searchFather(int el) {
    TreeNode p = root;
    TreeNode prev = null;
    while (p != null && !(p.key == el)) { // acha o nó p com a chave el
      prev = p;
      if (p.key < el) p = p.right;
      else p = p.left;
    }
    if (p != null && p.key == el) return prev;
    return null;
  }

  public void preorder() {
    preorder(root);
  }

  protected void preorder(TreeNode p) {
    if (p != null) {
      System.out.print(p.key + " ");
      preorder(p.left);
      preorder(p.right);
    }
  }

  public void postorder() {
    postorder(root);
  }

  protected void postorder(TreeNode p) {
    if (p != null) {
      postorder(p.left);
      postorder(p.right);
      System.out.print(p.key + " ");
    }
  }

  public int max(int i, int j) {
    return i > j ? i : j;
  }

  public void displayTree() {
    if (isEmpty()) {
      System.out.println("Árvore vazia!");
      return;
    }
    String separator = String.valueOf("  |__");
    System.out.println(this.root.key);
    displaySubTree(root.left, separator);
    displaySubTree(root.right, separator);
  }

  private void displaySubTree(TreeNode node, String separator) {
    if (node != null) {
      TreeNode father = this.searchFather(node.key);
      if (node.equals(father.left) == true) {
        System.out.println(separator + node.key + " (ESQ)");
      } else {
        System.out.println(separator + node.key + " (DIR)");
      }
      displaySubTree(node.left, "     " + separator);
      displaySubTree(node.right, "     " + separator);
    }
  }

  public int countFolhas() {
    int cont = 0;
    if (root != null) {
      cont = cont + countFolhas(root.left) + countFolhas(root.right);
      return cont;
    } else {
      return cont;
    }
  }

  protected int countFolhas(TreeNode p) {
    int cont = 0;
    if (p.left == null || p.right == null) {
      return 1;
    }
    if (p != null) {
      cont = cont + countFolhas(p.left) + countFolhas(p.right);
    }
    return cont;
  }

  public int count() {
    if (root != null) {
      int cont = 0;
      cont = 1;
      cont = cont + count(root.left) + count(root.right);
      return cont;
    } else {
      return 0;
    }
  }

  protected int count(TreeNode p) {
    int cont = 0;
    if (p != null) {
      cont = cont + 1 + count(p.left) + count(p.right);
    }
    return cont;
  }

  public void inorder() {
    inorder(root);
  }

  protected void inorder(TreeNode p) {
    if (p != null) {
      inorder(p.left);
      System.out.print(p.key + " ");
      inorder(p.right);
    }
  }

  //public int countLeafNodes()   Deve retornar o número de nodos folha da árvore
  //public int height()   Deve retornar a altura da árvore

  public int media() {
    int cont = 0;
    if (root != null) {
      cont = root.key;
      cont = cont + media(root.left) + media(root.right);
      return cont / count();
    } else {
      return cont;
    }
  }

  protected int media(TreeNode p) {
    int cont = 0;
    if (p != null) {
      cont = p.key + media(p.left) + media(p.right);
    }
    return cont;
  }

  public Tree clone() {
    if (root == null) {
      return new Tree(null);
    } else {
      TreeNode brandNew = new TreeNode(root.key);
      if (root.left != null) {
        brandNew.left = clone(root.left);
      }
      if (root.right != null) {
        brandNew.right = clone(root.right);
      }
      Tree ne = new Tree(brandNew);
      return ne;
    }
  }

  protected TreeNode clone(TreeNode p) {

    TreeNode brandNew = new TreeNode(p.key);
    if (p.left != null) {
      brandNew.left = clone(p.left);
    }
    if (p.right != null) {
      brandNew.right = clone(p.right);
    }
    return brandNew;
  }
}
