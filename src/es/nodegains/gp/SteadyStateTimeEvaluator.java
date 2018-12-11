/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nodegains.gp;

import ec.EvolutionState;
import ec.Individual;
import ec.steadystate.SteadyStateEvaluator;
import ec.util.Parameter;

/**
 *
 * @author pgarcia
 */
public class SteadyStateTimeEvaluator extends SteadyStateEvaluator {

    public static final String P_RUNTIME = "runtime";
    public long initTime;
    long runtime;

    public void setup(final EvolutionState state, final Parameter base) {
        super.setup(state, base);

        runtime = state.parameters.getLong(base.push(P_RUNTIME), null, -1);
        if (runtime <= 0) {
            state.output.fatal("ERROR in SteadyStateTimeEvaluator.java: Parameter " + P_RUNTIME + " must be set and higher than 0");
        }
        this.initTime = -1;
    }

    @Override
    public boolean runComplete(final EvolutionState state, final Individual ind) {
        if (ind.fitness.isIdealFitness()) {
            return true;
        }

        if (initTime < 0) {
            this.initTime = System.currentTimeMillis();
        }
        long actual = System.currentTimeMillis();
        if ((actual - initTime) > runtime) {
            return true;
        } else {
            return false;
        }
    }

}
