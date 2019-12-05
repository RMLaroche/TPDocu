/** ***************************************************
 * File:  HardwareStore.java
 *
 *    This program reads a random access file sequentially,
 *    updates data already written to the file, creates new
 *    data to be placed in the file, and deletes data
 *    already in the file.
 *
 *
 * Copyright (c) 2002-2003 Advanced Applications Total Applications Works.
 * (AATAW)  All Rights Reserved.
 *
 * AATAW grants you ("Licensee") a non-exclusive, royalty free, license to use,
 * modify and redistribute this software in source and binary code form,
 * provided that i) this copyright notice and license appear on all copies of
 * the software; and ii) Licensee does not utilize the software in a manner
 * which is disparaging to AATAW.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. AATAW AND ITS LICENSORS SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING
 * OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL AATAW OR ITS
 * LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
 * OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 *
 * This software is not designed or intended for use in on-line control of
 * aircraft, air traffic, aircraft navigation or aircraft communications; or in
 * the design, construction, operation or maintenance of any nuclear
 * facility. Licensee represents and warrants that it will not use or
 * redistribute the Software for such purposes.
 * 
 *
 *
 * <p>Title: HardwareStore </p>
 * <p>Description: The HardwareStore application is a program that is used to display
 * hardware items and it allows these items to be created, updated, and/or
 * deleted.</p>
 * <p>Copyright: Copyright (c)</p>
 * <p>Company: TAW</p>
 * @author unascribed
 * @version 2.0
 */

package record;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.util.*;
import java.lang.Runtime;
import java.util.List;

public class HardwareStore extends JFrame implements ActionListener {

	private JButton updateButton, /** update record */
			newButton, /** add new Record */
			deleteButton, /** delete Record */
			listButton, /** list all records */
			done;
	/** quit program */

	private Container c;
	private int numEntries = 0, ZERO;

	private UpdateRec update;
	/** dialog box for record update */
	private NewRec newRec;
	/** dialog box for new record */
	private DeleteRec deleteRec;
	/** dialog box for delete record */
	private Record data;
	private String pData[][] = new String[250][7];
	private JMenuBar menuBar;
	private JMenu fileMenu, viewMenu, optionsMenu, toolsMenu, helpMenu, aboutMenu;
	private JMenuItem eMI;
	private JMenuItem lmMI, lmtMI, hdMI, dpMI, hamMI, csMI, tabMI, bandMI, sandMI, stapMI, wdvMI, sccMI;
	private JMenuItem deleteMI, addMI, updateMI, listAllMI;
	private JMenuItem debugON, debugOFF;
	private JMenuItem helpHWMI;
	private JMenuItem aboutHWMI;
	private MenuHandler menuHandler = new MenuHandler();
	private JTable table;
	private RandomAccessFile file;
	/** file from which data is read */
	private File aFile;
	private JButton cancel, refresh;
	private JPanel buttonPanel;
	protected boolean validPW = false;
	protected boolean myDebug = false;
	/** This flag toggles debug */

	private String columnNames[] = { "Record ID", "Type of tool", "Brand Name", "Tool Description", "partNum",
			"Quantity", "Price" };

	public HardwareStore() {
		super("Hardware Store: Lawn Mower ");

		data = new Record();
		aFile = new File("lawnmower.dat");
		c = getContentPane();

		setupMenu();

		List<String[][]> tools = ToolsDescription.getAllTools();

		for (int i = 0; i < tools.size(); i++) {
			InitRecord(tools.get(i).toString().concat(".dat"), tools.get(i));
		}

		setup();

		addWindowListener(new WindowHandler(this));
		setSize(700, 400);
		setVisible(true);
	}

	public void setupMenu() {
		menuBar = new JMenuBar();

		setJMenuBar(menuBar);

		fileMenu = new JMenu("File");

		menuBar.add(fileMenu);

		eMI = new JMenuItem("Exit");
		fileMenu.add(eMI);
		eMI.addActionListener(menuHandler);

		viewMenu = new JMenu("View");

		lmMI = new JMenuItem("Lawn Mowers");
		viewMenu.add(lmMI);
		lmMI.addActionListener(menuHandler);

		lmtMI = new JMenuItem("Lawn Mowing Tractors");
		viewMenu.add(lmtMI);
		lmtMI.addActionListener(menuHandler);

		hdMI = new JMenuItem("Hand Drills Tools");
		viewMenu.add(hdMI);
		hdMI.addActionListener(menuHandler);

		dpMI = new JMenuItem("Drill Press Power Tools");
		viewMenu.add(dpMI);
		dpMI.addActionListener(menuHandler);

		csMI = new JMenuItem("Circular Saws");
		viewMenu.add(csMI);
		csMI.addActionListener(menuHandler);

		hamMI = new JMenuItem("Hammers");
		viewMenu.add(hamMI);
		hamMI.addActionListener(menuHandler);

		tabMI = new JMenuItem("Table Saws");
		viewMenu.add(tabMI);
		tabMI.addActionListener(menuHandler);

		bandMI = new JMenuItem("Band Saws");
		viewMenu.add(bandMI);
		bandMI.addActionListener(menuHandler);

		sandMI = new JMenuItem("Sanders");
		viewMenu.add(sandMI);
		sandMI.addActionListener(menuHandler);

		stapMI = new JMenuItem("Staplers");
		viewMenu.add(stapMI);
		stapMI.addActionListener(menuHandler);

		wdvMI = new JMenuItem("Wet-Dry Vacs");
		viewMenu.add(wdvMI);
		wdvMI.addActionListener(menuHandler);

		sccMI = new JMenuItem("Storage, Chests & Cabinets");
		viewMenu.add(sccMI);
		sccMI.addActionListener(menuHandler);

		menuBar.add(viewMenu);
		optionsMenu = new JMenu("Options");

		listAllMI = new JMenuItem("List All");
		optionsMenu.add(listAllMI);
		listAllMI.addActionListener(menuHandler);
		optionsMenu.addSeparator();

		addMI = new JMenuItem("Add");
		optionsMenu.add(addMI);
		addMI.addActionListener(menuHandler);

		updateMI = new JMenuItem("Update");
		optionsMenu.add(updateMI);
		updateMI.addActionListener(menuHandler);
		optionsMenu.addSeparator();

		deleteMI = new JMenuItem("Delete");
		optionsMenu.add(deleteMI);
		deleteMI.addActionListener(menuHandler);

		menuBar.add(optionsMenu);

		toolsMenu = new JMenu("Tools");
		menuBar.add(toolsMenu);

		debugON = new JMenuItem("Debug On");
		debugOFF = new JMenuItem("Debug Off");
		toolsMenu.add(debugON);
		toolsMenu.add(debugOFF);
		debugON.addActionListener(menuHandler);
		debugOFF.addActionListener(menuHandler);

		helpMenu = new JMenu("Help");

		helpHWMI = new JMenuItem("Help on HW Store");
		helpMenu.add(helpHWMI);
		helpHWMI.addActionListener(menuHandler);

		menuBar.add(helpMenu);

		aboutMenu = new JMenu("About");

		aboutHWMI = new JMenuItem("About HW Store");
		aboutMenu.add(aboutHWMI);
		aboutHWMI.addActionListener(menuHandler);

		menuBar.add(aboutMenu);
	}

	public void setup() {

		try {

			file = new RandomAccessFile("lawnmower.dat", "rw");

			aFile = new File("lawnmower.dat");

			numEntries = GetContenttoArrayFormat(file, pData);

			file.close();
		} catch (IOException ex) {
			// part.setText( "Error reading file" );
		}

		table = new JTable(pData, columnNames);
		table.addMouseListener(new MouseClickedHandler(file, table, pData));
		table.setEnabled(true);

		c.add(table, BorderLayout.CENTER);
		c.add(new JScrollPane(table));

		cancel = new JButton("Cancel");
		refresh = new JButton("Refresh");
		buttonPanel = new JPanel();

		buttonPanel.add(cancel);
		c.add(buttonPanel, BorderLayout.SOUTH);

		refresh.addActionListener(this);
		cancel.addActionListener(this);

		update = new UpdateRec(main.hws, file, pData, -1);
		deleteRec = new DeleteRec(main.hws, file, table, pData);

		Password pWord = new Password(this);
	}

	public void InitRecord(String fileDat, String FileRecord[][]) {

		aFile = new File(fileDat);

		sysPrintDebug("initRecord(): 1a - the value of fileData is " + aFile);

		try {

			sysPrintDebug("initTire(): 1ab - checking to see if " + aFile + " exist.");
			if (!aFile.exists()) {

				sysPrintDebug("initTire(): 1b - " + aFile + " does not exist.");

				file = new RandomAccessFile(aFile, "rw");
				data = new Record();

				for (int ii = 0; ii < FileRecord.length; ii++) {
					data.setRecID(Integer.parseInt(FileRecord[ii][0]));
					sysPrintDebug("initTire(): 1c - The value of record ID is " + data.getRecID());
					data.setToolType(FileRecord[ii][1]);
					sysPrintDebug("initTire(): 1cb - The length of ToolType is " + data.getToolType().length());
					data.setBrandName(FileRecord[ii][2]);
					data.setToolDesc(FileRecord[ii][3]);
					sysPrintDebug("initTire(): 1cc - The length of ToolDesc is " + data.getToolDesc().length());
					data.setPartNumber(FileRecord[ii][4]);
					data.setQuantity(Integer.parseInt(FileRecord[ii][5]));
					data.setCost(FileRecord[ii][6]);

					sysPrintDebug("initTire(): 1d - Calling Record method write() during initialization. " + ii);
					file.seek(ii * Record.getSize());
					data.write(file);

				}
			} else {
				sysPrintDebug("initTire(): 1e - " + fileDat + " exists.");
				file = new RandomAccessFile(aFile, "rw");
			}

			file.close();
		} catch (IOException e) {
			System.err.println("InitRecord() " + e.toString() + " " + aFile);
			System.exit(1);
		}
	}

	public void displayContent(String str) {

		String[] name = str.split(" ");
		name[0].substring(0, 1).toLowerCase();
		for (int i = 1; i < name.length; i++) {
			name[i].substring(0, 1).toUpperCase();
		}
		name.toString().concat(".dat");

		aFile = new File(name.toString());
		String title = new String("Hardware Store: " + str);

		try {

			sysPrintDebug("display(): 1a - checking to see if " + name.toString() + " exists.");
			if (!aFile.exists()) {

				sysPrintDebug("display(): 1b - " + name.toString() + " does not exist.");

			} else {
				file = new RandomAccessFile(name.toString(), "rw");

				this.setTitle(title);

				Redisplay(file, pData);
			}

			file.close();
		} catch (IOException e) {
			System.err.println(e.toString());
			System.err.println("Failed in opening " + name.toString());
			System.exit(1);
		}

	}

	public void Redisplay(RandomAccessFile file, String a[][]) {

		for (int i = 0; i < numEntries + 5; i++) {
			a[i][0] = "";
			a[i][1] = "";
			a[i][2] = "";
			a[i][3] = "";
			a[i][4] = "";
			a[i][5] = "";
			a[i][6] = "";
		}

		int entries = GetContenttoArrayFormat(file, a);
		sysPrintDebug("Redisplay(): 1  - The number of entries is " + entries);
		setEntries(entries);
		c.remove(table);
		table = new JTable(a, columnNames);
		table.setEnabled(true);
		c.add(table, BorderLayout.CENTER);
		c.add(new JScrollPane(table));
		c.validate();
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == refresh) {
			sysPrintDebug("\nThe Refresh button was pressed. ");
			Container cc = getContentPane();

			table = new JTable(pData, columnNames);
			cc.validate();
		} else if (e.getSource() == cancel)
			cleanup();
	}

	public void cleanup() {
		try {
			file.close();
		} catch (IOException e) {
			System.exit(1);
		}

		setVisible(false);
		System.exit(0);
	}

	public void displayDeleteDialog() {
		sysPrintDebug("The Delete Record Dialog was made visible.\n");
		deleteRec.setVisible(true);
	}

	public void displayUpdateDialog() {
		sysPrintDebug("The Update Record Dialog was made visible.\n");
		JOptionPane.showMessageDialog(null, "Enter the record ID to be updated and press enter.", "Update Record",
				JOptionPane.INFORMATION_MESSAGE);
		update = new UpdateRec(main.hws, file, pData, -1);
		update.setVisible(true);
	}

	public void displayAddDialog() {
		sysPrintDebug("The New/Add Record Dialog was made visible.\n");
		newRec = new NewRec(main.hws, file, table, pData);
		newRec.setVisible(true);
	}

	public void setEntries(int ent) {
		numEntries = ent;
	}

	public String getColumnAndRowData(int col, int row) {
		return pData[col][row];
	}

	public int getEntries() {
		return numEntries;
	}

	public void sysPrintDebug(String str) {
		if (myDebug) {
			System.out.println(str);
		}
	}

	public int GetContenttoArrayFormat(RandomAccessFile file, String a[][]) {

		Record NodeRef = new Record();

		int size = 0, col = 0, fileSize = 0;

		try {
			fileSize = (int) file.length() / Record.getSize();
			sysPrintDebug("toArray(): 1 - The size of the file is " + fileSize);

			if (fileSize > ZERO) {

				NodeRef.setFileLen(file.length());

				while (size < fileSize) {
					sysPrintDebug("toArray(): 2 - NodeRef.getRecID is " + NodeRef.getRecID());

					file.seek(0);
					file.seek(size * NodeRef.getSize());
					NodeRef.setFilePos(size * NodeRef.getSize());
					sysPrintDebug("toArray(): 3 - input data file - Read record " + size);
					NodeRef.ReadRec(file);

					sysPrintDebug("toArray(): 4 - the value of a[ ii ] [ 0 ] is " + a[0][0]);

					if (NodeRef.getRecID() != -1) {
						a[col][0] = String.valueOf(NodeRef.getRecID());
						a[col][1] = NodeRef.getToolType().trim();
						a[col][2] = NodeRef.getBrandName().trim();
						a[col][3] = NodeRef.getToolDesc().trim();
						a[col][4] = NodeRef.getPartNumber().trim();
						a[col][5] = String.valueOf(NodeRef.getQuantity());
						a[col][6] = NodeRef.getCost().trim();

						sysPrintDebug("toArray(): 5 - 0- " + a[col][0] + " 1- " + a[col][1] + " 2- " + a[col][2]
								+ " 3- " + a[col][3] + " 4- " + a[col][4] + " 5- " + a[col][5] + " 6- " + a[col][6]);

						col++;

					} else {
						sysPrintDebug("toArray(): 5a the record ID is " + size);
					}

					size++;

				}
			}
		} catch (IOException ex) {
			sysPrintDebug("toArray(): 6 - input data file failure. Index is " + size + "\nFilesize is " + fileSize);
		}

		return size;
	}
}