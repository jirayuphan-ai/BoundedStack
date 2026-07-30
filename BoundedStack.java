import java.util.ArrayList;
import java.util.List;

public class BoundedStack {

    /*  
      Abstraction Function:
      AF(items, capacity) = สแตกที่มีสมาชิกคือสมาชิกทั้งหมดใน items โดยยอดสแตกอยู่ที่ items.get(0)
      เเละยอดสเเตก (ตัวบนสุด) อยู่ที่ items.get(items.size() - 1)

      Representation Invariant:
        - items != null
        - items.size() <= capacity;
        
    */
       private final List<String> items;
       private final int capacity ;
    
    public BoundedStack(int capacity) {
        if (capacity < 0) {                      
            throw new IllegalArgumentException("capacity must be >= 0");
        }
        this.capacity = capacity;
        this.items = new ArrayList<>();
        checkRep();
    }

    private void checkRep() {
        assert items != null : "items not null";
        assert items.size() <= capacity ;  
        assert capacity >= 0 : "Capacity must be >= 0" ;
    }

    /**
     * @param 
     * @throws 
     */
    public void push(Object x) {
        
        
        
    }

    /**
     * @return 
     * @throws 
     */
    public Object pop() {
        
        
        
        return null; // แก้ตามจริง
    }

    public Object peek() {
        
        return null;
    }

   
    public boolean isEmpty() {
        
        return false;
    }

    
    public boolean isFull() {
       
        return false;
    }

   
    public int size() {
       
        return -1;
    }

    public BoundedStack copy() {
       
        return null;
    }

}