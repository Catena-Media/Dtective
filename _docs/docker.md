---
title: Docker
subtitle:
tags: [setup]
---

For basic configuration that is it. You don't need Docker to start right now (and if you want it now now, we advise you to jump to the [cheatsheet](https://catena-media.github.io/Dtective/docs/cheatsheet/)).

The boilerplate includes docker-compose files which can be used for testing locally with Docker containers.

The `docker-compose.selenium.yml` builds two containers which run a Selenium Grid with Chrome Node and another Node with Firefox.

The `docker-compose.testwebsite.yml` builds the image stored in [Docker Hub](https://hub.docker.com/r/catenaqa/examplesite) of a test site we use to run our tests internally. This can be useful as a playground of automation experiments!

### Usage:

 `$ docker-compose -f docker-compose.<name>.yml build`

 `$ docker-compose -f docker-compose.<name>.yml up -d`

 after all the test executions

 `$ docker-compose -f docker-compose.<name>.yml down`

**Note:** Do not forget to adjust the `environment.properties` to run the tests with Docker containers.
Usually is necessary to set the `IsRemoteInstance` to `true` so it attempts to find the Selenium Hub.



We have applied a similar approach as it is described in this [blog post](https://techblog.dotdash.com/setting-up-a-selenium-grid-with-docker-containers-for-running-automation-tests-c43aceccd5d9).
