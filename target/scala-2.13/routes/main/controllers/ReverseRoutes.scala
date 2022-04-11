// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers {

  // @LINE:6
  class ReverseHomeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:8
    def wordStats(query:String, id:Long): Call = {
    
      (query: @unchecked, id: @unchecked) match {
      
        // @LINE:8
        case (query, id)  =>
          
          Call("GET", _prefix + { _defaultPrefix } + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("query", query)) + "/wordStatsGlobal" + play.core.routing.queryString(List(if(id == -1) None else Some(implicitly[play.api.mvc.QueryStringBindable[Long]].unbind("id", id)))))
      
      }
    
    }
  
    // @LINE:10
    def searchBySkill(skillId:String, skillName:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "projectBySkills/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("skillId", skillId)) + "/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("skillName", skillName)))
    }
  
    // @LINE:7
    def profilePage(ownerId:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "profilePage/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("ownerId", ownerId)))
    }
  
    // @LINE:6
    def index(searchKeyword:String = ""): Call = {
      
      Call("GET", _prefix + play.core.routing.queryString(List(if(searchKeyword == "") None else Some(implicitly[play.api.mvc.QueryStringBindable[String]].unbind("searchKeyword", searchKeyword)))))
    }
  
    // @LINE:11
    def socket: Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "ws")
    }
  
  }

  // @LINE:15
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:15
    def versioned(file:Asset): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[Asset]].unbind("file", file))
    }
  
  }


}
