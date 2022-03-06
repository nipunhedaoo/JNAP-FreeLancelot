
package views.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import java.lang._
import java.util._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.api.data.Field
import play.data._
import play.core.j.PlayFormsMagicForJava._
import scala.jdk.CollectionConverters._

object index extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[String,play.twirl.api.HtmlFormat.Appendable] {

  /*
	@(searchMap : Map[String,List[UserRepositoryTopics]])
*/
  def apply/*5.2*/(searchString : String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*5.25*/("""

  """),_display_(/*7.4*/main("Welcome to Play")/*7.27*/ {_display_(Seq[Any](format.raw/*7.29*/("""
"""),format.raw/*8.1*/("""<div class=".container-fluid">
        <div class="jumbotron jumbotron-fluid">
            <h1 id="mainBanner" >FreeLancelot</h1>
        </div>
</div>
<div class="container">
    <div class="container">
        <div>
            <form action=""""),_display_(/*16.28*/routes/*16.34*/.HomeController.search()),format.raw/*16.58*/("""" method="post">
                <input type="text" class="input-lg" name="phrase" size="105">
                <button class="btn btn-primary"><span class="glyphicon glyphicon-search"></span>  Search</button>
            </form>
            <p>"""),_display_(/*20.17*/searchString),format.raw/*20.29*/("""</p>
        </div>
    </div>
</div>
""")))}),format.raw/*24.2*/("""

"""))
      }
    }
  }

  def render(searchString:String): play.twirl.api.HtmlFormat.Appendable = apply(searchString)

  def f:((String) => play.twirl.api.HtmlFormat.Appendable) = (searchString) => apply(searchString)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/index.scala.html
                  HASH: 7f056a83bc2a49c37b756d80e1ae00df8b205ee5
                  MATRIX: 965->67|1083->90|1115->97|1146->120|1185->122|1213->124|1493->377|1508->383|1553->407|1829->656|1862->668|1935->711
                  LINES: 29->5|34->5|36->7|36->7|36->7|37->8|45->16|45->16|45->16|49->20|49->20|53->24
                  -- GENERATED --
              */
          