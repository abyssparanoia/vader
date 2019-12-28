import slick.codegen.SourceCodeGenerator

object Main {
  def main(args: Array[String]): Unit = {
    SourceCodeGenerator.run(
      profile = "slick.jdbc.MySQLProfile",
      jdbcDriver = "com.mysql.cj.jdbc.Driver",
      url =
        "jdbc:mysql://localhost/vader?useSSL=false&nullNamePatternMatchesAll=true",
      outputDir = "./app/infrastructure",
      pkg = "entity",
      user = Some("root"),
      password = Some("password"),
      ignoreInvalidDefaults = true
    )
  }
}
