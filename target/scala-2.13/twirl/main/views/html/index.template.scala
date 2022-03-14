
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
                        <th>Project Description</th>
                        <th>Description Readability</th>
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
                    """),_display_(/*53.22*/for(skill<-project.getSkills()) yield /*53.53*/{_display_(Seq[Any](format.raw/*53.54*/("""
                    """),format.raw/*54.21*/("""<a>"""),_display_(/*54.25*/skill),format.raw/*54.30*/(""" """),format.raw/*54.31*/(""",</a>
                    """)))}),format.raw/*55.22*/("""
                    """),format.raw/*56.21*/("""</td>
                    <td>
                        <p>"""),_display_(/*58.29*/project/*58.36*/.getProjectDescription()),format.raw/*58.60*/("""</p>
                    </td>
                    <td>
                        <p>"""),_display_(/*61.29*/project/*61.36*/.getEducationalLevel()),format.raw/*61.58*/("""</p>
                    </td>
                </tr>
                """)))}),format.raw/*64.18*/("""
                """),format.raw/*65.17*/("""</tbody>
            </table>
            """)))}),format.raw/*67.14*/("""
        """)))}),format.raw/*68.10*/("""
    """),format.raw/*69.5*/("""</div>
</div>
""")))}),format.raw/*71.2*/("""

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
                  HASH: 7e7ee265b136c545e1acfb2d15d7a3ee8671e497
                  MATRIX: 610->1|970->32|1111->78|1141->83|1172->106|1211->108|1239->110|1818->662|1870->705|1910->707|1952->722|2007->761|2047->763|2089->777|2269->930|2296->936|2893->1506|2938->1535|2978->1537|3024->1555|3111->1615|3127->1622|3161->1635|3275->1722|3291->1729|3331->1748|3445->1835|3461->1842|3493->1853|3607->1940|3623->1947|3654->1957|3761->2037|3808->2068|3847->2069|3897->2091|3928->2095|3954->2100|3983->2101|4042->2129|4092->2151|4180->2212|4196->2219|4241->2243|4355->2330|4371->2337|4414->2359|4518->2432|4564->2450|4640->2495|4682->2506|4715->2512|4762->2529
                  LINES: 23->1|28->2|33->2|35->4|35->4|35->4|36->5|49->18|49->18|49->18|50->19|50->19|50->19|51->20|53->22|53->22|69->38|69->38|69->38|70->39|72->41|72->41|72->41|75->44|75->44|75->44|78->47|78->47|78->47|81->50|81->50|81->50|84->53|84->53|84->53|85->54|85->54|85->54|85->54|86->55|87->56|89->58|89->58|89->58|92->61|92->61|92->61|95->64|96->65|98->67|99->68|100->69|102->71
                  -- GENERATED --
              */
          