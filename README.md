# Generic Specification Builder Example

A **sample Spring Boot project** demonstrating how to implement and use a **generic specification builder pattern** for dynamic JPA queries.  
This project allows filtering entities using **nested joins, collections, and date ranges**, and comes with **MapStruct pre-configured** for mapping nested entities and collections to DTOs.

---

## Features

- **Dynamic JPA Queries**: Build queries dynamically using specifications.
- **Nested Joins Support**: Filter on nested entities and collections.
- **Date Filtering**: Supports `LocalDate` and `OffsetDateTime` ranges.
- **MapStruct Integration**: Easily map entities and nested collections to DTOs.
- **Flexible and Reusable**: Create complex specifications without repetitive code.

---

## DefaultSpecificationBuilderInterface Methods

This interface provides a set of generic methods to create specifications for different use cases:

| Method | Description |
|--------|-------------|
| `<T> equals(String field, Object value)` | Creates a specification for exact equality on a field. |
| `<T> like(String field, String value)` | Creates a specification for `LIKE` operations on string fields. |
| `<T> betweenOffsetDateTime(String field, OffsetDateTime start, OffsetDateTime end)` | Filters a field of type `OffsetDateTime` between a start and end value. |
| `<T> betweenLocalDates(String field, LocalDate startDate, LocalDate endDate)` | Filters a field of type `LocalDate` between a start and end date. |
| `<T> nestedJoinLike(String pathString, String value)` | Performs a `LIKE` filter on a nested entity through a **single join** (non-collection). Example: `profile.nickname`. |
| `<T> nestedJoinEquals(String pathString, Object value)` | Performs an equality filter on a nested entity through a **single join**. |
| `<T> nestedCollectionJoinEquals(String pathString, String value)` | Performs an equality filter on a nested **collection**. Example: `tables.name`. |
| `<T> nestedCollectionJoinLike(String pathString, String value)` | Performs a `LIKE` filter on a nested **collection**. Example: `tables.sessions.game.title`. |
| `<T> betweenLocalDatesCollection(String field, LocalDate start, LocalDate end)` | Filters a field of type `LocalDate` inside a nested **collection** between a start and end date. Example: `tables.sessions.playedAt`. |

---

> **Note:** The `Specification.where()` method is deprecated. This project uses `Specification.allOf()` and a builder pattern for combining multiple specifications.

## Project Structure

```yaml
src/main/java
|──controller # REST Endpoints
├── entity # JPA Entities
├── dto # Data Transfer Objects
├── mapper # MapStruct mappers
├── specification # Specifications builder for dynamic queries
│ ├── builder # Generic builder interface
│ └── player # Player-specific specifications
└── service # Business logic
```

---

## Getting Started

### Clone the repository

```bash
git clone https://github.com/<your-username>/generic-specification-builder-example.git
cd generic-specification-builder-example
```

### Build the project
```bash
mvn clean install
```
### Run the Spring Boot application
```bash
mvn spring-boot:run
```
### Database Setup
The project uses H2 for testing. Sample data is already provided:

```sql
-- Players
INSERT INTO player (id, name, email, role) VALUES ...;

-- Tables
INSERT INTO table_rpg (id, name) VALUES ...;

-- Table-Player relationships
INSERT INTO table_player (table_id, player_id) VALUES ...;

-- Game sessions
INSERT INTO game_session (id, adventure_name, played_at, table_id, game_id) VALUES ...;

-- Player profiles
INSERT INTO player_profile (id, nickname, email, platform) VALUES ...;
```
### API Example
#### Filter Players
#### Endpoint:

```bash
POST /players/filter?pageNumber=0&pageSize=10&sortBy=id&sortOrder=asc
```
### Request Body Example:

```json
{
  "name": "Flávio",
  "role": "MASTER",
  "email": "flavio@example.com",
  "nickname": "legbreaker",
  "tableName": "Forgotten Realms",
  "adventureName": "Lost Mine of Phandelver",
  "playedAtStart": "2025-01-01",
  "playedAtEnd": "2025-12-31",
  "gameTitle": "Dungeons & Dragons 5e"
}
```
### cURL Example:

```bash
Copiar código
curl --location 'http://localhost:8080/players/filter?pageNumber=0&pageSize=10&sortBy=id&sortOrder=asc' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Flávio",
    "role": "MASTER",
    "email": "flavio@example.com",
    "nickname": "legbreaker",
    "tableName": "Forgotten Realms",
    "adventureName": "Lost Mine of Phandelver",
    "playedAtStart": "2025-01-01",
    "playedAtEnd": "2025-12-31",
    "gameTitle": "Dungeons & Dragons 5e"
}'
```
This request returns a paginated response (Page<PlayerFilterRespondeDTO>) with the filtered players. You can adjust pageNumber, pageSize, sortBy and sortOrder parameters as needed.

### License
This project is open-sourced under the MIT License.


