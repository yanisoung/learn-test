package com.learn.test.demo.list;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

/**
 * 实现自己的集合
 *
 * @author Bai
 * @date 2021/11/24 21:03
 */
public class MyList<E> implements List<E> {
	/**
	 * 存储数据的元素
	 */
	private Object[] element;

	/**
	 * 初始容量
	 */
	private Integer DEFAULT_CAPACITY = 10;

	/**
	 * 最大容量
	 */
	private Integer MAX_CAPACITY = Integer.MAX_VALUE - 8;

	/**
	 * 当前数组存放的个数（element.length 代表的是数组的容量，也就是数组可以存多少个数据）
	 */
	private int size;

	public MyList () {
		this.element = new Object[] {};
	}

	public MyList (Integer capacity) {
		if (capacity < 0) {
			throw new RuntimeException();
		}
		if (capacity == 0) {
			this.element = new Object[] {};
		} else {
			this.element = new Object[capacity];
		}
	}

	@Override
	public int size () {
		return size;
	}

	@Override
	public boolean isEmpty () {
		return size < 1;
	}

	@Override
	public boolean contains (Object o) {
		return indexOf(o) >= 0;
	}

	@Override
	public Iterator iterator () {
		return null;
	}

	@Override
	public Object[] toArray () {
		//使用新数组，不直接返回element，防止对element的数据干扰
		return Arrays.copyOf(element, size);
	}

	@Override
	public boolean add (E o) {
		//check是否需要对数组进行扩容，并执行扩容
		//为什么要传入size+1？因为size是当前数组存的数据个数，在加入新的数据时，需要判断数组是否有足够的长度来存放新加入的数据
		growCapacity(size + 1);
		//加入数据o前，element的size可以看作是n,最后一个数据的下标是数组长度-1，也就是n-1
		//加入数据o后：
		// 1.element的长度会加1，新size也就是n+1
		// 2.数组长度+1后，o会被放到数组最后一位（也就是+1的位置上），o所处的下标是原数组最后一个下标+1，也就是n（n-1+1）
		element[size++] = o;
		return true;
	}

	/**
	 * 校验数组的容量是否满足，如果不满足按照以下规则扩容：
	 * 1.首次扩容时使用初始容量
	 * 2.非首次时，扩容按照原容量的1.5倍进行扩容
	 * 3.进行边界校验，容量最大不超过Integer.Max
	 *
	 * @param needMinCapacity 需要的最小容量
	 */
	private void growCapacity (int needMinCapacity) {
		//当前数组的容量不够时，进行扩容
		if (element.length - needMinCapacity > 0) {
			return;
		}
		//首次扩容使用默认容量
		if (DEFAULT_CAPACITY - needMinCapacity > 0) {
			needMinCapacity = DEFAULT_CAPACITY;
		}
		//原容量1.5倍库容
		int newMinCapacity = element.length + element.length >> 1;
		if (newMinCapacity - needMinCapacity > 0) {
			needMinCapacity = newMinCapacity;
		}
		//校验扩容边界
		if (needMinCapacity > MAX_CAPACITY) {
			needMinCapacity = Integer.MAX_VALUE;
		}
		this.element = Arrays.copyOf(element, needMinCapacity);
	}

	@Override
	public boolean remove (Object o) {
		if (this.size == 0) {
			return false;
		}
		//单独判断null，是为了防止空指针
		if (null == o) {
			for (int i = 0; i < size; i++) {
				if (element[i] == null) {
					fastRemove(i);
					return true;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (o.equals(this.element[i])) {
					fastRemove(i);
					return true;
				}
			}
		}
		return false;
	}

	private void fastRemove (int index) {
		//移除某个数据时，其实就是将要移除的数据之后的所有数据都向前移动一位
		//要移动的数据个数：
		// size是目前存放的所有数据的总数，index是数据所在数组中的下标，
		//size-index 减去不需要移动的个数，因为index是从0开始的，所有个数要多减去1
		int removeCount = size - index - 1;
		//无需移动
		if (removeCount < 1) {
			return;
		}
		//src：源数组
		// srcPos：开始复制的下标
		// dest：目标数组
		// destPos：目标数组起始的下标
		// length：要移动的个数
		System.arraycopy(element, index + 1, element, index, removeCount);
		//去除一个数据，size要-1，数组最后一位要置为空
		element[--size] = null;
	}

	@Override
	public boolean addAll (Collection c) {
		return false;
	}

	@Override
	public boolean addAll (int index, Collection c) {
		return false;
	}

	@Override
	public void clear () {
		for (int i = 0; i < size; i++) {
			this.element[i] = null;
		}
	}

	@Override
	public E get (int index) {
		rangeCheck(index);
		return (E)this.element[index];
	}

	@Override
	public E set (int index, E element) {
		rangeCheck(index);
		Object oldElement = this.element[index];
		this.element[index] = element;
		return (E)oldElement;
	}

	@Override
	public void add (int index, E element) {

	}

	@Override
	public E remove (int index) {
		rangeCheck(index);

		Object oldValue = element[index];
		int removeCount = size - index - 1;
		if (removeCount > 0) {
			System.arraycopy(element, index + 1, element, index, removeCount);
		}
		element[--size] = null;
		return (E)oldValue;
	}

	@Override
	public int indexOf (Object o) {
		if (size == 0) {
			return -1;
		}
		if (null == o) {
			for (int i = 0; i < size; i++) {
				if (this.element[i] == null) {
					return i;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (o.equals(this.element[i])) {
					return i;
				}
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf (Object o) {
		int lastIndex = -1;
		if (null == o) {
			for (int i = 0; i < size; i++) {
				if (null == this.element[i]) {
					lastIndex = i;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (o.equals(this.element[i])) {
					lastIndex = i;
				}
			}
		}
		return lastIndex;
	}

	@Override
	public ListIterator listIterator () {
		return null;
	}

	@Override
	public ListIterator listIterator (int index) {
		return null;
	}

	@Override
	public List subList (int fromIndex, int toIndex) {
		return null;
	}

	@Override
	public boolean retainAll (Collection c) {
		return false;
	}

	@Override
	public boolean removeAll (Collection c) {
		for (Object o : c) {
			while (contains(o)) {
				remove(o);
			}
		}
		return true;
	}

	@Override
	public boolean containsAll (Collection c) {
		//方法一：最简单
		for (Object o : c) {
			if (!contains(o)) {
				return false;
			}
		}
		return true;

		//方法二：迭代器处理
//		Iterator iterator = c.iterator();
//		while (iterator.hasNext()) {
//			Object next = iterator.next();
//			if (contains(next)) {
//				continue;
//			}
//			return false;
//		}
//		return true;
	}

	@Override
	public Object[] toArray (Object[] a) {
		return null;
	}

	/**
	 * 边界校验
	 *
	 * @param index
	 */
	public void rangeCheck (int index) {
		if (index >= size || index < 0) {
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	@Override
	public String toString () {
		if (size == 0) {
			return "[]";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		int nullCnt = 0;
		for (int i = 0; i < size; i++) {
			if (null == this.element[i]) {
				nullCnt++;
			}
		}
		if (Objects.equals(nullCnt, size)) {
			return "[]";
		}
		for (int i = 0; i < size; i++) {
			if (i == size - 1) {
				sb.append(element[i]).append("]");
			} else {
				sb.append(element[i]).append(", ");
			}
		}
		return sb.toString();
	}
}
