/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package info.androidhive.customlistviewvolley.model;

import java.io.Serializable;


/**
 *
 * @author dipti
 */

public class QuestionType implements Serializable{
   
  
    private long idqtype;
   
    
    private String qtype;

    /**
     * @return the idqtype
     */
    public long getIdqtype() {
        return idqtype;
    }

    /**
     * @param idqtype the idqtype to set
     */
    public void setIdqtype(long idqtype) {
        this.idqtype = idqtype;
    }

    /**
     * @return the qtype
     */
    public String getQtype() {
        return qtype;
    }

    /**
     * @param qtype the qtype to set
     */
    public void setQtype(String qtype) {
        this.qtype = qtype;
    }
    
    
    
}
