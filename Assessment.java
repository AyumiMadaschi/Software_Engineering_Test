package assessments;

import java.util.Collections;
import java.util.List;

public abstract class Assessment {
    private final String title;
    private final double maxMark;
    private final boolean aiPermitted;

    // Anyone in the package can us
    protected Assessment(String title, double maxMark, boolean aiPermitted)
    {
        this.title = title;
        this.maxMark = maxMark;
        this.aiPermitted = aiPermitted;
    }

    public String getTitle()
    {
        return title; }

    public double getMaxMark()
    { return maxMark; }

    public boolean isAiPermitted() { return aiPermitted; }
    public abstract double getFinalPercentage();
    protected abstract List<SubMark> createSubMarks();

    public List<SubMark> getSubMarks() {
        return Collections.unmodifiableList(createSubMarks());
    }

    @Override
    public String toString() {
        return String.format("%s (%s): %.2f%%", title,
                aiPermitted ? "AI permitted" : "AI not permitted", getFinalPercentage());
    }
}
