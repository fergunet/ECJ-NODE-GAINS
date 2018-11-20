/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nodegains.steadystate;

import ec.EvolutionState;
import ec.Individual;
import ec.SelectionMethod;
import ec.select.SelectDefaults;
import ec.steadystate.SteadyStateBSourceForm;
import ec.steadystate.SteadyStateEvolutionState;
import ec.util.Parameter;

/**
 *
 * @author pgarcia
 */
public class WorstSelection extends SelectionMethod implements SteadyStateBSourceForm
    {
    /** default base */
    public static final String P_FIRST = "worst-selection";

    public Parameter defaultBase()
        {
        return SelectDefaults.base().push(P_FIRST);
        }
    
    // I hard-code both produce(...) methods for efficiency's sake

    public int produce(final int subpopulation,
        final EvolutionState state,
        final int thread)
        {
        Individual[] oldinds = state.population.subpops[subpopulation].individuals;
        Individual worst = oldinds[0];
        int w = 0;
            for(int i = 0; i<oldinds.length;i++){
                Individual current = oldinds[i];
                //System.out.println(    "Actual      :"+current.fitness.fitnessToStringForHumans());
                if(worst.fitness.betterThan(current.fitness)){
                    //System.out.println("Es peor que :"+worst.fitness.fitnessToStringForHumans());
                    worst = current;
                    w = i;

                }
            }
        return w;
        }


    // I hard-code both produce(...) methods for efficiency's sake

    public int produce(final int min, 
        final int max, 
        final int start,
        final int subpopulation,
        final Individual[] inds,
        final EvolutionState state,
        final int thread) 
        {
            
        return -1111111; //This shouldn't be called never!
        
        }

    public void individualReplaced(final SteadyStateEvolutionState state,
        final int subpopulation,
        final int thread,
        final int individual)
        { return; }
    
    public void sourcesAreProperForm(final SteadyStateEvolutionState state)
        { return; }
    
    }
