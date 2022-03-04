
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

object index extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /*
	@(searchMap : Map[String,List[UserRepositoryTopics]])
*/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](_display_(/*5.4*/main("Welcome to Play")/*5.27*/ {_display_(Seq[Any](format.raw/*5.29*/("""
"""),format.raw/*6.1*/("""<div class=".container-fluid">
        <div class="jumbotron jumbotron-fluid">
            <h1 id="mainBanner" >FreeLancelot</h1>
        </div>
</div>
<div class="container">
    <div>
    <form action=""""),_display_(/*13.20*/routes/*13.26*/.HomeController.search()),format.raw/*13.50*/("""" method="post">
      <input type="text" class="input-lg" name="searchTerm" size="105">
      <button class="btn btn-primary"><span class="glyphicon glyphicon-search"></span>  Search</button>
    </form>
    </div>
</div>
""")))}),format.raw/*19.2*/("""

"""))
      }
    }
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/index.scala.html
                  HASH: 20bd3a1d022026c40e77a18867d330bc10dbb015
                  MATRIX: 1047->69|1078->92|1117->94|1145->96|1384->308|1399->314|1444->338|1704->568
                  LINES: 34->5|34->5|34->5|35->6|42->13|42->13|42->13|48->19
                  -- GENERATED --
              */
          