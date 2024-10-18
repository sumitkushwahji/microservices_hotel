# microservices_hotel

1. Introduction to Hasura
   Hasura is an open-source engine that auto-generates a real-time GraphQL API for your PostgreSQL database. It allows you to:

Instantly get a powerful GraphQL API without writing any code.
Manage database schema and business logic through migrations.
Implement authentication and authorization rules.
Extend the GraphQL API with custom business logic. 2. Understanding Migrations in Hasura
What Are Migrations?
Migrations in Hasura are a way to:

Track Database Schema Changes: Keep a version-controlled record of changes to your database schema (tables, columns, indexes, etc.).
Track Hasura Metadata: Record changes to Hasura-specific configurations like permissions, relationships, event triggers, and remote schemas.
Facilitate Collaboration: Allow teams to collaborate and manage changes across different environments (development, staging, production).
Automate Deployments: Automate the application of schema and metadata changes during deployments.
Why Use Migrations?
Consistency: Ensure that all environments (local, staging, production) have the same database schema and Hasura metadata.
Version Control: Use Git (or other VCS) to track changes, enabling rollback and auditing.
Continuous Integration: Integrate with CI/CD pipelines to automate deployments. 3. Setting Up Hasura with Docker
Let's extend your existing Docker setup to include Hasura.

3.1. Update docker-compose.yml
Modify your docker-compose.yml file to add a service for Hasura:

yaml
Copy code
version: '3'
services:
postgres:
image: postgres:latest
container_name: postgres
environment:
POSTGRES_USER: admin
POSTGRES_PASSWORD: admin
POSTGRES_DB: mydb
ports: - "5432:5432"
volumes: - postgres_data:/var/lib/postgresql/data
networks: - mynetwork

pgadmin:
image: dpage/pgadmin4:latest
container_name: pgadmin
environment:
PGADMIN_DEFAULT_EMAIL: admin@admin.com
PGADMIN_DEFAULT_PASSWORD: admin
ports: - "8080:80"
depends_on: - postgres
networks: - mynetwork

hasura:
image: hasura/graphql-engine:latest
container_name: hasura
ports: - "8085:8085"
depends_on: - postgres
restart: always
environment:
HASURA_GRAPHQL_DATABASE_URL: postgres://admin:admin@postgres:5432/mydb
HASURA_GRAPHQL_ENABLE_CONSOLE: "true"
HASURA_GRAPHQL_DEV_MODE: "true"
networks: - mynetwork

volumes:
postgres_data:

networks:
mynetwork:
3.2. Explanation of Hasura Service Configuration
Image: Uses the latest Hasura GraphQL Engine Docker image.
Ports: Maps container port 8085 to host port 8085.
Depends On: Ensures Hasura starts after PostgreSQL.
Environment Variables:
HASURA_GRAPHQL_DATABASE_URL: Connection string to the PostgreSQL database.
HASURA_GRAPHQL_ENABLE_CONSOLE: Enables the Hasura web console (set to "true").
HASURA_GRAPHQL_DEV_MODE: Enables development mode features (e.g., console, admin access without admin secret). 4. Running the Updated Docker Setup
Start all services:

docker-compose up -d
Check that all containers are running:

docker-compose ps 5. Accessing the Hasura Console
Open your browser and navigate to http://localhost:8085 to access the Hasura Console.

6. Creating Migrations with Hasura CLI
   To manage migrations, you need to use the Hasura CLI. Here's how to set it up and use it.

6.1. Install Hasura CLI
You can install the Hasura CLI globally or use it via Docker.

Option 1: Install Globally

macOS:

curl -L https://github.com/hasura/graphql-engine/raw/stable/cli/get.sh | bash
Windows:

Download the executable from Hasura CLI Releases and add it to your PATH.

Option 2: Use via NPM

npm install --global hasura-cli
Option 3: Use via Docker

alias hasura="docker run --rm -v $(pwd):/hasura -w /hasura hasura/graphql-engine:latest cli"
6.2. Initialize a Hasura Project
In your project directory, initialize a Hasura project:

hasura init hasura-project
This creates a directory hasura-project with the following structure:

migrations/: Contains migration files.
config.yaml: Hasura project configuration.
6.3. Configure config.yaml
Update config.yaml with the correct endpoint:

yaml
Copy code
endpoint: http://localhost:8085
admin_secret: ''
If you have set an admin secret in your Hasura configuration, include it here.

6.4. Create Migrations
6.4.1. Track Tables
If you have existing tables in your database, you can track them:

hasura migrate create "init" --from-server --schema "public"
This command creates an initial migration by introspecting the current database schema.

6.4.2. Apply Migrations
To apply migrations to another environment:

hasura migrate apply
6.4.3. Creating New Migrations
When you make changes through the Hasura Console (e.g., adding tables, relationships), you can capture those changes as migrations.

Start the Console with Migrations Enabled:

hasura console
This ensures that any changes you make in the console are recorded as migration files in the migrations/ directory.

6.4.4. Managing Metadata
Hasura metadata includes configurations like permissions, remote schemas, and event triggers.

Export Metadata:

hasura metadata export
Apply Metadata:

hasura metadata apply 7. Integrating Hasura with Spring Boot
While Hasura provides a GraphQL API, you might want to integrate it with your existing Spring Boot application.

7.1. Possible Integration Approaches
Use Hasura as the API Layer: Let Hasura handle the GraphQL API, and your Spring Boot application can focus on other services.
Consume Hasura's GraphQL API from Spring Boot: If you need to fetch data in your Spring Boot app, you can use a GraphQL client to query Hasura.
Event Triggers and Actions: Use Hasura's event triggers to call webhooks in your Spring Boot app for custom business logic.
7.2. Example: Consuming Hasura's API in Spring Boot
Add a GraphQL client library to your Spring Boot application, such as graphql-java.

8. Creating a Hasura Dockerfile (Optional)
   If you need to customize the Hasura Docker image, you can create your own Dockerfile.

8.1. Sample Hasura Dockerfile
dockerfile
Copy code
FROM hasura/graphql-engine:latest

# Set environment variables

ENV HASURA_GRAPHQL_DATABASE_URL=postgres://admin:admin@postgres:5432/mydb
ENV HASURA_GRAPHQL_ENABLE_CONSOLE=true
ENV HASURA_GRAPHQL_DEV_MODE=true

# Copy migrations and metadata (if any)

COPY migrations /hasura-migrations
COPY metadata /hasura-metadata

# Entry point (optional customization)

CMD ["graphql-engine", "serve"]
8.2. Build the Custom Hasura Image

docker build -t custom-hasura:latest .
8.3. Update docker-compose.yml
Change the image in the Hasura service to use your custom image:

yaml
Copy code
hasura:
image: custom-hasura:latest # ... rest of the configuration 9. Summary of Steps
Add Hasura Service to Docker Compose: Updated docker-compose.yml to include Hasura.
Run Docker Compose: Started all services using docker-compose up -d.
Access Hasura Console: Navigated to http://localhost:8085.
Install Hasura CLI: Installed Hasura CLI for managing migrations.
Initialize Hasura Project: Used hasura init to set up the project directory.
Configure Hasura Project: Updated config.yaml with the correct endpoint.
Create and Apply Migrations: Used hasura migrate and hasura metadata commands.
Optional Dockerfile for Hasura: Created a custom Dockerfile if needed. 10. Additional Tips
Version Control: Commit your migrations/ and metadata/ directories to your version control system.
Environment Variables: For production, secure your setup by setting HASURA_GRAPHQL_ADMIN_SECRET.
Automate Deployments: Integrate Hasura migrations into your CI/CD pipelines. 11. Resources
Hasura Documentation: https://hasura.io/docs/latest/graphql/core/index.html
Hasura CLI Installation: https://hasura.io/docs/latest/graphql/core/hasura-cli/install-hasura-cli.html
Dockerizing Hasura: https://hasura.io/docs/latest/graphql/core/deployment/deployment-guides/docker.html
Now, you have integrated Hasura into your Docker setup, understand what migrations are, how to create them, and how to manage your database schema and Hasura metadata effectively. This setup allows you to leverage Hasura's powerful features alongside your Spring Boot application.
