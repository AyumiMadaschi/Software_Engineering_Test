package assessments;

import java.util.Arrays;
import java.util.List;

/** Independent Programming Test the AI not permitted one. */
public class ProgrammingTest extends Assessment {
    private static final double UNEXPLAINED_CAP = 40;
    private final double programMark;
    private final boolean explainedSuccessfully;

    public ProgrammingTest(double programMark, boolean explainedSuccessfully)
    {
        super(AssessmentFeed.PROGRAMMING_TEST, 100.0, false);
        this.programMark = programMark;
        this.explainedSuccessfully = explainedSuccessfully;
    }
    public static ProgrammingTest fromFeed(String cid)
    {
        String t = AssessmentFeed.PROGRAMMING_TEST;
        return new ProgrammingTest(AssessmentFeed.getResult(cid, t, AssessmentFeed.PROGRAM_MARK),
                AssessmentFeed.getResult(cid, t, AssessmentFeed.EXPLAINED_SUCCESSFULLY) != 0.0);
    }

    @Override
    public double getFinalPercentage()
    {
        return explainedSuccessfully ? programMark : Math.min(programMark, UNEXPLAINED_CAP);
    }

    @Override
    protected List<SubMark> createSubMarks()
    {
        return Arrays.asList(new SubMark(AssessmentFeed.PROGRAM_MARK, programMark),
                new SubMark(AssessmentFeed.EXPLAINED_SUCCESSFULLY, explainedSuccessfully ? 1.0 : 0, true));
    }
}
