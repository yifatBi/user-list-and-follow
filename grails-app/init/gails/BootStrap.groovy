package gails

class BootStrap {

    def init = { servletContext ->
        for(i in 1..5) {
           def group= new UserGroup(name: 'group' + i).save()
           def user1=new User(name:'user'+i,group: group).save()
           def user2=new User(name:'user'+i,group: group).save()
            new Follow(user: user1,follower: user2).save()
        }

    }
    def destroy = {
    }
}
