# BÁO CÁO KIỂM THỬ HIỆU NĂNG BẰNG JMETER

## Mục lục

1. [Giới thiệu](#1-giới-thiệu)
2. [Website được kiểm thử](#2-website-được-kiểm-thử)
3. [Môi trường kiểm thử](#3-môi-trường-kiểm-thử)
4. [Các kịch bản kiểm thử](#4-các-kịch-bản-kiểm-thử)
5. [Kết quả kiểm thử](#5-kết-quả-kiểm-thử)
6. [So sánh tổng hợp](#6-so-sánh-tổng-hợp)
7. [Kết luận](#7-kết-luận)
8. [Tài liệu đính kèm](#8-tài-liệu-đính-kèm)

## 1. Giới thiệu

Bài thực hành này sử dụng Apache JMeter để kiểm thử hiệu năng website.

### Mục tiêu

- Hiểu cách sử dụng JMeter
- Thiết kế nhiều kịch bản kiểm thử với các tham số khác nhau
- Phân tích Response Time, Throughput và Error Rate
- Đánh giá hiệu năng hệ thống

## 2. Website được kiểm thử

**Website:** https://www.wikipedia.org

**Các trang sử dụng:**

- Trang chủ: `/`
- Trang con 1: `/wiki/Mathematics`
- Trang con 2: `/wiki/Games`

**Lý do chọn:** Website công khai, ổn định, phù hợp kiểm thử tải vừa phải.

## 3. Môi trường kiểm thử

| Thành phần  | Thông tin         |
|-------------|-------------------|
| Công cụ     | Apache JMeter 5.x |
| Giao thức   | HTTPS             |
| Phương thức | GET               |
| OS          | Windows 10/11     |
| Trình duyệt | Firefox 147.0.4          |

### Cấu trúc Test Plan
Test Plan
├── HTTP Request Defaults
├── Thread Group 1 – Cơ bản
├── Thread Group 2 – Tải nặng
├── Thread Group 3 – Tùy chỉnh
├── View Results Tree
└── Summary Report
text## 4. Các kịch bản kiểm thử

### Thread Group 1 – Kịch bản cơ bản

| Tham số        | Giá trị           |
|----------------|-------------------|
| Threads        | 10                |
| Loop Count     | 5                 |
| Ramp-up        | 10 giây           |
| Request        | GET đến trang chủ |

**Mô tả:**  
Mỗi người dùng gửi 5 yêu cầu đến trang chủ.

### Thread Group 2 – Kịch bản tải nặng

| Tham số        | Giá trị                     |
|----------------|-----------------------------|
| Threads        | 50                          |
| Ramp-up        | 30 giây                     |
| Loop Count     | 1                           |
| Request        | GET trang chủ + 1 trang con |

**Mô tả:**  
Người dùng truy cập đồng thời trang chủ và một bài viết.

### Thread Group 3 – Kịch bản tùy chỉnh

| Tham số        | Giá trị             |
|----------------|---------------------|
| Threads        | 20                  |
| Duration       | 60 giây             |
| Loop           | Forever             |
| Request        | GET đến 2 trang con |

**Mô tả:**  
Mô phỏng truy cập liên tục trong 60 giây.

## 5. Kết quả kiểm thử

### Thread Group 1 – Cơ bản

| Chỉ số                | Giá trị   |
|-----------------------|-----------|
| Average Response Time | 103 ms    |
| Min                   | 64 ms     |
| Max                   | 255 ms    |
| Throughput            | 1.8 req/s |
| Error Rate            | 0%        |

**Nhận xét:**

- Thời gian phản hồi ổn định
- Không có lỗi
- Hệ thống xử lý tốt tải nhẹ

### Thread Group 2 – Tải nặng

| Chỉ số                | Giá trị   |
|-----------------------|-----------|
| Average Response Time | 247 ms    |
| Min                   | 201 ms    |
| Max                   | 1501 ms   |
| Throughput            | 3.3 req/s |
| Error Rate            | 0.0%      |

**Nhận xét:**

- Response Time tăng đáng kể
- Không có lỗi
- Hệ thống vẫn hoạt động ổn định ở mức trung bình

### Thread Group 3 – Tùy chỉnh

| Chỉ số                | Giá trị     |
|-----------------------|-------------|
| Average Response Time | 180 ms      |
| Min                   | 55 ms       |
| Max                   | 1800 ms     |
| Throughput            | 105.9 req/s |
| Error Rate            | 0%          |

**Nhận xét:**

- Ổn định trong 60 giây
- Không có lỗi 
- Throughput duy trì đều

## 6. So sánh tổng hợp

| Kịch bản     | Users | Avg Response | Throughput  | Error Rate |
|--------------|-------|--------------|-------------|----------|
| Cơ bản       | 10    | 103 ms       | 1.8 req/s   | 0%       |
| Tải nặng     | 50    | 247 ms       | 3.3 req/s   | 0%       |
| Tùy chỉnh    | 20    | 180 ms       | 105.9 req/s | 0%       |

**Nhận xét chung:**

- Response Time tăng theo số lượng người dùng
- Throughput tăng khi số thread tăng
- Error Rate thấp
- Website hoạt động ổn định ở mức tải vừa phải

## 7. Kết luận

Sau bài thực hành:

- Biết cách tạo Test Plan
- Biết cấu hình Thread Group
- Biết phân tích Summary Report
- Hiểu mối quan hệ giữa tải hệ thống và hiệu năng

**Đánh giá tổng quan:**  
Website hoạt động tốt ở mức tải nhẹ và trung bình. Khi tải tăng cao, thời gian phản hồi tăng nhưng vẫn trong giới hạn chấp nhận được.

## 8. Tài liệu đính kèm
jmeter/
├── results (chứa toàn bộ hình ảnh kết quả cho 3 trường hợp)
├── scripts (file .jmx)
└── README.md (báo cáo)