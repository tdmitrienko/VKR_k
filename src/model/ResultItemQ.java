package model;

public class ResultItemQ {
    private String GSM;
    private String SM;
    private String HS;
    private String Q;

    public ResultItemQ(String GSM, String SM, String HS, String Q) {
        this.GSM = GSM;
        this.SM = SM;
        this.HS = HS;
        this.Q = Q;
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

    public String getQ() {
        return Q;
    }

    public void setQ(String Q) {
        this.Q = Q;
    }

    public String toString() {
        return "Конструкция: " + GSM + ",\tМатериал: " + SM + ",\tХимический элемент: " + HS + ",\tQ: " + Q + ";";
    }
}
