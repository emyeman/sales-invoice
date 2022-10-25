


package invoice.model;

import java.util.ArrayList;
 

/**
 *
 * @author Toshiba
 */
public class InvoiceHeader {
   private int number;
   private String date;
   private String name;
   private ArrayList<InvoiceItem> items;

   
    public InvoiceHeader() {
    }
  

    public InvoiceHeader(int num, String name, String date) {
        this.number = num;
        this.name = name;
        this.date = date;
    }
    public double getTotalInvoice(){
        double total=0.0;
        for(InvoiceItem item : getItems()){
            total= total + item.getTotalLine();
        }
        return total;
    }
   

    public int getNum() {
        return number;
    }

    public void setNum(int num) {
        this.number = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<InvoiceItem> getItems() {
        if(items==null){
            items=new ArrayList();
        }
        return items;
    }

    @Override
    public String toString() {
        return "sigHeader{" + "num=" + number + ", date=" + date + ", name=" + name  + ", items=" + items + '}';
    }
    public double getTotal(){
        double total=0;
        for(InvoiceItem item: getItems()){
            total= total+ item.getTotal();
        }
        return total;
    }

   public String getInvoicesFromTabel(){
       return number + "," + date + "," + name ;
   }
   

   
 
}
