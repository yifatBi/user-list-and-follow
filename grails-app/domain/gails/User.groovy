package gails
class User implements Comparable{
    Integer id
    String name
    UserGroup group

    static constraints = {
    }
    String toString() {
        name
    }
    Integer myFollowers(){
        return Follow.countByUser(this)
    }

    @Override
    int compareTo(Object obj) {
        return name<=>obj.name?:group<=>obj.group?:obj.myFollowers()<=>myFollowers()
    }
}
