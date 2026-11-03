# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Overview

Classic FizzBuzz demo project (Java 17, Maven) used to explore IDE setup, Maven builds, JUnit 5 testing, and JaCoCo coverage. See `agents.md` for additional agent guidance.

## Commands

```bash
mvn clean compile                                   # compile
mvn test                                            # run all tests (also generates JaCoCo report)
mvn test -Dtest=MainTest                            # run a single test class
mvn test -Dtest=MainTest#testMultipleOfThree        # run a single test method
mvn -DskipTests package                             # build jar
java -cp target/classes dev.ssims.fizzbuzz.Main     # run app (after compile)
```

Coverage report: `target/site/jacoco/index.html` (JaCoCo `report` goal is bound to the `test` phase).

There is no linter or formatter configured.

## Architecture

All logic lives in `src/main/java/dev/ssims/fizzbuzz/Main.java`:
- `main()` loops over 2..100,000 (hard-coded) and prints `"<n> FizzBuzz"`, `"<n> Fizz"`, `"<n> Buzz"`, or just `<n>`. Note the output includes the number as a prefix, unlike textbook FizzBuzz.
- `multipleOf(int multiple, int check)` is the only unit-tested helper (`check % multiple == 0`); `main()` itself has no test coverage. Passing `multiple == 0` throws `ArithmeticException`.

Surefire picks up `**/*Test.java` and `**/*Tests.java`. Mockito is declared as a test dependency but not currently used.

## Gotchas

- **Do not run `Main.main` in CI or casually during debugging** — it prints ~100k lines. Prefer `mvn test` for verification.
- When changing `multipleOf` semantics, update `MainTest` first and call out the behavior change in the PR description.
- CI (`.github/workflows/ci.yml`) runs `mvn -B verify` on Java 17 (Temurin) for pushes/PRs to `main`/`master`, then runs SonarCloud analysis (skipped when the `SONAR_TOKEN` secret is absent, e.g. fork PRs) and uploads `target/site/jacoco` as an artifact. Sonar settings live as `sonar.*` properties in `pom.xml`.
- Dependabot (`.github/dependabot.yml`) opens weekly PRs for Maven deps and GitHub Actions.
