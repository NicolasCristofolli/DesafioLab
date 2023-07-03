package Etapa4;

import Etapa3.OverflowException;
import Etapa3.UnderflowException;

public class LinkedStack<E> implements Stack<E> {
    private Node<E> topNode;
    private int size;

    public LinkedStack() {
        topNode = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return false; // A pilha encadeada não tem limite de capacidade
    }

    @Override
    public int numElements() {
        return size;
    }

    @Override
    public void push(E element) throws OverflowException {
        Node<E> newNode = new Node<>(element);
        newNode.setNext(topNode);
        topNode = newNode;
        size++;
    }

    @Override
    public E pop() throws UnderflowException {
        if (isEmpty()) {
            throw new UnderflowException();
        }
        E poppedElement = topNode.getData();
        topNode = topNode.getNext();
        size--;
        return poppedElement;
    }

    @Override
    public E top() throws UnderflowException {
        if (isEmpty()) {
            throw new UnderflowException();
        }
        return topNode.getData();
    }
}