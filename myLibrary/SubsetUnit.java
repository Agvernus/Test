package myLibrary;

public class SubsetUnit {
    private boolean leftBracket;
    private double x1;
    private double x2;
    private boolean rightBracket;

    public SubsetUnit() {}

    public SubsetUnit(SubsetUnit subsetUnit) {
        this.leftBracket = subsetUnit.leftBracket;
        this.x1 = subsetUnit.getX1();
        this.x2 = subsetUnit.getX2();
        this.rightBracket = subsetUnit.rightBracket;
    }

    public SubsetUnit(boolean leftBracket, double x1, double x2, boolean rightBracket) {
        this.leftBracket = leftBracket;
        this.x1 = x1;
        this.x2 = x2;
        this.rightBracket = rightBracket;
    }

    public boolean isLeftBracket() {
        return leftBracket;
    }

    public void setLeftBracket(boolean leftBracket) {
        this.leftBracket = leftBracket;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public boolean isRightBracket() {
        return rightBracket;
    }

    public void setRightBracket(boolean rightBracket) {
        this.rightBracket = rightBracket;
    }

    public SubsetUnit getIntersection(SubsetUnit subsetUnit) {
        if (Double.compare(this.x2, subsetUnit.x1) > 0 || (Double.compare(this.x2, subsetUnit.x1) == 0 && this.rightBracket && subsetUnit.leftBracket)) {
            if (Double.compare(this.x1, subsetUnit.x2) < 0 || (Double.compare(this.x1, subsetUnit.x2) == 0 && this.leftBracket && subsetUnit.rightBracket)) {
                SubsetUnit result = new SubsetUnit();
                if (Double.compare(this.x1, subsetUnit.x1) > 0) {
                    result.setX1(this.x1);
                    result.setLeftBracket(this.leftBracket);
                } else if (Double.compare(this.x1, subsetUnit.x1) < 0) {
                    result.setX1(subsetUnit.x1);
                    result.setLeftBracket(subsetUnit.leftBracket);
                } else if (this.leftBracket && subsetUnit.leftBracket) {
                    result.setX1(this.x1);
                    result.setLeftBracket(true);
                } else {
                    result.setX1(this.x1);
                    result.setLeftBracket(false);
                }
                if (Double.compare(this.x2, subsetUnit.x2) < 0) {
                    result.setX2(this.x2);
                    result.setRightBracket(this.rightBracket);
                } else if (Double.compare(this.x2, subsetUnit.x2) > 0) {
                    result.setX2(subsetUnit.x2);
                    result.setRightBracket(subsetUnit.rightBracket);
                } else if (this.rightBracket && subsetUnit.rightBracket) {
                    result.setX2(this.x2);
                    result.setRightBracket(true);
                } else {
                    result.setX2(this.x2);
                    result.setRightBracket(false);
                }
                return result;
            }
            else return null;
        }
        else return null;
    }

    public boolean isInhere(double num) {
        if (((Double.compare(num, x1) > 0) && (Double.compare(num, x2) < 0)) || ((Double.compare(num, x1) == 0) && (leftBracket)) || ((Double.compare(num, x2) == 0) && (rightBracket))) return true;
        else return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubsetUnit subsetUnit = (SubsetUnit) o;

        if (leftBracket != subsetUnit.leftBracket) return false;
        if (Double.compare(subsetUnit.x1, x1) != 0) return false;
        if (Double.compare(subsetUnit.x2, x2) != 0) return false;
        return rightBracket == subsetUnit.rightBracket;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (leftBracket ? 1 : 0);
        temp = Double.doubleToLongBits(x1);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(x2);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (rightBracket ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return (leftBracket ? "[" : "(") + x1 + ", " + x2 + (rightBracket ? "]" : ")");
    }
}
