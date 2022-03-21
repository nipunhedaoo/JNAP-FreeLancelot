
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
/*2.2*/import models.SearchResultModel

object index extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[Map[String, SearchResultModel],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*4.2*/(searchMap : Map[String, SearchResultModel]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*5.1*/("""
"""),_display_(/*6.2*/main("Welcome to Play")/*6.25*/ {_display_(Seq[Any](format.raw/*6.27*/("""
"""),format.raw/*7.1*/("""<div class=".container-fluid">
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
        """),_display_(/*20.10*/if(searchMap.size > 0)/*20.32*/ {_display_(Seq[Any](format.raw/*20.34*/("""
            """),_display_(/*21.14*/for((phrase, projectModal : SearchResultModel ) <- searchMap) yield /*21.75*/ {_display_(Seq[Any](format.raw/*21.77*/("""
            """),format.raw/*22.13*/("""<dl class="row" style="margin-top: 50px;">
                <dt class="col-sm-3"><h4>Search term : </h4></dt>
                <dd class="col-sm-3"><h4>"""),_display_(/*24.43*/phrase),format.raw/*24.49*/("""</h4></dd>
                <dd class="col-sm-6"><h4><a href="/"""),_display_(/*25.53*/phrase),format.raw/*25.59*/("""/wordStatsGlobal" target="_blank">Global Stats</a></h4></dd>
                <dd class="col-sm-3"><h4>FKCL : """),_display_(/*26.50*/projectModal/*26.62*/.getfleschReadabilityIndex()),format.raw/*26.90*/("""</h4></dd>
                <dd class="col-sm-3"><h4>FKGL : """),_display_(/*27.50*/projectModal/*27.62*/.getfleschKincaidGradeLevel()),format.raw/*27.91*/("""</h4></dd>
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
                        <th>Readability</th>
                    </tr>
                </thead>
                <tbody>
                """),_display_(/*43.18*/for( project <- projectModal.getprojectDetails().slice(0, 10) ) yield /*43.81*/ {_display_(Seq[Any](format.raw/*43.83*/("""
                """),format.raw/*44.17*/("""<tr>
                    <td>
                        <p><a href="profilePage/"""),_display_(/*46.50*/project/*46.57*/.getOwnerID()),format.raw/*46.70*/("""" target="_blank">"""),_display_(/*46.89*/project/*46.96*/.getOwnerID()),format.raw/*46.109*/("""</a></p>
                    </td>
                    <td>
                        <p>"""),_display_(/*49.29*/project/*49.36*/.getTimeSubmitted().format("MMMM dd, yyyy")),format.raw/*49.79*/("""</p>
                    </td>
                    <td>
                        <p>"""),_display_(/*52.29*/project/*52.36*/.getTitle()),format.raw/*52.47*/("""</p>
                    </td>
                    <td>
                        <p>"""),_display_(/*55.29*/project/*55.36*/.getType()),format.raw/*55.46*/("""</p>
                    </td>
                    <td>
                        <p><a href="/"""),_display_(/*58.39*/phrase),format.raw/*58.45*/("""/wordStats/"""),_display_(/*58.57*/project/*58.64*/.getProjectID()),format.raw/*58.79*/("""" target="_blank">Stats</a></p>
                    </td>
                    <td>
                    """),_display_(/*61.22*/for(skill<-project.getSkills()) yield /*61.53*/{_display_(Seq[Any](format.raw/*61.54*/("""
                    """),format.raw/*62.21*/("""<a href="/projectBySkills/"""),_display_(/*62.48*/skill(0)),format.raw/*62.56*/("""" target="_blank">"""),_display_(/*62.75*/skill(1)),format.raw/*62.83*/(""" """),format.raw/*62.84*/(""",</a>
                    """)))}),format.raw/*63.22*/("""
                    """),format.raw/*64.21*/("""</td>
                    <td>
                        <p>"""),_display_(/*66.29*/project/*66.36*/.getReadability()),format.raw/*66.53*/("""</p>
                    </td>
                </tr>
                """)))}),format.raw/*69.18*/("""
                """),format.raw/*70.17*/("""</tbody>
            </table>
            """)))}),format.raw/*72.14*/("""
        """)))}),format.raw/*73.10*/("""
    """),format.raw/*74.5*/("""</div>
</div>
""")))}),format.raw/*76.2*/("""

"""))
      }
    }
  }

  def render(searchMap:Map[String, SearchResultModel]): play.twirl.api.HtmlFormat.Appendable = apply(searchMap)

  def f:((Map[String, SearchResultModel]) => play.twirl.api.HtmlFormat.Appendable) = (searchMap) => apply(searchMap)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/index.scala.html
                  HASH: 3e80d03f2aa879aff26b2e05c988c0f77e7e0e16
                  MATRIX: 610->1|646->31|1006->65|1144->110|1171->112|1202->135|1241->137|1268->138|1834->677|1865->699|1905->701|1946->715|2023->776|2063->778|2104->791|2282->942|2309->948|2399->1011|2426->1017|2563->1127|2584->1139|2633->1167|2720->1227|2741->1239|2791->1268|3351->1801|3430->1864|3470->1866|3515->1883|3621->1962|3637->1969|3671->1982|3717->2001|3733->2008|3768->2021|3883->2109|3899->2116|3963->2159|4074->2243|4090->2250|4122->2261|4233->2345|4249->2352|4280->2362|4401->2456|4428->2462|4467->2474|4483->2481|4519->2496|4650->2600|4697->2631|4736->2632|4785->2653|4839->2680|4868->2688|4914->2707|4943->2715|4972->2716|5030->2743|5079->2764|5165->2823|5181->2830|5219->2847|5320->2917|5365->2934|5439->2977|5480->2987|5512->2992|5557->3007
                  LINES: 23->1|24->2|29->4|34->5|35->6|35->6|35->6|36->7|49->20|49->20|49->20|50->21|50->21|50->21|51->22|53->24|53->24|54->25|54->25|55->26|55->26|55->26|56->27|56->27|56->27|72->43|72->43|72->43|73->44|75->46|75->46|75->46|75->46|75->46|75->46|78->49|78->49|78->49|81->52|81->52|81->52|84->55|84->55|84->55|87->58|87->58|87->58|87->58|87->58|90->61|90->61|90->61|91->62|91->62|91->62|91->62|91->62|91->62|92->63|93->64|95->66|95->66|95->66|98->69|99->70|101->72|102->73|103->74|105->76
                  -- GENERATED --
              */
          