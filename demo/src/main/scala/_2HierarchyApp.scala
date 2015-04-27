import akka.actor._


object _2HierarchyApp extends App {

  class Worker extends Actor {
    def receive = {
      case func: Function0[Any] =>  val result = func()
                                    sender ! result
                                    context.self ! PoisonPill
    }
  }

  class Coordinator extends Actor {
    override val supervisorStrategy = SupervisorStrategy.stoppingStrategy

    def receive = {
      case func: Function0[Any] => context.actorOf(Props[Worker]) ! func

      case result => println(s"Boss got result: $result")
    }
  }

  val system = ActorSystem()

  val boss: ActorRef = system.actorOf(Props[Coordinator], "BOSS")


  val trivial = () => "x"
  boss ! trivial

  val bad = () => {throw new RuntimeException("HAHAHAH!")}
  boss ! bad

  val complicated = () => {2+2}
  boss ! complicated

  Thread.sleep(1000)

  system.shutdown()
}
