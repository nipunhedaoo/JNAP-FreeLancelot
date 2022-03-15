
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
                """),_display_(/*38.18*/for( project <- projectList.slice(0, 10) ) yield /*38.60*/ {_display_(Seq[Any](format.raw/*38.62*/("""
                """),format.raw/*39.17*/("""<tr>
                    <td>
<<<<<<< HEAD
                        <p><a href="profilePage/"""),_display_(/*39.50*/project/*39.57*/.getOwnerID()),format.raw/*39.70*/("""" target="_blank">"""),_display_(/*39.89*/project/*39.96*/.getOwnerID()),format.raw/*39.109*/("""</a></p>
                    </td>
                    <td>
                        <p>"""),_display_(/*42.29*/project/*42.36*/.getTimeSubmitted().format("MMMM dd, yyyy")),format.raw/*42.79*/("""</p>
=======
                        <p>"""),_display_(/*41.29*/project/*41.36*/.getOwnerID()),format.raw/*41.49*/("""</p>
                    </td>
                    <td>
                        <p>"""),_display_(/*44.29*/project/*44.36*/.getTimeSubmitted().format("MMMM dd, yyyy")),format.raw/*44.79*/("""</p>
>>>>>>> nipun-task4-1
                    </td>
                    <td>
                        <p>"""),_display_(/*47.29*/project/*47.36*/.getTitle()),format.raw/*47.47*/("""</p>
                    </td>
                    <td>
                        <p>"""),_display_(/*50.29*/project/*50.36*/.getType()),format.raw/*50.46*/("""</p>
                    </td>
                    <td>
<<<<<<< HEAD
                    """),_display_(/*51.22*/for(skill<-project.getSkills()) yield /*51.53*/{_display_(Seq[Any](format.raw/*51.54*/("""
                    """),format.raw/*52.21*/("""<a href="projectBySkills/"""),_display_(/*52.47*/skill(1)),format.raw/*52.55*/("""" target="_blank">"""),_display_(/*52.74*/skill(1)),format.raw/*52.82*/(""" """),format.raw/*52.83*/(""",</a>
                    """)))}),format.raw/*53.22*/("""
                    """),format.raw/*54.21*/("""</td>
=======
                        <p><a href="/"""),_display_(/*53.39*/phrase),format.raw/*53.45*/("""/wordStats/"""),_display_(/*53.57*/project/*53.64*/.getProjectID()),format.raw/*53.79*/("""" target="_blank">Stats</a></p>
                    </td>
                    <td>
                    """),_display_(/*56.22*/for(skill<-project.getSkills()) yield /*56.53*/{_display_(Seq[Any](format.raw/*56.54*/("""
                    """),format.raw/*57.21*/("""<a href="/projectBySkills/"""),_display_(/*57.48*/skill(0)),format.raw/*57.56*/("""" target="_blank">"""),_display_(/*57.75*/skill(1)),format.raw/*57.83*/(""" """),format.raw/*57.84*/(""",</a>
                    """)))}),format.raw/*58.22*/("""
                    """),format.raw/*59.21*/("""</td>
>>>>>>> nipun-task4-1
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
<<<<<<< HEAD
                  HASH: 94a9bb05b65bb0338e239f558b5713bc03f00441
                  MATRIX: 610->1|970->32|1111->78|1141->83|1172->106|1211->108|1239->110|1818->662|1870->705|1910->707|1952->722|2007->761|2047->763|2089->777|2269->930|2296->936|2781->1394|2826->1423|2866->1425|2912->1443|3020->1524|3036->1531|3070->1544|3116->1563|3132->1570|3167->1583|3285->1674|3301->1681|3365->1724|3479->1811|3495->1818|3527->1829|3641->1916|3657->1923|3688->1933|3795->2013|3842->2044|3881->2045|3931->2067|3984->2093|4013->2101|4059->2120|4088->2128|4117->2129|4176->2157|4226->2179|4304->2226|4350->2244|4426->2289|4468->2300|4501->2306|4548->2323
                  LINES: 23->1|28->2|33->2|35->4|35->4|35->4|36->5|49->18|49->18|49->18|50->19|50->19|50->19|51->20|53->22|53->22|67->36|67->36|67->36|68->37|70->39|70->39|70->39|70->39|70->39|70->39|73->42|73->42|73->42|76->45|76->45|76->45|79->48|79->48|79->48|82->51|82->51|82->51|83->52|83->52|83->52|83->52|83->52|83->52|84->53|85->54|87->56|88->57|90->59|91->60|92->61|94->63
=======
                  HASH: fd92a10dafd7108365eb28f47ed148ba211d75a8
                  MATRIX: 610->1|970->32|1111->78|1141->83|1172->106|1211->108|1239->110|1818->662|1870->705|1910->707|1952->722|2007->761|2047->763|2089->777|2269->930|2296->936|2387->1000|2414->1006|2994->1559|3052->1601|3092->1603|3138->1621|3225->1681|3241->1688|3275->1701|3389->1788|3405->1795|3469->1838|3583->1925|3599->1932|3631->1943|3745->2030|3761->2037|3792->2047|3916->2144|3943->2150|3982->2162|3998->2169|4034->2184|4168->2291|4215->2322|4254->2323|4304->2345|4358->2372|4387->2380|4433->2399|4462->2407|4491->2408|4550->2436|4600->2458|4678->2505|4724->2523|4800->2568|4842->2579|4875->2585|4922->2602
                  LINES: 23->1|28->2|33->2|35->4|35->4|35->4|36->5|49->18|49->18|49->18|50->19|50->19|50->19|51->20|53->22|53->22|54->23|54->23|69->38|69->38|69->38|70->39|72->41|72->41|72->41|75->44|75->44|75->44|78->47|78->47|78->47|81->50|81->50|81->50|84->53|84->53|84->53|84->53|84->53|87->56|87->56|87->56|88->57|88->57|88->57|88->57|88->57|88->57|89->58|90->59|92->61|93->62|95->64|96->65|97->66|99->68
>>>>>>> nipun-task4-1
                  -- GENERATED --
              */
          