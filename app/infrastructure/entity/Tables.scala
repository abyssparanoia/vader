package infrastructure.entity
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.MySQLProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Posts.schema ++ Users.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Posts
   *  @param id Database column id SqlType(BIGINT UNSIGNED), AutoInc, PrimaryKey
   *  @param userId Database column user_id SqlType(VARCHAR), Length(50,true)
   *  @param title Database column title SqlType(VARCHAR), Length(255,true)
   *  @param description Database column description SqlType(VARCHAR), Length(255,true)
   *  @param text Database column text SqlType(VARCHAR), Length(10000,true)
   *  @param createdAt Database column created_at SqlType(BIGINT)
   *  @param updatedAt Database column updated_at SqlType(BIGINT) */
  case class PostsRow(id: Long, userId: String, title: String, description: String, text: String, createdAt: Long, updatedAt: Long)
  /** GetResult implicit for fetching PostsRow objects using plain SQL queries */
  implicit def GetResultPostsRow(implicit e0: GR[Long], e1: GR[String]): GR[PostsRow] = GR{
    prs => import prs._
    PostsRow.tupled((<<[Long], <<[String], <<[String], <<[String], <<[String], <<[Long], <<[Long]))
  }
  /** Table description of table posts. Objects of this class serve as prototypes for rows in queries. */
  class Posts(_tableTag: Tag) extends profile.api.Table[PostsRow](_tableTag, Some("vader"), "posts") {
    def * = (id, userId, title, description, text, createdAt, updatedAt) <> (PostsRow.tupled, PostsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(userId), Rep.Some(title), Rep.Some(description), Rep.Some(text), Rep.Some(createdAt), Rep.Some(updatedAt)).shaped.<>({r=>import r._; _1.map(_=> PostsRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(BIGINT UNSIGNED), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column user_id SqlType(VARCHAR), Length(50,true) */
    val userId: Rep[String] = column[String]("user_id", O.Length(50,varying=true))
    /** Database column title SqlType(VARCHAR), Length(255,true) */
    val title: Rep[String] = column[String]("title", O.Length(255,varying=true))
    /** Database column description SqlType(VARCHAR), Length(255,true) */
    val description: Rep[String] = column[String]("description", O.Length(255,varying=true))
    /** Database column text SqlType(VARCHAR), Length(10000,true) */
    val text: Rep[String] = column[String]("text", O.Length(10000,varying=true))
    /** Database column created_at SqlType(BIGINT) */
    val createdAt: Rep[Long] = column[Long]("created_at")
    /** Database column updated_at SqlType(BIGINT) */
    val updatedAt: Rep[Long] = column[Long]("updated_at")

    /** Foreign key referencing Users (database name posts_ibfk_1) */
    lazy val usersFk = foreignKey("posts_ibfk_1", userId, Users)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Posts */
  lazy val Posts = new TableQuery(tag => new Posts(tag))

  /** Entity class storing rows of table Users
   *  @param id Database column id SqlType(VARCHAR), PrimaryKey, Length(50,true)
   *  @param displayName Database column display_name SqlType(VARCHAR), Length(36,true)
   *  @param avatarUrl Database column avatar_url SqlType(VARCHAR), Length(3000,true)
   *  @param createdAt Database column created_at SqlType(BIGINT)
   *  @param updatedAt Database column updated_at SqlType(BIGINT) */
  case class UsersRow(id: String, displayName: String, avatarUrl: String, createdAt: Long, updatedAt: Long)
  /** GetResult implicit for fetching UsersRow objects using plain SQL queries */
  implicit def GetResultUsersRow(implicit e0: GR[String], e1: GR[Long]): GR[UsersRow] = GR{
    prs => import prs._
    UsersRow.tupled((<<[String], <<[String], <<[String], <<[Long], <<[Long]))
  }
  /** Table description of table users. Objects of this class serve as prototypes for rows in queries. */
  class Users(_tableTag: Tag) extends profile.api.Table[UsersRow](_tableTag, Some("vader"), "users") {
    def * = (id, displayName, avatarUrl, createdAt, updatedAt) <> (UsersRow.tupled, UsersRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(displayName), Rep.Some(avatarUrl), Rep.Some(createdAt), Rep.Some(updatedAt)).shaped.<>({r=>import r._; _1.map(_=> UsersRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(VARCHAR), PrimaryKey, Length(50,true) */
    val id: Rep[String] = column[String]("id", O.PrimaryKey, O.Length(50,varying=true))
    /** Database column display_name SqlType(VARCHAR), Length(36,true) */
    val displayName: Rep[String] = column[String]("display_name", O.Length(36,varying=true))
    /** Database column avatar_url SqlType(VARCHAR), Length(3000,true) */
    val avatarUrl: Rep[String] = column[String]("avatar_url", O.Length(3000,varying=true))
    /** Database column created_at SqlType(BIGINT) */
    val createdAt: Rep[Long] = column[Long]("created_at")
    /** Database column updated_at SqlType(BIGINT) */
    val updatedAt: Rep[Long] = column[Long]("updated_at")
  }
  /** Collection-like TableQuery object for table Users */
  lazy val Users = new TableQuery(tag => new Users(tag))
}
