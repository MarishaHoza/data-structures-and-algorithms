package linkedList;

import org.apache.commons.math3.exception.OutOfRangeException;

import java.util.NoSuchElementException;

public class LinkedList<T> {

    Node<T> head = null;

    public void insert(T value){
        // insert into start
        Node<T> newNode = new Node<>(value, this.head);
        this.head = newNode;
    }

    public void append(T value){
        Node<T> current = head;
        // iterate to the end of the list
        while (current.next != null) {
            current = current.next;
        }
        Node<T> newNode = new Node<>(value, null);
        current.next = newNode;
    }

    public void insertBefore(T target, T value){
        Node<T> current = head;

        // check head
        if ( current.val.equals(target) ) {
            this.insert(value);
            return;
        }

        // iterate until the NEXT value is the target value
        // make sure to stop at the end
        while ( current != null ) {
            if ( current.next.val.equals(target) ){
                break;
            } else {
                current = current.next;
            }
        }

        if ( current == null ) {
            // some type of error should be thrown here
            throw new NoSuchElementException("The target value was not present in the list");
        } else {
            Node<T> newVal = new Node<>(value, current.next);
            current.next = newVal;
        }
    }

    public void insertAfter (T target, T value){
        Node<T> current = head;

        // check head
        if ( current.val.equals(target) ) {
            Node<T> newVal = new Node<>(value, current.next);
            current.next = newVal;
            return;
        }

        // iterate until the CURRENT value is the target value
        // make sure to stop at the end
        while ( current != null ) {
            if ( current.val.equals(target) ){
                break;
            } else {
                current = current.next;
            }
        }

        if ( current == null ) {
            // some type of error should be thrown here
            throw new NoSuchElementException("The target value was not present in the list");
        } else {
            Node<T> newVal = new Node<>(value, current.next);
            current.next = newVal;
        }
    }

    public T remove(T value) {
        Node<T> current = head;
        // check head
        if ( current.val.equals(value) ){
            Node<T> deletedNode = current;
            this.head = current.next;
            return deletedNode.val;
        }

        // check rest of list
        while ( current.next != null ){
            if ( current.next.val.equals(value) ){
                Node<T> deletedNode = current.next;
                current.next = current.next.next;
                return deletedNode.val;
            } else {
                current = current.next;
            }
        }

        return null;
    }

    public boolean includes(T target){
        // return true if value is included in the linked list
        Node<T> current = head;
        while ( current != null ) {
            if ( current.val.equals(target) ) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public T kthFromEnd(int k){
        int counter = 0;
        Node<T> current = this.head;
        Node<T> currentKFromEnd = this.head;

        if (k < 0){
            throw new NoSuchElementException("kthFromEnd must be called with a positive integer");
        }

        while ( current.next != null ) {
            counter ++;
            if ( counter > k ) {
                currentKFromEnd = currentKFromEnd.next;
            }
            current = current.next;
        }

        if ( counter < k ) {
            throw new NoSuchElementException("kthFromEnd must be called with an integer less than the length of the list");
        } else {
            return currentKFromEnd.val;
        }
    }

    public String toString(){
        // return a string of all things in the list
        String allItems = "List: ";
        Node<T> current = head;

        while ( current != null ) {
            if ( current.next != null ) {
                allItems += (current.val + ", ");
                current = current.next;
            } else {
                allItems += current.val;
                current = current.next;
            }
        }

        return allItems;
    }

    public static LinkedList mergeLists(LinkedList one, LinkedList two){
        Node current1 = one.head;
        Node current2 = two.head;

        while ( current1.next != null && current2.next != null ) {
            Node next1 = current1.next;
            Node next2 = current2.next;
            current2.next = current1.next;
            current1.next = current2;
            current1 = next1;
            current2 = next2;
        }
        if ( current2.next == null ) {
            current2.next = current1.next;
            current1.next = current2;
        } else {
            current1.next = current2;
        }
        return one;
    }

}
