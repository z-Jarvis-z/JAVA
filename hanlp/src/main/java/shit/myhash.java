package shit;

public class myhash {
    public static int getHash(String value) {
        int hash = 0;
        for (int i = 0; i < value.length() && i < 4; i++) {
            hash += value.charAt(i);
        }
        hash = hash+value.length();
        hash ^= hash << 13;
        hash ^= hash >> 17;
        hash ^= hash << 5;
        return hash%65536;
    }
    public static void main(String[] args) {
        System.out.println("getHash"+getHash("nothing"));
    }
}
