package model;

public class ChoosedItem {

    private String GSM;
    private String SM;
    private String mark;
    private String square;

    public ChoosedItem(String GSM, String SM, String mark, String square) {
        this.GSM = GSM;
        this.SM = SM;
        this.mark = mark;
        this.square = square;
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

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getSquare() {
        return square;
    }

    public void setSquare(String square) {
        this.square = square;
    }
}
