# Objectville Route Finder

Objectville Route Finder is a Kotlin project for solving chapter 10 problem of book [Head First Object-Oriented Analysis and Design](https://www.oreilly.com/library/view/head-first-object-oriented/0596008678/).

# Description

Basically, the system to develop must be able to load a file with a set of stations, lines and connections of a subway and calculate a route to go between stations. Also, the system has to let travel agents to print out a route indicating which lines to take and if the travellers have to change to another station in a connection line.

# Feature list

This is the list of features that the solution cover:

- Should be able to store complete network of subways lines and stations along each lines.
- Should be able to figure out the route between a starting and destination station.
- Should be able to print out the route indicating which lines to take, which stations are passed on a line and when the travelers have to get off a line and get on another.

# Use cases

The actors and the use cases are the following:

![uml use cases diagram](usecases_diagram.png)

# Requirements

The necessary steps to implement the use cases are:

## Load Network of subway

1. Load the file with stations and lines.
2. Read every station and add to the subway.
3. Read every line and add connections between stations

Validate each operation before adding to the subway is needed to ensure that the system is working properly.

# Design

![uml class diagram](class_diagram.png)

# Test cases

To run the test cases:

`$ ./gradlew test`

## Usage

To run the application only execute this command with gradle:

`$ ./gradlew run`