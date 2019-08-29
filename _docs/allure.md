---
title: Allure report
subtitle: Not mandatory. Yet, a beautiful report.
tags: [configuration]
---

Allure is not mandatory. Yet, we know it is important to have straightforward results. We have integrated Dtective with
Allure to make beautiful reports for your automation test suite.

{% include image.html img="allure.png" style="wide" lightbox="true" alt="Alt for image" %}

Allure can be easily set up with Maven. After a test run, on the right side panel of IntelliJ, expand the Maven tab.
Under Lifecycle press `site`. This generates a site with a report of the latest test run. The site file is found in
`target > site > allure-maven-plugin > index.html` right click on `index.html` to open the file with the browser of your preference.


If using another editor, it is necessary to have Maven [installed](https://catena-media.github.io/Dtective/docs/installation/) and accessible from the command line.
 On the root of the project, run the command `mvn site`. Results are stored in the directory specified above.
