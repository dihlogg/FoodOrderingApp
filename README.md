# Food Ordering App

Ứng dụng đặt món ăn (Food Ordering App) là một ứng dụng Android được xây dựng bằng Kotlin, cho phép người dùng duyệt thực đơn, đặt món ăn và quản lý đơn hàng một cách dễ dàng. Ứng dụng tích hợp giao diện người dùng thân thiện và thông báo trạng thái đơn hàng.

## Mô tả dự án
Ứng dụng Food Ordering App được thiết kế để cung cấp trải nghiệm đặt món ăn trực tuyến, người dùng có thể:
- Xem danh sách các món ăn từ nhà hàng.
- Thêm món ăn vào giỏ hàng.
- Theo dõi trạng thái đơn hàng theo thời gian thực.

## Tính năng chính
- **Duyệt thực đơn**: Hiển thị danh sách món ăn với hình ảnh, giá cả và mô tả chi tiết.
- **Giỏ hàng**: Quản lý các món ăn đã chọn trước khi đặt hàng.
- **Thông báo**: Nhận thông báo về trạng thái đơn hàng (đang xử lý, đã giao, v.v.).
- **Quản lý tài khoản**: Đăng nhập, đăng ký và quản lý thông tin cá nhân.

## Công nghệ sử dụng
- **Ngôn ngữ lập trình**: Kotlin
- **Platform**: Android
- **Thư viện**:
  - Retrofit: Gửi và nhận dữ liệu từ API.
  - RoomDB(database local): Lưu trữ dữ liệu cục bộ.
- **API**: ASP.NET Core Api, Entity Framework Core(ORM)
- **Database**: PostgreSql
- **IDE**: Android Studio

## Hướng dẫn cài đặt
1. **Sao chép repository**:
   ```bash
   git clone https://github.com/dihlogg/FoodOrderingApp
