package assessments;

// An individual value recorded within an assessment. 
public final class SubMark {
    private final String title;
    private final double value;
    private final boolean yesNo;

    public SubMark(String title, double value)
    {
        this(title, value, false);
    }

    public SubMark(String title, double value, boolean yesNo)
    {
        this.title = title;
        this.value = value;
        this.yesNo = yesNo;
    }

    public String getTitle()
    {
        return title;
    }

    public double getValue()
        {
            return value;
        }

    public String getDisplayValue() {
        if (yesNo) return value != 0.0 ? "Yes" : "No";
        return String.format("%.2f%%", value);
    }
}
