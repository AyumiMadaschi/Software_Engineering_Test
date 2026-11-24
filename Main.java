package assessments;

import java.util.List;
import javax.swing.SwingUtilities;

// prints the report and shows the resutls grid
public class Main {
    public static void main(String[] args) {
        List<Student> students = StudentRepository.loadAll();
        new ReportPrinter(System.out).print(students);
        SwingUtilities.invokeLater(() -> new ResultsFrame(students).setVisible(true));
    }
}
