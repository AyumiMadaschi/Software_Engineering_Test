package assessments;
import java.util.Arrays;
import java.util.List;

// Software Design Viva  MSc only do this one
public class DesignViva extends Assessment {
    private final double DESIGN_WEIGHT = 0.5;
    private static double EXPLANATION_WEIGHT = 0.5;
    private final double designMark;
    private double explanationMark;

    public DesignViva(double designMark, double explanationMark)
    {
        super(AssessmentFeed.DESIGN_VIVA, 100, false);
        this.designMark = designMark;
        this.explanationMark = explanationMark;
    }

    public static DesignViva fromFeed(String cid)
    {
        String t = AssessmentFeed.DESIGN_VIVA;
        return new DesignViva(AssessmentFeed.getResult(cid, t, AssessmentFeed.DESIGN_MARK), AssessmentFeed.getResult(cid, t, AssessmentFeed.EXPLANATION_MARK));
    }

    @Override
    public double getFinalPercentage()
    {
        return DESIGN_WEIGHT * designMark + EXPLANATION_WEIGHT * explanationMark;
    }

    @Override
    protected List<SubMark> createSubMarks()
    {
        return Arrays.asList(new SubMark(AssessmentFeed.DESIGN_MARK, designMark), new SubMark(AssessmentFeed.EXPLANATION_MARK, explanationMark));
    }
}
