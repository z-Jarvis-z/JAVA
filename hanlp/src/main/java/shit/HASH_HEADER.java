package shit;

import java.util.ArrayList;

public class HASH_HEADER {
    public HASH_HEADER() {
        hash = 0;
        count = 0;
        comment = new ArrayList<COMMENT>(65536);
        for (int i = 0; i <65536; i++) {
            comment.add(new COMMENT());
        }
    }
    public COMMENT get(int index) {
        return comment.get(index);
    }
    public int getHash() {
        return hash;
    }
    public void setHash(int hash) {
        this.hash = hash;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public void addComment(String Comment) {
        comment.set(count,new COMMENT(Comment));
        count++;
    }
    private int hash;
    private int count;
    private ArrayList<COMMENT> comment;
}
