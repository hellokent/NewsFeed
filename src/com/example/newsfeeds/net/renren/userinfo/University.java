package com.example.newsfeeds.net.renren.userinfo;

/**
 * User: yang-chen@renren-inc.com
 * Date: 13-6-17 上午1:06
 */
public class University {
    public int id;
    public int university_id;
    public String university_name;
    public int enroll_year;
    public String dorm;
    public String department;
    public String type_of_course;
    public String user_id;

    @Override
    public String toString() {
        return "University{" +
                "id=" + id +
                ", university_id=" + university_id +
                ", university_name='" + university_name + '\'' +
                ", enroll_year=" + enroll_year +
                ", dorm='" + dorm + '\'' +
                ", department='" + department + '\'' +
                ", type_of_course='" + type_of_course + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
