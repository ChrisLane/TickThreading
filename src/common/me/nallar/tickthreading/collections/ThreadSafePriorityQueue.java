package me.nallar.tickthreading.collections;

import java.util.PriorityQueue;

public class ThreadSafePriorityQueue<T> extends PriorityQueue<T> {
	@Override
	public synchronized T remove() {
		return super.remove();
	}

	@Override
	public synchronized boolean add(T t) {
		return super.add(t);
	}
}