---
title: Test Environment
tags: [configuration]
---
After adjusting the `environment.properties` it is now time to consider the use of `testEnvironment.json`.

The format of this file follows the current structure:

```json
{
"local-environment": {
    "Service_1": "http://localhost:63001",
    "Service_2": "http://localhost:63002"
  }
}
```

Where key (for example: local-environment) is the reference to the environment where the variables `Service_1` and `Service_2` will be used.

In `/build/environment/environment.properties` set the key `TestEnvironment` to correspond to the environment against
 which you want to run the tests. In this case, we would have:

`TestEnvironment=local-environment`

When initialising the test runner,  the value of the key corresponding to the parameter 'TestEnvironment'
will be converted into stored data. So, in the above case, two data stores will be created:

* `Service_1` containing value "http://localhost:63001"
* `Service_2` containing value "http://localhost:63002"

This means that you can then use these stored data within the Feature steps such as:

`Given I open website "{Service_1}"` or `Then url contains string "{Service_2}"`
