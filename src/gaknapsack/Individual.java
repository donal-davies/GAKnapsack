/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gaknapsack;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Donal
 */
public class Individual implements Comparable<Individual>{
    ArrayList<Byte> chromosome;
    
    public Individual(ArrayList<Byte> c){
        chromosome = new ArrayList<>(c);
    }
    
    public Individual(int s){
        chromosome = new ArrayList<>();
        Random rand = new Random();
        for(int i = 0;i<s;++i){
            chromosome.add((byte)rand.nextInt(2));
        }
    }
    
    public int measureFitness(){
        int weightTotal = 0;
        int curVal = 0;
        
        for(int i = 0; i < GAKnapsack.ITEMS;++i){
            if(chromosome.get(i)==1){
                weightTotal += GAKnapsack.weights[i];
                if(weightTotal <= GAKnapsack.MAXWEIGHT){
                    curVal += GAKnapsack.values[i];
                }else{
                    break;
                }
            }
        }
        //System.out.println(curVal + " " + weightTotal);
        return curVal;
    }

    @Override
    public int compareTo(Individual o) {
        return o.measureFitness()-measureFitness();
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof Individual){
            return chromosome.equals(((Individual)o).chromosome);
        }
        return false;
    }
}
