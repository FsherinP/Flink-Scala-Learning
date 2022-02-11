package myflink

import Functions.{AppConfiguration, BaseStreaming, KafkaMsg}
import org.apache.flink.streaming.api.scala._

object StreamingJob extends BaseStreaming {

  def main(args: Array[String]) {
    val config = AppConfiguration.config
    val jobName = "stream1"

    //Setting up the streaming environment
    implicit val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(config.getInt("flink.parallelism"))
    env.enableCheckpointing(config.getInt("flink.checkpointing.interval"))

    //Creating the kafka consumer and kafka producer with configurations
    val kafkaConsumer = createStreamConsumer(config.getString(jobName + ".input.topic"), config.getString(jobName + ".groupId"))
    val kafkaProducer = createStreamProducer(config.getString(jobName + ".output.success.topic"))

    //Attaching the kafka consumer as a source.
    val dataStream: DataStream[KafkaMsg] = env.addSource(kafkaConsumer).name("rawdata")

    // Case Handling logic
    val lowerCaseDs: DataStream[KafkaMsg] = dataStream.process(new CaseHandler).name("touppercase")

    //Attaching the kafka producer as a sink
    lowerCaseDs.addSink(kafkaProducer).name("output")

    env.execute(jobName)
  }
}
