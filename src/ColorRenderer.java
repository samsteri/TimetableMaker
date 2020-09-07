
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Sami
 */
public class ColorRenderer extends DefaultTableCellRenderer {

    /**
     * Constructor for ColorRenderer.
     */
    public ColorRenderer() {
        setOpaque(true);
        setFont(getFont().deriveFont(Font.BOLD));
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
    }

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
        // Get the model, so we can get the cell's background color
        TimetableModel ttm = (TimetableModel)jtable.getModel();
        // Set the background color to specified. Null if nothing is set.
        setBackground(ttm.getColorAt(i, i1));

        // Set the actual value of the cell.
        setValue(o);

        // Is selected
        if (bln)
            setBackground(jtable.getSelectionBackground());

        return this;
    }

    @Override
    public void setValue(Object v) {
        super.setValue(v);
    }

}
