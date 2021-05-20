package controllers;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public DAO dbConnect = new DBConnect();
    public BarChart VLVChart;
    public CategoryAxis x;
    public NumberAxis y;
    public TableView<ChoosedItem> tableVLV;
    public TableColumn<ChoosedItem,String> ColumnGSM;
    public TableColumn<ChoosedItem,String> ColumnSM;
    public TableColumn<ChoosedItem,String> ColumnMark;
    public TableColumn<ChoosedItem,String> ColumnS;
    public TableView<ResultItemQ> ResultQ;
    public TableColumn<ResultItemQ,String> ColumnQGSM;
    public TableColumn<ResultItemQ,String> ColumnQSM;
    public TableColumn<ResultItemQ,String> ColumnQHS;
    public TableColumn<ResultItemQ,String> ColumnQ;
    public TableView<ResultItemP> ResultP;
    public TableColumn<ResultItemP,String> ColumnPGSM;
    public TableColumn<ResultItemP,String> ColumnPSM;
    public TableColumn<ResultItemP,String> ColumnPHS;
    public TableColumn<ResultItemP,String> ColumnP;
    public TableView<ResultItemPDK_KK> ResultPDK_KK;
    public TableColumn<ResultItemPDK_KK,String> ColumnPDK_KKGSM;
    public TableColumn<ResultItemPDK_KK,String> ColumnPDK_KKHS;
    public TableColumn<ResultItemPDK_KK,String> ColumnPDK_KKSUMM;
    public TableColumn<ResultItemPDK_KK,String> ColumnPDK_KK;
    public TableColumn<ResultItemPDK_KK,String> ColumnPDK_KKRecommendation;
    public TableView<ResultItemPDK> ResultPDK;
    public TableColumn<ResultItemPDK,String> ColumnPDKHS;
    public TableColumn<ResultItemPDK,String> ColumnPDKSUMM;
    public TableColumn<ResultItemPDK,String> ColumnPDK;
    public TableColumn<ResultItemPDK,String> ColumnPDKRecommendation;


    /**
     * лист содержащий список материалов группы стройматериалы
     */
    ArrayList<Material> materials1 = new ArrayList<>(); //лист содержащий список материалов группы стройматериалы
    /**
     * лист содержащий список материалов группы отделочные материалы
     */
    ArrayList<Material> materials2 = new ArrayList<>();//лист содержащий список материалов группы отделочные материалы
    /**
     * лист содержащий список материалов группы мебель
     */
    ArrayList<Material> materials3 = new ArrayList<>();//лист содержащий список материалов группы мебель
    /**
     * лист содержащий все материалы по трем группам
     */
    ArrayList<Material> allMaterials = new ArrayList<>();//лист содержащий все материалы по трем группам

    /**
     * поле для ввода объема
     */
    public TextField V; //поле для ввода объема
    /**
     * поле для ввода температуры
     */
    public TextField t; //поле для ввода температуры
    /**
     * поле для ввода коэффициента воздухообмена
     */
    public TextField kw;
    /**
     * выбор группы строительного материала
     */
    public ComboBox GSM; //выбор группы строительного материала
    /**
     * поле для ввода площади
     */
    public TextField S; //поле для ввода площади
    /**
     * выбор строительного материала
     */
    public ComboBox SM; //выбор строительного материала
    /**
     * выбор марки строительного материала
     */
    public ComboBox mark;//выбор марки строительного материала
    Pipe pipe;


    /**
     * метод срабатывает автоматически при запуске программы, в данном методе производится выборка названий строительных конструкций из базы данных и эти названия записываются в GSM
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ResultSet rez = null;
        try {
            rez = dbConnect.dbExecuteQueryDao("SELECT name FROM buildConstruction");//запрос на выборку имени из таблицы  buildconstruction
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                if (!rez.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                GSM.getItems().add(rez.getString(1)); //добавление в комбобокс результатов выборки
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        ColumnGSM.setCellValueFactory(new PropertyValueFactory<>("GSM"));
        ColumnSM.setCellValueFactory(new PropertyValueFactory<>("SM"));
        ColumnS.setCellValueFactory(new PropertyValueFactory<>("square"));
        ColumnMark.setCellValueFactory(new PropertyValueFactory<>("mark"));

        ColumnQGSM.setCellValueFactory(new PropertyValueFactory<>("GSM"));
        ColumnQSM.setCellValueFactory(new PropertyValueFactory<>("SM"));
        ColumnQHS.setCellValueFactory(new PropertyValueFactory<>("HS"));
        ColumnQ.setCellValueFactory(new PropertyValueFactory<>("Q"));

        ColumnPGSM.setCellValueFactory(new PropertyValueFactory<>("GSM"));
        ColumnPSM.setCellValueFactory(new PropertyValueFactory<>("SM"));
        ColumnPHS.setCellValueFactory(new PropertyValueFactory<>("HS"));
        ColumnP.setCellValueFactory(new PropertyValueFactory<>("P"));

        ColumnPDK_KKGSM.setCellValueFactory(new PropertyValueFactory<>("GSM"));
        ColumnPDK_KKHS.setCellValueFactory(new PropertyValueFactory<>("HS"));
        ColumnPDK_KKSUMM.setCellValueFactory(new PropertyValueFactory<>("SUMM"));
        ColumnPDK_KK.setCellValueFactory(new PropertyValueFactory<>("PDK_KK"));
        ColumnPDK_KKRecommendation.setCellValueFactory(new PropertyValueFactory<>("Recommendation"));
        
        ColumnPDKHS.setCellValueFactory(new PropertyValueFactory<>("HS"));
        ColumnPDKSUMM.setCellValueFactory(new PropertyValueFactory<>("SUMM"));
        ColumnPDK.setCellValueFactory(new PropertyValueFactory<>("PDK"));
        ColumnPDKRecommendation.setCellValueFactory(new PropertyValueFactory<>("Recommendation"));

    }

    /**
     * метод для добавления в listView  а так же в лист материалов
     */
    public void add() { //метод для добавления в listView  а так же в лист материалов
        try {
            double ss = Double.parseDouble(S.getText());
            if (!GSM.getValue().toString().isEmpty() && !SM.getValue().toString().isEmpty() && !S.getText().isEmpty() && !mark.getValue().toString().isEmpty()) {
              //  listView.getItems().add(GSM.getValue() + ": " + SM.getValue() + ", площадью S = " + S.getText() + " м²");
                tableVLV.getItems().add(new ChoosedItem(GSM.getValue().toString(),SM.getValue().toString(),mark.getValue().toString(),S.getText()+ " м²"));
                allMaterials.add(new Material(SM.getValue().toString(), mark.getValue().toString(), ss));
                if (GSM.getValue().toString().equals("finish"))
                    materials2.add(new Material(SM.getValue().toString(), mark.getValue().toString(), ss));
                else if (GSM.getValue().toString().equals("furniture"))
                    materials3.add(new Material(SM.getValue().toString(), mark.getValue().toString(), ss));
                else
                    materials1.add(new Material(SM.getValue().toString(), mark.getValue().toString(), ss));
            }        } catch (NullPointerException | NumberFormatException e) {

        }
    }

    /**
     * метод для отбора строительного материала в зависимости от выбранной группы строительного материала
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void selectMaterials() throws SQLException, ClassNotFoundException { //метод для отбора строительного материала в зависимости от выбранной группы
        SM.getItems().clear();
        ResultSet rez;
        rez = dbConnect.dbExecuteQueryDao("SELECT buildMaterial.name FROM buildMaterial inner join buildConstruction on(buildConstruction.ID=buildMaterial.Id_buildConstruction) where buildConstruction.name='" + GSM.getValue() + "'");
        while (rez.next()) {
            SM.getItems().add(rez.getString(1));
        }
    }

    /**
     * метод для расчета
     */
    public void calculateREZ() {//метод для расчета
        try{
            ResultQ.getItems().clear();
            double temperature = Double.parseDouble(t.getText());
            double volume = Double.parseDouble(V.getText());
            double koefw = Double.parseDouble(kw.getText());


            Construction construction1 = new Construction("building materials", materials1);
            Construction construction2 = new Construction("finish", materials2);
            Construction construction3 = new Construction("furniture", materials3);

            ArrayList<Construction> constructions = new ArrayList<>();
            constructions.add(construction1);
            constructions.add(construction2);
            constructions.add(construction3);

            Filter filterQ = new FilterQ(constructions, dbConnect);//фильтр для расчета выделение j -того вредного вещества
            Filter filterP = new FilterP(temperature, volume, koefw);//фильтр для расчета концентрация выделения j -го вредного вещества
            Filter filterPDKKK = new FilterPDKKK();// фильтр для подсчета конечной концентрации по каждому химическому элементу
            Filter filterPDK = new FilterPDK();// фильтр для подсчета конечной концентрации по каждому химическому элементу


            ArrayList<ChemicalSubstance> chemicalSubstances = new ArrayList<>();
            pipe = new Pipe(chemicalSubstances, filterQ, filterP, filterPDKKK, filterPDK);
            pipe.calculate();
            ResultQ.setItems(pipe.getItemsResultQ());
            ResultP.setItems(pipe.getItemsResultP());
            ResultPDK_KK.setItems(pipe.getItemsResultPDK_KK());
            ResultPDK.setItems(pipe.getItemsResultPDK());
            VLVChart.getData().clear();
            VLVChart.getData().addAll(pipe.getGraphic());
        }
        catch (NullPointerException | NumberFormatException e){

        }




    }

    /**
     * удаление элементов из listView, а так же из листа материалов
     */
    public void delete() {//удаление элементов из  listView и листа материалов
        try {
            int x = tableVLV.getSelectionModel().getSelectedIndex();
            tableVLV.getItems().remove(x);


            for (Material material : materials1) {
                if (allMaterials.get(x).getName().equals(material.getName()) && allMaterials.get(x).getMark().equals(material.getMark()) && material.getSquare() == allMaterials.get(x).getSquare()) {
                    materials1.remove(material);
                    break;
                }
            }
            for (Material material : materials2) {
                if (allMaterials.get(x).getName().equals(material.getName()) && allMaterials.get(x).getMark().equals(material.getMark()) && material.getSquare() == allMaterials.get(x).getSquare()) {
                    materials2.remove(material);
                    break;
                }
            }
            for (Material material : materials3) {
                if (allMaterials.get(x).getName().equals(material.getName()) && allMaterials.get(x).getMark().equals(material.getMark()) && material.getSquare() == allMaterials.get(x).getSquare()) {
                    materials3.remove(material);
                    break;
                }
            }
            allMaterials.remove(x);
        } catch (ArrayIndexOutOfBoundsException e) {

        }
    }

    /**
     * метод для отбора марки в зависимости от выбранного строительного материала
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void selectMark() throws SQLException, ClassNotFoundException {
        mark.getItems().clear();
        ResultSet rez;
        rez = dbConnect.dbExecuteQueryDao("SELECT mark.name FROM mark inner join buildMaterial on(mark.id=buildMaterial.id_mark) where buildMaterial.name='" + SM.getValue() + "'");
        while (rez.next()) {
            mark.getItems().add(rez.getString(1));
        }
    }

    /**
     * вывод результата в текстовый файл
     */
    public void saveTxt() {//вывод результата в текстовый файл
        try {
            pipe.calculate();
            Files.write(Paths.get("demo.txt"), pipe.getFullListAsString(), StandardOpenOption.CREATE);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message Here...");
            alert.setHeaderText("Готово");
            alert.setContentText("Отчет успешно сформирован!!!!");
            alert.showAndWait();
        } catch (IOException e) {

        }
    }

    public void clear() {
        tableVLV.getItems().clear();
    }
}
