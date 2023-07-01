<div align="center">

<img src="MooN_shadow.png" alt="">

<h1 class="global-title">MooN API</h1>

An enhanced Wrapper of the official Call Of Duty API

</div>

## Introduction

<div style="text-align: justify">
<p>
Moon API is an abstract layer API used to retrieve and manage data from official Call of Duty API.
Please consider this as an unfinished project, as it is still under development.

Moon API isn't only a wrapper for the official API, it will also provide some useful tools to manage data. This will be
the official API for the Moon Project which is coming soon.

</p>

This project has been created in order to help [OtherGun](https://www.youtube.com/c/otherGun/) on his video on Modern Warfare 2019.

#### Ok, but what is the Moon Project ?

<p>

The Moon Project is a project that will provide a lot of tools to manage data from the official Call of Duty API.
A graphical desktop application in which you'll be able to use the API in one hand, and recover data in the other hand like codTracker does.
But it doesn't stop there, the Moon Project will allow 'blueprints' programming, which means that you'll be able to customize your request and apply tons of filters, conditions, calculations, etc... to your data.

</p>

## Differences between routes

<p>

Call of Duty API use 3 different routes to retrieve data. Public, Private and Protected.

- Private routes may only be accessed by the authenticated client as they contain data specific to the client's account. (Methods are accessible through main Moon class)
- Protected routes require an authenticated client but may supply data for any given player. (Methods are accessible through main Moon class)
- Public routes require no authenticated or initialization and can be interfaced without prior consideration.

## How to use ?

<p>

This library provides you classes for each Call of Duty game **that work with the API**. Each class contains methods compatible with the title.

</p>

###### Important note

<p>

Protected and Private routes require an authenticated client. This is why some methods require a `ssoToken` parameter.
SSO Token is a token linked to your Activision account that is unique **and must not be shared**. Since last year, Activision has
implemented a captcha system to their authentication page to prevent automated requests. Getting your SSO Token from there is no longer possible. 
Since there is no other proper way to get your SSO Token, you'll have to get it manually. You can follow the steps below to get your SSO Token:

</p>


- Go to [https://profile.callofduty.com/cod/login](https://profile.callofduty.com/cod/login)
- Authenticate using your credentials
- Right click and inspect the page, go to `Storage` -> `Cookies` and search for **ACT_SSO_COOKIE**



###### Fetch Title data

<p>

For instance, if you want to retrieve data from Black Ops 3, you'll have to instanciate a `BlackOps3` object and use its methods.
Each title class has a constructor that takes a `RequestManager` object as parameter. This object is used to make requests to the API
and contains a `HttpClient` object that can be configured. Also, you can attach a `Listener` object to the `RequestManager` if you want to use events.
</p>

You can follow the example below:


```java
public class Main {

    public static void main(String[] args) {
        // Create a new RequestManager Object
        RequestManager request = new RequestManager();
        // Create a new BlackOps3 Object
        BlackOps3 bo3 = new BlackOps3(request);
        // Handle your method using BlackOps3 Object methods...
        bo3.getLeaderboard(Platform.PLAYSTATION, TimeFrame.ALLTIME, Gamemode.CAREER, GameType.HARDCORE, 1);
    }
}
```

:warning: __Consider instanciating RequestManager object only once, as it has a heavy impact on client response since it is holding a HTTP Client.__

###### Fetch User data

```java
public class Main {

    public static void main(String[] args) {
        // Create a new RequestManager Object
        RequestManager request = new RequestManager();
        // Create a new User Object
        User user = new User(request);
        // Handle your method using User Object methods...
        user.searchPlayer("iZy", Platform.PLAYSTATION, ssoToken);
    }
}
```

## Working with events

<p>

Moon API allows you to interact between a request step using some event. The `RequestManager` Class has constructors that allow you to pass a `Listener` object instance as parameter.
All you have to do is to make a ListenerHandler class that extends `Listener` class and override the methods you want to use.

</p>

Consider that to be listened, you have to annotate your method with `@EventHandler` annotation.
EventHandler annotation has a `priority` parameter which is an enum of type `Priority` and has 3 values : `LOW`, `NORMAL` and `HIGH`. The default value is `NORMAL`. Use this if you have more than one Listener or event method.

###### Listener class:

```java
public class MyListener extends Listener {

    @EventHandler(priority = ListenerPriority.NORMAL)
    public void onPreRequestLowPriority(PreRequestEvent event) {
        System.out.println(event.getEventName() + " : This is an event!");
    }
}
```

###### Class where you instanciate RequestManager:

```java
public class Main {

    public static void main(String[] args) {
        // Create a new RequestManager Object
        RequestManager request = new RequestManager(new MyListener());
        // ...
    }
}
```

## Installation

### Gradle

```groovy
repositories {
    mavenCentral()
}
dependencies {
    implementation('io.github.izycorp:moonapi:1.0.3')
}
```

### Maven

```xml
<dependency>
  <groupId>io.github.izycorp</groupId>
  <artifactId>moonapi</artifactId>
  <version>1.0.3</version>
</dependency>
```

</div>



