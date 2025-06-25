<div align="center">

![Call of Duty Logo](assets/codlogo.png)

An unofficial wrapper for the official Call of Duty API

> ⚠️ **Warning – Use with caution**  
> The Call of Duty API is not officially open for public use and is intended for authorized partners only.  
> Using this API without proper authorization may go against Activision's terms of service and could potentially lead to access restrictions or legal action.  
> Activision has already taken action in the past against projects using their services without approval.  
> 🔗 [Example: Shutdown of the SBMM Warzone website (Eurogamer)](https://www.eurogamer.net/creators-of-hugely-popular-sbmm-warzone-website-say-activision-has-ordered-them-to-shut-down-by-monday)

</div>

## Summary

1. [Introduction](#introduction)
2. [Differences between routes](#differences-between-routes)
3. [How to use](#how-to-use)
   1. [Fetch Title data](#fetch-title-data)
   2. [Fetch User data](#fetch-user-data)
4. [Working with events](#working-with-events)
5. [Installation](#installation)
   1. [Gradle](#gradle)
   2. [Maven](#maven)

## Introduction

<div style="text-align: justify">
<p>
This wrapper acts as an abstraction layer over the official Call of Duty API, facilitating data retrieval and manipulation.

Please note that the project is still in development and may not be fully stable.

You are welcome to contribute.
</p>

This project was originally created to support [OtherGun](https://www.youtube.com/c/otherGun/) with his video on Modern Warfare 2019.

## Differences between routes

<p>

The Call of Duty API provides three types of routes: Public, Private, and Protected.

- **Private routes**: Accessible only by the authenticated client; they contain personal account data.
- **Protected routes**: Require authentication but allow access to data about other players.
- **Public routes**: Require no authentication and are freely accessible.

</p>

## How to use

<p>

This library offers classes for each Call of Duty title that supports API interaction. These classes provide game-specific methods. It also includes support for user-related operations.

</p>

### Authentication (v1.0.4 and above)

<p>

To perform operations that require authentication, you must now explicitly call the `authenticate` method on the `RequestManager` instance:

</p>

```java
final RequestManager requestManager = new RequestManager(new TestListener());
requestManager.authenticate("Your_SSO_TOKEN");
```

### Important note

<p>

Private and Protected routes require authentication using an SSO Token linked to your Activision account. This token is unique and **must not be shared**.

Due to Captcha protections added by Activision, it is no longer possible to retrieve this token via automated requests. You must obtain it manually:

</p>

- Go to [https://profile.callofduty.com/cod/login](https://profile.callofduty.com/cod/login)
- Log in using your Activision credentials
- Open DevTools > Storage > Cookies and find the cookie named `ACT_SSO_COOKIE`

---

### Fetch Title data

To retrieve data for a specific title (e.g., Black Ops 3), instantiate the corresponding class with a `RequestManager` object:

```java
public class Main {
   public static void main(String[] args) {
      RequestManager request = new RequestManager();
      request.authenticate("your_sso_token"); // Optional if already authenticated elsewhere

      BlackOps3 bo3 = new BlackOps3(request);
      bo3.getLeaderboard(Platform.PLAYSTATION, TimeFrame.ALLTIME, Gamemode.CAREER, GameType.HARDCORE, 1);
   }
}
```

⚠️ **Instantiate `RequestManager` only once** to avoid performance overhead due to repeated HTTP client creation.

### Fetch User data

```java
public class Main {
   public static void main(String[] args) {
      RequestManager request = new RequestManager();
      request.authenticate("your_sso_token");

      User user = new User(request);
      user.searchPlayer("iZy", Platform.PLAYSTATION);
   }
}
```

---

## Working with events

<p>

You can hook into request lifecycle events using the `Listener` system. Extend the `Listener` class and annotate your methods with `@EventHandler`.

</p>

You can also define the execution priority using the `priority` attribute (`LOW`, `NORMAL`, `HIGH`).

### Example Listener

```java
public class MyListener extends Listener {
   @EventHandler(priority = ListenerPriority.NORMAL)
   public void onPreRequest(PreRequestEvent event) {
      System.out.println(event.getEventName() + " : Event triggered!");
   }
}
```

### Usage

```java
public class Main {
   public static void main(String[] args) {
      RequestManager request = new RequestManager(new MyListener());
      request.authenticate("your_sso_token");
      // Continue with other operations...
   }
}
```

---

## Installation

### Gradle

```groovy
repositories {
   mavenCentral()
}
dependencies {
   implementation('io.github.izycorp:codapi:1.0.4')
}
```

### Maven

```xml
<dependency>
   <groupId>io.github.izycorp</groupId>
   <artifactId>codapi</artifactId>
   <version>1.0.4</version>
</dependency>
```
