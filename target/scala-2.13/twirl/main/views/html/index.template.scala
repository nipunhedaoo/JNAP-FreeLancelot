
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
                     var url = new URL(window.location.href);

                     let keywords = url.searchParams.get("searchKeyword");

                     ws.onopen = () => """),format.raw/*30.40*/("""{"""),format.raw/*30.41*/("""
                         """),format.raw/*31.26*/("""console.log('WebSocket opened');
                         searchKeywords();
                         let table = document.getElementById(keywords);
                         for(var i = 1; i < table.rows.length ; i++)"""),format.raw/*34.69*/("""{"""),format.raw/*34.70*/("""
                            """),format.raw/*35.29*/("""jsonStorage[table.rows[i].cells[0].innerText] = table.rows;
                         """),format.raw/*36.26*/("""}"""),format.raw/*36.27*/("""
                     """),format.raw/*37.22*/("""}"""),format.raw/*37.23*/(""";

                     ws.onmessage = function (msg) """),format.raw/*39.52*/("""{"""),format.raw/*39.53*/("""
                          """),format.raw/*40.27*/("""console.log("WebSocket message received");
                          let data = JSON.parse(msg.data);
                          let projects = data.result.projects.slice(0,10);

                          let table = document.getElementById(keywords);
                            for (const project of projects) """),format.raw/*45.61*/("""{"""),format.raw/*45.62*/("""
                                """),format.raw/*46.33*/("""if (jsonStorage[project.id]) """),format.raw/*46.62*/("""{"""),format.raw/*46.63*/("""
                                    """),format.raw/*47.37*/("""addNewRow(table, project);
                                """),format.raw/*48.33*/("""}"""),format.raw/*48.34*/("""
"""),format.raw/*49.1*/("""<!--                                if (!(project.id in jsonStorage)) """),format.raw/*49.71*/("""{"""),format.raw/*49.72*/("""-->
<!--                                    addNewRow(table, project);-->
<!--                                """),format.raw/*51.37*/("""}"""),format.raw/*51.38*/("""-->
                            """),format.raw/*52.29*/("""}"""),format.raw/*52.30*/("""

                     """),format.raw/*54.22*/("""}"""),format.raw/*54.23*/("""

                    """),format.raw/*56.21*/("""let keywordsInput = document.getElementById('myInput');

                    function searchKeywords() """),format.raw/*58.47*/("""{"""),format.raw/*58.48*/("""

                        """),format.raw/*60.25*/("""ws.send(JSON.stringify("""),format.raw/*60.48*/("""{"""),format.raw/*60.49*/("""
                            """),format.raw/*61.29*/("""type: "searchTerms",
                            data: keywords
                        """),format.raw/*63.25*/("""}"""),format.raw/*63.26*/("""));

                        recentKeywords = keywords;
                    """),format.raw/*66.21*/("""}"""),format.raw/*66.22*/("""

                    """),format.raw/*68.21*/("""function addNewRow(table, repository) """),format.raw/*68.59*/("""{"""),format.raw/*68.60*/("""
                        """),format.raw/*69.25*/("""jsonStorage[repository.id] = repository;
                        let row = table.insertRow(0);
                        let ownerCell = row.insertCell(0);
                        let subDateCell = row.insertCell(1);
                        let titleCell = row.insertCell(2);
                        let typeCell = row.insertCell(3);
                        let wordStatsCell = row.insertCell(4);
                        let skillsCell = row.insertCell(5);
                        let readabilityCell = row.insertCell(6);

                        let userAnchor = document.createElement('a');
                        userAnchor.setAttribute('href', "/profilePage/" + repository.owner_id);
                        userAnchor.setAttribute("target", "_blank");
                        userAnchor.innerHTML = repository.owner_id;
                        ownerCell.appendChild(userAnchor);

                        let repoAnchor = document.createElement('p');
                        repoAnchor.innerText = repository.submitdate;
                        subDateCell.appendChild(repoAnchor);

                        repoAnchor = document.createElement('p');
                        repoAnchor.innerText = repository.title;
                        titleCell.appendChild(repoAnchor);

                        repoAnchor = document.createElement('p');
                        repoAnchor.innerText = repository.type;
                        typeCell.appendChild(repoAnchor);

                        repoAnchor = document.createElement('a');
                        repoAnchor.setAttribute('href', keywords  + "/wordStats/" + repository.id);
                        repoAnchor.setAttribute("target", "_blank");
                        repoAnchor.innerHTML = repository.wordStats;
                        repoAnchor.innerText = "Stats";
                        wordStatsCell.appendChild(repoAnchor);

                        for (const topic of repository.jobs) """),format.raw/*104.62*/("""{"""),format.raw/*104.63*/("""
                            """),format.raw/*105.29*/("""let topicAnchor = document.createElement('a');
                            topicAnchor.setAttribute('href', "/projectBySkills/" + topic.id + "/"+ topic.name);
                            topicAnchor.setAttribute("target", "_blank");
                            topicAnchor.innerHTML = topic.name + " ,";
                            skillsCell.appendChild(topicAnchor);
                        """),format.raw/*110.25*/("""}"""),format.raw/*110.26*/("""

                        """),format.raw/*112.25*/("""repoAnchor = document.createElement('p');
                        repoAnchor.innerText = "Early";
                        readabilityCell.appendChild(repoAnchor);

                    """),format.raw/*116.21*/("""}"""),format.raw/*116.22*/("""

                    """),format.raw/*118.21*/("""function addHeader(keywords) """),format.raw/*118.50*/("""{"""),format.raw/*118.51*/("""
                        """),format.raw/*119.25*/("""let table = document.getElementById("result-table-body");
                        let row = table.insertRow(0);
                        let th = document.createElement('th');
                        th.setAttribute('colspan', 3);
                        th.setAttribute('class', 'keyword-header')
                        th.innerText = keywords;
                        row.appendChild(th);
                    """),format.raw/*126.21*/("""}"""),format.raw/*126.22*/("""

                    """),format.raw/*128.21*/("""window.addEventListener('beforeunload', function (e) """),format.raw/*128.74*/("""{"""),format.raw/*128.75*/("""
                        """),format.raw/*129.25*/("""ws.close();
                    """),format.raw/*130.21*/("""}"""),format.raw/*130.22*/(""");
                </script>


            """),_display_(/*134.14*/for((phrase, projectModal : SearchResultModel ) <- searchMap) yield /*134.75*/ {_display_(Seq[Any](format.raw/*134.77*/("""
            """),format.raw/*135.13*/("""<dl class="row" style="margin-top: 50px;">
                <dt class="col-sm-3"><h4>Search term : </h4></dt>
                <dd class="col-sm-3"><h4>"""),_display_(/*137.43*/phrase),format.raw/*137.49*/("""</h4></dd>
                <dd class="col-sm-6"><h4><a href="/"""),_display_(/*138.53*/phrase),format.raw/*138.59*/("""/wordStatsGlobal" target="_blank">Global Stats</a></h4></dd>
                <dd class="col-sm-3"><h4>FKCL : """),_display_(/*139.50*/projectModal/*139.62*/.getfleschReadabilityIndex()),format.raw/*139.90*/("""</h4></dd>
                <dd class="col-sm-3"><h4>FKGL : """),_display_(/*140.50*/projectModal/*140.62*/.getfleschKincaidGradeLevel()),format.raw/*140.91*/("""</h4></dd>
            </dl>

            <table border="1" class="table" id=""""),_display_(/*143.50*/phrase),format.raw/*143.56*/("""">
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
                """),_display_(/*156.18*/for( project <- projectModal.getprojectDetails().slice(0, 10) ) yield /*156.81*/ {_display_(Seq[Any](format.raw/*156.83*/("""
                """),format.raw/*157.17*/("""<tr>
                    <td>
                        <p><a href="profilePage/"""),_display_(/*159.50*/project/*159.57*/.getOwnerID()),format.raw/*159.70*/("""" target="_blank">"""),_display_(/*159.89*/project/*159.96*/.getOwnerID()),format.raw/*159.109*/("""</a></p>
                    </td>
                    <td>
                        <p>"""),_display_(/*162.29*/project/*162.36*/.getTimeSubmitted().format("MMMM dd, yyyy")),format.raw/*162.79*/("""</p>
                    </td>
                    <td>
                        <p>"""),_display_(/*165.29*/project/*165.36*/.getTitle()),format.raw/*165.47*/("""</p>
                    </td>
                    <td>
                        <p>"""),_display_(/*168.29*/project/*168.36*/.getType()),format.raw/*168.46*/("""</p>
                    </td>
                    <td>
                        <p><a href="/"""),_display_(/*171.39*/phrase),format.raw/*171.45*/("""/wordStats/"""),_display_(/*171.57*/project/*171.64*/.getProjectID()),format.raw/*171.79*/("""" target="_blank">Stats</a></p>
                    </td>
                    <td>
                    """),_display_(/*174.22*/for(skill<-project.getSkills()) yield /*174.53*/{_display_(Seq[Any](format.raw/*174.54*/("""
                    """),format.raw/*175.21*/("""<a href="/projectBySkills/"""),_display_(/*175.48*/skill(0)),format.raw/*175.56*/("""" target="_blank">"""),_display_(/*175.75*/skill(1)),format.raw/*175.83*/(""" """),format.raw/*175.84*/(""",</a>
                    """)))}),format.raw/*176.22*/("""
                    """),format.raw/*177.21*/("""</td>
                    <td>
                        <p>"""),_display_(/*179.29*/project/*179.36*/.getReadability()),format.raw/*179.53*/("""</p>
                    </td>
                </tr>
                """)))}),format.raw/*182.18*/("""
                """),format.raw/*183.17*/("""</tbody>
            </table>
            """)))}),format.raw/*185.14*/("""
        """)))}),format.raw/*186.10*/("""
    """),format.raw/*187.5*/("""</div>
</div>
""")))}),format.raw/*189.2*/("""

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
                  HASH: e864d34e6cc105c915798cd9f3fc389eda032c0a
                  MATRIX: 610->1|646->32|1028->68|1200->145|1230->150|1261->173|1300->175|1328->177|1920->742|1951->764|1991->766|2028->776|2179->899|2208->900|2237->901|2497->1133|2526->1134|2581->1161|2828->1380|2857->1381|2915->1411|3029->1497|3058->1498|3109->1521|3138->1522|3222->1578|3251->1579|3307->1607|3651->1923|3680->1924|3742->1958|3799->1987|3828->1988|3894->2026|3982->2086|4011->2087|4040->2089|4138->2159|4167->2160|4307->2272|4336->2273|4397->2306|4426->2307|4479->2332|4508->2333|4560->2357|4693->2462|4722->2463|4778->2491|4829->2514|4858->2515|4916->2545|5034->2635|5063->2636|5170->2715|5199->2716|5251->2740|5317->2778|5346->2779|5400->2805|7415->4791|7445->4792|7504->4822|7931->5220|7961->5221|8018->5249|8235->5437|8265->5438|8318->5462|8376->5491|8406->5492|8461->5518|8908->5936|8938->5937|8991->5961|9073->6014|9103->6015|9158->6041|9220->6074|9250->6075|9326->6123|9404->6184|9445->6186|9488->6200|9669->6353|9697->6359|9789->6423|9817->6429|9956->6540|9978->6552|10028->6580|10117->6641|10139->6653|10190->6682|10300->6764|10328->6770|10830->7244|10910->7307|10951->7309|10998->7327|11107->7408|11124->7415|11159->7428|11206->7447|11223->7454|11259->7467|11378->7558|11395->7565|11460->7608|11575->7695|11592->7702|11625->7713|11740->7800|11757->7807|11789->7817|11914->7914|11942->7920|11982->7932|11999->7939|12036->7954|12171->8061|12219->8092|12259->8093|12310->8115|12365->8142|12395->8150|12442->8169|12472->8177|12502->8178|12562->8206|12613->8228|12702->8289|12719->8296|12758->8313|12863->8386|12910->8404|12987->8449|13030->8460|13064->8466|13112->8483
                  LINES: 23->1|24->2|29->4|34->4|36->6|36->6|36->6|37->7|50->20|50->20|50->20|51->21|54->24|54->24|54->24|60->30|60->30|61->31|64->34|64->34|65->35|66->36|66->36|67->37|67->37|69->39|69->39|70->40|75->45|75->45|76->46|76->46|76->46|77->47|78->48|78->48|79->49|79->49|79->49|81->51|81->51|82->52|82->52|84->54|84->54|86->56|88->58|88->58|90->60|90->60|90->60|91->61|93->63|93->63|96->66|96->66|98->68|98->68|98->68|99->69|134->104|134->104|135->105|140->110|140->110|142->112|146->116|146->116|148->118|148->118|148->118|149->119|156->126|156->126|158->128|158->128|158->128|159->129|160->130|160->130|164->134|164->134|164->134|165->135|167->137|167->137|168->138|168->138|169->139|169->139|169->139|170->140|170->140|170->140|173->143|173->143|186->156|186->156|186->156|187->157|189->159|189->159|189->159|189->159|189->159|189->159|192->162|192->162|192->162|195->165|195->165|195->165|198->168|198->168|198->168|201->171|201->171|201->171|201->171|201->171|204->174|204->174|204->174|205->175|205->175|205->175|205->175|205->175|205->175|206->176|207->177|209->179|209->179|209->179|212->182|213->183|215->185|216->186|217->187|219->189
                  -- GENERATED --
              */
          