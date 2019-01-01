<#import "common.ftl" as c>

<@c.page>

<div class="form-box">
    <div class="header">Add News</div>
    <div class="body">
        <form method="post" action="/addNews">
            <input type="hidden" name="action" value="addNews">
            <div>
                <textarea name="textArea" cols="45" rows="15" placeholder="Tell us what's going on"></textarea>
            </div>
            <div class="button-field">
                <input type="submit" value="Post">
            </div>
            <#if error??>
                <div class="error">
                    ${error}
                </div>
            </#if>
        </form>
    </div>
</div>
<#---->



</@c.page>
