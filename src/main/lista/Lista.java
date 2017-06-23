package main.lista;

import java.util.Random;
import main.carta.Carta;

public class Lista{

    private static final int TAXA_CRESCIMENTO = 1000;
    private Carta[] lista;
    private int count;

    public Lista() {
        clear();
    }
    
    public void clear () {
        this.lista = new Carta[TAXA_CRESCIMENTO];
        this.count = 0;
    }

    public int size () {
        return count;
    }

    public boolean isEmpty () {
        return (count == 0);
    }

    public int indexOf (Carta element) {
        for (int index = 0; index < count; index++) {
            if (lista[index] == element) {
                return index;
            }
        }   
        return -1;
    }
    
    public boolean contains (Carta element) {
        return (indexOf(element) >= 0);
    }
    
    private boolean indiceValido (int index) {
        return (index >= 0 && index < count);
    }
    
    public Carta get (int index){
            return lista[index];
    }

    public Carta getLast(){
        if(count > 0){
            return lista[count-1];
        }
        return null;
    }

    public void set (int index , Carta element) throws Exception {
        if (indiceValido(index)) {
            lista[index] = element;
        } else {
            throw new Exception("Indice inválido " + index);
        }
    }

    public Carta remove(){
        if(count > 0){
            Carta aux = lista[count-1];
            count--;
            return aux;
        }
        return null;
    }

    public boolean remove (Carta element) {
        int index = indexOf(element);

        if (index < 0) {
            return false;
        } else {
            // remover o elemento que est
            
            if (index == count-1) {
                count--;
            } else {
                for (int i = index; i < count-1; i++) {
                    lista[i] = lista[i+1];
                }
                count--;
            }
            
            return true;
        }
    }

    public Carta remove (int index) {

        Carta aux = lista[index];

        if (index < 0) {
            return null;
        } else {
            // remover o elemento que es
            
            if (index == count-1) {  //
                count--;
            } else {    // � o primeiro ou outro no meio
                for (int i = index; i < count-1; i++) {
                    lista[i] = lista[i+1];
                }
                count--;
            }
            
            return aux;
        }
    }
    
    private boolean temEspacoParaAdicionar () {
        return (count < lista.length);
    }
    
    private void crescerLista() {
        Carta[] novaLista = new Carta[lista.length + TAXA_CRESCIMENTO];
        
        for (int i = 0; i < count; i++) {
            novaLista[i] = lista[i];
        }
        
        this.lista = novaLista;
    }
    
    /**
     * Adiciona um elemento no final da lista
     * @param element 
     */
    public void add (Carta element) {
        
        if (!temEspacoParaAdicionar()) {
            crescerLista();
        }

        lista[count] = element;
        count++;
    }

    public void add (int index , Carta element) {
        if (index == count) { //
            add(element);
        } else {    //
            if (!temEspacoParaAdicionar()) {
                crescerLista();
            }
            
            for (int pos = count-1; pos >= index; pos--) {
                lista[pos+1] = lista[pos];
            }
            
            lista[index] = element;
            count++;
        }
    }

    @Override
    public String toString () {
        String lstr = "[ ";
        for (int i = 0; i < count; i++) {
            lstr += lista[i] + " ";
        }
        
        return lstr + "]";
    }
    
    public void printaTexto() {    
        for(int i = 0; i < count; i++){
            System.out.print(i + ". " + lista[i].getNome() + " - " + lista[i].getCor());
            System.out.println();
        }
    }

    public void printaUltima(){
        System.out.println(lista[count-1].getNome() + " - " + lista[count-1].getCor());
    }

    public void embaralhar() {
        
        Random random = new Random();
        int tamanho = lista.length - 2;
        for (int i=0; i < tamanho; i++) {
            int j = random.nextInt(lista.length);

            Carta aux = lista[j];

            remove(lista[j]);
            
            add(aux); 
        }
        count--;
    }
}
