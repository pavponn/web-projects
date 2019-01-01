<#import "./common.ftlh" as c>

<@c.page>
    <#if userInfo??>
        <p>UserId: ${userInfo.id!}</p>
        <p>Login: ${userInfo.login!}</p>
        <p>Creation Time: ${userInfo.creationTime!}</p>
    <#else>
    <p>No such User</p>
    </#if>
</@c.page>


