

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import STUDENT.*;

public class RollCallSystem
{ 
    private ArrayList<Student> StArr = new ArrayList<Student>();

    public void RollCall()
    {
        StArr.add(new Student("张三", 1, 1));
        StArr.add(new Student("李四", 2, 1));
        StArr.add(new Student("王五", 3, 1));
        StArr.add(new Student("赵六", 4, 1));
        StArr.add(new Student("钱七", 5, 1));
        while (true) 
        {
            print_menu();
            int choice = get_choice(6);
            switch (choice) {
                case 1:
                Call();                    
                    break;
                case 2:
                System.out.println("输入查询班级");
                choice=get_choice(10);
                find_class(choice);                    
                    break;
                case 3:
                System.out.println("输入查询学号");
                choice=get_choice(10);
                find_id(choice);                    
                    break;
                case 4:
                reset(StArr);                    
                    break;          
                case 5: 
                sort_truecount(StArr);                   
                    break;          
                default:
                    break;
            }
            if(choice == 6)
            {
                Scanner scanner = new Scanner(System.in);
                scanner.close();
                break;
            }
        }
    }
    private void find_id (int Id)
    {
        for(Student St : StArr)
        {
            if( St.getId() == Id )
            {
                System.out.println(St.toString());
                return;
            }
        }
        System.out.println("找不到该学号的学生信息");
    }
    private void find_class (int classNumber)
    {
        ArrayList<Student> Stu = new ArrayList<Student>();
        for(Student St : StArr)
        {
            if(St.getClassNumber() == classNumber)
                {
                    Stu.add(St);
                }
        }
        if(Stu.size() == 0)
        {
            System.out.println("找不到该班级的学生信息");
        }
        else
        {
            sort_truecount(Stu);
        }
    }
    private void print_menu()
    {
        System.out.println("1.随机选一个幸运儿");
        System.out.println("2.按班级查找");
        System.out.println("3.按学号查找");
        System.out.println("4.重置选人名单");
        System.out.println("5.查询点名结果");
        System.out.println("6.退出");

    }

    private int get_choice(int range)
    {
        int choice = 0;
        while (choice < 1 || choice >range) 
        {
            Scanner scanner = new Scanner(System.in);
            if(scanner.hasNextInt())
                {
                    choice=scanner.nextInt();
                } 
        }    
        return choice;
    }

    private void sort_truecount(ArrayList<Student> StArr)
    {
        ArrayList<Student> Stu = new ArrayList<Student>(StArr);
        Stu.sort((Student a,Student b)->{return b.gettruecount()-a.gettruecount();});
        for(Student St:Stu)
        {
            System.out.println(St.toString());
        }
    }

    private void Call()
    {
        int count =0;
        for(Student St:StArr)
        {
            if(St.getflag() == true)
            count++;
        }
        if(count == StArr.size())
        {
            System.out.println("所有人都被点过了请重置\n");
            return;
        }
        Random random = new Random();
        int index = random.nextInt(StArr.size());
        while (StArr.get(index).getflag() == true) {
            index = random.nextInt(StArr.size());
        }
        System.out.println("幸运儿就是你");
        StArr.get(index).print_info();
        StArr.get(index).setflag(true);
        choose_status(index);
    }

    private void choose_status(int index)
    {
        System.out.println("请选择学生状态");
        System.out.println("1.答对(CORRECT)");
        System.out.println("2.答错(WRONG)");
        System.out.println("3.缺勤(ABSENT)");
        System.out.println("4.请假(LEAVE)");
        System.out.println("5.其他(OTHER)");
        int choice = get_choice(5);
        StArr.get(index).setAttendanceStatus(choice);;
    }
    private void reset(ArrayList<Student> StArr)
    {
        for(Student St:StArr)
        {
            St.setflag(false);
        }
    }
}
class Test {
    public static void main(String[] args)
    {
        RollCallSystem system = new RollCallSystem();
        system.RollCall();
    }
}