---
title: Docker
subtitle:
tags: [features]
author: evan
---

The project includes docker-compose files which can be used for testing locally:

  `docker-compose.selenium.yml` builds two containers which run a Selenium Grid with Chrome Node and another Node with Firefox.

### Usage:

 `$ docker-compose -f docker-compose.<name>.yml build`

 `$ docker-compose -f docker-compose.<name>.yml up -d`

 after all the test executions

 `$ docker-compose -f docker-compose.<name>.yml down`

**Note:** Do not forget to adjust the `environment.properties` to run the tests with Docker containers.
