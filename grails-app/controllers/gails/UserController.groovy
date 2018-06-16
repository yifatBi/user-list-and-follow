package gails

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import static org.springframework.http.HttpStatus.*

class UserController {

    static namespace = 'scaffolding'

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def followUser(Integer userId,Integer follow){
        User user=User.findById(userId)
        User follower=User.findById(follow)
        def followersNum=user.myFollowers();
        def followingData= Follow.findByFollowerAndUser(follower,user)
        //there is a following row
        if(followingData){
            followingData.delete(flush: true)
            render new JSON(action:'follow', followerNum:followersNum-1)
        }else{
            new Follow(follower: follower,user: user).save(flush:true)
            render new JSON(action:'following', followerNum:followersNum+1)
        }
    }
    boolean isUserFollow(Integer followerId,Integer userId){
        return Follow.findByFollowerAndUser(followerId,userId)
    }
    def index(Integer max) {
        respond User.list().sort{ a, b -> a.name <=> b.name ?: a.group <=> b.group ?: -(a.myFollowers() <=> b.myFollowers())
        }, model:[userCount: User.count()]
    }
    def show(User vehicle) {
        respond vehicle
    }

    @SuppressWarnings(['FactoryMethodName', 'GrailsMassAssignment'])
    def create() {
        respond new User(params)
    }

    @Transactional
    def save(User user) {
        if (user == null) {
            return
        }

        if (user.hasErrors()) {
            respond user.errors, view:'create'
            return
        }

        user.save flush:true

        request.withFormat {
            form multipartForm {

                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect vehicle
            }
            '*' { respond user, [status: CREATED] }
        }
    }
}
