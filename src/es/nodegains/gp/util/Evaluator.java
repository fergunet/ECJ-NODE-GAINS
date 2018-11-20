/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nodegains.gp.util;

import org.apache.commons.math3.linear.RealVector;
import com.dosilovic.hermanzvonimir.ecfjava.numeric.IFunction;
import ec.EvolutionState;
import ec.gp.GPIndividual;
import ec.gp.koza.KozaFitness;
import es.nodegains.gp.Benchmarks;
import java.util.ArrayList;

/**
 *
 * @author pgarcia
 */

    
    public class Evaluator<T extends RealVector> implements IFunction<T> {
        
    public Benchmarks benchs;
    public EvolutionState state;
    public GPIndividual ind;
    int threadNum;
    



    public void setArguments(EvolutionState state, GPIndividual ind, int threadNum, Benchmarks benchs){
        this.state = state;
        this.ind = ind;
        this.threadNum = threadNum;
        this.benchs = benchs;
    }
    @Override
    public double getValue(T point) {
        /*int    D = point.getDimension();
        double x;

        double value = 10 * D;
        for (int i = 0; i < D; i++) {
            x = point.getEntry(i);
            value += Math.pow(x, 2) - 10 * Math.cos(2 * Math.PI * x);
        }

        return value;*/
        ArrayList<Double> v = new ArrayList<Double>();
        for(int i = 0; i< point.getDimension();i++){
            v.add(point.getEntry(i));
        }
        TreeManager.setGainsToTree(ind.trees[0], v);
        KozaFitness kf = this.benchs.calculateFitness(state, ind, threadNum);
        return kf.standardizedFitness();
    }
}
    

