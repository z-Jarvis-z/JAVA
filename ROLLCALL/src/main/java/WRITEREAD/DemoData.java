package WRITEREAD;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;



@Data
@EqualsAndHashCode
public class DemoData {
    
    @ExcelProperty("姓名")
    private String name;
    @ExcelProperty("学号")
    private String id;
    @ExcelProperty("班级")
    private String classNumber;
    @ExcelProperty("状态")
    private String attendances;
}