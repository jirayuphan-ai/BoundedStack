# BoundedStack

#ผู้จัดทำ

นาย จิรายุ พันธุรพงศ์ รหัสนิสิต 6821651124 หมู่เรียน 800

Stack (LIFO) แบบมีขอบเขต (bounded) เขียนด้วย Java, จำกัดจำนวนสมาชิกสูงสุดไว้ที่ `capacity` 

## โครงสร้างไฟล์


| ไฟล์ | หน้าที่ |
|---|---|
| `BoundedStack.java` | คลาสหลัก implement โครงสร้างข้อมูล stack |
| `Testcase.java` | ชุดทดสอบ (test runner) |


## Abstraction Function & Representation Invariant


```
AF(items, capacity) = สแตกที่มีสมาชิกคือสมาชิกทั้งหมดใน items
         โดยก้นสแตกอยู่ที่ items.get(0)
          และยอดสแตก (ตัวบนสุด) อยู่ที่ items.get(items.size() - 1)


RI:
  - items != null
  - items.size() <= capacity
  - capacity >= 0
```


`checkRep()` ถูกเรียกหลัง (`push`, `pop`, constructor) เพื่อยืนยันว่า invariant 
ยังคงเป็นจริงเสมอ


## PI


| Method | คำอธิบาย | Exception |
|---|---|---|
| `BoundedStack(int capacity)` | สร้าง stack ว่างที่มี capacity ตามที่กำหนด | 
`IllegalArgumentException` ถ้า `capacity < 0` |
| `void push(String x)` | เพิ่มสมาชิกไว้บนสุด | `IllegalArgumentException` ถ้า `x == null`; 
`IllegalStateException` ถ้าสแตกเต็ม |
| `String pop()` | ลบและคืนค่าสมาชิกบนสุด | `IllegalStateException` ถ้าสแตกว่าง |
| `String peek()` | คืนค่าสมาชิกบนสุดโดยไม่ลบ | `IllegalStateException` ถ้าสแตกว่าง |
| `boolean isEmpty()` | คืน `true` ถ้าไม่มีสมาชิก | — |
| `boolean isFull()` | คืน `true` ถ้าสมาชิก == capacity | — |
| `int size()` | คืนจำนวนสมาชิกปัจจุบัน | — |
| `BoundedStack copy()` | คืน stack ใหม่ ที่มีสมาชิกและ capacity เหมือนเดิม | — |


**หมายเหตุ:** เมื่อ `capacity == 0` สแตกจะ `isEmpty()` และ `isFull()` เป็น `true` พร้อมกัน และ 
`push` ใด ๆ จะ throw `IllegalStateException` ทันที


## ชุดทดสอบ (`Testcase.java`)


โครงสร้างแบ่งตามเมธอดที่ทดสอบ:


- **`testCreators()`** — constructor: capacity ปกติ, `capacity = 0`, `capacity < 0` ต้อง throw 
`IllegalArgumentException`
- **`testPush()`** — push ปกติ (size +1), `push(null)` throw, push ตอนเต็มพอดี throw, push ตอน 
`capacity = 0` throw
- **`testPop()`** — pop คืนค่าตามลำดับ LIFO, size ลดลงจริง, pop จนว่างพอดี, pop ตอนว่างอยู่แล้ว 
throw
- **`testPeek()`** — peek คืนค่าตัวบนสุดถูกต้อง, ไม่มี side effect ต่อ size, เรียกซ้ำได้ค่าเดิม, 
peek ตอนว่าง throw
- **`testObservers()`** — `isEmpty` / `isFull` / `size` ครบทุกสถานะ: ว่าง, มีตัวเดียว, เต็มพอดี, 
และกรณีพิเศษ `capacity = 0`
- **`testCopy()`** — copy มีสมาชิก/ลำดับ/capacity เท่าต้นฉบับ, แก้ copy 
แล้วต้นฉบับไม่เปลี่ยน, copy ของสแตกว่าง

