# MySiteLOCATION

A Java-based web application for managing property rentals and reservations. Users can create accounts, browse available properties, manage reservations, and update their profiles.

## Overview

MySiteLOCATION is an accommodation rental platform built with Java, JSP, and Servlets. It provides a complete system for users to discover rental properties, make reservations, and manage their bookings. The application features a user-friendly interface with role-based functionality.

## Features

- **User Management**
  - User registration and authentication
  - Profile creation and updates
  - User account management

- **Property Catalog**
  - Browse available properties
  - View property details (type, description, daily price)
  - Check property availability

- **Reservation System**
  - Create new reservations
  - View reservation history
  - Manage active reservations
  - Cancel reservations
  - Track reservation status

- **Dashboard**
  - Personalized user dashboard
  - Quick access to reservations
  - Profile management

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   ├── Controllers/           # Servlet controllers handling HTTP requests
│   │   │   ├── ServletVerification.java
│   │   │   ├── CreateAccount.java
│   │   │   ├── CatalogServlet.java
│   │   │   ├── ReservationServlet.java
│   │   │   ├── GestionReservation.java
│   │   │   ├── AnnulReservation.java
│   │   │   ├── UpdateProfileServlet.java
│   │   │   └── UserServlet.java
│   │   ├── DAO/                   # Data Access Objects for database operations
│   │   │   ├── UtilisateurDAO.java    # User database operations
│   │   │   ├── BienDAO.java           # Property database operations
│   │   │   ├── ReservationDAO.java    # Reservation database operations
│   │   │   └── SingletonConnection.java # Database connection manager
│   │   └── Model/                 # Entity models
│   │       ├── Utilisateur.java   # User model
│   │       ├── Bien.java          # Property model
│   │       └── Reservation.java   # Reservation model
│   └── webapp/
│       ├── index.jsp              # Home page
│       ├── login.jsp              # Login page
│       ├── CreateAccount.jsp      # Registration page
│       ├── Catalog.jsp            # Property catalog
│       ├── LesRESERVATION.jsp     # Reservations page
│       ├── profil.jsp             # User profile page
│       ├── indexUtilisateur.jsp   # User dashboard
│       ├── logout.jsp             # Logout handler
│       ├── style4.css             # Main stylesheet
│       ├── CreataAccount.css      # Registration page styles
│       └── WEB-INF/
│           ├── web.xml            # Web application configuration
│           └── confirmation.jsp   # Confirmation page
```

## Technology Stack

- **Backend:** Java, Servlets, JSP (JavaServer Pages)
- **Database:** SQL Database (configured via SingletonConnection)
- **Frontend:** HTML, CSS, JSP
- **Architecture:** MVC (Model-View-Controller) Pattern with DAO

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Apache Tomcat 9.x or higher
- MySQL or compatible SQL database
- Maven (optional, for dependency management)

### Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd MySiteLOCATION
   ```

2. **Configure Database Connection**
   - Update database connection details in `src/main/java/DAO/SingletonConnection.java`
   - Create necessary database tables for Utilisateur, Bien, and Reservation

3. **Build the Project**
   - Package the application as a WAR file
   - Deploy to Tomcat webapps directory

4. **Access the Application**
   - Navigate to `http://localhost:8080/MySiteLOCATION`
   - Default entry point: Login page

### Quick Deploy (Ubuntu + Tomcat 10)

This repository now includes:

- `scripts/init.sql`: creates database, user, and required tables.
- `scripts/deploy.sh`: builds WAR, initializes DB, configures Tomcat DB environment, deploys, restarts Tomcat, and checks app availability.

Run:

```bash
chmod +x scripts/deploy.sh
./scripts/deploy.sh
```

Optional environment variables:

- `DB_URL` (default: `jdbc:mysql://localhost:3306/locationenligne?serverTimezone=UTC`)
- `DB_USER` (default: `locationapp`)
- `DB_PASSWORD` (default: `locationpass`)
- `TOMCAT_SERVICE` (default: `tomcat10`)
- `TOMCAT_WEBAPPS_DIR` (default: `/var/lib/tomcat10/webapps`)
- `APP_NAME` (default: `MySiteLOCATION`)
- `APP_URL` (default: `http://localhost:8080/MySiteLOCATION/`)
- `SKIP_DB_INIT=1` to skip SQL initialization

### Run With Docker

You can run the full stack (Tomcat + MySQL) with Docker Compose.

Files added for Docker:

- `Dockerfile`: builds the WAR and runs it on Tomcat 10.
- `docker-compose.yml`: starts `app` + `mysql` services.
- `.dockerignore`: excludes build/git/editor files from Docker context.

Start everything:

```bash
docker compose up --build -d
```

Watch logs:

```bash
docker compose logs -f app
docker compose logs -f mysql
```

Open application:

- `http://localhost:8080/MySiteLOCATION/`

Stop stack:

```bash
docker compose down
```

Reset database volume (fresh start):

```bash
docker compose down -v
```

Notes:

- MySQL is exposed on host port `3307`.
- DB credentials used by default: `locationapp` / `locationpass`.
- SQL bootstrap is loaded from `scripts/init.sql` on first DB initialization.

## Usage

### For New Users
1. Click "Create Account" on the login page
2. Fill in registration details
3. Login with your credentials

### For Browsing Properties
1. Login to your account
2. Navigate to the Catalog
3. Browse available properties
4. View details and pricing

### For Making a Reservation
1. Select a property from the catalog
2. Choose check-in and check-out dates
3. Confirm the reservation
4. View confirmation message

### For Managing Reservations
1. Go to "My Reservations"
2. View active or past reservations
3. Cancel a reservation if needed
4. Track reservation status

## Key Classes

### Model Classes
- **Utilisateur:** Represents a user with profile information
- **Bien:** Represents a rental property with type, description, and daily price
- **Reservation:** Represents a booking with dates, status, and pricing

### DAO Classes
- **UtilisateurDAO:** Handles user creation, authentication, and profile updates
- **BienDAO:** Retrieves and manages property listings
- **ReservationDAO:** Manages reservation creation, updates, and cancellations
- **SingletonConnection:** Provides centralized database connection management

### Servlet Controllers
- **ServletVerification:** Authenticates user login
- **CreateAccount:** Handles user registration
- **CatalogServlet:** Displays available properties
- **ReservationServlet:** Creates new reservations
- **GestionReservation:** Manages user reservations
- **AnnulReservation:** Cancels existing reservations
- **UpdateProfileServlet:** Updates user profile information
- **UserServlet:** Handles general user operations

## Database Schema

The application uses the following main entities:
- **Utilisateur:** User accounts with login credentials and profile data
- **Bien:** Rental properties with type, description, daily rate, and availability
- **Reservation:** Booking records with dates, pricing, and status

## Contributing

Contributions are welcome! Please follow these guidelines:
1. Create a feature branch for new features
2. Write clear commit messages
3. Test changes thoroughly before submitting
4. Submit a pull request with a description of changes

## License

This project is provided as-is. Modify and use according to your needs.

## Support

For issues or questions, please contact the development team or create an issue in the repository.

---

**Project Name:** MySiteLOCATION  
**Version:** 1.0  
**Last Updated:** March 2026
