This code supports my LinkedIn article : https://www.linkedin.com/pulse/boosting-java-performance-virtual-threads-21-jay-saraiya-ewejc

Perfect — that’s a clear and helpful addition. Here’s your **final, full `README.md`**, cleanly formatted and ready to drop into your GitHub repo.
It includes your exact run instructions, configuration details, and example GET request.

---

```markdown
# 🧭 Java 21 Virtual Threads — Trip Planner Demo

This project demonstrates the performance difference between **Platform Threads** and **Virtual Threads** in **Java 21 (Project Loom)** using a realistic microservice-style application scenario.

The sample application simulates a **Trip Planner** service that fetches information from multiple “remote” services such as weather, events, recommendations, accommodations, and transportation.  
It showcases how virtual threads can dramatically improve scalability and throughput for I/O-bound workloads.

---

## 🚀 Overview

The main **Trip Planner** application makes parallel REST calls to five dummy microservices that return hardcoded data for an airport code.  
These services are simulated using a packaged JAR called `external-services.jar` located in the `/lib` folder.

You can switch between **Platform Threads** and **Virtual Threads** using a single property in the application configuration, and observe the performance difference under load using tools like **Apache JMeter**.

---

## 🧩 Architecture

```

[ JMeter ] → [ Trip Planner App (Port 8080) ] → [ External Services (Port 7070) ]
|                                  ├── Weather Service
|                                  ├── Events Service
|                                  ├── Recommendation Service
|                                  ├── Accommodation Service
|                                  └── Transportation Service
|
Thread Model: Platform / Virtual

````

---

## ⚙️ Thread Configuration

The thread model used by the application is controlled via Spring configuration and properties.

### **1. Executor Service Configuration**

The following code in `ExecutorServiceConfig.java` determines whether the application runs using **Platform Threads** or **Virtual Threads**:

```java
@Configuration
public class ExecutorServiceConfig {

    @Bean
    @ConditionalOnThreading(Threading.VIRTUAL)
    public ExecutorService virtualThreadExecutor() {
        return Executors.newVirtualThreadPerTaskExecutor();
    }

    @Bean
    @ConditionalOnThreading(Threading.PLATFORM)
    public ExecutorService platformThreadExecutor() {
        return Executors.newCachedThreadPool();
    }
}
````

### **2. Property Control**

You can control which executor configuration is used via the following property in `application.properties`:

```properties
# Enable or disable Virtual Threads
spring.threads.virtual.enabled=true
```

* `true` → Application uses **Virtual Threads**
* `false` → Application uses **Platform Threads**

---

## 🧰 Prerequisites

* **Java 21** or later
* **Maven** or **Gradle** (for build)
* **Apache JMeter** (for load testing)
* **external-services.jar** available in the `lib` folder

---

## ▶️ Running the Demo

### **Step 1: Start the External Services**

Run the mock external services using the provided JAR:

```bash
cd lib
java -jar external-services.jar
```

This starts simulated services on port **7070**.
The Trip Planner application will make REST calls to these endpoints to fetch weather, events, accommodation, recommendation, and transportation data.

---

### **Step 2: Start the Trip Planner Application**

Import the **Trip Planner** project into your favorite IDE (IntelliJ IDEA, Eclipse, or VS Code) configured to use **Java 21+**.
You can run it like any standard Spring Boot application.

By default, the application runs on **port 8080**.

You can verify that it’s working by sending a GET request:

```bash
curl http://localhost:8080/trip/LAS
```

You should receive a JSON response describing the trip details for the given airport code, for example:

```json
{
  "airportCode": "LAS",
  "weather": "Sunny, 75°F",
  "events": ["Concert at The Sphere", "Food Festival Downtown"],
  "recommendations": ["Visit Fremont Street", "Try local cuisine"],
  "accommodation": "Grand Las Vegas Hotel",
  "transportation": "Uber, Rental Car, Monorail"
}
```

---

### **Step 3: Switch Between Platform and Virtual Threads**

Edit the property in `application.properties`:

```properties
spring.threads.virtual.enabled=true
```

Restart the application after each change to test both configurations under identical conditions.

---

### **Step 4: Run Load Test with JMeter**

1. Import the provided JMeter script (`trip-planner-test.jmx`).
2. Configure:

    * 300 users
    * Ramp-up period: 5 minutes (1 user per second)
    * Test duration: 6 minutes
3. Monitor:

    * Throughput (Transactions/sec)
    * Average & 95th percentile response times
    * Total thread usage (using VisualVM or JFR)

---

## 📊 Sample Results

| Configuration    | Total Requests | Avg (ms) | Median (ms) | 95% (ms) | Max (ms) | Throughput (req/sec) |
| ---------------- | -------------- | -------- | ----------- | -------- | -------- | -------------------- |
| Platform Threads | 50,535         | 1248     | 1169        | 1592     | 5724     | 140                  |
| Virtual Threads  | 62,338         | 1011     | 1009        | 1024     | 1297     | 173                  |

### **Observations**

* Virtual Threads handled **~23% more requests** with **lower latency**.
* VisualVM showed:

    * Platform Threads → ~1,500 OS threads
    * Virtual Threads → ~51 carrier threads
* JMeter Results:

    * Platform Threads → ~200 TPS
    * Virtual Threads → ~300 TPS (limited by JMeter load)

---

## 📸 Performance Visualization

* **Thread Usage (VisualVM):**
  *Insert screenshots comparing Platform vs Virtual Threads here*

* **Transactions Per Second (JMeter):**
  *Insert TPS graphs here*

* **Response Time Trends (JMeter):**
  *Insert response time graphs here*

---

## 🧠 Key Takeaways

* Virtual threads simplify concurrency — **no reactive frameworks or callbacks** needed.
* Perfect for **I/O-bound workloads** such as REST APIs or database calls.
* Deliver **better scalability**, **lower latency**, and **fewer system threads**.
* Switching models is **as easy as flipping a property flag**.

---

## 🧩 Tech Stack

* **Java 21 (Project Loom)**
* **Spring Boot 3.x**
* **Maven Build**
* **Apache JMeter**
* **VisualVM / JFR for Monitoring**

---

## 📚 References

* [Project Loom – OpenJDK Docs](https://openjdk.org/projects/loom/)
* [Java 21 Virtual Threads Overview](https://docs.oracle.com/en/java/javase/21/core/virtual-threads.html)
* [Spring Boot Conditional Threading Support](https://docs.spring.io/)

---

## 🧑‍💻 Author

**Jay Saraiya**
Architect | Performance Enthusiast | Java Concurrency Explorer

🔗 [LinkedIn](https://www.linkedin.com/in/jay-saraiya/)
💬 Feedback and contributions are welcome!

---

```

---

Would you like me to add a short section called **“🧪 Example JMeter Command-Line Execution”** (showing how to run your `.jmx` file headlessly with results output)? It helps others reproduce your performance test easily.
```
