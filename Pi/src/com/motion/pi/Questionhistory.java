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


public class Questionhistory implements  Serializable{
    
    
    
   
    private Integer idquestion;
    

    private String questiondetails;
    
  
    private String aboutmyquestion;
    
  
    private String createddate;
    
   
  
    private Integer quserid;
    
   
    private String qusername;
     
    
   
    private QuestionType qtypeid;

    

     public Questionhistory() {
    }

    public Questionhistory(Integer idquestion) {
        this.idquestion = idquestion;
    }

    public Questionhistory(Integer idquestion, String createddate) {
        this.idquestion = idquestion;
        this.createddate = createddate;
    }

    public Integer getIdquestion() {
        return idquestion;
    }

    public void setIdquestion(Integer idquestion) {
        this.idquestion = idquestion;
    }

    public String getQuestiondetails() {
        return questiondetails;
    }

    public void setQuestiondetails(String questiondetails) {
        this.questiondetails = questiondetails;
    }

    public String getAboutmyquestion() {
        return aboutmyquestion;
    }

    public void setAboutmyquestion(String aboutmyquestion) {
        this.aboutmyquestion = aboutmyquestion;
    }

    public String getCreateddate() {
        return createddate;
    }

    public void setCreateddate(String createddate) {
        this.createddate = createddate;
    }

    
   
 
    public QuestionType getQtypeid() {
        return qtypeid;
    }

    public void setQtypeid(QuestionType qtypeid) {
        this.qtypeid = qtypeid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idquestion != null ? idquestion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Questionhistory)) {
            return false;
        }
        Questionhistory other = (Questionhistory) object;
        if ((this.idquestion == null && other.idquestion != null) || (this.idquestion != null && !this.idquestion.equals(other.idquestion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dipti.Questionhistory[ idquestion=" + idquestion + " ]";
    }

    /**
     * @return the quserid
     */
    public Integer getQuserid() {
        return quserid;
    }

    /**
     * @param quserid the quserid to set
     */
    public void setQuserid(Integer quserid) {
        this.quserid = quserid;
    }

    /**
     * @return the qusername
     */
    public String getQusername() {
        return qusername;
    }

    /**
     * @param qusername the qusername to set
     */
    public void setQusername(String qusername) {
        this.qusername = qusername;
    }

    
    
    
}
