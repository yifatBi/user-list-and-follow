package gails

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(redirect: "/user/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
