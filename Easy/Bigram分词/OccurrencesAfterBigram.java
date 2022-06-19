package Easy.Bigram分词;

import java.util.ArrayList;
import java.util.List;

public class OccurrencesAfterBigram {
    public String[] findOcurrences(String text, String first, String second) {

        if ( text==null || text.length()==0 ||!text.contains(first) || !text.contains(second)){
            return new String[]{};
        }

        List<String> list = new ArrayList<>();
        String[] splits = text.split(" ");

        for (int i=0; i<splits.length-1; i++) {
            if (splits[i].equals(first) && splits[i + 1].equals(second)) {
                if (i+2>=splits.length){
                    break;
                }
                list.add(splits[i + 2]);
            }
        }

        String[] ans = new String[list.size()];

        int idx = 0;
        for (String s: list){
            ans[idx++] = s;
        }

        return ans;
    }
}
