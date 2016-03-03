import org.apache.flink.api.common.functions.FoldFunction
import org.apache.flink.api.scala._
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.windowing.assigners.GlobalWindows
import org.apache.flink.streaming.api.windowing.triggers.CountTrigger

import scala.util.Try

object TestApp {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val stream = env.fromElements(1, 2, 3, 4)
        .map(i => (i, i+1))

    stream
      .windowAll(GlobalWindows.create)
      .trigger(CountTrigger.of(2))
      .fold(List[Try[Wrapper]](), new FoldFunction[Tuple2[Int, Int], List[Try[Wrapper]]] {
        override def fold(accumulator: List[Try[Wrapper]], value: (Int, Int)): List[Try[Wrapper]] = ???
      })
  }

  case class Wrapper private(i: Int)
  object Wrapper {
    def create(i: Int) = Wrapper(i)
  }
}

/*
Compiling yields:
[error] TestApp.scala:18: could not find implicit value for evidence parameter of type org.apache.flink.api.common.typeinfo.TypeInformation[List[scala.util.Try[TestApp.Wrapper]]]
[error]       .fold(List[Try[Wrapper]](), new FoldFunction[Tuple2[Int, Int], List[Try[Wrapper]]] {
[error]            ^
[error] one error found
 */
