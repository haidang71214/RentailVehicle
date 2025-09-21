Car Rental App

A web-based car rental platform, built with Spring Boot and Next.js. It allows users to rent cars, manage bookings, and interact in real-time with secure authentication.

🚀 Features





🚗 Car Management – Admins add, edit, and manage car listings.



📅 Booking System – Customers book and cancel rentals.



🔐 Authentication – Secure login with symmetric cryptography (HMAC with JWT).



💬 Realtime Chat – Communicate via WebSocket.

🛠️ Tech Stack





Frontend: Next.js, TypeScript, TailwindCSS



Backend: Spring Boot, Java



ORM: JPA/Hibernate



Database: MySQL (via Docker)



Authentication: JWT with symmetric keys (HMAC)



Realtime: WebSocket



Tools: Docker, Git, Postman

📂 Project Structure

car-rental/
├── backend/                # Spring Boot server
│   ├── src/               
│   │   ├── main/          
│   │   │   ├── java/      
│   │   │   │   ├── com/   
│   │   │   │   │   ├── carrental/  # Package
│   │   │   │   │   │   ├── config/    # Config (WebSocket, JWT)
│   │   │   │   │   │   ├── controller/ # REST controllers
│   │   │   │   │   │   ├── entity/     # JPA entities
│   │   │   │   │   │   ├── repository/ # JPA repositories
│   │   │   │   │   │   ├── service/    # Business logic
│   │   │   │   │   │   └── websocket/  # WebSocket handlers
│   │   │   └── resources/  # Application properties
│   │   └── test/           # Test files
│   ├── docker-compose.yml  # Docker config for MySQL
│   ├── application.yml     # Spring config
│   ├── pom.xml             # Maven build file
└── README.md

⚡ Installation & Setup

1️⃣ Clone repository

git clone https://github.com/username/car-rental.git
cd car-rental

2️⃣ Setup backend

cd backend
./mvnw install

Set application.yml:

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/car_rental_db
    username: user
    password: password
  jpa:
    hibernate:
      ddl-auto: update
jwt:
  secret: your_symmetric_secret_key
  expiration: 86400000

4️⃣ Setup frontend

cd frontend
npm install

5️⃣ Run application

Backend:

cd backend
./mvnw spring-boot:run

Frontend:

cd frontend
npm run dev

App runs at:





Frontend: http://localhost:3000



Backend: http://localhost:8080

🧪 Tests

# Backend unit tests
cd backend
./mvnw test

# Frontend tests
cd frontend
npm run test


Fork repo.



Create branch (git checkout -b feature/your-feature).



Commit (git commit -m 'Add feature').



Push (git push origin feature/your-feature).



Create Pull Request.

📚 Resources





Spring Boot Docs



Next.js Docs



MySQL Docs



Docker Docs

📜 License

Apache 2.0
