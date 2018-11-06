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
    private Double gain = 1.0;

    public Double getGain() {
        return gain;
    }

    public void setGain(Double gain) {
        this.gain = gain;
    }
    
    public String stringGain(){
        return "["+this.gain+"]";
    }
    
    
}
