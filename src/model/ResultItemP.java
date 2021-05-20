package model;

public class ResultItemP {
    private String GSM;
    private String SM;
    private String HS;
    private String P;

    public ResultItemP(String GSM, String SM, String HS, String P) {
        this.GSM = GSM;
        this.SM = SM;
        this.HS = HS;
        this.P = P;
    }

    public String getGSM() {
        return GSM;
    }

    public void setGSM(String GSM) {
        this.GSM = GSM;
    }

    public String getSM() {
        return SM;
    }

    public void setSM(String SM) {
        this.SM = SM;
    }

    public String getHS() {
        return HS;
    }

    public void setHS(String HS) {
        this.HS = HS;
    }

    public String getP() {
        return P;
    }

    public void setP(String P) {
        this.P = P;
    }

    public String toString() {
        return "Конструкция: " + GSM + ",\tМатериал: " + SM + ",\tХимический элемент: " + HS + ",\tP: " + P + ";";
    }
}
