package com.remember.interview.luyunzhi;

/**
 * @author wangjiahao
 * @date 2021/4/23
 */
public class EMP {

    private int id;
    private String name;
    private int age;
    private String sex;
    private String major;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String printEMP() {
        return "EMP{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", major='" + major + '\'' +
                '}';
    }

    public static void main(String[] args) {
        EMP emp = new EMP();
        emp.setId(5);
        emp.setAge(23);
        emp.setSex("男");
        emp.setName("王强");
        emp.setMajor("计算机网络专业");
        System.err.println(emp.printEMP());

    }

}
