# 🎬 Sakila Actor Service (Spring Boot + MySQL + Docker Compose)

**Actor Service** là một ứng dụng Java **Spring Boot** để quản lý thông tin diễn viên trong cơ sở dữ liệu **Sakila**.  
Ứng dụng sử dụng **Docker Compose** để khởi chạy **MySQL** và hỗ trợ test API bằng file **test-api.http**.

---

## 🧱 Cấu trúc dự án

```
├── db/
│   └── docker-compose.yml
├── sakila-mysql.sql
├── src/
│   ├── main/java/com/hoang/actorservice/
│   │   ├── controller/ActorController.java
│   │   ├── dto
│   │   │   ├── ActorRequestDTO.java
│   │   │   └── ActorResponseDTO.java
│   │   ├── mapper/ActorMapper.java
│   │   ├── model/Actor.java
│   │   ├── repository/ActorRepository.java
│   │   ├── service/ActorService.java
│   │   └── ActorServiceApplication.java
│   └── resources/application.properties
├── test-api.http
└── README.md
```

---

## 1. Yêu cầu môi trường

Cần cài đặt trước:

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)
- [Maven](https://maven.apache.org/)
- IDE như **IntelliJ IDEA** hoặc **VS Code**

---

## 2. Khởi chạy MySQL bằng Docker Compose

### Bước 1 — Di chuyển vào thư mục `db`

```bash
cd db
```

### Bước 2 — Khởi chạy container MySQL

```bash
docker compose up -d
```

- Container sẽ tự tạo cơ sở dữ liệu `sakila` và khởi tạo dữ liệu từ file `sakila-mysql.sql` ở thư mục gốc.
- Kiểm tra container đang chạy:
  ```bash
  docker ps
  ```

---

## 3. Chạy ứng dụng Spring Boot

Từ thư mục gốc dự án, chạy:

```bash
mvn spring-boot:run
```

hoặc:

```bash
mvn clean package
java -jar target/actorservice-0.0.1-SNAPSHOT.jar
```

hoặc 
- **Với IntelliJ IDEA:**  
  Mở dự án → Mở file `ActorServiceApplication.java` (file có annotation `@SpringBootApplication`)  
  → Nhấn **Run ▶️** (hoặc tổ hợp phím `Shift + F10`).

Ứng dụng chạy tại:  
👉 `http://localhost:8080`

---

## 4. Test API bằng file `test-api.http`

Mở file `test-api.http` (nằm ở thư mục gốc)

---

## 5. Các API chính

| Method | Endpoint           | Mô tả               |
|---------|--------------------|---------------------|
| GET     | `/actors`          | Lấy danh sách actor |
| GET     | `/actors/{id}`     | Lấy actor theo ID   |
| POST    | `/actors`          | Thêm mới actor      |
| PUT     | `/actors/{id}`     | Cập nhật actor      |
| DELETE  | `/actors/{id}`     | Xóa actor           |

---

## 6. Dọn dẹp

Khi không cần dùng nữa, dừng và xóa container MySQL:

```bash
cd db
docker compose down -v
```

---

## 7. Giám sát log ứng dụng với ELK Stack (Elasticsearch + Kibana + Filebeat)

Cấu hình Docker Compose (thư mục docker-compose.yml kèm filebeat.yml ở ngoài cùng)

```bash
docker compose down -v
```

---

## 8. Truy cập Kibana

Mở trình duyệt truy cập: http://localhost:5601

Vào Discover → Create Index Pattern
Index name: filebeat-*
Chọn trường thời gian: @timestamp
Sau khi tạo xong, bạn sẽ thấy toàn bộ log của ứng dụng hiển thị dạng JSON với thời gian thực.

