package assessments;

import java.util.Arrays;
import java.util.List;

/// AI Assisted Software Project part
public class AIProject extends Assessment {
    private static final double FUNCTIONALITY_WEIGHT = 0.4;
    private static final double VERIFICATION_WEIGHT = 0.6;
    private final double functionalityMark;
    private final double verificationMark;

    public AIProject(double functionalityMark, double verificationMark)
    {
        super(AssessmentFeed.AI_PROJECT, 100, true);
        this.functionalityMark = functionalityMark;
        this.verificationMark = verificationMark;
    }

    public static AIProject fromFeed(String cid)
    {
        String t = AssessmentFeed.AI_PROJECT;
        return new AIProject(AssessmentFeed.getResult(cid, t, AssessmentFeed.FUNCTIONALITY_MARK),
                AssessmentFeed.getResult(cid, t, AssessmentFeed.VERIFICATION_MARK));
    }

    @Override
    public double getFinalPercentage()
    {
        return FUNCTIONALITY_WEIGHT * functionalityMark + VERIFICATION_WEIGHT * verificationMark;
    }

    @Override
    protected List<SubMark> createSubMarks()
    {
        return Arrays.asList(new SubMark(AssessmentFeed.FUNCTIONALITY_MARK, functionalityMark), new SubMark(AssessmentFeed.VERIFICATION_MARK, verificationMark));
    }
}
