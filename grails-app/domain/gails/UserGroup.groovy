package gails

class UserGroup implements Comparable{
    Integer id
    String name
    static constraints = {
    }
    String toString(){
        return name
    }

    @Override
    int compareTo(Object obj) {
        return name <=> obj.name
    }
}
