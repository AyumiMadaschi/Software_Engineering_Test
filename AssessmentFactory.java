package assessments;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public final class AssessmentFactory
{
    private static final Map<String, Function<String, Assessment>> LOADERS = new LinkedHashMap<>();
    // should keep it ordered
    static
    {
        LOADERS.put(AssessmentFeed.PROGRAMMING_TEST, ProgrammingTest::fromFeed);
        LOADERS.put(AssessmentFeed.AI_PROJECT, AIProject::fromFeed);
        LOADERS.put(AssessmentFeed.DESIGN_VIVA, DesignViva::fromFeed);
    }
    public static Assessment load(String title, String cid) {
        Function<String, Assessment> loader = LOADERS.get(title);
        return loader.apply(cid);
    }
}
