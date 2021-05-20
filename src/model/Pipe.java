package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pipe {

    private ObservableList<ResultItemQ> itemsResultQ            = FXCollections.observableArrayList();
    private ObservableList<ResultItemP> itemsResultP            = FXCollections.observableArrayList();
    private ObservableList<ResultItemPDK_KK> itemsResultPDK_KK  = FXCollections.observableArrayList();
    private ObservableList<ResultItemPDK> itemsResultPDK        = FXCollections.observableArrayList();
    private XYChart.Series graphic                              = new XYChart.Series();

    /**
     * Переменная: лист фильтров
     */
    private List<Filter> filters = new ArrayList<>();
    /**
     * Переменная: список содержащий таблицы со значениями экземпляров классов химически элементов
     */
    private ArrayList<ArrayList<ChemicalSubstance>> tables = new ArrayList<>();
    /**
     * Переменная: лист химических веществ
     */
    private ArrayList<ChemicalSubstance> chemicalSubstances;

    public Pipe(ArrayList<ChemicalSubstance> chemicalSubstances, Filter... filters) { //Передача фильтров автоматический массив [] (insertion - входное число)
        this.chemicalSubstances = chemicalSubstances;
        this.filters.addAll(Arrays.asList(filters));
    }

    /**
     * метод для вызова фильтров, передает результат из текущего фильтра в последующий
     */
    public void calculate() {
        DecimalFormat df = new DecimalFormat("0.000");

        chemicalSubstances = filters.get(0).filter(chemicalSubstances);
        chemicalSubstances.forEach(chemicalSubstance -> {
            itemsResultQ.add(new ResultItemQ(chemicalSubstance.getConstruction(), chemicalSubstance.getMaterial(), chemicalSubstance.getName(), df.format(chemicalSubstance.getEmission())));
        });

        chemicalSubstances = filters.get(1).filter(chemicalSubstances);
        chemicalSubstances.forEach(chemicalSubstance -> {
            itemsResultP.add(new ResultItemP(chemicalSubstance.getConstruction(), chemicalSubstance.getMaterial(), chemicalSubstance.getName(), df.format(chemicalSubstance.getEmission())));
        });

        chemicalSubstances = filters.get(2).filter(chemicalSubstances);
        chemicalSubstances.forEach(chemicalSubstance -> {
            double kk = 0.3;
            String result;
            if (chemicalSubstance.getConstruction().equals("Строительные материалы"))
                kk = 0.3;
            else if (chemicalSubstance.getConstruction().equals("Отделочные материалы"))
                kk = 0.5;
            else if (chemicalSubstance.getConstruction().equals("Мебель"))
                kk = 0.2;
            if (chemicalSubstance.getEmission() <= chemicalSubstance.getPdk() * kk)
                result = "В пределах нормы";
            else
                result = "Нарушает нормы ПДК ( " + chemicalSubstance.getConstruction() + " под замену)";
            double rez = chemicalSubstance.getPdk() * kk;
            itemsResultPDK_KK.add(new ResultItemPDK_KK(chemicalSubstance.getConstruction(),chemicalSubstance.getName(), df.format(chemicalSubstance.getEmission()),rez + "",result ));
        });

        chemicalSubstances = filters.get(3).filter(chemicalSubstances);
        chemicalSubstances.forEach(chemicalSubstance -> {
            String result = "норм";
            if (chemicalSubstance.getEmission() <= chemicalSubstance.getPdk())
                result = "В пределах нормы";
            else
                result = "Нарушает нормы ПДК ( " + chemicalSubstance.getConstruction() + " под замену)";
            itemsResultPDK.add(new ResultItemPDK(chemicalSubstance.getName(),df.format(chemicalSubstance.getEmission()),chemicalSubstance.getPdk()+"", result ));
        });

        graphic();
    }

    private void graphic() {
        double countSM = 0;
        double countOM = 0;
        double countM = 0;
        for (ChemicalSubstance chemicalSubstance : chemicalSubstances) {
            switch (chemicalSubstance.getConstruction()) {
                case "Строительные материалы":
                    countSM += chemicalSubstance.getEmission();
                    break;
                case "Отделочные материалы":
                    countOM += chemicalSubstance.getEmission();
                    break;
                case "Мебель":
                    countM += chemicalSubstance.getEmission();
                    break;
            }
        }
        this.graphic.getData().add(new XYChart.Data("Строительные материалы", countSM));
        this.graphic.getData().add(new XYChart.Data("Отделочные материалы", countOM));
        this.graphic.getData().add(new XYChart.Data("Мебель",countM));
    }

    public ObservableList<ResultItemQ> getItemsResultQ() {
        return itemsResultQ;
    }

    public ObservableList<ResultItemP> getItemsResultP() {
        return itemsResultP;
    }

    public ObservableList<ResultItemPDK_KK> getItemsResultPDK_KK() {
        return itemsResultPDK_KK;
    }

    public ObservableList<ResultItemPDK> getItemsResultPDK() {
        return itemsResultPDK;
    }

    public XYChart.Series getGraphic() {
        return graphic;
    }

    public ObservableList<String> getFullListAsString() {
        ObservableList<String> list = FXCollections.observableArrayList();
        itemsResultQ.forEach( item -> list.add(item.toString()) );
        itemsResultP.forEach( item -> list.add(item.toString()) );
        itemsResultPDK_KK.forEach( item -> list.add(item.toString()) );
        itemsResultPDK.forEach( item -> list.add(item.toString()) );
        return list;
    }
}