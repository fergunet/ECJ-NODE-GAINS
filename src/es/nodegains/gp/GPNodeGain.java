/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nodegains.gp;

import ec.gp.GPNode;

/**
 *
 * @author pgarcia
 */
public abstract class GPNodeGain extends GPNode{
    private double gain = 1.0;

    public double getGain() {
        return gain;
    }

    public void setGain(double gain) {
        this.gain = gain;
    }
    
    public String stringGain(){
        return "["+this.gain+"]";
    }
    
    
}
