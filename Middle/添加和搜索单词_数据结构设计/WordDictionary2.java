package Middle.添加和搜索单词_数据结构设计;

import java.util.ArrayList;
import java.util.List;

/**
 * 运行超时的代码
 */
public class WordDictionary2 {
    List<String> dictionary;
    public WordDictionary2() {
        this.dictionary = new ArrayList<>();
    }

    public void addWord(String word) {
        this.dictionary.add(word);
    }

    public boolean search(String word) {
        if (this.dictionary.contains(word)){
            return true;
        }
        if (!this.dictionary.contains(word) && word.indexOf('.')==-1){
            return false;
        }
        for (String str:this.dictionary){
            if (str.length() == word.length()) {
                for (int i = 0; i < str.length(); i++) {
                    if (str.charAt(i)!=word.charAt(i) && word.charAt(i)!='.'){
                        break;
                    }
                    if (i==str.length()-1){
                        return true;
                    }
                }

            }
        }
        return false;
    }
}
