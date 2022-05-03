# wolt
##Wolt home test assignment
This application was written for Wolt interview with using SpringBoot, Gradle, Kotlin, Spring, Junit.
Detekt is a static code analysis tool for the Kotlin programming language.

How to start:
Build and run with command ```./gradlew bootRun``` from project directory or from IDE. No additional arguments are needed
<details><summary>Curl</summary>
<p>

```curl
curl --location --request POST 'localhost:8080/opening-hours' \
--header 'Content-Type: application/json' \
--data-raw '{
"monday" : [],
"tuesday" : [
{
"type" : "open",
"value" : -1 },
{
"type" : "close",
"value" : 64800 }
],
"wednesday" : [], "thursday" : [
{
"type" : "open",
"value" : 37800 },
{
"type" : "close",
"value" : 64800 }
],
"friday" : [
{
"type" : "open",
"value" : 36000 }
],
"saturday" : [
{
"type" : "close",
"value" : 3600 },
{
"type" : "open",
"value" : 36000 }
],
"sunday" : [
{
"type" : "close",
"value" : 3600 },
{
"type" : "open",
"value" : 43200 },
{
"type" : "close",
"value" : 75600 }
] }'
```

</p>
</details>

###Notes:
Some issue what I have to faced during implementation:
1. it is not clear who is going to be consumer of this data to provide relevant format.
2. no data about holidays format - days with unique schedule of working hours
3. normalizing could be done in one travers however in order to avoid dependency from request format it was decided to map int inner model at first

##Part 2

For this data would be better to use zulu format of time for avoiding time zone dependency and use f.e. Instant for time. Also I would like to use that format in response however I have decided to follow tha task.
It is better to operate by objects or at least store all in map instead of using field naming.

As for JSON format it is better to use pair for open-close data like:
```
"hours": {
    "monday": {
        "open": "time",
        "close": "time"
    }
}
```