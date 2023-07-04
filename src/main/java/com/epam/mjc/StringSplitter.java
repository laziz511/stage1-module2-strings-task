package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for(String delimiter : delimiters) {
            sb.append(delimiter);
        }

        StringTokenizer st = new StringTokenizer(source, sb.toString());
        while(st.hasMoreTokens()) {
            list.add(st.nextToken());
        }
        return list;
    }
}
