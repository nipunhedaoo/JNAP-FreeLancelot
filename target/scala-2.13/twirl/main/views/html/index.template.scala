
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
/*1.2*/import models.ProjectDetails

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
                <dd class="col-sm-3"><h4>"""),_display_(/*22.43*/phrase),format.raw/*22.49*/("""</h4></dd>
                <dd class="col-sm-6"><h4><a href="/"""),_display_(/*23.53*/phrase),format.raw/*23.59*/("""/wordStatsGlobal" target="_blank">Global Stats</a></h4></dd>
            </dl>

            <table border="1" class="table">
                <thead class="thead-light">
                    <tr>
                        <th>Owner ID</th>
                        <th>Submission Date</th>
                        <th>Title</th>
                        <th>Type</th>
                        <th>Word Stats</th>
                        <th>Skills</th>
                    </tr>
                </thead>
                <tbody>
                """),_display_(/*38.18*/for( project <- projectList ) yield /*38.47*/ {_display_(Seq[Any](format.raw/*38.49*/("""
                """),format.raw/*39.17*/("""<tr>
                    <td>
                        <p>"""),_display_(/*41.29*/project/*41.36*/.getOwnerID()),format.raw/*41.49*/("""</p>
                    </td>
                    <td>
                        <p>"""),_display_(/*44.29*/project/*44.36*/.getTimeSubmitted()),format.raw/*44.55*/("""</p>
                    </td>
                    <td>
                        <p>"""),_display_(/*47.29*/project/*47.36*/.getTitle()),format.raw/*47.47*/("""</p>
                    </td>
                    <td>
                        <p>"""),_display_(/*50.29*/project/*50.36*/.getType()),format.raw/*50.46*/("""</p>
                    </td>
                    <td>
                        <p><a href="/"""),_display_(/*53.39*/phrase),format.raw/*53.45*/("""/wordStats/"""),_display_(/*53.57*/project/*53.64*/.getProjectID()),format.raw/*53.79*/("""" target="_blank">Stats</a></p>
                    </td>
                    <td>
                    """),_display_(/*56.22*/for(skill<-project.getSkills()) yield /*56.53*/{_display_(Seq[Any](format.raw/*56.54*/("""
                    """),format.raw/*57.21*/("""<a>"""),_display_(/*57.25*/skill),format.raw/*57.30*/(""" """),format.raw/*57.31*/(""",</a>
                    """)))}),format.raw/*58.22*/("""
                    """),format.raw/*59.21*/("""</td>
                </tr>
                """)))}),format.raw/*61.18*/("""
                """),format.raw/*62.17*/("""</tbody>
            </table>
            """)))}),format.raw/*64.14*/("""
        """)))}),format.raw/*65.10*/("""
    """),format.raw/*66.5*/("""</div>
</div>
""")))}),format.raw/*68.2*/("""

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
                  HASH: 4258313b6ea907af8b0fbf5405a23c0a6f8e8d10
                  MATRIX: 610->1|970->32|1111->78|1141->83|1172->106|1211->108|1239->110|1818->662|1870->705|1910->707|1952->722|2007->761|2047->763|2089->777|2269->930|2296->936|2387->1000|2414->1006|2994->1559|3039->1588|3079->1590|3125->1608|3212->1668|3228->1675|3262->1688|3376->1775|3392->1782|3432->1801|3546->1888|3562->1895|3594->1906|3708->1993|3724->2000|3755->2010|3879->2107|3906->2113|3945->2125|3961->2132|3997->2147|4131->2254|4178->2285|4217->2286|4267->2308|4298->2312|4324->2317|4353->2318|4412->2346|4462->2368|4540->2415|4586->2433|4662->2478|4704->2489|4737->2495|4784->2512
                  LINES: 23->1|28->2|33->2|35->4|35->4|35->4|36->5|49->18|49->18|49->18|50->19|50->19|50->19|51->20|53->22|53->22|54->23|54->23|69->38|69->38|69->38|70->39|72->41|72->41|72->41|75->44|75->44|75->44|78->47|78->47|78->47|81->50|81->50|81->50|84->53|84->53|84->53|84->53|84->53|87->56|87->56|87->56|88->57|88->57|88->57|88->57|89->58|90->59|92->61|93->62|95->64|96->65|97->66|99->68
                  -- GENERATED --
              */
          