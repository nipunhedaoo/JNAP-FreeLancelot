
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

                        """),format.raw/*111.25*/("""let repoAnchorFRE = document.createElement('p');
                        repoAnchorFRE.innerText = fleschReadingEase(repository.preview_description);
                        fleschRI.appendChild(repoAnchorFRE);

                        repoAnchor = document.createElement('p');
                        repoAnchor.innerText = fleschKincaidGrade(repository.preview_description);
                        fleschKG.appendChild(repoAnchor);

                        repoAnchor = document.createElement('p');
                        repoAnchor.innerText = educationLevel(repoAnchorFRE.innerText);
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

                    function getSyllables(word) """),format.raw/*139.49*/("""{"""),format.raw/*139.50*/("""
                      """),format.raw/*140.23*/("""word = word.toLowerCase();
                      if(word.length <= 3) """),format.raw/*141.44*/("""{"""),format.raw/*141.45*/(""" """),format.raw/*141.46*/("""return 1; """),format.raw/*141.56*/("""}"""),format.raw/*141.57*/("""
                      """),format.raw/*142.23*/("""word = word.replace(/(?:[^laeiouy]es|ed|[^laeiouy]e)$/, '');
                      word = word.replace(/^y/, '');
                      var syl = word.match(/[aeiouy]"""),format.raw/*144.53*/("""{"""),format.raw/*144.54*/("""1,2"""),format.raw/*144.57*/("""}"""),format.raw/*144.58*/("""/g);
                      return (syl && syl.length) || 0;
                    """),format.raw/*146.21*/("""}"""),format.raw/*146.22*/("""


                    """),format.raw/*149.21*/("""function fleschReadingEase(text) """),format.raw/*149.54*/("""{"""),format.raw/*149.55*/("""
                        """),format.raw/*150.25*/("""var numOfSentence = 0, numOfWords = 0, numOfSyllables = 0;

                        var sentences = text.split(/[\\.!\?]/);

                         numOfSentence = sentences.length;
                          sentences.forEach(function (sentence) """),format.raw/*155.65*/("""{"""),format.raw/*155.66*/("""
                            """),format.raw/*156.29*/("""var word = "";
                            for (var i = 0; i < sentence.length; i++) """),format.raw/*157.71*/("""{"""),format.raw/*157.72*/("""
                              """),format.raw/*158.31*/("""word += sentence[i];
                              if(sentence[i] == " ") """),format.raw/*159.54*/("""{"""),format.raw/*159.55*/("""
                                """),format.raw/*160.33*/("""numOfWords++;
                                numOfSyllables += getSyllables(word);
                                word = "";
                              """),format.raw/*163.31*/("""}"""),format.raw/*163.32*/("""
                            """),format.raw/*164.29*/("""}"""),format.raw/*164.30*/("""

                            """),format.raw/*166.29*/("""if(word.length > 0) """),format.raw/*166.49*/("""{"""),format.raw/*166.50*/("""
                              """),format.raw/*167.31*/("""numOfWords++;
                              numOfSyllables += getSyllables(word);
                              word = ""
                            """),format.raw/*170.29*/("""}"""),format.raw/*170.30*/("""
                          """),format.raw/*171.27*/("""}"""),format.raw/*171.28*/(""");

                      return (206.835 - (1.015 * (numOfWords/numOfSentence)) - (84.6 *(numOfSyllables/numOfWords)));
                    """),format.raw/*174.21*/("""}"""),format.raw/*174.22*/(""";

                    function fleschKincaidGrade(text)"""),format.raw/*176.54*/("""{"""),format.raw/*176.55*/("""
                        """),format.raw/*177.25*/("""var numOfSentence = 0, numOfWords = 0, numOfSyllables = 0;

                            var sentences = text.split(/[\\.!\?]/);

                             numOfSentence = sentences.length;
                              sentences.forEach(function (sentence) """),format.raw/*182.69*/("""{"""),format.raw/*182.70*/("""
                                """),format.raw/*183.33*/("""var word = "";
                                for (var i = 0; i < sentence.length; i++) """),format.raw/*184.75*/("""{"""),format.raw/*184.76*/("""
                                  """),format.raw/*185.35*/("""word += sentence[i];
                                  if(sentence[i] == " ") """),format.raw/*186.58*/("""{"""),format.raw/*186.59*/("""
                                    """),format.raw/*187.37*/("""numOfWords++;
                                    numOfSyllables += getSyllables(word);
                                    word = "";
                                  """),format.raw/*190.35*/("""}"""),format.raw/*190.36*/("""
                                """),format.raw/*191.33*/("""}"""),format.raw/*191.34*/("""

                                """),format.raw/*193.33*/("""if(word.length > 0) """),format.raw/*193.53*/("""{"""),format.raw/*193.54*/("""
                                  """),format.raw/*194.35*/("""numOfWords++;
                                  numOfSyllables += getSyllables(word);
                                  word = ""
                                """),format.raw/*197.33*/("""}"""),format.raw/*197.34*/("""
                              """),format.raw/*198.31*/("""}"""),format.raw/*198.32*/(""");
                      return ((0.39 * ( numOfWords / numOfSentence )) + 11.8 * ( numOfSyllables / numOfWords ) - 15.59);
                    """),format.raw/*200.21*/("""}"""),format.raw/*200.22*/(""";

                    function educationLevel(fkcl)"""),format.raw/*202.50*/("""{"""),format.raw/*202.51*/("""
                    """),format.raw/*203.21*/("""var educationalLevel = "";
                    if(fkcl > 100)"""),format.raw/*204.35*/("""{"""),format.raw/*204.36*/("""
                        """),format.raw/*205.25*/("""educationalLevel = "Early" ;
                    """),format.raw/*206.21*/("""}"""),format.raw/*206.22*/("""else if(fkcl > 91)"""),format.raw/*206.40*/("""{"""),format.raw/*206.41*/("""
                        """),format.raw/*207.25*/("""educationalLevel = "5th grade" ;
                    """),format.raw/*208.21*/("""}"""),format.raw/*208.22*/("""else if(fkcl > 81)"""),format.raw/*208.40*/("""{"""),format.raw/*208.41*/("""
                        """),format.raw/*209.25*/("""educationalLevel = "6th grade" ;
                    """),format.raw/*210.21*/("""}"""),format.raw/*210.22*/("""else if(fkcl > 71 )"""),format.raw/*210.41*/("""{"""),format.raw/*210.42*/("""
                        """),format.raw/*211.25*/("""educationalLevel = "7th grade" ;
                    """),format.raw/*212.21*/("""}"""),format.raw/*212.22*/("""else if(fkcl > 66 )"""),format.raw/*212.41*/("""{"""),format.raw/*212.42*/("""
                        """),format.raw/*213.25*/("""educationalLevel = "8th grade" ;
                    """),format.raw/*214.21*/("""}"""),format.raw/*214.22*/("""else if(fkcl > 61 )"""),format.raw/*214.41*/("""{"""),format.raw/*214.42*/("""
                        """),format.raw/*215.25*/("""educationalLevel = "9th grade" ;
                    """),format.raw/*216.21*/("""}"""),format.raw/*216.22*/("""else if(fkcl > 51 )"""),format.raw/*216.41*/("""{"""),format.raw/*216.42*/("""
                        """),format.raw/*217.25*/("""educationalLevel = "High School" ;
                    """),format.raw/*218.21*/("""}"""),format.raw/*218.22*/("""else if(fkcl > 31 )"""),format.raw/*218.41*/("""{"""),format.raw/*218.42*/("""
                        """),format.raw/*219.25*/("""educationalLevel = "Some College" ;
                    """),format.raw/*220.21*/("""}"""),format.raw/*220.22*/(""" """),format.raw/*220.23*/("""else if(fkcl > 0 )"""),format.raw/*220.41*/("""{"""),format.raw/*220.42*/("""
                        """),format.raw/*221.25*/("""educationalLevel = "College Graduate" ;
                    """),format.raw/*222.21*/("""}"""),format.raw/*222.22*/("""else"""),format.raw/*222.26*/("""{"""),format.raw/*222.27*/("""
                        """),format.raw/*223.25*/("""educationalLevel = "Law School Graduate" ;
                    """),format.raw/*224.21*/("""}"""),format.raw/*224.22*/("""

                        """),format.raw/*226.25*/("""return educationalLevel;
                    """),format.raw/*227.21*/("""}"""),format.raw/*227.22*/("""


                """),format.raw/*230.17*/("""</script>


            """),_display_(/*233.14*/for((phrase, projectModal : SearchResultModel ) <- searchMap) yield /*233.75*/ {_display_(Seq[Any](format.raw/*233.77*/("""
            """),format.raw/*234.13*/("""<dl class="row" style="margin-top: 50px;">
                <dt class="col-sm-3"><h4>Search term : </h4></dt>
                <dd class="col-sm-3"><h4>"""),_display_(/*236.43*/phrase),format.raw/*236.49*/("""</h4></dd>
                <dd class="col-sm-6"><h4><a href="/"""),_display_(/*237.53*/phrase),format.raw/*237.59*/("""/wordStatsGlobal" target="_blank">Global Stats</a></h4></dd>
                <dd class="col-sm-3"><h4>FKCL : """),_display_(/*238.50*/projectModal/*238.62*/.getfleschReadabilityIndex()),format.raw/*238.90*/("""</h4></dd>
                <dd class="col-sm-3"><h4>FKGL : """),_display_(/*239.50*/projectModal/*239.62*/.getfleschKincaidGradeLevel()),format.raw/*239.91*/("""</h4></dd>
            </dl>

            <table border="1" class="table" id=""""),_display_(/*242.50*/phrase),format.raw/*242.56*/("""">
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
                """),_display_(/*257.18*/for( project <- projectModal.getprojectDetails().slice(0, 10) ) yield /*257.81*/ {_display_(Seq[Any](format.raw/*257.83*/("""
                """),format.raw/*258.17*/("""<tr>
                    <td>
                        <p><a href="profilePage/"""),_display_(/*260.50*/project/*260.57*/.getOwnerID()),format.raw/*260.70*/("""" target="_blank">"""),_display_(/*260.89*/project/*260.96*/.getOwnerID()),format.raw/*260.109*/("""</a></p>
                    </td>
                    <td>
                        <p>"""),_display_(/*263.29*/project/*263.36*/.getTimeSubmitted().format("MMMM dd, yyyy")),format.raw/*263.79*/("""</p>
                    </td>
                    <td>
                        <p>"""),_display_(/*266.29*/project/*266.36*/.getTitle()),format.raw/*266.47*/("""</p>
                    </td>
                    <td>
                        <p>"""),_display_(/*269.29*/project/*269.36*/.getType()),format.raw/*269.46*/("""</p>
                    </td>
                    <td>
                        <p><a href="/"""),_display_(/*272.39*/phrase),format.raw/*272.45*/("""/wordStats/"""),_display_(/*272.57*/project/*272.64*/.getProjectID()),format.raw/*272.79*/("""" target="_blank">Stats</a></p>
                    </td>
                    <td>
                    """),_display_(/*275.22*/for(skill<-project.getSkills()) yield /*275.53*/{_display_(Seq[Any](format.raw/*275.54*/("""
                    """),format.raw/*276.21*/("""<a href="/projectBySkills/"""),_display_(/*276.48*/skill(0)),format.raw/*276.56*/("""" target="_blank">"""),_display_(/*276.75*/skill(1)),format.raw/*276.83*/(""" """),format.raw/*276.84*/(""",</a>
                    """)))}),format.raw/*277.22*/("""
                    """),format.raw/*278.21*/("""</td>
                    <td>
                        <p>"""),_display_(/*280.29*/project/*280.36*/.getFleschReadabilityIndex()),format.raw/*280.64*/("""</p>
                    </td>
                    <td>
                        <p>"""),_display_(/*283.29*/project/*283.36*/.getFleschKincaidGradeLevel()),format.raw/*283.65*/("""</p>
                    </td>
                    <td>
                        <p>"""),_display_(/*286.29*/project/*286.36*/.getReadability()),format.raw/*286.53*/("""</p>
                    </td>
                </tr>
                """)))}),format.raw/*289.18*/("""
                """),format.raw/*290.17*/("""</tbody>
            </table>
            """)))}),format.raw/*292.14*/("""
        """)))}),format.raw/*293.10*/("""
    """),format.raw/*294.5*/("""</div>
</div>
""")))}),format.raw/*296.2*/("""

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
                  HASH: 3e7f550e4c04b8ecc4ff74fcdf723d510ed9afc4
                  MATRIX: 610->1|646->32|1028->68|1200->145|1230->150|1261->173|1300->175|1328->177|1920->742|1951->764|1991->766|2028->776|2179->899|2208->900|2237->901|2497->1133|2526->1134|2581->1161|2828->1380|2857->1381|2915->1411|3029->1497|3058->1498|3109->1521|3138->1522|3222->1578|3251->1579|3307->1607|3655->1927|3684->1928|3746->1962|3803->1991|3832->1992|3898->2030|3986->2090|4015->2091|4073->2121|4102->2122|4153->2145|4182->2146|4234->2170|4367->2275|4396->2276|4450->2302|4501->2325|4530->2326|4588->2356|4706->2446|4735->2447|4842->2526|4871->2527|4923->2551|4989->2589|5018->2590|5072->2616|7205->4720|7235->4721|7294->4751|7721->5149|7751->5150|7808->5178|8525->5866|8555->5867|8608->5891|8666->5920|8696->5921|8751->5947|9198->6365|9228->6366|9281->6390|9363->6443|9393->6444|9448->6470|9510->6503|9540->6504|9623->6558|9653->6559|9706->6583|9806->6654|9836->6655|9866->6656|9905->6666|9935->6667|9988->6691|10185->6859|10215->6860|10247->6863|10277->6864|10388->6946|10418->6947|10473->6973|10535->7006|10565->7007|10620->7033|10902->7286|10932->7287|10991->7317|11106->7403|11136->7404|11197->7436|11301->7511|11331->7512|11394->7546|11583->7706|11613->7707|11672->7737|11702->7738|11763->7770|11812->7790|11842->7791|11903->7823|12085->7976|12115->7977|12172->8005|12202->8006|12375->8150|12405->8151|12492->8209|12522->8210|12577->8236|12871->8501|12901->8502|12964->8536|13083->8626|13113->8627|13178->8663|13286->8742|13316->8743|13383->8781|13584->8953|13614->8954|13677->8988|13707->8989|13772->9025|13821->9045|13851->9046|13916->9082|14110->9247|14140->9248|14201->9280|14231->9281|14406->9427|14436->9428|14519->9482|14549->9483|14600->9505|14691->9567|14721->9568|14776->9594|14855->9644|14885->9645|14932->9663|14962->9664|15017->9690|15100->9744|15130->9745|15177->9763|15207->9764|15262->9790|15345->9844|15375->9845|15423->9864|15453->9865|15508->9891|15591->9945|15621->9946|15669->9965|15699->9966|15754->9992|15837->10046|15867->10047|15915->10066|15945->10067|16000->10093|16083->10147|16113->10148|16161->10167|16191->10168|16246->10194|16331->10250|16361->10251|16409->10270|16439->10271|16494->10297|16580->10354|16610->10355|16640->10356|16687->10374|16717->10375|16772->10401|16862->10462|16892->10463|16925->10467|16955->10468|17010->10494|17103->10558|17133->10559|17190->10587|17265->10633|17295->10634|17346->10656|17402->10684|17480->10745|17521->10747|17564->10761|17745->10914|17773->10920|17865->10984|17893->10990|18032->11101|18054->11113|18104->11141|18193->11202|18215->11214|18266->11243|18376->11325|18404->11331|18984->11883|19064->11946|19105->11948|19152->11966|19261->12047|19278->12054|19313->12067|19360->12086|19377->12093|19413->12106|19532->12197|19549->12204|19614->12247|19729->12334|19746->12341|19779->12352|19894->12439|19911->12446|19943->12456|20068->12553|20096->12559|20136->12571|20153->12578|20190->12593|20325->12700|20373->12731|20413->12732|20464->12754|20519->12781|20549->12789|20596->12808|20626->12816|20656->12817|20716->12845|20767->12867|20856->12928|20873->12935|20923->12963|21038->13050|21055->13057|21106->13086|21221->13173|21238->13180|21277->13197|21382->13270|21429->13288|21506->13333|21549->13344|21583->13350|21631->13367
                  LINES: 23->1|24->2|29->4|34->4|36->6|36->6|36->6|37->7|50->20|50->20|50->20|51->21|54->24|54->24|54->24|60->30|60->30|61->31|64->34|64->34|65->35|66->36|66->36|67->37|67->37|69->39|69->39|70->40|77->47|77->47|78->48|78->48|78->48|79->49|80->50|80->50|81->51|81->51|82->52|82->52|84->54|86->56|86->56|87->57|87->57|87->57|88->58|90->60|90->60|93->63|93->63|95->65|95->65|95->65|96->66|133->103|133->103|134->104|139->109|139->109|141->111|153->123|153->123|155->125|155->125|155->125|156->126|163->133|163->133|165->135|165->135|165->135|166->136|167->137|167->137|169->139|169->139|170->140|171->141|171->141|171->141|171->141|171->141|172->142|174->144|174->144|174->144|174->144|176->146|176->146|179->149|179->149|179->149|180->150|185->155|185->155|186->156|187->157|187->157|188->158|189->159|189->159|190->160|193->163|193->163|194->164|194->164|196->166|196->166|196->166|197->167|200->170|200->170|201->171|201->171|204->174|204->174|206->176|206->176|207->177|212->182|212->182|213->183|214->184|214->184|215->185|216->186|216->186|217->187|220->190|220->190|221->191|221->191|223->193|223->193|223->193|224->194|227->197|227->197|228->198|228->198|230->200|230->200|232->202|232->202|233->203|234->204|234->204|235->205|236->206|236->206|236->206|236->206|237->207|238->208|238->208|238->208|238->208|239->209|240->210|240->210|240->210|240->210|241->211|242->212|242->212|242->212|242->212|243->213|244->214|244->214|244->214|244->214|245->215|246->216|246->216|246->216|246->216|247->217|248->218|248->218|248->218|248->218|249->219|250->220|250->220|250->220|250->220|250->220|251->221|252->222|252->222|252->222|252->222|253->223|254->224|254->224|256->226|257->227|257->227|260->230|263->233|263->233|263->233|264->234|266->236|266->236|267->237|267->237|268->238|268->238|268->238|269->239|269->239|269->239|272->242|272->242|287->257|287->257|287->257|288->258|290->260|290->260|290->260|290->260|290->260|290->260|293->263|293->263|293->263|296->266|296->266|296->266|299->269|299->269|299->269|302->272|302->272|302->272|302->272|302->272|305->275|305->275|305->275|306->276|306->276|306->276|306->276|306->276|306->276|307->277|308->278|310->280|310->280|310->280|313->283|313->283|313->283|316->286|316->286|316->286|319->289|320->290|322->292|323->293|324->294|326->296
                  -- GENERATED --
              */
          