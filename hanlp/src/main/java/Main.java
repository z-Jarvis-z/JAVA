
import java.io.File;
import java.io.IOException;
import java.util.List;

import com.hankcs.hanlp.*;
import com.hankcs.hanlp.classification.classifiers.IClassifier;
import com.hankcs.hanlp.classification.classifiers.NaiveBayesClassifier;
import com.hankcs.hanlp.classification.corpus.AbstractDataSet;
import com.hankcs.hanlp.classification.corpus.Document;
import com.hankcs.hanlp.classification.corpus.MemoryDataSet;
import com.hankcs.hanlp.classification.models.NaiveBayesModel;
import com.hankcs.hanlp.corpus.io.IOUtil;
import com.hankcs.hanlp.model.crf.crfpp.Model;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;


public class Main {

	public static void main(String[] args) {

		
		segment();
		// AbstractDataSet dataSet = new MemoryDataSet();
		// dataSet.load("C:/Users/Administrator.admin-PC/Desktop/新建文件夹/zjx/src/zjx/data/搜狗文本分类语料库迷你版");
		// dataSet.add("自然语言处理","自然语言处理很有趣");
		// List<String> allclasses = dataSet.getCatalog().getCategories();
		// System.out.println("表主机：%s \n",allclasses);
		// for(Document document: dataSet)
		// {
		// System.out.println("第一篇文章的类别：" + allclasses.get(document.category));
		// break;
		// }
	}

	public static void segment() {
		List<Term> list = HanLP.segment("你好欢迎使用hanlp");
		for (Term term : list) {
			System.out.println(term.word + " " + term.nature);
		}
		System.out.println(NLPTokenizer.segment("如何正确的标注新词词性"));
		List<Term> termlist = StandardTokenizer.segment("商品和服务");

		System.out.print(termlist);
		Segment mysSegment = HanLP.newSegment().enableNameRecognize(true);
		System.out.println(mysSegment.seg("程序员是一个容易掉头发的职业"));
	}
	public static void extract()
	{
		String content = "程序员(英文Programmer)是从事程序开发、维护的专业人员。一般将程序员分为程序设计人员和程序编码人员，但两者的界限并不非常清楚，特别是在中国。软件从业人员分为初级程序员、高级程序员、系统分析员和项目经理四大类。";
		List<String> keywordList = HanLP.extractKeyword(content, 5);
		System.out.println(keywordList);

		String document = "算法可大致分为基本算法、数据结构的算法、数论算法、计算几何的算法、图的算法、动态规划以及数值分析、加密算法、排序算法、检索算法、随机化算法、并行算法、厄米变形模型、随机森林算法。\n"
				+
				"算法可以宽泛的分为三类，\n" +
				"一，有限的确定性算法，这类算法在有限的一段时间内终止。他们可能要花很长时间来执行指定的任务，但仍将在一定的时间内终止。这类算法得出的结果常取决于输入值。\n" +
				"二，有限的非确定算法，这类算法在有限的时间内终止。然而，对于一个（或一些）给定的数值，算法的结果并不是唯一的或确定的。\n" +
				"三，无限的算法，是那些由于没有定义终止定义条件，或定义的条件无法由输入的数据满足而不终止运行的算法。通常，无限算法的产生是由于未能确定的定义终止条件。";
		List<String> sentenceList = HanLP.extractSummary(document, 3);
		System.out.println(sentenceList);
	}
	private static NaiveBayesModel trainOrLoadModel() throws IOException
    {
        NaiveBayesModel model = (NaiveBayesModel) IOUtil.readObjectFrom(MODEL_PATH);
        if (model != null) return model;

        File corpusFolder = new File(CORPUS_FOLDER);
        if (!corpusFolder.exists() || !corpusFolder.isDirectory())
        {
            System.err.println("没有文本分类语料，请阅读IClassifier.train(java.lang.String)中定义的语料格式与语料下载：" +
                                   "https://github.com/hankcs/HanLP/wiki/%E6%96%87%E6%9C%AC%E5%88%86%E7%B1%BB%E4%B8%8E%E6%83%85%E6%84%9F%E5%88%86%E6%9E%90");
            System.exit(1);
        }

        IClassifier classifier = new NaiveBayesClassifier(); // 创建分类器，更高级的功能请参考IClassifier的接口定义
        classifier.train(CORPUS_FOLDER);                     // 训练后的模型支持持久化，下次就不必训练了
        model = (NaiveBayesModel) classifier.getModel();
        IOUtil.saveObjectTo(model, MODEL_PATH);
        return model;
    }
	public static void classification()
	{
		try {
			IClassifier classifier = new NaiveBayesClassifier(trainOrLoadModel());
        	predict(classifier, "C罗获2018环球足球奖最佳球员 德尚荣膺最佳教练");
        	predict(classifier, "英国造航母耗时8年仍未服役 被中国速度远远甩在身后");
        	predict(classifier, "研究生考录模式亟待进一步专业化");
        	predict(classifier, "如果真想用食物解压,建议可以食用燕麦");
        	predict(classifier, "通用及其部分竞争对手目前正在考虑解决库存问题");
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
		}
	}
	private static void predict(IClassifier classifier, String text)
    {
        System.out.printf("《%s》 属于分类 【%s】\n", text, classifier.classify(text));
    }
	public static final String CORPUS_FOLDER = "./src/main/resources/data/搜狗文本分类语料库迷你版";
    /**
     * 模型保存路径
     */
    public static final String MODEL_PATH = "./src/main/resources/data/classification-model.ser";
}

