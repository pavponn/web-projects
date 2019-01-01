<#import "./common.ftlh" as c>

<@c.page>
<div class="datatable">
    <div class="caption">Users</div>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Login</th>
            <th>Created At</th>
            <th>Disable/Enable</th>
        </tr>
        </thead>
        <tbody>
    <#list users as user>
    <tr>
        <form method="post" action="/switch">
            <td>${user.id}</td>
            <td><a href="/user/${user.id}">${user.login}</td>
            <td>${user.creationTime}</td>
            <td>
                <div class="button-field">
                    <#if user.isDisabled() == true>
                        <input type="submit" value="Enable">
                    <#else>
                         <input type="submit" value="Disable">
                    </#if>
                </div>
            </td>
            <input type="hidden" name="userId" value="${user.id}">
        </form>
    </tr>
    </#list>
        </tbody>
    </table>
</div>
</@c.page>
