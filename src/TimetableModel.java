
import java.awt.Color;
import javax.swing.table.AbstractTableModel;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.UndoManager;

/**
 *
 * @author Sami
 */
public class TimetableModel extends AbstractTableModel {

    // Days serve as the columns titles
    private String[] days;
    // The timetable data itself
    private Object[][] data;
    // Colors of the cells
    private Color[][] colors;

    /**
     * Constructs a new TimetableModel based on column names
     * and timetable data.
     *
     * @param s Column titles.
     * @param o Table data.
     */
    public TimetableModel(String[] s, Object[][] o) {
        super();
        days = s;
        data = o;
        colors = new Color[o.length][o[0].length];
        // Init color value is null
        for (int i = 0; i < colors.length; i++)
            for (int j = 0; j < colors[i].length; j++)
                colors[i][j] = null;

    }

    /**
     * Constructs a new TimetableModel based on column names
     * and the number of rows.
     *
     * @param s Column titles.
     * @param rows Number of rows.
     */
    public TimetableModel(String[] s, int rows) {
        super();
        days = s;
        data = new Object[rows][days.length];
        colors = new Color[data.length][data[0].length];
        // Init color value is null
        for (int i = 0; i < colors.length; i++)
            for (int j = 0; j < colors[i].length; j++)
                colors[i][j] = null;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return days.length;
    }

    @Override
    public Object getValueAt(int i, int j) {
        return data[i][j];
    }

    @Override
    public String getColumnName(int c) {
        return days[c];
    }

    @Override
    public boolean isCellEditable(int r, int c) {
        return true;
    }

    @Override
    public void setValueAt(Object val, int r, int c) {
        // Get the old value
        Object oldValue = getValueAt(r, c);
        // Insert new value
        data[r][c] = val;
        fireTableCellUpdated(r,c);
        // Get the new value
        Object newValue = getValueAt(r, c);

        undos.addEdit(new AbstractUndoableEdit() {
            @Override
            public void undo() {
                super.undo();
                data[r][c] = oldValue;
                fireTableCellUpdated(r,c);
            }

            @Override
            public void redo() {
                super.redo();
                data[r][c] = newValue;
                fireTableCellUpdated(r,c);
            }
        });
    }

    /**
     * Sets the background color of the specified cell.
     *
     * @param color The cell's new background color.
     * @param r Row index.
     * @param c Column index.
     */
    void setColorAt(Color color, int r, int c) {
        // Get the old value
        Color oldValue = getColorAt(r, c);
        // Insert new value
        colors[r][c] = color;
        fireTableCellUpdated(r, c);
        // Get the new value
        Color newValue = getColorAt(r, c);

        undos.addEdit(new AbstractUndoableEdit() {
            @Override
            public void undo() {
                super.undo();
                colors[r][c] = oldValue;
                fireTableCellUpdated(r,c);
            }

            @Override
            public void redo() {
                super.redo();
                colors[r][c] = newValue;
                fireTableCellUpdated(r,c);
            }
        });
    }

    /**
     * Returns color at specified point
     *
     * @param i Row index.
     * @param i1 Column index.
     * @return Background color of the specified cell.
     */
    Color getColorAt(int i, int i1) {
        if (colors != null)
            return colors[i][i1];
        return null;
    }

    // Stores the undos
    UndoManager undos = new UndoManager();

}
