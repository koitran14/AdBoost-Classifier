Sure! Here's a polished and well-decorated version of your `README.md` file for the **Data-Mining-Proj** project, styled in proper Markdown with headings, code blocks, and tips:

---

# 📊 Data-Mining-Proj

A Java project for data mining using **Weka** and **Maven**, built with **Visual Studio Code**.

---

## 🧰 Prerequisites

Make sure you have the following installed:

- **Java JDK 8+**  
  Check with:

  ```bash
  java -version
  ```

- **Apache Maven**  
  Check with:

  ```bash
  mvn --version
  ```

- **VS Code Extensions**  
  Install:
  - [Java Extension Pack](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)
  - [Maven for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-maven)

---

## ⚙️ Project Setup

1. **Open the project folder** in Visual Studio Code.

2. **Verify the Weka dependency** inside `pom.xml`:

   ```xml
   <dependency>
        <groupId>nz.ac.waikato.cms.weka</groupId>
        <artifactId>weka-stable</artifactId>
        <version>3.8.6</version>
    </dependency>
   ```

3. **Sync the project** by running:
   ```bash
   mvn clean install
   ```

---

Got it! Here's the updated **"🚀 Running the Project"** section with your instructions integrated clearly and professionally:

---

## 🚀 Running the Project

1. **Save the project** after opening it in VS Code:  
   Press `Ctrl + S` — this will automatically trigger dependency installation if you have the **Java Extension Pack** and **Maven for Java** installed.

2. **Run the GUI**:  
   In the **Project Explorer**, right-click on your main class file (e.g., `MainFrame.java`) and select:

   ```
   Run Java
   ```

   > 🔁 Replace `MainFrame.java` with your actual main class file if it's different.

---

## 🛠️ Troubleshooting

- **JAVA_HOME Not Set?**  
  Make sure your `JAVA_HOME` environment variable points to your JDK installation.

---

## ✅ You're All Set!

Enjoy analyzing your data with the custom-built GUI powered by Weka and Java! 🎉

---

Let me know if you'd like to add badges, license info, or contribute sections!
