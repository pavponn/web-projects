<#import "/spring.ftl" as spring>
<#import "./common.ftlh" as c>

<#macro error field>
    <@spring.bind field/>
    <#list spring.status.errorMessages as error>
        <div class="error">${error}</div>
        <#break/>
    </#list>
</#macro>

<@c.page>
<div class="addNotice form-box">
    <div class="header">Add Notice</div>
    <div class="body">
        <form method="post" action="/addNotice">
            <input type="hidden" name="action" value="addNotice">
            <textarea name="content" value="${submitForm.content!}">What's new?</textarea>
            <div class="button-field">
                <input type="submit" value="Submit">
            </div>
            <@error 'submitForm.content'/>
        </form>
    </div>
</div>
</@c.page>
