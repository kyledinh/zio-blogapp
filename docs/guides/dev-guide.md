# Developer Guide

> This guide is written for local development on *nix platforms. 

Specifically done on MacOS 12.1 Monterey and will refer to software installed on a MacBook Pro 13 2020, with 2.3Ghz Quad Core i7 and 32 GB RAM. This scripts or software may behave differently on other *nix platforms or hardware. We will use Docker/Kubernetes to minimize differences in deployment.

### Topics 
- [Coding Practices](#coding-practices)
- [Debugger](#vs-code-debugger)

### Guides
- [Onboarding Guide](onboarding-guide.md) | [Onboarding Video]()
- [Docker Guide](docker-guide.md)
- [Laminar Guide](laminar-guide.md)
- [Postgres Guide](postgres-guide.md)
- [Scala 3 Guide](scala-guide.md)
- [Troubleshooting Guide](troubleshooting-guide.md)

<br><hr><br>

## Coding Practices

- Scala 3 
- `scalafmt` to reformat code
- Git proceedures
- PR reviews 


<br><hr><br>

## VS Code Debugger

- [Debugging Scala 3 in VS Code, YouTube](https://www.youtube.com/watch?v=zX-t5E5hQh0)
- [Metals for VS Code](https://scalameta.org/metals/docs/editors/vscode/)
- Logpoint to log console message
```
Some log message with ${someservice.getNote(id).title}
``` 
- Conditional Breakpoint
```
title.contains("Debug")
```
- launch.json in `.vscode/`
```json
{
    "version": "0.2.0",
    "configurations": [
        {
            "type": "scala",
            "request": "launch",
            "name": "BlogApp API",
            "mainClass": "blogapp.Main",
            "args": [],
            "jvmOptions": [],
            "env": {
                "PORT": "9000"
            }
        },
        {
            "type": "scala",
            "request": "launch",
            "name": "Test OwnerIdSpec",
            "testClass": "blogapp.services.OwnerIdSpec",
            "args": [],
            "jvmOptions": [],
            "env": {
                "PORT": "9000"
            }
        }
    ]
}
```

## Resources

- [SBT Docker Plugin](https://www.scala-sbt.org/sbt-native-packager/formats/docker.html)