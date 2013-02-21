package edu.kpi.pzks.core.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Author: snoop Date: 12.02.13 Time: 00:50
 */
public class Messages {
    //а какого хрена засовывать ресорсбандл внутрь кора? фейспалм
    //потому что это меседжи кора?

    private static Map<Locale, ResourceBundle> propertiesMap = new HashMap<>();
    public static final String VALIDATION_ERROR_GRAPH_INCONSISTENT = "core.validation.error.graph.inconsistent";
    public static final String VALIDATION_ERROR_CYCLES_PRESENT = "core.validation.error.cycles.present";
    public static final String VALIDATION_ERROR_SUBGRAPHS_PRESENT = "core.validation.error.subgraph.present";


    public static String getLocalizedMessage(String key, Locale locale) {
        if (!propertiesMap.containsKey(locale)) {
            propertiesMap.put(locale, ResourceBundle.getBundle("messages", locale));
        }
        return propertiesMap.get(locale).getString(key);
    }
}
