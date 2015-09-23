import com.twitter.app._
import com.twitter.finagle.Memcached
import io.circe._, io.circe.generic.auto._, io.circe.jawn._, io.circe.syntax._

object Main extends App {
  val flags = Flags("Test flags")

  implicit val sessionStoreFlaggable = new Flaggable[SessionStore] {
    def parse(s: String): SessionStore =
      SessionStores.MemcachedStore(Memcached.newRichClient(s)) // "localhost:11211,localhost:11212"
  }

  val sessionStore = flags[SessionStore]("sessionStore", SessionStores.InMemoryStore, "hosts")// Flaggable[SessionStore]
  val secretStore = flags[SecretStoreApi]("secretStore", SecretStores.InMemoryStore, "hosts")
  val serviceIds = flags[Set[ServiceIdentifier]]("serviceIds", Set.empty, "ids") // Flaggable[Set[ServiceIdentifier]]

  /*

  UberObject(???, ???, ???) // oo
  ??? | ??? | ??? | Object(_, _, _)

  case case ServerConfig(sessionStore: SessionStore,
                         secretStore: SecretStoreApi,
                         serviceIds: Set[ServiceIdentifier])

  import scala.util.{Try, Failure, Success}
  trait Try[+A] {
  }
  case class Failure(e: Throwable) extends Try[Throwable]
  case class Success[+A](a: A) extends Try[A]

  def unsafeParse(i: Int): String =
    if (i % 2 == 0) i.toString
    else throw new Exception("your input was odd")

  Try { unsafeParse(2) } // Success("2") -> Try[String]
  Try { unsafeParse(3) } // Failure(Exception("your input was odd"))

  val optionConfig = for {
    ses <- Try { sessionStore.getWithDefaults } // Try[Option[SessionStore]] -- Try[A] -- Xor[E, A]
    sec <- Try { secretStore.getWithDefault } // Try[Option[String]]
    sids <- Try { serviceIds.getWithDefault } // Try[Option[String]]
  } yield ServerConfig(ses, sec, sids) // Try[Option[ServerConfig]]

  for {
    a <- Some(1)
    b <- Some(2)
    c <- Some(3)
  } yield a + b + c

  Some(1).flatMap(a => Some(2).flatMap(b => Some(3).map(c => a + b +c )))

  optionConfig match {
    case Failure(e) => exit(1, e.getMessage)
    case Success(o) => o getOrElse exit(666, "this doesn't get called")
  }

  optionConfig getOrElse exit(1)
  optionConfig match {
    case Some(config) => ???
    case None => exit
  }

  trait Option[+A]
  case class Some[+A](a: A) extends Option[A] {
    def map[B](f: A => B): Option[B] = Some(f(a))
    def flatMap[B](f: A => Option[B]): Option[B] = f(a)
  }
  case object None extends Option[Nothing] {
    def map[B](f: A => B): Option[B] = None
    def flatMap[B](f: A => Option[B]): Option[B] = None
  }
  */
}
