package com.sys.bean;
import java.sql.Date;

public class Course {
    private String cno;
    private String cname;
    private Date csdate;
    private Date cedate;

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Date getCsdate() {
        return csdate;
    }

    public void setCsdate(Date csdate) {
        this.csdate = csdate;
    }

    public Date getCedate() {
        return cedate;
    }

    public void setCedate(Date cedate) {
        this.cedate = cedate;
    }
}
