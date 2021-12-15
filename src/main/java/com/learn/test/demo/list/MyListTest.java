package com.learn.test.demo.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * @author Bai
 * @date 2021/11/26 21:59
 */
public class MyListTest {

	public static void main (String[] args) {
		myListDemo();
//		listDemo();
	}

	public static void listDemo () {
		List<String> list = new ArrayList<>();
		list.add("2");
		list.add("1");
		list.add("1");
		list.add(null);
//		list.set(0, "12");
////		list.remove("1");
//		System.out.println(list.indexOf(null));
//		System.out.println(list.indexOf("1"));
//
////		list.clear();
//		List<String> strings = Lists.newArrayList("3", "4");
//		System.out.println(list.containsAll(strings));
//		list.removeAll(strings);
		for (String s : list) {
			list.addAll(Lists.newArrayList("44"));
		}
		Iterator<String> iterator = list.iterator();
//		list.addAll(1, strings);
//		while (iterator.hasNext()) {
//			iterator.next();
//			iterator.remove();
//		}
		System.out.println(list.toString());
	}

	public static void myListDemo () {
		MyList<String> myList = new MyList<>(0);
		myList.add("2");
		myList.add("1");
		myList.add("2");
		myList.add("3");
		myList.add("6");
		myList.add("4");
		myList.add(3, "5");
		myList.add(null);
		myList.remove("4");
		myList.remove(2);
//		myList.set(2,"30");
//		System.out.println(myList.indexOf(null));
//		System.out.println(myList.indexOf("4"));
//		System.out.println(myList.lastIndexOf("4"));
//		System.out.println(myList.lastIndexOf("11"));
//		System.out.println(myList.lastIndexOf(null));
//		myList.clear();
//		List<String> strings = Lists.newArrayList("4", "1");
//		System.out.println(myList.containsAll(strings));
//		myList.removeAll(strings);
//		System.out.println(myList.containsAll(strings));

//		MyList<Integer> myList1 = new MyList<>();
//		myList1.add(21);
//		myList1.add(22);
//		myList1.add(23);
//		myList.addAll(1, myList1);
//		myList.retainAll(Lists.newArrayList("4","5"));
//		for (String s : myList) {
//			myList.set(1,s);
//		}
		System.out.println(myList.toString());
		System.out.println(myList.size());
	}
}
