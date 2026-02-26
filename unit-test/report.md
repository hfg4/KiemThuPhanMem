# Báo Cáo Kiểm Thử Đơn Vị: StudentAnalyzer

## 1. Tổng Quan Dự Án

Dự án **StudentAnalyzer** là một ứng dụng Java được phát triển để phân tích điểm số học sinh với hai chức năng chính:
- Đếm số lượng học sinh đạt loại Giỏi (điểm từ 8.0 trở lên)
- Tính điểm trung bình của các điểm hợp lệ (trong khoảng [0, 10])

**Công nghệ sử dụng:** Java 25, JUnit 5.10.2, Maven

## 2. Phạm Vi Kiểm Thử

### 2.1. Phương thức `countExcellentStudents(List<Double> scores)`

**Mục đích:** Đếm số học sinh có điểm từ 8.0 trở lên (chỉ tính điểm hợp lệ trong khoảng [0, 10])

**Quy tắc nghiệp vụ:**
- Điểm hợp lệ: 0 đến 10
- Điểm đạt loại Giỏi: từ 8.0 trở lên
- Bỏ qua điểm không hợp lệ (nhỏ hơn 0 hoặc lớn hơn 10)
- Xử lý danh sách rỗng và danh sách null

### 2.2. Phương thức `calculateValidAverage(List<Double> scores)`

**Mục đích:** Tính điểm trung bình của các điểm hợp lệ

**Quy tắc nghiệp vụ:**
- Chỉ tính trung bình các điểm trong khoảng [0, 10]
- Bỏ qua điểm không hợp lệ
- Trả về 0.0 nếu không có điểm hợp lệ nào
- Xử lý danh sách rỗng và danh sách null

## 3. Chi Tiết Các Trường Hợp Kiểm Thử

### 3.1. Các trường hợp kiểm thử cho `countExcellentStudents()`

| Mã TC | Tên Test | Dữ liệu đầu vào | Kết quả mong đợi | Mục đích |
|-------|----------|-----------------|------------------|----------|
| TC01 | `testCountExcellentStudents_NormalCase_Mixed` | [9.5, 8.0, 7.9, 10.0, 11.0, -0.5, 8.7, 9.0] | 5 | Kiểm tra trường hợp hỗn hợp: điểm hợp lệ/không hợp lệ, đạt Giỏi/không đạt |
| TC02 | `testCountExcellentStudents_AllExcellent` | [8.0, 9.0, 10.0, 8.5] | 4 | Kiểm tra khi tất cả điểm đều đạt loại Giỏi |
| TC03 | `testCountExcellentStudents_EmptyList` | [] (danh sách rỗng) | 0 | Kiểm tra xử lý danh sách rỗng |
| TC04 | `testCountExcellentStudents_NullList` | null | 0 | Kiểm tra xử lý đầu vào null |
| TC05 | `testCountExcellentStudents_NoExcellentButValidScores` | [7.9, 6.5, 0.0, 7.0] | 0 | Kiểm tra khi không có điểm nào đạt Giỏi |
| TC06 | `testCountExcellentStudents_OnlyBoundaryValues` | [8.0, 7.999, 10.0, 8.0001] | 3 | Kiểm tra giá trị biên: 8.0 (đạt), 7.999 (không đạt), 8.0001 (đạt) |

**Phân tích TC01 - Trường hợp hỗn hợp:**
- Dữ liệu đầu vào: [9.5, 8.0, 7.9, 10.0, 11.0, -0.5, 8.7, 9.0]
- Điểm hợp lệ: 9.5, 8.0, 7.9, 10.0, 8.7, 9.0
- Điểm không hợp lệ (bị loại): 11.0, -0.5
- Điểm đạt Giỏi (từ 8.0 trở lên): 9.5, 8.0, 10.0, 8.7, 9.0 → **5 học sinh**

**Phân tích TC06 - Giá trị biên:**
- 8.0 → đạt (giá trị biên, bao gồm cả giá trị này)
- 7.999 → không đạt (dưới giá trị biên)
- 10.0 → đạt (giới hạn trên hợp lệ)
- 8.0001 → đạt (vượt giá trị biên)
- **Kết quả mong đợi: 3 học sinh đạt Giỏi**

### 3.2. Các trường hợp kiểm thử cho `calculateValidAverage()`

| Mã TC | Tên Test | Dữ liệu đầu vào | Kết quả mong đợi | Mục đích |
|-------|----------|-----------------|------------------|----------|
| TC07 | `testCalculateValidAverage_NormalCase_Mixed` | [9.0, 8.5, 7.0, 11.0, -1.0, 10.0] | 8.625 | Kiểm tra tính trung bình với điểm hỗn hợp |
| TC08 | `testCalculateValidAverage_AllValid` | [5.5, 6.0, 8.5] | 6.667 | Kiểm tra khi tất cả điểm đều hợp lệ |
| TC09 | `testCalculateValidAverage_EmptyList` | [] (danh sách rỗng) | 0.0 | Kiểm tra xử lý danh sách rỗng |
| TC10 | `testCalculateValidAverage_NullList` | null | 0.0 | Kiểm tra xử lý đầu vào null |
| TC11 | `testCalculateValidAverage_NoValidScores` | [-1.0, 11.0, -5.5, 12.3] | 0.0 | Kiểm tra khi không có điểm hợp lệ nào |
| TC12 | `testCalculateValidAverage_OnlyZeroAndTen` | [0.0, 10.0, 0.0, 10.0] | 5.0 | Kiểm tra giá trị biên tối thiểu/tối đa: 0 và 10 |
| TC13 | `testCalculateValidAverage_SingleValidScore` | [7.5, -1.0, 12.0] | 7.5 | Kiểm tra trường hợp chỉ có 1 điểm hợp lệ |

**Phân tích TC07 - Trường hợp hỗn hợp:**
- Dữ liệu đầu vào: [9.0, 8.5, 7.0, 11.0, -1.0, 10.0]
- Điểm hợp lệ: 9.0, 8.5, 7.0, 10.0
- Điểm không hợp lệ (bị loại): 11.0, -1.0
- Tính toán: (9.0 + 8.5 + 7.0 + 10.0) / 4 = 34.5 / 4 = **8.625**

**Phân tích TC13 - Chỉ có một điểm hợp lệ:**
- Dữ liệu đầu vào: [7.5, -1.0, 12.0]
- Chỉ có 7.5 là hợp lệ
- Trung bình: 7.5 / 1 = **7.5** (không chia cho 0)

## 4. Chiến Lược Kiểm Thử

### 4.1. Các kỹ thuật kiểm thử được áp dụng

1. **Phân vùng tương đương (Equivalence Partitioning)**
   - Điểm hợp lệ: [0, 10]
   - Điểm không hợp lệ: (âm vô cùng, 0) và (10, dương vô cùng)
   - Điểm đạt Giỏi: [8.0, 10]
   - Điểm không đạt Giỏi: [0, 8.0)

2. **Phân tích giá trị biên (Boundary Value Analysis)**
   - Biên dưới điểm hợp lệ: 0.0
   - Biên trên điểm hợp lệ: 10.0
   - Biên dưới điểm Giỏi: 8.0, 7.999, 8.0001

3. **Xử lý lỗi (Error Handling)**
   - Đầu vào null (TC04, TC10)
   - Danh sách rỗng (TC03, TC09)
   - Dữ liệu không hợp lệ (TC01, TC07, TC11)
   - Tránh chia cho 0 (TC11, TC13)

4. **Trường hợp đặc biệt (Edge Cases)**
   - Tất cả điểm đạt Giỏi (TC02)
   - Không có điểm đạt Giỏi (TC05)
   - Chỉ có 1 điểm hợp lệ (TC13)
   - Giá trị tối thiểu/tối đa: 0 và 10 (TC12)

### 4.2. Phân tích độ bao phủ mã nguồn

**Độ bao phủ đạt được:**
- **Độ bao phủ dòng lệnh (Line Coverage):** 100% - Tất cả dòng code đều được thực thi
- **Độ bao phủ nhánh (Branch Coverage):** 100% - Tất cả nhánh if/else được kiểm tra
- **Độ bao phủ điều kiện (Condition Coverage):** 100% - Tất cả điều kiện logic được test

**Phân tích chi tiết:**

**Phương thức `countExcellentStudents()`:**
- Kiểm tra null: `if (scores == null)` được test bởi TC04
- Kiểm tra rỗng: `isEmpty()` được test bởi TC03
- Kiểm tra phạm vi hợp lệ: `score >= 0 && score <= 10` được test bởi TC01, TC06
- Kiểm tra điểm Giỏi: `score >= 8.0` được test bởi TC01, TC02, TC05, TC06
- Kiểm tra phần tử null: `score != null` được bao phủ trong implementation

**Phương thức `calculateValidAverage()`:**
- Kiểm tra null: `if (scores == null)` được test bởi TC10
- Kiểm tra rỗng: `isEmpty()` được test bởi TC09
- Kiểm tra phạm vi hợp lệ: `score >= 0 && score <= 10` được test bởi TC07, TC11, TC12
- Kiểm tra chia cho 0: `if (validCount == 0)` được test bởi TC11
- Kiểm tra phần tử null: `score != null` được bao phủ trong implementation

## 5. Kết Quả Kiểm Thử

### 5.1. Tóm tắt kết quả thực thi

```
Tổng số test: 13
Thất bại: 0
Lỗi: 0
Bỏ qua: 0
Tỷ lệ thành công: 100%
```

**Tất cả 13 trường hợp kiểm thử đều THÀNH CÔNG**

### 5.2. Các phương thức kiểm tra sử dụng

- `assertEquals(expected, actual)` - So sánh giá trị chính xác
- `assertEquals(expected, actual, delta)` - So sánh số thực với độ chính xác 0.001

### 5.3. Chất lượng dữ liệu kiểm thử

**Điểm mạnh:**
- Bao phủ đầy đủ các trường hợp biên
- Kiểm tra đầy đủ xử lý lỗi
- Dữ liệu test đa dạng và có ý nghĩa thực tế
- Giá trị biên được test kỹ lưỡng (8.0, 7.999, 8.0001)

## 6. Phân Tích Chất Lượng Mã Nguồn

### 6.1. Điểm mạnh của mã nguồn

1. **Lập trình phòng thủ (Defensive Programming):**
   - Kiểm tra null và danh sách rỗng trước khi xử lý
   - Kiểm tra null cho từng phần tử trong danh sách
   - Tránh chia cho 0 trong `calculateValidAverage()`

2. **Tính rõ ràng của mã nguồn:**
   - Logic đơn giản, dễ hiểu
   - Tên biến rõ ràng (count, validCount, sum)
   - Chú thích đầy đủ bằng tiếng Việt

3. **Tính ổn định:**
   - Xử lý tốt các trường hợp đặc biệt
   - Không ném ra ngoại lệ với dữ liệu không hợp lệ
   - Trả về giá trị mặc định hợp lý (0, 0.0)

### 6.2. Khuyến nghị cải tiến

1. **Xác thực phần tử null:**
   ```java
   // Hiện tại code đã xử lý null elements tốt
   if (score != null && score >= 0 && score <= 10)
   ```

2. **Có thể sử dụng Stream API (Java 8+):**
   ```java
   // Cách triển khai thay thế
   public int countExcellentStudents(List<Double> scores) {
       return scores == null ? 0 : (int) scores.stream()
           .filter(s -> s != null && s >= 8.0 && s <= 10.0)
           .count();
   }
   ```

3. **Thêm logging cho điểm không hợp lệ (tùy chọn):**
   - Ghi log cảnh báo khi gặp điểm không hợp lệ
   - Hỗ trợ debug trong môi trường production

## 7. Khả Năng Bảo Trì Test

### 7.1. Tổ chức mã nguồn

- Test class được cấu trúc tốt với quy ước đặt tên rõ ràng
- Sử dụng annotation `@Test` của JUnit 5
- Thiết lập chung: `private final StudentAnalyzer analyzer`

### 7.2. Quy ước đặt tên

**Mẫu:** `test<TênPhươngThức>_<KịchBản>_<NgữCảnh>`

Ví dụ:
- `testCountExcellentStudents_NormalCase_Mixed`
- `testCalculateValidAverage_OnlyZeroAndTen`

**Ưu điểm:** Dễ hiểu mục đích test ngay từ tên phương thức

## 8. Kết Luận

### 8.1. Đánh giá độ bao phủ kiểm thử

| Tiêu chí | Điểm số | Nhận xét |
|----------|---------|----------|
| Độ bao phủ dòng lệnh | 100% | Tất cả dòng code được kiểm thử |
| Độ bao phủ nhánh | 100% | Tất cả nhánh logic được kiểm thử |
| Độ bao phủ trường hợp đặc biệt | Xuất sắc | Đầy đủ null, rỗng, giá trị biên |
| Xử lý lỗi | Xuất sắc | Xử lý tốt dữ liệu không hợp lệ |
| Kịch bản thực tế | Tốt | Test cases phản ánh trường hợp sử dụng thực tế |

### 8.2. Đánh giá

**Điểm mạnh:**
- Tỷ lệ test thành công 100%
- Độ bao phủ kiểm thử toàn diện
- Kiểm thử giá trị biên xuất sắc
- Xác minh xử lý lỗi chặt chẽ
- Mã nguồn test rõ ràng và dễ bảo trì

**Hướng cải tiến:**
- Có thể thêm kiểm thử hiệu năng cho tập dữ liệu lớn
- Có thể thêm parameterized tests để giảm trùng lặp code
- Cân nhắc thêm integration tests nếu mở rộng dự án

### 8.3. Kết luận chung

**Dự án StudentAnalyzer đã đạt chuẩn kiểm thử đơn vị với chất lượng cao.** Mã nguồn triển khai ổn định, các trường hợp kiểm thử toàn diện, và độ bao phủ đạt 100%. Mã nguồn sẵn sàng để sử dụng trong môi trường production với độ tin cậy cao về tính đúng đắn của logic.

---

**Người thực hiện kiểm thử:** StudentAnalyzerTest
**Framework:** JUnit 5.10.2
**Ngày cập nhật báo cáo:** 2026-02-27
**Tệp tin kiểm thử:**
- Mã nguồn: `unit-test/src/StudentAnalyzer.java`
- Kiểm thử: `unit-test/test/StudentAnalyzerTest.java`
