# 📝 AnnotationApp

A **Spring Boot + Thymeleaf** web application for **collaborative sentence pair annotation** (NLI, semantic similarity, etc.).

> 🎓 **Supervisor:** Prof. Tarik **Boudaa**
> 👥 **Contributors:** Mohamed-Saber **El Guelta**, Soukaina **El Hadifi**

---

## 🚀 Key Features

| Module                | Description                                                                                                 |
| --------------------- | ----------------------------------------------------------------------------------------------------------- |
| 🔐 **Authentication** | Secure login/logout via Spring Security.                                                                    |
| ⚙️ **Administration** | - Create/edit annotators <br> - Enable/disable (logical deletion)                                           |
| 📁 **Datasets**       | - Import CSV/JSON (`id, text1, text2`) <br> - Preview <br> - Assign annotators <br> - Progress tracking (%) |
| 🧠 **Annotation**     | Minimal interface for annotators to label their assigned pairs.                                             |
| 📤 **Export**         | Final export as CSV: `id, text1, text2, label, annotator, date`.                                            |

---

## 🏗️ Project Structure

```
src/main/java/
├── controller/      ← Web layer (Spring MVC)
├── service/         ← Business logic: import/export, metrics, etc.
├── entity/          ← JPA entities
├── repository/      ← Spring Data interfaces
├── security/        ← Spring Security configuration
```

```
src/main/resources/templates/
└── *.html           ← Thymeleaf views
```

---

## ⚙️ Requirements

| Tool  | Recommended Version              |
| ----- | -------------------------------- |
| JDK   | 17+                              |
| Maven | 4.0+                             |
| MySQL | 8+ (or any JDBC-compatible DBMS) |

---

## 🧪 Installation & Running

```bash
# 1. Clone the repository
git clone https://github.com/saber-elg/SpringAnnotationApp.git
cd SpringAnnotationApp

# 2. Configure the database in:
#    src/main/resources/application.properties
#    → spring.datasource.url, username, password

# 3. Run the app
mvn spring-boot:run
```

> ℹ️ Python scripts are expected in the `./scripts` folder by default.

---

## 👤 Demo Accounts

| Role   | Username   | Password   |
| ------ | ---------- | ---------- |
| Admin  | `admin`    | `ENSAH`    |
| Annot. | `med`      | `IDiWKPP4` |
|        | `soukaina` | `Ax99gavQ` |
|        | `lee`      | `0SdTbmNX` |
|        | `test`     | `JNSjrvAd` |

> 📄 See `data.sql` or use the admin interface to create new accounts.

---

## 🔄 Typical Workflow

1. **Log in** via `/login`
2. **Admin**:

   * Go to *Datasets* tab → Create (name, description, CSV/JSON)
   * Assign annotators
   * View data preview, progress %, export, and metrics
3. **Annotator**:

   * See pending sentence pairs
   * Label and save annotations
4. **Export**:

   * Final annotated CSV: `id, text1, text2, majority label, annotator, date`

---

## 📹 Live Demo

▶️ Watch the full demonstration on YouTube:
**[AnnotationApp - Demo](https://youtu.be/VgtuN56y99U)**

    🎧 Note: The explanation in the video is in French.
    🌐 You can enable English subtitles (CC) via YouTube settings.
