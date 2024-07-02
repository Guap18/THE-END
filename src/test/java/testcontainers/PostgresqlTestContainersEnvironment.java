package testcontainers;

import lombok.Getter;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.time.Duration;

@Getter
@Testcontainers
public class PostgresqlTestContainersEnvironment {

  @Container
  public static final DockerComposeContainer<?> environmentWithPostgresql = upContainersWithPostgresql();

  private static DockerComposeContainer<?> upContainersWithPostgresql() {
    File file = new File("./docker-compose-postgres.yml");

    assert file.exists();

    try (DockerComposeContainer<?> environment = new DockerComposeContainer<>(file)) {
      environment
          .waitingFor("postgres_1",
              Wait.forLogMessage(".*database system is ready to accept connections.*", 1)
                  .withStartupTimeout(Duration.ofSeconds(60)))
          .waitingFor("app_1",
              Wait.forLogMessage(".*Started ShopApplication.*", 1)
                  .withStartupTimeout(Duration.ofSeconds(60)))
          .waitingFor("nodejs_1",
              Wait.forLogMessage(".*DECLINED.*", 1)
                  .withStartupTimeout(Duration.ofSeconds(60)))
          .withLocalCompose(true)
          .withBuild(true)
          .withPull(false);

      return environment;
    }
  }

  public static void startContainers() {
      environmentWithPostgresql.start();
  }

}
