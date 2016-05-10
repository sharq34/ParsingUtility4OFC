package com.fix.parse.entity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by yangy1 on 5/10/2016.
 */
public class Vocabulary {
    private int count;
    private String word;
    private List<String> sentences;
    private Date issueDate;

    public String getWord() {
        return word;
    }

    public Vocabulary(String word, String sentence, Date issueDate) {
        this.word = word;
        this.count = 1;
        this.issueDate= issueDate;

        this.sentences = new ArrayList<String>();
        this.sentences.add(sentence);
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<String> getSentences() {
        return sentences;
    }

    public void setSentences(List<String> sentences) {
        this.sentences = sentences;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    @Override
    public String toString() {
        return "Vocabulary{" +
                "count=" + count +
                ", word='" + word + '\'' +
                ", sentences=" + sentences +
                ", issueDate=" + new SimpleDateFormat("yyyyMMdd").format(issueDate) +
                "}\n";
    }
}
