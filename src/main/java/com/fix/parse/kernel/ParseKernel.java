package com.fix.parse.kernel;

import com.fix.parse.entity.Vocabulary;
import com.fix.parse.entity.VocabularyList;
import oracle.jrockit.jfr.StringConstantPool;
import org.apache.log4j.Logger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangy1 on 5/9/2016.
 */
public class ParseKernel {


    private static Logger LOGGER = Logger.getLogger(ParseKernel.class);

    static VocabularyList vocabularyList = new VocabularyList();

    public void parse(File file) throws IOException, ParseException {

        BufferedReader br = new BufferedReader(new FileReader(file));
        String result = br.readLine();
        while (result != null) {

            String[] sentences = result.split("\\.");

            for (String sentence : sentences) {
                String[] words = sentence.split(" ");

                for (String word : words) {
                    Boolean flag = vocabularyList.find(format(word));
                    if (flag) {
                        vocabularyList.counterPlus(format(word));
                    } else {
                        vocabularyList.add(format(word), format(sentence), new SimpleDateFormat("yyyyMMdd").parse(file.getName()));
                    }
                }
            }
            result = br.readLine();
        }
    }

    // 格式化文本
    private String format(String str) {

        //去掉首尾空格
        str = str.trim();
        //如果中间有多个空格，把他们合并为1个空格
        str.replaceAll("\\s+", " ");

        return prune(str);
    }

    // 去掉非法字符
    private String prune(String str) {

        // 包含句号或者内部有空格分隔，说明是一个句子
        if (str.contains(".") || str.indexOf(" ") != -1) {
            // 这是对一个句子的处理

        } else {
            // 这是对一个单词的处理

        }
        return str;
    }


    public void convertDOCXToTXT(String sourceFolder, String outputFolder) throws IOException {

        LOGGER.debug("");

        File sf = new File(sourceFolder);

        for (File file : sf.listFiles()) {

            String suffix = file.getName().substring(file.getName().indexOf(".") + 1, file.getName().length());

            if (suffix.equals("docx")) {

                LOGGER.debug("docx fomat: " + file.getName());

                InputStream is = new FileInputStream(file);
                XWPFDocument doc = new XWPFDocument(is);
                List<XWPFParagraph> plist = doc.getParagraphs();
                for (XWPFParagraph p : plist) {
                    File f = new File(outputFolder + file.getName().substring(0, file.getName().indexOf('.')) + ".txt");
                    FileWriter writer = new FileWriter(f, true);
                    writer.write(p.getText());
                    writer.flush();
                    writer.close();

                }
            } else if (suffix.equals("doc")) {
                LOGGER.debug("doc fomat");
            } else {
                LOGGER.debug("others format");
            }
        }
    }

    public void parse(List<File> files) throws IOException, ParseException {

        for (File f : files)
            parse(f);

    }
}
