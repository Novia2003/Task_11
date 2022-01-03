package ru.vsu.cs.novichikhin;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordSelection {

    public List<String> findRightWords(List<String> text) {
        List<String> rightWords = new ArrayList<>();

        List<String> message = List.of("В файле нет слов, в которых количество гласных букв больше количества " +
                "согласных. Словом считается непрерывная последовательность символов (строчных и прописных) А-Я.");

        List<String> words = findWords(text);
        if (words.size() == 0) {
            return message;
        }

        Pattern vowels = Pattern.compile("[аеёиоуыэюяАЕЁИОУЫЭЮЯ]");
        Pattern consonants = Pattern.compile("[бБвВгГдДжЖйЙзЗкКлЛмМнНпПрРсСтТфФхХцЦчЧшШщЩ]");

        for (String word : words) {
            int quantityVowels = findQuantityVowelsOrConsonants(word, vowels);
            int quantityConsonants = findQuantityVowelsOrConsonants(word, consonants);

            if (quantityVowels > quantityConsonants && !isThisWordAlreadyThere(rightWords, word)) {
                rightWords.add(word);
            }
        }

        if (rightWords.size() == 0) {
            return message;
        }

        return rightWords;
    }

    private List<String> findWords(List<String> text) {
        List<String> words = new ArrayList<>();

        Pattern alphabet = Pattern.compile("[а-яА-я]+");
        Matcher matcher;

        for (String word : text) {
            matcher = alphabet.matcher(word);

            while (matcher.find()) {
                words.add(matcher.group());
            }
        }

        return words;
    }

    private int findQuantityVowelsOrConsonants(String word, Pattern pattern) {
        int quantity = 0;
        Matcher matcher = pattern.matcher(word);

        while (matcher.find()) {
            quantity++;
        }

        return quantity;
    }

    private boolean isThisWordAlreadyThere(List<String> rightWords, String checkedWord) {
        for (String word : rightWords) {
            if (checkedWord.equals(word)) {
                return true;
            }
        }

        return false;
    }
}
