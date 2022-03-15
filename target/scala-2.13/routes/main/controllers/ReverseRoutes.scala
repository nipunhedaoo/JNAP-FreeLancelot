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

  
    // @LINE:6
    def index(searchKeyword:String = "", skill:String = ""): Call = {
      
      Call("GET", _prefix + play.core.routing.queryString(List(if(searchKeyword == "") None else Some(implicitly[play.api.mvc.QueryStringBindable[String]].unbind("searchKeyword", searchKeyword)), if(skill == "") None else Some(implicitly[play.api.mvc.QueryStringBindable[String]].unbind("skill", skill)))))
    }
  
    // @LINE:7
    def searchBySkill(skillName:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "projectBySkills/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("skillName", skillName)))
    }
  
    // @LINE:8
    def profilePage(ownerId:Long): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "profilePage/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("ownerId", ownerId)))
    }
  
  }

  // @LINE:12
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:12
    def versioned(file:Asset): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[Asset]].unbind("file", file))
    }
  
  }


}
