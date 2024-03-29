package record;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class DeleteRec extends Dialog implements ActionListener {
	private RandomAccessFile file;
	private JTextField recID;
	private JLabel recIDLabel;
	private JButton cancel, delete;
	private Record data;
	private int partNum;
	private int theRecID = -1, toCont;
	private JTable table;
	private String pData[][];
	private HardwareStore hwStore;
	private boolean found = false;

	/**
	 * ******************************************************** Method: DeleteRec()
	 * constructor is used to initialize the DeleteRec class/dialog.
	 ********************************************************/
	public DeleteRec(HardwareStore hw_store, RandomAccessFile f, JTable tab, String p_Data[][]) {

		super(new Frame(), "Delete Record", true);
		setSize(400, 150);
		setLayout(new GridLayout(2, 2));
		file = f;
		table = tab;
		pData = p_Data;
		hwStore = hw_store;
		delSetup();
	}

	/**
	 * ******************************************************** Method: delSetup()
	 * is used to create new field and button
	 ********************************************************/
	public void delSetup() {
		recID = new JTextField(10);
		delete = new JButton("Delete Record");
		cancel = new JButton("Cancel");

		cancel.addActionListener(this);
		delete.addActionListener(this);
		recID.addActionListener(this);

		add(new JLabel("Record ID"));
		add(recID);
		add(delete);
		add(cancel);

		data = new Record();
	}

	/**
	 * ******************************************************** Method:
	 * actionPerformed() is used to respond to the event emanating from the Delete
	 * Record dialog.
	 ********************************************************/
	public void actionPerformed(ActionEvent e) {
		System.out.println("DeleteRec(): 1a - In the actionPerformed() method. ");
		if (e.getSource() == recID) {
			theRecID = Integer.parseInt(recID.getText());

			if (theRecID < 0 || theRecID > 250) {
				recID.setText("Invalid part number");
				// return;
			} else {

				try {
					file = new RandomAccessFile(hwStore.aFile, "rw");

					file.seek(theRecID * data.getSize());
					data.ReadRec(file);
					System.out.println("DeleteRec(): 1b - The record read is recid " + data.getRecID() + " "
							+ data.getToolType() + " " + data.getBrandName() + " " + data.getToolDesc() + " "
							+ data.getQuantity() + " " + data.getCost());
				} catch (IOException ex) {
					recID.setText("Error reading file");
				}

				// if ( data.getRecID() == 0 )
				// recID.setText( partNum + " does not exist" );
			}
		} else if (e.getSource() == delete) {
			theRecID = Integer.parseInt(recID.getText());

			for (int iii = 0; iii < pData.length; iii++) {
				if ((pData[iii][0]).equals("" + theRecID)) {
					theRecID = Integer.parseInt(pData[iii][0]);
					found = true;
					System.out.println("DeleteRec(): 2 - The record id was found is  " + pData[iii][0]);
					break;
				}
			}

			try {

				System.out.println("DeleteRec(): 3 - The data file is " + hwStore.aFile + "The record to be deleted is "
						+ theRecID);
				file = new RandomAccessFile(hwStore.aFile, "rw");
				// theRecID = Integer.parseInt( recID.getText() ) ;
				data.setRecID(theRecID);

				hwStore.setEntries(hwStore.getEntries() - 1);
				System.out.println("DeleteRec(): 4 - Go to the beginning of the file.");
				file.seek((0));
				file.seek((theRecID) * data.getSize());
				data.ReadRec(file);
				System.out.println("DeleteRec(): 5 - Go to the record " + theRecID + " to be deleted.");
				data.setRecID(-1);
				/*
				 * data.setToolType( " " ) ; data.setBrandName( " " ) ; data.setToolDesc( " " )
				 * ; data.setPartNumber( " " ) ; data.setQuantity( 0 ) ; data.setCost( " " ) ;
				 */
				System.out.println("DeleteRec(): 6 - Write the deleted file to the file.");
				file.seek((0));
				file.seek((theRecID) * data.getSize());
				data.writeInteger(file, -1);

				System.out.println("DeleteRec(): 7 - The number of entries is " + hwStore.getEntries());

				file.close();
			} catch (IOException ex) {
				recID.setText("Error writing file");
				return;
			}

			toCont = JOptionPane.showConfirmDialog(null, "Do you want to delete another record? \nChoose one",
					"Select Yes or No", JOptionPane.YES_NO_OPTION);

			if (toCont == JOptionPane.YES_OPTION) {
				recID.setText("");
			} else {
				delClear();
			}
		} else if (e.getSource() == cancel) {
			delClear();
		}
	}

	/**
	 * ******************************************************** Method: delClear()
	 * is used to close the delete record dialog.
	 ********************************************************/
	private void delClear() {
		try {
			System.out.println(
					"DeleteRec(): 3 - The data file is " + hwStore.aFile + "The record to be deleted is " + theRecID);
			file = new RandomAccessFile(hwStore.aFile, "rw");

			Redisplay(file, pData);
			file.close();
		} catch (IOException ex) {
			recID.setText("Error writing file");
			return;
		}
		setVisible(false);
		recID.setText("");
	}
}