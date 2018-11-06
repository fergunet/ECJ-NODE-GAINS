/*
  Copyright 2006 by Sean Luke
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
*/


package es.nodegains.gp.func;
import ec.app.regression.func.*;
import ec.*;
import ec.app.regression.*;
import ec.gp.*;
import ec.util.*;
import es.nodegains.gp.GPNodeGain;

/* 
 * X.java
 * 
 * Created: Wed Nov  3 18:26:37 1999
 * By: Sean Luke
 */

/**
 * @author Sean Luke
 * @version 1.0 
 */

public class X5 extends GPNodeGain
    {
    public String toString() { return this.stringGain()+"x5"; }

/*
  public void checkConstraints(final EvolutionState state,
  final int tree,
  final GPIndividual typicalIndividual,
  final Parameter individualBase)
  {
  super.checkConstraints(state,tree,typicalIndividual,individualBase);
  if (children.length!=0)
  state.output.error("Incorrect number of children for node " + 
  toStringForError() + " at " +
  individualBase);
  }
*/
    public int expectedChildren() { return 0; }

    public void eval(final EvolutionState state,
        final int thread,
        final GPData input,
        final ADFStack stack,
        final GPIndividual individual,
        final Problem problem)
        {
        RegressionData rd = ((RegressionData)(input));
        double[] c = ((es.nodegains.gp.Benchmarks)problem).currentValue;
        if (c.length >= 5)
            rd.x = ((es.nodegains.gp.Benchmarks)problem).currentValue[4];
        else rd.x = 0;
        rd.x = rd.x*this.getGain();
        }
    }



