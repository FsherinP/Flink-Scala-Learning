package Functions

import com.typesafe.config.{Config, ConfigFactory}

class AppConfiguration extends Serializable {
  val configuration: Config = ConfigFactory.load()
}

object AppConfiguration {
  def config(): Config = {
    val appConfig = new AppConfiguration
    appConfig.configuration
  }
}
