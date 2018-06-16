<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
    <p class="text-center">Welcome, User1 <div class="text-center">Choose users to follow:</div></p>
            <table class="table">
                <thead class="thead-dark">
                <tr class="text-capitalize">
                    <th >id</th>
                    <th >name</th>
                    <th >group</th>
                    <th >followersNum</th>
                    <th >isFollow</th>
                </tr>
                </thead>
                <tbody>
                <g:each var="user" in="${userList}">
                    <tr class="${user.id==1?'table-success':''}">
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.group}</td>
                    <td id="followersNum${user.id}">${user.myFollowers()}</td>
                    <td><button class="text-capitalize ${user.isFollowedBy(1) ? 'following':'follow'}" ${(user.id==1)?"disabled":""} data-id="${user.id}">${user.isFollowedBy(1) ? 'following':'follow'}</button></td>
                    </tr>
                </g:each>
                </tbody>
            </table>
    </body>
</html>