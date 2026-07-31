import java.util.ArrayList;
import java.util.List;


public class BoundedStack {

    /*  
      Abstraction Function:
      AF(items, capacity) = สแตกที่มีสมาชิกคือสมาชิกทั้งหมดใน items 
      โดยยอด Stack อยู่ที่ items.get(0)
      เเละยอด Stack (ตัวบนสุด) อยู่ที่ items.get(items.size() - 1)


      Representation Invariant:
        - items != null;
        - items.size() <= capacity;
        - capacity >= 0;
        
    */
       private final List<String> items;
       private final int capacity ;
    
    public BoundedStack(int capacity) {
        if (capacity < 0) {                      
            throw new IllegalArgumentException("capacity >= 0");
        }
        this.capacity = capacity;
        this.items = new ArrayList<>();
        checkRep();
    }

    //เช็คค่าว่าเป็น null, < 0, <=0
    private void checkRep() {
        if (items == null) {
        throw new AssertionError("items == null");
        }
        if (capacity < 0) {
            throw new AssertionError("capacity < 0");
        }
        if (items.size() > capacity) {
            throw new AssertionError("items <= capacity");
        }
    }

    /**
     * @param x รับค่าเข้ามา ค่าที่ต้องการเพิ่มเข้า Stack
     * @throws IllegalArgumentException เมื่อ x เป็น Null
     * @throws IllegalStateException เมื่อสเเตกเต็มเเล้ว
     */
    public void push(String x) {
        if (x == null) {
            throw new IllegalArgumentException();
        }
        if (items.size() >= capacity) {
            throw new IllegalStateException();
        }
        items.add(x);
        checkRep();
    }

    /**
     * pop ทำการลบค่าตำเเหน่งสุดท้ายออก เเละ คืนค่าตำเเหน่งสุดท้าย
     * @return ค่่าตำเเหล่งสุดท้าย
     * @throws IllegalStateException เมื่อค่าว่าง ไม่มีอะไรให้ pop
     */
    public String pop() {
        if (items.isEmpty()) {
            throw new IllegalStateException();
        }
        String top = items.get(items.size() - 1);
        items.remove(items.size() -1);
        checkRep();
        return top;
    }

    /**
     * @throws IllegalStateException เมื่อค่าว่าง
     * @return ค่าตำเเหน่งสุดท้าย
     */
    public String peek() {
        if (items.isEmpty()) {
            throw new IllegalStateException();
        }
        return items.get(items.size() -1);
    }

    /*
     *  @return คืนค่า true เมื่อไม่มีสมาชิกเลย
    */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /*
     *  @return คืนค่า true เมื่อสมาชิกมีค่าเท่ากับ capacity
    */
    public boolean isFull() {
        return items.size() == capacity ;
    }

    /*
     * @return คืนค่าจำนวนสมาชิกทั้งหมด
    */
    public int size() {
        return items.size();
    }

    /*
     * Producer Stack มีสมาชิกเเละลำดับเหมือนกัน เเต่คนละ Object
     * @return คืนค่า Stack ใหม่ที่มีสมาชิกเเละ capacity 
    */
    public BoundedStack copy() {
        BoundedStack stack = new BoundedStack(capacity);
        for (String s : items)
            stack.push(s);
        return stack;
    }


}

