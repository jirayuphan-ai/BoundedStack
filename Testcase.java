public class Testcase {

    private static int passed = 0;
    private static int failed = 0;
    // เช็คผลลัพธ์จากการทดสอบ
    private static void check(String name, boolean truth){
        if (truth) {
            passed ++;
            System.out.println("Pass " + name);
        }else{
            failed ++;
            System.out.println("Fail " + name);
        }
    } 
    public static void main(String[] args) {
        boolean asserOn = false;
        assert asserOn = true;
        if (!asserOn) {
            System.out.println("WARNING : assertion disbled" + "- re-run with: java -ea PlaylistTest\n");
        }
        System.out.println("=== BoundedStack Test Suite ===\n");


        testCreators();
        testPush();
        testPop();
        testPeek();
        testObservers();
        testCopy();


        System.out.println("\n=== Summary ===");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println("Total : " + (passed + failed));
        System.out.println(failed == 0 ? "ALL TESTS PASSED" : "SOME TESTS FAILED");


        if (failed > 0) {
            System.exit(1);
        } 
    }
    // ตรวจสอบ BoundedStack 
    private static void testCreators() {
        System.out.println("--- testCreators ---");
        BoundedStack stack1 = new BoundedStack(5);
        check("capacity =5 -> size", stack1.size() ==  0);

        // capacity = 0 ไม่ throw error
        BoundedStack stack2 = new BoundedStack(0);
        check("capacity =0 not throw", stack2.size() == 0);

        //  capacity ติดลบ throw IllegalArgumentException
        boolean threw1 = false;
        try {
            new BoundedStack(-1);
        } catch (IllegalArgumentException e) {
            threw1 = true;
        }
        check("capacity ติดลบ -> throw IllegalArgumentException", threw1);
    }
    // เพิ่มข้อมูลเข้า Stack
    private static void testPush() {
        System.out.println("--- testPush ---");
        BoundedStack stack1 = new BoundedStack(5);
        stack1.push("A");
        check("Push('A') -> size +1", stack1.size() == 1);

        //  เมื่อ push null throw IllegalArgumentException
        BoundedStack stack2 = new BoundedStack(5);
        boolean threw2 = false;
            try {
                stack2.push(null);
            } catch (IllegalArgumentException e) {
                threw2 = true;
            }
            check("push(null) -> throw IllegalArgumentException", threw2);
        
        //  stack เต็มเเล้ว throw IllegalStateException
        BoundedStack stack3 = new BoundedStack(1);
        stack3.push("A");
        boolean threw3 = false;
            try {
                stack3.push("B");
            } catch (IllegalStateException e) { 
                threw3 = true;
            }
            check("push full -> throw IllegalStateException", threw3);

        //  ถ้า stack เต็มตั้งเเต่ต้น throw IllegalStateException
        BoundedStack stack4 = new BoundedStack(0);
        boolean threw4 = false ;
            try {
                stack4.push("A");
            } catch (IllegalStateException e) {
                threw4 = true;
            }
            check("push 0 first time-> throw IllegalStateException", threw4);
    }
    // pop คืนค่าตัวบนสุด
    private static void testPop() {
        System.out.println("--- testPop ---");
        BoundedStack stack1 = new BoundedStack(5);
        stack1.push("A");
        stack1.push("B");
        check("pop return push ล่าสุด (B)", stack1.pop().equals("B"));
        check("pop size Decreased to 1", stack1.size() == 1);
    
        // pop จนไม่มีสมาชิกเหลืออยู่
        BoundedStack stack2 = new BoundedStack(5);
        stack2.push("X");
        stack2.pop();
        check("pop blank isEmpty -> true", stack2.isEmpty());
    
        // เมื่อ stack ว่าง throw IllegalStateException
        BoundedStack stack3 = new BoundedStack(5);
        boolean threw = false;
        try {
            stack3.pop();
        } catch (IllegalStateException e) {
            threw = true;
        }
        check("pop blank throw IllegalStateException", threw);
    }
        // peek คืนค่าบนสุด
        private static void testPeek() {
        System.out.println("--- testPeek ---");
        BoundedStack stack1 = new BoundedStack(5);
        stack1.push("A");
        stack1.push("B");
        check("peek On top (B)", stack1.peek().equals("B"));
        check("peek not change size", stack1.size() == 2);
        check("peek same value twice", stack1.peek().equals(stack1.peek()));

        // เมื่อว่าง throw IllegalStateException เมื่อไม่มีข้อมูล
        BoundedStack stack2 = new BoundedStack(5);
        boolean threw1 = false;
            try {
                stack2.peek();
            } catch (IllegalStateException e) {
                threw1 = true;
            }
            check("peek blank -> throw IllegalStateException", threw1);
    }

    private static void testObservers() {
        System.out.println("--- testObservers ---");
        // Stack blank
        BoundedStack stack1 = new BoundedStack(5);
        check("stack blank -> isEmpty true", stack1.isEmpty());
        check("stack blank -> isFull false", !stack1.isFull());
        check("stack blank -> size = 0", stack1.size() == 0);

        //ใส่ตัวเเรก
        stack1.push("A");
        check("Only one -> isEmpty false", !stack1.isEmpty());
        check("Only one -> isFull false", !stack1.isFull());
        check("Only one -> size = 1", stack1.size() == 1);


        // Stack เต็ม
        BoundedStack stack2 = new BoundedStack(1);
        stack2.push("A");
        check("stack full -> isFull true", stack2.isFull());
        check("stack full -> isEmpty false", !stack2.isEmpty());


        // capacity=0 
        BoundedStack s3 = new BoundedStack(0);
        check("capacity=0 -> isEmpty true", s3.isEmpty());
        check("capacity=0 -> isFull true", s3.isFull());
}
        //Copy ขนาดและลำดับข้อมูลต้องตรงกับข้อมูลอันเเรก
        private static void testCopy() {
        System.out.println("--- Copy ---");

        //ตรวจสอบว่า size เท่ากันมั้ย
        BoundedStack stack1 = new BoundedStack(3);
        stack1.push("A");
        stack1.push("B");
        BoundedStack copy = stack1.copy();
        check("copy has the same size", copy.size() == stack1.size());

        //ตรวจสอบลำดับข้ลมูลตรงกันมั้ย (LIFO)
        BoundedStack temp1 = stack1.copy();
        BoundedStack temp2 = copy.copy();
        boolean sameOrder = temp1.pop().equals(temp2.pop()) && temp1.pop().equals(temp2.pop());
        check("copy has elements in the same order as original", sameOrder);
        check("copy is a distinct object", copy != stack1);
        check("does not affect the copy", copy.size() == 2);

        copy.push("D");
        check("result not affect original stack size", stack1.size() == 2);

        BoundedStack emptyCopy = new BoundedStack(3).copy();
        check("copying empty list", emptyCopy.size() == 0);
    } 
}

