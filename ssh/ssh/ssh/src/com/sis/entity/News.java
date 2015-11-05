package com.sis.entity;
// default package



/**
 * News entity. @author MyEclipse Persistence Tools
 */

public class News  implements java.io.Serializable {


    // Fields    

     private Integer newsId;
     private String engConx;
     private String chiConx;


    // Constructors

    /** default constructor */
    public News() {
    }

    
    /** full constructor */
    public News(String engConx, String chiConx) {
        this.engConx = engConx;
        this.chiConx = chiConx;
    }

   
    // Property accessors

    public Integer getNewsId() {
        return this.newsId;
    }
    
    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getEngConx() {
        return this.engConx;
    }
    
    public void setEngConx(String engConx) {
        this.engConx = engConx;
    }

    public String getChiConx() {
        return this.chiConx;
    }
    
    public void setChiConx(String chiConx) {
        this.chiConx = chiConx;
    }
 
}