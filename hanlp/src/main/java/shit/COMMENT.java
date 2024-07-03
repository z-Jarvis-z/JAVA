package shit;

public class COMMENT {

    public COMMENT() {
        this.comment = new String();
        this.times = 0;
        this.flag = 0;
        this.emotion = 0;
    }
    public COMMENT(String comment) {
        this.comment = new String(comment);
        this.times = 0;
        this.flag = 0;
        this.emotion = 0;
    }
    public COMMENT(COMMENT Comment)
    {
     this.comment = new String(Comment.getComment());
     this.times = Comment.getTimes();
     this.flag = Comment.getFlag();
     this.emotion = Comment.getEmotion(); 
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment =new String(comment);
    }
    public int getTimes() {
        return times;
    }
    public void setTimes(int times) {
        this.times = times;
    }
    public void addTimes() {
        this.times +=1;
    }
    public int getFlag() {
        return flag;
    }
    public void setFlag(int flag) {
        this.flag = flag;
    }
    public int getEmotion() {
        return emotion;
    }
    public void setEmotion(int emotion) {
        this.emotion = emotion;
    }
    
    private String comment;
    private int times;
    private int flag;
    private int emotion;
}