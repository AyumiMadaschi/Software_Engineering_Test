package assessments;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class StudentRepository {
    private StudentRepository() { }
    public static List<Student> loadAll()
    {
        List<Student> students = new ArrayList<>();
        for (String cid : AssessmentFeed.getCIDs())
            students.add(load(cid));
        return students;
    }
    public static Student load(String cid) {
        String name = AssessmentFeed.getStudentName(cid);
        String type = AssessmentFeed.getStudentType(cid);
        Map<String, Double> weightings = weightingsFor(type);
        List<Assessment> assessments = new ArrayList<>();
        for (String title : weightings.keySet())
        {
            assessments.add(AssessmentFactory.load(title, cid));
        }
        if (MScStudent.TYPE.equals(type))
            return new MScStudent(cid, name, assessments);
        return new UndergraduateStudent(cid, name, assessments);
    }

    private static Map<String, Double> weightingsFor(String type) {
        if (UndergraduateStudent.TYPE.equals(type))
            return UndergraduateStudent.WEIGHTINGS;
        if (MScStudent.TYPE.equals(type))
            return MScStudent.WEIGHTINGS;
        return null;
    }

    public static List<String> allAssessmentTitles(List<Student> students) {
        Set<String> titles = new LinkedHashSet<>();
        for (Student s : students) {
            for (Assessment a : s.getAssessments())
                titles.add(a.getTitle());
        }
        return new ArrayList<>(titles);
    }
}
