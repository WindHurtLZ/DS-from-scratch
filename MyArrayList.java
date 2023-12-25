import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * The type My array list.
 * @param <E> the type parameter
 * @author WindHurtLZ
 */
public class MyArrayList<E> {

    // Array to store
    private E[] data;

    // record current element size
    private int size;

    // default capacity set to be 1
    private static final int INIT_CAP = 1;

    public MyArrayList() {
        this(INIT_CAP);
    }

    @SuppressWarnings("unchecked")
    public MyArrayList(int initCapacity) {
        data = (E[]) new Object[initCapacity];
        size = 0;
    }

    /**** Create(ADD) ****/

    public void add(int index, E e) {

        checkPositionIndex(index);

        if (data.length == size) {
            resize(data.length * 2);
        }

        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = e;
        size++;
    }


    public void addLast(E e) {
        if (data.length == size) {
            resize(data.length * 2);
        }

        data[size] = e;
        size++;
    }

    /**** Update ****/
    public E set(int index, E e) {
        checkElementIndex(index);

        E oldVal = data[index];
        data[index] = e;

        return oldVal;
    }

    /**** Retrieve ****/
    public E get(int index) {
        checkElementIndex(index);
        return data[index];
    }

    /**** Delete ****/
    public E remove(int index) {
        checkElementIndex(index);

        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        E delVal = data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        data[size - 1] = null;
        size--;

        return  delVal;
    }

    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        // data.length hold the space we already allocate in the heap, we manage our own ArrayList using size
        if (size < data.length / 4) {
            resize(data.length / 2);
        }

        E delVal = data[size - 1];
        data[size - 1] = null;
        size--;

        return  delVal;
    }

    /**** Public Method ****/
    public int getSize() {
        return  size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    /**** Private Method ****/
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    // !In most programming languages and libraries, it is standard and expected behavior to allow new elements to be inserted at the end of a list. For a list with 5 elements (indexed from 0 to 4), index 5 is indeed the next position outside the list, but it is also considered a valid insertion point at the end of the list. Inserting an element at this position will add the new element to the end of the list, increasing its size to 6 elements.
    //
    //This design allows lists or arrays to grow dynamically, making the addition of elements at the end of the sequence a simple and intuitive operation. If the insertion at index 5 were considered an error, it would not be possible to add new elements to the end of the list, limiting the flexibility and utility of dynamic data structures.
    //
    //Therefore, the existence of the checkPositionIndex method is to ensure that when performing an insertion operation, even at the end of the list, the index is valid and does not throw an error due to attempting to insert elements outside the sequence.
    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void resize(int newCap) {
        if (size > newCap) return;
        // create newCap array
        E[] newData = (E[]) new Object[newCap];
        // move from old to new array
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }
}
