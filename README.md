
<div style="text-align: center">

<img src="MooN_shadow.png" alt="">

<h1> MooN API </h1>
An enhanced Wrapper of the official Call Of Duty API
</div>

## Introduction

___
<p style="text-align: justify">
Moon API is an abstract layer API used to retrieve and manage data from official Call of Duty API.
Please consider this as an unfinished project, as it is still in development.

Moon API isn't only a wrapper for the official API, it will also provide some useful tools to manage data. This will be
the official API for the Moon Project which is coming soon.
</p>

This project has been created in order to help [OtherGun](https://www.youtube.com/c/otherGun/) on his video on Modern Warfare 2019.

#### Ok, but what is the Moon Project ?

<p style="text-align: justify">
The Moon Project is a project that will provide a lot of tools to manage data from the official Call of Duty API.
A graphical desktop application in which you'll be able to use the API in one hand, and recover data in the other hand like codTracker does.
But it doesn't stop there, the Moon Project will allow 'blueprints' programming, which means that you'll be able to customize your request and apply tons of filters, conditions, calculations, etc... to your data.
</p>

## How to use ?
___

<p style="text-align: justify">
You have **two ways** to use this API, either by overriding the abstract class `Title` or by using the `Moon` class which override everything from `Title` and let you use whatever method you want.
</p>

#### Using abstract Title class layer

Without abstract route implementation:
```java 
public class Bo3 extends Title {
    /**
     * Initialize the Title Object with a RequestManager object
     *
     * @param request - A valid RequestManager Object
     */
    public Bo3(RequestManager request) {
        super(request);
    }

    // Handle methods from Title class
}
```

<p style="text-align: justify">
With abstract route implementation:
This may be useful if you want to know what kind of methods are available for a specific title.
</p>

```java
public class Bo3 extends Title implements PublicRoute, ProtectedRoute {
    /**
     * Initialize the Title Object with a RequestManager object
     *
     * @param request - A valid RequestManager Object
     */
    public Bo3(RequestManager request) {
        super(request);
    }

    // Handle methods from Title class inside PublicRoute and ProtectedRoute override methods

    @Override
    public JSONObject searchPlayer(String playerName, Platform platform) throws MoonViolationException {
        return super.searchPlayer(playerName, platform);
    }
    
    ...
}
```

#### Using global Moon class

:warning: **Consider instanciating RequestManager object only once, as it has a heavy impact on client response because of HTTP Client.**

```java
public class Main {

    public static void main(String[] args) {
        // Create a new RequestManager Object
        RequestManager request = new RequestManager();
        // Create a new Moon Object
        Moon moon = new Moon(request);
        // Handle your method using Moon Object methods...
    }
}
```
