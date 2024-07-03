package shit;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.hankcs.hanlp.HanLP;
public class ANALYZE {
    ANALYZE()
    {
        KNOWN = new KnownHash();
        UNKNOWN = new unknownHash();
        COMMENTS = new OrderedComments();
    }
    public int AnalyzeComments(String comment)
    {
        int position = KNOWN.find(comment);
        int hash =myhash.getHash(comment);
        if(position != -1){
            KNOWN.get(hash).get(position).addTimes();
        }
        else{
            position = UNKNOWN.find(comment);
            if(position != -1){
                UNKNOWN.get(hash).get(position).addTimes();
            }
            else{
                UNKNOWN.addComment(comment);;
            }
        }
        return 0;

    }
    void try_update_orderedcommnets(COMMENT comment)
    {
        if(comment.getTimes()>Math.min(2,COMMENTS.getMintimes()))
        {
            COMMENTS.addComment(comment);
        }
    }
    public void readFileByLine(String filePath) {
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                AnalyzeComments(line);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private KnownHash KNOWN;
    private unknownHash UNKNOWN;
    private OrderedComments COMMENTS;
}
/**
 * test
 */
class test {

    public static void main(String[] args) {
        ANALYZE testAnalyze = new ANALYZE();
        String filepath ="src/main/resources/德佑湿厕纸#BV1B5411178w#2024#04#12.pinglun";
        testAnalyze.AnalyzeComments(filepath);
        System.out.println("Hello world!"+HanLP.segment("我爱自然语言处理技术"));
    }
}