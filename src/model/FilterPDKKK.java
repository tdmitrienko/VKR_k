package model;

import java.util.ArrayList;

/**
 * Класс для рассчета совокупного выделения по каждому химическому элементу
 */
public class FilterPDKKK implements Filter{
    /**
     * Фильтр: рассчет совокупного выделения по каждому химическому элементу
     * @param x
     * @return
     */
    @Override
    public ArrayList<ChemicalSubstance> filter(ArrayList<ChemicalSubstance> x) {
        for (int i = 0; i < x.size(); i++)
            for (int j = i + 1; j < x.size(); j++) {
                if (x.get(i).getName().equals(x.get(j).getName()) && x.get(i).getConstruction().equals(x.get(j).getConstruction())) {//если названия химических элементов по одной группе строительных конструкций совпадают,то складываем их расчетную концентрацию выделения вредных хим веществ
                    x.get(i).setEmission(x.get(i).getEmission() + x.get(j).getEmission());
                    x.remove(j);
                    j--;
                }
            }
        return x;
    }
}
