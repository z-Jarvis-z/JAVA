 
import com.hankcs.hanlp.HanLP;
 
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
 
public class CosineSimilarity {

    public static void main(String[] args) {
        String text3 = "我想养一头奶牛，这样就可以每天喝新鲜的牛奶。";
        String text4 = "我想每天喝新鲜的牛奶，所以打算养一头奶牛。";
        System.out.println("文本相似度为："+CosineSimilarity.getSimilarity(text3, text4));
    }
    /**
     * 使用余弦相似度算法计算文本相似性
     *
     * @param sentence1
     * @param sentence2
     * @return
     */
    public static double getSimilarity(String sentence1, String sentence2) {
        System.out.println("Step1. 分词");
        List<String> sent1Words = getSplitWords(sentence1);
        System.out.println(sentence1 + "\n分词结果：" + sent1Words);
        List<String> sent2Words = getSplitWords(sentence2);
        System.out.println(sentence2 + "\n分词结果：" + sent2Words);
        System.out.println("Step2. 取并集");
        List<String> allWords = mergeList(sent1Words, sent2Words);
        System.out.println(allWords);
        int[] statistic1 = statistic(allWords, sent1Words);
        int[] statistic2 = statistic(allWords, sent2Words);
        // 向量A与向量B的点乘
        double dividend = 0;
        // 向量A所有维度值的平方相加
        double divisor1 = 0;
        // 向量B所有维度值的平方相加
        double divisor2 = 0;
        // 余弦相似度 算法
        for (int i = 0; i < statistic1.length; i++) {
            dividend += statistic1[i] * statistic2[i];
            divisor1 += Math.pow(statistic1[i], 2);
            divisor2 += Math.pow(statistic2[i], 2);}
        System.out.println("Step3. 计算词频向量");
        for(int i : statistic1) {
            System.out.print(i+",");
        }
        System.out.println();
        for(int i : statistic2) {
            System.out.print(i+",");}
        System.out.println();
        // 向量A与向量B的点乘 / （向量A所有维度值的平方相加后开方 * 向量B所有维度值的平方相加后开方）
        return dividend / (Math.sqrt(divisor1) * Math.sqrt(divisor2));
    }
 
    // 3. 计算词频
    private static int[] statistic(List<String> allWords, List<String> sentWords) {
        int[] result = new int[allWords.size()];
        for (int i = 0; i < allWords.size(); i++) {
            // 返回指定集合中指定对象出现的次数
            result[i] = Collections.frequency(sentWords, allWords.get(i));
        }
        return result;
    }
 
    // 2. 取并集
    private static List<String> mergeList(List<String> list1, List<String> list2) {
        List<String> result = new ArrayList<>();
        result.addAll(list1);
        result.addAll(list2);
        return result.stream().distinct().collect(Collectors.toList());
    }
 
    // 1. 分词
    private static List<String> getSplitWords(String sentence) {
        // 标点符号会被单独分为一个Term，去除之
        return HanLP.segment(sentence).stream().map(a -> a.word).filter(s -> !"`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？ ".contains(s)).collect(Collectors.toList());
    }
}
