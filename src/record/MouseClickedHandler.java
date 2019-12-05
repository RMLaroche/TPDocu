package record;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.RandomAccessFile;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import record.HardwareStore.UpdateRec;

public class MouseClickedHandler extends MouseAdapter {
    JTable table;
    String pData[] [], columnNames[] ;
    RandomAccessFile f ;
    
    MouseClickedHandler( RandomAccessFile fPassed , JTable tablePassed ,
                  String p_Data[] []) {
        table = tablePassed ;
        pData = p_Data ;
        f     = fPassed ;

    }
    public void mouseClicked( MouseEvent e )    {
       if ( e.getSource() == table) {
           int ii = table.getSelectedRow() ;
           JOptionPane.showMessageDialog(null,
                  "Enter the record ID to be updated and press enter.",
                  "Update Record", JOptionPane.INFORMATION_MESSAGE) ;
           UpdateRec update = new UpdateRec( hws, f , pData , ii ) ;
           if (  ii < 250) {
              update.setVisible( true );
              table.repaint();
           }
       }
    }
 }