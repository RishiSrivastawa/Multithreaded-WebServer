# 🚀 Java Web Server Concurrency Models
### Single-Threaded vs Multi-Threaded vs Thread Pool
This project demonstrates **three different server concurrency models in Java** and compares how they behave under load:

1. **Single-Threaded Server**
2. **Multi-Threaded Server (Thread-per-Request)**
3. **Thread Pool Server (ExecutorService)**

The goal is to understand:

* How servers handle **multiple client requests**
* The impact of **threads** on performance
* Why **thread pools** are preferred in real-world systems
* How to **visualize performance using Apache JMeter**

---

## 📁 Project Structure

```
WEBSERVER/
│
├── SingleThreaded/
│   ├── Client.java
│   └── Server.java
│
├── MultiThreaded/
│   ├── Client.java
│   └── Server.java
│
├── ThreadPool/
│   └── Server.java
│
└── .gitignore
```

---

## 🧠 What This Project Is About

### 1️⃣ Single-Threaded Server

* Handles **one client at a time**
* New clients must **wait** until the current request finishes
* Simple but **not scalable**

**Use case:** Learning basics of sockets, not suitable for production

---

### 2️⃣ Multi-Threaded Server (Thread per Client)

* Creates a **new thread for every incoming connection**
* Multiple clients are handled concurrently
* Can lead to **too many threads** under heavy load

**Use case:** Understanding concurrency, but risky at scale

---

### 3️⃣ Thread Pool Server (Best Practice)

* Uses a **fixed number of threads**
* Requests are queued when all threads are busy
* Controlled resource usage and **high stability**

**Use case:** Real-world server design

---

## ⚙️ Requirements

* Java **8 or higher**
* Apache **JMeter**
* Any IDE or terminal (VS Code, IntelliJ, etc.)

---

## ▶️ How to Run the Project

### Step 1: Compile the Code

From the folder you want to run:

```bash
javac Server.java
javac Client.java
```

*(ThreadPool only has Server.java)*

---

### Step 2: Run the Server

#### Single-Threaded

```bash
java Server
```

#### Multi-Threaded

```bash
java Server
```

#### Thread Pool

```bash
java Server
```

All servers listen on:

```
Port: 8010
Host: localhost
```

---

### Step 3: Run the Client

#### Single-Threaded Client

```bash
java Client
```

#### Multi-Threaded Client

This client automatically spawns **100 threads**:

```bash
java Client
```

Each client thread:

* Connects to the server
* Sends a message
* Receives a response

---

## 📊 Load Testing with Apache JMeter

### Why JMeter?

JMeter allows you to:

* Simulate **hundreds or thousands of clients**
* Measure **response time**
* Observe **thread behavior**
* Visualize performance with **graphs**

---

## 🔧 JMeter Setup (Step-by-Step)

### 1️⃣ Create a Test Plan

* Open JMeter
* Right-click **Test Plan → Add → Threads → Thread Group**

### 2️⃣ Configure Thread Group

Example:

* Number of Threads (Users): `100`
* Ramp-Up Period: `10`
* Loop Count: `1`

---

### 3️⃣ Add TCP Sampler

Right-click Thread Group →
**Add → Sampler → TCP Sampler**

**Settings:**

```
Server Name: localhost
Port Number: 8010
TCPClient classname: TCPClient
```

---

### 4️⃣ Add Listeners (Graphs)

Add these listeners:

* **View Results in Table**
* **Summary Report**
* **Graph Results**
* **Aggregate Report**

---

## 🔍 What to Observe in JMeter

### 🧵 Threads

* Single-Threaded → Requests are processed **one by one**
* Multi-Threaded → Threads spike rapidly
* Thread Pool → Threads capped at pool size

---

### ⏱ Response Time

* **Single-Threaded:** Very high latency as load increases
* **Multi-Threaded:** Faster initially, degrades with many users
* **Thread Pool:** Stable and predictable

---

### 📈 Throughput

* Single-Threaded → Lowest
* Multi-Threaded → Medium
* Thread Pool → Highest and most consistent

---

### 📉 Error Rate

* Multi-Threaded may fail under heavy load (too many threads)
* Thread Pool remains stable

---

## 🧪 Key Learning Outcomes

* Creating a thread per request **does not scale**
* Thread pools prevent **resource exhaustion**
* Real servers (Tomcat, Netty, Spring Boot) use **thread pools**
* Load testing is essential to validate design decisions

---

## 🚀 Future Improvements

* Add request processing delay to simulate real workloads
* Graceful shutdown of servers
* Configurable port and pool size
* HTTP implementation instead of raw TCP

--- 

## 🧑‍💻 Author

Built for learning **Java networking, concurrency, and performance testing**.

---

