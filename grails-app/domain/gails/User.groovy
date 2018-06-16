package gails
@SuppressWarnings('GrailsDomainReservedSqlKeywordName')
class User {
    Integer id
    String name
    UserGroup group
//    static belongsTo = [ group: Group]

    static constraints = {
    }
    String toString() {
        name
    }
    Integer myFollowers(){
        return Follow.countByUser(this)
    }
    boolean isFollowedBy(def user){
        return Follow.findByFollowerAndUser(User.get(user),this)
    }
}
