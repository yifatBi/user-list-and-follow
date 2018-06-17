package gails
class Follow {
    Integer id
    User user
    User follower
    static constraints = {
        user validator: { val,obj ->
            return val.id != obj.follower.id
        }
    }
}
