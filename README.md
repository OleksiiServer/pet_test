# Project Title
The repository was added as part of the Test Automation Mentoring Program Advanced 2021
###The Test Runners module(Junit5)

## Getting Started
If you have IntelliJ IDEA:
 - File –> New –> Project from Version Control;
 - Set URL - https://git.epam.com/oleksii_dorofieiev/ta_mentoring_adv.git;

##SauceLabs account data:
- url: https://app.eu-central-1.saucelabs.com/dashboard/tests/vdc
- login: gena1245
- password: Qwerty1234!

##JsonFake server:
- url https://my-json-server.typicode.com/OleksiiServer/Web_Services

# Installing
- Run HttpClientTests api tests
- Run HomePageValidationTests for UI test
- Run HttpClientTests for getting notification to Slack that some test were failed.

# Set numbers of Thread for running test 
- open src/test/resources/junit-platform.properties file 
- change number for junit.jupiter.execution.parallel.config.fixed.parallelism property

# Results
As result, you will see result in the console about test condition.
To create report run: mvn surefire-report:report
Then open folder ./target/site - and you well find surefire-report.html file