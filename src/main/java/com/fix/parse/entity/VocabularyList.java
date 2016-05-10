package com.fix.parse.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yangy1 on 5/10/2016.
 */
public class VocabularyList {

    List<Vocabulary> vocabularyList=new ArrayList<Vocabulary>();

    public Boolean find(String word){

        Iterator it= vocabularyList.iterator();
        while(it.hasNext()){
            Vocabulary vo= (Vocabulary)it.next();
            if (word.equals(vo.getWord())){
                return true;
            }
        }

        return false;
    }

    public void counterPlus(String word){
        Iterator it= vocabularyList.iterator();
        while(it.hasNext()){
            Vocabulary vo= (Vocabulary)it.next();
            if (word.equals(vo.getWord())){
                vo.setCount(vo.getCount()+1);
                return;
            }
        }

    }

    public void add(String word, String sentence, Date issueDate){
        Vocabulary vocabulary = new Vocabulary(word, sentence, issueDate);
        vocabularyList.add(vocabulary);
    }

    public void print(){
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "VocabularyList{" +
                "vocabularyList=" + vocabularyList +
                '}';
    }
}
