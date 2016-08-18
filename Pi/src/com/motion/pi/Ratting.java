/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.motion.pi;

import java.io.Serializable;


/**
 *
 * @author dipti
 */

public class Ratting implements  Serializable{
    
   
    private long idratting;
    

    private String rattingtype;

    /**
     * @return the idratting
     */
    public long getIdratting() {
        return idratting;
    }

    /**
     * @param idratting the idratting to set
     */
    public void setIdratting(long idratting) {
        this.idratting = idratting;
    }

    /**
     * @return the rattingtype
     */
    public String getRattingtype() {
        return rattingtype;
    }

    /**
     * @param rattingtype the rattingtype to set
     */
    public void setRattingtype(String rattingtype) {
        this.rattingtype = rattingtype;
    }
    
    
    
}
