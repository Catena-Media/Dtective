---
title: Allure report
subtitle: Let's setup some beautiful reports for your automated tests, shall we?
tags: [features, featured]
author: evan
---

{% include image.html img="allure.png" style="wide" lightbox="true" alt="Alt for image" %}

After a test run which has been executed with Maven, either by clicking on
 `Maven > site` the right panel on the Lifecycle shown below

{% include image.html img="site-allure.png" style="wide" lightbox="true" alt="Alt for image" %}

Or if using another editor by running the command `mvn site` on the command line
on the root of the project, after a test run.

The results will be stored in `index.html` ready to be open on the browser of your choice.

```java
Dtective-boilerplate
├─┬ src
├─┬ target
│ └──── site
│ └──────── allure-maven-plugin
│ └─────────── index.html
│ │
└── pom.xml
```
Hello World!
