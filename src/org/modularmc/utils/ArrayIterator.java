package org.modularmc.utils;

import java.util.Iterator;


/**
 * @author Caspar Norée Palm
 */
public class ArrayIterator<T> implements Iterator<T>{
	
	int pos;
	final T[] elements;
	
	public ArrayIterator(T[] elements) {
		this.elements = elements;
	}

	@Override
	public boolean hasNext() {
		return pos >= elements.length;
	}

	@Override
	public T next() {
		return elements[pos++];
	}

}
