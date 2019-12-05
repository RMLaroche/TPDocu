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
          sysPrintDebug ( "The Lawn Mower menu Item was selected.\n" );

          displayContent( "Lawn Mowers" ) ;
       }
       else if ( e.getSource() == lmtMI ) {
          sysPrintDebug ( "The Lawn Mower Tractor menu Item was selected.\n" );

          displayContent( "Lawn Tractor Mowers" ) ;
       }
       else if ( e.getSource() == hdMI ) {
          sysPrintDebug ( "The Hand Drill Tools menu Item was selected.\n" );

          displayContent( "Hand Drill Tools" ) ;
       }
       else if ( e.getSource() == dpMI ) {
          sysPrintDebug ("The Drill Press Power Tools menu Item was selected.\n" );

          displayContent( "Drill Press Power Tools" ) ;
       }
       else if ( e.getSource() == csMI ) {
          sysPrintDebug ("The Circular Saws Tools menu Item was selected.\n" );

          displayContent( "Circular Saws" ) ;
       }
       else if ( e.getSource() == hamMI ) {
          sysPrintDebug ("The Hammer menu Item was selected.\n" );

          displayContent( "Hammers" ) ;
       }
       else if ( e.getSource() == tabMI ) {
          sysPrintDebug ("The Table Saws menu Item was selected.\n" );

          displayContent( "Table Saws" ) ;
       }
       else if ( e.getSource() == bandMI ) {
          sysPrintDebug ("The Band Saws menu Item was selected.\n" );

          displayContent( "Band Saws" ) ;
       }
       else if ( e.getSource() ==  sandMI ) {
          sysPrintDebug ("The Sanders menu Item was selected.\n" );

          displayContent( "Sanders" ) ;
       }
       else if ( e.getSource() == stapMI ) {
          sysPrintDebug ("The Staplers menu Item was selected.\n" );

          displayContent( "Staplers" ) ;
       }
       else if ( e.getSource() == wdvMI ) {
          sysPrintDebug ("The Wet-Dry Vacs menu Item was selected.\n" );
         // ListRecs BPTRecs = new ListRecs( hws , "WDV", "Wet-Dry Vacs" );
       }
       else if ( e.getSource() == sccMI ) {
          sysPrintDebug ("The Storage, Chests & Cabinets menu Item was selected.\n" );
          //ListRecs BPTRecs = new ListRecs( hws , "SCC", "Storage, Chests & Cabinets" );
       }
       else if ( e.getSource() == deleteMI ) {
          sysPrintDebug ("The Delete Record Dialog was made visible.\n") ;
          //DeleteRec( HardwareStore hw_store,  RandomAccessFile f,
                // JTable tab, String p_Data[] []  )
          deleteRec = new DeleteRec( hws, file, table, pData );
          deleteRec.setVisible( true );
       }
       else if ( e.getSource() == addMI ) {
          sysPrintDebug ("The Add menu Item was selected.\n" );
          pWord.displayDialog( "add" ) ;
       }
       else if ( e.getSource() == updateMI ) {
          sysPrintDebug ("The Update menu Item was selected.\n" );
          update = new UpdateRec( hws, file,  pData, -1 );
          update.setVisible( true );
       }
       else if ( e.getSource() == listAllMI ) {
          sysPrintDebug ("The List All menu Item was selected.\n" );
          //listRecs.setVisible( true );
       }
       else if ( e.getSource() == debugON ) {
          myDebug = true ;
          sysPrintDebug ("Debugging for this execution is turned on.\n" );
       }
       else if ( e.getSource() == debugOFF ) {
          sysPrintDebug ("Debugging for this execution is turned off.\n" );
          myDebug = false ;
       }
       else if ( e.getSource() == helpHWMI ) {
          sysPrintDebug ("The Help menu Item was selected.\n" );
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
             sysPrintDebug ("Process exit code is: " +
                               child.exitValue());
          }
          catch(IOException e2) {
             sysPrintDebug (
                "IOException starting process!");
          }
          catch(InterruptedException e3) {
             System.err.println(
                   "Interrupted waiting for process!");
          }
       }
       else if ( e.getSource() == aboutHWMI ) {
          sysPrintDebug ("The About menu Item was selected.\n" );
          Runtime rt = Runtime.getRuntime();
          String[] callAndArgs = { "c:\\Program Files\\Internet Explorer\\IEXPLORE.exe" ,
                         "http://www.sumtotalz.com/TotalAppsWorks/ProgrammingResource.html" };
          try {
             Process child = rt.exec(callAndArgs);
             child.waitFor();
             sysPrintDebug ("Process exit code is: " +
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