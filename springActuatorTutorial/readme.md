SpringBoot Actuator and Security Tutorial

Here are some of the most common endpoints Boot provides out of the box:
- http://localhost:9001/health – Shows application health information (a simple ‘status’ when accessed over an unauthenticated connection or full message details when authenticated); it’s not sensitive by default
- http://localhost:9001/info – Displays arbitrary application info; not sensitive by default
- http://localhost:9001/metrics – Shows ‘metrics’ information for the current application; it’s also sensitive by default
- http://localhost:9001/trace – Displays trace information (by default the last few HTTP requests)

Application, secured by basic auth (user):
- http://localhost:9000/hello-world