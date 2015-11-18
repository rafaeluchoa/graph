package com.naskar.graph.model;

import java.util.function.Function;

public interface Iterator {
	
	void iterate(Vertex root, Function<Vertex, Boolean> f);

}
