# Student Analyzer

## Mô tả bài toán

Đây là một dự án Java đơn giản dùng để phân tích điểm số của học sinh. Chương trình cung cấp hai chức năng chính:

1. **Đếm học sinh đạt loại Giỏi**: Đếm số lượng học sinh có điểm từ 8.0 trở lên
2. **Tính điểm trung bình hợp lệ**: Tính điểm trung bình của các điểm số hợp lệ (từ 0 đến 10)

### Quy tắc xử lý
- Điểm hợp lệ phải nằm trong khoảng [0, 10]
- Điểm âm hoặc lớn hơn 10 bị coi là dữ liệu sai và bị bỏ qua
- Nếu danh sách rỗng hoặc null, trả về 0
- Điểm từ 8.0 trở lên được coi là loại Giỏi

## Cấu trúc project

```
JUnit/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── StudentAnalyzer.java    # Class chính chứa logic xử lý
│   └── test/
│       └── java/
│           └── StudentAnalyzerTest.java # Class test JUnit 5
├── pom.xml                              # Maven configuration
└── README.md                            # File này
```

## Công nghệ sử dụng

- **Java 25**
- **Maven** - Quản lý dependency và build
- **JUnit 5.10.2** - Framework testing

## Cài đặt và chạy chương trình

### Yêu cầu
- Java Development Kit (JDK) phiên bản 25 hoặc cao hơn
- Apache Maven

### Các bước thực hiện

1. **Clone hoặc tải project về máy**
   ```bash
   git clone <repository-url>
   cd JUnit
   ```

2. **Biên dịch project**
   ```bash
   mvn compile
   ```

3. **Chạy test**
   ```bash
   mvn test
   ```

4. **Chạy chương trình (nếu có main method)**
   ```bash
   mvn exec:java -Dexec.mainClass="StudentAnalyzer"
   ```

## Testing

Project sử dụng JUnit 5 để thực hiện unit test. Các test case được thiết kế để kiểm tra:

### Test cases cho `countExcellentStudents()`
- Trường hợp bình thường với điểm số hỗn hợp
- Tất cả học sinh đều đạt loại Giỏi
- Danh sách rỗng
- Danh sách null
- Không có học sinh nào đạt Giỏi nhưng điểm hợp lệ
- Các giá trị biên (8.0, 7.999, 10.0, 8.0001)

### Test cases cho `calculateValidAverage()`
- Trường hợp bình thường với điểm số hỗn hợp
- Tất cả điểm số đều hợp lệ
- Danh sách rỗng
- Danh sách null
- Không có điểm hợp lệ nào
- Chỉ có điểm 0 và 10
- Điểm hợp lệ duy nhất

### Kết quả test mong đợi
Tất cả 12 test case đều phải pass. Kết quả hiển thị sẽ tương tự như:
```
[INFO] Tests run: 12, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

## Sử dụng các method

### Method 1: `countExcellentStudents(List<Double> scores)`

```java
StudentAnalyzer analyzer = new StudentAnalyzer();
List<Double> scores = Arrays.asList(9.5, 8.0, 7.9, 10.0, 11.0, -0.5, 8.7, 9.0);
int excellentCount = analyzer.countExcellentStudents(scores);
// Kết quả: 5 (9.5, 8.0, 10.0, 8.7, 9.0)
```

### Method 2: `calculateValidAverage(List<Double> scores)`

```java
StudentAnalyzer analyzer = new StudentAnalyzer();
List<Double> scores = Arrays.asList(9.0, 8.5, 7.0, 11.0, -1.0, 10.0);
double average = analyzer.calculateValidAverage(scores);
// Kết quả: 8.625 (trung bình của 9.0, 8.5, 7.0, 10.0)
```

## Lưu ý

- Project sử dụng encoding UTF-8
- Java version được set là 25 trong pom.xml
- Tất cả test case đều được viết theo chuẩn JUnit 5
- Các method được xử lý edge cases (null, empty list, invalid scores)
