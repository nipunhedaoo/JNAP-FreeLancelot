// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers.javascript {

  // @LINE:6
  class ReverseHomeController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:6
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.index",
      """
        function(searchKeyword0,skill1) {
          return _wA({method:"GET", url:"""" + _prefix + """" + _qS([(searchKeyword0 == null ? null : (""" + implicitly[play.api.mvc.QueryStringBindable[String]].javascriptUnbind + """)("searchKeyword", searchKeyword0)), (skill1 == null ? null : (""" + implicitly[play.api.mvc.QueryStringBindable[String]].javascriptUnbind + """)("skill", skill1))])})
        }
      """
    )
  
    // @LINE:7
    def searchBySkill: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.searchBySkill",
      """
        function(skillName0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "projectBySkills/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("skillName", skillName0))})
        }
      """
    )
  
    // @LINE:8
    def profilePage: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.profilePage",
      """
        function(ownerId0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "profilePage/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("ownerId", ownerId0))})
        }
      """
    )
  
    // @LINE:7
    def wordStats: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.wordStats",
      """
        function(query0,id1) {
        
          if (true) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("query", query0)) + "/wordStats/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id", id1))})
          }
        
        }
      """
    )
  
    // @LINE:9
    def searchBySkill: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.searchBySkill",
      """
        function(skillId0,skillName1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "projectBySkills/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("skillId", skillId0)) + "/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("skillName", skillName1))})
        }
      """
    )
  
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
    def versioned: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.versioned",
      """
        function(file1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[play.api.mvc.PathBindable[Asset]].javascriptUnbind + """)("file", file1)})
        }
      """
    )
  
  }


}
