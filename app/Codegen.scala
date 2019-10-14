object Main {
  def main(args: Array[String]): Unit = {
    val uri = getClass.getResource("/codegen.conf")
    slick.codegen.SourceCodeGenerator.main(Array(uri.toString + "#codegen.vader"))
  }
}
