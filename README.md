# Commons

The **Commons** module is a shared library containing utilities, exceptions, and helper functions used across multiple microservices in the QuantumGrid platform.

## Features

- Common utility classes (e.g., date and time utilities, string manipulation)
- Custom exceptions and error handling utilities
- Helper functions and classes

## Technology Stack

- **Java**: Programming language
- **Maven**: Build tool for packaging the commons library

## Getting Started

### Prerequisites

- **Java 17** or higher
- **Maven** for build automation

### Setup

1. Clone the repository:
    ```bash
    git clone https://github.com/bobnetnetwork/quantumgrid-commons.git
    cd quantumgrid-commons
    ```

2. Build the library:
    ```bash
    mvn clean install
    ```

3. Add the Commons library as a dependency in other microservice projects:

**Maven Dependency Example:**

```xml
<dependency>
    <groupId>com.bobnet.network</groupId>
    <artifactId>quantumgrid-commons</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Using Commons

- Import and use the utility classes or custom exceptions in your microservice codebase.

## Contributing

Please read the [CONTRIBUTING.md](https://github.com/bobnetnetwork/quantumgrid/blob/main/CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests.

## License

This project is licensed under the GPL-3.0 license - see the [LICENSE.md](https://github.com/bobnetnetwork/quantumgrid/blob/main/LICENSE.md) file for details.
