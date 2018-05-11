package es.sm2.rookieproyect.model;

import javax.validation.constraints.NotNull;


public class WordsTransformRequest {

    @NotNull
    private String word1;

    @NotNull
    private String word2;

    public String getWord1() {
        return word1;
    }

    public void setWord1(String word1) {
        this.word1 = word1;
    }

    public String getWord2() {
        return word2;
    }

    public void setWord2(String word2) {
        this.word2 = word2;
    }
}
