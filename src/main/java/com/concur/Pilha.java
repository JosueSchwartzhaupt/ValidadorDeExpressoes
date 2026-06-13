package com.concur;

public class Pilha {

    private int topo;
    private char pilha[];

    public Pilha(int tamanho) {
        this.topo = 0;
        this.pilha = new char[tamanho];
    }

    public char[] getPilha() {
        return this.pilha;
    }

    public void setPilha(char[] pilha) {
        this.pilha = pilha;
    }

    public int getTopo() {
        return this.topo;
    }

    public void setTopo(int topo) {
        this.topo = topo;
    }

    // Adicionar o novo nodo no topo da pilha.
    public boolean push(char numero) {
        // Verificar tamanho da pilha
        if (this.getTopo() < this.pilha.length)  {
            char novo = numero;
            this.pilha[this.getTopo()] = novo;
            this.topo++;
            return true;
        }
        else
            return false;
    }

    // Remover o nodo do topo da pilha.
    public Character pop() {
        // Verificar tamanho da pilha
        if (!this.pilhaVazia())  {
            char nodoTopo = this.pilha[this.topo-1];
            this.setTopo(this.getTopo()-1);
            return nodoTopo;
        }
        else
            return null;
    }

    // Buscar o nodo do topo da pilha
    public Character buscarTopo() {
        if (this.getTopo() > 0)
            return this.pilha[this.getTopo()-1];
        else
            return null;
    }

    // Verificar se a pilha está vazia
    public boolean pilhaVazia() {
        if (this.getTopo() == 0)
            return true;
        else
            return false;
    }

    // Imprimir os dados de todos os nodos
    public void imprimirPilha() {
        for (int i=this.getTopo()-1; i>=0; i--) {
            System.out.print(this.pilha[i] + " -> ");
        }
    }

}
