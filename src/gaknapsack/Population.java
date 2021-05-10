/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gaknapsack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Donal
 */
public class Population {
    ArrayList<Individual> population;
    int mutRate=40;
    int best = 0;
    
    public Population(int size, int problemLen){
        population = new ArrayList<>();
        for(int i=0;i<size;++i){
            population.add(new Individual(problemLen));
            //System.out.println(population.get(i).chromosome);
        }
    }
    
    public Individual cross(Individual p1, Individual p2){
        ArrayList<Byte> child = new ArrayList<>();
        Random r = new Random();
        for(int i = 0;i<GAKnapsack.ITEMS;++i){
            if(r.nextInt(2)==0){
                child.add(p1.chromosome.get(i));
            }else{
                child.add(p2.chromosome.get(i));
            }
        }
        if(r.nextInt(101) < mutRate){
            child.set(r.nextInt(GAKnapsack.ITEMS), (byte)r.nextInt(2));
        }
        return new Individual(child);
    }
    
    public void mutate(){
        Random r = new Random();
        for(Individual t : population)
        if(r.nextInt(101) < mutRate){
            int ind = r.nextInt(GAKnapsack.ITEMS);
            t.chromosome.set(ind, (byte)r.nextInt(2));
        }
    }
    
    public Individual tournament(int k){
        Individual best=null;
        Random r = new Random();
        for(int i=0;i<k;++i){
            int ind = r.nextInt(population.size());
            if(best == null)best = population.get(ind);
            if(population.get(ind).measureFitness() > best.measureFitness()){
                best = population.get(ind);
            }
        }
        //population.remove(best);
        return best;
    }
    
    public void select(){
        Collections.sort(population);
        best = population.get(0).measureFitness()>best?population.get(0).measureFitness():best;
        System.out.println("Fittest individual: "+population.get(0).measureFitness());
        System.out.println(population.get(0).chromosome);
        ArrayList<Individual> next = new ArrayList<>();
        Random r = new Random();
        for(int i = 0;i < GAKnapsack.K; ++i){
            next.add(population.get(i));
        }for(int i = 0;i < 2;++i){
            Individual n = tournament(5);
            if(next.contains(n))continue;
            next.add(n);
        }
        while(next.size() < GAKnapsack.N){
            int p1 = r.nextInt(GAKnapsack.N);
            int p2 = r.nextInt(GAKnapsack.N);
            if(p1 == p2)continue;
            next.add(cross(population.get(p1),population.get(p2)));
        }
        population = new ArrayList<>(next);
        //mutate();
    }
}
