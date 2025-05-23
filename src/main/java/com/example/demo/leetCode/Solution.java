package com.example.demo.leetCode;

class Solution {
    public static void main(String[] args) {
        String[] arr = {"papa", "pa"};
        Solution solution = new Solution();
        System.out.println(solution.countPrefixSuffixPairs(arr));
    }

    public int countPrefixSuffixPairs(String[] words) {
        int count = 0;
        if (words == null || words.length == 0 || words.length == 1) return 0;

        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                String word = words[i];
                String nextWord = words[j];
                if (isPrefixAndSuffix(word, nextWord)) {
                    count++;
                }
            }
        }


        return count;
    }

    public boolean isPrefixAndSuffix(String str1, String str2) {

        if (str1.length() > str2.length()) {
            return false;
        } else if (str1.length() == str2.length()) {
            if (str1.equals(str2))
                return true;
            else return false;

        }
        int smallHashCode = str1.hashCode();
        int bigHashCode = str2.substring(0, str1.length()).hashCode();
        int bigHashCodeReverse = str2.substring(str2.length() - str1.length(), str2.length()).hashCode();

        if (smallHashCode == bigHashCode && bigHashCode == bigHashCodeReverse)
            return true;

        return false;
    }
}