package org.modularmc.utils;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author Caspar Norée Palm
 */
public final class ArrayCollection<T> implements Collection<T> {
	private final T[] elements;

	public ArrayCollection(T[] elements) {
		this.elements = elements;
	}
	
	@Override
	public boolean add(T arg0) {
		throw new ArrayStoreException("This collection is static and cannot be modified!");
	}
	@Override
	public boolean addAll(Collection<? extends T> arg0) {
		throw new ArrayStoreException("This collection is static and cannot be modified!");
	}
	@Override
	public void clear() {
		throw new ArrayStoreException("This collection is static and cannot be modified!");
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Object e) {
		for(int i = 0; i < elements.length;i++)
			if(elements.equals(e))
				return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(Collection<?> collection) {
		for(Object a : collection)
			if(!contains(a))
				return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return elements.length == 0;
	}
	
	public Iterator<T> iterator() {
		return new ArrayIterator<T>(elements);
	}

	@Override
	public boolean remove(Object arg0) {
		throw new ArrayStoreException("This collection is static and cannot be modified!");
	}
	
	@Override
	public boolean removeAll(Collection<?> arg0) {
		throw new ArrayStoreException("This collection is static and cannot be modified!");
	}
	
	@Override
	public boolean retainAll(Collection<?> arg0) {
		throw new ArrayStoreException("This collection is static and cannot be modified!");
	}
	
	@Override
	public int size() {
		return elements.length;
	}

	@Override
	public Object[] toArray() {
		return elements;
	}

	@Override
	public <T> T[] toArray(T[] arg) {
		System.arraycopy(elements, 0, arg, 0, Math.min(arg.length, elements.length));
		return arg;
	}
	
}
