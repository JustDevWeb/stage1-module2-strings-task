package com.epam.mjc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class StringSplitter {
    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        String[] delimitersArray = delimiters.toArray(new String[0]);
        ArrayList<String> result = new ArrayList<>();
        result.add(source);
        try {
            for (int i = 0; i < delimitersArray.length; i++){
                ArrayList<String> swapResult = new ArrayList<>();
                for(String part:result){
                   String[] substrings = part.split(delimitersArray[i]);
                    for(String str : substrings) {
                        if(!str.isEmpty()){
                            swapResult.add(str);
                        }
                    }
                }
                result = swapResult;
            }
        }
        catch(Exception e){
            throw new UnsupportedOperationException("You should implement this method.");
        }

        return result;
    }
}
