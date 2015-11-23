package com.naskar.graph.game.skynet;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.naskar.graph.algorithms.DepthFirstSearchAlg;
import com.naskar.graph.model.Graph;

public class Player {

    public static void main(String args[]) {
    	new Player().run(System.in);
    }
    
    public void run(InputStream input) {
    	
    	Graph g = new Graph();
        DepthFirstSearchAlg alg = new DepthFirstSearchAlg();
        
        Scanner in = new Scanner(input);
        int N = in.nextInt(); // the total number of nodes in the level, including the gateways
        int L = in.nextInt(); // the number of links
        int E = in.nextInt(); // the number of exit gateways
        
        for (int i = 0; i < L; i++) {
            int N1 = in.nextInt(); // N1 and N2 defines a link between these nodes
            int N2 = in.nextInt();
            
            g.addEdge(String.valueOf(N1), String.valueOf(N2), 1);
            g.addEdge(String.valueOf(N2), String.valueOf(N1), 1);
        }

        List<Integer> maxPath = new ArrayList<Integer>(E);
        List<String> targets = new ArrayList<String>(E);
        for (int i = 0; i < E; i++) {
            int EI = in.nextInt(); // the index of a gateway node
            targets.add(String.valueOf(EI));
            maxPath.add(g.getSizeVertices());
        }

        // game loop
        
        while (true) {
        	long time = System.currentTimeMillis();
        	
            int SI = in.nextInt(); // The index of the node on which the Skynet agent is positioned this turn
            System.err.println("SI = " + SI);
            
            List<String> minorPath = null;
            for(int i = 0; i < targets.size(); i++) {
                
                List<String> path = alg.findPath(
                	g, String.valueOf(SI), targets.get(i), maxPath.get(i));
        		if(minorPath == null || path.size() < minorPath.size()) {
        		    minorPath = path;
        		}
        		
        		maxPath.set(i, path.size() 
        			+ g.getVertex(targets.get(i)).getAdjacenciesSize()
        			+ g.getVertex(String.valueOf(SI)).getAdjacenciesSize());
            }

            if(minorPath != null) {
                
                String v1 = minorPath.get(0);
                String v2 = minorPath.get(1);
                
                g.getVertex(v1).removeAdjacency(g.getVertex(v2));
                g.getVertex(v2).removeAdjacency(g.getVertex(v1));
                
                List<String> toRemove = new ArrayList<String>();
                for(int i = 0; i < targets.size(); i++) {
                	String ti = targets.get(i);
                	if(g.getVertex(ti).getAdjacenciesSize() == 0) {
                		toRemove.add(ti);
                		maxPath.remove(i);
                	}
                }
                targets.removeAll(toRemove);
                
                System.out.println(v1 + " " + v2);
            } else {
            	System.err.println(">>>> NOT FOUND");
            }
            
            System.err.println("Time = " + (System.currentTimeMillis() - time));
        }
    }
}
