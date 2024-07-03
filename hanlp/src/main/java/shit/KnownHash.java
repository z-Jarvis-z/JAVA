package shit;

import java.util.ArrayList;

public class KnownHash {
    public KnownHash() {
        konwnhash = new ArrayList<HASH_HEADER>(65536);
        for (int i = 0; i < 65536; i++) {
            konwnhash.add(new HASH_HEADER());
        }
    }

    public void addComment(String comment) {
        int hash = myhash.getHash(comment);
        konwnhash.get(hash).addComment(comment);
    }

    public int find(String comment) {
        int hash = myhash.getHash(comment);
        for (int i = 0; i < konwnhash.get(hash).getCount(); i++) {
            if (konwnhash.get(hash).get(i).getComment().equals(comment)) {
                return i;
            }
        }
        return -1;
    }
    public HASH_HEADER get(int index) {
        return konwnhash.get(index);
    }
    private ArrayList<HASH_HEADER> konwnhash;

}
