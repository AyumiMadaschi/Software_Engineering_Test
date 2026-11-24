package assessments;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// MSc student: Programming Test 25%, AI Project 45%, Design Viva 30%
public class MScStudent extends Student {
    public static final String TYPE = "MSc";
    public static final Map<String, Double> WEIGHTINGS;

    static {
        Map<String, Double> w = new LinkedHashMap<>();
        w.put(AssessmentFeed.PROGRAMMING_TEST, 25.0);
        w.put(AssessmentFeed.AI_PROJECT, 45.0);
        w.put(AssessmentFeed.DESIGN_VIVA, 30.0);
        WEIGHTINGS = Collections.unmodifiableMap(w);
    }
    public MScStudent(String cid, String name, List<Assessment> assessments)
    {
        super(cid, name, assessments);
    }

    public String getType()
    {
        return TYPE;
    }

    @Override
    public Map<String, Double> getWeightings() { return WEIGHTINGS; }
}
