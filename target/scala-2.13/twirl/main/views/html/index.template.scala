
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


Seq[Any](format.raw/*4.46*/("""

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
                        <p>"""),_display_(/*46.29*/project/*46.36*/.getOwnerID()),format.raw/*46.49*/("""</p>
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
                  HASH: 7500619e81bda8fbd7f8d60a7301b6c6ff49dca6
                  MATRIX: 610->1|646->32|1006->68|1145->112|1175->117|1206->140|1245->142|1273->144|1852->696|1883->718|1923->720|1965->735|2042->796|2082->798|2124->812|2304->965|2331->971|2422->1035|2449->1041|2587->1152|2608->1164|2657->1192|2745->1253|2766->1265|2816->1294|3392->1843|3471->1906|3511->1908|3557->1926|3644->1986|3660->1993|3694->2006|3808->2093|3824->2100|3888->2143|4002->2230|4018->2237|4050->2248|4164->2335|4180->2342|4211->2352|4335->2449|4362->2455|4401->2467|4417->2474|4453->2489|4587->2596|4634->2627|4673->2628|4723->2650|4777->2677|4806->2685|4852->2704|4881->2712|4910->2713|4969->2741|5019->2763|5107->2824|5123->2831|5161->2848|5265->2921|5311->2939|5387->2984|5429->2995|5462->3001|5509->3018
                  LINES: 23->1|24->2|29->4|34->4|36->6|36->6|36->6|37->7|50->20|50->20|50->20|51->21|51->21|51->21|52->22|54->24|54->24|55->25|55->25|56->26|56->26|56->26|57->27|57->27|57->27|73->43|73->43|73->43|74->44|76->46|76->46|76->46|79->49|79->49|79->49|82->52|82->52|82->52|85->55|85->55|85->55|88->58|88->58|88->58|88->58|88->58|91->61|91->61|91->61|92->62|92->62|92->62|92->62|92->62|92->62|93->63|94->64|96->66|96->66|96->66|99->69|100->70|102->72|103->73|104->74|106->76
                  -- GENERATED --
              */
          