# 🎮 JavaQuest

<div align="center">

![Java Version](https://img.shields.io/badge/Java-25-orange?style=flat&logo=openjdk)
![Maven](https://img.shields.io/badge/Maven-3.9-red?style=flat&logo=apachemaven)
![License](https://img.shields.io/badge/License-Education-blue?style=flat)
![Tests](https://img.shields.io/badge/Tests-166%20passing-success?style=flat&logo=junit)

**A turn-based strategy game built in Java with a console UI**

[Features](#-features) • [Tech Stack](#-tech-stack) • [Installation](#-installation) • [Usage](#-usage) • [Testing](#-testing) • [Project Structure](#-project-structure)

</div>

---

## 📖 About

JavaQuest is a turn-based strategy game where players manage territories, resources, and armies to conquer a procedurally generated map. Each player starts with a few territories and must strategically expand their empire while managing limited resources and military forces.

The project was developed as part of a Object-Oriented Programming (OOP) course in Java, implementing clean architecture patterns and comprehensive unit testing.

---

## ✨ Features

### 🏰 Territory Management
- **5 unique biomes** with distinct characteristics:
  - 🌿 **Plains** - Food production, cavalry bonus
  - 🌲 **Forests** - Wood production, archer bonus
  - ⛰️ **Mountains** - Stone/metal production, defensive bonus
  - 💧 **Water** - Natural barrier (no building)
  - 🏜️ **Deserts** - Low production, high risk

### 💼 Resource System
- **5 resources** to manage:
  - 🍔 **Food** - Feeds population and armies
  - 🪵 **Wood** - Construction and light units
  - 🪨 **Stone** - Fortifications and defenses
  - ⚔️ **Metal** - Heavy units and buildings
  - 💰 **Gold** - Universal currency

### ⚔️ Military System
- **5 unit types** with unique abilities:
  - 🛡️ **Infantry** - Balanced, cheap
  - 🏹 **Archer** - Bonus in forests
  - 🐎 **Cavalry** - Bonus in plains
  - 🐴 **Knight** - Heavy cavalry
  - ⚜️ **Paladin** - Elite unit, high cost

- Combat system based on:
  - Unit power and quantity
  - Terrain bonuses
  - Building defenses

### 🏗️ Buildings
- **6 building types**:
  - 🏕️ **Farm** - +5 Food
  - 🪓 **Sawmill** - +10 Wood
  - ⛏️ **Mine** - +10 Stone, +5 Metal
  - ⚖️ **Market** - +3 Gold
  - 🏰 **Fort (Outpost)** - +50 Defense
  - 🚧 **Barricade** - +10 Defense

### 🎯 Victory Conditions
1. **Territory Victory** - Control the most tiles after a fixed number of turns
2. **Military Victory** - Eliminate all opponents
3. **Economic Victory** - Accumulate a target amount of gold

---

## 🛠️ Tech Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| **Java** | 25 | Core language |
| **Maven** | 3.9 | Build management |
| **JUnit** | 3.8.1 | Unit testing |
| **Lanterna** | 3.1.1 | Console UI framework |

---

## 📦 Installation

### Prerequisites
- Java JDK 25 or higher
- Maven 3.9 or higher

### Clone the repository
```bash
git clone https://github.com/your-username/java-quest.git
cd java-quest
```

### Build the project
```bash
mvn clean compile
```

---

## 🚀 Usage

### Run the game
```bash
mvn exec:java
```

### Using Maven directly
```bash
mvn clean package
java -jar target/java-quest-1.0-SNAPSHOT.jar
```

---

## 🧪 Testing

The project includes **166 unit tests** covering all major systems:

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=ResourceHandlerTest

# Run with coverage
mvn clean test jacoco:report
```

### Test Coverage
- ✅ **ResourceHandler** - Resource management (10 tests)
- ✅ **ArmyHandler** - Army unit management (12 tests)
- ✅ **Squad** - Combat and power calculations (21 tests)
- ✅ **WarUnitType** - Unit behaviors and costs (18 tests)
- ✅ **Tile & Biomes** - Map tiles and terrain (34 tests)
- ✅ **Map & MapBuilder** - Map generation (17 tests)
- ✅ **Builds** - Building bonuses and costs (30 tests)
- ✅ **Market** - Resource trading (12 tests)
- ✅ **Player** - Player management (11 tests)

---

## 📁 Project Structure

```
java-quest/
├── src/
│   ├── main/java/JavaQuest/
│   │   ├── Game/
│   │   │   ├── Core/
│   │   │   │   ├── Army/           # Military system
│   │   │   │   ├── Map/            # Map generation & tiles
│   │   │   │   ├── Market/         # Trading system
│   │   │   │   ├── Resources/      # Resource management
│   │   │   │   ├── Player.java     # Player entity
│   │   │   │   └── Round.java      # Turn management
│   │   │   ├── Rendering/          # Console UI
│   │   │   ├── Game.java           # Main game logic
│   │   │   ├── GameManager.java    # Singleton pattern
│   │   │   └── GameConfig.java    # Configuration
│   │   ├── Exceptions/             # Custom exceptions
│   │   └── App.java                # Entry point
│   │
│   └── test/java/JavaQuest/
│       ├── Game/Core/
│       │   ├── Army/               # Army tests
│       │   ├── Map/                # Map tests
│       │   ├── Market/             # Market tests
│       │   ├── Resources/          # Resource tests
│       │   ├── PlayerTest.java
│       │   └── ...
│       └── AppTest.java            # Example test
│
├── enonce.pdf                      # Project requirements
├── plan.txt                        # Detailed project plan
├── pom.xml                         # Maven configuration
└── README.md                       # This file
```

---

## 🎓 Learning Objectives

This project demonstrates:
- **OOP Principles**: Encapsulation, inheritance, polymorphism
- **Design Patterns**: Singleton, Builder, Strategy
- **Clean Architecture**: Separation of concerns, modularity
- **Unit Testing**: Comprehensive test coverage
- **Console UI**: Text-based interface with Lanterna
- **Game Development**: Turn-based logic, state management

---

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📝 License

This project is created for educational purposes as part of an OOP course.

---

## 👥 Authors

- **Your Name** - *Initial work* - [Your GitHub](https://github.com/your-username)

---

## 🙏 Acknowledgments

- Developed for Object-Oriented Programming course
- Using [Lanterna](https://github.com/mabe02/lanterna) for console UI
- Inspired by classic turn-based strategy games

---

<div align="center">

**Made with ❤️ for educational purposes**

[⬆ Back to Top](#-javaquest)

</div>
