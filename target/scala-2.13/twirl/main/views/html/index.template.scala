
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

object index extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[play.mvc.Http.Request,Map[String, SearchResultModel],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*4.2*/(request : play.mvc.Http.Request,searchMap : Map[String, SearchResultModel]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.78*/("""

"""),_display_(/*6.2*/main(request,"Welcome to Play")/*6.33*/ {_display_(Seq[Any](format.raw/*6.35*/("""
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

                <script>


                     let ws = new WebSocket("ws://localhost:9000/ws");
                     ws.onopen = () => """),format.raw/*34.40*/("""{"""),format.raw/*34.41*/("""
                          """),format.raw/*35.27*/("""console.log('WebSocket opened');
                         searchKeywords();
                     """),format.raw/*37.22*/("""}"""),format.raw/*37.23*/(""";

                     function searchKeywords() """),format.raw/*39.48*/("""{"""),format.raw/*39.49*/("""

                         """),format.raw/*41.26*/("""ws.send(JSON.stringify("""),format.raw/*41.49*/("""{"""),format.raw/*41.50*/("""
                             """),format.raw/*42.30*/(""""type": "Jasleen",
                             "data": "Kaur"
                         """),format.raw/*44.26*/("""}"""),format.raw/*44.27*/("""));
                     """),format.raw/*45.22*/("""}"""),format.raw/*45.23*/("""



                     """),format.raw/*49.22*/("""ws.onmessage = function (msg) """),format.raw/*49.52*/("""{"""),format.raw/*49.53*/("""
                          """),format.raw/*50.27*/("""console.log("WebSocket message received:", msg);

                     """),format.raw/*52.22*/("""}"""),format.raw/*52.23*/(""";
                </script>
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
                """),_display_(/*67.18*/for( project <- projectModal.getprojectDetails().slice(0, 10) ) yield /*67.81*/ {_display_(Seq[Any](format.raw/*67.83*/("""
                """),format.raw/*68.17*/("""<tr>
                    <td>
                        <p><a href="profilePage/"""),_display_(/*70.50*/project/*70.57*/.getOwnerID()),format.raw/*70.70*/("""" target="_blank">"""),_display_(/*70.89*/project/*70.96*/.getOwnerID()),format.raw/*70.109*/("""</a></p>
                    </td>
                    <td>
                        <p>"""),_display_(/*73.29*/project/*73.36*/.getTimeSubmitted().format("MMMM dd, yyyy")),format.raw/*73.79*/("""</p>
                    </td>
                    <td>
                        <p>"""),_display_(/*76.29*/project/*76.36*/.getTitle()),format.raw/*76.47*/("""</p>
                    </td>
                    <td>
                        <p>"""),_display_(/*79.29*/project/*79.36*/.getType()),format.raw/*79.46*/("""</p>
                    </td>
                    <td>
                        <p><a href="/"""),_display_(/*82.39*/phrase),format.raw/*82.45*/("""/wordStats/"""),_display_(/*82.57*/project/*82.64*/.getProjectID()),format.raw/*82.79*/("""" target="_blank">Stats</a></p>
                    </td>
                    <td>
                    """),_display_(/*85.22*/for(skill<-project.getSkills()) yield /*85.53*/{_display_(Seq[Any](format.raw/*85.54*/("""
                    """),format.raw/*86.21*/("""<a href="/projectBySkills/"""),_display_(/*86.48*/skill(0)),format.raw/*86.56*/("""" target="_blank">"""),_display_(/*86.75*/skill(1)),format.raw/*86.83*/(""" """),format.raw/*86.84*/(""",</a>
                    """)))}),format.raw/*87.22*/("""
                    """),format.raw/*88.21*/("""</td>
                    <td>
                        <p>"""),_display_(/*90.29*/project/*90.36*/.getReadability()),format.raw/*90.53*/("""</p>
                    </td>
                </tr>
                """)))}),format.raw/*93.18*/("""
                """),format.raw/*94.17*/("""</tbody>
            </table>
            """)))}),format.raw/*96.14*/("""
        """)))}),format.raw/*97.10*/("""
    """),format.raw/*98.5*/("""</div>
</div>
""")))}),format.raw/*100.2*/("""

"""))
      }
    }
  }

  def render(request:play.mvc.Http.Request,searchMap:Map[String, SearchResultModel]): play.twirl.api.HtmlFormat.Appendable = apply(request,searchMap)

  def f:((play.mvc.Http.Request,Map[String, SearchResultModel]) => play.twirl.api.HtmlFormat.Appendable) = (request,searchMap) => apply(request,searchMap)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/index.scala.html
                  HASH: a47e07e2aff7ff0c705013d239ab083d9e50b316
                  MATRIX: 610->1|646->32|1028->68|1199->144|1229->149|1268->180|1307->182|1335->184|1914->736|1945->758|1985->760|2027->775|2104->836|2144->838|2186->852|2366->1005|2393->1011|2484->1075|2511->1081|2649->1192|2670->1204|2719->1232|2807->1293|2828->1305|2878->1334|3080->1508|3109->1509|3165->1537|3292->1636|3321->1637|3401->1689|3430->1690|3487->1719|3538->1742|3567->1743|3626->1774|3744->1864|3773->1865|3827->1891|3856->1892|3913->1921|3971->1951|4000->1952|4056->1980|4157->2053|4186->2054|4759->2600|4838->2663|4878->2665|4924->2683|5032->2764|5048->2771|5082->2784|5128->2803|5144->2810|5179->2823|5297->2914|5313->2921|5377->2964|5491->3051|5507->3058|5539->3069|5653->3156|5669->3163|5700->3173|5824->3270|5851->3276|5890->3288|5906->3295|5942->3310|6076->3417|6123->3448|6162->3449|6212->3471|6266->3498|6295->3506|6341->3525|6370->3533|6399->3534|6458->3562|6508->3584|6596->3645|6612->3652|6650->3669|6754->3742|6800->3760|6876->3805|6918->3816|6951->3822|6999->3839
                  LINES: 23->1|24->2|29->4|34->4|36->6|36->6|36->6|37->7|50->20|50->20|50->20|51->21|51->21|51->21|52->22|54->24|54->24|55->25|55->25|56->26|56->26|56->26|57->27|57->27|57->27|64->34|64->34|65->35|67->37|67->37|69->39|69->39|71->41|71->41|71->41|72->42|74->44|74->44|75->45|75->45|79->49|79->49|79->49|80->50|82->52|82->52|97->67|97->67|97->67|98->68|100->70|100->70|100->70|100->70|100->70|100->70|103->73|103->73|103->73|106->76|106->76|106->76|109->79|109->79|109->79|112->82|112->82|112->82|112->82|112->82|115->85|115->85|115->85|116->86|116->86|116->86|116->86|116->86|116->86|117->87|118->88|120->90|120->90|120->90|123->93|124->94|126->96|127->97|128->98|130->100
                  -- GENERATED --
              */
          