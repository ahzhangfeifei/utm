package com.utm.workattendance.entity;

import com.utm.basic.entity.AbstractEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name = "workattence", schema = "", catalog = "workattendance")
public class WorkattenceEntity extends AbstractEntity {
    private String id;
    private String comedate;
    private String cometime;
    private String leavedate;
    private String leavetime;
    private String type;
    private String more;
    private String state;
    private String other1;
    private String other2;
    private TeacherEntity teacherByTeacherid;
    private StudentEntity studentByStudentid;

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "comedate")
    public String getComedate() {
        return comedate;
    }

    public void setComedate(String comedate) {
        this.comedate = comedate;
    }

    @Basic
    @Column(name = "cometime")
    public String getCometime() {
        return cometime;
    }

    public void setCometime(String cometime) {
        this.cometime = cometime;
    }

    @Basic
    @Column(name = "leavedate")
    public String getLeavedate() {
        return leavedate;
    }

    public void setLeavedate(String leavedate) {
        this.leavedate = leavedate;
    }

    @Basic
    @Column(name = "leavetime")
    public String getLeavetime() {
        return leavetime;
    }

    public void setLeavetime(String leavetime) {
        this.leavetime = leavetime;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "more")
    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }

    @Basic
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "other1")
    public String getOther1() {
        return other1;
    }

    public void setOther1(String other1) {
        this.other1 = other1;
    }

    @Basic
    @Column(name = "other2")
    public String getOther2() {
        return other2;
    }

    public void setOther2(String other2) {
        this.other2 = other2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkattenceEntity that = (WorkattenceEntity) o;

        if (comedate != null ? !comedate.equals(that.comedate) : that.comedate != null) return false;
        if (cometime != null ? !cometime.equals(that.cometime) : that.cometime != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (leavedate != null ? !leavedate.equals(that.leavedate) : that.leavedate != null) return false;
        if (leavetime != null ? !leavetime.equals(that.leavetime) : that.leavetime != null) return false;
        if (more != null ? !more.equals(that.more) : that.more != null) return false;
        if (other1 != null ? !other1.equals(that.other1) : that.other1 != null) return false;
        if (other2 != null ? !other2.equals(that.other2) : that.other2 != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (comedate != null ? comedate.hashCode() : 0);
        result = 31 * result + (cometime != null ? cometime.hashCode() : 0);
        result = 31 * result + (leavedate != null ? leavedate.hashCode() : 0);
        result = 31 * result + (leavetime != null ? leavetime.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (more != null ? more.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (other1 != null ? other1.hashCode() : 0);
        result = 31 * result + (other2 != null ? other2.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "teacherid", referencedColumnName = "id")
    public TeacherEntity getTeacherByTeacherid() {
        return teacherByTeacherid;
    }

    public void setTeacherByTeacherid(TeacherEntity teacherByTeacherid) {
        this.teacherByTeacherid = teacherByTeacherid;
    }

    @ManyToOne
    @JoinColumn(name = "studentid", referencedColumnName = "id")
    public StudentEntity getStudentByStudentid() {
        return studentByStudentid;
    }

    public void setStudentByStudentid(StudentEntity studentByStudentid) {
        this.studentByStudentid = studentByStudentid;
    }
}
