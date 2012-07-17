# Java-Aspect-Messenger

Send aspect messages to disparate class methods.

## Project information

* Source: https://github.com/scottbyrns/Java-Aspect-Messenger

## Usage

### Registration Through Annotation

Many classes can register callbacks for the same key.

```java
    @RegisterAsCallback("user-did-update-profile")
    public void refreshProfile () {
        // Handler body
    }
    .
    .
    .
    {
        MessageController messageController = new MessageController();
        messageController.registerListenersOfClass(getClass(), this);
        
        messageController.sendMessage("user-did-update-profile");
    }    
```
