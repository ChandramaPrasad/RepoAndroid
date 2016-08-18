//package com.motion.pi;
//
//
//import java.io.Serializable;
//import java.net.PasswordAuthentication;
//import java.util.Properties;
//
//import android.os.Message;
//import android.service.textservice.SpellCheckerService.Session;
//
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
///**
// *
// * @author balaram
// */
//public class SendEmailToAllBean implements Serializable{
//    
//    private String email;
//    private String fname;
//    private Integer classidref;
//    
//    public void reset() {
//        viewAdd = false;
//        viewDelete = false;
//        viewEdit = false;
//
//    }
//public void sendMailid(String eMail,String name){
//    email=eMail;
//    fname=name;
//}
//    public void onAdd() {
//        reset();
//        viewAdd = true;
//
//    }
//    public SendEmailToAllBean(){
//       // allStudentadmited_sendmail();
//    }
//    
//    private boolean viewDelete;
//    private boolean viewAdd;
//    private boolean viewEdit;
//    private String description;
//    private String studentid;
//    
//    
//    public void send_Email(){
//        Properties props = new Properties();
//    props.put("mail.smtp.user", "balaram01051980@gmail.com");
//    props.put("mail.smtp.host", "smtp.gmail.com");
//    props.put("mail.smtp.port", "25");
//    props.put("mail.debug", "true");
//    props.put("mail.smtp.auth", "true");
//    props.put("mail.smtp.starttls.enable", "true");
//    props.put("mail.smtp.EnableSSL.enable", "true");
//    props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//    props.setProperty("mail.smtp.socketFactory.fallback", "false");
//    props.setProperty("mail.smtp.port", "465");
//    props.setProperty("mail.smtp.socketFactory.port", "465");
//    Session session = Session.getInstance(props,
//            new javax.mail.Authenticator() {
//
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication("balaram01051980@gmail.com", "01051980balaram");
//                }
//            });
//    try {
//
//        Message message = new MimeMessage(session);
//        message.setFrom(new InternetAddress("balaram01051980@gmail.com"));
//        message.setRecipients(Message.RecipientType.TO,
//                InternetAddress.parse("balaram01051980@gmail.com"));
//        message.setSubject("SUBJECT_OF_MAIL");
//        message.setText("hiiiiiii");
//        Transport.send(message);
//
//        System.out.println("Done");
//
//    } catch (Exception e) {
//
//
//    }
//    }
//     public void sendEmail(String messages, String subject,String destmailid){
//         
//         //https://www.google.com/settings/security/lesssecureapps
//         
//         //https://accounts.google.com/DisplayUnlockCaptcha
//         
//         String to=destmailid;//change accordingly  
//  
//  //Get the session object  
//  Properties props = new Properties();  
//  props.put("mail.smtp.host", "smtp.gmail.com");  
// // props.put("mail.smtp.socketFactory.port", "465");  
//  //props.put("mail.smtp.socketFactory.class",             "javax.net.ssl.SSLSocketFactory");  
//  
//  props.put("mail.smtp.port", "587");  
//     props.put("mail.smtp.auth", "true");
//    props.put("mail.smtp.starttls.enable", "true");
//     props.put("mail.debug", "false");
//       props.put("mail.smtp.protocol", "smtps");
//  
//       /**
//        
//        * mail.smtp.auth=true
//mail.smtp.starttls.enable=true
//mail.smtp.auth.mechanisms=login
//mail.smtp.quitwait=false
//mail.debug = false
//mail.username=test@gmail.com
//mail.password=test
//mail.smtp.host=smtp.gmail.com
//mail.smtp.port=587
//mail.smtp.protocol=smtps
//        
//        **/
//        
//  Session session = Session.getDefaultInstance(props,  
//   new javax.mail.Authenticator() {  
//   protected PasswordAuthentication getPasswordAuthentication() {  
//   return new PasswordAuthentication("piapprequest@gmail.com","pi.12345");//change accordingly  
//   }  
//  });  
//   
//  //compose message  
//  try {  
//   MimeMessage message = new MimeMessage(session);  
//   message.setFrom(new InternetAddress("piapprequest@gmail.com"));//change accordingly  
//   message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
//   message.setSubject(subject);  
//   message.setText(messages);  
//     
//   //send message  
//   Transport.send(message);  
//  
//   System.out.println("message sent successfully");  
//   
//  } catch (MessagingException e) {throw new RuntimeException(e);}  
//   
//    }
//
//    /**
//     * @return the viewDelete
//     */
//    public boolean isViewDelete() {
//        return viewDelete;
//    }
//
//    /**
//     * @param viewDelete the viewDelete to set
//     */
//    public void setViewDelete(boolean viewDelete) {
//        this.viewDelete = viewDelete;
//    }
//
//    /**
//     * @return the viewAdd
//     */
//    public boolean isViewAdd() {
//        return viewAdd;
//    }
//
//    /**
//     * @param viewAdd the viewAdd to set
//     */
//    public void setViewAdd(boolean viewAdd) {
//        this.viewAdd = viewAdd;
//    }
//
//    /**
//     * @return the viewEdit
//     */
//    public boolean isViewEdit() {
//        return viewEdit;
//    }
//
//    /**
//     * @param viewEdit the viewEdit to set
//     */
//    public void setViewEdit(boolean viewEdit) {
//        this.viewEdit = viewEdit;
//    }
//
//    /**
//     * @return the listStudentadmited
//     */
//   
//    
//    
//    
//    
//    
//   
//    /**
//     * @return the description
//     */
//    public String getDescription() {
//        return description;
//    }
//
//    /**
//     * @param description the description to set
//     */
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    /**
//     * @return the studentid
//     */
//    public String getStudentid() {
//        return studentid;
//    }
//
//    /**
//     * @param studentid the studentid to set
//     */
//    public void setStudentid(String studentid) {
//        this.studentid = studentid;
//    }
//
//    /**
//     * @return the email
//     */
//    public String getEmail() {
//        return email;
//    }
//
//    /**
//     * @param email the email to set
//     */
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    /**
//     * @return the fname
//     */
//    public String getFname() {
//        return fname;
//    }
//
//    /**
//     * @param fname the fname to set
//     */
//    public void setFname(String fname) {
//        this.fname = fname;
//    }
//
//    /**
//     * @return the classidref
//     */
//    public Integer getClassidref() {
//        return classidref;
//    }
//
//    /**
//     * @param classidref the classidref to set
//     */
//    public void setClassidref(Integer classidref) {
//        this.classidref = classidref;
//    }
//    
//    
//}
