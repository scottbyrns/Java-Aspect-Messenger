# Java-Aspect-Messenger

Send aspect messages to disparate class methods.

## Project information

```xml
<repositories>
    <repository>
        <id>scottbyrns-snapshots</id>
        <url>https://github.com/scottbyrns/Scottbyrns-Maven-Repo/raw/master/snapshots</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.scottbyrns</groupId>
    <artifactId>Java-Aspect-Messenger</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

* Source: https://github.com/scottbyrns/Java-Aspect-Messenger

## Usage

### Registration Through Annotation

Many classes can register callbacks for the same key.

```java
    @RegisterAsCallback(
        group = "user-did-update-profile"
    )
    public void refreshProfile () {
        // Handler body
    }
    .
    .
    .
    {
        MessageController.registerListenersOfClass(
            getClass(),
            this
        );
        MessageController.sendMessage(
            "user-did-update-profile",
            userProfileEntity
        );
    }    
```

### Channels

A channel is a way of routing messages to a sub grouping of handlers.
```java
    @RegisterAsCallback(
        group = "user-did-update-profile",
        channel = "updated-user-name"
    )
    public void refreshProfile () {
        // Handler body
    }
    .
    .
    .
    {
        MessageController.registerListenersOfClass(getClass(), this);
        MessageController.sendMessage(
            "user-did-update-profile",
            Message.create(
                "updated-user-name",
                userProfileEntity
            )
        );
    }
```
