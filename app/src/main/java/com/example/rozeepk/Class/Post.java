package com.example.rozeepk.Class;

public class Post {
    String id,age,careerlevel,city,department,
            eduction,gender,img,industry,jobdesc,
            jobexp,jobtitle,jobtype,post_date,post_valid_date;

    public Post(String id, String age, String careerlevel, String city, String department, String eduction, String gender, String img,
                String industry, String jobdesc, String jobexp, String jobtitle, String jobtype, String post_date, String post_valid_date) {
        this.id = id;
        this.age = age;
        this.careerlevel = careerlevel;
        this.city = city;
        this.department = department;
        this.eduction = eduction;
        this.gender = gender;
        this.img = img;
        this.industry = industry;
        this.jobdesc = jobdesc;
        this.jobexp = jobexp;
        this.jobtitle = jobtitle;
        this.jobtype = jobtype;
        this.post_date = post_date;
        this.post_valid_date = post_valid_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCareerlevel() {
        return careerlevel;
    }

    public void setCareerlevel(String careerlevel) {
        this.careerlevel = careerlevel;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEduction() {
        return eduction;
    }

    public void setEduction(String eduction) {
        this.eduction = eduction;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getJobdesc() {
        return jobdesc;
    }

    public void setJobdesc(String jobdesc) {
        this.jobdesc = jobdesc;
    }

    public String getJobexp() {
        return jobexp;
    }

    public void setJobexp(String jobexp) {
        this.jobexp = jobexp;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public String getJobtype() {
        return jobtype;
    }

    public void setJobtype(String jobtype) {
        this.jobtype = jobtype;
    }

    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }

    public String getPost_valid_date() {
        return post_valid_date;
    }

    public void setPost_valid_date(String post_valid_date) {
        this.post_valid_date = post_valid_date;
    }
}
