package STUDENT;

import THETIME.*;

// 定义一个名为Attendance的类，用于记录点名情况
public class Attendance {
   private AttendanceStatus status;
   private String time ;
   public Attendance()
   {}
   public Attendance(int status)
   {
    setAttendanceStatus(status);
    time=TIME.get_time();
   }
   public void setAttendanceStatus(int status)
   {
    switch (status) {
        case 1:
            this.status = AttendanceStatus.CORRECT;
            break;
         case 2:
            this.status = AttendanceStatus.WRONG;
            break;
         case 3:
            this.status = AttendanceStatus.ABSENT;
            break;
         case 4:
            this.status = AttendanceStatus.LEAVE;
            break;
         case 5:
            this.status = AttendanceStatus.OTHER;
            break;        
        default:
            break;
    }
   }
   public AttendanceStatus getAttendanceStatus(){
        return this.status;
   }
   public String gettime(){
    return this.time;
   }

   public String toString()
   {
      StringBuilder sb = new StringBuilder();
      sb.append(time).append(" : ").append(status.toString());
      return sb.toString();
   }
}