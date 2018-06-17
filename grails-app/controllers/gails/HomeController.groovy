package gails

import grails.converters.JSON


class HomeController {

    def currentUser=null

    def getUserData(){
        def cookieValue=request?.cookies?.find{it.name=='user-id'}?.value
        if(!cookieValue){
            response.status=403
            render "there is no cookie,please allow cookies"
        }
        currentUser=User.findById(Integer.parseInt(cookieValue))
        if(!currentUser){
            response.status=403
            render "there is no cookie,please allow cookies"
        }

        render new JSON(userId:currentUser.id,userName: currentUser.name,following:Follow.findAllByFollower(currentUser)?.user?.id)
    }

    //follow user
    def followUser(Integer userId,Integer followersNum){
        User user=User.findById(userId)
        //validate that there is a connected user
        if(currentUser){
            def followingData = Follow.findByFollowerAndUser(currentUser, user)
            //there is a following row
            if (followingData) {
                followingData.delete(flush: true)
                render new JSON(action: 'follow', followerNum: followersNum - 1)
            } else {
                new Follow(follower: currentUser, user: user).save(flush: true)
                render new JSON(action: 'following', followerNum: followersNum + 1)
            }
        }else{
            response.status=403
            render ''
        }
    }

    def index(Integer max) {
        respond User.list().sort(), model:[userCount: User.count()],view: 'index'
    }
}
