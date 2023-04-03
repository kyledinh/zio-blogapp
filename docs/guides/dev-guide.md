# Developer Guide

- [Debugger](#vs-code-debugger)

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