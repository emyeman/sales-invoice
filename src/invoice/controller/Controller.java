package invoice.controller;

import invoice.model.FileOperation;
import invoice.model.ShowInvoiceTabel;
import invoice.model.ShowLineTabel;
import invoice.model.InvoiceHeader;
import invoice.model.InvoiceItem;
import invoice.view.InvoiceFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import invoice.view.AddInvoiceDialog;
import invoice.view.AddLineDialog;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Controller implements ActionListener, ListSelectionListener {

    private InvoiceFrame frame;
    private InvoiceHeader header;
    private InvoiceItem item;
    private String name ;
    private AddInvoiceDialog invoiceDialog;
    private AddLineDialog itemDialog;

    public Controller(InvoiceFrame frame) {
        this.frame = frame;

    }

    @Override
    
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            case "New Invoice":
                newInvoice();
                break;
            case "Delete Invoice":
                deleteInvoice();
                break;
            case "New Line":
                newLine();
                break;
            case "Delete Line":
                deleteLine();
                break;
                   
            case "createInvoice":
                addInvoiceOk();
                break;
                
            case "cancelInvoice":
                cancelInvoice();
                break;
            case "createLine":
                createLine();
                break;
            case "cancelLine":
                cancelLine();
                break;  
                
            case "Load File":
                
                frame.setInvoices(null);
                FileOperation fileOperations = new FileOperation(frame);
                ArrayList<InvoiceHeader> inv= fileOperations.readFile();
                frame.setInvoices(inv);
                ShowInvoiceTabel invoiceTable = new ShowInvoiceTabel(inv);
                frame.setHeaderTabel(invoiceTable);
                frame.getTableInvoiceHeader().setModel(invoiceTable);
                frame.getHeaderTabel().fireTableDataChanged();
                break;

            case "Save File":
                FileOperation fileOperation = new FileOperation(frame);

            
                fileOperation.saveFile(frame.getInvoices());
            
            
                break;

                
        }
    }

    private void newInvoice() {
        invoiceDialog = new AddInvoiceDialog(frame);
        invoiceDialog.setVisible(true);

    }

    private void deleteInvoice() {
         int row = frame.getTableInvoiceHeader().getSelectedRow();
        if(row!= -1){
            frame.getInvoices().remove(row);
            frame.getHeaderTabel().fireTableDataChanged();
            
        }
    }
    private void newLine() {
        itemDialog = new AddLineDialog(frame);
        itemDialog.setVisible(true);
        
    }

    private void deleteLine() {
        int invoiceSelected= frame.getTableInvoiceHeader().getSelectedRow();
          int row = frame.getTableInvoiceLines().getSelectedRow();
          
        if((invoiceSelected!=-1) && (row!= -1)){
            InvoiceHeader invoice = frame.getInvoices().get(invoiceSelected);
            invoice.getItems().remove(row);
            frame.getHeaderTabel().fireTableDataChanged();
            ShowLineTabel line = new ShowLineTabel(invoice.getItems());
            frame.getTableInvoiceLines().setModel(line);
            line.fireTableDataChanged();
    }
    }

    
    public void addInvoiceOk() {
      String date= invoiceDialog.getInvoiceDate().getText();
      String customer = invoiceDialog.getCustomerName().getText();
    
      
      int num= frame.getTotalInvNum();
      num++;
        InvoiceHeader newInvoice = new InvoiceHeader(num,customer,date);
        frame.getInvoices().add(newInvoice);
        frame.getHeaderTabel().fireTableDataChanged();
        invoiceDialog.setVisible(false);
        invoiceDialog.dispose();
        invoiceDialog=null;
        
    }

    
    public InvoiceHeader getInvoiceByNum(int num){
       for(InvoiceHeader inv: frame.getInvoices()){
        if(num==inv.getNum()){
            return inv;
        }
    } 
        return null;
}

    @Override
    public void valueChanged(ListSelectionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    private void cancelInvoice() {
        invoiceDialog.setVisible(false);
        invoiceDialog.dispose();
        invoiceDialog=null;
    }

    
    private void createLine() {
        
      int invoiceSelected= frame.getTableInvoiceHeader().getSelectedRow();
        if(invoiceSelected!=-1){
            InvoiceHeader invoice = frame.getInvoices().get(invoiceSelected);
            String item= itemDialog.getItemName().getText();
            String unitPrice = itemDialog.getUnitPrice().getText();
            String quantity = itemDialog.getQuantity().getText();
            double itemUnitPrice = Double.parseDouble(unitPrice);
            int itemQuantity = Integer.parseInt(quantity);
            InvoiceItem newLine = new InvoiceItem(item,itemQuantity,itemUnitPrice,invoice);
            invoice.getItems().add(newLine);
            ShowLineTabel line = new ShowLineTabel(invoice.getItems());
            frame.getHeaderTabel().fireTableDataChanged();
            frame.getTableInvoiceLines().setModel(line);
            line.fireTableDataChanged();

        }
        itemDialog.setVisible(false);
        itemDialog.dispose();
        itemDialog=null;
        
    }

    private void cancelLine() {
        itemDialog.setVisible(false);
        itemDialog.dispose();
        itemDialog=null;
    }
}
