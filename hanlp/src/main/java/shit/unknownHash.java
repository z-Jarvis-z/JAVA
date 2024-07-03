package shit;

import java.util.ArrayList;

public class unknownHash {
    public unknownHash() {
        unkonwnhash = new ArrayList<HASH_HEADER>(65536);
        for (int i = 0;i < 65536;i++){
            unkonwnhash.add(new HASH_HEADER());
        }
    }
    public void addComment(String comment) {
        int hash = myhash.getHash(comment);
        unkonwnhash.get(hash).addComment(comment);
        unkonwnhash.get(hash).get(unkonwnhash.get(hash).getCount()-1).setTimes(1);
    }
    public int find(String comment) {   
        int hash = myhash.getHash(comment);
        for (int i = 0; i < unkonwnhash.get(hash).getCount(); i++) {
            if (unkonwnhash.get(hash).get(i).getComment().equals(comment)) {
                return i;
            }
        }
        return -1;
    }
    private ArrayList<HASH_HEADER> unkonwnhash;
    public HASH_HEADER get(int index) {
        return unkonwnhash.get(index);
    }
}
