package record;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import record.HardwareStore.DeleteRec;

public class MenuHandler implements ActionListener {
    public void actionPerformed( ActionEvent e )  {

       if ( e.getSource() == eMI ) {
       /**The Exit menu Item was selected. */
          cleanup();
       }
       else if ( e.getSource() == lmMI ) {
          sysPrint ( "The Lawn Mower menu Item was selected.\n" );

          display( "Lawn Mowers" ) ;
       }
       else if ( e.getSource() == lmtMI ) {
          sysPrint ( "The Lawn Mower Tractor menu Item was selected.\n" );

          display( "Lawn Tractor Mowers" ) ;
       }
       else if ( e.getSource() == hdMI ) {
          sysPrint ( "The Hand Drill Tools menu Item was selected.\n" );

          display( "Hand Drill Tools" ) ;
       }
       else if ( e.getSource() == dpMI ) {
          sysPrint ("The Drill Press Power Tools menu Item was selected.\n" );

          display( "Drill Press Power Tools" ) ;
       }
       else if ( e.getSource() == csMI ) {
          sysPrint ("The Circular Saws Tools menu Item was selected.\n" );

          display( "Circular Saws" ) ;
       }
       else if ( e.getSource() == hamMI ) {
          sysPrint ("The Hammer menu Item was selected.\n" );

          display( "Hammers" ) ;
       }
       else if ( e.getSource() == tabMI ) {
          sysPrint ("The Table Saws menu Item was selected.\n" );

          display( "Table Saws" ) ;
       }
       else if ( e.getSource() == bandMI ) {
          sysPrint ("The Band Saws menu Item was selected.\n" );

          display( "Band Saws" ) ;
       }
       else if ( e.getSource() ==  sandMI ) {
          sysPrint ("The Sanders menu Item was selected.\n" );

          display( "Sanders" ) ;
       }
       else if ( e.getSource() == stapMI ) {
          sysPrint ("The Staplers menu Item was selected.\n" );

          display( "Staplers" ) ;
       }
       else if ( e.getSource() == wdvMI ) {
          sysPrint ("The Wet-Dry Vacs menu Item was selected.\n" );
         // ListRecs BPTRecs = new ListRecs( hws , "WDV", "Wet-Dry Vacs" );
       }
       else if ( e.getSource() == sccMI ) {
          sysPrint ("The Storage, Chests & Cabinets menu Item was selected.\n" );
          //ListRecs BPTRecs = new ListRecs( hws , "SCC", "Storage, Chests & Cabinets" );
       }
       else if ( e.getSource() == deleteMI ) {
          sysPrint ("The Delete Record Dialog was made visible.\n") ;
          //DeleteRec( HardwareStore hw_store,  RandomAccessFile f,
                // JTable tab, String p_Data[] []  )
          deleteRec = new DeleteRec( hws, file, table, pData );
          deleteRec.setVisible( true );
       }
       else if ( e.getSource() == addMI ) {
          sysPrint ("The Add menu Item was selected.\n" );
          pWord.displayDialog( "add" ) ;
       }
       else if ( e.getSource() == updateMI ) {
          sysPrint ("The Update menu Item was selected.\n" );
          update = new UpdateRec( hws, file,  pData, -1 );
          update.setVisible( true );
       }
       else if ( e.getSource() == listAllMI ) {
          sysPrint ("The List All menu Item was selected.\n" );
          //listRecs.setVisible( true );
       }
       else if ( e.getSource() == debugON ) {
          myDebug = true ;
          sysPrint ("Debugging for this execution is turned on.\n" );
       }
       else if ( e.getSource() == debugOFF ) {
          sysPrint ("Debugging for this execution is turned off.\n" );
          myDebug = false ;
       }
       else if ( e.getSource() == helpHWMI ) {
          sysPrint ("The Help menu Item was selected.\n" );
          File hd = new File("HW_Tutorial.html");
          //File net = new File("Netscp.exe");
          //System.out.println( "the path for help_doc is " + hd.getAbsolutePath() );
          //System.out.println( "the path for netscape is " + net.getAbsolutePath() );

          Runtime rt = Runtime.getRuntime();
          //String[] callAndArgs = { "d:\\Program Files\\netscape\\netscape\\Netscp.exe" ,
          String[] callAndArgs = { "c:\\Program Files\\Internet Explorer\\IEXPLORE.exe" ,
                       "" + hd.getAbsolutePath() };

          try {

             Process child = rt.exec( callAndArgs );
             child.waitFor();
             sysPrint ("Process exit code is: " +
                               child.exitValue());
          }
          catch(IOException e2) {
             sysPrint (
                "IOException starting process!");
          }
          catch(InterruptedException e3) {
             System.err.println(
                   "Interrupted waiting for process!");
          }
       }
       else if ( e.getSource() == aboutHWMI ) {
          sysPrint ("The About menu Item was selected.\n" );
          Runtime rt = Runtime.getRuntime();
          String[] callAndArgs = { "c:\\Program Files\\Internet Explorer\\IEXPLORE.exe" ,
                         "http://www.sumtotalz.com/TotalAppsWorks/ProgrammingResource.html" };
          try {
             Process child = rt.exec(callAndArgs);
             child.waitFor();
             sysPrint ("Process exit code is: " +
                               child.exitValue());
          }
          catch(IOException e2) {
             System.err.println(
                "IOException starting process!");
          }
          catch(InterruptedException e3) {
             System.err.println(
                   "Interrupted waiting for process!");
          }
       }
       String current = ( String ) e.getActionCommand();
    }
 }