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
<div class="form-box">
    <div class="header">Notice #{notice.id}</div>
    <div class="body">
        <div class="fieldwithtext">
            <p><i>By ${notice.user.login} at ${notice.creationTime}</i></p>
            <p>${notice.text}</p>
            <br/>
            <#if notice.tags?has_content>
                tags:
            </#if>
            <#list notice.tags as tag>
                #${tag.name?html}
            </#list>
        </div>

        <#if user??>
            <form method="post" action="/notice/${notice.id}">
                <div class="field">
                    <div class="name">
                        <label for="text">Comment this:</label>
                    </div>
                    <div class="value">
                        <textarea id="text" rows="3" name="text">${new_comment.text!?html}</textarea>
                    </div>
                    <@error 'new_comment.text'/>
                </div>
                <div class="button-field">
                    <input type="submit" value="Comment">
                </div>
            </form>
        <#else>
            <div class="fieldWithText">
                Log in to comment
            </div>
        </#if>

        <#list notice.comments as comment>
            <div class="field">
                <div class="name">
                    ${comment.user.login!?html}:
                </div>
                <div class="value">
                    ${comment.text!?html}
                </div>
            </div>
        </#list>
    </div>
</div>
</@c.page>