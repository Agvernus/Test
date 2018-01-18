package myLibrary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Subset {
    private ArrayList<SubsetUnit> subsetUnits = new ArrayList<>();
    private Comparator comparatorBy1stNumber = new Comparator<SubsetUnit>() {
        @Override
        public int compare(SubsetUnit o1, SubsetUnit o2) {
            if (Double.compare(o1.getX1(), o2.getX1()) != 0) {
                return Double.compare(o1.getX1(), o2.getX1());
            }
            else if (o1.isLeftBracket() && !o2.isLeftBracket()) {
                return Double.compare(o1.getX1(), o2.getX1() + Double.MIN_VALUE);
            }
            else if (!o1.isLeftBracket() && o2.isLeftBracket()) {
                return Double.compare(o1.getX1() + Double.MIN_VALUE, o2.getX1());
            }
            else return 0;
        }
    };
    private Comparator comparatorBy2ndNumber = new Comparator<SubsetUnit>() {
        @Override
        public int compare(SubsetUnit o1, SubsetUnit o2) {
            if (Double.compare(o1.getX2(), o2.getX2()) != 0) {
                return Double.compare(o1.getX2(), o2.getX2());
            }
            else if (o1.isRightBracket() && !o2.isRightBracket()) {
                return Double.compare(o1.getX2(), o2.getX2() - Double.MIN_VALUE);
            }
            else if (!o1.isRightBracket() && o2.isRightBracket()) {
                return Double.compare(o1.getX2() - Double.MIN_VALUE, o2.getX2());
            }
            else return 0;
        }
    };

    public Subset() {}

    public Subset(Subset subset) {
        this.subsetUnits.addAll(subset.subsetUnits);
    }

    public Subset(ArrayList<SubsetUnit> subsetUnits) {
        this.subsetUnits.addAll(subsetUnits);
    }

    public Subset(SubsetUnit... subsetUnits) {
        this.subsetUnits.addAll(Arrays.asList(subsetUnits));
    }

    public ArrayList<SubsetUnit> getSubsetUnits() {
        return subsetUnits;
    }

    public void setSubsetUnits(Subset subset) {
        this.subsetUnits.addAll(subset.subsetUnits);
    }

    public void setSubsetUnits(ArrayList<SubsetUnit> subsetUnits) {
        this.subsetUnits.addAll(subsetUnits);
    }

    public void setSubsetUnits(SubsetUnit... subsetUnits) {
        this.subsetUnits.addAll(Arrays.asList(subsetUnits));
    }

    public void addSubsetUnits(SubsetUnit subsetUnit) {
        this.subsetUnits.add(subsetUnit);
    }

    public void removeSubsetUnits(SubsetUnit subsetUnit) {
        this.subsetUnits.remove(subsetUnit);
    }

    public Subset getIntersection(Subset subset) {
        ArrayList<SubsetUnit> commonSubsetUnitsSortedBy1stNumber = new ArrayList<>();
        ArrayList<SubsetUnit> commonSubsetUnitsSortedBy2ndNumber = new ArrayList<>();
        commonSubsetUnitsSortedBy1stNumber.addAll(this.subsetUnits);
        commonSubsetUnitsSortedBy1stNumber.addAll(subset.subsetUnits);
        commonSubsetUnitsSortedBy2ndNumber.addAll(this.subsetUnits);
        commonSubsetUnitsSortedBy2ndNumber.addAll(subset.subsetUnits);
        Collections.sort(commonSubsetUnitsSortedBy1stNumber, comparatorBy1stNumber);
        Collections.sort(commonSubsetUnitsSortedBy2ndNumber, comparatorBy2ndNumber);
        Subset result = new Subset();
        for (int i = 0; i < commonSubsetUnitsSortedBy1stNumber.size() - 1; i++) {
            SubsetUnit intersection = commonSubsetUnitsSortedBy1stNumber.get(i).getIntersection(commonSubsetUnitsSortedBy1stNumber.get(i+1));
            if (intersection != null) {
                result.addSubsetUnits(intersection);
            }
        }
        for (int i = 0; i < commonSubsetUnitsSortedBy2ndNumber.size() - 1; i++) {
            SubsetUnit intersection = commonSubsetUnitsSortedBy2ndNumber.get(i).getIntersection(commonSubsetUnitsSortedBy2ndNumber.get(i+1));
            if (intersection != null) {
                boolean label = true;
                for (int j = 0; j < result.subsetUnits.size(); j++) {
                    if (result.subsetUnits.get(j).equals(intersection)) label = false;
                }
                if (label) result.addSubsetUnits(intersection);
            }
        }
        Collections.sort(result.getSubsetUnits(), comparatorBy1stNumber);
        return result;
    }

    public double getNearestX(double x) {
        for (SubsetUnit s : subsetUnits) {
            if (s.isInhere(x)) return x;
        }
        double nearest = subsetUnits.get(0).getX1();
        double minimum = Math.abs(subsetUnits.get(0).getX1() - x);
        boolean bracket = subsetUnits.get(0).isLeftBracket();
        for (SubsetUnit s : subsetUnits) {
            if (Double.compare(Math.abs(s.getX1() - x), minimum) < 0) {
                nearest = s.getX1();
                minimum = Math.abs(s.getX1() - x);
                bracket = s.isLeftBracket();
            } else if (Double.compare(Math.abs(s.getX1() - x), minimum) == 0) {
                if (s.isLeftBracket() && !bracket) {
                    nearest = s.getX1();
                    minimum = Math.abs(s.getX1() - x);
                    bracket = true;
                }
            }
            if (Double.compare(Math.abs(s.getX2() - x), minimum) < 0) {
                nearest = s.getX2();
                minimum = Math.abs(s.getX2() - x);
                bracket = s.isRightBracket();
            } else if (Double.compare(Math.abs(s.getX2() - x), minimum) == 0) {
                if (s.isRightBracket() && !bracket) {
                    nearest = s.getX2();
                    minimum = Math.abs(s.getX2() - x);
                    bracket = true;
                }
            }
        }
        return nearest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subset subset = (Subset) o;

        return subsetUnits != null ? subsetUnits.equals(subset.subsetUnits) : subset.subsetUnits == null;
    }

    @Override
    public int hashCode() {
        return subsetUnits != null ? subsetUnits.hashCode() : 0;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < subsetUnits.size() - 1; i++) {
            buffer.append(subsetUnits.get(i).toString()).append(" U ");
        }
        buffer.append(subsetUnits.get(subsetUnits.size() - 1));
        return buffer.toString();
    }
}
