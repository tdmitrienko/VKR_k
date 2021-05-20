package model;

public class ResultItemPDK_KK {
    private String GSM;
    private String HS;
    private String SUMM;
    private String PDK_KK;
    private String Recommendation;

    public ResultItemPDK_KK(String GSM, String HS, String SUMM, String PDK_KK, String recommendation) {
        this.GSM = GSM;
        this.HS = HS;
        this.SUMM = SUMM;
        this.PDK_KK = PDK_KK;
        Recommendation = recommendation;
    }

    public String getGSM() {
        return GSM;
    }

    public void setGSM(String GSM) {
        this.GSM = GSM;
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

    public String getPDK_KK() {
        return PDK_KK;
    }

    public void setPDK_KK(String PDK_KK) {
        this.PDK_KK = PDK_KK;
    }

    public String getRecommendation() {
        return Recommendation;
    }

    public void setRecommendation(String recommendation) {
        Recommendation = recommendation;
    }

    public String toString() {
        return "Конструкция: " + GSM + ",\tХимический элемент: " + HS + ",\tSUMM: " + SUMM + ",\tPDK_kk: " + PDK_KK + ",\tРекомендации: " + Recommendation + ";";
    }

}
