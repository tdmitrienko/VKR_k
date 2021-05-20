package model;

public class ResultItemPDK {
    private String HS;
    private String SUMM;
    private String PDK;
    private String Recommendation;

    public ResultItemPDK(String HS, String SUMM, String PDK, String recommendation) {
        this.HS = HS;
        this.SUMM = SUMM;
        this.PDK = PDK;
        Recommendation = recommendation;
    }

    public String getHS() {
        return HS;
    }

    public void setHS(String HS) {
        this.HS = HS;
    }

    public String getSUMM() {
        return SUMM;
    }

    public void setSUMM(String SUMM) {
        this.SUMM = SUMM;
    }

    public String getPDK() {
        return PDK;
    }

    public void setPDK(String PDK) {
        this.PDK = PDK;
    }

    public String getRecommendation() {
        return Recommendation;
    }

    public void setRecommendation(String recommendation) {
        Recommendation = recommendation;
    }

    public String toString() {
        return "Химический элемент: " + HS + ",\tSUMM: " + SUMM + ",\tPDK: " + PDK + ",\tРекомендации: " + Recommendation + ";";
    }
}
