
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
                <dd class="col-sm-9"><h4><a href="/"""),_display_(/*22.53*/phrase),format.raw/*22.59*/("""/wordStatsGlobal" target="_blank">"""),_display_(/*22.94*/phrase),format.raw/*22.100*/("""</a></h4></dd>
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
                        <p><a href="/"""),_display_(/*45.39*/phrase),format.raw/*45.45*/("""/wordStats/"""),_display_(/*45.57*/project/*45.64*/.getProjectID()),format.raw/*45.79*/("""" target="_blank">"""),_display_(/*45.98*/project/*45.105*/.getTitle()),format.raw/*45.116*/("""</a></p>
                    </td>
                    <td>
                        <p>"""),_display_(/*48.29*/project/*48.36*/.getType()),format.raw/*48.46*/("""</p>
                    </td>
                    <td>
                    """),_display_(/*51.22*/for(skill<-project.getSkills()) yield /*51.53*/{_display_(Seq[Any](format.raw/*51.54*/("""
                    """),format.raw/*52.21*/("""<a>"""),_display_(/*52.25*/skill),format.raw/*52.30*/(""" """),format.raw/*52.31*/(""",</a>
                    """)))}),format.raw/*53.22*/("""
                    """),format.raw/*54.21*/("""</td>
                </tr>
                """)))}),format.raw/*56.18*/("""
                """),format.raw/*57.17*/("""</tbody>
            </table>
            """)))}),format.raw/*59.14*/("""
        """)))}),format.raw/*60.10*/("""
    """),format.raw/*61.5*/("""</div>
</div>
""")))}),format.raw/*63.2*/("""

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
                  HASH: 80a8af4513250a7b9ba8b671e1f9989091c5d08f
                  MATRIX: 610->1|970->32|1111->78|1141->83|1172->106|1211->108|1239->110|1818->662|1870->705|1910->707|1952->722|2007->761|2047->763|2089->777|2279->940|2306->946|2368->981|2396->987|2885->1449|2930->1478|2970->1480|3016->1498|3103->1558|3119->1565|3153->1578|3267->1665|3283->1672|3323->1691|3447->1788|3474->1794|3513->1806|3529->1813|3565->1828|3611->1847|3628->1854|3661->1865|3779->1956|3795->1963|3826->1973|3933->2053|3980->2084|4019->2085|4069->2107|4100->2111|4126->2116|4155->2117|4214->2145|4264->2167|4342->2214|4388->2232|4464->2277|4506->2288|4539->2294|4586->2311
                  LINES: 23->1|28->2|33->2|35->4|35->4|35->4|36->5|49->18|49->18|49->18|50->19|50->19|50->19|51->20|53->22|53->22|53->22|53->22|67->36|67->36|67->36|68->37|70->39|70->39|70->39|73->42|73->42|73->42|76->45|76->45|76->45|76->45|76->45|76->45|76->45|76->45|79->48|79->48|79->48|82->51|82->51|82->51|83->52|83->52|83->52|83->52|84->53|85->54|87->56|88->57|90->59|91->60|92->61|94->63
                  -- GENERATED --
              */
          