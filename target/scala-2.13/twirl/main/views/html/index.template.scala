
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
                <input type="text" class="input-lg" size="105" id="myInput" name="searchKeyword" placeholder="Please Search Here" />
                <button class="btn btn-primary"><span class="glyphicon glyphicon-search"></span>  Search</button>
            </form>
        </div>
        """),_display_(/*20.10*/if(searchMap.size > 0)/*20.32*/ {_display_(Seq[Any](format.raw/*20.34*/("""

        """),format.raw/*22.9*/("""<script>

                     let ws = new WebSocket("ws://localhost:9000/ws");
                     let jsonStorage = """),format.raw/*25.40*/("""{"""),format.raw/*25.41*/("""}"""),format.raw/*25.42*/(""";
                     let recentKeywords = '';

                     ws.onopen = () => """),format.raw/*28.40*/("""{"""),format.raw/*28.41*/("""
                         """),format.raw/*29.26*/("""console.log('WebSocket opened');
                         searchKeywords();

                     """),format.raw/*32.22*/("""}"""),format.raw/*32.23*/(""";

                     ws.onmessage = function (msg) """),format.raw/*34.52*/("""{"""),format.raw/*34.53*/("""
                          """),format.raw/*35.27*/("""console.log("WebSocket message received:", msg);
                     """),format.raw/*36.22*/("""}"""),format.raw/*36.23*/(""";

                    let keywordsInput = document.getElementById('myInput');

<!--                    keywordsInput.addEventListener('keypress', function (event) """),format.raw/*40.85*/("""{"""),format.raw/*40.86*/("""-->
<!--                        if (event.key === 'Enter') """),format.raw/*41.56*/("""{"""),format.raw/*41.57*/("""-->
<!--                        console.log(event);-->
<!--                            searchKeywords();-->
<!--                            event.preventDefault();-->
<!--                        """),format.raw/*45.29*/("""}"""),format.raw/*45.30*/("""-->
<!--                    """),format.raw/*46.25*/("""}"""),format.raw/*46.26*/(""");-->



                    function searchKeywords() """),format.raw/*50.47*/("""{"""),format.raw/*50.48*/("""
                        """),format.raw/*51.25*/("""var url = new URL(window.location.href);

                        let keywords = url.searchParams.get("searchKeyword");

                        ws.send(JSON.stringify("""),format.raw/*55.48*/("""{"""),format.raw/*55.49*/("""
                            """),format.raw/*56.29*/("""type: "searchTerms",
                            data: keywords
                        """),format.raw/*58.25*/("""}"""),format.raw/*58.26*/("""));

                        recentKeywords = keywords;
                    """),format.raw/*61.21*/("""}"""),format.raw/*61.22*/("""
                """),format.raw/*62.17*/("""</script>


            """),_display_(/*65.14*/for((phrase, projectModal : SearchResultModel ) <- searchMap) yield /*65.75*/ {_display_(Seq[Any](format.raw/*65.77*/("""
            """),format.raw/*66.13*/("""<dl class="row" style="margin-top: 50px;">
                <dt class="col-sm-3"><h4>Search term : </h4></dt>
                <dd class="col-sm-3"><h4>"""),_display_(/*68.43*/phrase),format.raw/*68.49*/("""</h4></dd>
                <dd class="col-sm-6"><h4><a href="/"""),_display_(/*69.53*/phrase),format.raw/*69.59*/("""/wordStatsGlobal" target="_blank">Global Stats</a></h4></dd>
                <dd class="col-sm-3"><h4>FKCL : """),_display_(/*70.50*/projectModal/*70.62*/.getfleschReadabilityIndex()),format.raw/*70.90*/("""</h4></dd>
                <dd class="col-sm-3"><h4>FKGL : """),_display_(/*71.50*/projectModal/*71.62*/.getfleschKincaidGradeLevel()),format.raw/*71.91*/("""</h4></dd>
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
                """),_display_(/*87.18*/for( project <- projectModal.getprojectDetails().slice(0, 10) ) yield /*87.81*/ {_display_(Seq[Any](format.raw/*87.83*/("""
                """),format.raw/*88.17*/("""<tr>
                    <td>
                        <p><a href="profilePage/"""),_display_(/*90.50*/project/*90.57*/.getOwnerID()),format.raw/*90.70*/("""" target="_blank">"""),_display_(/*90.89*/project/*90.96*/.getOwnerID()),format.raw/*90.109*/("""</a></p>
                    </td>
                    <td>
                        <p>"""),_display_(/*93.29*/project/*93.36*/.getTimeSubmitted().format("MMMM dd, yyyy")),format.raw/*93.79*/("""</p>
                    </td>
                    <td>
                        <p>"""),_display_(/*96.29*/project/*96.36*/.getTitle()),format.raw/*96.47*/("""</p>
                    </td>
                    <td>
                        <p>"""),_display_(/*99.29*/project/*99.36*/.getType()),format.raw/*99.46*/("""</p>
                    </td>
                    <td>
                        <p><a href="/"""),_display_(/*102.39*/phrase),format.raw/*102.45*/("""/wordStats/"""),_display_(/*102.57*/project/*102.64*/.getProjectID()),format.raw/*102.79*/("""" target="_blank">Stats</a></p>
                    </td>
                    <td>
                    """),_display_(/*105.22*/for(skill<-project.getSkills()) yield /*105.53*/{_display_(Seq[Any](format.raw/*105.54*/("""
                    """),format.raw/*106.21*/("""<a href="/projectBySkills/"""),_display_(/*106.48*/skill(0)),format.raw/*106.56*/("""" target="_blank">"""),_display_(/*106.75*/skill(1)),format.raw/*106.83*/(""" """),format.raw/*106.84*/(""",</a>
                    """)))}),format.raw/*107.22*/("""
                    """),format.raw/*108.21*/("""</td>
                    <td>
                        <p>"""),_display_(/*110.29*/project/*110.36*/.getReadability()),format.raw/*110.53*/("""</p>
                    </td>
                </tr>
                """)))}),format.raw/*113.18*/("""
                """),format.raw/*114.17*/("""</tbody>
            </table>
            """)))}),format.raw/*116.14*/("""
        """)))}),format.raw/*117.10*/("""
    """),format.raw/*118.5*/("""</div>
</div>
""")))}),format.raw/*120.2*/("""

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
                  HASH: 74fb89745684c3b06bf9ee19991505a2f5d56e0d
                  MATRIX: 610->1|646->32|1028->68|1199->144|1229->149|1260->172|1299->174|1327->176|1919->741|1950->763|1990->765|2029->777|2180->900|2209->901|2238->902|2357->993|2386->994|2441->1021|2570->1122|2599->1123|2683->1179|2712->1180|2768->1208|2867->1279|2896->1280|3092->1448|3121->1449|3209->1509|3238->1510|3465->1709|3494->1710|3551->1739|3580->1740|3667->1799|3696->1800|3750->1826|3950->1998|3979->1999|4037->2029|4155->2119|4184->2120|4291->2199|4320->2200|4366->2218|4421->2246|4498->2307|4538->2309|4580->2323|4760->2476|4787->2482|4878->2546|4905->2552|5043->2663|5064->2675|5113->2703|5201->2764|5222->2776|5272->2805|5848->3354|5927->3417|5967->3419|6013->3437|6121->3518|6137->3525|6171->3538|6217->3557|6233->3564|6268->3577|6386->3668|6402->3675|6466->3718|6580->3805|6596->3812|6628->3823|6742->3910|6758->3917|6789->3927|6914->4024|6942->4030|6982->4042|6999->4049|7036->4064|7171->4171|7219->4202|7259->4203|7310->4225|7365->4252|7395->4260|7442->4279|7472->4287|7502->4288|7562->4316|7613->4338|7702->4399|7719->4406|7758->4423|7863->4496|7910->4514|7987->4559|8030->4570|8064->4576|8112->4593
                  LINES: 23->1|24->2|29->4|34->4|36->6|36->6|36->6|37->7|50->20|50->20|50->20|52->22|55->25|55->25|55->25|58->28|58->28|59->29|62->32|62->32|64->34|64->34|65->35|66->36|66->36|70->40|70->40|71->41|71->41|75->45|75->45|76->46|76->46|80->50|80->50|81->51|85->55|85->55|86->56|88->58|88->58|91->61|91->61|92->62|95->65|95->65|95->65|96->66|98->68|98->68|99->69|99->69|100->70|100->70|100->70|101->71|101->71|101->71|117->87|117->87|117->87|118->88|120->90|120->90|120->90|120->90|120->90|120->90|123->93|123->93|123->93|126->96|126->96|126->96|129->99|129->99|129->99|132->102|132->102|132->102|132->102|132->102|135->105|135->105|135->105|136->106|136->106|136->106|136->106|136->106|136->106|137->107|138->108|140->110|140->110|140->110|143->113|144->114|146->116|147->117|148->118|150->120
                  -- GENERATED --
              */
          