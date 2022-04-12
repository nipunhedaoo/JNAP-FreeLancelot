// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:6
  HomeController_0: controllers.HomeController,
  // @LINE:14
  Assets_1: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    HomeController_0: controllers.HomeController,
    // @LINE:14
    Assets_1: controllers.Assets
  ) = this(errorHandler, HomeController_0, Assets_1, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_0, Assets_1, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.HomeController.index(request:Request, searchKeyword:String ?= "")"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """profilePage/""" + "$" + """ownerId<[^/]+>""", """controllers.HomeController.profilePage(ownerId:String, request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """""" + "$" + """query<[^/]+>/wordStats/""" + "$" + """id<[^/]+>""", """controllers.HomeController.wordStats(query:String, id:Long, request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """projectBySkills/""" + "$" + """skillId<[^/]+>/""" + "$" + """skillName<[^/]+>""", """controllers.HomeController.searchBySkill(skillId:String, skillName:String, request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """ws""", """controllers.HomeController.socket"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:6
  private[this] lazy val controllers_HomeController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_HomeController_index0_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_0.index(fakeValue[play.mvc.Http.Request], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "index",
      Seq(classOf[play.mvc.Http.Request], classOf[String]),
      "GET",
      this.prefix + """""",
      """ An example controller showing a sample home page""",
      Seq()
    )
  )

  // @LINE:7
  private[this] lazy val controllers_HomeController_profilePage1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("profilePage/"), DynamicPart("ownerId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_HomeController_profilePage1_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_0.profilePage(fakeValue[String], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "profilePage",
      Seq(classOf[String], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """profilePage/""" + "$" + """ownerId<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:8
  private[this] lazy val controllers_HomeController_wordStats2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), DynamicPart("query", """[^/]+""",true), StaticPart("/wordStats/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_HomeController_wordStats2_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_0.wordStats(fakeValue[String], fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "wordStats",
      Seq(classOf[String], classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """""" + "$" + """query<[^/]+>/wordStats/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:9
  private[this] lazy val controllers_HomeController_searchBySkill3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("projectBySkills/"), DynamicPart("skillId", """[^/]+""",true), StaticPart("/"), DynamicPart("skillName", """[^/]+""",true)))
  )
  private[this] lazy val controllers_HomeController_searchBySkill3_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_0.searchBySkill(fakeValue[String], fakeValue[String], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "searchBySkill",
      Seq(classOf[String], classOf[String], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """projectBySkills/""" + "$" + """skillId<[^/]+>/""" + "$" + """skillName<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:10
  private[this] lazy val controllers_HomeController_socket4_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("ws")))
  )
  private[this] lazy val controllers_HomeController_socket4_invoker = createInvoker(
    HomeController_0.socket,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "socket",
      Nil,
      "GET",
      this.prefix + """ws""",
      """""",
      Seq()
    )
  )

  // @LINE:14
  private[this] lazy val controllers_Assets_versioned5_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned5_invoker = createInvoker(
    Assets_1.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:6
    case controllers_HomeController_index0_route(params@_) =>
      call(params.fromQuery[String]("searchKeyword", Some(""))) { (searchKeyword) =>
        controllers_HomeController_index0_invoker.call(
          req => HomeController_0.index(req, searchKeyword))
      }
  
    // @LINE:7
    case controllers_HomeController_profilePage1_route(params@_) =>
      call(params.fromPath[String]("ownerId", None)) { (ownerId) =>
        controllers_HomeController_profilePage1_invoker.call(
          req => HomeController_0.profilePage(ownerId, req))
      }
  
    // @LINE:8
    case controllers_HomeController_wordStats2_route(params@_) =>
      call(params.fromPath[String]("query", None), params.fromPath[Long]("id", None)) { (query, id) =>
        controllers_HomeController_wordStats2_invoker.call(
          req => HomeController_0.wordStats(query, id, req))
      }
  
    // @LINE:9
    case controllers_HomeController_searchBySkill3_route(params@_) =>
      call(params.fromPath[String]("skillId", None), params.fromPath[String]("skillName", None)) { (skillId, skillName) =>
        controllers_HomeController_searchBySkill3_invoker.call(
          req => HomeController_0.searchBySkill(skillId, skillName, req))
      }
  
    // @LINE:10
    case controllers_HomeController_socket4_route(params@_) =>
      call { 
        controllers_HomeController_socket4_invoker.call(HomeController_0.socket)
      }
  
    // @LINE:14
    case controllers_Assets_versioned5_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned5_invoker.call(Assets_1.versioned(path, file))
      }
  }
}
