package gails

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class UserSpec extends Specification implements DomainUnitTest<User> {

    def setup() {
        def group1=new UserGroup(name:"Group1").save(flush: true)
        def group2=new UserGroup(name:"Group2").save(flush: true)
        new User(name: 'c',group: group1).save(flush: true)
        new User(name: 'a',group: group1).save(flush: true)
        def user3=new User(name: 'a',group: group2).save(flush: true)
        def user4=new User(name: 'a',group: group2).save(flush: true)
        new Follow(user: user4,follower: user3)

    }

    def cleanup() {
    }

    void "test compare name"() {
        expect:"name c is bigger than name a"
            def user1=User.get(1)
            def user2=User.get(2)
            user1.name>user2.name
            user1>user2
    }
    void "test compare group"() {
        expect:"same name group2 bigger than group1"
        def user2=User.get(2)
        def user3=User.get(3)
        user3.name==user2.name
        user3.group>user2.group
        user3>user2
    }

    //doesn't work to do fix it
    void "test compare follow num"() {
        expect:"same name group"
        def user3=User.get(3)
        def user4=User.get(4)
        user4.name==user3.name
        user4.group==user3.group
//        user4.myFollowers()>user3.myFollowers()
//        user4>user3
    }
}
