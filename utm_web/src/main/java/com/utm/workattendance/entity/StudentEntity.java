package com.utm.workattendance.entity;

import com.utm.basic.entity.AbstractEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "student", schema = "", catalog = "workattendance")
public class StudentEntity extends AbstractEntity{
    private String id;
    private String code;
    private String name;
    private String sex;
    private Integer age;
    private String address;
    private String school;
    private String grade;
    private String clazz;
    private String familyphone;
    private String phone;
    private String registtime;
    private String more;
    private String state;
    private String other1;
    private String password;
    private Collection<WorkattenceEntity> workattencesById;

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
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "school")
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Basic
    @Column(name = "grade")
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Basic
    @Column(name = "clazz")
    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    @Basic
    @Column(name = "familyphone")
    public String getFamilyphone() {
        return familyphone;
    }

    public void setFamilyphone(String familyphone) {
        this.familyphone = familyphone;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "registtime")
    public String getRegisttime() {
        return registtime;
    }

    public void setRegisttime(String registtime) {
        this.registtime = registtime;
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
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentEntity that = (StudentEntity) o;

        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (age != null ? !age.equals(that.age) : that.age != null) return false;
        if (clazz != null ? !clazz.equals(that.clazz) : that.clazz != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (familyphone != null ? !familyphone.equals(that.familyphone) : that.familyphone != null) return false;
        if (grade != null ? !grade.equals(that.grade) : that.grade != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (more != null ? !more.equals(that.more) : that.more != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (other1 != null ? !other1.equals(that.other1) : that.other1 != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (registtime != null ? !registtime.equals(that.registtime) : that.registtime != null) return false;
        if (school != null ? !school.equals(that.school) : that.school != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (school != null ? school.hashCode() : 0);
        result = 31 * result + (grade != null ? grade.hashCode() : 0);
        result = 31 * result + (clazz != null ? clazz.hashCode() : 0);
        result = 31 * result + (familyphone != null ? familyphone.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (registtime != null ? registtime.hashCode() : 0);
        result = 31 * result + (more != null ? more.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (other1 != null ? other1.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "studentByStudentid")
    public Collection<WorkattenceEntity> getWorkattencesById() {
        return workattencesById;
    }

    public void setWorkattencesById(Collection<WorkattenceEntity> workattencesById) {
        this.workattencesById = workattencesById;
    }
}
