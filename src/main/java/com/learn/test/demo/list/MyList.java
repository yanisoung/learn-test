package com.learn.test.demo.list;

import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
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

	/**
	 * 记录当前集合修改次数，该字段主要用于迭代器对比数据是否有修改
	 */
	private int modCount;

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
		return new MyItr();
	}

	@Override
	public Object[] toArray () {
		//使用新数组，不直接返回element，防止对element的数据干扰
		return Arrays.copyOf(element, size);
	}

	@Override
	public boolean add (E o) {
		//增加修改次数
		modCount++;

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
		//增加修改次数
		//todo 为什么修改时也要增加修改次数？白
		modCount++;

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
		//如果扩容1.5倍后容量还是比需要的最小的容量小，则使用需要最小容量
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
		//增加修改次数
		modCount++;

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
		//增加修改次数
		modCount++;

		Object[] src = c.toArray();
		//要添加的数组的长度
		int numb = src.length;
		//扩容校验：判断element的length是否足够存放c里的数据
		growCapacity(size + numb);
		//src: 数据源
		//srcPos: 从数据源哪个下标开始复制
		//element: 要复制到的数组
		//size：复制到数组时从哪个下标开始
		//numb：要复制的长度
		System.arraycopy(src, 0, element, size, numb);
		size += numb;
		//System.arraycopy 没有返回值，要判断是否复制成功，可以以是否有需要复制的个数为主
		return numb != 0;
	}

	@Override
	public boolean addAll (int index, Collection c) {
		//索引边界校验
		rangeCheck(index);

		//增加修改次数
		modCount++;

		Object[] src = c.toArray();
		//要添加的数组的长度
		int numb = src.length;
		//扩容校验：判断element的length是否足够存放c里的数据
		growCapacity(size + numb);
		//src: 数据源
		//srcPos: 从数据源哪个下标开始复制
		//element: 要复制到的数组
		//size：复制到数组时从哪个下标开始
		//numb：要复制的长度
		//将index后的数据复制到后面，中间空出来存放src数组的长度
		System.arraycopy(element, index, element, index + numb, size - index - 1);
		//从index开始复制src长度的个数
		System.arraycopy(src, 0, element, index, numb);
		size += numb;
		//System.arraycopy 没有返回值，要判断是否复制成功，可以以是否有需要复制的个数为主
		return numb != 0;
	}

	@Override
	public void clear () {
		for (int i = 0; i < size; i++) {
			//清除引用释放资源，如果当前元素没有再被引用，就会引起GC回收
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
		//索引边界校验
		rangeCheck(index);
		//增加修改次数
		modCount++;
		//扩容校验：判断element的length是否可以放下多一个元素
		growCapacity(size + 1);
		//src: 数据源
		//srcPos: 从数据源哪个下标开始复制
		//element: 要复制到的数组
		//size：复制到数组时从哪个下标开始
		//numb：要复制的长度
		//将index后的数据复制到后面，中间空出来存放src数组的长度
		System.arraycopy(this.element, index, this.element, index + 1, size - index - 1);
		this.element[index] = element;
		size += 1;
	}

	@Override
	public E remove (int index) {
		rangeCheck(index);
		//增加修改次数
		modCount++;
		Object oldValue = element[index];
		//计算要移动的元素个数：-index时减去的是个数，因为index是从0开始的，而size是统计的个数从1开始 与index相差了一个，所以需要-1
		int removeCount = size - index - 1;
		if (removeCount > 0) {
			System.arraycopy(element, index + 1, element, index, removeCount);
		}
		//移除一位元素时，则size-1，同时置为null，如果元素没有再被引用则会引起GC回收
		element[--size] = null;
		return (E)oldValue;
	}

	@Override
	public int indexOf (Object o) {
		if (size == 0) {
			return -1;
		}
		//为什么需要判null？因为这里比较时使用的equals，不判空否则会报nullPointE
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
	public List<E> subList (int fromIndex, int toIndex) {
		rangeCheck(fromIndex);
		rangeCheck(toIndex);
		//增加修改次数
		modCount++;

		List<E> result = new MyList<>();
		for (int i = fromIndex; i < toIndex; i++) {
			result.add((E)this.element[i]);
		}
		return result;
	}

	@Override
	public boolean retainAll (Collection c) {
		Objects.requireNonNull(c);
		return batchRemove(c, true);
	}

	/**
	 * 保留还是去除数据
	 *
	 * @param c          数据集合
	 * @param complement true 保留 false 不保留
	 * @return
	 */
	private boolean batchRemove (Collection c, Boolean complement) {
		boolean result = false;
		//循环element时的下标
		int r = 0;
		//存放有效数据的下标
		int w = 0;
		try {
			//判断element里的元素是否在c中存在，如果存在的话，就往前存一下
			for (; r < size; r++) {
				//循环取element的每个元素,判断元素是否存在要保留或是要去除的集合里
				//complement：true 保留，false 不保留
				//c.contains(this.element[r]) == complement，有四种情况需要处理：
				//1.complement = true,保留并且element包含(true)，则将数据往前移，保留数据
				//2.complement = true,保留并且element不包含(false)，则不做任何处理，不保留数据
				//3.complement = false,不保留并且element包含(true)，则不做任何处理，不保留数据
				//4.complement = false,不保留并且element不包含(false)，则将数据往前移，保留数据
				//以上4种情况，只有1和4需要处理，也就是当true==true,false==false时才处理
				if (c.contains(this.element[r]) == complement) {
					this.element[w++] = this.element[r];
				}
			}
		} finally {
			//防止出现异常时，数组数据受到影响
			//如果r!=size 就表示循环没有正常执行完成，出现了异常，
			// 这时候需要整理未循环到的数据，以及将不需要保留的数据清除
			if (r != size) {
				//将r索引之后未循环到的数据前移到 w后，w前的数据都是循环过需要保留的数据
				System.arraycopy(element, r, element, w, size - r);
				//重新赋值w：正常执行完后会清空w后的数据，所以这里也要赋值w，不要清除未循环到的数据，以免丢失
				w += size - r;
			}
			//w=size：说明没有数据做了去除，所以不需要处理
			if (w != size) {
				//增加修改次数
				modCount++;
				//将w后的数据都去除
				for (int i = size; i >= w; i--) {
					this.element[i] = null;
				}
				//重新计算数组的size，这里w不需要+1，是因为w包含了当前元素的个数以及下个接下来要存放元素的下标
				size = w;
				result = true;
			}
		}
		return result;
	}

	@Override
	public boolean removeAll (Collection c) {
		Objects.requireNonNull(c);
		return batchRemove(c, false);
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

	public class MyItr implements Iterator<E> {
		/**
		 * 下个元素的下标
		 */
		int cursor;

		/**
		 * 返回的最新元素的下标，默认-1
		 */
		int lastRet = -1;

		/**
		 * 集合操作次数，默认使用MyList的操作次数，该字段主要用来校验foreach时数组是否进行了修改
		 */
		int expectedModCount = modCount;

		@Override
		public boolean hasNext () {
			return cursor != size;
		}

		@Override
		public E next () {
			//结构性修改校验
			checkForComodification();

			int index = cursor;
			if (index >= size) {
				throw new NoSuchElementException();
			}
			Object[] element = MyList.this.element;
			if (index >= element.length) {
				throw new ConcurrentModificationException();
			}
			//cursor++：继续计算下个元素的下标
			cursor++;
			//lastRet = cursor：下个元素的下标其实就是当前要取的值的下标，取完值后就变成了最后一个返回的元素下标，
			// 所以直接进将下个元素的下标直接赋值最后一个返回的元素下标是可以的
			return (E)element[lastRet = index];

			//下面的写法就是上面写法的简化版
//			return (E)MyList.this.element[lastRet = cursor++];
		}

		/**
		 * 校验在foreach期间是否存在结构性修改
		 * 结构性修改包括：添加/插入/删除，如果只是修改某个元素内容则不属于结构性修改
		 */
		private void checkForComodification () {
			if (expectedModCount != modCount) {
				throw new ConcurrentModificationException();
			}
		}

		@Override
		public void remove () {
			//校验是否调用next接口
			if (lastRet < 0) {
				throw new IllegalStateException();
			}
			//结构性修改校验
			checkForComodification();
			//调用MyList的remove方法
			MyList.this.remove(lastRet);
			//cursor是下个元素的下标，lastRet是最后返回的下标，两者之间是差了1位，
			// 移除当前的元素后，后面的所有元素都会向前移动一位，也就是这些元素的索引都-1了，
			// 被移除元素后面一位元素也就重新移动到了当前移除元素的所有位置，也就是lastRet的位置是新的元素了
			// 而下个元素的索引位置也需要往前移动一位，因此：cursor = cursor -1 = lastRet
			cursor = lastRet;
			//因为元素被移除后就不存在这个元素对应的索引了，所以lastRet赋值为-1
			lastRet = -1;
			//重新赋值当前修改次数，与外部的修改次数保持一致
			expectedModCount = modCount;
		}
	}
}
