package gails

class UserGroup {
    Integer id
    String name
    static constraints = {
    }
    String toString(){
        return name
    }
}
