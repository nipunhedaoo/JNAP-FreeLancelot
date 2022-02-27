
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

object main extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[String,Html,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(title: String)(content: Html):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.32*/("""

"""),format.raw/*3.1*/("""<!DOCTYPE html>

<html lang="en">
    <head>
        <title>"""),_display_(/*7.17*/title),format.raw/*7.22*/("""</title>
        <link rel="stylesheet" media="screen" href=""""),_display_(/*8.54*/routes/*8.60*/.Assets.versioned("stylesheets/main.css")),format.raw/*8.101*/("""">
        <link rel="shortcut icon" type="image/png" href=""""),_display_(/*9.59*/routes/*9.65*/.Assets.versioned("images/favicon.png")),format.raw/*9.104*/("""">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <style>
            h4:empty::before """),format.raw/*14.30*/("""{"""),format.raw/*14.31*/("""content: "No data""""),format.raw/*14.49*/("""}"""),format.raw/*14.50*/("""

           """),format.raw/*16.12*/("""#mainBanner """),format.raw/*16.24*/("""{"""),format.raw/*16.25*/("""
           """),format.raw/*17.12*/("""width: 100px;
           margin-left: 570px;
           margin-right: auto;
           """),format.raw/*20.12*/("""}"""),format.raw/*20.13*/("""

           """),format.raw/*22.12*/("""#form-center """),format.raw/*22.25*/("""{"""),format.raw/*22.26*/("""
	        """),format.raw/*23.10*/("""width:400px;
	        margin: 0 auto;
            """),format.raw/*25.13*/("""}"""),format.raw/*25.14*/("""
        """),format.raw/*26.9*/("""</style>
    </head>
    <body>
    """),_display_(/*29.6*/content),format.raw/*29.13*/("""
    """),format.raw/*30.5*/("""</body>
</html>
"""))
      }
    }
  }

  def render(title:String,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(title)(content)

  def f:((String) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (title) => (content) => apply(title)(content)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/main.scala.html
                  HASH: 0c73db2790247422f69d4b99cccc2ec24c4ce5e3
                  MATRIX: 911->1|1036->31|1066->35|1157->100|1182->105|1271->168|1285->174|1347->215|1435->277|1449->283|1509->322|1896->681|1925->682|1971->700|2000->701|2043->716|2083->728|2112->729|2153->742|2271->832|2300->833|2343->848|2384->861|2413->862|2452->873|2532->925|2561->926|2598->936|2664->976|2692->983|2725->989
                  LINES: 27->1|32->1|34->3|38->7|38->7|39->8|39->8|39->8|40->9|40->9|40->9|45->14|45->14|45->14|45->14|47->16|47->16|47->16|48->17|51->20|51->20|53->22|53->22|53->22|54->23|56->25|56->25|57->26|60->29|60->29|61->30
                  -- GENERATED --
              */
          