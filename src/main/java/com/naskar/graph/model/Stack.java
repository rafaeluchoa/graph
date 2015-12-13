package com.naskar.graph.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Stack<T> {
	
	private List<T> data = new ArrayList<T>();
	private HashSet<T> dataSet = new HashSet<T>();
	
	public Stack() {
		this.data = new ArrayList<T>();
		this.dataSet = new HashSet<T>();	
	}
	
	public Stack(List<T> list) {
		this.data = new ArrayList<T>(list);
		this.dataSet = new HashSet<T>();
		this.dataSet.addAll(list);
	}
	
	@SuppressWarnings("unchecked")
	public Stack<T> copy() {
		Stack<T> c = new Stack<T>();
		c.data = (List<T>) ((ArrayList<T>)data).clone();
		c.dataSet = (HashSet<T>) dataSet.clone();
		return c;
	}
	
	public Stack<T> concat(Stack<T> other) {
		Stack<T> copy = this.copy();
		copy.data.addAll(other.data);
		copy.dataSet.addAll(other.dataSet);
		return copy;
	}
	
	public void push(T t) { 
		data.add(t);
		dataSet.add(t);
	}
	
	public T pop() {
		T t = data.remove(data.size()-1);
		dataSet.remove(t);
		return t;
	}
	
	public T peek() { 
		return data.get(data.size()-1); 
	}
	
	public boolean contains(T t) {
		return dataSet.contains(t);
	}
	
	public boolean isEmpty() {
		return data.isEmpty();
	}
	
	public List<T> asList() {
		return data;
	}
	
	public int size() {
		return data.size();
	}
	
	@Override
	public String toString() {
		return this.data.toString();
	}
}