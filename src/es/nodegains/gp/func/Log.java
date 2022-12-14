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
 * Log.java
 * 
 * Created: Wed Nov  3 18:26:37 1999
 * By: Sean Luke
 */

/**
 * @author Sean Luke
 * @version 1.0 
 */

public class Log extends GPNodeGain
    {
    private static final long serialVersionUID = 1;

    public String toString() { return this.stringGain()+"rlog"; }

/*
  public void checkConstraints(final EvolutionState state,
  final int tree,
  final GPIndividual typicalIndividual,
  final Parameter individualBase)
  {
  super.checkConstraints(state,tree,typicalIndividual,individualBase);
  if (children.length!=1)
  state.output.error("Incorrect number of children for node " + 
  toStringForError() + " at " +
  individualBase);
  }
*/
    public int expectedChildren() { return 1; }

    public void eval(final EvolutionState state,
        final int thread,
        final GPData input,
        final ADFStack stack,
        final GPIndividual individual,
        final Problem problem)
        {
        RegressionData rd = ((RegressionData)(input));

        children[0].eval(state,thread,input,stack,individual,problem);
        rd.x = (rd.x == 0.0 ? 0.0 : /*Strict*/Math.log(/*Strict*/Math.abs(rd.x)));
        rd.x = rd.x*this.getGain();
        }
    }



