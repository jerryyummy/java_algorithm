package amazon;

public class MatchStrings {
    public String[] matchStrings(String[] text, String[] pat) {
        String[] results = new String[text.length];

        for (int i = 0; i < text.length; i++) {
            String t = text[i];
            String p = pat[i];
            int wildcardIndex = p.indexOf('*');

            // Split pattern into prefix and suffix
            String prefix = p.substring(0, wildcardIndex);
            String suffix = p.substring(wildcardIndex + 1);

            // Check if the prefix matches the start of the text
            // and the suffix matches the end of the text
            if (t.startsWith(prefix) && t.endsWith(suffix)) {
                // Check if the combined length of prefix and suffix is less than or equal to text length
                if (prefix.length() + suffix.length() <= t.length()) {
                    results[i] = "YES";
                } else {
                    results[i] = "NO";
                }
            } else {
                results[i] = "NO";
            }
        }

        return results;
    }
}
