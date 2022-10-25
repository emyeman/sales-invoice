

//class of InvoiceItem which store in item name, unit price, quantity and links the invoice with it 


package invoice.model;
/**
 *
 * @author Toshiba
 */
public class InvoiceItem {
    private InvoiceHeader invoice;
    private String itemName;
    private double price;
    private int quantity;

    public InvoiceItem() {
    }

    public InvoiceItem(String itemName, int quantity,double unitPrice,InvoiceHeader header) {
        this.invoice= header;
        this.quantity = quantity;
        this.itemName = itemName;
        this.price = unitPrice;
    }
     public double getTotalLine(){
        return price * quantity;
    }



    public InvoiceHeader getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceHeader invoice) {
        this.invoice = invoice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getUnitPrice() {
        return price;
    }

    public void setUnitPrice(double unitPrice) {
        this.price = unitPrice;
    }

    @Override
    public String toString() {
        return "sigItem{" + "itemName=" + itemName + ", unitPrice=" + price + ", quantity=" + quantity + '}';
    }
    public double getTotal(){
        return price * quantity;
    }

   public String getItemsFromTabel(){
       return invoice.getNum() + "," + itemName + "," + price + "," +quantity;
   }

   
    
}
