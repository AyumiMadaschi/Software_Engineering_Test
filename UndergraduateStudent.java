package assessments;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// Undergraduate student where Programming Test 30%, AI Project 70 and the Design vita doesnt count towards it */
public class UndergraduateStudent extends Student {
    public static final String TYPE = "UG";
    public static final Map<String, Double> WEIGHTINGS;
    static
    {
        Map<String, Double> w = new LinkedHashMap<>();
        w.put(AssessmentFeed.PROGRAMMING_TEST, 30.0);
        w.put(AssessmentFeed.AI_PROJECT, 70.0);
        WEIGHTINGS = Collections.unmodifiableMap(w);
    }

    public UndergraduateStudent(String cid, String name, List<Assessment> assessments)
    {
        super(cid, name, assessments);
    }

    @Override
    public String getType() { return TYPE; }
    @Override
    public Map<String, Double> getWeightings() { return WEIGHTINGS; }
}
