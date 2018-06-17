<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <asset:stylesheet src="application.css"/>
        <asset:javascript src="application.js"/>
        <title>User list and followers</title>
    </head>
    <body>
    <div class="text-center mt-2 row justify-content-md-center">
        <div id="no-cookie">Must be signed in</div>
        <div id="content" class="d-none col-6">
            <p>Welcome, <span id="userName"></span><div>Choose users to follow:</div></p>
            <table class="table">
                <thead class="thead-dark">
                <tr class="text-capitalize">
                    <th class="w-25">name</th>
                    <th class="w-25">group</th>
                    <th class="w-25">followersNum</th>
                    <th class="w-25">isFollow</th>
                </tr>
                </thead>
                <tbody>
                <g:each var="user" in="${userList}">
                    <tr id="row${user.id}">
                        <td>${user.name}</td>
                        <td>${user.group}</td>
                        <td id="followersNum${user.id}">${user.myFollowers()}</td>
                        <td><button id="isFollow${user.id}" class="text-capitalize follow" data-id="${user.id}">follow</button></td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>
    </div>
    <div id="spinner" class="spinner" style="display:none;">
        <g:message default="Loading&hellip;"/>
    </div>
    </body>
</html>