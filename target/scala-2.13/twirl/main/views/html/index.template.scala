
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
/*1.2*/import Models.ProjectDetails

object index extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[Map[String, List[ProjectDetails]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*2.2*/(searchMap : Map[String,List[ProjectDetails]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.48*/("""

"""),_display_(/*4.2*/main("Welcome to Play")/*4.25*/ {_display_(Seq[Any](format.raw/*4.27*/("""
"""),format.raw/*5.1*/("""<div class=".container-fluid">
        <div class="jumbotron jumbotron-fluid">
            <h1 id="mainBanner" >FreeLancelot</h1>
        </div>
</div>
<div class="container">
    <div class="container">
        <div>
            <form action="/" method="get">
                <input type="text" class="input-lg" size="105" name="searchKeyword" placeholder="Please Search Here" />
                <button class="btn btn-primary"><span class="glyphicon glyphicon-search"></span>  Search</button>
            </form>
        </div>
        """),_display_(/*18.10*/if(searchMap != null && searchMap.size > 0)/*18.53*/ {_display_(Seq[Any](format.raw/*18.55*/("""
            """),_display_(/*19.14*/for((phrase, projectList) <- searchMap) yield /*19.53*/ {_display_(Seq[Any](format.raw/*19.55*/("""
            """),format.raw/*20.13*/("""<dl class="row" style="margin-top: 50px;">
                <dt class="col-sm-3"><h4>Search term : </h4></dt>
                <dd class="col-sm-9"><h4>"""),_display_(/*22.43*/phrase),format.raw/*22.49*/("""</h4></dd>
            </dl>

            <table border="1" class="table">
                <thead class="thead-light">
                    <tr>
                        <th>Owner ID</th>
                        <th>Submission Date</th>
                        <th>Title</th>
                        <th>Type</th>
                        <th>Skills</th>
                    </tr>
                </thead>
                <tbody>
                """),_display_(/*36.18*/for( project <- projectList ) yield /*36.47*/ {_display_(Seq[Any](format.raw/*36.49*/("""
                """),format.raw/*37.17*/("""<tr>
                    <td>
                        <p>"""),_display_(/*39.29*/project/*39.36*/.getOwnerID()),format.raw/*39.49*/("""</p>
                    </td>
                    <td>
                        <p>"""),_display_(/*42.29*/project/*42.36*/.getTimeSubmitted()),format.raw/*42.55*/("""</p>
                    </td>
                    <td>
                        <p>"""),_display_(/*45.29*/project/*45.36*/.getTitle()),format.raw/*45.47*/("""</p>
                    </td>
                    <td>
                        <p>"""),_display_(/*48.29*/project/*48.36*/.getType()),format.raw/*48.46*/("""</p>
                    </td>
                </tr>
                """)))}),format.raw/*51.18*/("""
                """),format.raw/*52.17*/("""</tbody>
            </table>
            """)))}),format.raw/*54.14*/("""
        """)))}),format.raw/*55.10*/("""
    """),format.raw/*56.5*/("""</div>
</div>
""")))}),format.raw/*58.2*/("""

"""))
      }
    }
  }

  def render(searchMap:Map[String, List[ProjectDetails]]): play.twirl.api.HtmlFormat.Appendable = apply(searchMap)

  def f:((Map[String, List[ProjectDetails]]) => play.twirl.api.HtmlFormat.Appendable) = (searchMap) => apply(searchMap)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/index.scala.html
                  HASH: f63ade95a3b6a7213f0ea46a40774e0b5614f7d7
                  MATRIX: 610->1|970->32|1111->78|1141->83|1172->106|1211->108|1239->110|1818->662|1870->705|1910->707|1952->722|2007->761|2047->763|2089->777|2269->930|2296->936|2781->1394|2826->1423|2866->1425|2912->1443|2999->1503|3015->1510|3049->1523|3163->1610|3179->1617|3219->1636|3333->1723|3349->1730|3381->1741|3495->1828|3511->1835|3542->1845|3646->1918|3692->1936|3768->1981|3810->1992|3843->1998|3890->2015
                  LINES: 23->1|28->2|33->2|35->4|35->4|35->4|36->5|49->18|49->18|49->18|50->19|50->19|50->19|51->20|53->22|53->22|67->36|67->36|67->36|68->37|70->39|70->39|70->39|73->42|73->42|73->42|76->45|76->45|76->45|79->48|79->48|79->48|82->51|83->52|85->54|86->55|87->56|89->58
                  -- GENERATED --
              */
          