
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
                            for (const project of projects) """),format.raw/*47.61*/("""{"""),format.raw/*47.62*/("""
                                """),format.raw/*48.33*/("""if (jsonStorage[project.id]) """),format.raw/*48.62*/("""{"""),format.raw/*48.63*/("""
                                    """),format.raw/*49.37*/("""addNewRow(table, project);
                                """),format.raw/*50.33*/("""}"""),format.raw/*50.34*/("""
                            """),format.raw/*51.29*/("""}"""),format.raw/*51.30*/("""
                     """),format.raw/*52.22*/("""}"""),format.raw/*52.23*/("""

                    """),format.raw/*54.21*/("""let keywordsInput = document.getElementById('myInput');

                    function searchKeywords() """),format.raw/*56.47*/("""{"""),format.raw/*56.48*/("""
                        """),format.raw/*57.25*/("""ws.send(JSON.stringify("""),format.raw/*57.48*/("""{"""),format.raw/*57.49*/("""
                            """),format.raw/*58.29*/("""type: "searchTerms",
                            data: keywords
                        """),format.raw/*60.25*/("""}"""),format.raw/*60.26*/("""));

                        recentKeywords = keywords;
                    """),format.raw/*63.21*/("""}"""),format.raw/*63.22*/("""

                    """),format.raw/*65.21*/("""function addNewRow(table, repository) """),format.raw/*65.59*/("""{"""),format.raw/*65.60*/("""
                        """),format.raw/*66.25*/("""jsonStorage[repository.id] = repository;
                        let row = table.insertRow(0);
                        let ownerCell = row.insertCell(0);
                        let subDateCell = row.insertCell(1);
                        let titleCell = row.insertCell(2);
                        let typeCell = row.insertCell(3);
                        let wordStatsCell = row.insertCell(4);
                        let skillsCell = row.insertCell(5);
                        let fleschRI = row.insertCell(6);
                        let fleschKG = row.insertCell(7);
                        let readabilityCell = row.insertCell(8);

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

                        for (const topic of repository.jobs) """),format.raw/*103.62*/("""{"""),format.raw/*103.63*/("""
                            """),format.raw/*104.29*/("""let topicAnchor = document.createElement('a');
                            topicAnchor.setAttribute('href', "/projectBySkills/" + topic.id + "/"+ topic.name);
                            topicAnchor.setAttribute("target", "_blank");
                            topicAnchor.innerHTML = topic.name + " ,";
                            skillsCell.appendChild(topicAnchor);
                        """),format.raw/*109.25*/("""}"""),format.raw/*109.26*/("""

                        """),format.raw/*111.25*/("""repoAnchor = document.createElement('p');
                        repoAnchor.innerText = fleschReadingEase(repository.preview_description);
                        fleschRI.appendChild(repoAnchor);

                        repoAnchor = document.createElement('p');
                        repoAnchor.innerText = fleschKincaidGrade(repository.preview_description);
                        fleschKG.appendChild(repoAnchor);

                        repoAnchor = document.createElement('p');
                        repoAnchor.innerText = "Early";
                        readabilityCell.appendChild(repoAnchor);

                    """),format.raw/*123.21*/("""}"""),format.raw/*123.22*/("""

                    """),format.raw/*125.21*/("""function addHeader(keywords) """),format.raw/*125.50*/("""{"""),format.raw/*125.51*/("""
                        """),format.raw/*126.25*/("""let table = document.getElementById("result-table-body");
                        let row = table.insertRow(0);
                        let th = document.createElement('th');
                        th.setAttribute('colspan', 3);
                        th.setAttribute('class', 'keyword-header')
                        th.innerText = keywords;
                        row.appendChild(th);
                    """),format.raw/*133.21*/("""}"""),format.raw/*133.22*/("""

                    """),format.raw/*135.21*/("""window.addEventListener('beforeunload', function (e) """),format.raw/*135.74*/("""{"""),format.raw/*135.75*/("""
                        """),format.raw/*136.25*/("""ws.close();
                    """),format.raw/*137.21*/("""}"""),format.raw/*137.22*/(""");

                    var sentenceRegex = new RegExp('[.?!]\\s[^a-z]', 'g');
                    var syllableRegex = new RegExp('[aiouy]+e*|e(?!d$|ly).|[td]ed|le$', 'g');

                    var fleschReadingEase(text) """),format.raw/*142.49*/("""{"""),format.raw/*142.50*/("""
                      """),format.raw/*143.23*/("""var sentenceLength = avgSentenceLength(text);
                      var syllablesPerWord = avgSyllablesPerWord(text);
                      return legacyRound(
                        freBase.base -
                        freBase.sentenceLength * sentenceLength -
                        freBase.syllablesPerWord * syllablesPerWord,
                        2
                      );
                    """),format.raw/*151.21*/("""}"""),format.raw/*151.22*/(""";

                    var fleschKincaidGrade(text)"""),format.raw/*153.49*/("""{"""),format.raw/*153.50*/("""
                      """),format.raw/*154.23*/("""var sentenceLength = avgSentenceLength(text);
                      var syllablesPerWord = avgSyllablesPerWord(text);
                      return legacyRound(
                        0.39 * sentenceLength +
                        11.8 * syllablesPerWord -
                        15.59,
                        2
                      );
                    """),format.raw/*162.21*/("""}"""),format.raw/*162.22*/(""";


                </script>


            """),_display_(/*168.14*/for((phrase, projectModal : SearchResultModel ) <- searchMap) yield /*168.75*/ {_display_(Seq[Any](format.raw/*168.77*/("""
            """),format.raw/*169.13*/("""<dl class="row" style="margin-top: 50px;">
                <dt class="col-sm-3"><h4>Search term : </h4></dt>
                <dd class="col-sm-3"><h4>"""),_display_(/*171.43*/phrase),format.raw/*171.49*/("""</h4></dd>
                <dd class="col-sm-6"><h4><a href="/"""),_display_(/*172.53*/phrase),format.raw/*172.59*/("""/wordStatsGlobal" target="_blank">Global Stats</a></h4></dd>
                <dd class="col-sm-3"><h4>FKCL : """),_display_(/*173.50*/projectModal/*173.62*/.getfleschReadabilityIndex()),format.raw/*173.90*/("""</h4></dd>
                <dd class="col-sm-3"><h4>FKGL : """),_display_(/*174.50*/projectModal/*174.62*/.getfleschKincaidGradeLevel()),format.raw/*174.91*/("""</h4></dd>
            </dl>

            <table border="1" class="table" id=""""),_display_(/*177.50*/phrase),format.raw/*177.56*/("""">
                <thead class="thead-light">
                    <tr>
                        <th>Owner ID</th>
                        <th>Submission Date</th>
                        <th>Title</th>
                        <th>Type</th>
                        <th>Word Stats</th>
                        <th>Skills</th>
                        <th>FKCL</th>
                        <th>FKGL</th>
                        <th>Readability</th>
                    </tr>
                </thead>
                <tbody>
                """),_display_(/*192.18*/for( project <- projectModal.getprojectDetails().slice(0, 10) ) yield /*192.81*/ {_display_(Seq[Any](format.raw/*192.83*/("""
                """),format.raw/*193.17*/("""<tr>
                    <td>
                        <p><a href="profilePage/"""),_display_(/*195.50*/project/*195.57*/.getOwnerID()),format.raw/*195.70*/("""" target="_blank">"""),_display_(/*195.89*/project/*195.96*/.getOwnerID()),format.raw/*195.109*/("""</a></p>
                    </td>
                    <td>
                        <p>"""),_display_(/*198.29*/project/*198.36*/.getTimeSubmitted().format("MMMM dd, yyyy")),format.raw/*198.79*/("""</p>
                    </td>
                    <td>
                        <p>"""),_display_(/*201.29*/project/*201.36*/.getTitle()),format.raw/*201.47*/("""</p>
                    </td>
                    <td>
                        <p>"""),_display_(/*204.29*/project/*204.36*/.getType()),format.raw/*204.46*/("""</p>
                    </td>
                    <td>
                        <p><a href="/"""),_display_(/*207.39*/phrase),format.raw/*207.45*/("""/wordStats/"""),_display_(/*207.57*/project/*207.64*/.getProjectID()),format.raw/*207.79*/("""" target="_blank">Stats</a></p>
                    </td>
                    <td>
                    """),_display_(/*210.22*/for(skill<-project.getSkills()) yield /*210.53*/{_display_(Seq[Any](format.raw/*210.54*/("""
                    """),format.raw/*211.21*/("""<a href="/projectBySkills/"""),_display_(/*211.48*/skill(0)),format.raw/*211.56*/("""" target="_blank">"""),_display_(/*211.75*/skill(1)),format.raw/*211.83*/(""" """),format.raw/*211.84*/(""",</a>
                    """)))}),format.raw/*212.22*/("""
                    """),format.raw/*213.21*/("""</td>
                    <td>
                        <p>"""),_display_(/*215.29*/project/*215.36*/.getFleschReadabilityIndex()),format.raw/*215.64*/("""</p>
                    </td>
                    <td>
                        <p>"""),_display_(/*218.29*/project/*218.36*/.getFleschKincaidGradeLevel()),format.raw/*218.65*/("""</p>
                    </td>
                    <td>
                        <p>"""),_display_(/*221.29*/project/*221.36*/.getReadability()),format.raw/*221.53*/("""</p>
                    </td>
                </tr>
                """)))}),format.raw/*224.18*/("""
                """),format.raw/*225.17*/("""</tbody>
            </table>
            """)))}),format.raw/*227.14*/("""
        """)))}),format.raw/*228.10*/("""
    """),format.raw/*229.5*/("""</div>
</div>
""")))}),format.raw/*231.2*/("""

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
                  HASH: ef0a4f7c0c5c70c282c25089a6f2098591d90e6b
                  MATRIX: 610->1|646->31|1028->65|1199->143|1226->145|1257->168|1296->170|1323->171|1902->723|1933->745|1973->747|2009->756|2157->876|2186->877|2215->878|2469->1104|2498->1105|2552->1131|2796->1347|2825->1348|2882->1377|2995->1462|3024->1463|3074->1485|3103->1486|3185->1540|3214->1541|3269->1568|3610->1881|3639->1882|3700->1915|3757->1944|3786->1945|3851->1982|3938->2041|3967->2042|4024->2071|4053->2072|4103->2094|4132->2095|4182->2117|4313->2220|4342->2221|4395->2246|4446->2269|4475->2270|4532->2299|4648->2387|4677->2388|4781->2464|4810->2465|4860->2487|4926->2525|4955->2526|5008->2551|7104->4618|7134->4619|7192->4648|7614->5041|7644->5042|7699->5068|8359->5699|8389->5700|8440->5722|8498->5751|8528->5752|8582->5777|9022->6188|9052->6189|9103->6211|9185->6264|9215->6265|9269->6290|9330->6322|9360->6323|9611->6545|9641->6546|9693->6569|10127->6974|10157->6975|10237->7026|10267->7027|10319->7050|10708->7410|10738->7411|10811->7456|10889->7517|10930->7519|10972->7532|11151->7683|11179->7689|11270->7752|11298->7758|11436->7868|11458->7880|11508->7908|11596->7968|11618->7980|11669->8009|11776->8088|11804->8094|12369->8631|12449->8694|12490->8696|12536->8713|12643->8792|12660->8799|12695->8812|12742->8831|12759->8838|12795->8851|12911->8939|12928->8946|12993->8989|13105->9073|13122->9080|13155->9091|13267->9175|13284->9182|13316->9192|13438->9286|13466->9292|13506->9304|13523->9311|13560->9326|13692->9430|13740->9461|13780->9462|13830->9483|13885->9510|13915->9518|13962->9537|13992->9545|14022->9546|14081->9573|14131->9594|14218->9653|14235->9660|14285->9688|14397->9772|14414->9779|14465->9808|14577->9892|14594->9899|14633->9916|14735->9986|14781->10003|14856->10046|14898->10056|14931->10061|14977->10076
                  LINES: 23->1|24->2|29->4|34->5|35->6|35->6|35->6|36->7|49->20|49->20|49->20|50->21|53->24|53->24|53->24|59->30|59->30|60->31|63->34|63->34|64->35|65->36|65->36|66->37|66->37|68->39|68->39|69->40|76->47|76->47|77->48|77->48|77->48|78->49|79->50|79->50|80->51|80->51|81->52|81->52|83->54|85->56|85->56|86->57|86->57|86->57|87->58|89->60|89->60|92->63|92->63|94->65|94->65|94->65|95->66|132->103|132->103|133->104|138->109|138->109|140->111|152->123|152->123|154->125|154->125|154->125|155->126|162->133|162->133|164->135|164->135|164->135|165->136|166->137|166->137|171->142|171->142|172->143|180->151|180->151|182->153|182->153|183->154|191->162|191->162|197->168|197->168|197->168|198->169|200->171|200->171|201->172|201->172|202->173|202->173|202->173|203->174|203->174|203->174|206->177|206->177|221->192|221->192|221->192|222->193|224->195|224->195|224->195|224->195|224->195|224->195|227->198|227->198|227->198|230->201|230->201|230->201|233->204|233->204|233->204|236->207|236->207|236->207|236->207|236->207|239->210|239->210|239->210|240->211|240->211|240->211|240->211|240->211|240->211|241->212|242->213|244->215|244->215|244->215|247->218|247->218|247->218|250->221|250->221|250->221|253->224|254->225|256->227|257->228|258->229|260->231
                  -- GENERATED --
              */
          