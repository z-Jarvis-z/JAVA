package WRITEREAD;


import java.util.ArrayList;

import java.util.List;

import STUDENT.*;
import THETIME.TIME;

import com.alibaba.excel.EasyExcel;



public class EasyTest {

    String INPATH = "src\\";
    String OUTPATH = "target\\generated-sources\\";
    
    private List<DemoData> data(ArrayList<Student> starArrayList) {
        List<DemoData> list = new ArrayList<DemoData>();
            
            for(Student St : starArrayList){
                DemoData data = new DemoData();    
                data.setName(St.getName());
                data.setId(Integer.toString(St.getId()));
                data.setClassNumber(Integer.toString(St.getClassNumber()));
                if(St.getAttendance().size()>0)
                    data.setAttendances(St.getAttendance().toString());
                else
                    data.setAttendances("");
                list.add(data);}
        return list;
    }

    @org.junit.Test 
    public void simpleWrite(ArrayList<Student> starArrayList) {
        // 写法2
        String fileName = OUTPATH +TIME.get_time().substring(0, 10)+ "Student.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        
        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data(starArrayList));
    }

    public List<DemoData> simpleRead() {
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法3：
        String fileName = INPATH + "Student.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
         DemoDataListener data = new DemoDataListener();
        EasyExcel.read(fileName, DemoData.class,data).sheet().doRead();
        return data.getcachedDataList();
    }

}

/*class Test {
    public static void main(String[] args)
    {
        EasyTest easyTest = new EasyTest();
        easyTest.simpleRead();
    }
}*/

