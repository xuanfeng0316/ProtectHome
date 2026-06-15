# ProtectHome

A base protection plugin for Minecraft Java Edition 1.21.11 Purpur server.

## Features

- Blocks players outside the whitelist from entering a specified circular area
- Teleports intruders to a safe location
- Whitelist players can enter freely

## Configuration

Edit the constants in the src/main/java/com/xuanfeng/protecthome/ProtectHome.java:

- `WHITELIST`: List of whitelisted player names
- `CENTER_X`, `CENTER_Z`: Center coordinates of the restricted area
- `RADIUS`: Radius of the restricted area (in blocks)
  
``` java
    private static final Set<String> WHITELIST = new HashSet<>(Arrays.asList(
        "Steve",
        "Alex"
    ));
```

## Build

```bash
mvn package
