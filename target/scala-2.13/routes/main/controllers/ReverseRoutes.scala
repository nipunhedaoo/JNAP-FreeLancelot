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
  
    // @LINE:7
    def wordStats(query:String, id:Long): Call = {
    
      (query: @unchecked, id: @unchecked) match {
      
        // @LINE:7
        case (query, id)  =>
          
          Call("GET", _prefix + { _defaultPrefix } + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("query", query)) + "/wordStats/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)))
      
      }
    
    }
  
    // @LINE:9
    def searchBySkill(skillId:String, skillName:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "projectBySkills/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("skillId", skillId)) + "/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("skillName", skillName)))
    }
  
  }

<<<<<<< HEAD
  // @LINE:12
=======
  // @LINE:13
>>>>>>> nipun-task4-1
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
<<<<<<< HEAD
    // @LINE:12
=======
    // @LINE:13
>>>>>>> nipun-task4-1
    def versioned(file:Asset): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[Asset]].unbind("file", file))
    }
  
  }


}
