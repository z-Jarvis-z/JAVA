package shit;

import java.util.ArrayList;

public class OrderedComments {
    public OrderedComments(){
        comments = new ArrayList<COMMENT>(64);
        for(int i = 0; i < 64; i++){
            comments.add(new COMMENT());
        }
    }
    public void addComment(COMMENT comment){
        for (int i = 0; i < count; i++)
    {
        if (comment.getComment().equals(comments.get(i).getComment()))
        {
            comments.get(i).setTimes(comment.getTimes());
			while(0 != i && comments.get(i).getTimes() > comments.get(i-1).getTimes())
			{
				COMMENT temp = comments.get(i);
                comments.set(i, comments.get(i-1));
                comments.set(i-1, temp);
                i--;
			}
            mintimes = comments.get(i-1).getTimes();
            return;
        }
    }
    int i = 0;//insert position
    while(comments.get(i).getTimes() >= comment.getTimes() && i < count)
    {
        i++;
    }
    for (int j = count-1; i < j; j--)
    {
        comments.set(j, comments.get(j-1));
    }
    comments.set(i, comment);
    if (count != 64)
        count++;
    mintimes = comments.get(count-1).getTimes();
    }
    public int getMintimes() {
        return mintimes;
    }
    public COMMENT get(int index)
    {
        return comments.get(index);
    } 
    private ArrayList<COMMENT> comments;
    private int count;
    private int mintimes;
    
}
