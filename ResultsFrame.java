package assessments;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

// grid with one row per student and the AI-discrepancy rows are in red
public class ResultsFrame extends JFrame {
    private static final int FIXED_COLUMNS = 3;

    public ResultsFrame(List<Student> students) {
        super("AI-Aware Software Engineering Assessment Results");
        ResultsTableModel model = new ResultsTableModel(students);
        JTable table = new JTable(model);
        table.setDefaultRenderer(Object.class, new DiscrepancyRenderer(model));
        table.setRowHeight(24);
        table.getTableHeader().setReorderingAllowed(false);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(new JLabel("  Red rows: AI-permitted average at least " + (int) Student.AI_DISCREPANCY_THRESHOLD + " percentage points above non-AI average."), BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 250);
        setLocationRelativeTo(null);
    }

    private static final class ResultsTableModel extends AbstractTableModel {
        private final List<Student> students;
        private final List<String> titles;
        ResultsTableModel(List<Student> students)
        {
            this.students = students;
            this.titles = StudentRepository.allAssessmentTitles(students);
        }

        Student getStudent(int row) { return students.get(row); }

        @Override
        public int getRowCount() {
            return students.size();
        }
        public int getColumnCount()
        {
            return FIXED_COLUMNS + titles.size() + 1;
        }

        @Override
        public String getColumnName(int col) {
            switch (col) {
                case 0: return "Name";
                case 1: return "CID";
                case 2: return "Type";
                default:
                    if (col - FIXED_COLUMNS < titles.size()) return titles.get(col - FIXED_COLUMNS);
                    return "Module Mark";
            }
        }

        @Override
        public Object getValueAt(int row, int col) {
            Student s = students.get(row);
            switch (col) {
                case 0: return s.getName();
                case 1: return s.getCid();
                case 2: return s.getType();
                default:
                    if (col - FIXED_COLUMNS < titles.size())
                    {
                        Assessment a = s.getAssessment(titles.get(col - FIXED_COLUMNS));
                        return a == null ? "N/A" : String.format("%.2f%%", a.getFinalPercentage());
                    }
                    return String.format("%.2f%%", s.getModuleMark());
            }
        }
    }

    private static final class DiscrepancyRenderer extends DefaultTableCellRenderer {
        private final ResultsTableModel model;
        DiscrepancyRenderer(ResultsTableModel model) { this.model = model; }
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            Student s = model.getStudent(table.convertRowIndexToModel(row));
            if (s.hasAiDiscrepancy())
            {
                setBackground(Color.RED);
                setForeground(Color.WHITE);
            } else if (isSelected)
            {
                setBackground(table.getSelectionBackground());
                setForeground(table.getSelectionForeground());
            } else
            {
                setBackground(table.getBackground());
                setForeground(table.getForeground());
            }
            return this;
        }
    }
}
