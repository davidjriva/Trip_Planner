# Trip Planning Application

## Pre-Optimization
![Image Description](https://github.com/davidjriva/Trip_Planner/raw/main/README_Images/Screenshot%202023-12-14%20at%2012.53.51%20PM.png)\

## Post-Optimization
![abc](https://github.com/davidjriva/Trip_Planner/blob/main/README_Images/Screenshot%202023-12-14%20at%2012.54.06%20PM.png)

If you have not yet completed the instructions in [LOCAL-SETUP.md](./LOCAL-SETUP.md), start with them. They will help you download the tools required
to run the code as described in this file.

Here we describe how to run the code, specifically using the [bin/run.sh script](./bin/run.sh).

## Run Configurations

We have two configurations for our code. Here is a brief description of each and when you should use them.

#### Using the Dev Environment
To run the server in development mode, invoke the run script as is:

```bash
./bin/run.sh
```

This starts two processes:
* the client code running via `npm run devClient` listening on port 431XX, with XX being your team's number
* the server code running via `npm run server` listening on port 413XX, with XX being your team's number 

#### Prod Environment Description

While the dev environment is useful for developers, there are a lot of differences between
the dev and prod environments because user demand is different from dev demand.

#### Using the Prod Environment
The easiest way to run the server and make sure everything works is to use the
run script with the flag to tell which environment to use:

```bash
./bin/run.sh -e prod
```

This will install all npm dependencies (if they haven't been already), bundle
together all of the Javascript source, compile and test your React and Java Code, package
everything into a single JAR, and start running the server on the default port. Visit `http://localhost:413XX` 
to see the web page.

### Deployment

Ultimately, you will deploy your production server to a different machine. To
package everything into a single executable jar file to be submitted through
checkin, use the run.sh script with the `-d` flag set:

```bash
./bin/run.sh -d
```

This will create a directory called `target` if it does not already exist and
write the jar file to this directory. This jar can then be copied and ran on 
any other machine with java.

