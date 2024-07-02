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
public class MysqlTestContainersEnvironment {

  @Container
  public static final DockerComposeContainer<?> environmentWithMysql = upContainersWithMysql();

  private static DockerComposeContainer<?> upContainersWithMysql() {
    File file = new File("./docker-compose-mysql.yml");

    assert file.exists();

    try (DockerComposeContainer<?> environment = new DockerComposeContainer<>(file)) {
      environment
          .waitingFor("mysql_1",
              Wait.forLogMessage(".*ready for connections.*", 1)
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
      environmentWithMysql.start();
  }

}
