package gails
@SuppressWarnings('GrailsDomainReservedSqlKeywordName')
class Follow {
    User user
    User follower
    static constraints = {
        user validator: { val,obj ->
            return val.id != obj.follower.id
        }
    }
}
