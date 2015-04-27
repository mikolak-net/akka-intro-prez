import akka.actor.{Props, Actor, ActorRef, ActorSystem}


object _1App extends App {

  class HaiActor extends Actor {

    def receive: Receive = {
      case target: ActorRef => println(s"$self: sending Hello! to $target")
                               target ! "Hello!"
      case "Hello!" => println(s"$self: got Hello! from $sender")
                       sender ! "OHAI"
      case "OHAI" => println(s"$self: got OHAI from $sender")
    }

  }

  val system = ActorSystem()

  val actor1: ActorRef = system.actorOf(Props[HaiActor], "ACTOR1")

  val actor2: ActorRef = system.actorOf(Props[HaiActor], "ACTOR2")

  actor1 ! actor2

  Thread.sleep(100)

  system.shutdown()

}
