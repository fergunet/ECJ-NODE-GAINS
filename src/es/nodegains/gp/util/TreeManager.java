/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nodegains.gp.util;

import ec.gp.GPTree;
import es.nodegains.gp.GPNodeGain;
import java.util.ArrayList;

/**
 *
 * @author pgarcia
 */
public class TreeManager {
    
    private static void addChildsToNodeVector(GPNodeGain n, ArrayList<GPNodeGain> v){
        v.add(n);
        
        if (n.children.length == 0)
            return;
        else
            for(int i = 0; i<n.children.length; i++)
                addChildsToNodeVector((GPNodeGain)n.children[i], v);
    }
    
    public static ArrayList<GPNodeGain> treeToNodeVector(GPTree tree){
    
        ArrayList<GPNodeGain> v = new ArrayList<GPNodeGain>();
        GPNodeGain root = (GPNodeGain) tree.child;
        addChildsToNodeVector(root, v);   
        return v;
    }
    
    public static ArrayList<Double> getGainsFromNodeVector(ArrayList<GPNodeGain> vector){
        ArrayList<Double> v = new ArrayList<Double>();
        for(int i = 0; i < vector.size(); i++){
                v.add(vector.get(i).getGain());
            }
        return v;
        
    }
    
    public static void setGainsToNodeVector(ArrayList<GPNodeGain> vector, ArrayList<Double> gains){
        for(int i = 0; i < vector.size(); i++){
               vector.get(i).setGain(gains.get(i));
            }        
    }
    
    public static ArrayList<Double> getGainsFromTree(GPTree tree){
        ArrayList<GPNodeGain> gnv = treeToNodeVector(tree);
        return getGainsFromNodeVector(gnv);        
    }
    
    public static void setGainsToTree(GPTree tree, ArrayList<Double> gains){
        setGainsToNodeVector(treeToNodeVector(tree), gains);       
    }
    
}
