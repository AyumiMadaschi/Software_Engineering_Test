package assessments;

import java.io.PrintStream;
import java.util.List;

// prints a report of assessment results to the terminal
public class ReportPrinter {
    private final PrintStream out;

    public ReportPrinter(PrintStream out) {
        this.out = out;
    }

    public void print(List<Student> students) {
        out.println("Technical Assessment Report ");
        for (Student s : students) printStudent(s);
    }

    private void printStudent(Student s) {
        out.println();
        out.printf("Name: %s , CID: %s , Type: %s%n", s.getName(), s.getCid(), s.getType());
        for (Assessment a : s.getAssessments()) {
            out.printf("%s (%s, weighting %.0f%%): %.2f%%%n", a.getTitle(),
                    a.isAiPermitted() ? "AI permitted" : "AI not permitted",
                    s.getWeightings().get(a.getTitle()), a.getFinalPercentage());
            for (SubMark m : a.getSubMarks())
            {
                out.printf("%s: %s%n", m.getTitle(), m.getDisplayValue());
            }
        }
        out.printf("Overall weighted module mark: %.2f%%%n", s.getModuleMark());
        out.printf("AI permitted average: %.2f%% vs Non-AI average: %.2f%%%n",
                s.getAiAverage(), s.getNonAiAverage());
        out.println("AI discrepancy?: " + (s.hasAiDiscrepancy() ? "YES" : "No :)"));
    }
}
