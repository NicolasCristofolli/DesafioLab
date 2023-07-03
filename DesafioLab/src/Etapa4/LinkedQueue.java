package Etapa4;

import Etapa3.OverflowException;
import Etapa3.UnderflowException;

public class LinkedQueue<E> implements Queue<E> {
    private Node<E> frontNode;
    private Node<E> backNode;
    private int size;

    public LinkedQueue() {
        frontNode = null;
        backNode = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return false; // A fila encadeada não tem limite de capacidade
    }

    @Override
    public int numElements() {
        return size;
    }

    @Override
    public void enqueue(E element) throws OverflowException {
        Node<E> newNode = new Node<>(element);
        if (isEmpty()) {
            frontNode = newNode;
        } else {
            backNode.setNext(newNode);
        }
        backNode = newNode;
        size++;
    }

    @Override
    public E dequeue() throws UnderflowException {
        if (isEmpty()) {
            throw new UnderflowException();
        }
        E dequeuedElement = frontNode.getData();
        frontNode = frontNode.getNext();
        if (frontNode == null) {
            backNode = null;
        }
        size--;
        return dequeuedElement;
    }

    @Override
    public E front() throws UnderflowException {
        if (isEmpty()) {
            throw new UnderflowException();
        }
        return frontNode.getData();
    }

    @Override
    public E back() throws UnderflowException {
        if (isEmpty()) {
            throw new UnderflowException();
        }
        return backNode.getData();
    }
}