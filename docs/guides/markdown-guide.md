# Markdown Guide

## Guide Project Layout

- Guide md pages and indexes 
- Folder with pages 
- Links to other pages 
```
[Text To Appear](./relative/link/to/page#anchor)
```
- Embed an image
```
![Text if image not rendered](./relative/link/to/image)
```
- Alias for linking
```
[alias-name]: html/zio-pet-clinic-webpage.png
```
- Comments
```
<!-- Commented message between arrows -->
```

## Alias Example

```
[![Board Page Screenshot][board-page-screenshot]](./docs/assets/blogapp-board-screenshot.png)
[![People Page Screenshot][people-page-screenshot]](./docs/assets/blogapp-people-screenshot.png)

<!-- MARKDOWN LINKS & IMAGES -->
[board-page-scroueenshot]: docs/assets/simplex/blogapp-board-screenshot.png
[people-page-screenshot]: docs/assets/simplex/blogapp-people-screenshot.png

```


## Formatting a Table
- [Markdown Table Prettify](https://marketplace.visualstudio.com/items?itemName=darkriszty.markdown-table-prettify)
- Right Click Menu : Format Section ⌘K ⌘F

## Table of Contents Generator
- VS Code Extension [DocToc](https://github.com/thlorenz/doctoc)
```
<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [Golang Template Project](#golang-template-project)
  - [About the project](#about-the-project)
    - [API docs](#api-docs)
    - [Design](#design)
    - [Status](#status)
    - [See also](#see-also)
  - [Getting started](#getting-started)
    - [Layout](#layout)
  - [Notes](#notes)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->
```

## Export to PDF 
- [Markdown PDF VS Code Extension](https://marketplace.visualstudio.com/items?itemName=yzane.markdown-pdf)
- Right Click Menu > Export to PDF 

## Other Tools 
- [tree for MacOS](https://sourabhbajaj.com/mac-setup/iTerm/tree.html)

## Resources 
- https://www.markdownguide.org/basic-syntax/#reference-style-links 
- Marp for slide presentations - https://marp.app/