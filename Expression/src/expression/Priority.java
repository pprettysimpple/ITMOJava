public enum Priority {
    LOW_S(1, 0),
    LOW_A(1, 1),
    HIGH_D(4, 0),
    HIGH_M(4, 1),
    VAR(10, 1);

    private int value;
    private  int subValue;

    Priority(int value, int subValue) {
        this.value = value;
        this.subValue = subValue;
    }

    public int getValue() {
        return value;
    }

    public int getSubValue() {
        return subValue;
    }
}
