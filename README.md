# 💼 Job Portal Web Application

Welcome to the Job Portal project—a dynamic, role-based web application built using **Spring Boot**, **Thymeleaf**, and **Bootstrap**. This platform bridges the gap between **Job Seekers** and **Recruiters**, offering a seamless experience for job discovery, application tracking, and candidate management.

---

## ✨ What I Built

### 🔐 Role-Based Dashboards
- **Job Seekers** have access to:
  - A personalized dashboard to search and filter jobs
  - Profile editing with resume and photo upload
  - Job application and saving functionality
- **Recruiters** can:
  - Post new job listings
  - View candidates who applied to their jobs
  - Access detailed candidate profiles with resume download

### 🔍 Global Job Search
- Implemented a flexible search form with filters for:
  - Employment Type: Full-Time, Part-Time, Freelance
  - Remote Type: Remote-Only, Office-Only, Partial-Remote
  - Date Posted: Today, Last 7 Days, Last 30 Days
- Search results dynamically update based on selected filters and keywords

### 📄 Candidate Profile Viewer
- Recruiters can click on a candidate’s name to view their full profile
- Profile includes:
  - Skills with experience levels
  - Resume and profile photo
  - Personal details and job history

### 📦 File Upload Integration
- Integrated `MultipartFile` handling for:
  - Profile photo uploads
  - Resume uploads
- Files are stored in user-specific directories for easy access and organization

### 🧠 Smart Thymeleaf Binding
- Used advanced Thymeleaf techniques:
  - Indexed field binding with `*{skills[__${iterStat.index}__].name}`
  - Conditional rendering based on user roles
  - Dynamic form rehydration after submission

### 🛠️ Backend Logic
- Built robust controller methods for:
  - Job application and saving
  - Candidate profile retrieval
  - Global search with filter logic and fallback handling
- Designed service layer to handle business logic cleanly and efficiently

---

## 🧩 Technologies Used

- **Spring Boot** for backend and security
- **Thymeleaf** for server-side rendering
- **Bootstrap 4** for responsive UI
- **Font Awesome** for icons
- **JPA/Hibernate** for ORM
- **MySQL** (or H2) for data persistence

---

## 🧠 What I Learned

- How to manage role-based access with Spring Security
- Advanced Thymeleaf binding and dynamic form rendering
- Building reusable controller logic for search and filtering
- Handling file uploads securely and efficiently
- Designing clean, user-friendly dashboards for multiple roles

---

Thanks for checking out my project! Feel free to explore the code, try it out, or reach out if you're interested in collaborating.
