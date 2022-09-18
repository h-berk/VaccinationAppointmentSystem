package hasanberk.vaccinationappointmentsystem.DataStructure;

public class Node<E> {
    public E element; //Element within the node
    public Node<E> next; //new node representing the next node

    Node(E element, Node<E> next) { //add element and next element
        this.element = element;
        this.next = next;
    }

    public E getElement()
    {
        return element;
    }

    public void setElement(E element)
    {
        this.element = element;
    }

    public Node<E> getNext()
    {
        return next;
    }

    public void setNext(Node<E> next)
    {
        this.next = next;
    }
}