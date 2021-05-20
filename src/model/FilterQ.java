package model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Класс для расчета выделения химического вещества
 */
public class FilterQ implements Filter {

    /**
     * Переменная: список строительных конструкций
     */
    private final ArrayList<Construction> constructions;
    /**
     * Переменная: список химических элементов
     */
    private ArrayList<ChemicalSubstance> chemicalSubstances = new ArrayList<>();
    /**
     * Переменная: для работы с базой данных
     */
    private DAO dataBase;

    /**
     * Конструктор класса
     * @param constructions
     * @param dataBase
     */
    public FilterQ(ArrayList<Construction> constructions, DAO dataBase) {
        this.constructions = constructions;
        this.dataBase = dataBase;
    }

    /**
     * Фильтр для расчета выделения химического вещества
     * @param x
     * @return
     */
    @Override
    public ArrayList<ChemicalSubstance> filter(ArrayList<ChemicalSubstance> x) {
        constructions.forEach(construction -> {
            construction.getMaterials().forEach(material -> {
                ResultSet resultSet = null;
                try {
                    resultSet = dataBase.dbExecuteQueryDao(
                            "SELECT chemicalSubstance.name, chemicalSubstance.emission, chemicalSubstance.PDK, buildMaterial.name, buildConstruction.name FROM chemicalSubstance " +
                                    "INNER JOIN chemicalSubstance_buildMaterial ON (chemicalSubstance.id = chemicalSubstance_buildMaterial.id_chemicalSubstance) " +
                                    "INNER JOIN buildMaterial ON (buildMaterial.id = chemicalSubstance_buildMaterial.id_buildMaterial) " +
                                    "INNER JOIN buildConstruction ON (buildConstruction.id = buildMaterial.id_buildConstruction) " +
                                    "INNER JOIN mark ON (mark.id = buildMaterial.id_mark) " +
                                    "WHERE buildMaterial.name = '" + material.getName() + "' AND mark.name = '" + material.getMark() + "';");
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                while (true) {
                    try {
                        if (!resultSet.next())
                            break;
                        else {
                            chemicalSubstances.add(new ChemicalSubstance(resultSet.getString(1), Double.parseDouble(resultSet.getString(2)), Double.parseDouble(resultSet.getString(3)), resultSet.getString(4), resultSet.getString(5)));
                            x.add(new ChemicalSubstance(resultSet.getString(1), Double.parseDouble(resultSet.getString(2)) * material.getSquare(), Double.parseDouble(resultSet.getString(3)), resultSet.getString(4), resultSet.getString(5)));
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
        });
        return x;
    }

}
