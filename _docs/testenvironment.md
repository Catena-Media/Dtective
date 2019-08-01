---
title: Test Environment
subtitle: How to use testEnvironment.json
tags: [features]
author: evan
---

The format of this file follows the current structure:

```json
{
"local-environment": {
    "Service_1": "http://localhost:63001",
    "Service_2": "http://localhost:63002"
  }
}
```

Where key (for example: local-environment) is the reference to the environment
And the value should be a JSON object containing one or more key value pairs with its key being the reference
to a site/microservice and its value  the URL itself to access.

In /build/environment/environment.properties set the key 'TestEnvironment' to correspond to the environment against
 which you want to run the tests (example: local-environment) such as:

`TestEnvironment=local-environment`

When initialising the test runner,  the value of the key corresponding to the parameter 'TestEnvironment'
will be converted into stored data. So, in the above case, two data stores will be created:

* "Service_1" containing value "http://localhost:63001"
* "Service_2" containing value "http://localhost:63002"

This means that you can then use these stored data within the Feature steps such as:

`Given I open website "{Service_1}"`
