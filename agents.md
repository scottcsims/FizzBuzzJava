AGENTS
======

Purpose
-------
Provide concise, actionable instructions for automated agents (CI, bots, local helpers, or AI coding agents) so they can be immediately productive in this repo.

Quick orientation (what an agent should read first)
--------------------------------------------------
- README.md — describes project intent, tech stack (Java 17, Maven) and run/test commands.
- pom.xml — build, dependencies (JUnit, Mockito, JaCoCo) and test reporting configured.
- src/main/java/dev/ssims/fizzbuzz/Main.java — main app and the helper method Main.multipleOf(int,int).
- src/test/java/dev/ssims/fizzbuzz/MainTest.java — unit tests and expected behaviors.

Essential commands (copy/paste)
-------------------------------
# Compile
mvn clean compile

# Run unit tests (JUnit 5)
mvn test

# Run the application (prints 2..100000—avoid on CI)
mvn exec:java -Dexec.mainClass="dev.ssims.fizzbuzz.Main"
# Or run compiled class directly (after mvn compile)
java -cp target/classes dev.ssims.fizzbuzz.Main

# View coverage report (after mvn test)
open target/site/jacoco/index.html

Project-specific notes & conventions
-----------------------------------
- Java 17 is required (pom.xml sets compiler.release 17).
- Tests live under src/test/java and follow *Test.java naming (maven-surefire includes **/*Test.java).
- The main() runs a loop from 2 to 100,000 and prints output — avoid running it without limiting output during debugging or CI.
- multipleOf(int multiple, int check) is the core helper used by tests; prefer changing tests first when changing its behavior.

- Repository note: the agent guidance file is `agents.md` (lowercase) at the project root. There are no special copilot/agent rule files (for example, `.github/copilot-instructions.md`) present in this repository.

Concrete tasks an AI coding agent can perform (with examples)
-----------------------------------------------------------
1) Run tests and report failures
   - Command: mvn test
   - Exit with status 0 on success; include failing test names and stack traces in the report.

2) Add a CI workflow (recommended)
   - Path: .github/workflows/ci.yml
   - Minimal actions: checkout, set up Java 17, mvn -B -DskipTests=false test, upload JaCoCo report as artifact.
   - Example step: run: mvn -B -DskipTests=false test

    NOTE: this repository does not currently include a `.github/workflows/ci.yml` file. Add the workflow below as a minimal, safe CI that runs tests and publishes the JaCoCo report. Do NOT run `Main.main` in CI (it prints 100k lines).

    Example minimal workflow (copy to .github/workflows/ci.yml):

    ```yaml
    name: CI

    on:
      push:
        branches: [ main, master ]
      pull_request:
        branches: [ main, master ]

    jobs:
      build:
        runs-on: ubuntu-latest
        steps:
          - name: Checkout
            uses: actions/checkout@v4

          - name: Set up JDK 17
            uses: actions/setup-java@v4
            with:
              distribution: 'temurin'
              java-version: '17'
              cache: 'maven'

          - name: Run tests
            run: mvn -B -DskipTests=false test

          - name: Upload JaCoCo report
            uses: actions/upload-artifact@v4
            with:
              name: jacoco-report
              path: target/site/jacoco
    ```

3) Make small, focused code changes
   - Example: Add an optional CLI upper bound instead of hard-coded 100000 in Main.main.
     - Edit Main.main to accept an integer arg or use System.getProperty("fizzbuzz.max").
     - Update or add a unit test that verifies behavior for a small range.
   - Always run mvn test after the change and include the failing tests if any.

4) Improve test coverage or add tests
   - Add parameterized tests around Main.multipleOf for edge cases (0, negative numbers).
   - Command: mvn test

5) Generate and attach artifacts
   - Build jar: mvn -DskipTests package
   - Publish or attach the built jar as a CI artifact when requested.

Safety and operational guidance for agents
-----------------------------------------
- Do not run Main.main in CI without reducing output; it prints 100k lines which clutters logs and may time out runners.
- Prefer unit tests (mvn test) for verification — they are fast and deterministic.
- When adding dependencies, update pom.xml and run mvn -DskipTests=false test to validate.
- If changing public API/behavior (e.g., multipleOf semantics), add/adjust unit tests and document breaking changes in PR description.

Entry template (when adding agents here)
----------------------------------------
- Name: CI (example)
- Type: GitHub Action
- Location: .github/workflows/ci.yml
- Description: Runs mvn test and publishes JaCoCo HTML report to artifacts on successful runs.
- Run notes: Runs on push and pull_request; uses Java 17; do not execute Main.main directly.
- Maintainer: @github-username

If anything specific should be included (maintainers, deployed bots, or third-party integrations), reply with details and the file will be updated.