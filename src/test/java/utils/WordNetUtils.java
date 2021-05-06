package utils;

import edu.mit.jwi.IRAMDictionary;
import edu.mit.jwi.RAMDictionary;
import edu.mit.jwi.data.ILoadPolicy;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.POS;

import java.io.File;

public class WordNetUtils {
    private IRAMDictionary dict;

    public WordNetUtils() throws Exception {
        File file = new File("src/test/resources/WNdb-3.0/dict");
        setupDictionary(file);
    }


    public void setupDictionary(File wnDir) throws Exception {
        dict = new RAMDictionary(wnDir, ILoadPolicy.NO_LOAD);
        dict.open();
    }


    public boolean isValidWord(String text) {
        //CHECKING ALL WORD TYPES FOR VALID ENGLISH WORD
        IIndexWord indexWord1 = dict.getIndexWord(text, POS.NOUN);
        IIndexWord indexWord2 = dict.getIndexWord(text, POS.ADJECTIVE);
        IIndexWord indexWord3 = dict.getIndexWord(text, POS.VERB);
        IIndexWord indexWord4 = dict.getIndexWord(text, POS.ADVERB);
        return indexWord1 != null || indexWord2 != null || indexWord3 != null || indexWord4 != null;
    }


}
