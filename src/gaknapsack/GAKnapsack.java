/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gaknapsack;

import java.util.ArrayList;

/**
 *
 * @author Donal
 */
public class GAKnapsack {
    
    public static int MAXWEIGHT;
    public static int ITEMS;
    public static int[] weights={1,5,4,4,3,2,1,3,2,1,7,6,3,4,2};
    public static int[] values={5,8,7,7,5,2,3,1,7,0,5,7,9,5,4};
    
    public static int N = 250;
    public static int K = 10;
    
    public static void main(String[] args) {
        MAXWEIGHT = 30;
        ITEMS = 15;

        Population pop = new Population(N,ITEMS);
        for(int i = 1;i <= 100;++i){
            System.out.println(i);
            System.out.println("Generation "+i);
            pop.select();
            System.out.println("------------");
        }
        
        
        System.out.println("Best Result: "+pop.best);
    }
    
}
