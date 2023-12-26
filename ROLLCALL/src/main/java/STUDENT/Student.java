package STUDENT;
// 定义一个学生类

import java.util.ArrayList;

public class Student 
{

   
    // 定义学生的姓名属性
    private String name;
    // 定义学生的学号属性
    private int id;
    // 定义学生的班级属性
    private int classNumber;
    // 添加点名状态属性
    private ArrayList<Attendance> attendances;

    private int totalcount;
    private int truecount;
    private boolean flag;
    // 构造方法，用于创建学生对象时初始化属性
    public Student(String name, int id, int classNumber) {
        this.name = name;
        this.id = id;
        this.classNumber = classNumber;
        this.attendances = new ArrayList<Attendance>();
        flag = false;
    }

    // 获取学生姓名的方法
    public String getName() {
        return name;
    }

    // 设置学生姓名的方法
    public void setName(String name) {
        this.name = name;
    }

    // 获取学生学号的方法
    public int getId() {
        return id;
    }

    // 设置学生学号的方法
    public void setId(int id) {
        this.id = id;
    }

    // 获取学生班级的方法
    public int getClassNumber() {
        return classNumber;
    }

    // 设置学生班级的方法
    public void setClassNumber(int classNumber) {
        this.classNumber = classNumber;
    }

   public ArrayList<Attendance>getAttendance()
   {
    return this.attendances;
   }
   public int gettruecount(){
    return this.truecount;
   }
   public void setAttendanceStatus(int status)
   {
    attendances.add(new Attendance(status));
    if(status == 1){
        truecount++;
    }
    totalcount++;
   }
   public void setflag(boolean flag){
    this.flag=flag;
   }
   public boolean getflag(){
    return this.flag;
   }
   public String toString(){
    StringBuilder sb = new StringBuilder();
            sb.append("姓名： ").append(name).append(",");
            sb.append("学号： ").append(id).append(",");
            sb.append("班级： ").append(classNumber).append(",");
            sb.append("点名状态： ");
            for (int i = 0; i < attendances.size(); i++) {
                sb.append(attendances.get(i));
                if (i < attendances.size() - 1) {
                    sb.append(", ");
                }
            }
            return sb.toString();
   }

   public void print_info()
   {
     StringBuilder sb = new StringBuilder();
            sb.append("姓名： ").append(name).append(",");
            sb.append("学号： ").append(id).append(",");
            sb.append("班级： ").append(classNumber);
    System.out.println(sb.toString());
   }
}

