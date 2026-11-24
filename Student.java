package assessments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

// Abstract student so the subclasses define the type and assessment weightings e
public abstract class Student
{
    public static double AI_DISCREPANCY_THRESHOLD = 20.0;

    private final String cid;
    private final String name;
    private final List<Assessment> assessments;

    protected Student(String cid, String name, List<Assessment> assessments)
    {
        this.cid = cid;
        this.name = name;
        this.assessments = new ArrayList<>(assessments);
    }

    public String getCid() { return cid; }

    public String getName()
        { return name; }
    public List<Assessment> getAssessments()
    {
        return Collections.unmodifiableList(assessments);
    }

    /** Undergrad or msc */
    public abstract String getType();

    /** Weighting for each assessment title this student takes. */
    public abstract Map<String, Double> getWeightings();
    public Assessment getAssessment(String title) {
        for (Assessment a : assessments) if (a.getTitle().equals(title)) return a;
        return null; //
    }

    /** Weighted mean of final percentages */
    public double getModuleMark() {
        double weighted = 0, totalWeight = 0;
        for (Assessment a : assessments)
        {
            Double w = getWeightings().get(a.getTitle());
            if (w == null)
                continue;
            weighted += w * a.getFinalPercentage();
            totalWeight += w;
        }
        if (totalWeight == 0)
            return 0;
        else
        {
            return weighted / totalWeight;
        }
    }

    private double average(boolean aiPermitted) {
        double sum = 0;
        int n = 0;
        for (Assessment a : assessments) {
            if (a.isAiPermitted() == aiPermitted) {
                sum += a.getFinalPercentage();
                n++;
            }
        }
        if (n == 0)
        {
            return Double.NaN;
        }
        else
        {
            return sum / n;
        }
    }
    public double getAiAverage() { return average(true); }
    public double getNonAiAverage() { return average(false); }
    public boolean hasAiDiscrepancy() {
        double ai = getAiAverage(), nonAi = getNonAiAverage();
        if (Double.isNaN(ai) || Double.isNaN(nonAi))
            return false;
        return ai - nonAi >= AI_DISCREPANCY_THRESHOLD;
    }
}
