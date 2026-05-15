# 🐠 JavaFX Fish Simulation

<div align="center">

![Java](https://img.shields.io/badge/Java-24-orange?style=for-the-badge&logo=openjdk&logoColor=white)
![JavaFX](https://img.shields.io/badge/JavaFX-25-blue?style=for-the-badge&logo=openjfx&logoColor=white)
![Maven](https://img.shields.io/badge/Apache%20Maven-Wrapper-red?style=for-the-badge&logo=apache-maven&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)

*A simulation that explores software architecture paradigms and integrates real quantum randomness.*


![demo-peceraa](https://github.com/user-attachments/assets/3c0c5256-a54f-4c06-9761-1fe992b2cc79)

</div>


---


## 🚀 Project Evolution & Learning Goals

> **This isn't just a static project; it's a technical journey.** Each version represents a deep dive into a different architectural approach, tackling the same problem with increasingly sophisticated solutions.

### 📊 Technical Roadmap

| **Version** | **Branch** | **Paradigm** | **Key Concept** | **Status** |
|-------------|------------|--------------|-----------------|------------|
| **v1.0** | [`version-1`](../../tree/version-1) |  🔷 Composition & Strategy | Modern design favoring composition over inheritance | ✅ Complete |
| **v1.1** | [`quantum_experiment`](../../tree/quantum_experiment) | ⚛️ Quantum Randomness | Real quantum numbers from ANU API (baaed on v.1.0)| ✅ Complete |
| **v2.0** | [`ECS`](../../tree/ECS_%2333) | 🏗️ ECS Architecture | Pure Entity-Component-System for data-oriented design | 🚧 In Progress |

---

## 🧩 Architectural Evolution

<div align="center">

---

</div>

### 🔷 Version 1: Composition & Strategy Pattern

**Core Design**: Composition over Inheritance with Dependency Injection

The system uses composition and the Strategy pattern to build flexible entities. Each SceneObject is composed of injectable behaviors:

- **Animation strategy**: Handles rendering (e.g.,AnimationCoralFish, AnimationBubbleIdle, AnimationFishIdle)
- **Movement strategy**: Handles movement (e.g.,MovementRebound, LinearMovement)

```java
// Usage: Inject behaviors at construction
Animation animation = new AnimationCoralFish(30.0);
Movement movement = new MovementRebound();
Fish fish = new Fish(position, movement, animation);
```

**Key Benefits**: Flexible, extensible, testable, and SOLID-compliant design.

> 📖 **Implementation Details**: Available in [`version-1`](../../tree/version-1) branch

<div align="center">

---

</div>

### ⚛️ Version 1.1: The Quantum Advantage

<div align="center">
<img src="https://img.shields.io/badge/Quantum-Powered-blueviolet?style=flat-square&logo=atom&logoColor=white" alt="Quantum Powered">
</div>

This version integrates with the **Australian National University (ANU) Quantum Random Number Generator**. This API provides true randomness by measuring the quantum fluctuations of the vacuum, based on Heisenberg's uncertainty principle.

#### 🔬 Why Quantum?
- ✨ **True randomness**: Replaces Java's pseudo-random generators (Random, Math.random())
- 🐟 **Authentic behavior**: Fish behave with genuine, physical unpredictability
- 🧬 **Real physics**: Based on fundamental quantum mechanical principles

> 📚 **API Documentation**: [ANU Quantum RNG](https://qrng.anu.edu.au/contact/api-documentation/)  
> 📖 **Implementation Details**: Available in [`quantum_experiment`](../../tree/quantum_experiment) branch

<div align="center">

---

</div>

###  🏗️ Version 2: Entity-Component-System (ECS) Architecture

To explore the limits of composition and data-oriented design, this version implements a pure ECS architecture:

- **Entities** become mere identifiers, devoid of logic
- **Components** become pure data containers (e.g., PositionComponent, ColliderComponent, SpriteComponet)
- **Systems** become processors that act on all entities possessing required components

**Why switch to ECS?**
- 🔄 **Flexibility**: Components can be combined in different ways for varied entity types
- 🧩 **Collision control**: easier to implement and manage collision systems across thousands of objects
- ⚡ **Scalability**: data-oriented design optimizes performance for large-scale simulations
- 🎮 **Industry alignment**: ECS is widely used in modern game engines (Unity, Bevy, etc.)

This experimental shift aims to prepare the simulation for **collision detection between fishes, bubbles, and corals**, enabling richer interactions and emergent behaviors.

> 📖 **Actually working**: Available in [`ECS`](../../tree/ECS_%2333) branch

<div align="center">

---

</div>

---

## ⚙️ Prerequisites & Setup

| **Requirement** | **Version** | **Purpose** |
|-----------------|-------------|-------------|
| ☕ **JDK** | 24+ | Core runtime and language features |
| 🔨 **Maven Wrapper** | Bundled | Build and dependency management |
| 🌐 **Internet** | - | Maven dependencies and Quantum API |

**Recommended IDEs:**
- 🚀 **IntelliJ IDEA** (Ultimate/Community + Maven plugin)
- 🔵 **NetBeans** (Full Java SE support)
- 💚 **VS Code** + Java Extension Pack

**Quick Start:**

```powershell
.\run.ps1
```

For detailed setup, IDE instructions, and troubleshooting, see [`docs/RUNNING.md`](docs/RUNNING.md).

---

## 🚀 Desktop Releases

The repository includes a GitHub Actions workflow that packages desktop builds for Windows, Linux, and macOS and publishes them to GitHub Releases when you push a version tag.

```bash
git tag v0.1.0
git push origin v0.1.0
```

You can also run the workflow manually from the Actions tab to generate downloadable artifacts without creating a release.

---

## 📁 Navigating the Versions

Each version branch contains its own source code, resources, and implementation details:

- 🏗️ **Architecture implementation** - See how each design pattern is applied
- ⚡ **Specific execution setup** - Maven configuration and dependencies  
- 🎯 **Code examples** - Real implementations of the architectural concepts
- 📁 **Project structure** - How components are organized in each approach

---

## 🛠️ Built With

<div align="center">

| Technology | Purpose | Version |
|------------|---------|---------|
| ☕ **Java** | Programming language | 24 |
| 🎨 **JavaFX** | Graphics and UI library | 25 |
| 🔨**Apache Maven Wrapper** | Build management | 3.9.14 |
| 📄 **Maven** | Build tool executed by the wrapper | 3.9.14 |
| ⚛️ **ANU Quantum API** | Source of true random numbers | v1 |

</div>

---

<div align="center">

### 🌟 Ready to explore different architectural paradigms?

**Choose your journey:**

[![Version 1](https://img.shields.io/badge/v1.0-Composition%20%26%20Strategy-orange?style=for-the-badge)](../../tree/version-1)
[![Version 1.1](https://img.shields.io/badge/v1.1-Quantum%20Random-blueviolet?style=for-the-badge)](../../tree/quantum_experiment)
[![Version 2](https://img.shields.io/badge/v2.0-Entity%20Component%20System-blue?style=for-the-badge)](../../tree/ECS_%2333)

</div>

---

## 🤝 Contributing

Found a bug? Contributions are welcome!

---

## 📄 License

This project is licensed under the MIT License.


---
<div align="center">

*Made with ❤️ for learning software architecture*

🐛 [Report Issues](../../issues)  • ⭐ [Star this repo](../../stargazers) • 🚀 [Latest Release](../../releases)

</div>
