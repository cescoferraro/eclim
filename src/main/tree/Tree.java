package main.tree;


import java.util.ArrayList;
import java.util.List;
import main.linked.Linked;
import main.queue.Queue;
import main.calc.Calc;

public class Tree {

    public static final class Node {

        public Node father;
        public Node left;
        public Node right;
        public String element;

        public Node(String element) {
            father = null;
            left = null;
            right = null;
            this.element = element;
        }
        
        public void setElement(String element) {
            this.element = element;
        }
    }

    private int count; 
    private Node root; 
    private Node cursor; 

    public Tree() {
        count = 0;
        root = null;
    }

    public boolean isEmpty() {
        return (root == null);
    }

    public int size() {
        return count;
    }

    public int height() throws Exception {
        Queue<Node> fila = new Queue<>();
        Node aux = null;
        Node fim = null;
        if (root != null) {
            fila.enqueue(root);
            while(!fila.isEmpty()) {
                aux = fila.dequeue();
                if (aux.left != null)
                    fila.enqueue(aux.left);
                if (aux.right != null)
                    fila.enqueue(aux.right);
                fim = aux;
            }
        }                  
        int cont=0;        
        while (fim != root) {
            cont++;
            fim = fim.father;
        }        
        return cont;     
    }
    
    public boolean addRoot(String element) {
        if (root != null){
            return false;
        }
        
        Node node = new Node(element);
        root = node;
        cursor = root;
        count++;
        return true;
    }

    public void setValueOnCursor(String value) {
        cursor.setElement(value);
    }
    
    public void addAndMoveCursorToLowerLevel(String valor) {
        Node novoNo = new Node(valor);
        if(hasLeft()) {
            cursor.left = novoNo;
            novoNo.father = cursor;
        }
        else {
            cursor.right = novoNo;
            novoNo.father = cursor;
        }
        cursor = novoNo;
        count++;
    }
    
    public void returnCursorToUpperLevel() {
        cursor = cursor.father;
    }
    
    public void addOperando(String valor) {
        addAndMoveCursorToLowerLevel(valor);
        returnCursorToUpperLevel();
    }
    
    public boolean hasLeft() {
        if(cursor.left == null)
            return true;
        else
            return false;
    }
    
    public boolean hasRight() {
        if(cursor.right == null)
            return true;
        else
            return false;
    }    
    
    public boolean verificaSeEstaNaRaiz() {
        return cursor == root;
    }
    
    public Double calc() throws Exception  {
        Calc calc = new Calc();
        
        while(count > 1) {
            List<Node> listaDeNodosExternos = getListOfExternalNodes();
            for (int i = 1; i < listaDeNodosExternos.size(); i++) {
                Node anterior = listaDeNodosExternos.get(i - 1);
                Node atual = listaDeNodosExternos.get(i);
                if(anterior.father == atual.father) {
                    Node pai = anterior.father;
                    calc.setOperador1(Double.parseDouble(anterior.element));
                    calc.setOperador2(Double.parseDouble(atual.element));
                    calc.setOperando(pai.element.charAt(0));
                    double valor = calc.calcula();

                    String valorString = String.format("%f", valor).replace(',','.');
                    pai.setElement(valorString);

                    pai.left = null;
                    pai.right = null;
                    anterior.father = null;
                    atual.father = null;
                    count = count - 2;
                }
            }
        }
        
        return Double.parseDouble(root.element);
    }
    
    public boolean isExternal(Node node) {
        return node.left == null && node.right == null;
    }
    
    public Linked positionsPre() {
        Linked res = new Linked();
        positionsPreAux(root, res);
        return res;
    }
    
    private void positionsPreAux(Node n, Linked res) {
        if(n == null)
            return;
        res.add(n.element);
        if(n.left != null)
            positionsPreAux(n.left, res);
        if(n.right != null)
            positionsPreAux(n.right, res);
    }

    public Linked positionsPos() {
        Linked res = new Linked();
        positionsPosAux(root, res);
        return res;
    }
    
    private void positionsPosAux(Node n, Linked res) {
        if(n == null)
            return;
        if(n.left != null)
            positionsPosAux(n.left, res);
        if(n.right != null)
            positionsPosAux(n.right, res);
        res.add(n.element);
    }

    public Linked positionsCentral() {
        Linked res = new Linked();
        positionsCentralAux(root, res);
        return res;
    }
    
    private void positionsCentralAux(Node n, Linked res) {
        if(n == null)
            return;
        if(n.left != null)
            positionsCentralAux(n.left, res);
        res.add(n.element);
        if(n.right != null)
            positionsCentralAux(n.right, res);
    }

    public Linked positionsWidth() throws Exception {
        Linked li = new Linked();
        Queue<Node> fila = new Queue<>();
        Node aux = null;
        if (root != null) {
            fila.enqueue(root);
            while(!fila.isEmpty()) {
                aux = fila.dequeue();
                if (aux.left != null)
                    fila.enqueue(aux.left);
                if (aux.right != null)
                    fila.enqueue(aux.right);
                li.add(aux.element);
            }
        }
        return li;
    }
    
    //variacao do caminhamento em largura, porem adiciona
    //apenas os nodes externos, e retorna uma lista de Node
    public List<Node> getListOfExternalNodes() throws Exception {
        List<Node> li = new ArrayList<Node>();
        Queue<Node> fila = new Queue<>();
        Node aux = null;
        if (root != null) {
            fila.enqueue(root);
            while(!fila.isEmpty()) {
                aux = fila.dequeue();
                if (aux.left != null)
                    fila.enqueue(aux.left);
                if (aux.right != null)
                    fila.enqueue(aux.right);
                
                if(aux.left == null && aux.right == null)
                    li.add(aux);
            }
        }
        return li;
    }
}
