/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nodegains.gp.util;

import com.dosilovic.hermanzvonimir.ecfjava.metaheuristics.sa.ISimulatedAnnealing;
import com.dosilovic.hermanzvonimir.ecfjava.metaheuristics.sa.SimpleSA;
import com.dosilovic.hermanzvonimir.ecfjava.metaheuristics.sa.cooling.GeometricCoolingSchedule;
import com.dosilovic.hermanzvonimir.ecfjava.metaheuristics.sa.cooling.ICoolingSchedule;
import com.dosilovic.hermanzvonimir.ecfjava.models.mutations.IMutation;
import com.dosilovic.hermanzvonimir.ecfjava.models.mutations.RealVectorGaussianMutation;
import com.dosilovic.hermanzvonimir.ecfjava.models.mutations.adapters.CopyOnMutationAdapter;
import com.dosilovic.hermanzvonimir.ecfjava.models.problems.FunctionMinimizationProblem;
import com.dosilovic.hermanzvonimir.ecfjava.models.problems.IProblem;
import com.dosilovic.hermanzvonimir.ecfjava.models.solutions.vector.BoundedRealVector;
import com.dosilovic.hermanzvonimir.ecfjava.models.solutions.vector.RealVector;
import com.dosilovic.hermanzvonimir.ecfjava.numeric.IFunction;
import com.dosilovic.hermanzvonimir.ecfjava.numeric.RastriginFunction;
import ec.EvolutionState;
import ec.gp.GPIndividual;
import es.nodegains.gp.Benchmarks;
import java.util.ArrayList;

/**
 *
 * @author pgarcia
 */
public class SARunner {
    
    public static ArrayList<Double> runSA(EvolutionState state, GPIndividual ind, int threadNum, Benchmarks benchs) {
        
        //int NUMBER_OF_COMPONENTS = 30;
        double MIN_COMPONENT_VALUE = 0;
        double MAX_COMPONENT_VALUE = 1;
        double DESIRED_PENALTY = 0;
        double DESIRED_PRECISION = 1e-3;
        int OUTER_ITERATIONS = 40;
        double OUTER_INITIAL_TEMP = 1000;
        double OUTER_FINAL_TEMP = 1e-4;
        int INNER_ITERATIONS = 10;
        double INNER_INITIAL_TEMP = 1000;
        double INNER_FINAL_TEMP = 1e-4;
        double MUTATION_PROBABILITY = 0.2;
        boolean FORCE_MUTATION = true;
        double MUTATION_SIGMA = 0.2;

        Evaluator<RealVector> benchmarks = new Evaluator<>();
        benchmarks.setArguments(state, ind, threadNum, benchs );
        IProblem<RealVector> problem = new FunctionMinimizationProblem<>(benchmarks);

        IMutation<RealVector> mutation = new CopyOnMutationAdapter<>(new RealVectorGaussianMutation<>(
                MUTATION_PROBABILITY, FORCE_MUTATION, MUTATION_SIGMA
        ));

        ICoolingSchedule outerCoolingSchedule = new GeometricCoolingSchedule(
                OUTER_ITERATIONS, OUTER_INITIAL_TEMP, OUTER_FINAL_TEMP
        );

        ICoolingSchedule innerCoolingSchedule = new GeometricCoolingSchedule(
                INNER_ITERATIONS, INNER_INITIAL_TEMP, INNER_FINAL_TEMP
        );

        ISimulatedAnnealing<RealVector> simulatedAnnealing = new SimpleSA<>(
                DESIRED_PENALTY,
                DESIRED_PRECISION,
                problem,
                mutation,
                outerCoolingSchedule,
                innerCoolingSchedule
        );

        
        //initialSolution.randomizeValues();
        ArrayList<Double> initialValues = TreeManager.getGainsFromTree(ind.trees[0]);
        RealVector initialSolution = new BoundedRealVector(initialValues.size(), MIN_COMPONENT_VALUE, MAX_COMPONENT_VALUE);
        
        for(int i = 0; i<initialValues.size();i++){
            initialSolution.setValue(i, initialValues.get(i));
            //System.out.print(initialValues.get(i)+" ");
        }
        //System.out.println();
        
        simulatedAnnealing.run(initialSolution);
        RealVector bestRV= simulatedAnnealing.getBestSolution();
        ArrayList<Double> bestSol = new ArrayList<Double>();
        for(int i = 0; i<bestRV.getSize();i++){
            bestSol.add(bestRV.getValue(i));
            
        }
        
        /*for(Double d:bestSol)
            System.out.print(d+" ");
        System.out.println();*/
        return bestSol;
    }
}
