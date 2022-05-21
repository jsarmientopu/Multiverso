package structure;

import java.util.*;

public class ArrayList<T> {
	
	protected Node head;
	protected int size;
	
	public ArrayList() {
		Node newHead = new Node();
		this.head = newHead;
		this.size = 0;
	}
	
	public ArrayList(String p1, String p2, String p3, String p4) {
		
		Node newHead = new Node(p1);
		Node newNode0 = new Node(p2);
		newHead.setNext(newNode0);
		Node newNode1 = new Node(p3);
		newNode0.setNext(newNode1);
		Node newNode2 = new Node(p4);
		newNode1.setNext(newNode2);
		
		this.head = newHead;
		this.size = 4;
		
	}
	
	public boolean isEmpty( ) {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	public void checkIndex(int index) {
		if((index >= size)||(index < 0)) {
			throw new IndexOutOfBoundsException("index = " + index + "  size = " + size);
		}
	}
	
	public T get( int index ) {
		checkIndex( index);
		Node current = head;
		for(int i = 0;i<index;i++) {
			current = current.getNext();
		}
		return (T)current.getData();	
	}
	
	public int indexOf(T e) {
		Node current = head;
		for(int i = 0; i < size; i++) {
			if(current.getData().equals(e)) {
				return i;
			}
			current = current.getNext();
		}
		return -1;
	}
	
	public T remove( int index ) {
		checkIndex(index);
		Node current = head;
		T e = null;
		for(int i = 0; i < index ; i++) {
			current = current.getNext();
		}
		e = (T) current.getNext().getData();
		current.setNext(current.getNext().getNext());
		size -= 1;
		return e;
	}
	
	public void add(int index, T e) {
		Node newNode = new Node(e);
		Node current = head;
		for(int i = 0 ;i < index; i++ ) {
			current = current.getNext();
		}
		newNode.setNext(current.getNext());
		current.setNext(newNode);
		size += 1;
	}
	
	public void add(T e) {
		Node newNode = new Node(e);
		if(isEmpty()) {
			head = newNode;
			System.out.println("Set");
		}else {
			Node current = head;
			for(int i = 0 ;i < size-1; i++ ) {
				current = current.getNext();
			}
			current.setNext(newNode);
		}
		size += 1;
	}
	
	public String toString() {
		String message = "[";
		Node current = head;
		for(int i = 0 ; i< size; i++) {
			message += current.getData() + ",";
			current = current.getNext();
		}
		message = message.substring(0, message.length()-1);
		message += "]";
		return message;
	}
	
}
