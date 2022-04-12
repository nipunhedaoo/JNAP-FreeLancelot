
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
  def apply/*4.2*/(request : play.mvc.Http.Request, searchMap : Map[String, SearchResultModel]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.79*/("""

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
        """),format.raw/*21.9*/("""<script>

                     let ws = new WebSocket("ws://localhost:9000/ws");
                     let jsonStorage = """),format.raw/*24.40*/("""{"""),format.raw/*24.41*/("""}"""),format.raw/*24.42*/(""";
                     let recentKeywords = '';

                     ws.onopen = () => """),format.raw/*27.40*/("""{"""),format.raw/*27.41*/("""
                         """),format.raw/*28.26*/("""console.log('WebSocket opened');
                         searchKeywords();
                     """),format.raw/*30.22*/("""}"""),format.raw/*30.23*/(""";

                     ws.onmessage = function (msg) """),format.raw/*32.52*/("""{"""),format.raw/*32.53*/("""
                          """),format.raw/*33.27*/("""console.log("WebSocket message received");
                          let data = JSON.parse(msg.data);
                          let repositories = data.result.projects;

                          let table = document.getElementById("result-table-body");
                            if (table.rows.length === 0) """),format.raw/*38.58*/("""{"""),format.raw/*38.59*/("""
                                """),format.raw/*39.33*/("""for (const repository of repositories) """),format.raw/*39.72*/("""{"""),format.raw/*39.73*/("""
                                    """),format.raw/*40.37*/("""addNewRow(table, repository);
                                """),format.raw/*41.33*/("""}"""),format.raw/*41.34*/("""
                            """),format.raw/*42.29*/("""}"""),format.raw/*42.30*/("""
                            """),format.raw/*43.29*/("""else """),format.raw/*43.34*/("""{"""),format.raw/*43.35*/("""
                            """),format.raw/*44.29*/("""for (const repository of repositories) """),format.raw/*44.68*/("""{"""),format.raw/*44.69*/("""
                                """),format.raw/*45.33*/("""if (!(repository.fullName in jsonStorage)) """),format.raw/*45.76*/("""{"""),format.raw/*45.77*/("""
                                    """),format.raw/*46.37*/("""addNewRow(table, repository);
                                """),format.raw/*47.33*/("""}"""),format.raw/*47.34*/("""
                            """),format.raw/*48.29*/("""}"""),format.raw/*48.30*/("""
                          """),format.raw/*49.27*/("""}"""),format.raw/*49.28*/("""
                     """),format.raw/*50.22*/("""}"""),format.raw/*50.23*/("""

                    """),format.raw/*52.21*/("""let keywordsInput = document.getElementById('myInput');

                    function searchKeywords() """),format.raw/*54.47*/("""{"""),format.raw/*54.48*/("""
                        """),format.raw/*55.25*/("""var url = new URL(window.location.href);

                        let keywords = url.searchParams.get("searchKeyword");

                        ws.send(JSON.stringify("""),format.raw/*59.48*/("""{"""),format.raw/*59.49*/("""
                            """),format.raw/*60.29*/("""type: "searchTerms",
                            data: keywords
                        """),format.raw/*62.25*/("""}"""),format.raw/*62.26*/("""));

                        recentKeywords = keywords;
                    """),format.raw/*65.21*/("""}"""),format.raw/*65.22*/("""

                    """),format.raw/*67.21*/("""function addNewRow(table, repository) """),format.raw/*67.59*/("""{"""),format.raw/*67.60*/("""
                        """),format.raw/*68.25*/("""console.log(repository.id);
                        jsonStorage[repository.id] = repository;
                        let row = table.insertRow(0);
                        let ownerCell = row.insertCell(0);
                        let subDateCell = row.insertCell(1);
                        let titleCell = row.insertCell(2);
                        let typeCell = row.insertCell(3);
                        let wordStatsCell = row.insertCell(4);
                        let skillsCell = row.insertCell(5);
                        let readabilityCell = row.insertCell(6);

                        let userAnchor = document.createElement('a');
                        userAnchor.setAttribute('href', "profilePage" + repository.owner_id);
                        userAnchor.innerHTML = repository.owner_id;
                        userCell.appendChild(userAnchor);

<!--                        let repoAnchor = document.createElement('a');-->
<!--                        repoAnchor.setAttribute('href', "/repo/" + repository.fullName);-->
<!--                        repoAnchor.innerHTML = repository.repoName;-->
<!--                        repoCell.appendChild(repoAnchor);-->

<!--                        let repoAnchor = document.createElement('a');-->
<!--                        repoAnchor.setAttribute('href', "/repo/" + repository.fullName);-->
<!--                        repoAnchor.innerHTML = repository.repoName;-->
<!--                        repoCell.appendChild(repoAnchor);-->

<!--                        let repoAnchor = document.createElement('a');-->
<!--                        repoAnchor.setAttribute('href', "/repo/" + repository.fullName);-->
<!--                        repoAnchor.innerHTML = repository.repoName;-->
<!--                        repoCell.appendChild(repoAnchor);-->

<!--                        let repoAnchor = document.createElement('a');-->
<!--                        repoAnchor.setAttribute('href', "/repo/" + repository.fullName);-->
<!--                        repoAnchor.innerHTML = repository.repoName;-->
<!--                        repoCell.appendChild(repoAnchor);-->

<!--                        let repoAnchor = document.createElement('a');-->
<!--                        repoAnchor.setAttribute('href', "/repo/" + repository.fullName);-->
<!--                        repoAnchor.innerHTML = repository.repoName;-->
<!--                        repoCell.appendChild(repoAnchor);-->

<!--                        let repoAnchor = document.createElement('a');-->
<!--                        repoAnchor.setAttribute('href', "/repo/" + repository.fullName);-->
<!--                        repoAnchor.innerHTML = repository.repoName;-->
<!--                        repoCell.appendChild(repoAnchor);-->

                        for (const topic of repository.topics) """),format.raw/*114.64*/("""{"""),format.raw/*114.65*/("""
                            """),format.raw/*115.29*/("""let topicAnchor = document.createElement('a');
                            topicAnchor.setAttribute('href', "/search?topic=" + topic);
                            topicAnchor.innerHTML = topic;
                            topicCell.appendChild(topicAnchor);
                        """),format.raw/*119.25*/("""}"""),format.raw/*119.26*/("""
                    """),format.raw/*120.21*/("""}"""),format.raw/*120.22*/("""

                    """),format.raw/*122.21*/("""function addHeader(keywords) """),format.raw/*122.50*/("""{"""),format.raw/*122.51*/("""
                        """),format.raw/*123.25*/("""let table = document.getElementById("result-table-body");
                        let row = table.insertRow(0);
                        let th = document.createElement('th');
                        th.setAttribute('colspan', 3);
                        th.setAttribute('class', 'keyword-header')
                        th.innerText = keywords;
                        row.appendChild(th);
                    """),format.raw/*130.21*/("""}"""),format.raw/*130.22*/("""
                """),format.raw/*131.17*/("""</script>


<!--            """),_display_(/*134.18*/for((phrase, projectModal : SearchResultModel ) <- searchMap) yield /*134.79*/ {_display_(Seq[Any](format.raw/*134.81*/("""-->
            <dl class="row" style="margin-top: 50px;">
                <dt class="col-sm-3"><h4>Search term : </h4></dt>
                <dd class="col-sm-3"><h4>"""),_display_(/*137.43*/phrase),format.raw/*137.49*/("""</h4></dd>
                <dd class="col-sm-6"><h4><a href="/"""),_display_(/*138.53*/phrase),format.raw/*138.59*/("""/wordStatsGlobal" target="_blank">Global Stats</a></h4></dd>
                <dd class="col-sm-3"><h4>FKCL : """),_display_(/*139.50*/projectModal/*139.62*/.getfleschReadabilityIndex()),format.raw/*139.90*/("""</h4></dd>
                <dd class="col-sm-3"><h4>FKGL : """),_display_(/*140.50*/projectModal/*140.62*/.getfleschKincaidGradeLevel()),format.raw/*140.91*/("""</h4></dd>
            </dl>

        <div class="table-responsive m-4">
            <table class="table table-bordered">
                <thead>
                <tr class="table-info">
                    <th scope="col">Owner ID</th>-->
                    <th scope="col">Submission Date</th>
                    <th scope="col">Title</th>
                    <th scope="col">Type</th>
                    <th scope="col">Word Stats</th>
                    <th scope="col">Skills</th>
                    <th scope="col">Readability</th>
                </tr>
                </thead>
                <tbody id="result-table-body">
                </tbody>
            </table>
        </div>

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
                """),_display_(/*174.18*/for( project <- projectModal.getprojectDetails().slice(0, 10) ) yield /*174.81*/ {_display_(Seq[Any](format.raw/*174.83*/("""
                """),format.raw/*175.17*/("""<tr>
                    <td>
                        <p><a href="profilePage/"""),_display_(/*177.50*/project/*177.57*/.getOwnerID()),format.raw/*177.70*/("""" target="_blank">"""),_display_(/*177.89*/project/*177.96*/.getOwnerID()),format.raw/*177.109*/("""</a></p>
                    </td>
                    <td>
                        <p>"""),_display_(/*180.29*/project/*180.36*/.getTimeSubmitted().format("MMMM dd, yyyy")),format.raw/*180.79*/("""</p>
                    </td>
                    <td>
                        <p>"""),_display_(/*183.29*/project/*183.36*/.getTitle()),format.raw/*183.47*/("""</p>
                    </td>
                    <td>
                        <p>"""),_display_(/*186.29*/project/*186.36*/.getType()),format.raw/*186.46*/("""</p>
                    </td>
                    <td>
                        <p><a href="/"""),_display_(/*189.39*/phrase),format.raw/*189.45*/("""/wordStats/"""),_display_(/*189.57*/project/*189.64*/.getProjectID()),format.raw/*189.79*/("""" target="_blank">Stats</a></p>
                    </td>
                    <td>
                    """),_display_(/*192.22*/for(skill<-project.getSkills()) yield /*192.53*/{_display_(Seq[Any](format.raw/*192.54*/("""
                    """),format.raw/*193.21*/("""<a href="/projectBySkills/"""),_display_(/*193.48*/skill(0)),format.raw/*193.56*/("""" target="_blank">"""),_display_(/*193.75*/skill(1)),format.raw/*193.83*/(""" """),format.raw/*193.84*/(""",</a>
                    """)))}),format.raw/*194.22*/("""
                    """),format.raw/*195.21*/("""</td>
                    <td>
                        <p>"""),_display_(/*197.29*/project/*197.36*/.getReadability()),format.raw/*197.53*/("""</p>
                    </td>
                </tr>
                """)))}),format.raw/*200.18*/("""
                """),format.raw/*201.17*/("""</tbody>
            </table>
            """)))}),format.raw/*203.14*/("""
        """)))}),format.raw/*204.10*/("""
    """),format.raw/*205.5*/("""</div>
</div>
""")))}),format.raw/*207.2*/("""

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
                  HASH: e4b4553e15acc8d635c777e32b55cf27b727c253
                  MATRIX: 610->1|646->32|1028->68|1200->145|1230->150|1261->173|1300->175|1328->177|1920->742|1951->764|1991->766|2028->776|2179->899|2208->900|2237->901|2356->992|2385->993|2440->1020|2567->1119|2596->1120|2680->1176|2709->1177|2765->1205|3109->1521|3138->1522|3200->1556|3267->1595|3296->1596|3362->1634|3453->1697|3482->1698|3540->1728|3569->1729|3627->1759|3660->1764|3689->1765|3747->1795|3814->1834|3843->1835|3905->1869|3976->1912|4005->1913|4071->1951|4162->2014|4191->2015|4249->2045|4278->2046|4334->2074|4363->2075|4414->2098|4443->2099|4495->2123|4628->2228|4657->2229|4711->2255|4911->2427|4940->2428|4998->2458|5116->2548|5145->2549|5252->2628|5281->2629|5333->2653|5399->2691|5428->2692|5482->2718|8368->5575|8398->5576|8457->5606|8772->5892|8802->5893|8853->5915|8883->5916|8936->5940|8994->5969|9024->5970|9079->5996|9526->6414|9556->6415|9603->6433|9663->6465|9741->6526|9782->6528|9980->6698|10008->6704|10100->6768|10128->6774|10267->6885|10289->6897|10339->6925|10428->6986|10450->6998|10501->7027|11764->8262|11844->8325|11885->8327|11932->8345|12041->8426|12058->8433|12093->8446|12140->8465|12157->8472|12193->8485|12312->8576|12329->8583|12394->8626|12509->8713|12526->8720|12559->8731|12674->8818|12691->8825|12723->8835|12848->8932|12876->8938|12916->8950|12933->8957|12970->8972|13105->9079|13153->9110|13193->9111|13244->9133|13299->9160|13329->9168|13376->9187|13406->9195|13436->9196|13496->9224|13547->9246|13636->9307|13653->9314|13692->9331|13797->9404|13844->9422|13921->9467|13964->9478|13998->9484|14046->9501
                  LINES: 23->1|24->2|29->4|34->4|36->6|36->6|36->6|37->7|50->20|50->20|50->20|51->21|54->24|54->24|54->24|57->27|57->27|58->28|60->30|60->30|62->32|62->32|63->33|68->38|68->38|69->39|69->39|69->39|70->40|71->41|71->41|72->42|72->42|73->43|73->43|73->43|74->44|74->44|74->44|75->45|75->45|75->45|76->46|77->47|77->47|78->48|78->48|79->49|79->49|80->50|80->50|82->52|84->54|84->54|85->55|89->59|89->59|90->60|92->62|92->62|95->65|95->65|97->67|97->67|97->67|98->68|144->114|144->114|145->115|149->119|149->119|150->120|150->120|152->122|152->122|152->122|153->123|160->130|160->130|161->131|164->134|164->134|164->134|167->137|167->137|168->138|168->138|169->139|169->139|169->139|170->140|170->140|170->140|204->174|204->174|204->174|205->175|207->177|207->177|207->177|207->177|207->177|207->177|210->180|210->180|210->180|213->183|213->183|213->183|216->186|216->186|216->186|219->189|219->189|219->189|219->189|219->189|222->192|222->192|222->192|223->193|223->193|223->193|223->193|223->193|223->193|224->194|225->195|227->197|227->197|227->197|230->200|231->201|233->203|234->204|235->205|237->207
                  -- GENERATED --
              */
          