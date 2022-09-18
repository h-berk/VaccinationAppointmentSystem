package hasanberk.vaccinationappointmentsystem.DataStructure;

import java.util.Collection;
import java.util.Iterator;

public class MyLinkedList<E> implements Collection<E>, Iterable<E> {
    public Node<E> head;

    public void insertAtStart(E element) {
        head = new Node<>(element, head);
    }

    @Override
    public int size() {
        Node<E> temporaryNode = head;
        int counter = 0; //runs a counter to find size of list
        while (temporaryNode != null) {
            counter++;
            temporaryNode = temporaryNode.getNext();
        }
        return counter;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        Node<E> temporaryNode = head;
        while (temporaryNode != null) {
            if (temporaryNode.getElement().equals(o))
                return true;
            temporaryNode = temporaryNode.getNext(); //iterates through until temporaryNode element equals object
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> iterator = new Iterator<E>() {
            private Node<E> currentNode = head;

            @Override
            public boolean hasNext() {
                return currentNode != null; //if current node is null, it cant have a next node
            }

            @Override
            public E next() {
                Node<E> temporaryNode = currentNode;
                currentNode = currentNode.getNext();
                return temporaryNode.getElement();
            }
        };
        return iterator;
    }



    @Override
    public boolean add(E element) {
        Node<E> newNode = new Node<>(element, null); //creates new node of element
        if (head == null) { //if there is no head, element is new head.
            head = newNode;
        } else {
            Node<E> temporaryNode = head;
            while (temporaryNode.getNext() != null) { //iterates until last element, then adds the node.
                temporaryNode = temporaryNode.getNext();
            }
            temporaryNode.setNext(newNode);
        }
        return true;
        //Changed to using a while loop, old method below.

//        if(!isEmpty()) {
//            Node<E> currentNode = head;
//            while(currentNode.next != null) {
//                currentNode= currentNode.next;
//            }
//            currentNode.next = newNode;
//        }
//        head = newNode;
    }

    @Override
    public boolean remove(Object o) {
        Node<E> currentNode = head, previousNode = null;
        if (o != null) {
            while (currentNode != null) {
                if (currentNode.getElement().equals(o)) {
                    if (previousNode != null) {
                        previousNode.setNext(currentNode.getNext()); // skips over removed element
                    } else {
                        head = head.getNext(); //if it is the first object, moves the head to next object.
                    }
                    return true;
                }
                previousNode = currentNode; // iterates
                currentNode = currentNode.getNext();
            }
        }
        return false;
    }



    @Override
    public boolean removeAll(Collection<?> c) {
        boolean removed = false;
        for (Object o : c) {
            if (remove(o)) {
                removed = true;
            }
        }
        return removed;
    }

    @Override
    public void clear() {
        head = null;

    }

    //Unused Methods
    @Override
    public Object[] toArray() {
        return new Object[0];
    } // Unused collections method

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    } // Unused collections method

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for(E e : c){
            add(e);
        }
        return true;
    }

}