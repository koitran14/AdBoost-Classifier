# 📊 Data-Mining-Proj

A Java-based data mining application powered by **Weka** and **Maven**, with an optional Swing-based GUI — designed and built in **Visual Studio Code**.

---

## 📚 Table of Content

- [🧰 Prerequisites](#-prerequisites)
- [⚙️ Project Setup](#️-project-setup)
- [🚀 Running the Project](#-running-the-project)
- [🛠️ Troubleshooting](#️-troubleshooting)
- [📂 Project Structure](#-project-structure)
- [📦 Project Structure Overview](#-project-structure-overview)
- [✅ You're All Set!](#-youre-all-set)

---

## 🧰 Prerequisites

Make sure the following tools and extensions are installed on your system:

- **Java JDK 8+**  
  ```bash
  java -version
  ```

- **Apache Maven**  
  ```bash
  mvn --version
  ```

- **Visual Studio Code Extensions**  
  - [Java Extension Pack](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)
  - [Maven for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-maven)

---

## ⚙️ Project Setup

1. **Open the project folder** in VS Code.

2. **Verify Weka dependency** in `pom.xml`:
   ```xml
   <dependency>
     <groupId>nz.ac.waikato.cms.weka</groupId>
     <artifactId>weka-stable</artifactId>
     <version>3.8.6</version>
   </dependency>
   ```

3. **Install dependencies** by running:
   ```bash
   mvn clean install
   ```

---

## 🚀 Running the Project

1. **Save the project**  
   Press `Ctrl + S` in VS Code — this triggers auto-build and downloads all required dependencies (with Java + Maven extensions installed).

2. **Run the application**  
   Right-click on your main class (e.g., `MainFrame.java`) in the **Project Explorer** and select:

   ```
   Run Java
   ```

   > 🔁 Replace `MainFrame.java` with your actual entry class if different.

---

## 🛠️ Troubleshooting

- **JAVA_HOME Not Set?**  
  Make sure the `JAVA_HOME` environment variable points to your JDK installation.

- **Dependency issues?**  
  Use the command:
  ```bash
  mvn dependency:tree
  ```

---

## 📂 Project Structure

```
project-root/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/
│   │   │       ├── data/            # Data handling module
│   │   │       ├── algorithms/      # Algorithm modules
│   │   │       ├── controller/      # Controls flow
│   │   │       ├── gui/             # GUI (optional)
│   │   │       └── utils/           # Helpers
│   │   │       └── evaluation/      # Evaluation
│   │   └── resources/               # Static data (dataset csv, arff...)
│   └── test/
│       └── java/com/example/tests/  # JUnit Tests
│
├── pom.xml
└── README.md
```

---

## 📦 Project Structure Overview

| **Module**            | **Folder**     | **Purpose**                                                                 | **Key Classes/Files**                                  | **Notes**                                                                 |
|-----------------------|----------------|------------------------------------------------------------------------------|--------------------------------------------------------|---------------------------------------------------------------------------
| **Data Handling**     | `data/`         | Load and manage datasets                                                    | `DataLoader.java`                                      | Loads datasets (e.g., ARFF), stores as Weka `Instances`                   |
| **Algorithms**        | `algorithms/`   | Encapsulate individual algorithms                                           | `Algorithm.java`, `J48Classifier.java`, `NaiveBayes.java` | Implements common interface for flexibility                               |
| **Controller**        | `controller/`   | Manage data and algorithm flow                                              | `AnalysisController.java`                              | Handles data loading and algorithm execution  -> Remember to **add new classifiers** if its evaluation required                            |
| **GUI (Optional)**    | `gui/`          | User interface for dataset and algorithm interaction                        | `MainFrame.java`                                       |Call directly to mining controller to handle various datasets   |
| **Utilities**         | `utils/`        | Reusable helper functions and tools                                         | Logging, validation utilities                          | Shared logic across modules                                               |
| **Testing**           | `tests/`        | Validate functionality and correctness                                     | `AlgorithmTests.java`, integration tests               | Use **JUnit** or **TestNG** for testing                                   |
| **Evaluation**           | `evaluation/`        | evaluate selected classifier with given dataset and visualize its result.                                    | `ModelEvaluator.java`              | including Accuracy, F1-score, Recall,...                             |
---
